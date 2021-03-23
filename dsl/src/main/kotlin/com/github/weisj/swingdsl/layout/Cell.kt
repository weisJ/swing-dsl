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

import com.github.weisj.swingdsl.Modifiable
import com.github.weisj.swingdsl.ModifiableComponent
import com.github.weisj.swingdsl.binding.PropertyBinding
import com.github.weisj.swingdsl.binding.toBinding
import com.github.weisj.swingdsl.binding.toNullable
import com.github.weisj.swingdsl.laf.WrappedComponent
import com.github.weisj.swingdsl.renderer.SimpleListCellRenderer
import com.github.weisj.swingdsl.style.DynamicUI
import com.github.weisj.swingdsl.style.UIFactory
import com.github.weisj.swingdsl.text.Text
import com.github.weisj.swingdsl.text.TextLabel
import com.github.weisj.swingdsl.text.textOf
import com.github.weisj.swingdsl.text.textOfNullable
import java.awt.Dimension
import java.awt.event.ActionEvent
import javax.swing.*
import kotlin.reflect.KMutableProperty0

@CellMarker
abstract class Cell : ButtonGroupBuilder {

    internal companion object {
        const val UNBOUND_RADIO_BUTTON = "unbound.radio.button"
    }

    /**
     * Sets how keen the component should be to grow in relation to other component **in the same cell**. Use `push` in addition if need.
     * If this constraint is not set the grow weight is set to 0 and the component will not grow (unless some automatic rule is not applied.
     * Grow weight will only be compared against the weights for the same cell.
     */
    val growX = CCFlags.growX

    @Suppress("unused")
    val growY = CCFlags.growY
    val grow = CCFlags.grow

    /**
     * Makes the column that the component is residing in grow with `weight`.
     */
    val pushX = CCFlags.pushX

    /**
     * Makes the row that the component is residing in grow with `weight`.
     */
    @Suppress("unused")
    val pushY = CCFlags.pushY
    val push = CCFlags.push

    fun label(text: String, bold: Boolean = false): CellBuilder<JLabel> = label(textOf(text), bold)

    @JvmOverloads
    fun label(text: Text, bold: Boolean = false): CellBuilder<JLabel> {
        val label = TextLabel(text)
        if (bold) DynamicUI.withBoldFont(label)
        return component(label)
    }

    fun button(text: Text, actionListener: (event: ActionEvent) -> Unit): CellBuilder<JButton> {
        val button = UIFactory.createButton(text)
        button.component.addActionListener(actionListener)
        return component(button)
    }

    @JvmOverloads
    fun checkBox(
        text: Text,
        isSelected: Boolean = false,
        comment: Text? = null,
        actionListener: (event: ActionEvent, component: JCheckBox) -> Unit
    ): CellBuilder<JCheckBox> {
        return checkBox(text, isSelected, comment)
            .applyToComponent {
                addActionListener { actionListener(it, this) }
            }
    }

    fun checkBox(
        text: Text,
        isSelected: Boolean = false,
        comment: Text? = null
    ): CellBuilder<JCheckBox> {
        val result = UIFactory.createCheckBox(text)
        result.component.isSelected = isSelected
        return result(comment = comment)
    }

    fun checkBox(
        text: Text,
        prop: KMutableProperty0<Boolean>,
        comment: Text? = null
    ): CellBuilder<JCheckBox> {
        return checkBox(text, prop.toBinding(), comment)
    }

    @JvmOverloads
    fun checkBox(
        text: Text,
        getter: () -> Boolean,
        setter: (Boolean) -> Unit,
        comment: Text? = null
    ): CellBuilder<JCheckBox> {
        return checkBox(text, PropertyBinding(getter, setter), comment)
    }

    private fun checkBox(
        text: Text,
        modelBinding: PropertyBinding<Boolean>,
        comment: Text?
    ): CellBuilder<JCheckBox> {
        val component = UIFactory.createCheckBox(text)
        component.component.isSelected = modelBinding.get()
        return component(comment = comment).withSelectedBinding(modelBinding)
    }

    open fun radioButton(text: Text, comment: Text? = null): CellBuilder<JRadioButton> {
        val component = UIFactory.createRadioButton(text)
        component.component.putClientProperty(UNBOUND_RADIO_BUTTON, true)
        return component(comment = comment)
    }

    open fun radioButton(
        text: Text,
        getter: () -> Boolean,
        setter: (Boolean) -> Unit,
        comment: Text? = null
    ): CellBuilder<JRadioButton> {
        val component = UIFactory.createRadioButton(text)
        component.component.isSelected = getter()
        return component(comment = comment)
            .withSelectedBinding(PropertyBinding(getter, setter))
    }

    open fun radioButton(
        text: Text,
        prop: KMutableProperty0<Boolean>,
        comment: Text? = null
    ): CellBuilder<JRadioButton> {
        val component = UIFactory.createRadioButton(text)
        component.component.isSelected = prop.get()
        return component(comment = comment).withSelectedBinding(prop.toBinding())
    }

    fun <T> comboBox(
        model: ComboBoxModel<T>,
        getter: () -> T?,
        setter: (T?) -> Unit,
        renderer: ListCellRenderer<T?>? = null
    ): CellBuilder<JComboBox<T>> {
        return comboBox(model, PropertyBinding(getter, setter), renderer)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> comboBox(
        model: ComboBoxModel<T>,
        modelBinding: PropertyBinding<T?>,
        renderer: ListCellRenderer<T?>? = null
    ): CellBuilder<JComboBox<T>> {
        return component(JComboBox(model))
            .applyToComponent {
                this.renderer = renderer ?: SimpleListCellRenderer.create { it -> it.toString() }
                selectedItem = modelBinding.get()
            }
            .withBinding(
                { component -> component.selectedItem as T? },
                { component, value -> component.setSelectedItem(value) },
                modelBinding
            )
    }

    inline fun <reified T : Any> comboBox(
        model: ComboBoxModel<T>,
        prop: KMutableProperty0<T>,
        renderer: ListCellRenderer<T?>? = null
    ): CellBuilder<JComboBox<T>> {
        return comboBox(model, prop.toBinding().toNullable(), renderer)
    }

    fun textField(prop: KMutableProperty0<String>, columns: Int? = null): CellBuilder<JTextField> =
        textField(prop.toBinding(), columns)

    @JvmOverloads
    fun textField(getter: () -> String, setter: (String) -> Unit, columns: Int? = null) =
        textField(PropertyBinding(getter, setter), columns)

    @JvmOverloads
    fun textField(binding: PropertyBinding<String>, columns: Int? = null): CellBuilder<JTextField> {
        return component(JTextField(binding.get(), columns ?: 0))
            .withTextBinding(binding)
    }

    fun spinner(prop: KMutableProperty0<Int>, minValue: Int, maxValue: Int, step: Int = 1): CellBuilder<JSpinner> {
        val spinnerModel = SpinnerNumberModel(prop.get(), minValue, maxValue, step)
        val spinner = JSpinner(spinnerModel)
        return component(spinner).withIntBinding(prop.toBinding())
    }

    @JvmOverloads
    fun spinner(
        getter: () -> Int,
        setter: (Int) -> Unit,
        minValue: Int,
        maxValue: Int,
        step: Int = 1
    ): CellBuilder<JSpinner> {
        val spinnerModel = SpinnerNumberModel(getter(), minValue, maxValue, step)
        val spinner = JSpinner(spinnerModel)
        return component(spinner).withBinding(
            { it.value as Int },
            JSpinner::setValue,
            PropertyBinding(getter, setter)
        )
    }

    fun <T : JComponent> scrollPane(component: ModifiableComponent<T>): CellBuilder<JScrollPane> {
        return scrollPane(component.container).bindModifiable(component)
    }

    fun scrollPane(component: JComponent): CellBuilder<JScrollPane> {
        return component(UIFactory.createScrollPane(component))
    }

    fun placeholder(): CellBuilder<JComponent> {
        return component(
            JPanel().apply {
                minimumSize = Dimension(0, 0)
                preferredSize = Dimension(0, 0)
                maximumSize = Dimension(0, 0)
            }
        )
    }

    fun <T : JComponent> component(component: ModifiableComponent<T>): CellBuilder<T> =
        component(component as WrappedComponent<T>).bindModifiable(component)

    private fun <T : JComponent> CellBuilder<T>.bindModifiable(modifiable: Modifiable): CellBuilder<T> {
        onApply { modifiable.apply() }
        onReset { modifiable.reset() }
        onIsModified { modifiable.isModified() }
        return this
    }

    abstract fun <T : JComponent> component(component: T): CellBuilder<T>
    abstract fun <T : JComponent> component(wrappedComponent: WrappedComponent<T>): CellBuilder<T>

    internal operator fun <T : JComponent> T.invoke(
        growPolicy: GrowPolicy? = null,
        comment: Text? = null,
        vararg constraints: CCFlags
    ): CellBuilder<T> = component(this).apply {
        init(growPolicy, comment, *constraints)
    }

    internal operator fun <T : JComponent> WrappedComponent<T>.invoke(
        growPolicy: GrowPolicy? = null,
        comment: Text? = null,
        vararg constraints: CCFlags
    ): CellBuilder<T> = component(this).apply {
        init(growPolicy, comment, *constraints)
    }

    private fun CellBuilder<*>.init(
        growPolicy: GrowPolicy? = null,
        comment: Text? = null,
        vararg constraints: CCFlags
    ) {
        constraints(*constraints)
        if (comment != null) comment(comment)
        if (growPolicy != null) growPolicy(growPolicy)
    }

    // String overloads
    fun button(text: String, actionListener: (event: ActionEvent) -> Unit): CellBuilder<JButton> =
        button(textOf(text), actionListener)

    @JvmOverloads
    fun checkBox(
        text: String,
        isSelected: Boolean = false,
        comment: String? = null,
        actionListener: (event: ActionEvent, component: JCheckBox) -> Unit
    ): CellBuilder<JCheckBox> = checkBox(textOf(text), isSelected, textOfNullable(comment), actionListener)

    @JvmOverloads
    fun checkBox(
        text: String,
        isSelected: Boolean = false,
        comment: String? = null
    ): CellBuilder<JCheckBox> = checkBox(textOf(text), isSelected, textOfNullable(comment))

    fun checkBox(
        text: String,
        prop: KMutableProperty0<Boolean>,
        comment: String? = null
    ): CellBuilder<JCheckBox> {
        return checkBox(textOf(text), prop, textOfNullable(comment))
    }

    @JvmOverloads
    fun checkBox(
        text: String,
        getter: () -> Boolean,
        setter: (Boolean) -> Unit,
        comment: String? = null
    ): CellBuilder<JCheckBox> {
        return checkBox(textOf(text), getter, setter, textOfNullable(comment))
    }

    fun radioButton(text: String, comment: String? = null): CellBuilder<JRadioButton> =
        radioButton(textOf(text), textOfNullable(comment))

    fun radioButton(
        text: String,
        getter: () -> Boolean,
        setter: (Boolean) -> Unit,
        comment: String? = null
    ): CellBuilder<JRadioButton> = radioButton(textOf(text), getter, setter, textOfNullable(comment))

    fun radioButton(
        text: String,
        prop: KMutableProperty0<Boolean>,
        comment: String? = null
    ): CellBuilder<JRadioButton> = radioButton(textOf(text), prop, textOfNullable(comment))
}