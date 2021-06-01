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
package com.github.weisj.swingdsl.util

import com.github.weisj.swingdsl.core.binding.Property
import com.github.weisj.swingdsl.core.binding.SimpleProperty
import com.github.weisj.swingdsl.core.text.HasTextProperty
import com.github.weisj.swingdsl.core.text.textProperty
import com.github.weisj.swingdsl.getProperty
import com.github.weisj.swingdsl.renderer.HasStringMapper
import com.github.weisj.swingdsl.renderer.StringMapper
import com.github.weisj.swingdsl.visualpadding.VisualPaddingProvider
import java.awt.Component
import java.awt.Insets
import javax.swing.AbstractButton
import javax.swing.JComboBox
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JSpinner
import javax.swing.border.Border
import javax.swing.border.CompoundBorder
import javax.swing.text.JTextComponent

@Suppress("UNCHECKED_CAST")
fun getTextForComponent(component: Component): String {
    return when (component) {
        is HasTextProperty -> component.textProp.get()
        is AbstractButton -> component.text
        is JTextComponent -> component.text
        is JLabel -> component.text
        is JSpinner.DefaultEditor -> getTextForComponent(component.textField)
        is JComboBox<*> -> getDisplayTextOfComboBox(component as JComboBox<Any?>)
        else -> ""
    }
}

@Suppress("UNCHECKED_CAST")
private fun getDisplayTextOfComboBox(comboBox: JComboBox<Any?>): String {
    val selected = comboBox.selectedItem ?: return ""
    if (comboBox.isEditable) {
        val editor = comboBox.editor?.editorComponent
        if (editor != null) return getTextForComponent(editor)
    }
    return when (val renderer = comboBox.renderer) {
        is StringMapper<*> -> (renderer as StringMapper<Any?>).stringValueOf(selected)
        is HasStringMapper<*> -> (renderer as HasStringMapper<Any?>).mapper.stringValueOf(selected)
        else -> {
            val rendererComponent =
                renderer.getListCellRendererComponent(SharedLazyComponents.list, selected, -1, false, false)
            getTextForComponent(rendererComponent)
        }
    }
}

fun getTextPropertyForComponent(component: JComponent): Property<String>? {
    return when (component) {
        is HasTextProperty -> component.textProp
        is AbstractButton, is JTextComponent, is JLabel -> component.textProperty()
        is JComboBox<*> -> SimpleProperty { getTextForComponent(component) }
        is JSpinner -> SimpleProperty { getTextForComponent(component.editor) }
        else -> null
    }
}

fun getVisualPaddingsForComponent(component: JComponent): Insets? {
    return when (val unwrapped = unwrapBorder(component.border)) {
        is VisualPaddingProvider -> unwrapped.getVisualPaddings(component)
        else -> component.getProperty<Insets>(VisualPaddingProvider.VISUAL_PADDING_PROP)
    }
}

private fun unwrapBorder(border: Border?): Border? {
    var b = border
    while (b is CompoundBorder) {
        b = b.outsideBorder
    }
    return b
}
