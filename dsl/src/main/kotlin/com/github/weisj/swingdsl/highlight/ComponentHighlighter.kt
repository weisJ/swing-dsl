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
package com.github.weisj.swingdsl.highlight

import com.github.weisj.swingdsl.util.drawRect
import com.github.weisj.swingdsl.util.fillRect
import java.awt.Color
import java.awt.Graphics
import java.awt.Rectangle
import javax.swing.JComponent

interface RegionPainter {
    fun paint(g: Graphics, bounds: Rectangle)
}

class RectanglePainter : RegionPainter {
    override fun paint(g: Graphics, bounds: Rectangle) {
        g.color = Color(200, 0, 0)
        g.drawRect(bounds)
        g.color = Color(255, 0, 0, 50)
        g.fillRect(bounds)
    }
}

class ComponentHighlighter(private val painter: RegionPainter = RectanglePainter()) : JComponent() {

    var targets: List<LayoutTag> = emptyList()
        set(value) {
            if (field != value) {
                field = value
                repaint()
            }
        }

    init {
        isOpaque = false
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        targets.forEach {
            painter.paint(g, it.getBoundsIn(this))
        }
    }
}
