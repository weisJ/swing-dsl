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

import javax.swing.filechooser.FileSystemView
import javax.swing.tree.DefaultTreeModel

@Suppress("LeakingThis")
open class FileTreeModel(
    val fsv: FileSystemView,
    isShowHiddenFiles: Boolean,
    roots: List<FileNode>?
) : DefaultTreeModel(null) {

    var preloadDepth: Int = -1
    var isShowHiddenFiles: Boolean = isShowHiddenFiles
        set(value) {
            if (field != value) {
                field = value
                reload()
            }
        }
    val rootNode: FileTreeNode
        get() = root as FileTreeNode

    init {
        beforeInit()
        root = RootFileNode(this, roots)
    }

    protected open fun beforeInit() {}

    override fun reload(): Unit = rootNode.reload(Int.MAX_VALUE)

    internal fun FileTreeNode?.createChildNode(fileNode: FileNode?): FileTreeNode {
        return FileTreeNode(this, this@FileTreeModel, fileNode!!)
    }

    internal open fun register(node: FileTreeNode) {}
    internal open fun unregister(node: FileTreeNode) {}
}
