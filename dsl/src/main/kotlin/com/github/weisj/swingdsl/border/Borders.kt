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
package com.github.weisj.swingdsl.border

import com.github.weisj.swingdsl.height
import com.github.weisj.swingdsl.layout.getDefaultSpacingConfiguration
import com.github.weisj.swingdsl.style.UIFactory
import com.github.weisj.swingdsl.width
import java.awt.Color
import java.awt.Component
import java.awt.Graphics
import java.awt.Insets
import javax.swing.BorderFactory
import javax.swing.border.Border

fun emptyBorder(): Border = BorderFactory.createEmptyBorder()
fun emptyBorder(size: Int): Border = emptyBorder(size, size, size, size)
fun emptyBorder(insets: Insets): Border = emptyBorder(insets.top, insets.left, insets.bottom, insets.right)
fun emptyBorder(top: Int = 0, left: Int = 0, bottom: Int = 0, right: Int = 0): Border =
    BorderFactory.createEmptyBorder(top, left, bottom, right)

fun dialogSpacing(): Border = dialogSpacing(top = true, left = true, bottom = true, right = true)

fun dialogSpacing(
    top: Boolean = false,
    left: Boolean = false,
    bottom: Boolean = false,
    right: Boolean = false,
): Border = getDefaultSpacingConfiguration().run {
    emptyBorder(
        if (top) dialogTopBottom else 0,
        if (left) dialogLeftRight else 0,
        if (bottom) dialogTopBottom else 0,
        if (right) dialogLeftRight else 0,
    )
}

fun lineBorder(): Border = lineBorder(1, 1, 1, 1, UIFactory::getBorderColor)

fun lineBorder(insets: Insets, colorSupplier: () -> Color): Border = LineBorder(insets, colorSupplier)
fun lineBorder(insets: Insets, color: Color): Border = LineBorder(insets) { color }
fun lineBorder(top: Int, left: Int, bottom: Int, right: Int, color: Color): Border =
    lineBorder(Insets(top, left, bottom, right), color)

fun lineBorder(top: Int, left: Int, bottom: Int, right: Int, colorSupplier: () -> Color): Border =
    lineBorder(Insets(top, left, bottom, right), colorSupplier)

fun topBorder(colorSupplier: () -> Color): Border = lineBorder(1, 0, 0, 0, colorSupplier)
fun topBorder(color: Color): Border = lineBorder(1, 0, 0, 0, color)

fun bottomBorder(colorSupplier: () -> Color = UIFactory::getBorderColor): Border = lineBorder(0, 0, 1, 0, colorSupplier)
fun bottomBorder(color: Color): Border = lineBorder(0, 0, 1, 0, color)

fun rightBorder(colorSupplier: () -> Color = UIFactory::getBorderColor): Border = lineBorder(0, 0, 0, 1, colorSupplier)
fun rightBorder(color: Color): Border = lineBorder(0, 0, 0, 1, color)

fun leftBorder(colorSupplier: () -> Color = UIFactory::getBorderColor): Border = lineBorder(0, 1, 0, 0, colorSupplier)
fun leftBorder(color: Color): Border = lineBorder(0, 1, 0, 0, color)

class LineBorder(private val insets: Insets, private val colorSupplier: () -> Color) : Border {
    override fun paintBorder(c: Component?, g: Graphics?, x: Int, y: Int, width: Int, height: Int) {
        g ?: return
        c ?: return
        g.translate(x, y)
        g.color = colorSupplier()
        g.fillRect(0, 0, c.width, insets.top)
        g.fillRect(0, insets.top, insets.left, height - insets.height)
        g.fillRect(width - insets.right, insets.top, insets.right, height - insets.height)
        g.fillRect(0, height - insets.bottom, width, insets.bottom)
        g.translate(-x, -y)
    }

    override fun getBorderInsets(c: Component?): Insets = Insets(insets.top, insets.left, insets.bottom, insets.right)

    override fun isBorderOpaque(): Boolean = true
}
