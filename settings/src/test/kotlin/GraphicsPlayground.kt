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
import com.github.weisj.swingdsl.dsl.borderPanel
import com.github.weisj.swingdsl.dsl.frame
import com.github.weisj.swingdsl.dsl.onSwingThread
import com.github.weisj.swingdsl.dsl.unaryPlus
import com.github.weisj.swingdsl.util.drawRect
import com.github.weisj.swingdsl.util.drawRoundedRectangle
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Rectangle
import javax.swing.JComponent

fun main() = onSwingThread {
    frame {
        content {
            borderPanel {
                center {
                    +object : JComponent() {

                        init {
                            isOpaque = true
                        }

                        override fun paintComponent(g: Graphics?) {
                            super.paintComponent(g)
                            if (g !is Graphics2D) return
                            val rect = Rectangle(30, 30, 500, 150)
                            g.color = Color.RED
                            g.drawRect(rect, lineWidth = 5)
                            g.color = Color.GREEN
                            g.drawRoundedRectangle(rect, arc = 30f, lineWidth = 5f, inside = true)
                            g.color = Color.BLUE
                            g.drawRoundedRectangle(rect, arc = 30f, lineWidth = 5f, inside = false)
                        }
                    }
                }
            }
        }
        title = "GraphicsPlayground"
        minimumSize = Dimension(600, 300)
        visible = true
    }
}
