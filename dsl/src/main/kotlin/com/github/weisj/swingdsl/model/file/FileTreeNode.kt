/*
 * MIT License
 *
 * Copyright (c) 2021 Jannis Weis
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package com.github.weisj.swingdsl.model.file

import com.github.weisj.swingdsl.BackgroundWorker
import com.github.weisj.swingdsl.component.Hideable
import java.nio.file.WatchKey
import java.util.*
import javax.swing.Timer
import javax.swing.tree.TreeNode
import javax.swing.tree.TreePath
import kotlin.streams.asSequence

open class FileTreeNode(
    private val parentNode: FileTreeNode?,
    protected val model: FileTreeModel,
    val fileNode: FileNode
) : TreeNode, Comparable<FileTreeNode>, Hideable {

    internal var watchKey: WatchKey? = null
    private val backgroundWorker = BackgroundWorker()
    private val isBusy: Boolean
        get() = backgroundWorker.isBusy

    private lateinit var _children: MutableList<FileTreeNode>
    private var lazyChildren: Lazy<MutableList<FileTreeNode>> = lazy { loadChildren() }

    val path: TreePath
        get() {
            val parentPath = parentNode?.path
            return if (parentPath != null) {
                parentPath.pathByAddingChild(this)
            } else {
                TreePath(this)
            }
        }

    protected open fun loadChildren(): MutableList<FileTreeNode> {
        _children = Collections.synchronizedList(ArrayList())
        backgroundWorker.runAggregated<Int>(
            task = { publisher ->
                addLoadIndicator()
                traverseChildren { files ->
                    files.filter { model.isShowHiddenFiles || !it.isHidden }
                        .map { with(model) { createChildNode(it) } }
                        .sorted()
                        .map { _children.addChild(it) }
                        .forEach(publisher)
                }
            },
            processor = {
                model.nodesWereInserted(this@FileTreeNode, it.toIntArray())
            },
            finalizer = {
                removeLoadIndicator()
                model.nodeChanged(this@FileTreeNode)
            }
        )
        return _children
    }

    private inline fun traverseChildren(action: (Sequence<FileNode>) -> Unit) {
        if (fileNode.isDirectory) {
            runCatching {
                fileNode.list(model).use { files ->
                    action(files.asSequence().filter { it.isReadable })
                }
            }
        }
    }

    val children: MutableList<FileTreeNode> by lazyChildren

    private val safeChildren: MutableList<FileTreeNode>?
        get() = if (::_children.isInitialized) _children else null

    override val isVisible: Boolean
        get() = true
    override val isEnabled: Boolean
        get() = true

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as FileTreeNode
        return fileNode == that.fileNode
    }

    override fun hashCode(): Int {
        return fileNode.hashCode()
    }

    private fun MutableList<FileTreeNode>.addChild(node: FileTreeNode): Int {
        var index = Collections.binarySearch(this, node)
        if (index < 0) index = index.inv()
        add(index, node)
        model.register(node)
        return index
    }

    private fun MutableList<FileTreeNode>.removeChild(node: FileTreeNode): Int {
        val index = indexOf(node)
        if (index >= 0) {
            removeAt(index)
            model.unregister(node)
        }
        return index
    }

    open fun reload(depth: Int = Int.MAX_VALUE) {
        if (depth < 0) return
        val childList = safeChildren
        if (childList.isNullOrEmpty()) return
        backgroundWorker.runAggregated<ReloadOp>(
            task = { publisher ->
                addLoadIndicator()
                traverseChildren { s ->
                    val nodes = childList.asSequence() + s.map { with(model) { createChildNode(it) } }
                    nodes.mapNotNull {
                        when {
                            it.fileNode.isNonExistent -> {
                                ReloadOp.remove(it, childList.removeChild(it))
                            }
                            model.isShowHiddenFiles && !childList.contains(it) -> {
                                ReloadOp.add(it, childList.addChild(it))
                            }
                            !model.isShowHiddenFiles && it.fileNode.isHidden -> {
                                ReloadOp.remove(it, childList.removeChild(it))
                            }
                            !model.isShowHiddenFiles && !childList.contains(it) -> {
                                ReloadOp.add(it, childList.addChild(it))
                            }
                            else -> null
                        }
                    }.filter { it.index >= 0 }.forEach(publisher)
                }
            },
            processor = { ops ->
                val groupedOps = ops.groupBy(ReloadOp::type)
                val addOps = groupedOps[ReloadOp.Type.ADD]
                val removeOps = groupedOps[ReloadOp.Type.REMOVE]
                if (addOps != null) {
                    model.nodesWereInserted(
                        this@FileTreeNode,
                        addOps.toIntArray { it.index }
                    )
                }
                if (removeOps != null) {
                    model.nodesWereRemoved(
                        this@FileTreeNode,
                        removeOps.toIntArray { it.index },
                        removeOps.toTypedArray { it.node }
                    )
                }
            },
            finalizer = {
                removeLoadIndicator()
                fileNode.invalidate()
                if (depth > 0) childList.forEach {
                    it.reload(depth - 1)
                }
            }
        )
    }

    private fun addLoadIndicator() {
        Timer(500) {
            if (!isBusy) return@Timer
            if (_children.getOrNull(0) is LoadIndicatorNode) return@Timer
            val indicator = LoadIndicatorNode(this, model)
            _children.add(0, indicator)
            model.nodesWereInserted(this, intArrayOf(0))
        }.apply {
            isRepeats = false
            start()
        }
    }

    private fun removeLoadIndicator() {
        val indicator = _children.getOrNull(0) as? LoadIndicatorNode ?: return
        model.nodesWereRemoved(this, intArrayOf(_children.removeChild(indicator)), arrayOf(indicator))
    }

    private inline fun <T> List<T>.toIntArray(mapping: (T) -> Int): IntArray {
        val result = IntArray(size)
        var index = 0
        for (element in this)
            result[index++] = mapping(element)
        return result
    }

    private inline fun <T, reified R> List<T>.toTypedArray(mapping: (T) -> R): Array<R> {
        val iterator = iterator()
        return Array(size) {
            mapping(iterator.next())
        }
    }

    override fun getChildAt(childIndex: Int): TreeNode? {
        return children[childIndex]
    }

    override fun getChildCount(): Int = children.size
    override fun getParent(): FileTreeNode? = parentNode

    override fun getIndex(node: TreeNode): Int {
        if (node.parent != this) return -1
        return children.indexOf(node)
    }

    override fun getAllowsChildren(): Boolean = fileNode.isDirectory

    override fun isLeaf(): Boolean {
        if (!allowsChildren) return true
        val childList = safeChildren
        return if (childList != null && !isBusy) {
            childList.isEmpty()
        } else {
            fileNode.isEmpty(model.isShowHiddenFiles)
        }
    }

    override fun children(): Enumeration<out TreeNode> {
        return Collections.enumeration(children)
    }

    override operator fun compareTo(other: FileTreeNode): Int {
        if (other is LoadIndicatorNode) return 1
        val thisDir = fileNode.isDirectory
        val oDir = fileNode.isDirectory
        return if (thisDir == oDir) {
            fileNode.compareTo(other.fileNode)
        } else {
            if (thisDir) -1 else 1
        }
    }
}

internal class LoadIndicatorNode(
    parentNode: FileTreeNode?,
    model: FileTreeModel,
) : FileTreeNode(parentNode, model, NullFileNode(name = "Loading")) {
    override val isEnabled: Boolean
        get() = false

    override fun loadChildren(): MutableList<FileTreeNode> = mutableListOf()
    override fun isLeaf(): Boolean = true
    override fun getAllowsChildren(): Boolean = false
    override fun compareTo(other: FileTreeNode): Int {
        return if (other is LoadIndicatorNode) 0 else -1
    }

    override fun equals(other: Any?): Boolean {
        if (other is LoadIndicatorNode) return true
        return super.equals(other)
    }

    override fun hashCode(): Int = javaClass.hashCode()
}

private data class ReloadOp constructor(
    val type: Type,
    val node: FileTreeNode,
    val index: Int
) {
    enum class Type {
        ADD, REMOVE
    }

    companion object {
        fun add(n: FileTreeNode, index: Int): ReloadOp {
            return ReloadOp(Type.ADD, n, index)
        }

        fun remove(n: FileTreeNode, index: Int): ReloadOp {
            return ReloadOp(Type.REMOVE, n, index)
        }
    }
}
