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
package com.github.weisj.swingdsl.settings

import com.github.weisj.swingdsl.binding.MutableProperty
import com.github.weisj.swingdsl.binding.PseudoObservableProperty
import com.github.weisj.swingdsl.binding.container.ObservableList
import com.github.weisj.swingdsl.comboBoxModelOf
import com.github.weisj.swingdsl.layout.CellBuilder
import com.github.weisj.swingdsl.layout.Row
import com.github.weisj.swingdsl.renderer.SimpleListCellRenderer
import com.github.weisj.swingdsl.text.Text

class StringValue(element: ContainedElement<Group>, displayName: Text, value: MutableProperty<String>) :
    DefaultValue<String>(element, displayName, value) {
    override fun createValueUI(row: Row): CellBuilder<*> {
        return row.textField(preview)() { commitImmediately() }
    }
}

class BoolValue(element: ContainedElement<Group>, displayName: Text, value: MutableProperty<Boolean>) :
    DefaultValue<Boolean>(element, displayName, value) {
    override val showTitle: Boolean = false
    override val showDescription: Boolean = false
    override fun createValueUI(row: Row): CellBuilder<*> {
        return row.checkBox(displayName, preview, description)() { commitImmediately() }
    }
}

class IntValue(
    element: ContainedElement<Group>,
    displayName: Text,
    value: MutableProperty<Int>,
    private val low: Int,
    private val high: Int,
    private val step: Int
) :
    DefaultValue<Int>(element, displayName, value) {
    override fun createValueUI(row: Row): CellBuilder<*> {
        return row.spinner(preview, preview.get(), low, high, step)() { commitImmediately() }
    }
}

class ChoiceValue<T : Any>(
    element: ContainedElement<Group>,
    displayName: Text,
    value: MutableProperty<T>,
    private val choices: ObservableList<T>,
    private val renderer: (T) -> String = { it.toString() },
) :
    DefaultValue<T>(element, displayName, value) {
    override fun createValueUI(row: Row): CellBuilder<*> {
        return row.comboBox(comboBoxModelOf(choices), preview, SimpleListCellRenderer.create(renderer))() {
            commitImmediately()
        }
    }
}

class StaticChoiceValue<T : Any>(
    element: ContainedElement<Group>,
    displayName: Text,
    value: MutableProperty<T>,
    private val choices: List<T>,
    private val renderer: (T) -> String = { it.toString() },
    private val unwrapLimit: Int = 2
) :
    DefaultValue<T>(element, displayName, value) {
    override fun createValueUI(row: Row): CellBuilder<*> {
        return if (choices.size <= unwrapLimit) {
            lateinit var cellBuilder: CellBuilder<*>
            row.apply {
                buttonGroup {
                    choices.forEach { item ->
                        row {
                            cellBuilder = radioButton(PseudoObservableProperty { renderer(item) }).applyToComponent {
                                isSelected = value.get() == item
                                addActionListener { if (isSelected) preview.set(item) }
                            }
                        }
                    }
                }
            }
            cellBuilder
        } else {
            row.comboBox(comboBoxModelOf(choices), preview, SimpleListCellRenderer.create(renderer))() {
                commitImmediately()
            }
        }
    }
}
