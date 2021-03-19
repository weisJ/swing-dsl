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

import javax.swing.Icon
import javax.swing.JButton
import javax.swing.JCheckBox
import javax.swing.JLabel
import javax.swing.JRadioButton

/**
 * Button which takes [Text] instead of a [String].
 */
class TextButton @JvmOverloads constructor(
    text: Text = emptyText(),
    icon: Icon? = null
) : JButton(text.text, icon) {
    var textValue: Text? = text

    override fun getText(): String {
        return textValue?.text.orEmpty()
    }
}

/**
 * Checkbox which takes [Text] instead of a [String].
 */
class TextCheckBox @JvmOverloads constructor(
    text: Text = emptyText(),
    icon: Icon? = null
) : JCheckBox(text.text, icon) {
    var textValue: Text? = text
        set(value) {
            field = value
            text = value?.text.orEmpty()
        }

    override fun getText(): String {
        return textValue?.text.orEmpty()
    }
}

/**
 * Radiobutton which takes [Text] instead of a [String].
 */
class TextRadioButton @JvmOverloads constructor(
    text: Text = emptyText(),
    icon: Icon? = null
) : JRadioButton(text.text, icon) {
    var textValue: Text? = text
        set(value) {
            field = value
            text = value?.text.orEmpty()
        }

    override fun getText(): String {
        return textValue?.text.orEmpty()
    }
}

/**
 * Label which takes [Text] instead of a [String].
 */
class TextLabel @JvmOverloads constructor(text: Text, icon: Icon? = null) : JLabel(text.text, icon, LEFT) {
    var textValue: Text? = text
        set(value) {
            field = value
            text = value?.text.orEmpty()
        }

    override fun getText(): String {
        return textValue?.text.orEmpty()
    }
}
