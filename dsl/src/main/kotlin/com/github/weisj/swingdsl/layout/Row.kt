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

// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.github.weisj.swingdsl.layout

import com.github.weisj.swingdsl.text.Text
import javax.swing.AbstractButton

abstract class Row : Cell(), RowBuilder {
    abstract var enabled: Boolean
    abstract var visible: Boolean

    abstract var subRowsEnabled: Boolean
    abstract var subRowsVisible: Boolean

    /**
     * Indent for child rows of this row, expressed in steps (multiples of [SpacingConfiguration.indentLevel]). Replaces indent
     * calculated from row nesting.
     */
    abstract var subRowIndentationLevel: Int

    internal abstract val builder: PanelBuilderImpl

    /**
     * Specifies the right alignment for the component if the cell is larger than the component plus its gaps.
     */
    fun right(init: Row.() -> Unit) {
        alignRight()
        init()
    }

    @PublishedApi
    internal abstract fun alignRight()

    abstract fun largeGapAfter()

    /**
     * Shares cell between components.
     *
     * @param isFullWidth If `true`, the cell occupies the full width of the enclosing component.
     */
    inline fun cell(isVerticalFlow: Boolean = false, isFullWidth: Boolean = false, init: InnerCell.() -> Unit) {
        setCellMode(true, isVerticalFlow, isFullWidth)
        InnerCell(this).init()
        setCellMode(false, isVerticalFlow, isFullWidth)
    }

    @PublishedApi
    internal abstract fun setCellMode(value: Boolean, isVerticalFlow: Boolean, fullWidth: Boolean)

    @PublishedApi
    internal abstract fun createRow(label: Text?): Row

    fun attachSubRowsEnabled(component: AbstractButton) {
        subRowsEnabled = component.isSelected
        component.addChangeListener {
            subRowsEnabled = component.isSelected
        }
    }
}
