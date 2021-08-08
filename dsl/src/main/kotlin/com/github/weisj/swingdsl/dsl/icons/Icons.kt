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
package com.github.weisj.swingdsl.dsl.icons

import com.github.weisj.swingdsl.dsl.style.UIFactory
import com.github.weisj.swingdsl.dsl.util.Insets
import com.github.weisj.swingdsl.dsl.util.drawRect
import com.github.weisj.swingdsl.dsl.util.height
import com.github.weisj.swingdsl.dsl.util.width
import java.awt.Color
import java.awt.Component
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Insets
import javax.swing.Icon

class SolidColorIcon(
    private val color: Color,
    private val borderColor: Color = UIFactory.borderColor,
    private val size: Dimension,
    private val insets: Insets = Insets(0)
) : Icon {

    constructor(color: Color, borderColor: Color = UIFactory.borderColor, size: Int = 16, colorSize: Int = 16) :
        this(color, borderColor, Dimension(size, size), Insets((size - colorSize) / 2))

    override fun paintIcon(c: Component?, g: Graphics?, x: Int, y: Int) {
        g ?: return
        val lineWidth = 1
        val realX = x + insets.left
        val realY = y + insets.top
        val realW = size.width - insets.width
        val realH = size.height - insets.height

        g.color = color
        g.fillRect(realX, realY, realW, realH)
        g.color = borderColor
        g.drawRect(realX, realY, realW, realH, lineWidth = lineWidth)
    }

    override fun getIconWidth(): Int = size.width

    override fun getIconHeight(): Int = size.height
}
