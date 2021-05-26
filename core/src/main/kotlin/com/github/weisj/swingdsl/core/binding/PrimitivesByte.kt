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

@JvmName("byteComparedToByte")
infix fun ObservableProperty<Byte>.comparedTo(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("byteComparedToByteP")
infix fun ObservableProperty<Byte>.comparedTo(other: Byte): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PByteComparedToByte")
infix fun Byte.comparedTo(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("byteLessThanByte")
infix fun ObservableProperty<Byte>.lessThan(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("byteLessThanByteP")
infix fun ObservableProperty<Byte>.lessThan(other: Byte): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PByteLessThanByte")
infix fun Byte.lessThan(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("byteLessThanOrEqualsByte")
infix fun ObservableProperty<Byte>.lessThanOrEquals(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("byteLessThanOrEqualsByteP")
infix fun ObservableProperty<Byte>.lessThanOrEquals(other: Byte): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PByteLessThanOrEqualsByte")
infix fun Byte.lessThanOrEquals(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("byteGreaterThanByte")
infix fun ObservableProperty<Byte>.greaterThan(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("byteGreaterThanByteP")
infix fun ObservableProperty<Byte>.greaterThan(other: Byte): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PByteGreaterThanByte")
infix fun Byte.greaterThan(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("byteGreaterThanOrEqualsByte")
infix fun ObservableProperty<Byte>.greaterThanOrEquals(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("byteGreaterThanOrEqualsByteP")
infix fun ObservableProperty<Byte>.greaterThanOrEquals(other: Byte): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PByteGreaterThanOrEqualsByte")
infix fun Byte.greaterThanOrEquals(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("byteComparedToShort")
infix fun ObservableProperty<Byte>.comparedTo(other: ObservableProperty<Short>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("byteComparedToShortP")
infix fun ObservableProperty<Byte>.comparedTo(other: Short): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PByteComparedToShort")
infix fun Byte.comparedTo(other: ObservableProperty<Short>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("byteLessThanShort")
infix fun ObservableProperty<Byte>.lessThan(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("byteLessThanShortP")
infix fun ObservableProperty<Byte>.lessThan(other: Short): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PByteLessThanShort")
infix fun Byte.lessThan(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("byteLessThanOrEqualsShort")
infix fun ObservableProperty<Byte>.lessThanOrEquals(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("byteLessThanOrEqualsShortP")
infix fun ObservableProperty<Byte>.lessThanOrEquals(other: Short): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PByteLessThanOrEqualsShort")
infix fun Byte.lessThanOrEquals(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("byteGreaterThanShort")
infix fun ObservableProperty<Byte>.greaterThan(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("byteGreaterThanShortP")
infix fun ObservableProperty<Byte>.greaterThan(other: Short): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PByteGreaterThanShort")
infix fun Byte.greaterThan(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("byteGreaterThanOrEqualsShort")
infix fun ObservableProperty<Byte>.greaterThanOrEquals(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("byteGreaterThanOrEqualsShortP")
infix fun ObservableProperty<Byte>.greaterThanOrEquals(other: Short): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PByteGreaterThanOrEqualsShort")
infix fun Byte.greaterThanOrEquals(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("byteComparedToInt")
infix fun ObservableProperty<Byte>.comparedTo(other: ObservableProperty<Int>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("byteComparedToIntP")
infix fun ObservableProperty<Byte>.comparedTo(other: Int): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PByteComparedToInt")
infix fun Byte.comparedTo(other: ObservableProperty<Int>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("byteLessThanInt")
infix fun ObservableProperty<Byte>.lessThan(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("byteLessThanIntP")
infix fun ObservableProperty<Byte>.lessThan(other: Int): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PByteLessThanInt")
infix fun Byte.lessThan(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("byteLessThanOrEqualsInt")
infix fun ObservableProperty<Byte>.lessThanOrEquals(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("byteLessThanOrEqualsIntP")
infix fun ObservableProperty<Byte>.lessThanOrEquals(other: Int): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PByteLessThanOrEqualsInt")
infix fun Byte.lessThanOrEquals(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("byteGreaterThanInt")
infix fun ObservableProperty<Byte>.greaterThan(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("byteGreaterThanIntP")
infix fun ObservableProperty<Byte>.greaterThan(other: Int): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PByteGreaterThanInt")
infix fun Byte.greaterThan(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("byteGreaterThanOrEqualsInt")
infix fun ObservableProperty<Byte>.greaterThanOrEquals(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("byteGreaterThanOrEqualsIntP")
infix fun ObservableProperty<Byte>.greaterThanOrEquals(other: Int): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PByteGreaterThanOrEqualsInt")
infix fun Byte.greaterThanOrEquals(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("byteComparedToLong")
infix fun ObservableProperty<Byte>.comparedTo(other: ObservableProperty<Long>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("byteComparedToLongP")
infix fun ObservableProperty<Byte>.comparedTo(other: Long): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PByteComparedToLong")
infix fun Byte.comparedTo(other: ObservableProperty<Long>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("byteLessThanLong")
infix fun ObservableProperty<Byte>.lessThan(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("byteLessThanLongP")
infix fun ObservableProperty<Byte>.lessThan(other: Long): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PByteLessThanLong")
infix fun Byte.lessThan(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("byteLessThanOrEqualsLong")
infix fun ObservableProperty<Byte>.lessThanOrEquals(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("byteLessThanOrEqualsLongP")
infix fun ObservableProperty<Byte>.lessThanOrEquals(other: Long): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PByteLessThanOrEqualsLong")
infix fun Byte.lessThanOrEquals(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("byteGreaterThanLong")
infix fun ObservableProperty<Byte>.greaterThan(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("byteGreaterThanLongP")
infix fun ObservableProperty<Byte>.greaterThan(other: Long): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PByteGreaterThanLong")
infix fun Byte.greaterThan(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("byteGreaterThanOrEqualsLong")
infix fun ObservableProperty<Byte>.greaterThanOrEquals(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("byteGreaterThanOrEqualsLongP")
infix fun ObservableProperty<Byte>.greaterThanOrEquals(other: Long): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PByteGreaterThanOrEqualsLong")
infix fun Byte.greaterThanOrEquals(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("byteComparedToFloat")
infix fun ObservableProperty<Byte>.comparedTo(other: ObservableProperty<Float>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("byteComparedToFloatP")
infix fun ObservableProperty<Byte>.comparedTo(other: Float): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PByteComparedToFloat")
infix fun Byte.comparedTo(other: ObservableProperty<Float>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("byteLessThanFloat")
infix fun ObservableProperty<Byte>.lessThan(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("byteLessThanFloatP")
infix fun ObservableProperty<Byte>.lessThan(other: Float): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PByteLessThanFloat")
infix fun Byte.lessThan(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("byteLessThanOrEqualsFloat")
infix fun ObservableProperty<Byte>.lessThanOrEquals(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("byteLessThanOrEqualsFloatP")
infix fun ObservableProperty<Byte>.lessThanOrEquals(other: Float): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PByteLessThanOrEqualsFloat")
infix fun Byte.lessThanOrEquals(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("byteGreaterThanFloat")
infix fun ObservableProperty<Byte>.greaterThan(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("byteGreaterThanFloatP")
infix fun ObservableProperty<Byte>.greaterThan(other: Float): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PByteGreaterThanFloat")
infix fun Byte.greaterThan(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("byteGreaterThanOrEqualsFloat")
infix fun ObservableProperty<Byte>.greaterThanOrEquals(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("byteGreaterThanOrEqualsFloatP")
infix fun ObservableProperty<Byte>.greaterThanOrEquals(other: Float): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PByteGreaterThanOrEqualsFloat")
infix fun Byte.greaterThanOrEquals(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("byteComparedToDouble")
infix fun ObservableProperty<Byte>.comparedTo(other: ObservableProperty<Double>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("byteComparedToDoubleP")
infix fun ObservableProperty<Byte>.comparedTo(other: Double): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PByteComparedToDouble")
infix fun Byte.comparedTo(other: ObservableProperty<Double>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("byteLessThanDouble")
infix fun ObservableProperty<Byte>.lessThan(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("byteLessThanDoubleP")
infix fun ObservableProperty<Byte>.lessThan(other: Double): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PByteLessThanDouble")
infix fun Byte.lessThan(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("byteLessThanOrEqualsDouble")
infix fun ObservableProperty<Byte>.lessThanOrEquals(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("byteLessThanOrEqualsDoubleP")
infix fun ObservableProperty<Byte>.lessThanOrEquals(other: Double): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PByteLessThanOrEqualsDouble")
infix fun Byte.lessThanOrEquals(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("byteGreaterThanDouble")
infix fun ObservableProperty<Byte>.greaterThan(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("byteGreaterThanDoubleP")
infix fun ObservableProperty<Byte>.greaterThan(other: Double): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PByteGreaterThanDouble")
infix fun Byte.greaterThan(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("byteGreaterThanOrEqualsDouble")
infix fun ObservableProperty<Byte>.greaterThanOrEquals(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("byteGreaterThanOrEqualsDoubleP")
infix fun ObservableProperty<Byte>.greaterThanOrEquals(other: Double): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PByteGreaterThanOrEqualsDouble")
infix fun Byte.greaterThanOrEquals(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("bytePlusByte")
operator fun ObservableProperty<Byte>.plus(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("bytePlusByteP")
operator fun ObservableProperty<Byte>.plus(other: Byte): ObservableProperty<Int> =
    derive { a -> a + other }

@JvmName("PBytePlusByte")
operator fun Byte.plus(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    other.derive { a -> this + a }

@JvmName("bytePlusShort")
operator fun ObservableProperty<Byte>.plus(other: ObservableProperty<Short>): ObservableProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("bytePlusShortP")
operator fun ObservableProperty<Byte>.plus(other: Short): ObservableProperty<Int> =
    derive { a -> a + other }

@JvmName("PBytePlusShort")
operator fun Byte.plus(other: ObservableProperty<Short>): ObservableProperty<Int> =
    other.derive { a -> this + a }

@JvmName("bytePlusInt")
operator fun ObservableProperty<Byte>.plus(other: ObservableProperty<Int>): ObservableProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("bytePlusIntP")
operator fun ObservableProperty<Byte>.plus(other: Int): ObservableProperty<Int> =
    derive { a -> a + other }

@JvmName("PBytePlusInt")
operator fun Byte.plus(other: ObservableProperty<Int>): ObservableProperty<Int> =
    other.derive { a -> this + a }

@JvmName("bytePlusLong")
operator fun ObservableProperty<Byte>.plus(other: ObservableProperty<Long>): ObservableProperty<Long> =
    combine(other) { a, b -> a + b }

@JvmName("bytePlusLongP")
operator fun ObservableProperty<Byte>.plus(other: Long): ObservableProperty<Long> =
    derive { a -> a + other }

@JvmName("PBytePlusLong")
operator fun Byte.plus(other: ObservableProperty<Long>): ObservableProperty<Long> =
    other.derive { a -> this + a }

@JvmName("bytePlusFloat")
operator fun ObservableProperty<Byte>.plus(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("bytePlusFloatP")
operator fun ObservableProperty<Byte>.plus(other: Float): ObservableProperty<Float> =
    derive { a -> a + other }

@JvmName("PBytePlusFloat")
operator fun Byte.plus(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this + a }

@JvmName("bytePlusDouble")
operator fun ObservableProperty<Byte>.plus(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("bytePlusDoubleP")
operator fun ObservableProperty<Byte>.plus(other: Double): ObservableProperty<Double> =
    derive { a -> a + other }

@JvmName("PBytePlusDouble")
operator fun Byte.plus(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this + a }

@JvmName("byteMinusByte")
operator fun ObservableProperty<Byte>.minus(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("byteMinusByteP")
operator fun ObservableProperty<Byte>.minus(other: Byte): ObservableProperty<Int> =
    derive { a -> a - other }

@JvmName("PByteMinusByte")
operator fun Byte.minus(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    other.derive { a -> this - a }

@JvmName("byteMinusShort")
operator fun ObservableProperty<Byte>.minus(other: ObservableProperty<Short>): ObservableProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("byteMinusShortP")
operator fun ObservableProperty<Byte>.minus(other: Short): ObservableProperty<Int> =
    derive { a -> a - other }

@JvmName("PByteMinusShort")
operator fun Byte.minus(other: ObservableProperty<Short>): ObservableProperty<Int> =
    other.derive { a -> this - a }

@JvmName("byteMinusInt")
operator fun ObservableProperty<Byte>.minus(other: ObservableProperty<Int>): ObservableProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("byteMinusIntP")
operator fun ObservableProperty<Byte>.minus(other: Int): ObservableProperty<Int> =
    derive { a -> a - other }

@JvmName("PByteMinusInt")
operator fun Byte.minus(other: ObservableProperty<Int>): ObservableProperty<Int> =
    other.derive { a -> this - a }

@JvmName("byteMinusLong")
operator fun ObservableProperty<Byte>.minus(other: ObservableProperty<Long>): ObservableProperty<Long> =
    combine(other) { a, b -> a - b }

@JvmName("byteMinusLongP")
operator fun ObservableProperty<Byte>.minus(other: Long): ObservableProperty<Long> =
    derive { a -> a - other }

@JvmName("PByteMinusLong")
operator fun Byte.minus(other: ObservableProperty<Long>): ObservableProperty<Long> =
    other.derive { a -> this - a }

@JvmName("byteMinusFloat")
operator fun ObservableProperty<Byte>.minus(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("byteMinusFloatP")
operator fun ObservableProperty<Byte>.minus(other: Float): ObservableProperty<Float> =
    derive { a -> a - other }

@JvmName("PByteMinusFloat")
operator fun Byte.minus(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this - a }

@JvmName("byteMinusDouble")
operator fun ObservableProperty<Byte>.minus(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("byteMinusDoubleP")
operator fun ObservableProperty<Byte>.minus(other: Double): ObservableProperty<Double> =
    derive { a -> a - other }

@JvmName("PByteMinusDouble")
operator fun Byte.minus(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this - a }

@JvmName("byteTimesByte")
operator fun ObservableProperty<Byte>.times(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("byteTimesByteP")
operator fun ObservableProperty<Byte>.times(other: Byte): ObservableProperty<Int> =
    derive { a -> a * other }

@JvmName("PByteTimesByte")
operator fun Byte.times(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    other.derive { a -> this * a }

@JvmName("byteTimesShort")
operator fun ObservableProperty<Byte>.times(other: ObservableProperty<Short>): ObservableProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("byteTimesShortP")
operator fun ObservableProperty<Byte>.times(other: Short): ObservableProperty<Int> =
    derive { a -> a * other }

@JvmName("PByteTimesShort")
operator fun Byte.times(other: ObservableProperty<Short>): ObservableProperty<Int> =
    other.derive { a -> this * a }

@JvmName("byteTimesInt")
operator fun ObservableProperty<Byte>.times(other: ObservableProperty<Int>): ObservableProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("byteTimesIntP")
operator fun ObservableProperty<Byte>.times(other: Int): ObservableProperty<Int> =
    derive { a -> a * other }

@JvmName("PByteTimesInt")
operator fun Byte.times(other: ObservableProperty<Int>): ObservableProperty<Int> =
    other.derive { a -> this * a }

@JvmName("byteTimesLong")
operator fun ObservableProperty<Byte>.times(other: ObservableProperty<Long>): ObservableProperty<Long> =
    combine(other) { a, b -> a * b }

@JvmName("byteTimesLongP")
operator fun ObservableProperty<Byte>.times(other: Long): ObservableProperty<Long> =
    derive { a -> a * other }

@JvmName("PByteTimesLong")
operator fun Byte.times(other: ObservableProperty<Long>): ObservableProperty<Long> =
    other.derive { a -> this * a }

@JvmName("byteTimesFloat")
operator fun ObservableProperty<Byte>.times(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("byteTimesFloatP")
operator fun ObservableProperty<Byte>.times(other: Float): ObservableProperty<Float> =
    derive { a -> a * other }

@JvmName("PByteTimesFloat")
operator fun Byte.times(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this * a }

@JvmName("byteTimesDouble")
operator fun ObservableProperty<Byte>.times(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("byteTimesDoubleP")
operator fun ObservableProperty<Byte>.times(other: Double): ObservableProperty<Double> =
    derive { a -> a * other }

@JvmName("PByteTimesDouble")
operator fun Byte.times(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this * a }

@JvmName("byteDivByte")
operator fun ObservableProperty<Byte>.div(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("byteDivByteP")
operator fun ObservableProperty<Byte>.div(other: Byte): ObservableProperty<Int> =
    derive { a -> a / other }

@JvmName("PByteDivByte")
operator fun Byte.div(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    other.derive { a -> this / a }

@JvmName("byteDivShort")
operator fun ObservableProperty<Byte>.div(other: ObservableProperty<Short>): ObservableProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("byteDivShortP")
operator fun ObservableProperty<Byte>.div(other: Short): ObservableProperty<Int> =
    derive { a -> a / other }

@JvmName("PByteDivShort")
operator fun Byte.div(other: ObservableProperty<Short>): ObservableProperty<Int> =
    other.derive { a -> this / a }

@JvmName("byteDivInt")
operator fun ObservableProperty<Byte>.div(other: ObservableProperty<Int>): ObservableProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("byteDivIntP")
operator fun ObservableProperty<Byte>.div(other: Int): ObservableProperty<Int> =
    derive { a -> a / other }

@JvmName("PByteDivInt")
operator fun Byte.div(other: ObservableProperty<Int>): ObservableProperty<Int> =
    other.derive { a -> this / a }

@JvmName("byteDivLong")
operator fun ObservableProperty<Byte>.div(other: ObservableProperty<Long>): ObservableProperty<Long> =
    combine(other) { a, b -> a / b }

@JvmName("byteDivLongP")
operator fun ObservableProperty<Byte>.div(other: Long): ObservableProperty<Long> =
    derive { a -> a / other }

@JvmName("PByteDivLong")
operator fun Byte.div(other: ObservableProperty<Long>): ObservableProperty<Long> =
    other.derive { a -> this / a }

@JvmName("byteDivFloat")
operator fun ObservableProperty<Byte>.div(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("byteDivFloatP")
operator fun ObservableProperty<Byte>.div(other: Float): ObservableProperty<Float> =
    derive { a -> a / other }

@JvmName("PByteDivFloat")
operator fun Byte.div(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this / a }

@JvmName("byteDivDouble")
operator fun ObservableProperty<Byte>.div(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("byteDivDoubleP")
operator fun ObservableProperty<Byte>.div(other: Double): ObservableProperty<Double> =
    derive { a -> a / other }

@JvmName("PByteDivDouble")
operator fun Byte.div(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this / a }

@JvmName("byteRemByte")
operator fun ObservableProperty<Byte>.rem(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("byteRemByteP")
operator fun ObservableProperty<Byte>.rem(other: Byte): ObservableProperty<Int> =
    derive { a -> a % other }

@JvmName("PByteRemByte")
operator fun Byte.rem(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    other.derive { a -> this % a }

@JvmName("byteRemShort")
operator fun ObservableProperty<Byte>.rem(other: ObservableProperty<Short>): ObservableProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("byteRemShortP")
operator fun ObservableProperty<Byte>.rem(other: Short): ObservableProperty<Int> =
    derive { a -> a % other }

@JvmName("PByteRemShort")
operator fun Byte.rem(other: ObservableProperty<Short>): ObservableProperty<Int> =
    other.derive { a -> this % a }

@JvmName("byteRemInt")
operator fun ObservableProperty<Byte>.rem(other: ObservableProperty<Int>): ObservableProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("byteRemIntP")
operator fun ObservableProperty<Byte>.rem(other: Int): ObservableProperty<Int> =
    derive { a -> a % other }

@JvmName("PByteRemInt")
operator fun Byte.rem(other: ObservableProperty<Int>): ObservableProperty<Int> =
    other.derive { a -> this % a }

@JvmName("byteRemLong")
operator fun ObservableProperty<Byte>.rem(other: ObservableProperty<Long>): ObservableProperty<Long> =
    combine(other) { a, b -> a % b }

@JvmName("byteRemLongP")
operator fun ObservableProperty<Byte>.rem(other: Long): ObservableProperty<Long> =
    derive { a -> a % other }

@JvmName("PByteRemLong")
operator fun Byte.rem(other: ObservableProperty<Long>): ObservableProperty<Long> =
    other.derive { a -> this % a }

@JvmName("byteRemFloat")
operator fun ObservableProperty<Byte>.rem(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("byteRemFloatP")
operator fun ObservableProperty<Byte>.rem(other: Float): ObservableProperty<Float> =
    derive { a -> a % other }

@JvmName("PByteRemFloat")
operator fun Byte.rem(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this % a }

@JvmName("byteRemDouble")
operator fun ObservableProperty<Byte>.rem(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("byteRemDoubleP")
operator fun ObservableProperty<Byte>.rem(other: Double): ObservableProperty<Double> =
    derive { a -> a % other }

@JvmName("PByteRemDouble")
operator fun Byte.rem(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this % a }

@JvmName("byteUnaryPlus")
operator fun ObservableProperty<Byte>.unaryPlus(): ObservableProperty<Int> =
    derive { a -> a.toInt() }

@JvmName("byteUnaryMinus")
operator fun ObservableProperty<Byte>.unaryMinus(): ObservableProperty<Int> =
    derive { a -> -a.toInt() }

@JvmName("byteRangeToByte")
operator fun ObservableProperty<Byte>.rangeTo(other: ObservableProperty<Byte>): ObservableProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("byteRangeToByteP")
operator fun ObservableProperty<Byte>.rangeTo(other: Byte): ObservableProperty<IntRange> =
    derive { a -> a..other }

@JvmName("PByteRangeToByte")
operator fun Byte.rangeTo(other: ObservableProperty<Byte>): ObservableProperty<IntRange> =
    other.derive { a -> this..a }

@JvmName("byteRangeToShort")
operator fun ObservableProperty<Byte>.rangeTo(other: ObservableProperty<Short>): ObservableProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("byteRangeToShortP")
operator fun ObservableProperty<Byte>.rangeTo(other: Short): ObservableProperty<IntRange> =
    derive { a -> a..other }

@JvmName("PByteRangeToShort")
operator fun Byte.rangeTo(other: ObservableProperty<Short>): ObservableProperty<IntRange> =
    other.derive { a -> this..a }

@JvmName("byteRangeToInt")
operator fun ObservableProperty<Byte>.rangeTo(other: ObservableProperty<Int>): ObservableProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("byteRangeToIntP")
operator fun ObservableProperty<Byte>.rangeTo(other: Int): ObservableProperty<IntRange> =
    derive { a -> a..other }

@JvmName("PByteRangeToInt")
operator fun Byte.rangeTo(other: ObservableProperty<Int>): ObservableProperty<IntRange> =
    other.derive { a -> this..a }

@JvmName("byteRangeToLong")
operator fun ObservableProperty<Byte>.rangeTo(other: ObservableProperty<Long>): ObservableProperty<LongRange> =
    combine(other) { a, b -> a..b }

@JvmName("byteRangeToLongP")
operator fun ObservableProperty<Byte>.rangeTo(other: Long): ObservableProperty<LongRange> =
    derive { a -> a..other }

@JvmName("PByteRangeToLong")
operator fun Byte.rangeTo(other: ObservableProperty<Long>): ObservableProperty<LongRange> =
    other.derive { a -> this..a }

@JvmName("byteToByte")
fun ObservableProperty<Byte>.toByte(): ObservableProperty<Byte> = this

@JvmName("byteToShort")
fun ObservableProperty<Byte>.toShort(): ObservableProperty<Short> =
    derive { a -> a.toShort() }

@JvmName("byteToInt")
fun ObservableProperty<Byte>.toInt(): ObservableProperty<Int> =
    derive { a -> a.toInt() }

@JvmName("byteToLong")
fun ObservableProperty<Byte>.toLong(): ObservableProperty<Long> =
    derive { a -> a.toLong() }

@JvmName("byteToFloat")
fun ObservableProperty<Byte>.toFloat(): ObservableProperty<Float> =
    derive { a -> a.toFloat() }

@JvmName("byteToDouble")
fun ObservableProperty<Byte>.toDouble(): ObservableProperty<Double> =
    derive { a -> a.toDouble() }
