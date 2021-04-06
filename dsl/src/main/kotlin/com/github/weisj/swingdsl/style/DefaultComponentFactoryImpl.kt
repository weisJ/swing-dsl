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
package com.github.weisj.swingdsl.style

import com.github.weisj.swingdsl.laf.CollapsibleComponent
import com.github.weisj.swingdsl.laf.ComponentFactory
import com.github.weisj.swingdsl.laf.SelfWrappedComponent
import com.github.weisj.swingdsl.laf.SeparatorSpec
import com.github.weisj.swingdsl.laf.SeparatorSpec.DefaultCollapsible
import com.github.weisj.swingdsl.laf.TextProperty
import com.github.weisj.swingdsl.laf.WrappedComponent
import com.github.weisj.swingdsl.text.Text
import com.github.weisj.swingdsl.text.TextButton
import com.github.weisj.swingdsl.text.TextCheckBox
import com.github.weisj.swingdsl.text.TextLabel
import com.github.weisj.swingdsl.text.TextRadioButton
import java.awt.Color
import java.util.function.Consumer
import javax.swing.Icon
import javax.swing.JButton
import javax.swing.JCheckBox
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JList
import javax.swing.JRadioButton
import javax.swing.JScrollPane
import javax.swing.JSplitPane
import javax.swing.ListModel
import javax.swing.UIManager

internal class TextPropertyWrapper(internal val text: Text) : TextProperty {
    override fun get(): String = text.get()

    override fun onChange(callback: Consumer<String>) {
        text.onChange { callback.accept(it) }
    }
}

internal class TextWrapper(internal val textProp: TextProperty) : Text {
    override fun get(): String = textProp.get()

    override fun onChange(callback: (String) -> Unit) {
        textProp.onChange(callback)
    }
}

fun Text.asTextProperty(): TextProperty = if (this is TextWrapper) this.textProp else TextPropertyWrapper(this)
fun TextProperty.asText(): Text = if (this is TextPropertyWrapper) this.text else TextWrapper(this)

class DefaultComponentFactoryImpl : ComponentFactory {

    override fun createLabel(text: TextProperty, icon: Icon?): WrappedComponent<JLabel> {
        return SelfWrappedComponent(TextLabel(text.asText(), icon))
    }

    override fun createButton(text: TextProperty, icon: Icon?): WrappedComponent<JButton> {
        return SelfWrappedComponent(TextButton(text.asText(), icon))
    }

    override fun createCheckBox(text: TextProperty, icon: Icon?): WrappedComponent<JCheckBox> {
        return SelfWrappedComponent(TextCheckBox(text.asText(), icon))
    }

    override fun createRadioButton(text: TextProperty, icon: Icon?): WrappedComponent<JRadioButton> {
        return SelfWrappedComponent(TextRadioButton(text.asText(), icon))
    }

    override fun createScrollPane(content: JComponent): WrappedComponent<JScrollPane> {
        return SelfWrappedComponent(JScrollPane(content))
    }

    override fun <T> createList(content: ListModel<T>): WrappedComponent<JList<T>> {
        return SelfWrappedComponent(JList(content))
    }

    override fun createSplitPane(left: JComponent, right: JComponent): WrappedComponent<JSplitPane> {
        return SelfWrappedComponent(JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right))
    }

    override fun createSeparatorComponent(label: TextProperty?): SeparatorSpec<JComponent, SeparatorSpec.Default> {
        return SeparatorSpec(null, SeparatorSpec.Default(::getSeparatorColor))
    }

    override fun createCollapsibleSeparatorComponent(
        label: TextProperty?
    ): SeparatorSpec<CollapsibleComponent, DefaultCollapsible> {
        return SeparatorSpec(
            null, DefaultCollapsible(::getSeparatorColor, null, null)
        )
    }

    private fun getSeparatorColor(enabled: Boolean): Color {
        val c = UIManager.getColor(if (enabled) "Label.foreground" else "Label.disabledForeground")
        if (c != null) {
            return c
        }
        return if (enabled) Color.BLACK else Color.GRAY
    }
}