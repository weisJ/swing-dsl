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
import com.github.weisj.swingdsl.util.drawRoundedRectangle
import com.github.weisj.swingdsl.util.fillRect
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Rectangle
import java.awt.geom.Area
import java.awt.geom.RoundRectangle2D
import javax.swing.JComponent
import kotlin.math.min

interface RegionPainter {
    fun paint(g: Graphics2D, bounds: List<Rectangle>)
}

abstract class IndividualRegionPainter : RegionPainter {
    abstract fun paint(g: Graphics2D, bounds: Rectangle)

    override fun paint(g: Graphics2D, bounds: List<Rectangle>) {
        bounds.forEach { paint(g, it) }
    }
}

class RectanglePainter(
    private val borderColor: Color = Color(200, 0, 0),
    private val fillColor: Color = Color(255, 0, 0, 50),
) : IndividualRegionPainter() {
    override fun paint(g: Graphics2D, bounds: Rectangle) {
        g.color = borderColor
        g.drawRect(bounds)
        g.color = fillColor
        g.fillRect(bounds)
    }
}

class MaskedOvalPainter(
    private val maskColor: Color = Color(0, 0, 0, 80),
    private val lineColor: Color,
    private val lineWidth: Float = 2f,
) : RegionPainter {
    override fun paint(g: Graphics2D, bounds: List<Rectangle>) {
        val roundedRects = bounds.map {
            val maxArc = 25f
            var arc = min(it.height, it.width).toFloat()
            var factor = 2
            if (arc > maxArc) {
                arc = maxArc
                factor = 4
            }
            var x = it.x - arc / (2 * factor)
            var y = it.y - arc / (2 * factor)
            var width = it.width + arc / factor
            var height = it.height + arc / factor
            if (it.height <= it.width) {
                x -= arc / (2 * factor)
                width += arc / factor
            } else {
                y -= arc / (2 * factor)
                height += arc / factor
            }
            arc = min(min(width, height), maxArc)
            RoundRectangle2D.Float(x, y, width, height, arc, arc)
        }.toList()
        val area = Area(g.clipBounds)
        roundedRects.forEach { area.subtract(Area(it)) }
        g.color = maskColor
        g.fill(area)

        g.color = lineColor
        roundedRects.forEach {
            g.drawRoundedRectangle(it.bounds2D, it.arcwidth, lineWidth, inside = false)
        }
    }
}

class ComponentHighlighter(private val painter: RegionPainter = RectanglePainter()) : JComponent() {

    var targets: List<LayoutTag> = emptyList()
        set(value) {
            field = value
            repaint()
        }

    init {
        isOpaque = false
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        if (g !is Graphics2D) return
        painter.paint(g, targets.map { it.getBoundsIn(this) })
    }
}
