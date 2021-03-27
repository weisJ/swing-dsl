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

import com.github.weisj.swingdsl.binding.ObservableProperty
import com.github.weisj.swingdsl.binding.derive

/**
 * A portion of text.
 */
typealias Text = ObservableProperty<String>

/**
 * Create a new [ConstantText] with the given text value. [.getText] will always
 * return this exact value.
 *
 * @param text the text value.
 */
data class ConstantText(private val text: String) : Text {
    override fun toString(): String {
        return text
    }

    override fun get(): String = text

    override fun onChange(callback: (String) -> Unit) {
        /* Do nothing. Constant values never change */
    }
}

fun textOf(str: String = ""): Text = ConstantText(str)
fun <T> textOf(prop: ObservableProperty<T>): Text = prop.derive { it.toString() }

@JvmName("textOfText")
fun textOf(prop: Text): Text = prop
fun textOfNullable(str: String? = null): Text? = str?.let { textOf(str) }

fun emptyText() = textOf()
