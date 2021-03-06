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
package com.github.weisj.swingdsl.dsl.layout

import com.github.weisj.swingdsl.core.binding.MutableProperty
import com.github.weisj.swingdsl.core.binding.PropertyBinding
import com.github.weisj.swingdsl.core.binding.bind
import com.github.weisj.swingdsl.core.binding.toProperty
import com.github.weisj.swingdsl.core.condition.ObservableCondition
import com.github.weisj.swingdsl.core.text.Text
import com.github.weisj.swingdsl.core.text.textOf
import com.github.weisj.swingdsl.core.text.textOfNullable
import com.github.weisj.swingdsl.dsl.BuilderWithEnabledProperty
import com.github.weisj.swingdsl.dsl.ModifiableContainerBuilder
import com.github.weisj.swingdsl.dsl.highlight.StringSearchPointSink
import com.github.weisj.swingdsl.dsl.observableSelected
import com.github.weisj.swingdsl.dsl.style.UIFactory
import com.github.weisj.swingdsl.dsl.unaryPlus
import com.github.weisj.swingdsl.laf.WrappedComponent
import javax.swing.AbstractButton
import javax.swing.ButtonGroup
import javax.swing.JComponent
import javax.swing.JLabel
import kotlin.reflect.KMutableProperty0

interface RowBuilder : ButtonGroupBuilder, ModifiableContainerBuilder<Row>, BuilderWithEnabledProperty<RowBuilder> {

    var subRowsEnabled: Boolean
    var subRowsVisible: Boolean
    val subRowIndentationPolicy: IndentationPolicy

    fun indent(policy: IndentationPolicy)

    fun <T : RowBuilder> T.withIndentationPolicy(policy: IndentationPolicy, init: T.() -> Unit) {
        val oldPolicy = subRowIndentationPolicy
        indent(policy)
        init()
        indent(oldPolicy)
    }

    fun <T : RowBuilder> T.noIndent(init: T.() -> Unit): Unit = withIndentationPolicy(IndentationPolicy.NO, init)
    fun <T : RowBuilder> T.forceIndent(init: T.() -> Unit): Unit = withIndentationPolicy(IndentationPolicy.YES, init)

    fun commitImmediately()

    fun setSearchPointSink(sink: StringSearchPointSink?)
    fun withSearchPointSink(sink: StringSearchPointSink, init: Row.() -> Unit)

    fun subRowsEnabledIf(condition: ObservableCondition) {
        condition.bind(this) {
            subRowsEnabled = it
        }
    }

    fun subRowsEnabledIf(component: AbstractButton) {
        subRowsEnabledIf(component.observableSelected())
    }

    fun subRowsVisibleIf(condition: ObservableCondition) {
        condition.bind(this) {
            subRowsVisible = it
        }
    }

    fun subRowsVisibleIf(component: AbstractButton) {
        subRowsEnabledIf(component.observableSelected())
    }

    // manual JvmOverloads
    fun row(init: Row.() -> Unit): Row = row(null as Text?, init)

    // manual JvmOverloads
    fun row(label: Text?, init: Row.() -> Unit): Row =
        row(label, separated = false, init = init)

    fun row(
        label: String?,
        separated: Boolean = false,
        init: Row.() -> Unit
    ): Row {
        return row(textOfNullable(label), separated, init)
    }

    fun row(
        label: JLabel? = null,
        separated: Boolean = false,
        init: Row.() -> Unit
    ): Row {
        return createChildRow(label = label?.let { +it }, separated).apply(init)
    }

    fun row(
        label: Text?,
        separated: Boolean = false,
        init: Row.() -> Unit
    ): Row {
        return createChildRow(label?.let { UIFactory.createLabel(it) }, separated).apply(init)
    }

    fun fullRow(init: InnerCell.() -> Unit): Row = row { cell(isFullWidth = true, init = init) }

    fun createChildRow(
        label: WrappedComponent<JLabel>? = null,
        isSeparated: Boolean = false,
        isIndented: IndentationPolicy = subRowIndentationPolicy,
        noGrid: Boolean = false,
        title: Text? = null,
        insertionPosition: InsertionPosition = InsertionPosition.DEFAULT
    ): Row

    fun titledRow(title: Text, init: Row.() -> Unit): Row
    fun titledRow(title: String, init: Row.() -> Unit): Row = titledRow(textOf(title), init)

    /**
     * Creates row with hideable decorator.
     * It allows to hide some information under the titled decorator
     */
    fun hideableRow(title: Text, startHidden: Boolean = true, init: Row.() -> Unit): Row
    fun hideableRow(title: String, startHidden: Boolean = true, init: Row.() -> Unit): Row =
        hideableRow(textOf(title), startHidden, init)

    /**
     * Creates row with a huge gap after it, that can be used to group related components.
     * Think of [titledRow] without a title and additional indent.
     */
    fun blockRow(init: Row.() -> Unit): Row

    /**
     * Creates a nested UI DSL panel, with a grid which is independent of this pane.
     */
    fun nestedPanel(title: Text? = null, init: PanelBuilder.() -> Unit): CellBuilder<ModifiablePanel>

    fun createCommentRow(component: JComponent): Row
    fun commentRow(
        text: Text,
        maxLineLength: Int = 70,
        withLeftGap: Boolean = true
    )

    fun commentRow(
        text: String,
        maxLineLength: Int = 70,
        withLeftGap: Boolean = true
    ): Unit = commentRow(textOf(text), maxLineLength, withLeftGap)
}

inline fun <reified T : Any> RowBuilder.buttonGroup(
    prop: KMutableProperty0<T>,
    crossinline init: RowBuilderWithButtonGroupProperty<T>.() -> Unit
) {
    buttonGroup(prop.toProperty(), init)
}

inline fun <reified T : Any> RowBuilder.buttonGroup(
    noinline getter: () -> T,
    noinline setter: (T) -> Unit,
    crossinline init: RowBuilderWithButtonGroupProperty<T>.() -> Unit
) {
    buttonGroup(PropertyBinding(getter, setter), init)
}

inline fun <reified T : Any> RowBuilder.buttonGroup(
    binding: MutableProperty<T>,
    crossinline init: RowBuilderWithButtonGroupProperty<T>.() -> Unit
) {
    withButtonGroup(ButtonGroup()) {
        RowBuilderWithButtonGroupProperty(this, binding).init()
    }
}
