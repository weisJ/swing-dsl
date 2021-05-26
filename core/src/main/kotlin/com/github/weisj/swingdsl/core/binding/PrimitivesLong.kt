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

// Auto-generated file. DO NOT EDIT!
@file:Suppress("unused")

package com.github.weisj.swingdsl.core.binding

@JvmName("longComparedToByte")
infix fun ObservableProperty<Long>.comparedTo(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("longComparedToByteP")
infix fun ObservableProperty<Long>.comparedTo(other: Byte): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PLongComparedToByte")
infix fun Long.comparedTo(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("longLessThanByte")
infix fun ObservableProperty<Long>.lessThan(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("longLessThanByteP")
infix fun ObservableProperty<Long>.lessThan(other: Byte): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PLongLessThanByte")
infix fun Long.lessThan(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("longLessThanOrEqualsByte")
infix fun ObservableProperty<Long>.lessThanOrEquals(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("longLessThanOrEqualsByteP")
infix fun ObservableProperty<Long>.lessThanOrEquals(other: Byte): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PLongLessThanOrEqualsByte")
infix fun Long.lessThanOrEquals(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("longGreaterThanByte")
infix fun ObservableProperty<Long>.greaterThan(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("longGreaterThanByteP")
infix fun ObservableProperty<Long>.greaterThan(other: Byte): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PLongGreaterThanByte")
infix fun Long.greaterThan(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("longGreaterThanOrEqualsByte")
infix fun ObservableProperty<Long>.greaterThanOrEquals(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("longGreaterThanOrEqualsByteP")
infix fun ObservableProperty<Long>.greaterThanOrEquals(other: Byte): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PLongGreaterThanOrEqualsByte")
infix fun Long.greaterThanOrEquals(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("longComparedToShort")
infix fun ObservableProperty<Long>.comparedTo(other: ObservableProperty<Short>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("longComparedToShortP")
infix fun ObservableProperty<Long>.comparedTo(other: Short): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PLongComparedToShort")
infix fun Long.comparedTo(other: ObservableProperty<Short>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("longLessThanShort")
infix fun ObservableProperty<Long>.lessThan(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("longLessThanShortP")
infix fun ObservableProperty<Long>.lessThan(other: Short): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PLongLessThanShort")
infix fun Long.lessThan(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("longLessThanOrEqualsShort")
infix fun ObservableProperty<Long>.lessThanOrEquals(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("longLessThanOrEqualsShortP")
infix fun ObservableProperty<Long>.lessThanOrEquals(other: Short): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PLongLessThanOrEqualsShort")
infix fun Long.lessThanOrEquals(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("longGreaterThanShort")
infix fun ObservableProperty<Long>.greaterThan(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("longGreaterThanShortP")
infix fun ObservableProperty<Long>.greaterThan(other: Short): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PLongGreaterThanShort")
infix fun Long.greaterThan(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("longGreaterThanOrEqualsShort")
infix fun ObservableProperty<Long>.greaterThanOrEquals(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("longGreaterThanOrEqualsShortP")
infix fun ObservableProperty<Long>.greaterThanOrEquals(other: Short): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PLongGreaterThanOrEqualsShort")
infix fun Long.greaterThanOrEquals(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("longComparedToInt")
infix fun ObservableProperty<Long>.comparedTo(other: ObservableProperty<Int>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("longComparedToIntP")
infix fun ObservableProperty<Long>.comparedTo(other: Int): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PLongComparedToInt")
infix fun Long.comparedTo(other: ObservableProperty<Int>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("longLessThanInt")
infix fun ObservableProperty<Long>.lessThan(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("longLessThanIntP")
infix fun ObservableProperty<Long>.lessThan(other: Int): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PLongLessThanInt")
infix fun Long.lessThan(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("longLessThanOrEqualsInt")
infix fun ObservableProperty<Long>.lessThanOrEquals(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("longLessThanOrEqualsIntP")
infix fun ObservableProperty<Long>.lessThanOrEquals(other: Int): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PLongLessThanOrEqualsInt")
infix fun Long.lessThanOrEquals(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("longGreaterThanInt")
infix fun ObservableProperty<Long>.greaterThan(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("longGreaterThanIntP")
infix fun ObservableProperty<Long>.greaterThan(other: Int): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PLongGreaterThanInt")
infix fun Long.greaterThan(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("longGreaterThanOrEqualsInt")
infix fun ObservableProperty<Long>.greaterThanOrEquals(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("longGreaterThanOrEqualsIntP")
infix fun ObservableProperty<Long>.greaterThanOrEquals(other: Int): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PLongGreaterThanOrEqualsInt")
infix fun Long.greaterThanOrEquals(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("longComparedToLong")
infix fun ObservableProperty<Long>.comparedTo(other: ObservableProperty<Long>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("longComparedToLongP")
infix fun ObservableProperty<Long>.comparedTo(other: Long): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PLongComparedToLong")
infix fun Long.comparedTo(other: ObservableProperty<Long>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("longLessThanLong")
infix fun ObservableProperty<Long>.lessThan(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("longLessThanLongP")
infix fun ObservableProperty<Long>.lessThan(other: Long): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PLongLessThanLong")
infix fun Long.lessThan(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("longLessThanOrEqualsLong")
infix fun ObservableProperty<Long>.lessThanOrEquals(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("longLessThanOrEqualsLongP")
infix fun ObservableProperty<Long>.lessThanOrEquals(other: Long): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PLongLessThanOrEqualsLong")
infix fun Long.lessThanOrEquals(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("longGreaterThanLong")
infix fun ObservableProperty<Long>.greaterThan(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("longGreaterThanLongP")
infix fun ObservableProperty<Long>.greaterThan(other: Long): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PLongGreaterThanLong")
infix fun Long.greaterThan(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("longGreaterThanOrEqualsLong")
infix fun ObservableProperty<Long>.greaterThanOrEquals(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("longGreaterThanOrEqualsLongP")
infix fun ObservableProperty<Long>.greaterThanOrEquals(other: Long): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PLongGreaterThanOrEqualsLong")
infix fun Long.greaterThanOrEquals(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("longComparedToFloat")
infix fun ObservableProperty<Long>.comparedTo(other: ObservableProperty<Float>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("longComparedToFloatP")
infix fun ObservableProperty<Long>.comparedTo(other: Float): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PLongComparedToFloat")
infix fun Long.comparedTo(other: ObservableProperty<Float>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("longLessThanFloat")
infix fun ObservableProperty<Long>.lessThan(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("longLessThanFloatP")
infix fun ObservableProperty<Long>.lessThan(other: Float): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PLongLessThanFloat")
infix fun Long.lessThan(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("longLessThanOrEqualsFloat")
infix fun ObservableProperty<Long>.lessThanOrEquals(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("longLessThanOrEqualsFloatP")
infix fun ObservableProperty<Long>.lessThanOrEquals(other: Float): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PLongLessThanOrEqualsFloat")
infix fun Long.lessThanOrEquals(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("longGreaterThanFloat")
infix fun ObservableProperty<Long>.greaterThan(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("longGreaterThanFloatP")
infix fun ObservableProperty<Long>.greaterThan(other: Float): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PLongGreaterThanFloat")
infix fun Long.greaterThan(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("longGreaterThanOrEqualsFloat")
infix fun ObservableProperty<Long>.greaterThanOrEquals(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("longGreaterThanOrEqualsFloatP")
infix fun ObservableProperty<Long>.greaterThanOrEquals(other: Float): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PLongGreaterThanOrEqualsFloat")
infix fun Long.greaterThanOrEquals(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("longComparedToDouble")
infix fun ObservableProperty<Long>.comparedTo(other: ObservableProperty<Double>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("longComparedToDoubleP")
infix fun ObservableProperty<Long>.comparedTo(other: Double): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PLongComparedToDouble")
infix fun Long.comparedTo(other: ObservableProperty<Double>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("longLessThanDouble")
infix fun ObservableProperty<Long>.lessThan(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("longLessThanDoubleP")
infix fun ObservableProperty<Long>.lessThan(other: Double): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PLongLessThanDouble")
infix fun Long.lessThan(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("longLessThanOrEqualsDouble")
infix fun ObservableProperty<Long>.lessThanOrEquals(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("longLessThanOrEqualsDoubleP")
infix fun ObservableProperty<Long>.lessThanOrEquals(other: Double): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PLongLessThanOrEqualsDouble")
infix fun Long.lessThanOrEquals(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("longGreaterThanDouble")
infix fun ObservableProperty<Long>.greaterThan(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("longGreaterThanDoubleP")
infix fun ObservableProperty<Long>.greaterThan(other: Double): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PLongGreaterThanDouble")
infix fun Long.greaterThan(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("longGreaterThanOrEqualsDouble")
infix fun ObservableProperty<Long>.greaterThanOrEquals(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("longGreaterThanOrEqualsDoubleP")
infix fun ObservableProperty<Long>.greaterThanOrEquals(other: Double): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PLongGreaterThanOrEqualsDouble")
infix fun Long.greaterThanOrEquals(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("longPlusByte")
operator fun ObservableProperty<Long>.plus(other: ObservableProperty<Byte>): ObservableProperty<Long> =
    combine(other) { a, b -> a + b }

@JvmName("longPlusByteP")
operator fun ObservableProperty<Long>.plus(other: Byte): ObservableProperty<Long> =
    derive { a -> a + other }

@JvmName("PLongPlusByte")
operator fun Long.plus(other: ObservableProperty<Byte>): ObservableProperty<Long> =
    other.derive { a -> this + a }

@JvmName("longPlusShort")
operator fun ObservableProperty<Long>.plus(other: ObservableProperty<Short>): ObservableProperty<Long> =
    combine(other) { a, b -> a + b }

@JvmName("longPlusShortP")
operator fun ObservableProperty<Long>.plus(other: Short): ObservableProperty<Long> =
    derive { a -> a + other }

@JvmName("PLongPlusShort")
operator fun Long.plus(other: ObservableProperty<Short>): ObservableProperty<Long> =
    other.derive { a -> this + a }

@JvmName("longPlusInt")
operator fun ObservableProperty<Long>.plus(other: ObservableProperty<Int>): ObservableProperty<Long> =
    combine(other) { a, b -> a + b }

@JvmName("longPlusIntP")
operator fun ObservableProperty<Long>.plus(other: Int): ObservableProperty<Long> =
    derive { a -> a + other }

@JvmName("PLongPlusInt")
operator fun Long.plus(other: ObservableProperty<Int>): ObservableProperty<Long> =
    other.derive { a -> this + a }

@JvmName("longPlusLong")
operator fun ObservableProperty<Long>.plus(other: ObservableProperty<Long>): ObservableProperty<Long> =
    combine(other) { a, b -> a + b }

@JvmName("longPlusLongP")
operator fun ObservableProperty<Long>.plus(other: Long): ObservableProperty<Long> =
    derive { a -> a + other }

@JvmName("PLongPlusLong")
operator fun Long.plus(other: ObservableProperty<Long>): ObservableProperty<Long> =
    other.derive { a -> this + a }

@JvmName("longPlusFloat")
operator fun ObservableProperty<Long>.plus(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("longPlusFloatP")
operator fun ObservableProperty<Long>.plus(other: Float): ObservableProperty<Float> =
    derive { a -> a + other }

@JvmName("PLongPlusFloat")
operator fun Long.plus(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this + a }

@JvmName("longPlusDouble")
operator fun ObservableProperty<Long>.plus(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("longPlusDoubleP")
operator fun ObservableProperty<Long>.plus(other: Double): ObservableProperty<Double> =
    derive { a -> a + other }

@JvmName("PLongPlusDouble")
operator fun Long.plus(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this + a }

@JvmName("longMinusByte")
operator fun ObservableProperty<Long>.minus(other: ObservableProperty<Byte>): ObservableProperty<Long> =
    combine(other) { a, b -> a - b }

@JvmName("longMinusByteP")
operator fun ObservableProperty<Long>.minus(other: Byte): ObservableProperty<Long> =
    derive { a -> a - other }

@JvmName("PLongMinusByte")
operator fun Long.minus(other: ObservableProperty<Byte>): ObservableProperty<Long> =
    other.derive { a -> this - a }

@JvmName("longMinusShort")
operator fun ObservableProperty<Long>.minus(other: ObservableProperty<Short>): ObservableProperty<Long> =
    combine(other) { a, b -> a - b }

@JvmName("longMinusShortP")
operator fun ObservableProperty<Long>.minus(other: Short): ObservableProperty<Long> =
    derive { a -> a - other }

@JvmName("PLongMinusShort")
operator fun Long.minus(other: ObservableProperty<Short>): ObservableProperty<Long> =
    other.derive { a -> this - a }

@JvmName("longMinusInt")
operator fun ObservableProperty<Long>.minus(other: ObservableProperty<Int>): ObservableProperty<Long> =
    combine(other) { a, b -> a - b }

@JvmName("longMinusIntP")
operator fun ObservableProperty<Long>.minus(other: Int): ObservableProperty<Long> =
    derive { a -> a - other }

@JvmName("PLongMinusInt")
operator fun Long.minus(other: ObservableProperty<Int>): ObservableProperty<Long> =
    other.derive { a -> this - a }

@JvmName("longMinusLong")
operator fun ObservableProperty<Long>.minus(other: ObservableProperty<Long>): ObservableProperty<Long> =
    combine(other) { a, b -> a - b }

@JvmName("longMinusLongP")
operator fun ObservableProperty<Long>.minus(other: Long): ObservableProperty<Long> =
    derive { a -> a - other }

@JvmName("PLongMinusLong")
operator fun Long.minus(other: ObservableProperty<Long>): ObservableProperty<Long> =
    other.derive { a -> this - a }

@JvmName("longMinusFloat")
operator fun ObservableProperty<Long>.minus(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("longMinusFloatP")
operator fun ObservableProperty<Long>.minus(other: Float): ObservableProperty<Float> =
    derive { a -> a - other }

@JvmName("PLongMinusFloat")
operator fun Long.minus(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this - a }

@JvmName("longMinusDouble")
operator fun ObservableProperty<Long>.minus(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("longMinusDoubleP")
operator fun ObservableProperty<Long>.minus(other: Double): ObservableProperty<Double> =
    derive { a -> a - other }

@JvmName("PLongMinusDouble")
operator fun Long.minus(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this - a }

@JvmName("longTimesByte")
operator fun ObservableProperty<Long>.times(other: ObservableProperty<Byte>): ObservableProperty<Long> =
    combine(other) { a, b -> a * b }

@JvmName("longTimesByteP")
operator fun ObservableProperty<Long>.times(other: Byte): ObservableProperty<Long> =
    derive { a -> a * other }

@JvmName("PLongTimesByte")
operator fun Long.times(other: ObservableProperty<Byte>): ObservableProperty<Long> =
    other.derive { a -> this * a }

@JvmName("longTimesShort")
operator fun ObservableProperty<Long>.times(other: ObservableProperty<Short>): ObservableProperty<Long> =
    combine(other) { a, b -> a * b }

@JvmName("longTimesShortP")
operator fun ObservableProperty<Long>.times(other: Short): ObservableProperty<Long> =
    derive { a -> a * other }

@JvmName("PLongTimesShort")
operator fun Long.times(other: ObservableProperty<Short>): ObservableProperty<Long> =
    other.derive { a -> this * a }

@JvmName("longTimesInt")
operator fun ObservableProperty<Long>.times(other: ObservableProperty<Int>): ObservableProperty<Long> =
    combine(other) { a, b -> a * b }

@JvmName("longTimesIntP")
operator fun ObservableProperty<Long>.times(other: Int): ObservableProperty<Long> =
    derive { a -> a * other }

@JvmName("PLongTimesInt")
operator fun Long.times(other: ObservableProperty<Int>): ObservableProperty<Long> =
    other.derive { a -> this * a }

@JvmName("longTimesLong")
operator fun ObservableProperty<Long>.times(other: ObservableProperty<Long>): ObservableProperty<Long> =
    combine(other) { a, b -> a * b }

@JvmName("longTimesLongP")
operator fun ObservableProperty<Long>.times(other: Long): ObservableProperty<Long> =
    derive { a -> a * other }

@JvmName("PLongTimesLong")
operator fun Long.times(other: ObservableProperty<Long>): ObservableProperty<Long> =
    other.derive { a -> this * a }

@JvmName("longTimesFloat")
operator fun ObservableProperty<Long>.times(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("longTimesFloatP")
operator fun ObservableProperty<Long>.times(other: Float): ObservableProperty<Float> =
    derive { a -> a * other }

@JvmName("PLongTimesFloat")
operator fun Long.times(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this * a }

@JvmName("longTimesDouble")
operator fun ObservableProperty<Long>.times(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("longTimesDoubleP")
operator fun ObservableProperty<Long>.times(other: Double): ObservableProperty<Double> =
    derive { a -> a * other }

@JvmName("PLongTimesDouble")
operator fun Long.times(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this * a }

@JvmName("longDivByte")
operator fun ObservableProperty<Long>.div(other: ObservableProperty<Byte>): ObservableProperty<Long> =
    combine(other) { a, b -> a / b }

@JvmName("longDivByteP")
operator fun ObservableProperty<Long>.div(other: Byte): ObservableProperty<Long> =
    derive { a -> a / other }

@JvmName("PLongDivByte")
operator fun Long.div(other: ObservableProperty<Byte>): ObservableProperty<Long> =
    other.derive { a -> this / a }

@JvmName("longDivShort")
operator fun ObservableProperty<Long>.div(other: ObservableProperty<Short>): ObservableProperty<Long> =
    combine(other) { a, b -> a / b }

@JvmName("longDivShortP")
operator fun ObservableProperty<Long>.div(other: Short): ObservableProperty<Long> =
    derive { a -> a / other }

@JvmName("PLongDivShort")
operator fun Long.div(other: ObservableProperty<Short>): ObservableProperty<Long> =
    other.derive { a -> this / a }

@JvmName("longDivInt")
operator fun ObservableProperty<Long>.div(other: ObservableProperty<Int>): ObservableProperty<Long> =
    combine(other) { a, b -> a / b }

@JvmName("longDivIntP")
operator fun ObservableProperty<Long>.div(other: Int): ObservableProperty<Long> =
    derive { a -> a / other }

@JvmName("PLongDivInt")
operator fun Long.div(other: ObservableProperty<Int>): ObservableProperty<Long> =
    other.derive { a -> this / a }

@JvmName("longDivLong")
operator fun ObservableProperty<Long>.div(other: ObservableProperty<Long>): ObservableProperty<Long> =
    combine(other) { a, b -> a / b }

@JvmName("longDivLongP")
operator fun ObservableProperty<Long>.div(other: Long): ObservableProperty<Long> =
    derive { a -> a / other }

@JvmName("PLongDivLong")
operator fun Long.div(other: ObservableProperty<Long>): ObservableProperty<Long> =
    other.derive { a -> this / a }

@JvmName("longDivFloat")
operator fun ObservableProperty<Long>.div(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("longDivFloatP")
operator fun ObservableProperty<Long>.div(other: Float): ObservableProperty<Float> =
    derive { a -> a / other }

@JvmName("PLongDivFloat")
operator fun Long.div(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this / a }

@JvmName("longDivDouble")
operator fun ObservableProperty<Long>.div(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("longDivDoubleP")
operator fun ObservableProperty<Long>.div(other: Double): ObservableProperty<Double> =
    derive { a -> a / other }

@JvmName("PLongDivDouble")
operator fun Long.div(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this / a }

@JvmName("longRemByte")
operator fun ObservableProperty<Long>.rem(other: ObservableProperty<Byte>): ObservableProperty<Long> =
    combine(other) { a, b -> a % b }

@JvmName("longRemByteP")
operator fun ObservableProperty<Long>.rem(other: Byte): ObservableProperty<Long> =
    derive { a -> a % other }

@JvmName("PLongRemByte")
operator fun Long.rem(other: ObservableProperty<Byte>): ObservableProperty<Long> =
    other.derive { a -> this % a }

@JvmName("longRemShort")
operator fun ObservableProperty<Long>.rem(other: ObservableProperty<Short>): ObservableProperty<Long> =
    combine(other) { a, b -> a % b }

@JvmName("longRemShortP")
operator fun ObservableProperty<Long>.rem(other: Short): ObservableProperty<Long> =
    derive { a -> a % other }

@JvmName("PLongRemShort")
operator fun Long.rem(other: ObservableProperty<Short>): ObservableProperty<Long> =
    other.derive { a -> this % a }

@JvmName("longRemInt")
operator fun ObservableProperty<Long>.rem(other: ObservableProperty<Int>): ObservableProperty<Long> =
    combine(other) { a, b -> a % b }

@JvmName("longRemIntP")
operator fun ObservableProperty<Long>.rem(other: Int): ObservableProperty<Long> =
    derive { a -> a % other }

@JvmName("PLongRemInt")
operator fun Long.rem(other: ObservableProperty<Int>): ObservableProperty<Long> =
    other.derive { a -> this % a }

@JvmName("longRemLong")
operator fun ObservableProperty<Long>.rem(other: ObservableProperty<Long>): ObservableProperty<Long> =
    combine(other) { a, b -> a % b }

@JvmName("longRemLongP")
operator fun ObservableProperty<Long>.rem(other: Long): ObservableProperty<Long> =
    derive { a -> a % other }

@JvmName("PLongRemLong")
operator fun Long.rem(other: ObservableProperty<Long>): ObservableProperty<Long> =
    other.derive { a -> this % a }

@JvmName("longRemFloat")
operator fun ObservableProperty<Long>.rem(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("longRemFloatP")
operator fun ObservableProperty<Long>.rem(other: Float): ObservableProperty<Float> =
    derive { a -> a % other }

@JvmName("PLongRemFloat")
operator fun Long.rem(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this % a }

@JvmName("longRemDouble")
operator fun ObservableProperty<Long>.rem(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("longRemDoubleP")
operator fun ObservableProperty<Long>.rem(other: Double): ObservableProperty<Double> =
    derive { a -> a % other }

@JvmName("PLongRemDouble")
operator fun Long.rem(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this % a }

@JvmName("longUnaryPlus")
operator fun ObservableProperty<Long>.unaryPlus(): ObservableProperty<Long> = this

@JvmName("longUnaryMinus")
operator fun ObservableProperty<Long>.unaryMinus(): ObservableProperty<Long> =
    derive { a -> -a }

@JvmName("longRangeToByte")
operator fun ObservableProperty<Long>.rangeTo(other: ObservableProperty<Byte>): ObservableProperty<LongRange> =
    combine(other) { a, b -> a..b }

@JvmName("longRangeToByteP")
operator fun ObservableProperty<Long>.rangeTo(other: Byte): ObservableProperty<LongRange> =
    derive { a -> a..other }

@JvmName("PLongRangeToByte")
operator fun Long.rangeTo(other: ObservableProperty<Byte>): ObservableProperty<LongRange> =
    other.derive { a -> this..a }

@JvmName("longRangeToShort")
operator fun ObservableProperty<Long>.rangeTo(other: ObservableProperty<Short>): ObservableProperty<LongRange> =
    combine(other) { a, b -> a..b }

@JvmName("longRangeToShortP")
operator fun ObservableProperty<Long>.rangeTo(other: Short): ObservableProperty<LongRange> =
    derive { a -> a..other }

@JvmName("PLongRangeToShort")
operator fun Long.rangeTo(other: ObservableProperty<Short>): ObservableProperty<LongRange> =
    other.derive { a -> this..a }

@JvmName("longRangeToInt")
operator fun ObservableProperty<Long>.rangeTo(other: ObservableProperty<Int>): ObservableProperty<LongRange> =
    combine(other) { a, b -> a..b }

@JvmName("longRangeToIntP")
operator fun ObservableProperty<Long>.rangeTo(other: Int): ObservableProperty<LongRange> =
    derive { a -> a..other }

@JvmName("PLongRangeToInt")
operator fun Long.rangeTo(other: ObservableProperty<Int>): ObservableProperty<LongRange> =
    other.derive { a -> this..a }

@JvmName("longRangeToLong")
operator fun ObservableProperty<Long>.rangeTo(other: ObservableProperty<Long>): ObservableProperty<LongRange> =
    combine(other) { a, b -> a..b }

@JvmName("longRangeToLongP")
operator fun ObservableProperty<Long>.rangeTo(other: Long): ObservableProperty<LongRange> =
    derive { a -> a..other }

@JvmName("PLongRangeToLong")
operator fun Long.rangeTo(other: ObservableProperty<Long>): ObservableProperty<LongRange> =
    other.derive { a -> this..a }

@JvmName("longToByte")
fun ObservableProperty<Long>.toByte(): ObservableProperty<Byte> =
    derive { a -> a.toByte() }

@JvmName("longToShort")
fun ObservableProperty<Long>.toShort(): ObservableProperty<Short> =
    derive { a -> a.toShort() }

@JvmName("longToInt")
fun ObservableProperty<Long>.toInt(): ObservableProperty<Int> =
    derive { a -> a.toInt() }

@JvmName("longToLong")
fun ObservableProperty<Long>.toLong(): ObservableProperty<Long> = this

@JvmName("longToFloat")
fun ObservableProperty<Long>.toFloat(): ObservableProperty<Float> =
    derive { a -> a.toFloat() }

@JvmName("longToDouble")
fun ObservableProperty<Long>.toDouble(): ObservableProperty<Double> =
    derive { a -> a.toDouble() }
