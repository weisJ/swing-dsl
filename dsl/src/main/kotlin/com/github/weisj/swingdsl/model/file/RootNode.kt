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

import java.nio.file.FileSystems

class RootFileNode(
    model: FileTreeModel,
    roots: List<FileNode>?
) : FileTreeNode(null, model, NullFileNode()) {

    private val rootPaths: List<FileNode> = roots ?: emptyList()

    private fun createInitialDirectories(): Sequence<FileNode> = if (rootPaths.isNotEmpty()) {
        rootPaths.asSequence()
    } else {
        FileSystems.getDefault().rootDirectories.asSequence().map(::FileNode)
    }

    override fun loadChildren(): MutableList<FileTreeNode> {
        val nodes: MutableList<FileTreeNode> = ArrayList()
        createInitialDirectories().forEach {
            val node = with(model) { createChildNode(it) }
            model.register(node)
            nodes.add(node)
        }
        return nodes
    }

    override fun reload(depth: Int) {
        if (depth < 0) return
        val childNodes = children
        createInitialDirectories().forEach {
            val node = with(model) { createChildNode(it) }
            if (!childNodes.contains(node)) {
                model.register(node)
                childNodes.add(node)
            }
        }
        childNodes.removeIf {
            if (it.fileNode.isNonExistent) {
                model.unregister(it)
                true
            } else false
        }
        if (depth > 0) children.forEach { it.reload(depth - 1) }
        fileNode.invalidate()
    }

    override fun isLeaf(): Boolean = children.isEmpty()

    override fun getAllowsChildren(): Boolean = true
}
