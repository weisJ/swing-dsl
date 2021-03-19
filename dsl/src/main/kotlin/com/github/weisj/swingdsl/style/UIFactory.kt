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

import com.github.weisj.swingdsl.laf.ComponentFactory
import com.github.weisj.swingdsl.laf.ComponentFactory.COMPONENT_FACTORY_KEY
import com.github.weisj.swingdsl.laf.SelfWrappedComponent
import com.github.weisj.swingdsl.laf.WrappedComponent
import com.github.weisj.swingdsl.text.Text
import com.github.weisj.swingdsl.text.TextButton
import com.github.weisj.swingdsl.text.TextCheckBox
import com.github.weisj.swingdsl.text.TextRadioButton
import java.beans.PropertyChangeEvent
import javax.swing.BorderFactory
import javax.swing.Icon
import javax.swing.JButton
import javax.swing.JCheckBox
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JRadioButton
import javax.swing.JScrollPane
import javax.swing.UIManager
import javax.swing.border.Border

internal object UIFactory : ComponentFactory {
    private var delegate: ComponentFactory = DefaultComponentFactory()

    init {
        UIManager.addPropertyChangeListener { e: PropertyChangeEvent? ->
            e ?: return@addPropertyChangeListener
            val key: String = e.propertyName
            if ("lookAndFeel" == key) {
                delegate = (UIManager.get(COMPONENT_FACTORY_KEY) as? ComponentFactory) ?: DefaultComponentFactory()
            }
        }
    }

    override fun createButton(text: Text, icon: Icon?): WrappedComponent<JButton> =
        delegate.createButton(text, icon)

    override fun createCheckBox(text: Text, icon: Icon?): WrappedComponent<JCheckBox> =
        delegate.createCheckBox(text, icon)

    override fun createRadioButton(text: Text, icon: Icon?): WrappedComponent<JRadioButton> =
        delegate.createRadioButton(text, icon)

    fun createButton(text: Text): WrappedComponent<JButton> = createButton(text, null)
    fun createCheckBox(text: Text): WrappedComponent<JCheckBox> = createCheckBox(text, null)
    fun createRadioButton(text: Text): WrappedComponent<JRadioButton> = createRadioButton(text, null)

    override fun createScrollPane(content: JComponent): WrappedComponent<JScrollPane> =
        delegate.createScrollPane(content)

    override fun createDividerBorder(title: String?): Border =
        delegate.createDividerBorder(title)
}

internal class DefaultComponentFactory : ComponentFactory {
    override fun createButton(text: Text, icon: Icon?): WrappedComponent<JButton> =
        SelfWrappedComponent(TextButton(text, icon))

    override fun createCheckBox(text: Text, icon: Icon?): WrappedComponent<JCheckBox> =
        SelfWrappedComponent(TextCheckBox(text, icon))

    override fun createRadioButton(text: Text, icon: Icon?): WrappedComponent<JRadioButton> =
        SelfWrappedComponent(TextRadioButton(text, icon))

    override fun createScrollPane(content: JComponent): WrappedComponent<JScrollPane> =
        SelfWrappedComponent(JScrollPane(content))

    override fun createDividerBorder(title: String?): Border {
        return BorderFactory.createTitledBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, JLabel().foreground),
            title
        )
    }
}
