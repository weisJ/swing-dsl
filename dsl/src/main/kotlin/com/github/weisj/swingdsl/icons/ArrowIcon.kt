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
package com.github.weisj.swingdsl.icons

import com.github.weisj.swingdsl.laf.VisualPaddingProvider
import java.awt.Color
import java.awt.Component
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Insets
import java.awt.geom.AffineTransform
import java.awt.geom.Path2D
import javax.swing.Icon

class ArrowDownIcon(color: Color) : ArrowIcon(color) {
    override val path: Path2D = Path2D.Float(Path2D.WIND_EVEN_ODD).apply {
        moveTo(8.0, 6.0)
        lineTo(12.5, 11.0)
        lineTo(3.5, 11.0)
        closePath()
        transform(AffineTransform(1.0, 0.0, 0.0, -1.0, 0.0, 17.0))
    }
}

class ArrowRightIcon(color: Color) : ArrowIcon(color) {
    override val path: Path2D = Path2D.Float(Path2D.WIND_EVEN_ODD).apply {
        moveTo(8.5, 5.5)
        lineTo(13.0, 10.5)
        lineTo(4.0, 10.5)
        closePath()
        transform(AffineTransform(0.0, -1.0, -1.0, 0.0, 16.5, 16.5))
    }
}

sealed class ArrowIcon(private val color: Color) : Icon, VisualPaddingProvider {
    internal abstract val path: Path2D
    override fun paintIcon(c: Component, g: Graphics, x: Int, y: Int) {
        g.color = color
        g.translate(x, y)
        (g as Graphics2D).fill(path)
    }

    override fun getIconWidth(): Int {
        return 16
    }

    override fun getIconHeight(): Int {
        return 16
    }

    override fun getVisualPaddings(component: Component): Insets =
        Insets(5, 5, 5, 5)
}
