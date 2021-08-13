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

import com.github.weisj.swingdsl.core.binding.ObservableMutableProperty
import com.github.weisj.swingdsl.core.binding.ObservableProperty
import com.github.weisj.swingdsl.core.condition.ObservableCondition
import java.awt.Component
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.lang.UnsupportedOperationException
import javax.swing.AbstractButton
import javax.swing.JList
import javax.swing.JSpinner
import javax.swing.ListSelectionModel
import javax.swing.text.JTextComponent

fun Component.visibleBinding(): ObservableCondition = object : ObservableCondition {
    override fun get(): Boolean = isVisible

    override fun onChange(observeKey: Any?, callback: (Boolean) -> Unit) {
        addComponentListener(object : ComponentAdapter() {
            override fun componentShown(e: ComponentEvent?) = callback(get())
            override fun componentHidden(e: ComponentEvent?) = callback(get())
        })
    }

    override fun removeCallback(observeKey: Any?) {
        throw UnsupportedOperationException()
    }
}

fun ListSelectionModel.selectionBinding(): ObservableProperty<IntArray> = object : ObservableProperty<IntArray> {
    override fun get(): IntArray = selectedIndicesArray

    override fun onChange(observeKey: Any?, callback: (IntArray) -> Unit) {
        addListSelectionListener {
            if (!it.valueIsAdjusting) {
                callback(get())
            }
        }
    }

    override fun removeCallback(observeKey: Any?) {
        throw UnsupportedOperationException()
    }
}

fun <T> JList<T>.observableSelection(): ObservableProperty<IntArray> = selectionModel.selectionBinding()
fun JTextComponent.observableText(): ObservableMutableProperty<String> =
    object : ObservableMutableProperty<String> {
        override fun get(): String = text

        override fun set(value: String) {
            text = value
        }

        override fun onChange(observeKey: Any?, callback: (String) -> Unit) {
            addDocumentChangeListener { callback(get()) }
        }

        override fun removeCallback(observeKey: Any?) {
            throw UnsupportedOperationException()
        }
    }

fun AbstractButton.observableSelected(): ObservableMutableProperty<Boolean> =
    object : ObservableMutableProperty<Boolean> {
        override fun get(): Boolean = isSelected

        override fun set(value: Boolean) {
            isSelected = value
        }

        override fun onChange(observeKey: Any?, callback: (Boolean) -> Unit) {
            addChangeListener { callback(get()) }
        }

        override fun removeCallback(observeKey: Any?) {
            throw UnsupportedOperationException()
        }
    }

fun <V> JSpinner.observableValue(): ObservableMutableProperty<V> =
    object : ObservableMutableProperty<V> {
        @Suppress("UNCHECKED_CAST")
        override fun get(): V = value as V

        override fun set(value: V) {
            this@observableValue.value = value
        }

        override fun onChange(observeKey: Any?, callback: (V) -> Unit) {
            addChangeListener { callback(get()) }
        }

        override fun removeCallback(observeKey: Any?) {
            throw UnsupportedOperationException()
        }
    }
