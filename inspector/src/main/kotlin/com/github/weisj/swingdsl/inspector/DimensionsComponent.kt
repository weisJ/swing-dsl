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
package com.github.weisj.swingdsl.inspector

import com.github.weisj.swingdsl.dsl.border.emptyBorder
import com.github.weisj.swingdsl.dsl.style.DynamicUI
import com.github.weisj.swingdsl.dsl.util.SharedLazyComponents
import com.github.weisj.swingdsl.util.bottom
import com.github.weisj.swingdsl.util.drawCenteredString
import com.github.weisj.swingdsl.util.drawDottedRectangle
import com.github.weisj.swingdsl.util.drawRect
import com.github.weisj.swingdsl.util.fillRect
import com.github.weisj.swingdsl.util.left
import com.github.weisj.swingdsl.util.right
import com.github.weisj.swingdsl.util.setupStrokePainting
import com.github.weisj.swingdsl.util.subtract
import com.github.weisj.swingdsl.util.top
import com.github.weisj.swingdsl.util.xCenter
import com.github.weisj.swingdsl.util.yCenter
import java.awt.Component
import java.awt.Dimension
import java.awt.FontMetrics
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Insets
import java.awt.Rectangle
import javax.swing.JComponent
import javax.swing.border.Border

internal class DimensionsComponent(private var component: Component) : JComponent() {
    var componentWidth = 0
    var componentHeight = 0
    var componentBorder: Border? = null
    var componentInsets: Insets? = null
    fun update(comp: Component?) {
        comp ?: return
        componentWidth = comp.width
        componentHeight = comp.height
        if (comp is JComponent) {
            componentBorder = comp.border
            componentInsets = comp.insets
        }
        component = comp
        repaint()
    }

    override fun paintComponent(g: Graphics) {
        if (g !is Graphics2D) return
        g.color = background
        g.fillRect(0, 0, width, height)
        g.setupStrokePainting()

        val bounds = bounds
        val insets = insets

        g.color = background
        g.fillRect(bounds.subtract(insets))

        val sizeString = component.size.render()

        val fm = g.fontMetrics
        val sizeWidth = fm.stringWidth(sizeString)
        val fontHeight = fm.height

        val innerBoxWidthGap = 20
        val innerBoxHeightGap = 5
        val boxSize = 15

        val centerX = bounds.width / 2
        val centerY = bounds.height / 2

        val innerRect = Rectangle(
            centerX - sizeWidth / 2 - innerBoxWidthGap,
            centerY - fontHeight / 2 - innerBoxHeightGap,
            sizeWidth + innerBoxWidthGap * 2,
            fontHeight + innerBoxHeightGap * 2
        )

        g.color = foreground
        g.drawCenteredAtPoint(fm, fontHeight, sizeString, centerX, centerY)

        g.drawRect(innerRect, lineWidth = 1)

        val borderInsets: Insets? = componentBorder?.getBorderInsets(component)
        val borderRect = Rectangle(innerRect).also { it.grow(boxSize, boxSize) }
        g.drawDottedRectangle(borderRect)
        g.drawInsets(fm, "border", borderInsets, boxSize, fontHeight, innerRect)

        borderRect.grow(boxSize, boxSize)
        g.drawRect(borderRect, lineWidth = 1)
        g.drawInsets(fm, "insets", componentInsets, boxSize * 2, fontHeight, innerRect)
    }

    override fun getMinimumSize(): Dimension = Dimension(120, 120)

    override fun getPreferredSize(): Dimension = Dimension(150, 150)

    private fun Graphics2D.drawInsets(
        fm: FontMetrics,
        name: String,
        insets: Insets?,
        offset: Int,
        fontHeight: Int,
        innerRect: Rectangle
    ) {
        color = foreground
        drawString(name, innerRect.x - offset + 5, innerRect.y - offset + fontHeight)

        val shift = 7
        val outerRect = Rectangle(innerRect).also { it.grow(offset - shift, offset - shift) }
        drawCenteredAtPoint(
            fm, fontHeight, insets?.top?.toString() ?: "-",
            outerRect.xCenter(), outerRect.top()
        )
        drawCenteredAtPoint(
            fm, fontHeight, insets?.bottom?.toString() ?: "-",
            outerRect.xCenter(), outerRect.bottom()
        )
        drawCenteredAtPoint(
            fm, fontHeight, insets?.left?.toString() ?: "-",
            outerRect.left(), outerRect.yCenter()
        )
        drawCenteredAtPoint(
            fm, fontHeight, insets?.right?.toString() ?: "-",
            outerRect.right(), outerRect.yCenter()
        )
    }

    private fun Graphics2D.drawCenteredAtPoint(fm: FontMetrics, fontHeight: Int, text: String, x: Int, y: Int) {
        val width = fm.stringWidth(text)
        drawCenteredString(Rectangle(x - width / 2, y - fontHeight / 2, width, fontHeight), text)
    }

    init {
        isOpaque = true
        background = SharedLazyComponents.tree.background
        border = emptyBorder(5, 0, 5, 0)
        DynamicUI.withDynamic(this) {
            it.font = SharedLazyComponents.label.font.deriveFont(9f)
        }
        update(component)
    }
}
