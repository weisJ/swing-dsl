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
package com.github.weisj.swingdsl.core.text

import com.github.weisj.swingdsl.core.binding.Property
import com.github.weisj.swingdsl.core.binding.PseudoObservableProperty
import com.github.weisj.swingdsl.core.binding.SimpleProperty
import com.github.weisj.swingdsl.core.binding.bindToComponentWhileVisible
import javax.swing.AbstractButton
import javax.swing.Icon
import javax.swing.JButton
import javax.swing.JCheckBox
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JRadioButton
import javax.swing.text.JTextComponent

interface HasTextProperty {
    val textProp: Property<String>
}

fun JLabel.textProperty(): Property<String> = when (this) {
    is HasTextProperty -> textProp
    else -> SimpleProperty(this::getText)
}

fun AbstractButton.textProperty(): Property<String> = when (this) {
    is HasTextProperty -> textProp
    else -> SimpleProperty(this::getText)
}

fun JTextComponent.textProperty(): Property<String> = when (this) {
    is HasTextProperty -> textProp
    else -> SimpleProperty(this::getText)
}

fun JComponent.textProperty(): Property<String>? = when (this) {
    is JTextComponent -> textProperty()
    is AbstractButton -> textProperty()
    is JLabel -> textProperty()
    else -> null
}

private fun JComponent.invalidateIfNecessary(text: String, current: String): String {
    if (text != current) {
        invalidate()
    }
    return text
}

/**
 * Button which takes [Text] instead of a [String].
 */
open class TextButton @JvmOverloads constructor(
    final override val textProp: Text = emptyText(),
    icon: Icon? = null
) : JButton(textProp.get(), icon), HasTextProperty {
    init {
        @Suppress("LeakingThis")
        textProp.bindToComponentWhileVisible(this) {
            this.text = it
        }
    }

    override fun getText(): String {
        return if (textProp is PseudoObservableProperty<*>) invalidateIfNecessary(
            textProp.get(),
            super.getText()
        ) else super.getText()
    }
}

/**
 * Checkbox which takes [Text] instead of a [String].
 */
open class TextCheckBox @JvmOverloads constructor(
    final override val textProp: Text = emptyText(),
    icon: Icon? = null
) : JCheckBox(textProp.get(), icon), HasTextProperty {
    init {
        @Suppress("LeakingThis")
        textProp.bindToComponentWhileVisible(this) {
            this.text = it
        }
    }

    override fun getText(): String {
        return if (textProp is PseudoObservableProperty<*>) invalidateIfNecessary(
            textProp.get(),
            super.getText()
        ) else super.getText()
    }
}

/**
 * Radiobutton which takes [Text] instead of a [String].
 */
open class TextRadioButton @JvmOverloads constructor(
    final override val textProp: Text = emptyText(),
    icon: Icon? = null
) : JRadioButton(textProp.get(), icon), HasTextProperty {
    init {
        @Suppress("LeakingThis")
        textProp.bindToComponentWhileVisible(this) {
            this.text = it
        }
    }

    override fun getText(): String {
        return if (textProp is PseudoObservableProperty<*>) invalidateIfNecessary(
            textProp.get(),
            super.getText()
        ) else super.getText()
    }
}

/**
 * Label which takes [Text] instead of a [String].
 */
open class TextLabel @JvmOverloads constructor(
    final override val textProp: Text,
    icon: Icon? = null
) : JLabel(textProp.get(), icon, LEFT), HasTextProperty {
    init {
        @Suppress("LeakingThis")
        textProp.bindToComponentWhileVisible(this) {
            this.text = it
        }
    }

    override fun getText(): String {
        return if (textProp is PseudoObservableProperty<*>) invalidateIfNecessary(
            textProp.get(),
            super.getText()
        ) else super.getText()
    }
}
