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
package com.github.weisj.swingdsl.dsl

import com.github.weisj.swingdsl.core.binding.ObservableProperty
import com.github.weisj.swingdsl.dsl.style.DynamicUI
import java.awt.Component
import java.lang.UnsupportedOperationException
import java.util.*
import javax.swing.JComponent
import javax.swing.UIManager

inline fun <reified T> Component.getProperty(key: Any): T? {
    if (this !is JComponent) return null
    return getClientProperty(key) as? T
}

@PublishedApi
internal class UIManagerProperty<T>(private val key: String, private val type: Class<T>) : ObservableProperty<T> {
    override fun get(): T = type.cast(UIManager.get(key, Locale.getDefault()))!!

    override fun onChange(observeKey: Any?, callback: (T) -> Unit) {
        DynamicUI.registerUIListener { callback(get()) }
    }

    override fun removeCallback(observeKey: Any?) {
        throw UnsupportedOperationException()
    }
}

/**
 * Create new observable property, which takes it value from the UIManager.
 * The update cycle of the property follows the state of the current laf.
 * It can be manually updated using DynamicUI#updateAll.
 */
inline fun <reified T> observableUIManagerProperty(key: String): ObservableProperty<T> =
    UIManagerProperty(key, T::class.java)
