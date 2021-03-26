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

// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.github.weisj.swingdsl.layout

import com.github.weisj.swingdsl.BuilderWithEnabledProperty
import com.github.weisj.swingdsl.addDocumentChangeListener
import com.github.weisj.swingdsl.binding.MutableBoundProperty
import com.github.weisj.swingdsl.binding.PropertyBinding
import com.github.weisj.swingdsl.on
import com.github.weisj.swingdsl.text.Text
import com.github.weisj.swingdsl.text.textOf
import java.awt.event.ActionEvent
import java.util.concurrent.atomic.AtomicBoolean
import javax.swing.AbstractButton
import javax.swing.JComponent
import javax.swing.JSpinner
import javax.swing.KeyStroke
import javax.swing.event.DocumentEvent
import javax.swing.text.JTextComponent

@DslMarker
annotation class CellMarker

interface CellBuilder<out T : JComponent> : BuilderWithEnabledProperty<CellBuilder<T>> {
    val component: T

    fun onApply(callback: () -> Unit): CellBuilder<T>
    fun onReset(callback: () -> Unit): CellBuilder<T>
    fun onIsModified(callback: () -> Boolean): CellBuilder<T>

    fun commitImmediately()
    fun shouldSaveOnApply(): Boolean

    fun <V> withBinding(
        componentGet: (T) -> V,
        componentSet: (T, V) -> Unit,
        modelBinding: PropertyBinding<V>,
        immediateModeUpdater: (() -> Unit)? = null
    ): CellBuilder<T> {
        onApply { if (shouldSaveOnApply()) modelBinding.set(componentGet(component)) }
        onReset { componentSet(component, modelBinding.get()) }
        onIsModified { shouldSaveOnApply() && componentGet(component) != modelBinding.get() }
        return this
    }

    fun comment(
        text: Text,
        maxLineLength: Int = 70,
        forComponent: Boolean = false
    ): CellBuilder<T>

    fun comment(
        text: String,
        maxLineLength: Int = 70,
        forComponent: Boolean = false
    ): CellBuilder<T> = comment(textOf(text), maxLineLength, forComponent)

    fun commentComponent(component: JComponent, forComponent: Boolean = false): CellBuilder<T>

    fun withLargeLeftGap(): CellBuilder<T>
    fun withLeftGap(): CellBuilder<T>

    /**
     * All components of the same group share will get the same BoundSize (min/preferred/max),
     * which is that of the biggest component in the group
     */
    fun sizeGroup(name: String): CellBuilder<T>
    fun growPolicy(growPolicy: GrowPolicy): CellBuilder<T>
    fun constraints(vararg constraints: CCFlags): CellBuilder<T>

    fun applyToComponent(task: T.() -> Unit): CellBuilder<T> {
        return also { task(component) }
    }

    fun on(vararg keyCode: KeyStroke, action: T.(ActionEvent?) -> Unit) {
        component.on(*keyCode, action = action)
    }

    operator fun invoke(action: CellBuilder<T>.() -> Unit): CellBuilder<T> = apply(action)
}

internal interface ScrollPaneCellBuilder {
    fun noGrowY()
}

internal interface CheckboxCellBuilder {
    fun actsAsLabel()
}

fun <T : JSpinner> CellBuilder<T>.withIntBinding(modelBinding: PropertyBinding<Int>): CellBuilder<T> {
    return withBinding({ it.value as Int }, JSpinner::setValue, modelBinding) {
        component.addChangeListener {
            modelBinding.set(component.value as Int)
        }
    }
}

fun <T : JTextComponent> CellBuilder<T>.withTextBinding(modelBinding: PropertyBinding<String>): CellBuilder<T> {
    return withTextBinding(modelBinding)
}

internal fun <T : JTextComponent> CellBuilder<T>.withTextBinding(
    modelBinding: PropertyBinding<String>,
    lock: AtomicBoolean
): CellBuilder<T> {
    return withBinding(JTextComponent::getText, JTextComponent::setText, modelBinding) {
        component.addDocumentChangeListener {
            if (!lock.get()) {
                lock.set(true)
                modelBinding.set(component.text)
                lock.set(false)
            }
        }
    }
}

fun <T : AbstractButton> CellBuilder<T>.withSelectedBinding(modelBinding: PropertyBinding<Boolean>): CellBuilder<T> {
    return withBinding(AbstractButton::isSelected, AbstractButton::setSelected, modelBinding) {
        component.addChangeListener {
            modelBinding.set((it.source as AbstractButton).model.isSelected)
        }
    }
}

fun <T : JSpinner> CellBuilder<T>.intValue(): MutableBoundProperty<Int> = object : MutableBoundProperty<Int> {
    override fun get(): Int = component.value as Int

    override fun set(value: Int) {
        component.value = value
    }

    override fun onPropertyChange(callback: (Int) -> Unit) {
        component.addChangeListener { callback(get()) }
    }
}

fun <T : JTextComponent> CellBuilder<T>.textValue(): MutableBoundProperty<String> =
    object : MutableBoundProperty<String> {
        override fun get(): String = component.text

        override fun set(value: String) {
            component.text = value
        }

        override fun onPropertyChange(callback: (String) -> Unit) {
            component.addDocumentChangeListener { callback(get()) }
        }
    }

fun <T : AbstractButton> CellBuilder<T>.selectionStatus(): MutableBoundProperty<Boolean> =
    object : MutableBoundProperty<Boolean> {
        override fun get(): Boolean = component.isSelected

        override fun set(value: Boolean) {
            component.isSelected = value
        }

        override fun onPropertyChange(callback: (Boolean) -> Unit) {
            component.addChangeListener { callback(get()) }
        }
    }
