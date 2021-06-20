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

import com.github.weisj.swingdsl.invokeLater
import java.io.File
import java.nio.file.Path
import javax.swing.filechooser.FileSystemView

class WatchFileTree : FileTree {

    constructor(showHiddenFiles: Boolean = false, vararg roots: File) : super(
        showHiddenFiles,
        WatchFileTreeModel(
            FileSystemView.getFileSystemView(),
            showHiddenFiles,
            roots = roots.map(::FileNode).ifEmpty { null }
        )
    )

    constructor(showHiddenFiles: Boolean = false, vararg roots: Path) : super(
        showHiddenFiles,
        WatchFileTreeModel(
            FileSystemView.getFileSystemView(),
            showHiddenFiles,
            roots = roots.map(::FileNode).ifEmpty { null }
        )
    )

    override fun changeModel(model: FileTreeModel) {
        if (model === model) {
            super.changeModel(model)
            return
        }
        if (model is WatchFileTreeModel) model.stopWatching()
        if (isVisible && model is WatchFileTreeModel) model.startWatching()
        super.changeModel(model)
    }

    override fun addNotify() {
        super.addNotify()
        invokeLater { (model as? WatchFileTreeModel)?.startWatching() }
    }

    override fun removeNotify() {
        super.removeNotify()
        invokeLater { (model as? WatchFileTreeModel)?.stopWatching() }
    }
}
