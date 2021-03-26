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
import com.github.weisj.swingdsl.binding.MutableProperty
import com.github.weisj.swingdsl.binding.Observable
import com.github.weisj.swingdsl.binding.ObservableMutableProperty
import com.github.weisj.swingdsl.on
import com.github.weisj.swingdsl.onSwingThread
import com.github.weisj.swingdsl.text.Text
import com.github.weisj.swingdsl.text.textOf
import java.awt.event.ActionEvent
import javax.swing.AbstractButton
import javax.swing.JComponent
import javax.swing.JSpinner
import javax.swing.KeyStroke
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
        modelBinding: MutableProperty<V>,
        immediateModeUpdater: (() -> Unit)? = null
    ): CellBuilder<T> {
        if (modelBinding is Observable<*>) {
            modelBinding.onPropertyChange {
                onSwingThread {
                    componentSet(component, modelBinding.get())
                }
            }
        }
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

fun <T : JSpinner> CellBuilder<T>.withIntBinding(modelBinding: MutableProperty<Int>): CellBuilder<T> {
    return withBinding({ it.value as Int }, JSpinner::setValue, modelBinding) {
        component.addChangeListener {
            modelBinding.set(component.value as Int)
        }
    }
}

fun <T : JTextComponent> CellBuilder<T>.withTextBinding(modelBinding: MutableProperty<String>): CellBuilder<T> {
    return withBinding(JTextComponent::getText, JTextComponent::setText, modelBinding) {
        component.addDocumentChangeListener {
            modelBinding.set(component.text)
        }
    }
}

fun <T : AbstractButton> CellBuilder<T>.withSelectedBinding(modelBinding: MutableProperty<Boolean>): CellBuilder<T> {
    return withBinding(AbstractButton::isSelected, AbstractButton::setSelected, modelBinding) {
        component.addChangeListener {
            modelBinding.set((it.source as AbstractButton).model.isSelected)
        }
    }
}

fun <T : JSpinner> CellBuilder<T>.intValue(): ObservableMutableProperty<Int> = object : ObservableMutableProperty<Int> {
    override fun get(): Int = component.value as Int

    override fun set(value: Int) {
        component.value = value
    }

    override fun onPropertyChange(callback: (Int) -> Unit) {
        component.addChangeListener { callback(get()) }
    }
}

fun <T : JTextComponent> CellBuilder<T>.textValue(): ObservableMutableProperty<String> =
    object : ObservableMutableProperty<String> {
        override fun get(): String = component.text

        override fun set(value: String) {
            component.text = value
        }

        override fun onPropertyChange(callback: (String) -> Unit) {
            component.addDocumentChangeListener { callback(get()) }
        }
    }

fun <T : AbstractButton> CellBuilder<T>.selectionStatus(): ObservableMutableProperty<Boolean> =
    object : ObservableMutableProperty<Boolean> {
        override fun get(): Boolean = component.isSelected

        override fun set(value: Boolean) {
            component.isSelected = value
        }

        override fun onPropertyChange(callback: (Boolean) -> Unit) {
            component.addChangeListener { callback(get()) }
        }
    }
