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
package com.github.weisj.swingdsl

import com.github.weisj.swingdsl.condition.Condition
import com.github.weisj.swingdsl.text.Text
import javax.swing.AbstractButton
import javax.swing.JComponent
import javax.swing.JTextField

@DslMarker
annotation class CellMarker

interface CellBuilder<out T : JComponent> {
    val component: T

    fun onApply(callback: () -> Unit): CellBuilder<T>
    fun onReset(callback: () -> Unit): CellBuilder<T>
    fun onIsModified(callback: () -> Boolean): CellBuilder<T>

    fun shouldSaveOnApply(): Boolean

    fun <V> withBinding(
        componentGet: (T) -> V,
        componentSet: (T, V) -> Unit,
        modelBinding: PropertyBinding<V>
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

    fun commentComponent(component: JComponent, forComponent: Boolean = false): CellBuilder<T>

    fun visible(isVisible: Boolean)
    fun visibleIf(predicate: Condition): CellBuilder<T>

    fun enabled(isEnabled: Boolean)
    fun enableIf(predicate: Condition): CellBuilder<T>

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
}

internal interface ScrollPaneCellBuilder {
    fun noGrowY()
}

internal interface CheckboxCellBuilder {
    fun actsAsLabel()
}

fun <T : JTextField> CellBuilder<T>.withTextBinding(modelBinding: PropertyBinding<String>): CellBuilder<T> {
    return withBinding(JTextField::getText, JTextField::setText, modelBinding)
}

fun <T : AbstractButton> CellBuilder<T>.withSelectedBinding(modelBinding: PropertyBinding<Boolean>): CellBuilder<T> {
    return withBinding(AbstractButton::isSelected, AbstractButton::setSelected, modelBinding)
}
