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
package com.github.weisj.swingdsl.components

import java.util.*
import javax.swing.JTree
import javax.swing.event.TreeModelEvent
import javax.swing.event.TreeModelListener
import javax.swing.event.TreeSelectionEvent
import javax.swing.event.TreeSelectionListener
import javax.swing.tree.TreeModel
import javax.swing.tree.TreeNode
import javax.swing.tree.TreeSelectionModel

interface BreadcrumbModel<NodeType, T> {
    val breadcrumbs: List<NodeType>

    fun getChildren(node: NodeType): Sequence<NodeType>
    fun getChildCount(node: NodeType): Int

    fun getValue(node: NodeType): T

    fun addBreadCrumbModelListener(listener: BreadCrumbModelListener)
    fun removeBreadCrumbModelListener(listener: BreadCrumbModelListener)
}

interface BreadCrumbModelListener {
    fun nodesChanged()
}

class ListBreadcrumbModel<T> : BreadcrumbModel<Int, T> {
    private val listeners = mutableListOf<BreadCrumbModelListener>()
    var nodes: List<T> = Collections.emptyList()
        set(value) {
            field = value
            listeners.forEach { it.nodesChanged() }
        }

    override val breadcrumbs: List<Int>
        get() = nodes.indices.toList()

    override fun getChildCount(node: Int): Int {
        return if (node != nodes.size - 1) 1 else 0
    }

    override fun getChildren(node: Int): Sequence<Int> {
        return if (node != nodes.size - 1) sequenceOf(node + 1) else emptySequence()
    }

    override fun getValue(node: Int): T = nodes[node]

    override fun addBreadCrumbModelListener(listener: BreadCrumbModelListener) {
        listeners.add(listener)
    }

    override fun removeBreadCrumbModelListener(listener: BreadCrumbModelListener) {
        listeners.remove(listener)
    }
}

@Suppress("UNCHECKED_CAST")
class TreeBreadCrumbModel<NodeType : TreeNode, T>(
    private val treeModel: TreeModel,
    private val treeSelectionModel: TreeSelectionModel,
    private val showRootNode: Boolean = true,
    private val nodeAccessor: (NodeType) -> T
) : BreadcrumbModel<NodeType, T> {

    constructor(tree: JTree, nodeAccessor: (NodeType) -> T) :
        this(tree.model, tree.selectionModel, tree.isRootVisible, nodeAccessor)

    override val breadcrumbs: List<NodeType>
        get() {
            val crumbs = treeSelectionModel.leadSelectionPath?.run {
                path.asSequence().toList() as List<NodeType>
            } ?: emptyList()
            return if (!showRootNode) crumbs.drop(1) else crumbs
        }

    override fun getChildren(node: NodeType): Sequence<NodeType> {
        return node.children().asSequence() as Sequence<NodeType>
    }

    override fun getChildCount(node: NodeType): Int {
        return treeModel.getChildCount(node)
    }

    override fun getValue(node: NodeType): T {
        return nodeAccessor(node)
    }

    override fun addBreadCrumbModelListener(listener: BreadCrumbModelListener) {
        treeSelectionModel.addTreeSelectionListener(TreeSelectionListenerWrapper(listener))
        treeModel.addTreeModelListener(TreeModelListenerWrapper(listener))
    }

    override fun removeBreadCrumbModelListener(listener: BreadCrumbModelListener) {
        treeSelectionModel.removeTreeSelectionListener(TreeSelectionListenerWrapper(listener))
        treeModel.removeTreeModelListener(TreeModelListenerWrapper(listener))
    }

    private data class TreeSelectionListenerWrapper(private val listener: BreadCrumbModelListener) :
        TreeSelectionListener {
        override fun valueChanged(e: TreeSelectionEvent?) {
            listener.nodesChanged()
        }
    }

    private data class TreeModelListenerWrapper(private val listener: BreadCrumbModelListener) :
        TreeModelListener {
        override fun treeNodesChanged(e: TreeModelEvent?) = listener.nodesChanged()
        override fun treeNodesInserted(e: TreeModelEvent?) = listener.nodesChanged()
        override fun treeNodesRemoved(e: TreeModelEvent?) = listener.nodesChanged()
        override fun treeStructureChanged(e: TreeModelEvent?) = listener.nodesChanged()
    }
}
