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

import com.github.weisj.swingdsl.condition.BoundCondition
import com.github.weisj.swingdsl.condition.not
import kotlin.reflect.KMutableProperty0

data class PropertyBinding<V>(val get: () -> V, val set: (V) -> Unit)

interface BoundProperty<out T> {
    fun get(): T
    fun onPropertyChange(callback: (T) -> Unit)
}

interface MutableBoundProperty<T> : BoundProperty<T> {
    fun set(value: T)
}

private class DerivedProperty<T, K>(private val prop: BoundProperty<K>, private val transform: (K) -> T) :
    BoundProperty<T> {
    override fun get(): T = transform(prop.get())

    override fun onPropertyChange(callback: (T) -> Unit) {
        prop.onPropertyChange { callback(get()) }
    }
}

private open class CombinedProperty<T, K1, K2>(
    private val first: BoundProperty<K1>,
    private val second: BoundProperty<K2>,
    private val combiner: (K1, K2) -> T
) : BoundProperty<T> {
    override fun get(): T = combiner(first.get(), second.get())

    override fun onPropertyChange(callback: (T) -> Unit) {
        first.onPropertyChange { callback(get()) }
        second.onPropertyChange { callback(get()) }
    }
}

fun <T, K> BoundProperty<K>.derive(transform: (K) -> T): BoundProperty<T> = DerivedProperty(this, transform)

fun <T, K1, K2> combinedProperty(
    first: BoundProperty<K1>,
    second: BoundProperty<K2>,
    combiner: (K1, K2) -> T
): BoundProperty<T> =
    CombinedProperty(first, second, combiner)

fun <K1, K2, T> BoundProperty<K1>.combine(
    other: BoundProperty<K2>,
    combiner: (K1, K2) -> T
): BoundProperty<T> = combinedProperty(this, other, combiner)

@PublishedApi
internal fun <T> createPropertyBinding(prop: KMutableProperty0<T>): PropertyBinding<T> {
    return PropertyBinding({ prop.get() }, { prop.set(it) })
}

fun <T> KMutableProperty0<T>.toBinding(): PropertyBinding<T> {
    return createPropertyBinding(this)
}

fun <T> PropertyBinding<T?>.withFallback(fallback: T): PropertyBinding<T> {
    return PropertyBinding({ get() ?: fallback }, { set(it) })
}

fun <T> PropertyBinding<T>.toNullable(): PropertyBinding<T?> {
    return PropertyBinding<T?>({ get() }, { if (it != null) set(it) })
}

fun <T> BoundProperty<T?>.isNull(): BoundCondition = derive { it == null }
fun <T> BoundProperty<T?>.isNotNull(): BoundCondition = !isNull()
