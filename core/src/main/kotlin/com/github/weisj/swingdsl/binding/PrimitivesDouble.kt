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

@JvmName("doubleComparedToByte")
infix fun BoundProperty<Double>.comparedTo(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("doubleComparedToByteP")
infix fun BoundProperty<Double>.comparedTo(other: Byte): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PDoubleComparedToByte")
infix fun Double.comparedTo(other: BoundProperty<Byte>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("doubleLessThanByte")
infix fun BoundProperty<Double>.lessThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("doubleLessThanByteP")
infix fun BoundProperty<Double>.lessThan(other: Byte): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PDoubleLessThanByte")
infix fun Double.lessThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("doubleLessThanOrEqualsByte")
infix fun BoundProperty<Double>.lessThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("doubleLessThanOrEqualsByteP")
infix fun BoundProperty<Double>.lessThanOrEquals(other: Byte): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PDoubleLessThanOrEqualsByte")
infix fun Double.lessThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("doubleGreaterThanByte")
infix fun BoundProperty<Double>.greaterThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("doubleGreaterThanByteP")
infix fun BoundProperty<Double>.greaterThan(other: Byte): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PDoubleGreaterThanByte")
infix fun Double.greaterThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("doubleGreaterThanOrEqualsByte")
infix fun BoundProperty<Double>.greaterThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("doubleGreaterThanOrEqualsByteP")
infix fun BoundProperty<Double>.greaterThanOrEquals(other: Byte): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PDoubleGreaterThanOrEqualsByte")
infix fun Double.greaterThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("doubleComparedToShort")
infix fun BoundProperty<Double>.comparedTo(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("doubleComparedToShortP")
infix fun BoundProperty<Double>.comparedTo(other: Short): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PDoubleComparedToShort")
infix fun Double.comparedTo(other: BoundProperty<Short>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("doubleLessThanShort")
infix fun BoundProperty<Double>.lessThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("doubleLessThanShortP")
infix fun BoundProperty<Double>.lessThan(other: Short): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PDoubleLessThanShort")
infix fun Double.lessThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("doubleLessThanOrEqualsShort")
infix fun BoundProperty<Double>.lessThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("doubleLessThanOrEqualsShortP")
infix fun BoundProperty<Double>.lessThanOrEquals(other: Short): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PDoubleLessThanOrEqualsShort")
infix fun Double.lessThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("doubleGreaterThanShort")
infix fun BoundProperty<Double>.greaterThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("doubleGreaterThanShortP")
infix fun BoundProperty<Double>.greaterThan(other: Short): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PDoubleGreaterThanShort")
infix fun Double.greaterThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("doubleGreaterThanOrEqualsShort")
infix fun BoundProperty<Double>.greaterThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("doubleGreaterThanOrEqualsShortP")
infix fun BoundProperty<Double>.greaterThanOrEquals(other: Short): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PDoubleGreaterThanOrEqualsShort")
infix fun Double.greaterThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("doubleComparedToInt")
infix fun BoundProperty<Double>.comparedTo(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("doubleComparedToIntP")
infix fun BoundProperty<Double>.comparedTo(other: Int): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PDoubleComparedToInt")
infix fun Double.comparedTo(other: BoundProperty<Int>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("doubleLessThanInt")
infix fun BoundProperty<Double>.lessThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("doubleLessThanIntP")
infix fun BoundProperty<Double>.lessThan(other: Int): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PDoubleLessThanInt")
infix fun Double.lessThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("doubleLessThanOrEqualsInt")
infix fun BoundProperty<Double>.lessThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("doubleLessThanOrEqualsIntP")
infix fun BoundProperty<Double>.lessThanOrEquals(other: Int): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PDoubleLessThanOrEqualsInt")
infix fun Double.lessThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("doubleGreaterThanInt")
infix fun BoundProperty<Double>.greaterThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("doubleGreaterThanIntP")
infix fun BoundProperty<Double>.greaterThan(other: Int): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PDoubleGreaterThanInt")
infix fun Double.greaterThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("doubleGreaterThanOrEqualsInt")
infix fun BoundProperty<Double>.greaterThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("doubleGreaterThanOrEqualsIntP")
infix fun BoundProperty<Double>.greaterThanOrEquals(other: Int): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PDoubleGreaterThanOrEqualsInt")
infix fun Double.greaterThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("doubleComparedToLong")
infix fun BoundProperty<Double>.comparedTo(other: BoundProperty<Long>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("doubleComparedToLongP")
infix fun BoundProperty<Double>.comparedTo(other: Long): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PDoubleComparedToLong")
infix fun Double.comparedTo(other: BoundProperty<Long>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("doubleLessThanLong")
infix fun BoundProperty<Double>.lessThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("doubleLessThanLongP")
infix fun BoundProperty<Double>.lessThan(other: Long): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PDoubleLessThanLong")
infix fun Double.lessThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("doubleLessThanOrEqualsLong")
infix fun BoundProperty<Double>.lessThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("doubleLessThanOrEqualsLongP")
infix fun BoundProperty<Double>.lessThanOrEquals(other: Long): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PDoubleLessThanOrEqualsLong")
infix fun Double.lessThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("doubleGreaterThanLong")
infix fun BoundProperty<Double>.greaterThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("doubleGreaterThanLongP")
infix fun BoundProperty<Double>.greaterThan(other: Long): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PDoubleGreaterThanLong")
infix fun Double.greaterThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("doubleGreaterThanOrEqualsLong")
infix fun BoundProperty<Double>.greaterThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("doubleGreaterThanOrEqualsLongP")
infix fun BoundProperty<Double>.greaterThanOrEquals(other: Long): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PDoubleGreaterThanOrEqualsLong")
infix fun Double.greaterThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("doubleComparedToFloat")
infix fun BoundProperty<Double>.comparedTo(other: BoundProperty<Float>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("doubleComparedToFloatP")
infix fun BoundProperty<Double>.comparedTo(other: Float): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PDoubleComparedToFloat")
infix fun Double.comparedTo(other: BoundProperty<Float>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("doubleLessThanFloat")
infix fun BoundProperty<Double>.lessThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("doubleLessThanFloatP")
infix fun BoundProperty<Double>.lessThan(other: Float): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PDoubleLessThanFloat")
infix fun Double.lessThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("doubleLessThanOrEqualsFloat")
infix fun BoundProperty<Double>.lessThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("doubleLessThanOrEqualsFloatP")
infix fun BoundProperty<Double>.lessThanOrEquals(other: Float): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PDoubleLessThanOrEqualsFloat")
infix fun Double.lessThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("doubleGreaterThanFloat")
infix fun BoundProperty<Double>.greaterThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("doubleGreaterThanFloatP")
infix fun BoundProperty<Double>.greaterThan(other: Float): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PDoubleGreaterThanFloat")
infix fun Double.greaterThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("doubleGreaterThanOrEqualsFloat")
infix fun BoundProperty<Double>.greaterThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("doubleGreaterThanOrEqualsFloatP")
infix fun BoundProperty<Double>.greaterThanOrEquals(other: Float): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PDoubleGreaterThanOrEqualsFloat")
infix fun Double.greaterThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("doubleComparedToDouble")
infix fun BoundProperty<Double>.comparedTo(other: BoundProperty<Double>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("doubleComparedToDoubleP")
infix fun BoundProperty<Double>.comparedTo(other: Double): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PDoubleComparedToDouble")
infix fun Double.comparedTo(other: BoundProperty<Double>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("doubleLessThanDouble")
infix fun BoundProperty<Double>.lessThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("doubleLessThanDoubleP")
infix fun BoundProperty<Double>.lessThan(other: Double): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PDoubleLessThanDouble")
infix fun Double.lessThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("doubleLessThanOrEqualsDouble")
infix fun BoundProperty<Double>.lessThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("doubleLessThanOrEqualsDoubleP")
infix fun BoundProperty<Double>.lessThanOrEquals(other: Double): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PDoubleLessThanOrEqualsDouble")
infix fun Double.lessThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("doubleGreaterThanDouble")
infix fun BoundProperty<Double>.greaterThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("doubleGreaterThanDoubleP")
infix fun BoundProperty<Double>.greaterThan(other: Double): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PDoubleGreaterThanDouble")
infix fun Double.greaterThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("doubleGreaterThanOrEqualsDouble")
infix fun BoundProperty<Double>.greaterThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("doubleGreaterThanOrEqualsDoubleP")
infix fun BoundProperty<Double>.greaterThanOrEquals(other: Double): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PDoubleGreaterThanOrEqualsDouble")
infix fun Double.greaterThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("doublePlusByte")
operator fun BoundProperty<Double>.plus(other: BoundProperty<Byte>): BoundProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("doublePlusByteP")
operator fun BoundProperty<Double>.plus(other: Byte): BoundProperty<Double> =
    derive { a -> a + other }

@JvmName("PDoublePlusByte")
operator fun Double.plus(other: BoundProperty<Byte>): BoundProperty<Double> =
    other.derive { a -> this + a }

@JvmName("doublePlusShort")
operator fun BoundProperty<Double>.plus(other: BoundProperty<Short>): BoundProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("doublePlusShortP")
operator fun BoundProperty<Double>.plus(other: Short): BoundProperty<Double> =
    derive { a -> a + other }

@JvmName("PDoublePlusShort")
operator fun Double.plus(other: BoundProperty<Short>): BoundProperty<Double> =
    other.derive { a -> this + a }

@JvmName("doublePlusInt")
operator fun BoundProperty<Double>.plus(other: BoundProperty<Int>): BoundProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("doublePlusIntP")
operator fun BoundProperty<Double>.plus(other: Int): BoundProperty<Double> =
    derive { a -> a + other }

@JvmName("PDoublePlusInt")
operator fun Double.plus(other: BoundProperty<Int>): BoundProperty<Double> =
    other.derive { a -> this + a }

@JvmName("doublePlusLong")
operator fun BoundProperty<Double>.plus(other: BoundProperty<Long>): BoundProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("doublePlusLongP")
operator fun BoundProperty<Double>.plus(other: Long): BoundProperty<Double> =
    derive { a -> a + other }

@JvmName("PDoublePlusLong")
operator fun Double.plus(other: BoundProperty<Long>): BoundProperty<Double> =
    other.derive { a -> this + a }

@JvmName("doublePlusFloat")
operator fun BoundProperty<Double>.plus(other: BoundProperty<Float>): BoundProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("doublePlusFloatP")
operator fun BoundProperty<Double>.plus(other: Float): BoundProperty<Double> =
    derive { a -> a + other }

@JvmName("PDoublePlusFloat")
operator fun Double.plus(other: BoundProperty<Float>): BoundProperty<Double> =
    other.derive { a -> this + a }

@JvmName("doublePlusDouble")
operator fun BoundProperty<Double>.plus(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("doublePlusDoubleP")
operator fun BoundProperty<Double>.plus(other: Double): BoundProperty<Double> =
    derive { a -> a + other }

@JvmName("PDoublePlusDouble")
operator fun Double.plus(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this + a }

@JvmName("doubleMinusByte")
operator fun BoundProperty<Double>.minus(other: BoundProperty<Byte>): BoundProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("doubleMinusByteP")
operator fun BoundProperty<Double>.minus(other: Byte): BoundProperty<Double> =
    derive { a -> a - other }

@JvmName("PDoubleMinusByte")
operator fun Double.minus(other: BoundProperty<Byte>): BoundProperty<Double> =
    other.derive { a -> this - a }

@JvmName("doubleMinusShort")
operator fun BoundProperty<Double>.minus(other: BoundProperty<Short>): BoundProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("doubleMinusShortP")
operator fun BoundProperty<Double>.minus(other: Short): BoundProperty<Double> =
    derive { a -> a - other }

@JvmName("PDoubleMinusShort")
operator fun Double.minus(other: BoundProperty<Short>): BoundProperty<Double> =
    other.derive { a -> this - a }

@JvmName("doubleMinusInt")
operator fun BoundProperty<Double>.minus(other: BoundProperty<Int>): BoundProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("doubleMinusIntP")
operator fun BoundProperty<Double>.minus(other: Int): BoundProperty<Double> =
    derive { a -> a - other }

@JvmName("PDoubleMinusInt")
operator fun Double.minus(other: BoundProperty<Int>): BoundProperty<Double> =
    other.derive { a -> this - a }

@JvmName("doubleMinusLong")
operator fun BoundProperty<Double>.minus(other: BoundProperty<Long>): BoundProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("doubleMinusLongP")
operator fun BoundProperty<Double>.minus(other: Long): BoundProperty<Double> =
    derive { a -> a - other }

@JvmName("PDoubleMinusLong")
operator fun Double.minus(other: BoundProperty<Long>): BoundProperty<Double> =
    other.derive { a -> this - a }

@JvmName("doubleMinusFloat")
operator fun BoundProperty<Double>.minus(other: BoundProperty<Float>): BoundProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("doubleMinusFloatP")
operator fun BoundProperty<Double>.minus(other: Float): BoundProperty<Double> =
    derive { a -> a - other }

@JvmName("PDoubleMinusFloat")
operator fun Double.minus(other: BoundProperty<Float>): BoundProperty<Double> =
    other.derive { a -> this - a }

@JvmName("doubleMinusDouble")
operator fun BoundProperty<Double>.minus(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("doubleMinusDoubleP")
operator fun BoundProperty<Double>.minus(other: Double): BoundProperty<Double> =
    derive { a -> a - other }

@JvmName("PDoubleMinusDouble")
operator fun Double.minus(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this - a }

@JvmName("doubleTimesByte")
operator fun BoundProperty<Double>.times(other: BoundProperty<Byte>): BoundProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("doubleTimesByteP")
operator fun BoundProperty<Double>.times(other: Byte): BoundProperty<Double> =
    derive { a -> a * other }

@JvmName("PDoubleTimesByte")
operator fun Double.times(other: BoundProperty<Byte>): BoundProperty<Double> =
    other.derive { a -> this * a }

@JvmName("doubleTimesShort")
operator fun BoundProperty<Double>.times(other: BoundProperty<Short>): BoundProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("doubleTimesShortP")
operator fun BoundProperty<Double>.times(other: Short): BoundProperty<Double> =
    derive { a -> a * other }

@JvmName("PDoubleTimesShort")
operator fun Double.times(other: BoundProperty<Short>): BoundProperty<Double> =
    other.derive { a -> this * a }

@JvmName("doubleTimesInt")
operator fun BoundProperty<Double>.times(other: BoundProperty<Int>): BoundProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("doubleTimesIntP")
operator fun BoundProperty<Double>.times(other: Int): BoundProperty<Double> =
    derive { a -> a * other }

@JvmName("PDoubleTimesInt")
operator fun Double.times(other: BoundProperty<Int>): BoundProperty<Double> =
    other.derive { a -> this * a }

@JvmName("doubleTimesLong")
operator fun BoundProperty<Double>.times(other: BoundProperty<Long>): BoundProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("doubleTimesLongP")
operator fun BoundProperty<Double>.times(other: Long): BoundProperty<Double> =
    derive { a -> a * other }

@JvmName("PDoubleTimesLong")
operator fun Double.times(other: BoundProperty<Long>): BoundProperty<Double> =
    other.derive { a -> this * a }

@JvmName("doubleTimesFloat")
operator fun BoundProperty<Double>.times(other: BoundProperty<Float>): BoundProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("doubleTimesFloatP")
operator fun BoundProperty<Double>.times(other: Float): BoundProperty<Double> =
    derive { a -> a * other }

@JvmName("PDoubleTimesFloat")
operator fun Double.times(other: BoundProperty<Float>): BoundProperty<Double> =
    other.derive { a -> this * a }

@JvmName("doubleTimesDouble")
operator fun BoundProperty<Double>.times(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("doubleTimesDoubleP")
operator fun BoundProperty<Double>.times(other: Double): BoundProperty<Double> =
    derive { a -> a * other }

@JvmName("PDoubleTimesDouble")
operator fun Double.times(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this * a }

@JvmName("doubleDivByte")
operator fun BoundProperty<Double>.div(other: BoundProperty<Byte>): BoundProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("doubleDivByteP")
operator fun BoundProperty<Double>.div(other: Byte): BoundProperty<Double> =
    derive { a -> a / other }

@JvmName("PDoubleDivByte")
operator fun Double.div(other: BoundProperty<Byte>): BoundProperty<Double> =
    other.derive { a -> this / a }

@JvmName("doubleDivShort")
operator fun BoundProperty<Double>.div(other: BoundProperty<Short>): BoundProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("doubleDivShortP")
operator fun BoundProperty<Double>.div(other: Short): BoundProperty<Double> =
    derive { a -> a / other }

@JvmName("PDoubleDivShort")
operator fun Double.div(other: BoundProperty<Short>): BoundProperty<Double> =
    other.derive { a -> this / a }

@JvmName("doubleDivInt")
operator fun BoundProperty<Double>.div(other: BoundProperty<Int>): BoundProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("doubleDivIntP")
operator fun BoundProperty<Double>.div(other: Int): BoundProperty<Double> =
    derive { a -> a / other }

@JvmName("PDoubleDivInt")
operator fun Double.div(other: BoundProperty<Int>): BoundProperty<Double> =
    other.derive { a -> this / a }

@JvmName("doubleDivLong")
operator fun BoundProperty<Double>.div(other: BoundProperty<Long>): BoundProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("doubleDivLongP")
operator fun BoundProperty<Double>.div(other: Long): BoundProperty<Double> =
    derive { a -> a / other }

@JvmName("PDoubleDivLong")
operator fun Double.div(other: BoundProperty<Long>): BoundProperty<Double> =
    other.derive { a -> this / a }

@JvmName("doubleDivFloat")
operator fun BoundProperty<Double>.div(other: BoundProperty<Float>): BoundProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("doubleDivFloatP")
operator fun BoundProperty<Double>.div(other: Float): BoundProperty<Double> =
    derive { a -> a / other }

@JvmName("PDoubleDivFloat")
operator fun Double.div(other: BoundProperty<Float>): BoundProperty<Double> =
    other.derive { a -> this / a }

@JvmName("doubleDivDouble")
operator fun BoundProperty<Double>.div(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("doubleDivDoubleP")
operator fun BoundProperty<Double>.div(other: Double): BoundProperty<Double> =
    derive { a -> a / other }

@JvmName("PDoubleDivDouble")
operator fun Double.div(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this / a }

@JvmName("doubleRemByte")
operator fun BoundProperty<Double>.rem(other: BoundProperty<Byte>): BoundProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("doubleRemByteP")
operator fun BoundProperty<Double>.rem(other: Byte): BoundProperty<Double> =
    derive { a -> a % other }

@JvmName("PDoubleRemByte")
operator fun Double.rem(other: BoundProperty<Byte>): BoundProperty<Double> =
    other.derive { a -> this % a }

@JvmName("doubleRemShort")
operator fun BoundProperty<Double>.rem(other: BoundProperty<Short>): BoundProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("doubleRemShortP")
operator fun BoundProperty<Double>.rem(other: Short): BoundProperty<Double> =
    derive { a -> a % other }

@JvmName("PDoubleRemShort")
operator fun Double.rem(other: BoundProperty<Short>): BoundProperty<Double> =
    other.derive { a -> this % a }

@JvmName("doubleRemInt")
operator fun BoundProperty<Double>.rem(other: BoundProperty<Int>): BoundProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("doubleRemIntP")
operator fun BoundProperty<Double>.rem(other: Int): BoundProperty<Double> =
    derive { a -> a % other }

@JvmName("PDoubleRemInt")
operator fun Double.rem(other: BoundProperty<Int>): BoundProperty<Double> =
    other.derive { a -> this % a }

@JvmName("doubleRemLong")
operator fun BoundProperty<Double>.rem(other: BoundProperty<Long>): BoundProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("doubleRemLongP")
operator fun BoundProperty<Double>.rem(other: Long): BoundProperty<Double> =
    derive { a -> a % other }

@JvmName("PDoubleRemLong")
operator fun Double.rem(other: BoundProperty<Long>): BoundProperty<Double> =
    other.derive { a -> this % a }

@JvmName("doubleRemFloat")
operator fun BoundProperty<Double>.rem(other: BoundProperty<Float>): BoundProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("doubleRemFloatP")
operator fun BoundProperty<Double>.rem(other: Float): BoundProperty<Double> =
    derive { a -> a % other }

@JvmName("PDoubleRemFloat")
operator fun Double.rem(other: BoundProperty<Float>): BoundProperty<Double> =
    other.derive { a -> this % a }

@JvmName("doubleRemDouble")
operator fun BoundProperty<Double>.rem(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("doubleRemDoubleP")
operator fun BoundProperty<Double>.rem(other: Double): BoundProperty<Double> =
    derive { a -> a % other }

@JvmName("PDoubleRemDouble")
operator fun Double.rem(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this % a }

@JvmName("doubleUnaryPlus")
operator fun BoundProperty<Double>.unaryPlus(): BoundProperty<Double> = this

@JvmName("doubleUnaryMinus")
operator fun BoundProperty<Double>.unaryMinus(): BoundProperty<Double> =
    derive { a -> -a.toDouble() }

@JvmName("doubleToChar")
fun BoundProperty<Double>.toChar(): BoundProperty<Char> =
    derive { a -> a.toChar() }

@JvmName("doubleToInt")
fun BoundProperty<Double>.toInt(): BoundProperty<Int> =
    derive { a -> a.toInt() }

@JvmName("doubleToLong")
fun BoundProperty<Double>.toLong(): BoundProperty<Long> =
    derive { a -> a.toLong() }

@JvmName("doubleToFloat")
fun BoundProperty<Double>.toFloat(): BoundProperty<Float> =
    derive { a -> a.toFloat() }

@JvmName("doubleToDouble")
fun BoundProperty<Double>.toDouble(): BoundProperty<Double> = this
