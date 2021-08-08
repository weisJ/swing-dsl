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
package com.github.weisj.swingdsl.components.file

import com.github.weisj.swingdsl.components.HideableTreeSelectionModel
import java.io.File
import java.nio.file.Path
import javax.swing.JTree
import javax.swing.event.TreeExpansionEvent
import javax.swing.event.TreeWillExpandListener
import javax.swing.filechooser.FileSystemView
import javax.swing.tree.TreeModel

open class FileTree protected constructor(
    showHiddenFiles: Boolean,
    model: FileTreeModel
) : JTree(model) {

    constructor(showHiddenFiles: Boolean = false, vararg roots: File) : this(
        showHiddenFiles,
        FileTreeModel(
            FileSystemView.getFileSystemView(),
            showHiddenFiles,
            roots = roots.map(::FileNode).ifEmpty { null }
        )
    )

    constructor(showHiddenFiles: Boolean = false, vararg roots: Path) : this(
        showHiddenFiles,
        FileTreeModel(
            FileSystemView.getFileSystemView(),
            showHiddenFiles,
            roots = roots.map(::FileNode).ifEmpty { null }
        )
    )

    var fileModel: FileTreeModel
        get() = super.getModel() as FileTreeModel
        set(value) = changeModel(value)

    protected open fun changeModel(model: FileTreeModel) {
        super.setModel(model)
    }

    var isShowHiddenFiles: Boolean
        get() = fileModel.isShowHiddenFiles
        set(value) {
            fileModel.isShowHiddenFiles = value
        }
    var preloadDepth: Int
        get() = fileModel.preloadDepth
        set(value) {
            fileModel.preloadDepth = value
        }

    val selectedFile: FileNode?
        get() = (selectionPath?.lastPathComponent as? FileTreeNode)?.fileNode

    val selectedFiles: List<FileNode>
        get() {
            val paths = selectionPaths ?: return emptyList()
            return paths.asSequence()
                .map { it.lastPathComponent }
                .filterIsInstance<FileTreeNode>()
                .map { it.fileNode }
                .toList()
        }

    override fun setModel(newModel: TreeModel) {
        check(newModel is FileTreeModel) { "Model needs to be of type ${FileTreeModel::class.java}" }
        super.setModel(newModel)
    }

    fun reload() {
        fileModel.reload()
    }

    init {
        fileModel = model
        setSelectionModel(HideableTreeSelectionModel(this))
        this.setCellRenderer(FileTreeCellRenderer(FileSystemView.getFileSystemView()))
        isShowHiddenFiles = showHiddenFiles
        isRootVisible = false
        model.rootNode.preload(preloadDepth)
        addTreeWillExpandListener(object : TreeWillExpandListener {
            override fun treeWillExpand(event: TreeExpansionEvent?) {
                event ?: return
                val node = event.path.lastPathComponent as? FileTreeNode ?: return
                node.preload(preloadDepth)
            }

            override fun treeWillCollapse(event: TreeExpansionEvent?) { /* do nothing */ }
        })
    }
}
