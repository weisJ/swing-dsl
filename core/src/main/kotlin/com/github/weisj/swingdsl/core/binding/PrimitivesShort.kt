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

@JvmName("shortComparedToByte")
infix fun ObservableProperty<Short>.comparedTo(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("shortComparedToByteP")
infix fun ObservableProperty<Short>.comparedTo(other: Byte): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PShortComparedToByte")
infix fun Short.comparedTo(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("shortLessThanByte")
infix fun ObservableProperty<Short>.lessThan(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("shortLessThanByteP")
infix fun ObservableProperty<Short>.lessThan(other: Byte): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PShortLessThanByte")
infix fun Short.lessThan(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("shortLessThanOrEqualsByte")
infix fun ObservableProperty<Short>.lessThanOrEquals(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("shortLessThanOrEqualsByteP")
infix fun ObservableProperty<Short>.lessThanOrEquals(other: Byte): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PShortLessThanOrEqualsByte")
infix fun Short.lessThanOrEquals(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("shortGreaterThanByte")
infix fun ObservableProperty<Short>.greaterThan(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("shortGreaterThanByteP")
infix fun ObservableProperty<Short>.greaterThan(other: Byte): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PShortGreaterThanByte")
infix fun Short.greaterThan(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("shortGreaterThanOrEqualsByte")
infix fun ObservableProperty<Short>.greaterThanOrEquals(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("shortGreaterThanOrEqualsByteP")
infix fun ObservableProperty<Short>.greaterThanOrEquals(other: Byte): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PShortGreaterThanOrEqualsByte")
infix fun Short.greaterThanOrEquals(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("shortComparedToShort")
infix fun ObservableProperty<Short>.comparedTo(other: ObservableProperty<Short>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("shortComparedToShortP")
infix fun ObservableProperty<Short>.comparedTo(other: Short): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PShortComparedToShort")
infix fun Short.comparedTo(other: ObservableProperty<Short>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("shortLessThanShort")
infix fun ObservableProperty<Short>.lessThan(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("shortLessThanShortP")
infix fun ObservableProperty<Short>.lessThan(other: Short): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PShortLessThanShort")
infix fun Short.lessThan(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("shortLessThanOrEqualsShort")
infix fun ObservableProperty<Short>.lessThanOrEquals(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("shortLessThanOrEqualsShortP")
infix fun ObservableProperty<Short>.lessThanOrEquals(other: Short): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PShortLessThanOrEqualsShort")
infix fun Short.lessThanOrEquals(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("shortGreaterThanShort")
infix fun ObservableProperty<Short>.greaterThan(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("shortGreaterThanShortP")
infix fun ObservableProperty<Short>.greaterThan(other: Short): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PShortGreaterThanShort")
infix fun Short.greaterThan(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("shortGreaterThanOrEqualsShort")
infix fun ObservableProperty<Short>.greaterThanOrEquals(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("shortGreaterThanOrEqualsShortP")
infix fun ObservableProperty<Short>.greaterThanOrEquals(other: Short): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PShortGreaterThanOrEqualsShort")
infix fun Short.greaterThanOrEquals(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("shortComparedToInt")
infix fun ObservableProperty<Short>.comparedTo(other: ObservableProperty<Int>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("shortComparedToIntP")
infix fun ObservableProperty<Short>.comparedTo(other: Int): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PShortComparedToInt")
infix fun Short.comparedTo(other: ObservableProperty<Int>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("shortLessThanInt")
infix fun ObservableProperty<Short>.lessThan(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("shortLessThanIntP")
infix fun ObservableProperty<Short>.lessThan(other: Int): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PShortLessThanInt")
infix fun Short.lessThan(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("shortLessThanOrEqualsInt")
infix fun ObservableProperty<Short>.lessThanOrEquals(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("shortLessThanOrEqualsIntP")
infix fun ObservableProperty<Short>.lessThanOrEquals(other: Int): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PShortLessThanOrEqualsInt")
infix fun Short.lessThanOrEquals(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("shortGreaterThanInt")
infix fun ObservableProperty<Short>.greaterThan(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("shortGreaterThanIntP")
infix fun ObservableProperty<Short>.greaterThan(other: Int): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PShortGreaterThanInt")
infix fun Short.greaterThan(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("shortGreaterThanOrEqualsInt")
infix fun ObservableProperty<Short>.greaterThanOrEquals(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("shortGreaterThanOrEqualsIntP")
infix fun ObservableProperty<Short>.greaterThanOrEquals(other: Int): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PShortGreaterThanOrEqualsInt")
infix fun Short.greaterThanOrEquals(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("shortComparedToLong")
infix fun ObservableProperty<Short>.comparedTo(other: ObservableProperty<Long>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("shortComparedToLongP")
infix fun ObservableProperty<Short>.comparedTo(other: Long): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PShortComparedToLong")
infix fun Short.comparedTo(other: ObservableProperty<Long>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("shortLessThanLong")
infix fun ObservableProperty<Short>.lessThan(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("shortLessThanLongP")
infix fun ObservableProperty<Short>.lessThan(other: Long): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PShortLessThanLong")
infix fun Short.lessThan(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("shortLessThanOrEqualsLong")
infix fun ObservableProperty<Short>.lessThanOrEquals(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("shortLessThanOrEqualsLongP")
infix fun ObservableProperty<Short>.lessThanOrEquals(other: Long): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PShortLessThanOrEqualsLong")
infix fun Short.lessThanOrEquals(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("shortGreaterThanLong")
infix fun ObservableProperty<Short>.greaterThan(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("shortGreaterThanLongP")
infix fun ObservableProperty<Short>.greaterThan(other: Long): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PShortGreaterThanLong")
infix fun Short.greaterThan(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("shortGreaterThanOrEqualsLong")
infix fun ObservableProperty<Short>.greaterThanOrEquals(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("shortGreaterThanOrEqualsLongP")
infix fun ObservableProperty<Short>.greaterThanOrEquals(other: Long): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PShortGreaterThanOrEqualsLong")
infix fun Short.greaterThanOrEquals(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("shortComparedToFloat")
infix fun ObservableProperty<Short>.comparedTo(other: ObservableProperty<Float>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("shortComparedToFloatP")
infix fun ObservableProperty<Short>.comparedTo(other: Float): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PShortComparedToFloat")
infix fun Short.comparedTo(other: ObservableProperty<Float>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("shortLessThanFloat")
infix fun ObservableProperty<Short>.lessThan(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("shortLessThanFloatP")
infix fun ObservableProperty<Short>.lessThan(other: Float): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PShortLessThanFloat")
infix fun Short.lessThan(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("shortLessThanOrEqualsFloat")
infix fun ObservableProperty<Short>.lessThanOrEquals(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("shortLessThanOrEqualsFloatP")
infix fun ObservableProperty<Short>.lessThanOrEquals(other: Float): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PShortLessThanOrEqualsFloat")
infix fun Short.lessThanOrEquals(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("shortGreaterThanFloat")
infix fun ObservableProperty<Short>.greaterThan(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("shortGreaterThanFloatP")
infix fun ObservableProperty<Short>.greaterThan(other: Float): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PShortGreaterThanFloat")
infix fun Short.greaterThan(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("shortGreaterThanOrEqualsFloat")
infix fun ObservableProperty<Short>.greaterThanOrEquals(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("shortGreaterThanOrEqualsFloatP")
infix fun ObservableProperty<Short>.greaterThanOrEquals(other: Float): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PShortGreaterThanOrEqualsFloat")
infix fun Short.greaterThanOrEquals(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("shortComparedToDouble")
infix fun ObservableProperty<Short>.comparedTo(other: ObservableProperty<Double>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("shortComparedToDoubleP")
infix fun ObservableProperty<Short>.comparedTo(other: Double): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PShortComparedToDouble")
infix fun Short.comparedTo(other: ObservableProperty<Double>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("shortLessThanDouble")
infix fun ObservableProperty<Short>.lessThan(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("shortLessThanDoubleP")
infix fun ObservableProperty<Short>.lessThan(other: Double): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PShortLessThanDouble")
infix fun Short.lessThan(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("shortLessThanOrEqualsDouble")
infix fun ObservableProperty<Short>.lessThanOrEquals(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("shortLessThanOrEqualsDoubleP")
infix fun ObservableProperty<Short>.lessThanOrEquals(other: Double): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PShortLessThanOrEqualsDouble")
infix fun Short.lessThanOrEquals(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("shortGreaterThanDouble")
infix fun ObservableProperty<Short>.greaterThan(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("shortGreaterThanDoubleP")
infix fun ObservableProperty<Short>.greaterThan(other: Double): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PShortGreaterThanDouble")
infix fun Short.greaterThan(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("shortGreaterThanOrEqualsDouble")
infix fun ObservableProperty<Short>.greaterThanOrEquals(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("shortGreaterThanOrEqualsDoubleP")
infix fun ObservableProperty<Short>.greaterThanOrEquals(other: Double): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PShortGreaterThanOrEqualsDouble")
infix fun Short.greaterThanOrEquals(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("shortPlusByte")
operator fun ObservableProperty<Short>.plus(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("shortPlusByteP")
operator fun ObservableProperty<Short>.plus(other: Byte): ObservableProperty<Int> =
    derive { a -> a + other }

@JvmName("PShortPlusByte")
operator fun Short.plus(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    other.derive { a -> this + a }

@JvmName("shortPlusShort")
operator fun ObservableProperty<Short>.plus(other: ObservableProperty<Short>): ObservableProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("shortPlusShortP")
operator fun ObservableProperty<Short>.plus(other: Short): ObservableProperty<Int> =
    derive { a -> a + other }

@JvmName("PShortPlusShort")
operator fun Short.plus(other: ObservableProperty<Short>): ObservableProperty<Int> =
    other.derive { a -> this + a }

@JvmName("shortPlusInt")
operator fun ObservableProperty<Short>.plus(other: ObservableProperty<Int>): ObservableProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("shortPlusIntP")
operator fun ObservableProperty<Short>.plus(other: Int): ObservableProperty<Int> =
    derive { a -> a + other }

@JvmName("PShortPlusInt")
operator fun Short.plus(other: ObservableProperty<Int>): ObservableProperty<Int> =
    other.derive { a -> this + a }

@JvmName("shortPlusLong")
operator fun ObservableProperty<Short>.plus(other: ObservableProperty<Long>): ObservableProperty<Long> =
    combine(other) { a, b -> a + b }

@JvmName("shortPlusLongP")
operator fun ObservableProperty<Short>.plus(other: Long): ObservableProperty<Long> =
    derive { a -> a + other }

@JvmName("PShortPlusLong")
operator fun Short.plus(other: ObservableProperty<Long>): ObservableProperty<Long> =
    other.derive { a -> this + a }

@JvmName("shortPlusFloat")
operator fun ObservableProperty<Short>.plus(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("shortPlusFloatP")
operator fun ObservableProperty<Short>.plus(other: Float): ObservableProperty<Float> =
    derive { a -> a + other }

@JvmName("PShortPlusFloat")
operator fun Short.plus(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this + a }

@JvmName("shortPlusDouble")
operator fun ObservableProperty<Short>.plus(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("shortPlusDoubleP")
operator fun ObservableProperty<Short>.plus(other: Double): ObservableProperty<Double> =
    derive { a -> a + other }

@JvmName("PShortPlusDouble")
operator fun Short.plus(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this + a }

@JvmName("shortMinusByte")
operator fun ObservableProperty<Short>.minus(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("shortMinusByteP")
operator fun ObservableProperty<Short>.minus(other: Byte): ObservableProperty<Int> =
    derive { a -> a - other }

@JvmName("PShortMinusByte")
operator fun Short.minus(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    other.derive { a -> this - a }

@JvmName("shortMinusShort")
operator fun ObservableProperty<Short>.minus(other: ObservableProperty<Short>): ObservableProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("shortMinusShortP")
operator fun ObservableProperty<Short>.minus(other: Short): ObservableProperty<Int> =
    derive { a -> a - other }

@JvmName("PShortMinusShort")
operator fun Short.minus(other: ObservableProperty<Short>): ObservableProperty<Int> =
    other.derive { a -> this - a }

@JvmName("shortMinusInt")
operator fun ObservableProperty<Short>.minus(other: ObservableProperty<Int>): ObservableProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("shortMinusIntP")
operator fun ObservableProperty<Short>.minus(other: Int): ObservableProperty<Int> =
    derive { a -> a - other }

@JvmName("PShortMinusInt")
operator fun Short.minus(other: ObservableProperty<Int>): ObservableProperty<Int> =
    other.derive { a -> this - a }

@JvmName("shortMinusLong")
operator fun ObservableProperty<Short>.minus(other: ObservableProperty<Long>): ObservableProperty<Long> =
    combine(other) { a, b -> a - b }

@JvmName("shortMinusLongP")
operator fun ObservableProperty<Short>.minus(other: Long): ObservableProperty<Long> =
    derive { a -> a - other }

@JvmName("PShortMinusLong")
operator fun Short.minus(other: ObservableProperty<Long>): ObservableProperty<Long> =
    other.derive { a -> this - a }

@JvmName("shortMinusFloat")
operator fun ObservableProperty<Short>.minus(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("shortMinusFloatP")
operator fun ObservableProperty<Short>.minus(other: Float): ObservableProperty<Float> =
    derive { a -> a - other }

@JvmName("PShortMinusFloat")
operator fun Short.minus(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this - a }

@JvmName("shortMinusDouble")
operator fun ObservableProperty<Short>.minus(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("shortMinusDoubleP")
operator fun ObservableProperty<Short>.minus(other: Double): ObservableProperty<Double> =
    derive { a -> a - other }

@JvmName("PShortMinusDouble")
operator fun Short.minus(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this - a }

@JvmName("shortTimesByte")
operator fun ObservableProperty<Short>.times(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("shortTimesByteP")
operator fun ObservableProperty<Short>.times(other: Byte): ObservableProperty<Int> =
    derive { a -> a * other }

@JvmName("PShortTimesByte")
operator fun Short.times(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    other.derive { a -> this * a }

@JvmName("shortTimesShort")
operator fun ObservableProperty<Short>.times(other: ObservableProperty<Short>): ObservableProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("shortTimesShortP")
operator fun ObservableProperty<Short>.times(other: Short): ObservableProperty<Int> =
    derive { a -> a * other }

@JvmName("PShortTimesShort")
operator fun Short.times(other: ObservableProperty<Short>): ObservableProperty<Int> =
    other.derive { a -> this * a }

@JvmName("shortTimesInt")
operator fun ObservableProperty<Short>.times(other: ObservableProperty<Int>): ObservableProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("shortTimesIntP")
operator fun ObservableProperty<Short>.times(other: Int): ObservableProperty<Int> =
    derive { a -> a * other }

@JvmName("PShortTimesInt")
operator fun Short.times(other: ObservableProperty<Int>): ObservableProperty<Int> =
    other.derive { a -> this * a }

@JvmName("shortTimesLong")
operator fun ObservableProperty<Short>.times(other: ObservableProperty<Long>): ObservableProperty<Long> =
    combine(other) { a, b -> a * b }

@JvmName("shortTimesLongP")
operator fun ObservableProperty<Short>.times(other: Long): ObservableProperty<Long> =
    derive { a -> a * other }

@JvmName("PShortTimesLong")
operator fun Short.times(other: ObservableProperty<Long>): ObservableProperty<Long> =
    other.derive { a -> this * a }

@JvmName("shortTimesFloat")
operator fun ObservableProperty<Short>.times(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("shortTimesFloatP")
operator fun ObservableProperty<Short>.times(other: Float): ObservableProperty<Float> =
    derive { a -> a * other }

@JvmName("PShortTimesFloat")
operator fun Short.times(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this * a }

@JvmName("shortTimesDouble")
operator fun ObservableProperty<Short>.times(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("shortTimesDoubleP")
operator fun ObservableProperty<Short>.times(other: Double): ObservableProperty<Double> =
    derive { a -> a * other }

@JvmName("PShortTimesDouble")
operator fun Short.times(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this * a }

@JvmName("shortDivByte")
operator fun ObservableProperty<Short>.div(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("shortDivByteP")
operator fun ObservableProperty<Short>.div(other: Byte): ObservableProperty<Int> =
    derive { a -> a / other }

@JvmName("PShortDivByte")
operator fun Short.div(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    other.derive { a -> this / a }

@JvmName("shortDivShort")
operator fun ObservableProperty<Short>.div(other: ObservableProperty<Short>): ObservableProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("shortDivShortP")
operator fun ObservableProperty<Short>.div(other: Short): ObservableProperty<Int> =
    derive { a -> a / other }

@JvmName("PShortDivShort")
operator fun Short.div(other: ObservableProperty<Short>): ObservableProperty<Int> =
    other.derive { a -> this / a }

@JvmName("shortDivInt")
operator fun ObservableProperty<Short>.div(other: ObservableProperty<Int>): ObservableProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("shortDivIntP")
operator fun ObservableProperty<Short>.div(other: Int): ObservableProperty<Int> =
    derive { a -> a / other }

@JvmName("PShortDivInt")
operator fun Short.div(other: ObservableProperty<Int>): ObservableProperty<Int> =
    other.derive { a -> this / a }

@JvmName("shortDivLong")
operator fun ObservableProperty<Short>.div(other: ObservableProperty<Long>): ObservableProperty<Long> =
    combine(other) { a, b -> a / b }

@JvmName("shortDivLongP")
operator fun ObservableProperty<Short>.div(other: Long): ObservableProperty<Long> =
    derive { a -> a / other }

@JvmName("PShortDivLong")
operator fun Short.div(other: ObservableProperty<Long>): ObservableProperty<Long> =
    other.derive { a -> this / a }

@JvmName("shortDivFloat")
operator fun ObservableProperty<Short>.div(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("shortDivFloatP")
operator fun ObservableProperty<Short>.div(other: Float): ObservableProperty<Float> =
    derive { a -> a / other }

@JvmName("PShortDivFloat")
operator fun Short.div(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this / a }

@JvmName("shortDivDouble")
operator fun ObservableProperty<Short>.div(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("shortDivDoubleP")
operator fun ObservableProperty<Short>.div(other: Double): ObservableProperty<Double> =
    derive { a -> a / other }

@JvmName("PShortDivDouble")
operator fun Short.div(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this / a }

@JvmName("shortRemByte")
operator fun ObservableProperty<Short>.rem(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("shortRemByteP")
operator fun ObservableProperty<Short>.rem(other: Byte): ObservableProperty<Int> =
    derive { a -> a % other }

@JvmName("PShortRemByte")
operator fun Short.rem(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    other.derive { a -> this % a }

@JvmName("shortRemShort")
operator fun ObservableProperty<Short>.rem(other: ObservableProperty<Short>): ObservableProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("shortRemShortP")
operator fun ObservableProperty<Short>.rem(other: Short): ObservableProperty<Int> =
    derive { a -> a % other }

@JvmName("PShortRemShort")
operator fun Short.rem(other: ObservableProperty<Short>): ObservableProperty<Int> =
    other.derive { a -> this % a }

@JvmName("shortRemInt")
operator fun ObservableProperty<Short>.rem(other: ObservableProperty<Int>): ObservableProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("shortRemIntP")
operator fun ObservableProperty<Short>.rem(other: Int): ObservableProperty<Int> =
    derive { a -> a % other }

@JvmName("PShortRemInt")
operator fun Short.rem(other: ObservableProperty<Int>): ObservableProperty<Int> =
    other.derive { a -> this % a }

@JvmName("shortRemLong")
operator fun ObservableProperty<Short>.rem(other: ObservableProperty<Long>): ObservableProperty<Long> =
    combine(other) { a, b -> a % b }

@JvmName("shortRemLongP")
operator fun ObservableProperty<Short>.rem(other: Long): ObservableProperty<Long> =
    derive { a -> a % other }

@JvmName("PShortRemLong")
operator fun Short.rem(other: ObservableProperty<Long>): ObservableProperty<Long> =
    other.derive { a -> this % a }

@JvmName("shortRemFloat")
operator fun ObservableProperty<Short>.rem(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("shortRemFloatP")
operator fun ObservableProperty<Short>.rem(other: Float): ObservableProperty<Float> =
    derive { a -> a % other }

@JvmName("PShortRemFloat")
operator fun Short.rem(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this % a }

@JvmName("shortRemDouble")
operator fun ObservableProperty<Short>.rem(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("shortRemDoubleP")
operator fun ObservableProperty<Short>.rem(other: Double): ObservableProperty<Double> =
    derive { a -> a % other }

@JvmName("PShortRemDouble")
operator fun Short.rem(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this % a }

@JvmName("shortUnaryPlus")
operator fun ObservableProperty<Short>.unaryPlus(): ObservableProperty<Int> =
    derive { a -> a.toInt() }

@JvmName("shortUnaryMinus")
operator fun ObservableProperty<Short>.unaryMinus(): ObservableProperty<Int> =
    derive { a -> -a.toInt() }

@JvmName("shortRangeToByte")
operator fun ObservableProperty<Short>.rangeTo(other: ObservableProperty<Byte>): ObservableProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("shortRangeToByteP")
operator fun ObservableProperty<Short>.rangeTo(other: Byte): ObservableProperty<IntRange> =
    derive { a -> a..other }

@JvmName("PShortRangeToByte")
operator fun Short.rangeTo(other: ObservableProperty<Byte>): ObservableProperty<IntRange> =
    other.derive { a -> this..a }

@JvmName("shortRangeToShort")
operator fun ObservableProperty<Short>.rangeTo(other: ObservableProperty<Short>): ObservableProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("shortRangeToShortP")
operator fun ObservableProperty<Short>.rangeTo(other: Short): ObservableProperty<IntRange> =
    derive { a -> a..other }

@JvmName("PShortRangeToShort")
operator fun Short.rangeTo(other: ObservableProperty<Short>): ObservableProperty<IntRange> =
    other.derive { a -> this..a }

@JvmName("shortRangeToInt")
operator fun ObservableProperty<Short>.rangeTo(other: ObservableProperty<Int>): ObservableProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("shortRangeToIntP")
operator fun ObservableProperty<Short>.rangeTo(other: Int): ObservableProperty<IntRange> =
    derive { a -> a..other }

@JvmName("PShortRangeToInt")
operator fun Short.rangeTo(other: ObservableProperty<Int>): ObservableProperty<IntRange> =
    other.derive { a -> this..a }

@JvmName("shortRangeToLong")
operator fun ObservableProperty<Short>.rangeTo(other: ObservableProperty<Long>): ObservableProperty<LongRange> =
    combine(other) { a, b -> a..b }

@JvmName("shortRangeToLongP")
operator fun ObservableProperty<Short>.rangeTo(other: Long): ObservableProperty<LongRange> =
    derive { a -> a..other }

@JvmName("PShortRangeToLong")
operator fun Short.rangeTo(other: ObservableProperty<Long>): ObservableProperty<LongRange> =
    other.derive { a -> this..a }

@JvmName("shortToByte")
fun ObservableProperty<Short>.toByte(): ObservableProperty<Byte> =
    derive { a -> a.toByte() }

@JvmName("shortToShort")
fun ObservableProperty<Short>.toShort(): ObservableProperty<Short> = this

@JvmName("shortToInt")
fun ObservableProperty<Short>.toInt(): ObservableProperty<Int> =
    derive { a -> a.toInt() }

@JvmName("shortToLong")
fun ObservableProperty<Short>.toLong(): ObservableProperty<Long> =
    derive { a -> a.toLong() }

@JvmName("shortToFloat")
fun ObservableProperty<Short>.toFloat(): ObservableProperty<Float> =
    derive { a -> a.toFloat() }

@JvmName("shortToDouble")
fun ObservableProperty<Short>.toDouble(): ObservableProperty<Double> =
    derive { a -> a.toDouble() }
