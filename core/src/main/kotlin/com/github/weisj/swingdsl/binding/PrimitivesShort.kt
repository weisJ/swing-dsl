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

@JvmName("shortComparedToByte")
infix fun BoundProperty<Short>.comparedTo(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("shortComparedToByteP")
infix fun BoundProperty<Short>.comparedTo(other: Byte): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PShortComparedToByte")
infix fun Short.comparedTo(other: BoundProperty<Byte>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("shortLessThanByte")
infix fun BoundProperty<Short>.lessThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("shortLessThanByteP")
infix fun BoundProperty<Short>.lessThan(other: Byte): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PShortLessThanByte")
infix fun Short.lessThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("shortLessThanOrEqualsByte")
infix fun BoundProperty<Short>.lessThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("shortLessThanOrEqualsByteP")
infix fun BoundProperty<Short>.lessThanOrEquals(other: Byte): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PShortLessThanOrEqualsByte")
infix fun Short.lessThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("shortGreaterThanByte")
infix fun BoundProperty<Short>.greaterThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("shortGreaterThanByteP")
infix fun BoundProperty<Short>.greaterThan(other: Byte): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PShortGreaterThanByte")
infix fun Short.greaterThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("shortGreaterThanOrEqualsByte")
infix fun BoundProperty<Short>.greaterThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("shortGreaterThanOrEqualsByteP")
infix fun BoundProperty<Short>.greaterThanOrEquals(other: Byte): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PShortGreaterThanOrEqualsByte")
infix fun Short.greaterThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("shortComparedToShort")
infix fun BoundProperty<Short>.comparedTo(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("shortComparedToShortP")
infix fun BoundProperty<Short>.comparedTo(other: Short): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PShortComparedToShort")
infix fun Short.comparedTo(other: BoundProperty<Short>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("shortLessThanShort")
infix fun BoundProperty<Short>.lessThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("shortLessThanShortP")
infix fun BoundProperty<Short>.lessThan(other: Short): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PShortLessThanShort")
infix fun Short.lessThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("shortLessThanOrEqualsShort")
infix fun BoundProperty<Short>.lessThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("shortLessThanOrEqualsShortP")
infix fun BoundProperty<Short>.lessThanOrEquals(other: Short): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PShortLessThanOrEqualsShort")
infix fun Short.lessThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("shortGreaterThanShort")
infix fun BoundProperty<Short>.greaterThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("shortGreaterThanShortP")
infix fun BoundProperty<Short>.greaterThan(other: Short): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PShortGreaterThanShort")
infix fun Short.greaterThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("shortGreaterThanOrEqualsShort")
infix fun BoundProperty<Short>.greaterThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("shortGreaterThanOrEqualsShortP")
infix fun BoundProperty<Short>.greaterThanOrEquals(other: Short): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PShortGreaterThanOrEqualsShort")
infix fun Short.greaterThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("shortComparedToInt")
infix fun BoundProperty<Short>.comparedTo(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("shortComparedToIntP")
infix fun BoundProperty<Short>.comparedTo(other: Int): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PShortComparedToInt")
infix fun Short.comparedTo(other: BoundProperty<Int>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("shortLessThanInt")
infix fun BoundProperty<Short>.lessThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("shortLessThanIntP")
infix fun BoundProperty<Short>.lessThan(other: Int): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PShortLessThanInt")
infix fun Short.lessThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("shortLessThanOrEqualsInt")
infix fun BoundProperty<Short>.lessThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("shortLessThanOrEqualsIntP")
infix fun BoundProperty<Short>.lessThanOrEquals(other: Int): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PShortLessThanOrEqualsInt")
infix fun Short.lessThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("shortGreaterThanInt")
infix fun BoundProperty<Short>.greaterThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("shortGreaterThanIntP")
infix fun BoundProperty<Short>.greaterThan(other: Int): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PShortGreaterThanInt")
infix fun Short.greaterThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("shortGreaterThanOrEqualsInt")
infix fun BoundProperty<Short>.greaterThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("shortGreaterThanOrEqualsIntP")
infix fun BoundProperty<Short>.greaterThanOrEquals(other: Int): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PShortGreaterThanOrEqualsInt")
infix fun Short.greaterThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("shortComparedToLong")
infix fun BoundProperty<Short>.comparedTo(other: BoundProperty<Long>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("shortComparedToLongP")
infix fun BoundProperty<Short>.comparedTo(other: Long): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PShortComparedToLong")
infix fun Short.comparedTo(other: BoundProperty<Long>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("shortLessThanLong")
infix fun BoundProperty<Short>.lessThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("shortLessThanLongP")
infix fun BoundProperty<Short>.lessThan(other: Long): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PShortLessThanLong")
infix fun Short.lessThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("shortLessThanOrEqualsLong")
infix fun BoundProperty<Short>.lessThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("shortLessThanOrEqualsLongP")
infix fun BoundProperty<Short>.lessThanOrEquals(other: Long): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PShortLessThanOrEqualsLong")
infix fun Short.lessThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("shortGreaterThanLong")
infix fun BoundProperty<Short>.greaterThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("shortGreaterThanLongP")
infix fun BoundProperty<Short>.greaterThan(other: Long): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PShortGreaterThanLong")
infix fun Short.greaterThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("shortGreaterThanOrEqualsLong")
infix fun BoundProperty<Short>.greaterThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("shortGreaterThanOrEqualsLongP")
infix fun BoundProperty<Short>.greaterThanOrEquals(other: Long): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PShortGreaterThanOrEqualsLong")
infix fun Short.greaterThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("shortComparedToFloat")
infix fun BoundProperty<Short>.comparedTo(other: BoundProperty<Float>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("shortComparedToFloatP")
infix fun BoundProperty<Short>.comparedTo(other: Float): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PShortComparedToFloat")
infix fun Short.comparedTo(other: BoundProperty<Float>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("shortLessThanFloat")
infix fun BoundProperty<Short>.lessThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("shortLessThanFloatP")
infix fun BoundProperty<Short>.lessThan(other: Float): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PShortLessThanFloat")
infix fun Short.lessThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("shortLessThanOrEqualsFloat")
infix fun BoundProperty<Short>.lessThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("shortLessThanOrEqualsFloatP")
infix fun BoundProperty<Short>.lessThanOrEquals(other: Float): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PShortLessThanOrEqualsFloat")
infix fun Short.lessThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("shortGreaterThanFloat")
infix fun BoundProperty<Short>.greaterThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("shortGreaterThanFloatP")
infix fun BoundProperty<Short>.greaterThan(other: Float): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PShortGreaterThanFloat")
infix fun Short.greaterThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("shortGreaterThanOrEqualsFloat")
infix fun BoundProperty<Short>.greaterThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("shortGreaterThanOrEqualsFloatP")
infix fun BoundProperty<Short>.greaterThanOrEquals(other: Float): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PShortGreaterThanOrEqualsFloat")
infix fun Short.greaterThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("shortComparedToDouble")
infix fun BoundProperty<Short>.comparedTo(other: BoundProperty<Double>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("shortComparedToDoubleP")
infix fun BoundProperty<Short>.comparedTo(other: Double): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PShortComparedToDouble")
infix fun Short.comparedTo(other: BoundProperty<Double>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("shortLessThanDouble")
infix fun BoundProperty<Short>.lessThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("shortLessThanDoubleP")
infix fun BoundProperty<Short>.lessThan(other: Double): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PShortLessThanDouble")
infix fun Short.lessThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("shortLessThanOrEqualsDouble")
infix fun BoundProperty<Short>.lessThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("shortLessThanOrEqualsDoubleP")
infix fun BoundProperty<Short>.lessThanOrEquals(other: Double): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PShortLessThanOrEqualsDouble")
infix fun Short.lessThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("shortGreaterThanDouble")
infix fun BoundProperty<Short>.greaterThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("shortGreaterThanDoubleP")
infix fun BoundProperty<Short>.greaterThan(other: Double): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PShortGreaterThanDouble")
infix fun Short.greaterThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("shortGreaterThanOrEqualsDouble")
infix fun BoundProperty<Short>.greaterThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("shortGreaterThanOrEqualsDoubleP")
infix fun BoundProperty<Short>.greaterThanOrEquals(other: Double): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PShortGreaterThanOrEqualsDouble")
infix fun Short.greaterThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("shortPlusByte")
operator fun BoundProperty<Short>.plus(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("shortPlusByteP")
operator fun BoundProperty<Short>.plus(other: Byte): BoundProperty<Int> =
    derive { a -> a + other }

@JvmName("PShortPlusByte")
operator fun Short.plus(other: BoundProperty<Byte>): BoundProperty<Int> =
    other.derive { a -> this + a }

@JvmName("shortPlusShort")
operator fun BoundProperty<Short>.plus(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("shortPlusShortP")
operator fun BoundProperty<Short>.plus(other: Short): BoundProperty<Int> =
    derive { a -> a + other }

@JvmName("PShortPlusShort")
operator fun Short.plus(other: BoundProperty<Short>): BoundProperty<Int> =
    other.derive { a -> this + a }

@JvmName("shortPlusInt")
operator fun BoundProperty<Short>.plus(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("shortPlusIntP")
operator fun BoundProperty<Short>.plus(other: Int): BoundProperty<Int> =
    derive { a -> a + other }

@JvmName("PShortPlusInt")
operator fun Short.plus(other: BoundProperty<Int>): BoundProperty<Int> =
    other.derive { a -> this + a }

@JvmName("shortPlusLong")
operator fun BoundProperty<Short>.plus(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a + b }

@JvmName("shortPlusLongP")
operator fun BoundProperty<Short>.plus(other: Long): BoundProperty<Long> =
    derive { a -> a + other }

@JvmName("PShortPlusLong")
operator fun Short.plus(other: BoundProperty<Long>): BoundProperty<Long> =
    other.derive { a -> this + a }

@JvmName("shortPlusFloat")
operator fun BoundProperty<Short>.plus(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("shortPlusFloatP")
operator fun BoundProperty<Short>.plus(other: Float): BoundProperty<Float> =
    derive { a -> a + other }

@JvmName("PShortPlusFloat")
operator fun Short.plus(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this + a }

@JvmName("shortPlusDouble")
operator fun BoundProperty<Short>.plus(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("shortPlusDoubleP")
operator fun BoundProperty<Short>.plus(other: Double): BoundProperty<Double> =
    derive { a -> a + other }

@JvmName("PShortPlusDouble")
operator fun Short.plus(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this + a }

@JvmName("shortMinusByte")
operator fun BoundProperty<Short>.minus(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("shortMinusByteP")
operator fun BoundProperty<Short>.minus(other: Byte): BoundProperty<Int> =
    derive { a -> a - other }

@JvmName("PShortMinusByte")
operator fun Short.minus(other: BoundProperty<Byte>): BoundProperty<Int> =
    other.derive { a -> this - a }

@JvmName("shortMinusShort")
operator fun BoundProperty<Short>.minus(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("shortMinusShortP")
operator fun BoundProperty<Short>.minus(other: Short): BoundProperty<Int> =
    derive { a -> a - other }

@JvmName("PShortMinusShort")
operator fun Short.minus(other: BoundProperty<Short>): BoundProperty<Int> =
    other.derive { a -> this - a }

@JvmName("shortMinusInt")
operator fun BoundProperty<Short>.minus(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("shortMinusIntP")
operator fun BoundProperty<Short>.minus(other: Int): BoundProperty<Int> =
    derive { a -> a - other }

@JvmName("PShortMinusInt")
operator fun Short.minus(other: BoundProperty<Int>): BoundProperty<Int> =
    other.derive { a -> this - a }

@JvmName("shortMinusLong")
operator fun BoundProperty<Short>.minus(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a - b }

@JvmName("shortMinusLongP")
operator fun BoundProperty<Short>.minus(other: Long): BoundProperty<Long> =
    derive { a -> a - other }

@JvmName("PShortMinusLong")
operator fun Short.minus(other: BoundProperty<Long>): BoundProperty<Long> =
    other.derive { a -> this - a }

@JvmName("shortMinusFloat")
operator fun BoundProperty<Short>.minus(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("shortMinusFloatP")
operator fun BoundProperty<Short>.minus(other: Float): BoundProperty<Float> =
    derive { a -> a - other }

@JvmName("PShortMinusFloat")
operator fun Short.minus(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this - a }

@JvmName("shortMinusDouble")
operator fun BoundProperty<Short>.minus(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("shortMinusDoubleP")
operator fun BoundProperty<Short>.minus(other: Double): BoundProperty<Double> =
    derive { a -> a - other }

@JvmName("PShortMinusDouble")
operator fun Short.minus(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this - a }

@JvmName("shortTimesByte")
operator fun BoundProperty<Short>.times(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("shortTimesByteP")
operator fun BoundProperty<Short>.times(other: Byte): BoundProperty<Int> =
    derive { a -> a * other }

@JvmName("PShortTimesByte")
operator fun Short.times(other: BoundProperty<Byte>): BoundProperty<Int> =
    other.derive { a -> this * a }

@JvmName("shortTimesShort")
operator fun BoundProperty<Short>.times(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("shortTimesShortP")
operator fun BoundProperty<Short>.times(other: Short): BoundProperty<Int> =
    derive { a -> a * other }

@JvmName("PShortTimesShort")
operator fun Short.times(other: BoundProperty<Short>): BoundProperty<Int> =
    other.derive { a -> this * a }

@JvmName("shortTimesInt")
operator fun BoundProperty<Short>.times(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("shortTimesIntP")
operator fun BoundProperty<Short>.times(other: Int): BoundProperty<Int> =
    derive { a -> a * other }

@JvmName("PShortTimesInt")
operator fun Short.times(other: BoundProperty<Int>): BoundProperty<Int> =
    other.derive { a -> this * a }

@JvmName("shortTimesLong")
operator fun BoundProperty<Short>.times(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a * b }

@JvmName("shortTimesLongP")
operator fun BoundProperty<Short>.times(other: Long): BoundProperty<Long> =
    derive { a -> a * other }

@JvmName("PShortTimesLong")
operator fun Short.times(other: BoundProperty<Long>): BoundProperty<Long> =
    other.derive { a -> this * a }

@JvmName("shortTimesFloat")
operator fun BoundProperty<Short>.times(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("shortTimesFloatP")
operator fun BoundProperty<Short>.times(other: Float): BoundProperty<Float> =
    derive { a -> a * other }

@JvmName("PShortTimesFloat")
operator fun Short.times(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this * a }

@JvmName("shortTimesDouble")
operator fun BoundProperty<Short>.times(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("shortTimesDoubleP")
operator fun BoundProperty<Short>.times(other: Double): BoundProperty<Double> =
    derive { a -> a * other }

@JvmName("PShortTimesDouble")
operator fun Short.times(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this * a }

@JvmName("shortDivByte")
operator fun BoundProperty<Short>.div(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("shortDivByteP")
operator fun BoundProperty<Short>.div(other: Byte): BoundProperty<Int> =
    derive { a -> a / other }

@JvmName("PShortDivByte")
operator fun Short.div(other: BoundProperty<Byte>): BoundProperty<Int> =
    other.derive { a -> this / a }

@JvmName("shortDivShort")
operator fun BoundProperty<Short>.div(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("shortDivShortP")
operator fun BoundProperty<Short>.div(other: Short): BoundProperty<Int> =
    derive { a -> a / other }

@JvmName("PShortDivShort")
operator fun Short.div(other: BoundProperty<Short>): BoundProperty<Int> =
    other.derive { a -> this / a }

@JvmName("shortDivInt")
operator fun BoundProperty<Short>.div(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("shortDivIntP")
operator fun BoundProperty<Short>.div(other: Int): BoundProperty<Int> =
    derive { a -> a / other }

@JvmName("PShortDivInt")
operator fun Short.div(other: BoundProperty<Int>): BoundProperty<Int> =
    other.derive { a -> this / a }

@JvmName("shortDivLong")
operator fun BoundProperty<Short>.div(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a / b }

@JvmName("shortDivLongP")
operator fun BoundProperty<Short>.div(other: Long): BoundProperty<Long> =
    derive { a -> a / other }

@JvmName("PShortDivLong")
operator fun Short.div(other: BoundProperty<Long>): BoundProperty<Long> =
    other.derive { a -> this / a }

@JvmName("shortDivFloat")
operator fun BoundProperty<Short>.div(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("shortDivFloatP")
operator fun BoundProperty<Short>.div(other: Float): BoundProperty<Float> =
    derive { a -> a / other }

@JvmName("PShortDivFloat")
operator fun Short.div(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this / a }

@JvmName("shortDivDouble")
operator fun BoundProperty<Short>.div(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("shortDivDoubleP")
operator fun BoundProperty<Short>.div(other: Double): BoundProperty<Double> =
    derive { a -> a / other }

@JvmName("PShortDivDouble")
operator fun Short.div(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this / a }

@JvmName("shortRemByte")
operator fun BoundProperty<Short>.rem(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("shortRemByteP")
operator fun BoundProperty<Short>.rem(other: Byte): BoundProperty<Int> =
    derive { a -> a % other }

@JvmName("PShortRemByte")
operator fun Short.rem(other: BoundProperty<Byte>): BoundProperty<Int> =
    other.derive { a -> this % a }

@JvmName("shortRemShort")
operator fun BoundProperty<Short>.rem(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("shortRemShortP")
operator fun BoundProperty<Short>.rem(other: Short): BoundProperty<Int> =
    derive { a -> a % other }

@JvmName("PShortRemShort")
operator fun Short.rem(other: BoundProperty<Short>): BoundProperty<Int> =
    other.derive { a -> this % a }

@JvmName("shortRemInt")
operator fun BoundProperty<Short>.rem(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("shortRemIntP")
operator fun BoundProperty<Short>.rem(other: Int): BoundProperty<Int> =
    derive { a -> a % other }

@JvmName("PShortRemInt")
operator fun Short.rem(other: BoundProperty<Int>): BoundProperty<Int> =
    other.derive { a -> this % a }

@JvmName("shortRemLong")
operator fun BoundProperty<Short>.rem(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a % b }

@JvmName("shortRemLongP")
operator fun BoundProperty<Short>.rem(other: Long): BoundProperty<Long> =
    derive { a -> a % other }

@JvmName("PShortRemLong")
operator fun Short.rem(other: BoundProperty<Long>): BoundProperty<Long> =
    other.derive { a -> this % a }

@JvmName("shortRemFloat")
operator fun BoundProperty<Short>.rem(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("shortRemFloatP")
operator fun BoundProperty<Short>.rem(other: Float): BoundProperty<Float> =
    derive { a -> a % other }

@JvmName("PShortRemFloat")
operator fun Short.rem(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this % a }

@JvmName("shortRemDouble")
operator fun BoundProperty<Short>.rem(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("shortRemDoubleP")
operator fun BoundProperty<Short>.rem(other: Double): BoundProperty<Double> =
    derive { a -> a % other }

@JvmName("PShortRemDouble")
operator fun Short.rem(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this % a }

@JvmName("shortUnaryPlus")
operator fun BoundProperty<Short>.unaryPlus(): BoundProperty<Int> =
    derive { a -> a.toInt() }

@JvmName("shortUnaryMinus")
operator fun BoundProperty<Short>.unaryMinus(): BoundProperty<Int> =
    derive { a -> -a.toInt() }

@JvmName("shortRangeToByte")
operator fun BoundProperty<Short>.rangeTo(other: BoundProperty<Byte>): BoundProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("shortRangeToByteP")
operator fun BoundProperty<Short>.rangeTo(other: Byte): BoundProperty<IntRange> =
    derive { a -> a..other }

@JvmName("PShortRangeToByte")
operator fun Short.rangeTo(other: BoundProperty<Byte>): BoundProperty<IntRange> =
    other.derive { a -> this..a }

@JvmName("shortRangeToShort")
operator fun BoundProperty<Short>.rangeTo(other: BoundProperty<Short>): BoundProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("shortRangeToShortP")
operator fun BoundProperty<Short>.rangeTo(other: Short): BoundProperty<IntRange> =
    derive { a -> a..other }

@JvmName("PShortRangeToShort")
operator fun Short.rangeTo(other: BoundProperty<Short>): BoundProperty<IntRange> =
    other.derive { a -> this..a }

@JvmName("shortRangeToInt")
operator fun BoundProperty<Short>.rangeTo(other: BoundProperty<Int>): BoundProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("shortRangeToIntP")
operator fun BoundProperty<Short>.rangeTo(other: Int): BoundProperty<IntRange> =
    derive { a -> a..other }

@JvmName("PShortRangeToInt")
operator fun Short.rangeTo(other: BoundProperty<Int>): BoundProperty<IntRange> =
    other.derive { a -> this..a }

@JvmName("shortRangeToLong")
operator fun BoundProperty<Short>.rangeTo(other: BoundProperty<Long>): BoundProperty<LongRange> =
    combine(other) { a, b -> a..b }

@JvmName("shortRangeToLongP")
operator fun BoundProperty<Short>.rangeTo(other: Long): BoundProperty<LongRange> =
    derive { a -> a..other }

@JvmName("PShortRangeToLong")
operator fun Short.rangeTo(other: BoundProperty<Long>): BoundProperty<LongRange> =
    other.derive { a -> this..a }

@JvmName("shortToByte")
fun BoundProperty<Short>.toByte(): BoundProperty<Byte> =
    derive { a -> a.toByte() }

@JvmName("shortToChar")
fun BoundProperty<Short>.toChar(): BoundProperty<Char> =
    derive { a -> a.toChar() }

@JvmName("shortToShort")
fun BoundProperty<Short>.toShort(): BoundProperty<Short> = this

@JvmName("shortToInt")
fun BoundProperty<Short>.toInt(): BoundProperty<Int> =
    derive { a -> a.toInt() }

@JvmName("shortToLong")
fun BoundProperty<Short>.toLong(): BoundProperty<Long> =
    derive { a -> a.toLong() }

@JvmName("shortToFloat")
fun BoundProperty<Short>.toFloat(): BoundProperty<Float> =
    derive { a -> a.toFloat() }

@JvmName("shortToDouble")
fun BoundProperty<Short>.toDouble(): BoundProperty<Double> =
    derive { a -> a.toDouble() }
