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
package com.github.weisj.swingdsl.binding

import com.github.weisj.swingdsl.condition.ObservableCondition
import com.github.weisj.swingdsl.condition.not
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty

interface Property<out T> {
    fun get(): T
}

class SimpleProperty<T>(private val getter: () -> T) : Property<T> {
    override fun get(): T = getter()
}

interface MutableProperty<T> : Property<T> {
    fun set(value: T)
}

data class PropertyBinding<V>(
    private val getter: () -> V,
    private val setter: (V) -> Unit
) : MutableProperty<V> {
    override fun get(): V = getter()

    override fun set(value: V) {
        setter(value)
    }
}

interface Observable<out T> {
    fun onChange(callback: (T) -> Unit)
}

class PseudoObservableProperty<T>(val getter: () -> T) : ObservableProperty<T> {
    override fun get(): T = getter()

    override fun onChange(callback: (T) -> Unit) {
        /* Can't know whether something changed */
    }
}

interface ObservableProperty<out T> : Property<T>, Observable<T>

fun <T> ObservableProperty<T>.onChange(invokeOnce: Boolean, callback: (T) -> Unit) {
    onChange(callback)
    if (invokeOnce) callback(get())
}

interface ObservableMutableProperty<T> : MutableProperty<T>, ObservableProperty<T>

operator fun <R, T> Property<T>.getValue(thisRef: R, property: KProperty<*>): T {
    return get()
}

operator fun <R, T> MutableProperty<T>.setValue(thisRef: R, property: KProperty<*>, value: T) {
    return set(value)
}

class ChangeTracker<T>(private val prop: Property<T>) {
    private var current: T = prop.get()
    private val changeStatus: MutableMap<(T) -> Unit, Boolean> by lazy { mutableMapOf() }

    private fun refresh() {
        val updated = prop.get()
        val changed = updated != current
        current = updated
        if (changed) {
            changeStatus.entries.forEach { it.setValue(true) }
        }
    }

    fun registerListener(listener: (T) -> Unit) {
        changeStatus[listener] = false
    }

    fun hasChangedFor(listener: (T) -> Unit): Boolean {
        refresh()
        return changeStatus[listener] ?: true
    }
}

private open class DerivedProperty<T, K, PropType : Property<K>>(
    protected val prop: PropType,
    private val transform: (K) -> T
) : Property<T> {
    override fun get(): T = transform(prop.get())
}

private class ObservableDerivedProperty<T, K>(
    prop: ObservableProperty<K>,
    transform: (K) -> T
) : DerivedProperty<T, K, ObservableProperty<K>>(prop, transform), ObservableProperty<T> {
    private val changeTracker = ChangeTracker(this)

    override fun onChange(callback: (T) -> Unit) {
        changeTracker.registerListener(callback)
        prop.onChange {
            if (changeTracker.hasChangedFor(callback)) callback(get())
        }
    }
}

private class CombinedProperty<T, K1, K2>(
    private val first: ObservableProperty<K1>,
    private val second: ObservableProperty<K2>,
    private val combiner: (K1, K2) -> T
) : ObservableProperty<T> {
    private val changeTracker = ChangeTracker(this)
    override fun get(): T = combiner(first.get(), second.get())

    override fun onChange(callback: (T) -> Unit) {
        changeTracker.registerListener(callback)
        first.onChange { if (changeTracker.hasChangedFor(callback)) callback(get()) }
        second.onChange { if (changeTracker.hasChangedFor(callback)) callback(get()) }
    }
}

fun <T, K> ObservableProperty<K>.derive(transform: (K) -> T): ObservableProperty<T> =
    ObservableDerivedProperty(this, transform)

fun <T, K> Property<K>.derive(transform: (K) -> T): Property<T> =
    DerivedProperty(this, transform)

fun <T, K1, K2> combinedProperty(
    first: ObservableProperty<K1>,
    second: ObservableProperty<K2>,
    combiner: (K1, K2) -> T
): ObservableProperty<T> = CombinedProperty(first, second, combiner)

fun <K1, K2, T> ObservableProperty<K1>.combine(
    other: ObservableProperty<K2>,
    combiner: (K1, K2) -> T
): ObservableProperty<T> = combinedProperty(this, other, combiner)

class KPropertyBackedProperty<T>(val prop: KMutableProperty0<T>) : MutableProperty<T> {
    override fun get(): T = prop.get()

    override fun set(value: T) = prop.set(value)
}

internal fun <T> createPropertyBinding(prop: KMutableProperty0<T>): MutableProperty<T> {
    return KPropertyBackedProperty(prop)
}

fun <T> KMutableProperty0<T>.toProperty(): MutableProperty<T> {
    return createPropertyBinding(this)
}

fun <T> MutableProperty<T?>.withFallback(fallback: T): MutableProperty<T> {
    return PropertyBinding({ get() ?: fallback }, { set(it) })
}

fun <T> MutableProperty<T>.toNullable(): MutableProperty<T?> {
    return PropertyBinding({ get() }, { if (it != null) set(it) })
}

fun <T> ObservableProperty<T?>.withFallback(fallback: T): ObservableProperty<T> =
    derive { it ?: fallback }

fun <T> ObservableProperty<T?>.isNull(): ObservableCondition = derive { it == null }
fun <T> ObservableProperty<T?>.isNotNull(): ObservableCondition = !isNull()

fun <T> ObservableProperty<T>.bind(setter: (T) -> Unit) {
    onChange(invokeOnce = true) { setter(it) }
}

fun <T> ObservableProperty<T>.bind(prop: MutableProperty<T>) {
    onChange(invokeOnce = true) { prop.set(it) }
}

fun <T> ObservableProperty<T>.bind(prop: KMutableProperty0<T>) {
    onChange(invokeOnce = true) { prop.set(it) }
}

fun <T> observableProperty(initial: T): ObservableMutableProperty<T> = object : ObservableMutableProperty<T> {
    private val listeners by lazy { mutableListOf<(T) -> Unit>() }
    private var backingField: T = initial

    override fun get(): T = backingField
    override fun set(value: T) {
        if (backingField != value) {
            backingField = value
            listeners.forEach { it(backingField) }
        }
    }

    override fun onChange(callback: (T) -> Unit) {
        listeners.add(callback)
    }
}
