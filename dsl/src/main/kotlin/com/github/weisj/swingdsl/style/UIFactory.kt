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
import com.github.weisj.swingdsl.laf.ComponentFactoryDelegate
import com.github.weisj.swingdsl.laf.DefaultComponentFactory
import com.github.weisj.swingdsl.laf.WrappedComponent
import com.github.weisj.swingdsl.text.Text
import java.beans.PropertyChangeEvent
import javax.swing.JButton
import javax.swing.JCheckBox
import javax.swing.JRadioButton
import javax.swing.UIManager

internal object UIFactory : ComponentFactoryDelegate(DefaultComponentFactory()) {

    private val superDelegate
        get() = super.getDelegate()
    private var effectiveDelegate = superDelegate

    init {
        UIManager.addPropertyChangeListener { e: PropertyChangeEvent? ->
            e ?: return@addPropertyChangeListener
            val key: String = e.propertyName
            if ("lookAndFeel" == key) {
                effectiveDelegate = (UIManager.get(COMPONENT_FACTORY_KEY) as? ComponentFactory) ?: superDelegate
            }
        }
    }

    override fun getDelegate(): ComponentFactory {
        return effectiveDelegate
    }

    fun createButton(text: Text): WrappedComponent<JButton> = createButton(text, null)
    fun createCheckBox(text: Text): WrappedComponent<JCheckBox> = createCheckBox(text, null)
    fun createRadioButton(text: Text): WrappedComponent<JRadioButton> = createRadioButton(text, null)
}
