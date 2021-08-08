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
package com.github.weisj.swingdsl.dsl.layout

import com.github.weisj.swingdsl.core.binding.MutableProperty
import com.github.weisj.swingdsl.core.binding.PropertyBinding
import com.github.weisj.swingdsl.core.text.Text
import com.github.weisj.swingdsl.laf.WrappedComponent
import javax.swing.ButtonGroup
import javax.swing.JComponent
import javax.swing.JLabel

class InnerCell(val cell: Cell) : Cell() {

    override fun commentNoWrap(text: Text): CellBuilder<JLabel> {
        return cell.commentNoWrap(text)
    }

    override fun <T : JComponent> component(component: T): CellBuilder<T> {
        return cell.component(component)
    }

    override fun <T : JComponent> component(wrappedComponent: WrappedComponent<T>): CellBuilder<T> {
        return cell.component(wrappedComponent)
    }

    override fun withButtonGroup(title: Text?, buttonGroup: ButtonGroup, body: () -> Unit) {
        cell.withButtonGroup(title, buttonGroup, body)
    }

    override fun withButtonGroup(title: String?, buttonGroup: ButtonGroup, body: () -> Unit) {
        cell.withButtonGroup(title, buttonGroup, body)
    }

    inline fun <reified T> buttonGroup(
        binding: MutableProperty<T>,
        crossinline init: CellBuilderWithButtonGroupProperty<T>.() -> Unit
    ) {
        withButtonGroup(ButtonGroup()) {
            CellBuilderWithButtonGroupProperty(binding).init()
        }
    }

    inline fun <reified T> buttonGroup(
        noinline getter: () -> T,
        noinline setter: (T) -> Unit,
        crossinline init: CellBuilderWithButtonGroupProperty<T>.() -> Unit
    ) {
        buttonGroup(PropertyBinding(getter, setter), init)
    }
}
