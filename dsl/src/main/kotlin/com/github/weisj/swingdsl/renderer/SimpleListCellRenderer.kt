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
package com.github.weisj.swingdsl.renderer

import java.awt.Component
import javax.swing.Icon
import javax.swing.JLabel
import javax.swing.JList
import javax.swing.ListCellRenderer
import javax.swing.UIManager
import javax.swing.border.Border

/*
 * MIT License
 *
 * Copyright (c) 2020 Jannis Weis
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

open class SimpleListCellRenderer<T> : JLabel(), ListCellRenderer<T> {
    override fun getListCellRendererComponent(
        list: JList<out T>,
        value: T,
        index: Int,
        isSelected: Boolean,
        cellHasFocus: Boolean
    ): Component {
        componentOrientation = list.componentOrientation
        customize(value)
        isEnabled = list.isEnabled
        font = list.font
        var border: Border? = null
        if (cellHasFocus) {
            if (isSelected) {
                border = UIManager.getBorder("List.focusSelectedCellHighlightBorder")
            }
            if (border == null) {
                border = UIManager.getBorder("List.focusCellHighlightBorder")
            }
        } else {
            border = UIManager.getBorder("List.cellNoFocusBorder")
        }
        setBorder(border)
        return this
    }

    protected open fun customize(value: T?) {
        if (value is Icon) {
            icon = value
            setText("")
        } else {
            icon = null
            setText(value?.toString() ?: "")
        }
    }

    companion object {
        fun <T> create(toText: StringMapper<T>): SimpleListCellRenderer<T?> {
            return StringMapperListCellRenderer(toText)
        }

        fun <T> create(customize: (JLabel, T) -> Unit): SimpleListCellRenderer<T?> {
            return object : SimpleListCellRenderer<T?>() {
                override fun customize(value: T?) {
                    value?.let { customize(this, it) }
                }
            }
        }
    }
}

fun interface StringMapper<T> {
    fun stringValueOf(value: T): String
}

interface HasStringMapper<T> {
    val mapper: StringMapper<T>
}

internal class StringMapperListCellRenderer<T>(override val mapper: StringMapper<T>) :
    SimpleListCellRenderer<T?>(),
    HasStringMapper<T> {
    override fun customize(value: T?) {
        value?.let {
            text = mapper.stringValueOf(it)
        }
    }
}
