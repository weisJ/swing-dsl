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
infix fun ObservableProperty<Double>.comparedTo(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("doubleComparedToByteP")
infix fun ObservableProperty<Double>.comparedTo(other: Byte): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PDoubleComparedToByte")
infix fun Double.comparedTo(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("doubleLessThanByte")
infix fun ObservableProperty<Double>.lessThan(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("doubleLessThanByteP")
infix fun ObservableProperty<Double>.lessThan(other: Byte): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PDoubleLessThanByte")
infix fun Double.lessThan(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("doubleLessThanOrEqualsByte")
infix fun ObservableProperty<Double>.lessThanOrEquals(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("doubleLessThanOrEqualsByteP")
infix fun ObservableProperty<Double>.lessThanOrEquals(other: Byte): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PDoubleLessThanOrEqualsByte")
infix fun Double.lessThanOrEquals(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("doubleGreaterThanByte")
infix fun ObservableProperty<Double>.greaterThan(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("doubleGreaterThanByteP")
infix fun ObservableProperty<Double>.greaterThan(other: Byte): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PDoubleGreaterThanByte")
infix fun Double.greaterThan(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("doubleGreaterThanOrEqualsByte")
infix fun ObservableProperty<Double>.greaterThanOrEquals(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("doubleGreaterThanOrEqualsByteP")
infix fun ObservableProperty<Double>.greaterThanOrEquals(other: Byte): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PDoubleGreaterThanOrEqualsByte")
infix fun Double.greaterThanOrEquals(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("doubleComparedToShort")
infix fun ObservableProperty<Double>.comparedTo(other: ObservableProperty<Short>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("doubleComparedToShortP")
infix fun ObservableProperty<Double>.comparedTo(other: Short): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PDoubleComparedToShort")
infix fun Double.comparedTo(other: ObservableProperty<Short>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("doubleLessThanShort")
infix fun ObservableProperty<Double>.lessThan(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("doubleLessThanShortP")
infix fun ObservableProperty<Double>.lessThan(other: Short): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PDoubleLessThanShort")
infix fun Double.lessThan(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("doubleLessThanOrEqualsShort")
infix fun ObservableProperty<Double>.lessThanOrEquals(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("doubleLessThanOrEqualsShortP")
infix fun ObservableProperty<Double>.lessThanOrEquals(other: Short): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PDoubleLessThanOrEqualsShort")
infix fun Double.lessThanOrEquals(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("doubleGreaterThanShort")
infix fun ObservableProperty<Double>.greaterThan(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("doubleGreaterThanShortP")
infix fun ObservableProperty<Double>.greaterThan(other: Short): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PDoubleGreaterThanShort")
infix fun Double.greaterThan(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("doubleGreaterThanOrEqualsShort")
infix fun ObservableProperty<Double>.greaterThanOrEquals(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("doubleGreaterThanOrEqualsShortP")
infix fun ObservableProperty<Double>.greaterThanOrEquals(other: Short): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PDoubleGreaterThanOrEqualsShort")
infix fun Double.greaterThanOrEquals(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("doubleComparedToInt")
infix fun ObservableProperty<Double>.comparedTo(other: ObservableProperty<Int>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("doubleComparedToIntP")
infix fun ObservableProperty<Double>.comparedTo(other: Int): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PDoubleComparedToInt")
infix fun Double.comparedTo(other: ObservableProperty<Int>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("doubleLessThanInt")
infix fun ObservableProperty<Double>.lessThan(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("doubleLessThanIntP")
infix fun ObservableProperty<Double>.lessThan(other: Int): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PDoubleLessThanInt")
infix fun Double.lessThan(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("doubleLessThanOrEqualsInt")
infix fun ObservableProperty<Double>.lessThanOrEquals(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("doubleLessThanOrEqualsIntP")
infix fun ObservableProperty<Double>.lessThanOrEquals(other: Int): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PDoubleLessThanOrEqualsInt")
infix fun Double.lessThanOrEquals(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("doubleGreaterThanInt")
infix fun ObservableProperty<Double>.greaterThan(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("doubleGreaterThanIntP")
infix fun ObservableProperty<Double>.greaterThan(other: Int): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PDoubleGreaterThanInt")
infix fun Double.greaterThan(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("doubleGreaterThanOrEqualsInt")
infix fun ObservableProperty<Double>.greaterThanOrEquals(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("doubleGreaterThanOrEqualsIntP")
infix fun ObservableProperty<Double>.greaterThanOrEquals(other: Int): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PDoubleGreaterThanOrEqualsInt")
infix fun Double.greaterThanOrEquals(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("doubleComparedToLong")
infix fun ObservableProperty<Double>.comparedTo(other: ObservableProperty<Long>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("doubleComparedToLongP")
infix fun ObservableProperty<Double>.comparedTo(other: Long): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PDoubleComparedToLong")
infix fun Double.comparedTo(other: ObservableProperty<Long>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("doubleLessThanLong")
infix fun ObservableProperty<Double>.lessThan(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("doubleLessThanLongP")
infix fun ObservableProperty<Double>.lessThan(other: Long): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PDoubleLessThanLong")
infix fun Double.lessThan(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("doubleLessThanOrEqualsLong")
infix fun ObservableProperty<Double>.lessThanOrEquals(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("doubleLessThanOrEqualsLongP")
infix fun ObservableProperty<Double>.lessThanOrEquals(other: Long): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PDoubleLessThanOrEqualsLong")
infix fun Double.lessThanOrEquals(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("doubleGreaterThanLong")
infix fun ObservableProperty<Double>.greaterThan(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("doubleGreaterThanLongP")
infix fun ObservableProperty<Double>.greaterThan(other: Long): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PDoubleGreaterThanLong")
infix fun Double.greaterThan(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("doubleGreaterThanOrEqualsLong")
infix fun ObservableProperty<Double>.greaterThanOrEquals(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("doubleGreaterThanOrEqualsLongP")
infix fun ObservableProperty<Double>.greaterThanOrEquals(other: Long): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PDoubleGreaterThanOrEqualsLong")
infix fun Double.greaterThanOrEquals(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("doubleComparedToFloat")
infix fun ObservableProperty<Double>.comparedTo(other: ObservableProperty<Float>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("doubleComparedToFloatP")
infix fun ObservableProperty<Double>.comparedTo(other: Float): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PDoubleComparedToFloat")
infix fun Double.comparedTo(other: ObservableProperty<Float>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("doubleLessThanFloat")
infix fun ObservableProperty<Double>.lessThan(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("doubleLessThanFloatP")
infix fun ObservableProperty<Double>.lessThan(other: Float): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PDoubleLessThanFloat")
infix fun Double.lessThan(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("doubleLessThanOrEqualsFloat")
infix fun ObservableProperty<Double>.lessThanOrEquals(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("doubleLessThanOrEqualsFloatP")
infix fun ObservableProperty<Double>.lessThanOrEquals(other: Float): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PDoubleLessThanOrEqualsFloat")
infix fun Double.lessThanOrEquals(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("doubleGreaterThanFloat")
infix fun ObservableProperty<Double>.greaterThan(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("doubleGreaterThanFloatP")
infix fun ObservableProperty<Double>.greaterThan(other: Float): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PDoubleGreaterThanFloat")
infix fun Double.greaterThan(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("doubleGreaterThanOrEqualsFloat")
infix fun ObservableProperty<Double>.greaterThanOrEquals(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("doubleGreaterThanOrEqualsFloatP")
infix fun ObservableProperty<Double>.greaterThanOrEquals(other: Float): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PDoubleGreaterThanOrEqualsFloat")
infix fun Double.greaterThanOrEquals(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("doubleComparedToDouble")
infix fun ObservableProperty<Double>.comparedTo(other: ObservableProperty<Double>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("doubleComparedToDoubleP")
infix fun ObservableProperty<Double>.comparedTo(other: Double): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PDoubleComparedToDouble")
infix fun Double.comparedTo(other: ObservableProperty<Double>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("doubleLessThanDouble")
infix fun ObservableProperty<Double>.lessThan(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("doubleLessThanDoubleP")
infix fun ObservableProperty<Double>.lessThan(other: Double): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PDoubleLessThanDouble")
infix fun Double.lessThan(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("doubleLessThanOrEqualsDouble")
infix fun ObservableProperty<Double>.lessThanOrEquals(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("doubleLessThanOrEqualsDoubleP")
infix fun ObservableProperty<Double>.lessThanOrEquals(other: Double): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PDoubleLessThanOrEqualsDouble")
infix fun Double.lessThanOrEquals(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("doubleGreaterThanDouble")
infix fun ObservableProperty<Double>.greaterThan(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("doubleGreaterThanDoubleP")
infix fun ObservableProperty<Double>.greaterThan(other: Double): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PDoubleGreaterThanDouble")
infix fun Double.greaterThan(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("doubleGreaterThanOrEqualsDouble")
infix fun ObservableProperty<Double>.greaterThanOrEquals(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("doubleGreaterThanOrEqualsDoubleP")
infix fun ObservableProperty<Double>.greaterThanOrEquals(other: Double): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PDoubleGreaterThanOrEqualsDouble")
infix fun Double.greaterThanOrEquals(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("doublePlusByte")
operator fun ObservableProperty<Double>.plus(other: ObservableProperty<Byte>): ObservableProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("doublePlusByteP")
operator fun ObservableProperty<Double>.plus(other: Byte): ObservableProperty<Double> =
    derive { a -> a + other }

@JvmName("PDoublePlusByte")
operator fun Double.plus(other: ObservableProperty<Byte>): ObservableProperty<Double> =
    other.derive { a -> this + a }

@JvmName("doublePlusShort")
operator fun ObservableProperty<Double>.plus(other: ObservableProperty<Short>): ObservableProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("doublePlusShortP")
operator fun ObservableProperty<Double>.plus(other: Short): ObservableProperty<Double> =
    derive { a -> a + other }

@JvmName("PDoublePlusShort")
operator fun Double.plus(other: ObservableProperty<Short>): ObservableProperty<Double> =
    other.derive { a -> this + a }

@JvmName("doublePlusInt")
operator fun ObservableProperty<Double>.plus(other: ObservableProperty<Int>): ObservableProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("doublePlusIntP")
operator fun ObservableProperty<Double>.plus(other: Int): ObservableProperty<Double> =
    derive { a -> a + other }

@JvmName("PDoublePlusInt")
operator fun Double.plus(other: ObservableProperty<Int>): ObservableProperty<Double> =
    other.derive { a -> this + a }

@JvmName("doublePlusLong")
operator fun ObservableProperty<Double>.plus(other: ObservableProperty<Long>): ObservableProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("doublePlusLongP")
operator fun ObservableProperty<Double>.plus(other: Long): ObservableProperty<Double> =
    derive { a -> a + other }

@JvmName("PDoublePlusLong")
operator fun Double.plus(other: ObservableProperty<Long>): ObservableProperty<Double> =
    other.derive { a -> this + a }

@JvmName("doublePlusFloat")
operator fun ObservableProperty<Double>.plus(other: ObservableProperty<Float>): ObservableProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("doublePlusFloatP")
operator fun ObservableProperty<Double>.plus(other: Float): ObservableProperty<Double> =
    derive { a -> a + other }

@JvmName("PDoublePlusFloat")
operator fun Double.plus(other: ObservableProperty<Float>): ObservableProperty<Double> =
    other.derive { a -> this + a }

@JvmName("doublePlusDouble")
operator fun ObservableProperty<Double>.plus(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("doublePlusDoubleP")
operator fun ObservableProperty<Double>.plus(other: Double): ObservableProperty<Double> =
    derive { a -> a + other }

@JvmName("PDoublePlusDouble")
operator fun Double.plus(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this + a }

@JvmName("doubleMinusByte")
operator fun ObservableProperty<Double>.minus(other: ObservableProperty<Byte>): ObservableProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("doubleMinusByteP")
operator fun ObservableProperty<Double>.minus(other: Byte): ObservableProperty<Double> =
    derive { a -> a - other }

@JvmName("PDoubleMinusByte")
operator fun Double.minus(other: ObservableProperty<Byte>): ObservableProperty<Double> =
    other.derive { a -> this - a }

@JvmName("doubleMinusShort")
operator fun ObservableProperty<Double>.minus(other: ObservableProperty<Short>): ObservableProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("doubleMinusShortP")
operator fun ObservableProperty<Double>.minus(other: Short): ObservableProperty<Double> =
    derive { a -> a - other }

@JvmName("PDoubleMinusShort")
operator fun Double.minus(other: ObservableProperty<Short>): ObservableProperty<Double> =
    other.derive { a -> this - a }

@JvmName("doubleMinusInt")
operator fun ObservableProperty<Double>.minus(other: ObservableProperty<Int>): ObservableProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("doubleMinusIntP")
operator fun ObservableProperty<Double>.minus(other: Int): ObservableProperty<Double> =
    derive { a -> a - other }

@JvmName("PDoubleMinusInt")
operator fun Double.minus(other: ObservableProperty<Int>): ObservableProperty<Double> =
    other.derive { a -> this - a }

@JvmName("doubleMinusLong")
operator fun ObservableProperty<Double>.minus(other: ObservableProperty<Long>): ObservableProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("doubleMinusLongP")
operator fun ObservableProperty<Double>.minus(other: Long): ObservableProperty<Double> =
    derive { a -> a - other }

@JvmName("PDoubleMinusLong")
operator fun Double.minus(other: ObservableProperty<Long>): ObservableProperty<Double> =
    other.derive { a -> this - a }

@JvmName("doubleMinusFloat")
operator fun ObservableProperty<Double>.minus(other: ObservableProperty<Float>): ObservableProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("doubleMinusFloatP")
operator fun ObservableProperty<Double>.minus(other: Float): ObservableProperty<Double> =
    derive { a -> a - other }

@JvmName("PDoubleMinusFloat")
operator fun Double.minus(other: ObservableProperty<Float>): ObservableProperty<Double> =
    other.derive { a -> this - a }

@JvmName("doubleMinusDouble")
operator fun ObservableProperty<Double>.minus(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("doubleMinusDoubleP")
operator fun ObservableProperty<Double>.minus(other: Double): ObservableProperty<Double> =
    derive { a -> a - other }

@JvmName("PDoubleMinusDouble")
operator fun Double.minus(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this - a }

@JvmName("doubleTimesByte")
operator fun ObservableProperty<Double>.times(other: ObservableProperty<Byte>): ObservableProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("doubleTimesByteP")
operator fun ObservableProperty<Double>.times(other: Byte): ObservableProperty<Double> =
    derive { a -> a * other }

@JvmName("PDoubleTimesByte")
operator fun Double.times(other: ObservableProperty<Byte>): ObservableProperty<Double> =
    other.derive { a -> this * a }

@JvmName("doubleTimesShort")
operator fun ObservableProperty<Double>.times(other: ObservableProperty<Short>): ObservableProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("doubleTimesShortP")
operator fun ObservableProperty<Double>.times(other: Short): ObservableProperty<Double> =
    derive { a -> a * other }

@JvmName("PDoubleTimesShort")
operator fun Double.times(other: ObservableProperty<Short>): ObservableProperty<Double> =
    other.derive { a -> this * a }

@JvmName("doubleTimesInt")
operator fun ObservableProperty<Double>.times(other: ObservableProperty<Int>): ObservableProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("doubleTimesIntP")
operator fun ObservableProperty<Double>.times(other: Int): ObservableProperty<Double> =
    derive { a -> a * other }

@JvmName("PDoubleTimesInt")
operator fun Double.times(other: ObservableProperty<Int>): ObservableProperty<Double> =
    other.derive { a -> this * a }

@JvmName("doubleTimesLong")
operator fun ObservableProperty<Double>.times(other: ObservableProperty<Long>): ObservableProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("doubleTimesLongP")
operator fun ObservableProperty<Double>.times(other: Long): ObservableProperty<Double> =
    derive { a -> a * other }

@JvmName("PDoubleTimesLong")
operator fun Double.times(other: ObservableProperty<Long>): ObservableProperty<Double> =
    other.derive { a -> this * a }

@JvmName("doubleTimesFloat")
operator fun ObservableProperty<Double>.times(other: ObservableProperty<Float>): ObservableProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("doubleTimesFloatP")
operator fun ObservableProperty<Double>.times(other: Float): ObservableProperty<Double> =
    derive { a -> a * other }

@JvmName("PDoubleTimesFloat")
operator fun Double.times(other: ObservableProperty<Float>): ObservableProperty<Double> =
    other.derive { a -> this * a }

@JvmName("doubleTimesDouble")
operator fun ObservableProperty<Double>.times(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("doubleTimesDoubleP")
operator fun ObservableProperty<Double>.times(other: Double): ObservableProperty<Double> =
    derive { a -> a * other }

@JvmName("PDoubleTimesDouble")
operator fun Double.times(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this * a }

@JvmName("doubleDivByte")
operator fun ObservableProperty<Double>.div(other: ObservableProperty<Byte>): ObservableProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("doubleDivByteP")
operator fun ObservableProperty<Double>.div(other: Byte): ObservableProperty<Double> =
    derive { a -> a / other }

@JvmName("PDoubleDivByte")
operator fun Double.div(other: ObservableProperty<Byte>): ObservableProperty<Double> =
    other.derive { a -> this / a }

@JvmName("doubleDivShort")
operator fun ObservableProperty<Double>.div(other: ObservableProperty<Short>): ObservableProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("doubleDivShortP")
operator fun ObservableProperty<Double>.div(other: Short): ObservableProperty<Double> =
    derive { a -> a / other }

@JvmName("PDoubleDivShort")
operator fun Double.div(other: ObservableProperty<Short>): ObservableProperty<Double> =
    other.derive { a -> this / a }

@JvmName("doubleDivInt")
operator fun ObservableProperty<Double>.div(other: ObservableProperty<Int>): ObservableProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("doubleDivIntP")
operator fun ObservableProperty<Double>.div(other: Int): ObservableProperty<Double> =
    derive { a -> a / other }

@JvmName("PDoubleDivInt")
operator fun Double.div(other: ObservableProperty<Int>): ObservableProperty<Double> =
    other.derive { a -> this / a }

@JvmName("doubleDivLong")
operator fun ObservableProperty<Double>.div(other: ObservableProperty<Long>): ObservableProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("doubleDivLongP")
operator fun ObservableProperty<Double>.div(other: Long): ObservableProperty<Double> =
    derive { a -> a / other }

@JvmName("PDoubleDivLong")
operator fun Double.div(other: ObservableProperty<Long>): ObservableProperty<Double> =
    other.derive { a -> this / a }

@JvmName("doubleDivFloat")
operator fun ObservableProperty<Double>.div(other: ObservableProperty<Float>): ObservableProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("doubleDivFloatP")
operator fun ObservableProperty<Double>.div(other: Float): ObservableProperty<Double> =
    derive { a -> a / other }

@JvmName("PDoubleDivFloat")
operator fun Double.div(other: ObservableProperty<Float>): ObservableProperty<Double> =
    other.derive { a -> this / a }

@JvmName("doubleDivDouble")
operator fun ObservableProperty<Double>.div(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("doubleDivDoubleP")
operator fun ObservableProperty<Double>.div(other: Double): ObservableProperty<Double> =
    derive { a -> a / other }

@JvmName("PDoubleDivDouble")
operator fun Double.div(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this / a }

@JvmName("doubleRemByte")
operator fun ObservableProperty<Double>.rem(other: ObservableProperty<Byte>): ObservableProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("doubleRemByteP")
operator fun ObservableProperty<Double>.rem(other: Byte): ObservableProperty<Double> =
    derive { a -> a % other }

@JvmName("PDoubleRemByte")
operator fun Double.rem(other: ObservableProperty<Byte>): ObservableProperty<Double> =
    other.derive { a -> this % a }

@JvmName("doubleRemShort")
operator fun ObservableProperty<Double>.rem(other: ObservableProperty<Short>): ObservableProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("doubleRemShortP")
operator fun ObservableProperty<Double>.rem(other: Short): ObservableProperty<Double> =
    derive { a -> a % other }

@JvmName("PDoubleRemShort")
operator fun Double.rem(other: ObservableProperty<Short>): ObservableProperty<Double> =
    other.derive { a -> this % a }

@JvmName("doubleRemInt")
operator fun ObservableProperty<Double>.rem(other: ObservableProperty<Int>): ObservableProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("doubleRemIntP")
operator fun ObservableProperty<Double>.rem(other: Int): ObservableProperty<Double> =
    derive { a -> a % other }

@JvmName("PDoubleRemInt")
operator fun Double.rem(other: ObservableProperty<Int>): ObservableProperty<Double> =
    other.derive { a -> this % a }

@JvmName("doubleRemLong")
operator fun ObservableProperty<Double>.rem(other: ObservableProperty<Long>): ObservableProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("doubleRemLongP")
operator fun ObservableProperty<Double>.rem(other: Long): ObservableProperty<Double> =
    derive { a -> a % other }

@JvmName("PDoubleRemLong")
operator fun Double.rem(other: ObservableProperty<Long>): ObservableProperty<Double> =
    other.derive { a -> this % a }

@JvmName("doubleRemFloat")
operator fun ObservableProperty<Double>.rem(other: ObservableProperty<Float>): ObservableProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("doubleRemFloatP")
operator fun ObservableProperty<Double>.rem(other: Float): ObservableProperty<Double> =
    derive { a -> a % other }

@JvmName("PDoubleRemFloat")
operator fun Double.rem(other: ObservableProperty<Float>): ObservableProperty<Double> =
    other.derive { a -> this % a }

@JvmName("doubleRemDouble")
operator fun ObservableProperty<Double>.rem(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("doubleRemDoubleP")
operator fun ObservableProperty<Double>.rem(other: Double): ObservableProperty<Double> =
    derive { a -> a % other }

@JvmName("PDoubleRemDouble")
operator fun Double.rem(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this % a }

@JvmName("doubleUnaryPlus")
operator fun ObservableProperty<Double>.unaryPlus(): ObservableProperty<Double> = this

@JvmName("doubleUnaryMinus")
operator fun ObservableProperty<Double>.unaryMinus(): ObservableProperty<Double> =
    derive { a -> -a.toDouble() }

@JvmName("doubleToChar")
fun ObservableProperty<Double>.toChar(): ObservableProperty<Char> =
    derive { a -> a.toChar() }

@JvmName("doubleToInt")
fun ObservableProperty<Double>.toInt(): ObservableProperty<Int> =
    derive { a -> a.toInt() }

@JvmName("doubleToLong")
fun ObservableProperty<Double>.toLong(): ObservableProperty<Long> =
    derive { a -> a.toLong() }

@JvmName("doubleToFloat")
fun ObservableProperty<Double>.toFloat(): ObservableProperty<Float> =
    derive { a -> a.toFloat() }

@JvmName("doubleToDouble")
fun ObservableProperty<Double>.toDouble(): ObservableProperty<Double> = this
