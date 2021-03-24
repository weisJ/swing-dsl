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
package com.github.weisj.swingdsl.binding

import com.github.weisj.swingdsl.condition.BoundCondition
import com.github.weisj.swingdsl.condition.not

@JvmName("rangeToFloat")
operator fun BoundProperty<Float>.rangeTo(other: BoundProperty<Float>): BoundProperty<ClosedFloatingPointRange<Float>> =
    combine(other) { a, b -> a..b }

@JvmName("rangeToDouble")
operator fun BoundProperty<Double>.rangeTo(other: BoundProperty<Double>): BoundProperty<ClosedFloatingPointRange<Double>> =
    combine(other) { a, b -> a..b }

@JvmName("rangeToComparable")
operator fun <T : Comparable<T>> BoundProperty<T>.rangeTo(other: BoundProperty<T>): BoundProperty<ClosedRange<T>> =
    combine(other) { a, b -> a..b }

fun <T : Comparable<T>> BoundProperty<ClosedRange<T>>.contains(element: T): BoundCondition =
    derive { it.contains(element) }

val <T : Comparable<T>> BoundProperty<ClosedRange<T>>.start: BoundProperty<T> get() = derive { it.start }
val <T : Comparable<T>> BoundProperty<ClosedRange<T>>.endInclusive: BoundProperty<T> get() = derive { it.endInclusive }
fun <T : Comparable<T>> BoundProperty<ClosedRange<T>>.isEmpty(): BoundCondition = derive { it.isEmpty() }
fun <T : Comparable<T>> BoundProperty<ClosedRange<T>>.isNotEmpty(): BoundCondition = !isEmpty()
