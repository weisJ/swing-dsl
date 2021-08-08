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
package com.github.weisj.swingdsl.dsl.layout

import com.github.weisj.swingdsl.core.binding.MutableProperty
import com.github.weisj.swingdsl.core.binding.Observable
import com.github.weisj.swingdsl.core.binding.ObservableMutableProperty
import com.github.weisj.swingdsl.core.binding.ObservableProperty
import com.github.weisj.swingdsl.core.text.Text
import com.github.weisj.swingdsl.core.text.textOf
import com.github.weisj.swingdsl.dsl.BuilderWithEnabledProperty
import com.github.weisj.swingdsl.dsl.addDocumentChangeListener
import com.github.weisj.swingdsl.dsl.makeDefaultButton
import com.github.weisj.swingdsl.dsl.observableSelected
import com.github.weisj.swingdsl.dsl.observableSelection
import com.github.weisj.swingdsl.dsl.observableText
import com.github.weisj.swingdsl.dsl.observableValue
import com.github.weisj.swingdsl.dsl.on
import com.github.weisj.swingdsl.dsl.onSwingThread
import com.github.weisj.swingdsl.laf.WrappedComponent
import java.awt.event.ActionEvent
import java.util.concurrent.atomic.AtomicBoolean
import javax.swing.AbstractButton
import javax.swing.JButton
import javax.swing.JCheckBox
import javax.swing.JComponent
import javax.swing.JList
import javax.swing.JSpinner
import javax.swing.KeyStroke
import javax.swing.event.ChangeEvent
import javax.swing.event.DocumentEvent
import javax.swing.text.JTextComponent

@DslMarker
annotation class CellMarker

interface CellBuilder<out T : JComponent> : BuilderWithEnabledProperty<CellBuilder<T>> {
    val wrappedComponent: WrappedComponent<out T>
    val component: T
        get() = wrappedComponent.component

    fun onApply(callback: () -> Unit): CellBuilder<T>
    fun onReset(callback: () -> Unit): CellBuilder<T>
    fun onIsModified(callback: () -> Boolean): CellBuilder<T>

    fun commitImmediately()
    fun shouldSaveOnApply(): Boolean

    fun <V> withBinding(
        componentGet: (T) -> V,
        componentSet: (T, V) -> Unit,
        modelBinding: MutableProperty<V>
    ): CellBuilder<T> = withBinding<V, Any>(componentGet, componentSet, modelBinding, null)

    fun <V, L> withBinding(
        componentGet: (T) -> V,
        componentSet: (T, V) -> Unit,
        modelBinding: MutableProperty<V>,
        componentListenerAdder: ((T, (L) -> Unit) -> Unit)? = null,
    ): CellBuilder<T> {
        var lock: AtomicBoolean? = null
        if (modelBinding is Observable<*>) {
            if (componentListenerAdder != null) lock = AtomicBoolean(false)
            modelBinding.onChange {
                onSwingThread { if (lock?.get() != true) componentSet(component, modelBinding.get()) }
            }
        }
        onImmedeateModeActivated {
            if (componentListenerAdder != null) {
                componentListenerAdder(component) {
                    lock?.set(true)
                    modelBinding.set(componentGet(component))
                    lock?.set(false)
                }
            }
        }
        onApply { if (shouldSaveOnApply()) modelBinding.set(componentGet(component)) }
        onReset { componentSet(component, modelBinding.get()) }
        onIsModified { shouldSaveOnApply() && componentGet(component) != modelBinding.get() }
        return this
    }

    // Do not call directly
    fun onImmedeateModeActivated(immediateModeUpdater: (() -> Unit)? = null)

    fun comment(
        text: Text,
        maxLineLength: Int = 70,
        forComponent: Boolean = false
    ): CellBuilder<T>

    fun comment(
        text: String,
        maxLineLength: Int = 70,
        forComponent: Boolean = false
    ): CellBuilder<T> =
        comment(textOf(text), maxLineLength, forComponent)

    fun commentComponent(component: JComponent, forComponent: Boolean = false): CellBuilder<T>

    fun withLargeLeftGap(): CellBuilder<T>
    fun withLeftGap(): CellBuilder<T>

    /**
     * All components of the same group share will get the same BoundSize (min/preferred/max),
     * which is that of the biggest component in the group
     */
    fun sizeGroup(name: String): CellBuilder<T>
    fun growPolicy(growPolicy: GrowPolicy): CellBuilder<T>
    fun constraints(vararg constraints: Constraint): CellBuilder<T>

    fun applyToComponent(task: T.() -> Unit): CellBuilder<T> {
        return also { task(component) }
    }

    fun on(vararg keyCode: KeyStroke, action: T.(ActionEvent?) -> Unit) {
        component.on(*keyCode, action = action)
    }

    operator fun invoke(action: CellBuilder<T>.() -> Unit): CellBuilder<T> = apply(action)

    fun CheckboxCellBuilder.actsAsLabel()
}

typealias CheckboxCellBuilder = CellBuilder<JCheckBox>
class ScrollPaneCellBuilder<T : JComponent> internal constructor(
    builder: CellBuilder<T>
) : CellBuilder<T> by builder {
    fun noGrowY() {
        constraints(Cell.growY.withWeight(0.0f), Cell.pushY.withWeight(0.0f))
    }
}

fun <T : JSpinner> CellBuilder<T>.withIntBinding(modelBinding: MutableProperty<Int>): CellBuilder<T> {
    return withBinding(
        { it.value as Int },
        JSpinner::setValue,
        modelBinding
    ) { comp: JSpinner, listener: (ChangeEvent) -> Unit ->
        comp.addChangeListener(listener)
    }
}

fun <T : JTextComponent> CellBuilder<T>.withTextBinding(modelBinding: MutableProperty<String>): CellBuilder<T> {
    return withBinding(
        JTextComponent::getText,
        JTextComponent::setText,
        modelBinding
    ) { comp: JTextComponent, listener: (DocumentEvent?) -> Unit ->
        comp.addDocumentChangeListener(listener)
    }
}

fun <T : AbstractButton> CellBuilder<T>.withSelectedBinding(modelBinding: MutableProperty<Boolean>): CellBuilder<T> {
    return withBinding(
        AbstractButton::isSelected,
        AbstractButton::setSelected,
        modelBinding
    ) { comp: AbstractButton, listener: (ChangeEvent) -> Unit ->
        comp.addChangeListener(listener)
    }
}

fun <T : JButton> CellBuilder<T>.makeDefaultButton(requestFocus: Boolean = true): CellBuilder<T> = applyToComponent {
    this@applyToComponent.makeDefaultButton(requestFocus)
}

fun <V, T : JList<V>> CellBuilder<T>.observableSelection(): ObservableProperty<IntArray> =
    component.observableSelection()

fun <T : JSpinner> CellBuilder<T>.observableIntValue(): ObservableMutableProperty<Int> =
    observableValue()

fun <T : JSpinner, V> CellBuilder<T>.observableValue(): ObservableMutableProperty<V> =
    component.observableValue()

fun <T : JTextComponent> CellBuilder<T>.observableText(): ObservableMutableProperty<String> =
    component.observableText()

fun <T : AbstractButton> CellBuilder<T>.observableSelected(): ObservableMutableProperty<Boolean> =
    component.observableSelected()
