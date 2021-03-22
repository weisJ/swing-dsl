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
package com.github.weisj.swingdsl.component

import com.github.weisj.swingdsl.Row
import com.github.weisj.swingdsl.style.DynamicUI
import com.github.weisj.swingdsl.text.Text
import java.awt.Color
import java.awt.Component
import java.awt.Cursor
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.Icon
import javax.swing.JComponent

open class TitledSeparator(title: Text?) : JComponent() {

    init {
        DynamicUI.withTitledDivider(this, title = title)
    }

    override fun getMinimumSize(): Dimension {
        return insets.run {
            Dimension(left + right, bottom + top)
        }
    }

    override fun getMaximumSize(): Dimension {
        return Dimension(Int.MAX_VALUE, preferredSize.height)
    }

    override fun getPreferredSize(): Dimension = minimumSize
}

class HideableTitledSeparator(title: Text) : TitledSeparator(title) {

    private var isExpanded: Boolean = true
    private lateinit var icon: Icon
    private lateinit var disabledIcon: Icon
    lateinit var row: Row

    fun expand() = update(true)

    fun collapse() = update(false)

    init {
        cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        updateIcon(isExpanded)
        addMouseListener(object : MouseAdapter() {
            override fun mouseReleased(e: MouseEvent) {
                update(!isExpanded)
            }
        })
    }

    private fun update(expand: Boolean) {
        if (isExpanded == expand) return
        isExpanded = expand
        row.visible = expand
        row.subRowsVisible = expand
        updateIcon(expand)
        repaint()
    }

    private fun updateIcon(expand: Boolean) {
        icon = if (expand) ColorIcon(Color.RED) else ColorIcon((Color.BLUE))
        disabledIcon = ColorIcon(Color.GRAY)
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        icon.paintIcon(this, g, 0, 0)
    }
}

private class ColorIcon(private val col: Color) : Icon {
    override fun paintIcon(c: Component?, g: Graphics?, x: Int, y: Int) {
        g?.run {
            color = col
            fillRect(0, 0, iconWidth, iconHeight)
        }
    }

    override fun getIconWidth(): Int = 16

    override fun getIconHeight(): Int = 16
}
