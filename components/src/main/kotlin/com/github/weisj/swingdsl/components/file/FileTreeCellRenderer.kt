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

import java.awt.Component
import javax.swing.Icon
import javax.swing.JTree
import javax.swing.UIManager
import javax.swing.filechooser.FileSystemView
import javax.swing.tree.DefaultTreeCellRenderer

class FileTreeCellRenderer(private val fsv: FileSystemView) : DefaultTreeCellRenderer() {
    var fileIcon: Icon? = null
    var directoryIcon: Icon? = null

    override fun updateUI() {
        super.updateUI()
        fileIcon = UIManager.getIcon("FileView.fileIcon")
        directoryIcon = UIManager.getIcon("FileView.directoryIcon")
    }

    override fun getTreeCellRendererComponent(
        tree: JTree,
        value: Any,
        selected: Boolean,
        expanded: Boolean,
        leaf: Boolean,
        row: Int,
        hasFocus: Boolean
    ): Component {
        val node = value as FileTreeNode
        if (node is LoadIndicatorNode) {
            icon = null
            disabledIcon = null
            text = "Loading"
            isEnabled = false
        } else {
            isEnabled = true
            val f = node.fileNode
            icon = getFileIcon(f)
            text = f.getSystemDisplayName(fsv)
        }
        return this
    }

    private fun getFileIcon(f: FileNode): Icon? {
        var icon = f.getSystemIcon(fsv)
        if (icon == null && f.isExistent) {
            icon = if (f.isDirectory) directoryIcon else fileIcon
        }
        return icon
    }
}
