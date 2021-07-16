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
import com.github.weisj.darklaf.LafManager
import com.github.weisj.darklaf.theme.IntelliJTheme
import com.github.weisj.swingdsl.borderPanel
import com.github.weisj.swingdsl.components.createFileBreadcrumbBar
import com.github.weisj.swingdsl.components.file.FileTree
import com.github.weisj.swingdsl.frame
import com.github.weisj.swingdsl.onSwingThread
import com.github.weisj.swingdsl.scrollPane
import com.github.weisj.swingdsl.unaryPlus
import java.awt.Dimension
import javax.swing.filechooser.FileSystemView

fun main() = onSwingThread {
    if (true) LafManager.install(IntelliJTheme())
    frame {
        content {
            borderPanel {
                val fsv = FileSystemView.getFileSystemView()
                val rootFiles = fsv.getFiles(fsv.roots[0], true)
                val fileTree = FileTree(showHiddenFiles = false, *rootFiles)
                fileTree.setSelectionRow(0)
                north {
                    +createFileBreadcrumbBar(fileTree)
                }
                center {
                    scrollPane { +fileTree }
                }
            }
        }
        preferredSize = Dimension(300, 300)
        centerOnScreen()
        visible = true
    }
}
