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
package com.github.weisj.swingdsl.core.binding

import com.github.weisj.swingdsl.core.condition.ObservableCondition
import com.github.weisj.swingdsl.core.condition.not

@JvmName("rangeToFloat")
operator fun ObservableProperty<Float>.rangeTo(other: ObservableProperty<Float>): ObservableProperty<ClosedFloatingPointRange<Float>> =
    combine(other) { a, b -> a..b }

@JvmName("rangeToDouble")
operator fun ObservableProperty<Double>.rangeTo(other: ObservableProperty<Double>): ObservableProperty<ClosedFloatingPointRange<Double>> =
    combine(other) { a, b -> a..b }

@JvmName("rangeToComparable")
operator fun <T : Comparable<T>> ObservableProperty<T>.rangeTo(other: ObservableProperty<T>): ObservableProperty<ClosedRange<T>> =
    combine(other) { a, b -> a..b }

fun <T : Comparable<T>> ObservableProperty<ClosedRange<T>>.contains(element: T): ObservableCondition =
    derive { it.contains(element) }

val <T : Comparable<T>> ObservableProperty<ClosedRange<T>>.start: ObservableProperty<T> get() = derive { it.start }
val <T : Comparable<T>> ObservableProperty<ClosedRange<T>>.endInclusive: ObservableProperty<T> get() = derive { it.endInclusive }
fun <T : Comparable<T>> ObservableProperty<ClosedRange<T>>.isEmpty(): ObservableCondition = derive { it.isEmpty() }
fun <T : Comparable<T>> ObservableProperty<ClosedRange<T>>.isNotEmpty(): ObservableCondition = !isEmpty()
