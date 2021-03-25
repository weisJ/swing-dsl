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

package com.github.weisj.swingdsl.binding

@JvmName("longComparedToByte")
infix fun BoundProperty<Long>.comparedTo(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("longComparedToByteP")
infix fun BoundProperty<Long>.comparedTo(other: Byte): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PLongComparedToByte")
infix fun Long.comparedTo(other: BoundProperty<Byte>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("longLessThanByte")
infix fun BoundProperty<Long>.lessThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("longLessThanByteP")
infix fun BoundProperty<Long>.lessThan(other: Byte): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PLongLessThanByte")
infix fun Long.lessThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("longLessThanOrEqualsByte")
infix fun BoundProperty<Long>.lessThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("longLessThanOrEqualsByteP")
infix fun BoundProperty<Long>.lessThanOrEquals(other: Byte): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PLongLessThanOrEqualsByte")
infix fun Long.lessThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("longGreaterThanByte")
infix fun BoundProperty<Long>.greaterThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("longGreaterThanByteP")
infix fun BoundProperty<Long>.greaterThan(other: Byte): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PLongGreaterThanByte")
infix fun Long.greaterThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("longGreaterThanOrEqualsByte")
infix fun BoundProperty<Long>.greaterThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("longGreaterThanOrEqualsByteP")
infix fun BoundProperty<Long>.greaterThanOrEquals(other: Byte): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PLongGreaterThanOrEqualsByte")
infix fun Long.greaterThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("longComparedToShort")
infix fun BoundProperty<Long>.comparedTo(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("longComparedToShortP")
infix fun BoundProperty<Long>.comparedTo(other: Short): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PLongComparedToShort")
infix fun Long.comparedTo(other: BoundProperty<Short>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("longLessThanShort")
infix fun BoundProperty<Long>.lessThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("longLessThanShortP")
infix fun BoundProperty<Long>.lessThan(other: Short): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PLongLessThanShort")
infix fun Long.lessThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("longLessThanOrEqualsShort")
infix fun BoundProperty<Long>.lessThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("longLessThanOrEqualsShortP")
infix fun BoundProperty<Long>.lessThanOrEquals(other: Short): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PLongLessThanOrEqualsShort")
infix fun Long.lessThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("longGreaterThanShort")
infix fun BoundProperty<Long>.greaterThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("longGreaterThanShortP")
infix fun BoundProperty<Long>.greaterThan(other: Short): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PLongGreaterThanShort")
infix fun Long.greaterThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("longGreaterThanOrEqualsShort")
infix fun BoundProperty<Long>.greaterThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("longGreaterThanOrEqualsShortP")
infix fun BoundProperty<Long>.greaterThanOrEquals(other: Short): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PLongGreaterThanOrEqualsShort")
infix fun Long.greaterThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("longComparedToInt")
infix fun BoundProperty<Long>.comparedTo(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("longComparedToIntP")
infix fun BoundProperty<Long>.comparedTo(other: Int): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PLongComparedToInt")
infix fun Long.comparedTo(other: BoundProperty<Int>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("longLessThanInt")
infix fun BoundProperty<Long>.lessThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("longLessThanIntP")
infix fun BoundProperty<Long>.lessThan(other: Int): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PLongLessThanInt")
infix fun Long.lessThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("longLessThanOrEqualsInt")
infix fun BoundProperty<Long>.lessThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("longLessThanOrEqualsIntP")
infix fun BoundProperty<Long>.lessThanOrEquals(other: Int): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PLongLessThanOrEqualsInt")
infix fun Long.lessThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("longGreaterThanInt")
infix fun BoundProperty<Long>.greaterThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("longGreaterThanIntP")
infix fun BoundProperty<Long>.greaterThan(other: Int): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PLongGreaterThanInt")
infix fun Long.greaterThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("longGreaterThanOrEqualsInt")
infix fun BoundProperty<Long>.greaterThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("longGreaterThanOrEqualsIntP")
infix fun BoundProperty<Long>.greaterThanOrEquals(other: Int): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PLongGreaterThanOrEqualsInt")
infix fun Long.greaterThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("longComparedToLong")
infix fun BoundProperty<Long>.comparedTo(other: BoundProperty<Long>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("longComparedToLongP")
infix fun BoundProperty<Long>.comparedTo(other: Long): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PLongComparedToLong")
infix fun Long.comparedTo(other: BoundProperty<Long>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("longLessThanLong")
infix fun BoundProperty<Long>.lessThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("longLessThanLongP")
infix fun BoundProperty<Long>.lessThan(other: Long): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PLongLessThanLong")
infix fun Long.lessThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("longLessThanOrEqualsLong")
infix fun BoundProperty<Long>.lessThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("longLessThanOrEqualsLongP")
infix fun BoundProperty<Long>.lessThanOrEquals(other: Long): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PLongLessThanOrEqualsLong")
infix fun Long.lessThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("longGreaterThanLong")
infix fun BoundProperty<Long>.greaterThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("longGreaterThanLongP")
infix fun BoundProperty<Long>.greaterThan(other: Long): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PLongGreaterThanLong")
infix fun Long.greaterThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("longGreaterThanOrEqualsLong")
infix fun BoundProperty<Long>.greaterThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("longGreaterThanOrEqualsLongP")
infix fun BoundProperty<Long>.greaterThanOrEquals(other: Long): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PLongGreaterThanOrEqualsLong")
infix fun Long.greaterThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("longComparedToFloat")
infix fun BoundProperty<Long>.comparedTo(other: BoundProperty<Float>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("longComparedToFloatP")
infix fun BoundProperty<Long>.comparedTo(other: Float): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PLongComparedToFloat")
infix fun Long.comparedTo(other: BoundProperty<Float>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("longLessThanFloat")
infix fun BoundProperty<Long>.lessThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("longLessThanFloatP")
infix fun BoundProperty<Long>.lessThan(other: Float): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PLongLessThanFloat")
infix fun Long.lessThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("longLessThanOrEqualsFloat")
infix fun BoundProperty<Long>.lessThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("longLessThanOrEqualsFloatP")
infix fun BoundProperty<Long>.lessThanOrEquals(other: Float): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PLongLessThanOrEqualsFloat")
infix fun Long.lessThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("longGreaterThanFloat")
infix fun BoundProperty<Long>.greaterThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("longGreaterThanFloatP")
infix fun BoundProperty<Long>.greaterThan(other: Float): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PLongGreaterThanFloat")
infix fun Long.greaterThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("longGreaterThanOrEqualsFloat")
infix fun BoundProperty<Long>.greaterThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("longGreaterThanOrEqualsFloatP")
infix fun BoundProperty<Long>.greaterThanOrEquals(other: Float): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PLongGreaterThanOrEqualsFloat")
infix fun Long.greaterThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("longComparedToDouble")
infix fun BoundProperty<Long>.comparedTo(other: BoundProperty<Double>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("longComparedToDoubleP")
infix fun BoundProperty<Long>.comparedTo(other: Double): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PLongComparedToDouble")
infix fun Long.comparedTo(other: BoundProperty<Double>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("longLessThanDouble")
infix fun BoundProperty<Long>.lessThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("longLessThanDoubleP")
infix fun BoundProperty<Long>.lessThan(other: Double): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PLongLessThanDouble")
infix fun Long.lessThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("longLessThanOrEqualsDouble")
infix fun BoundProperty<Long>.lessThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("longLessThanOrEqualsDoubleP")
infix fun BoundProperty<Long>.lessThanOrEquals(other: Double): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PLongLessThanOrEqualsDouble")
infix fun Long.lessThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("longGreaterThanDouble")
infix fun BoundProperty<Long>.greaterThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("longGreaterThanDoubleP")
infix fun BoundProperty<Long>.greaterThan(other: Double): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PLongGreaterThanDouble")
infix fun Long.greaterThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("longGreaterThanOrEqualsDouble")
infix fun BoundProperty<Long>.greaterThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("longGreaterThanOrEqualsDoubleP")
infix fun BoundProperty<Long>.greaterThanOrEquals(other: Double): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PLongGreaterThanOrEqualsDouble")
infix fun Long.greaterThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("longPlusByte")
operator fun BoundProperty<Long>.plus(other: BoundProperty<Byte>): BoundProperty<Long> =
    combine(other) { a, b -> a + b }

@JvmName("longPlusByteP")
operator fun BoundProperty<Long>.plus(other: Byte): BoundProperty<Long> =
    derive { a -> a + other }

@JvmName("PLongPlusByte")
operator fun Long.plus(other: BoundProperty<Byte>): BoundProperty<Long> =
    other.derive { a -> this + a }

@JvmName("longPlusShort")
operator fun BoundProperty<Long>.plus(other: BoundProperty<Short>): BoundProperty<Long> =
    combine(other) { a, b -> a + b }

@JvmName("longPlusShortP")
operator fun BoundProperty<Long>.plus(other: Short): BoundProperty<Long> =
    derive { a -> a + other }

@JvmName("PLongPlusShort")
operator fun Long.plus(other: BoundProperty<Short>): BoundProperty<Long> =
    other.derive { a -> this + a }

@JvmName("longPlusInt")
operator fun BoundProperty<Long>.plus(other: BoundProperty<Int>): BoundProperty<Long> =
    combine(other) { a, b -> a + b }

@JvmName("longPlusIntP")
operator fun BoundProperty<Long>.plus(other: Int): BoundProperty<Long> =
    derive { a -> a + other }

@JvmName("PLongPlusInt")
operator fun Long.plus(other: BoundProperty<Int>): BoundProperty<Long> =
    other.derive { a -> this + a }

@JvmName("longPlusLong")
operator fun BoundProperty<Long>.plus(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a + b }

@JvmName("longPlusLongP")
operator fun BoundProperty<Long>.plus(other: Long): BoundProperty<Long> =
    derive { a -> a + other }

@JvmName("PLongPlusLong")
operator fun Long.plus(other: BoundProperty<Long>): BoundProperty<Long> =
    other.derive { a -> this + a }

@JvmName("longPlusFloat")
operator fun BoundProperty<Long>.plus(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("longPlusFloatP")
operator fun BoundProperty<Long>.plus(other: Float): BoundProperty<Float> =
    derive { a -> a + other }

@JvmName("PLongPlusFloat")
operator fun Long.plus(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this + a }

@JvmName("longPlusDouble")
operator fun BoundProperty<Long>.plus(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("longPlusDoubleP")
operator fun BoundProperty<Long>.plus(other: Double): BoundProperty<Double> =
    derive { a -> a + other }

@JvmName("PLongPlusDouble")
operator fun Long.plus(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this + a }

@JvmName("longMinusByte")
operator fun BoundProperty<Long>.minus(other: BoundProperty<Byte>): BoundProperty<Long> =
    combine(other) { a, b -> a - b }

@JvmName("longMinusByteP")
operator fun BoundProperty<Long>.minus(other: Byte): BoundProperty<Long> =
    derive { a -> a - other }

@JvmName("PLongMinusByte")
operator fun Long.minus(other: BoundProperty<Byte>): BoundProperty<Long> =
    other.derive { a -> this - a }

@JvmName("longMinusShort")
operator fun BoundProperty<Long>.minus(other: BoundProperty<Short>): BoundProperty<Long> =
    combine(other) { a, b -> a - b }

@JvmName("longMinusShortP")
operator fun BoundProperty<Long>.minus(other: Short): BoundProperty<Long> =
    derive { a -> a - other }

@JvmName("PLongMinusShort")
operator fun Long.minus(other: BoundProperty<Short>): BoundProperty<Long> =
    other.derive { a -> this - a }

@JvmName("longMinusInt")
operator fun BoundProperty<Long>.minus(other: BoundProperty<Int>): BoundProperty<Long> =
    combine(other) { a, b -> a - b }

@JvmName("longMinusIntP")
operator fun BoundProperty<Long>.minus(other: Int): BoundProperty<Long> =
    derive { a -> a - other }

@JvmName("PLongMinusInt")
operator fun Long.minus(other: BoundProperty<Int>): BoundProperty<Long> =
    other.derive { a -> this - a }

@JvmName("longMinusLong")
operator fun BoundProperty<Long>.minus(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a - b }

@JvmName("longMinusLongP")
operator fun BoundProperty<Long>.minus(other: Long): BoundProperty<Long> =
    derive { a -> a - other }

@JvmName("PLongMinusLong")
operator fun Long.minus(other: BoundProperty<Long>): BoundProperty<Long> =
    other.derive { a -> this - a }

@JvmName("longMinusFloat")
operator fun BoundProperty<Long>.minus(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("longMinusFloatP")
operator fun BoundProperty<Long>.minus(other: Float): BoundProperty<Float> =
    derive { a -> a - other }

@JvmName("PLongMinusFloat")
operator fun Long.minus(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this - a }

@JvmName("longMinusDouble")
operator fun BoundProperty<Long>.minus(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("longMinusDoubleP")
operator fun BoundProperty<Long>.minus(other: Double): BoundProperty<Double> =
    derive { a -> a - other }

@JvmName("PLongMinusDouble")
operator fun Long.minus(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this - a }

@JvmName("longTimesByte")
operator fun BoundProperty<Long>.times(other: BoundProperty<Byte>): BoundProperty<Long> =
    combine(other) { a, b -> a * b }

@JvmName("longTimesByteP")
operator fun BoundProperty<Long>.times(other: Byte): BoundProperty<Long> =
    derive { a -> a * other }

@JvmName("PLongTimesByte")
operator fun Long.times(other: BoundProperty<Byte>): BoundProperty<Long> =
    other.derive { a -> this * a }

@JvmName("longTimesShort")
operator fun BoundProperty<Long>.times(other: BoundProperty<Short>): BoundProperty<Long> =
    combine(other) { a, b -> a * b }

@JvmName("longTimesShortP")
operator fun BoundProperty<Long>.times(other: Short): BoundProperty<Long> =
    derive { a -> a * other }

@JvmName("PLongTimesShort")
operator fun Long.times(other: BoundProperty<Short>): BoundProperty<Long> =
    other.derive { a -> this * a }

@JvmName("longTimesInt")
operator fun BoundProperty<Long>.times(other: BoundProperty<Int>): BoundProperty<Long> =
    combine(other) { a, b -> a * b }

@JvmName("longTimesIntP")
operator fun BoundProperty<Long>.times(other: Int): BoundProperty<Long> =
    derive { a -> a * other }

@JvmName("PLongTimesInt")
operator fun Long.times(other: BoundProperty<Int>): BoundProperty<Long> =
    other.derive { a -> this * a }

@JvmName("longTimesLong")
operator fun BoundProperty<Long>.times(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a * b }

@JvmName("longTimesLongP")
operator fun BoundProperty<Long>.times(other: Long): BoundProperty<Long> =
    derive { a -> a * other }

@JvmName("PLongTimesLong")
operator fun Long.times(other: BoundProperty<Long>): BoundProperty<Long> =
    other.derive { a -> this * a }

@JvmName("longTimesFloat")
operator fun BoundProperty<Long>.times(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("longTimesFloatP")
operator fun BoundProperty<Long>.times(other: Float): BoundProperty<Float> =
    derive { a -> a * other }

@JvmName("PLongTimesFloat")
operator fun Long.times(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this * a }

@JvmName("longTimesDouble")
operator fun BoundProperty<Long>.times(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("longTimesDoubleP")
operator fun BoundProperty<Long>.times(other: Double): BoundProperty<Double> =
    derive { a -> a * other }

@JvmName("PLongTimesDouble")
operator fun Long.times(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this * a }

@JvmName("longDivByte")
operator fun BoundProperty<Long>.div(other: BoundProperty<Byte>): BoundProperty<Long> =
    combine(other) { a, b -> a / b }

@JvmName("longDivByteP")
operator fun BoundProperty<Long>.div(other: Byte): BoundProperty<Long> =
    derive { a -> a / other }

@JvmName("PLongDivByte")
operator fun Long.div(other: BoundProperty<Byte>): BoundProperty<Long> =
    other.derive { a -> this / a }

@JvmName("longDivShort")
operator fun BoundProperty<Long>.div(other: BoundProperty<Short>): BoundProperty<Long> =
    combine(other) { a, b -> a / b }

@JvmName("longDivShortP")
operator fun BoundProperty<Long>.div(other: Short): BoundProperty<Long> =
    derive { a -> a / other }

@JvmName("PLongDivShort")
operator fun Long.div(other: BoundProperty<Short>): BoundProperty<Long> =
    other.derive { a -> this / a }

@JvmName("longDivInt")
operator fun BoundProperty<Long>.div(other: BoundProperty<Int>): BoundProperty<Long> =
    combine(other) { a, b -> a / b }

@JvmName("longDivIntP")
operator fun BoundProperty<Long>.div(other: Int): BoundProperty<Long> =
    derive { a -> a / other }

@JvmName("PLongDivInt")
operator fun Long.div(other: BoundProperty<Int>): BoundProperty<Long> =
    other.derive { a -> this / a }

@JvmName("longDivLong")
operator fun BoundProperty<Long>.div(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a / b }

@JvmName("longDivLongP")
operator fun BoundProperty<Long>.div(other: Long): BoundProperty<Long> =
    derive { a -> a / other }

@JvmName("PLongDivLong")
operator fun Long.div(other: BoundProperty<Long>): BoundProperty<Long> =
    other.derive { a -> this / a }

@JvmName("longDivFloat")
operator fun BoundProperty<Long>.div(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("longDivFloatP")
operator fun BoundProperty<Long>.div(other: Float): BoundProperty<Float> =
    derive { a -> a / other }

@JvmName("PLongDivFloat")
operator fun Long.div(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this / a }

@JvmName("longDivDouble")
operator fun BoundProperty<Long>.div(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("longDivDoubleP")
operator fun BoundProperty<Long>.div(other: Double): BoundProperty<Double> =
    derive { a -> a / other }

@JvmName("PLongDivDouble")
operator fun Long.div(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this / a }

@JvmName("longRemByte")
operator fun BoundProperty<Long>.rem(other: BoundProperty<Byte>): BoundProperty<Long> =
    combine(other) { a, b -> a % b }

@JvmName("longRemByteP")
operator fun BoundProperty<Long>.rem(other: Byte): BoundProperty<Long> =
    derive { a -> a % other }

@JvmName("PLongRemByte")
operator fun Long.rem(other: BoundProperty<Byte>): BoundProperty<Long> =
    other.derive { a -> this % a }

@JvmName("longRemShort")
operator fun BoundProperty<Long>.rem(other: BoundProperty<Short>): BoundProperty<Long> =
    combine(other) { a, b -> a % b }

@JvmName("longRemShortP")
operator fun BoundProperty<Long>.rem(other: Short): BoundProperty<Long> =
    derive { a -> a % other }

@JvmName("PLongRemShort")
operator fun Long.rem(other: BoundProperty<Short>): BoundProperty<Long> =
    other.derive { a -> this % a }

@JvmName("longRemInt")
operator fun BoundProperty<Long>.rem(other: BoundProperty<Int>): BoundProperty<Long> =
    combine(other) { a, b -> a % b }

@JvmName("longRemIntP")
operator fun BoundProperty<Long>.rem(other: Int): BoundProperty<Long> =
    derive { a -> a % other }

@JvmName("PLongRemInt")
operator fun Long.rem(other: BoundProperty<Int>): BoundProperty<Long> =
    other.derive { a -> this % a }

@JvmName("longRemLong")
operator fun BoundProperty<Long>.rem(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a % b }

@JvmName("longRemLongP")
operator fun BoundProperty<Long>.rem(other: Long): BoundProperty<Long> =
    derive { a -> a % other }

@JvmName("PLongRemLong")
operator fun Long.rem(other: BoundProperty<Long>): BoundProperty<Long> =
    other.derive { a -> this % a }

@JvmName("longRemFloat")
operator fun BoundProperty<Long>.rem(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("longRemFloatP")
operator fun BoundProperty<Long>.rem(other: Float): BoundProperty<Float> =
    derive { a -> a % other }

@JvmName("PLongRemFloat")
operator fun Long.rem(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this % a }

@JvmName("longRemDouble")
operator fun BoundProperty<Long>.rem(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("longRemDoubleP")
operator fun BoundProperty<Long>.rem(other: Double): BoundProperty<Double> =
    derive { a -> a % other }

@JvmName("PLongRemDouble")
operator fun Long.rem(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this % a }

@JvmName("longUnaryPlus")
operator fun BoundProperty<Long>.unaryPlus(): BoundProperty<Long> = this

@JvmName("longUnaryMinus")
operator fun BoundProperty<Long>.unaryMinus(): BoundProperty<Long> =
    derive { a -> -a.toLong() }

@JvmName("longRangeToByte")
operator fun BoundProperty<Long>.rangeTo(other: BoundProperty<Byte>): BoundProperty<LongRange> =
    combine(other) { a, b -> a..b }

@JvmName("longRangeToByteP")
operator fun BoundProperty<Long>.rangeTo(other: Byte): BoundProperty<LongRange> =
    derive { a -> a..other }

@JvmName("PLongRangeToByte")
operator fun Long.rangeTo(other: BoundProperty<Byte>): BoundProperty<LongRange> =
    other.derive { a -> this..a }

@JvmName("longRangeToShort")
operator fun BoundProperty<Long>.rangeTo(other: BoundProperty<Short>): BoundProperty<LongRange> =
    combine(other) { a, b -> a..b }

@JvmName("longRangeToShortP")
operator fun BoundProperty<Long>.rangeTo(other: Short): BoundProperty<LongRange> =
    derive { a -> a..other }

@JvmName("PLongRangeToShort")
operator fun Long.rangeTo(other: BoundProperty<Short>): BoundProperty<LongRange> =
    other.derive { a -> this..a }

@JvmName("longRangeToInt")
operator fun BoundProperty<Long>.rangeTo(other: BoundProperty<Int>): BoundProperty<LongRange> =
    combine(other) { a, b -> a..b }

@JvmName("longRangeToIntP")
operator fun BoundProperty<Long>.rangeTo(other: Int): BoundProperty<LongRange> =
    derive { a -> a..other }

@JvmName("PLongRangeToInt")
operator fun Long.rangeTo(other: BoundProperty<Int>): BoundProperty<LongRange> =
    other.derive { a -> this..a }

@JvmName("longRangeToLong")
operator fun BoundProperty<Long>.rangeTo(other: BoundProperty<Long>): BoundProperty<LongRange> =
    combine(other) { a, b -> a..b }

@JvmName("longRangeToLongP")
operator fun BoundProperty<Long>.rangeTo(other: Long): BoundProperty<LongRange> =
    derive { a -> a..other }

@JvmName("PLongRangeToLong")
operator fun Long.rangeTo(other: BoundProperty<Long>): BoundProperty<LongRange> =
    other.derive { a -> this..a }

@JvmName("longToByte")
fun BoundProperty<Long>.toByte(): BoundProperty<Byte> =
    derive { a -> a.toByte() }

@JvmName("longToChar")
fun BoundProperty<Long>.toChar(): BoundProperty<Char> =
    derive { a -> a.toChar() }

@JvmName("longToShort")
fun BoundProperty<Long>.toShort(): BoundProperty<Short> =
    derive { a -> a.toShort() }

@JvmName("longToInt")
fun BoundProperty<Long>.toInt(): BoundProperty<Int> =
    derive { a -> a.toInt() }

@JvmName("longToLong")
fun BoundProperty<Long>.toLong(): BoundProperty<Long> = this

@JvmName("longToFloat")
fun BoundProperty<Long>.toFloat(): BoundProperty<Float> =
    derive { a -> a.toFloat() }

@JvmName("longToDouble")
fun BoundProperty<Long>.toDouble(): BoundProperty<Double> =
    derive { a -> a.toDouble() }
