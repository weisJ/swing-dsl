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
package com.github.weisj.swingdsl.text

import com.github.weisj.swingdsl.binding.PseudoObservableProperty
import javax.swing.Icon
import javax.swing.JButton
import javax.swing.JCheckBox
import javax.swing.JLabel
import javax.swing.JRadioButton

/**
 * Button which takes [Text] instead of a [String].
 */
open class TextButton @JvmOverloads constructor(
    private val textProp: Text = emptyText(),
    icon: Icon? = null
) : JButton(textProp.get(), icon) {
    init {
        textProp.onChange {
            this.text = it
        }
    }

    private fun String.invalidateIfNecessary(): String {
        if (this != super.getText()) {
            invalidate()
        }
        return this
    }

    override fun getText(): String {
        return if (textProp is PseudoObservableProperty<*>) textProp.get().invalidateIfNecessary() else super.getText()
    }
}

/**
 * Checkbox which takes [Text] instead of a [String].
 */
open class TextCheckBox @JvmOverloads constructor(
    private val textProp: Text = emptyText(),
    icon: Icon? = null
) : JCheckBox(textProp.get(), icon) {
    init {
        textProp.onChange {
            this.text = it
        }
    }

    private fun String.invalidateIfNecessary(): String {
        if (this != super.getText()) {
            invalidate()
        }
        return this
    }

    override fun getText(): String {
        return if (textProp is PseudoObservableProperty<*>) textProp.get().invalidateIfNecessary() else super.getText()
    }
}

/**
 * Radiobutton which takes [Text] instead of a [String].
 */
open class TextRadioButton @JvmOverloads constructor(
    private val textProp: Text = emptyText(),
    icon: Icon? = null
) : JRadioButton(textProp.get(), icon) {
    init {
        textProp.onChange {
            this.text = it
        }
    }

    private fun String.invalidateIfNecessary(): String {
        if (this != super.getText()) {
            invalidate()
        }
        return this
    }

    override fun getText(): String {
        return if (textProp is PseudoObservableProperty<*>) textProp.get().invalidateIfNecessary() else super.getText()
    }
}

/**
 * Label which takes [Text] instead of a [String].
 */
open class TextLabel @JvmOverloads constructor(
    private val textProp: Text,
    icon: Icon? = null
) : JLabel(textProp.get(), icon, LEFT) {
    init {
        textProp.onChange {
            this.text = it
        }
    }

    private fun String.invalidateIfNecessary(): String {
        if (this != super.getText()) {
            invalidate()
        }
        return this
    }

    override fun getText(): String {
        return if (textProp is PseudoObservableProperty<*>) textProp.get().invalidateIfNecessary() else super.getText()
    }
}
