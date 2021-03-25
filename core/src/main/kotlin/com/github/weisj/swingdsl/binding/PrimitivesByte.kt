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

@JvmName("byteComparedToByte")
infix fun BoundProperty<Byte>.comparedTo(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("byteComparedToByteP")
infix fun BoundProperty<Byte>.comparedTo(other: Byte): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PByteComparedToByte")
infix fun Byte.comparedTo(other: BoundProperty<Byte>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("byteLessThanByte")
infix fun BoundProperty<Byte>.lessThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("byteLessThanByteP")
infix fun BoundProperty<Byte>.lessThan(other: Byte): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PByteLessThanByte")
infix fun Byte.lessThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("byteLessThanOrEqualsByte")
infix fun BoundProperty<Byte>.lessThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("byteLessThanOrEqualsByteP")
infix fun BoundProperty<Byte>.lessThanOrEquals(other: Byte): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PByteLessThanOrEqualsByte")
infix fun Byte.lessThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("byteGreaterThanByte")
infix fun BoundProperty<Byte>.greaterThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("byteGreaterThanByteP")
infix fun BoundProperty<Byte>.greaterThan(other: Byte): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PByteGreaterThanByte")
infix fun Byte.greaterThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("byteGreaterThanOrEqualsByte")
infix fun BoundProperty<Byte>.greaterThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("byteGreaterThanOrEqualsByteP")
infix fun BoundProperty<Byte>.greaterThanOrEquals(other: Byte): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PByteGreaterThanOrEqualsByte")
infix fun Byte.greaterThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("byteComparedToShort")
infix fun BoundProperty<Byte>.comparedTo(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("byteComparedToShortP")
infix fun BoundProperty<Byte>.comparedTo(other: Short): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PByteComparedToShort")
infix fun Byte.comparedTo(other: BoundProperty<Short>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("byteLessThanShort")
infix fun BoundProperty<Byte>.lessThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("byteLessThanShortP")
infix fun BoundProperty<Byte>.lessThan(other: Short): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PByteLessThanShort")
infix fun Byte.lessThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("byteLessThanOrEqualsShort")
infix fun BoundProperty<Byte>.lessThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("byteLessThanOrEqualsShortP")
infix fun BoundProperty<Byte>.lessThanOrEquals(other: Short): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PByteLessThanOrEqualsShort")
infix fun Byte.lessThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("byteGreaterThanShort")
infix fun BoundProperty<Byte>.greaterThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("byteGreaterThanShortP")
infix fun BoundProperty<Byte>.greaterThan(other: Short): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PByteGreaterThanShort")
infix fun Byte.greaterThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("byteGreaterThanOrEqualsShort")
infix fun BoundProperty<Byte>.greaterThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("byteGreaterThanOrEqualsShortP")
infix fun BoundProperty<Byte>.greaterThanOrEquals(other: Short): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PByteGreaterThanOrEqualsShort")
infix fun Byte.greaterThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("byteComparedToInt")
infix fun BoundProperty<Byte>.comparedTo(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("byteComparedToIntP")
infix fun BoundProperty<Byte>.comparedTo(other: Int): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PByteComparedToInt")
infix fun Byte.comparedTo(other: BoundProperty<Int>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("byteLessThanInt")
infix fun BoundProperty<Byte>.lessThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("byteLessThanIntP")
infix fun BoundProperty<Byte>.lessThan(other: Int): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PByteLessThanInt")
infix fun Byte.lessThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("byteLessThanOrEqualsInt")
infix fun BoundProperty<Byte>.lessThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("byteLessThanOrEqualsIntP")
infix fun BoundProperty<Byte>.lessThanOrEquals(other: Int): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PByteLessThanOrEqualsInt")
infix fun Byte.lessThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("byteGreaterThanInt")
infix fun BoundProperty<Byte>.greaterThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("byteGreaterThanIntP")
infix fun BoundProperty<Byte>.greaterThan(other: Int): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PByteGreaterThanInt")
infix fun Byte.greaterThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("byteGreaterThanOrEqualsInt")
infix fun BoundProperty<Byte>.greaterThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("byteGreaterThanOrEqualsIntP")
infix fun BoundProperty<Byte>.greaterThanOrEquals(other: Int): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PByteGreaterThanOrEqualsInt")
infix fun Byte.greaterThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("byteComparedToLong")
infix fun BoundProperty<Byte>.comparedTo(other: BoundProperty<Long>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("byteComparedToLongP")
infix fun BoundProperty<Byte>.comparedTo(other: Long): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PByteComparedToLong")
infix fun Byte.comparedTo(other: BoundProperty<Long>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("byteLessThanLong")
infix fun BoundProperty<Byte>.lessThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("byteLessThanLongP")
infix fun BoundProperty<Byte>.lessThan(other: Long): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PByteLessThanLong")
infix fun Byte.lessThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("byteLessThanOrEqualsLong")
infix fun BoundProperty<Byte>.lessThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("byteLessThanOrEqualsLongP")
infix fun BoundProperty<Byte>.lessThanOrEquals(other: Long): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PByteLessThanOrEqualsLong")
infix fun Byte.lessThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("byteGreaterThanLong")
infix fun BoundProperty<Byte>.greaterThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("byteGreaterThanLongP")
infix fun BoundProperty<Byte>.greaterThan(other: Long): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PByteGreaterThanLong")
infix fun Byte.greaterThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("byteGreaterThanOrEqualsLong")
infix fun BoundProperty<Byte>.greaterThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("byteGreaterThanOrEqualsLongP")
infix fun BoundProperty<Byte>.greaterThanOrEquals(other: Long): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PByteGreaterThanOrEqualsLong")
infix fun Byte.greaterThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("byteComparedToFloat")
infix fun BoundProperty<Byte>.comparedTo(other: BoundProperty<Float>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("byteComparedToFloatP")
infix fun BoundProperty<Byte>.comparedTo(other: Float): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PByteComparedToFloat")
infix fun Byte.comparedTo(other: BoundProperty<Float>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("byteLessThanFloat")
infix fun BoundProperty<Byte>.lessThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("byteLessThanFloatP")
infix fun BoundProperty<Byte>.lessThan(other: Float): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PByteLessThanFloat")
infix fun Byte.lessThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("byteLessThanOrEqualsFloat")
infix fun BoundProperty<Byte>.lessThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("byteLessThanOrEqualsFloatP")
infix fun BoundProperty<Byte>.lessThanOrEquals(other: Float): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PByteLessThanOrEqualsFloat")
infix fun Byte.lessThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("byteGreaterThanFloat")
infix fun BoundProperty<Byte>.greaterThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("byteGreaterThanFloatP")
infix fun BoundProperty<Byte>.greaterThan(other: Float): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PByteGreaterThanFloat")
infix fun Byte.greaterThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("byteGreaterThanOrEqualsFloat")
infix fun BoundProperty<Byte>.greaterThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("byteGreaterThanOrEqualsFloatP")
infix fun BoundProperty<Byte>.greaterThanOrEquals(other: Float): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PByteGreaterThanOrEqualsFloat")
infix fun Byte.greaterThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("byteComparedToDouble")
infix fun BoundProperty<Byte>.comparedTo(other: BoundProperty<Double>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("byteComparedToDoubleP")
infix fun BoundProperty<Byte>.comparedTo(other: Double): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PByteComparedToDouble")
infix fun Byte.comparedTo(other: BoundProperty<Double>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("byteLessThanDouble")
infix fun BoundProperty<Byte>.lessThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("byteLessThanDoubleP")
infix fun BoundProperty<Byte>.lessThan(other: Double): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PByteLessThanDouble")
infix fun Byte.lessThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("byteLessThanOrEqualsDouble")
infix fun BoundProperty<Byte>.lessThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("byteLessThanOrEqualsDoubleP")
infix fun BoundProperty<Byte>.lessThanOrEquals(other: Double): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PByteLessThanOrEqualsDouble")
infix fun Byte.lessThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("byteGreaterThanDouble")
infix fun BoundProperty<Byte>.greaterThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("byteGreaterThanDoubleP")
infix fun BoundProperty<Byte>.greaterThan(other: Double): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PByteGreaterThanDouble")
infix fun Byte.greaterThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("byteGreaterThanOrEqualsDouble")
infix fun BoundProperty<Byte>.greaterThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("byteGreaterThanOrEqualsDoubleP")
infix fun BoundProperty<Byte>.greaterThanOrEquals(other: Double): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PByteGreaterThanOrEqualsDouble")
infix fun Byte.greaterThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("bytePlusByte")
operator fun BoundProperty<Byte>.plus(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("bytePlusByteP")
operator fun BoundProperty<Byte>.plus(other: Byte): BoundProperty<Int> =
    derive { a -> a + other }

@JvmName("PBytePlusByte")
operator fun Byte.plus(other: BoundProperty<Byte>): BoundProperty<Int> =
    other.derive { a -> this + a }

@JvmName("bytePlusShort")
operator fun BoundProperty<Byte>.plus(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("bytePlusShortP")
operator fun BoundProperty<Byte>.plus(other: Short): BoundProperty<Int> =
    derive { a -> a + other }

@JvmName("PBytePlusShort")
operator fun Byte.plus(other: BoundProperty<Short>): BoundProperty<Int> =
    other.derive { a -> this + a }

@JvmName("bytePlusInt")
operator fun BoundProperty<Byte>.plus(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("bytePlusIntP")
operator fun BoundProperty<Byte>.plus(other: Int): BoundProperty<Int> =
    derive { a -> a + other }

@JvmName("PBytePlusInt")
operator fun Byte.plus(other: BoundProperty<Int>): BoundProperty<Int> =
    other.derive { a -> this + a }

@JvmName("bytePlusLong")
operator fun BoundProperty<Byte>.plus(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a + b }

@JvmName("bytePlusLongP")
operator fun BoundProperty<Byte>.plus(other: Long): BoundProperty<Long> =
    derive { a -> a + other }

@JvmName("PBytePlusLong")
operator fun Byte.plus(other: BoundProperty<Long>): BoundProperty<Long> =
    other.derive { a -> this + a }

@JvmName("bytePlusFloat")
operator fun BoundProperty<Byte>.plus(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("bytePlusFloatP")
operator fun BoundProperty<Byte>.plus(other: Float): BoundProperty<Float> =
    derive { a -> a + other }

@JvmName("PBytePlusFloat")
operator fun Byte.plus(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this + a }

@JvmName("bytePlusDouble")
operator fun BoundProperty<Byte>.plus(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("bytePlusDoubleP")
operator fun BoundProperty<Byte>.plus(other: Double): BoundProperty<Double> =
    derive { a -> a + other }

@JvmName("PBytePlusDouble")
operator fun Byte.plus(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this + a }

@JvmName("byteMinusByte")
operator fun BoundProperty<Byte>.minus(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("byteMinusByteP")
operator fun BoundProperty<Byte>.minus(other: Byte): BoundProperty<Int> =
    derive { a -> a - other }

@JvmName("PByteMinusByte")
operator fun Byte.minus(other: BoundProperty<Byte>): BoundProperty<Int> =
    other.derive { a -> this - a }

@JvmName("byteMinusShort")
operator fun BoundProperty<Byte>.minus(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("byteMinusShortP")
operator fun BoundProperty<Byte>.minus(other: Short): BoundProperty<Int> =
    derive { a -> a - other }

@JvmName("PByteMinusShort")
operator fun Byte.minus(other: BoundProperty<Short>): BoundProperty<Int> =
    other.derive { a -> this - a }

@JvmName("byteMinusInt")
operator fun BoundProperty<Byte>.minus(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("byteMinusIntP")
operator fun BoundProperty<Byte>.minus(other: Int): BoundProperty<Int> =
    derive { a -> a - other }

@JvmName("PByteMinusInt")
operator fun Byte.minus(other: BoundProperty<Int>): BoundProperty<Int> =
    other.derive { a -> this - a }

@JvmName("byteMinusLong")
operator fun BoundProperty<Byte>.minus(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a - b }

@JvmName("byteMinusLongP")
operator fun BoundProperty<Byte>.minus(other: Long): BoundProperty<Long> =
    derive { a -> a - other }

@JvmName("PByteMinusLong")
operator fun Byte.minus(other: BoundProperty<Long>): BoundProperty<Long> =
    other.derive { a -> this - a }

@JvmName("byteMinusFloat")
operator fun BoundProperty<Byte>.minus(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("byteMinusFloatP")
operator fun BoundProperty<Byte>.minus(other: Float): BoundProperty<Float> =
    derive { a -> a - other }

@JvmName("PByteMinusFloat")
operator fun Byte.minus(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this - a }

@JvmName("byteMinusDouble")
operator fun BoundProperty<Byte>.minus(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("byteMinusDoubleP")
operator fun BoundProperty<Byte>.minus(other: Double): BoundProperty<Double> =
    derive { a -> a - other }

@JvmName("PByteMinusDouble")
operator fun Byte.minus(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this - a }

@JvmName("byteTimesByte")
operator fun BoundProperty<Byte>.times(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("byteTimesByteP")
operator fun BoundProperty<Byte>.times(other: Byte): BoundProperty<Int> =
    derive { a -> a * other }

@JvmName("PByteTimesByte")
operator fun Byte.times(other: BoundProperty<Byte>): BoundProperty<Int> =
    other.derive { a -> this * a }

@JvmName("byteTimesShort")
operator fun BoundProperty<Byte>.times(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("byteTimesShortP")
operator fun BoundProperty<Byte>.times(other: Short): BoundProperty<Int> =
    derive { a -> a * other }

@JvmName("PByteTimesShort")
operator fun Byte.times(other: BoundProperty<Short>): BoundProperty<Int> =
    other.derive { a -> this * a }

@JvmName("byteTimesInt")
operator fun BoundProperty<Byte>.times(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("byteTimesIntP")
operator fun BoundProperty<Byte>.times(other: Int): BoundProperty<Int> =
    derive { a -> a * other }

@JvmName("PByteTimesInt")
operator fun Byte.times(other: BoundProperty<Int>): BoundProperty<Int> =
    other.derive { a -> this * a }

@JvmName("byteTimesLong")
operator fun BoundProperty<Byte>.times(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a * b }

@JvmName("byteTimesLongP")
operator fun BoundProperty<Byte>.times(other: Long): BoundProperty<Long> =
    derive { a -> a * other }

@JvmName("PByteTimesLong")
operator fun Byte.times(other: BoundProperty<Long>): BoundProperty<Long> =
    other.derive { a -> this * a }

@JvmName("byteTimesFloat")
operator fun BoundProperty<Byte>.times(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("byteTimesFloatP")
operator fun BoundProperty<Byte>.times(other: Float): BoundProperty<Float> =
    derive { a -> a * other }

@JvmName("PByteTimesFloat")
operator fun Byte.times(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this * a }

@JvmName("byteTimesDouble")
operator fun BoundProperty<Byte>.times(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("byteTimesDoubleP")
operator fun BoundProperty<Byte>.times(other: Double): BoundProperty<Double> =
    derive { a -> a * other }

@JvmName("PByteTimesDouble")
operator fun Byte.times(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this * a }

@JvmName("byteDivByte")
operator fun BoundProperty<Byte>.div(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("byteDivByteP")
operator fun BoundProperty<Byte>.div(other: Byte): BoundProperty<Int> =
    derive { a -> a / other }

@JvmName("PByteDivByte")
operator fun Byte.div(other: BoundProperty<Byte>): BoundProperty<Int> =
    other.derive { a -> this / a }

@JvmName("byteDivShort")
operator fun BoundProperty<Byte>.div(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("byteDivShortP")
operator fun BoundProperty<Byte>.div(other: Short): BoundProperty<Int> =
    derive { a -> a / other }

@JvmName("PByteDivShort")
operator fun Byte.div(other: BoundProperty<Short>): BoundProperty<Int> =
    other.derive { a -> this / a }

@JvmName("byteDivInt")
operator fun BoundProperty<Byte>.div(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("byteDivIntP")
operator fun BoundProperty<Byte>.div(other: Int): BoundProperty<Int> =
    derive { a -> a / other }

@JvmName("PByteDivInt")
operator fun Byte.div(other: BoundProperty<Int>): BoundProperty<Int> =
    other.derive { a -> this / a }

@JvmName("byteDivLong")
operator fun BoundProperty<Byte>.div(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a / b }

@JvmName("byteDivLongP")
operator fun BoundProperty<Byte>.div(other: Long): BoundProperty<Long> =
    derive { a -> a / other }

@JvmName("PByteDivLong")
operator fun Byte.div(other: BoundProperty<Long>): BoundProperty<Long> =
    other.derive { a -> this / a }

@JvmName("byteDivFloat")
operator fun BoundProperty<Byte>.div(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("byteDivFloatP")
operator fun BoundProperty<Byte>.div(other: Float): BoundProperty<Float> =
    derive { a -> a / other }

@JvmName("PByteDivFloat")
operator fun Byte.div(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this / a }

@JvmName("byteDivDouble")
operator fun BoundProperty<Byte>.div(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("byteDivDoubleP")
operator fun BoundProperty<Byte>.div(other: Double): BoundProperty<Double> =
    derive { a -> a / other }

@JvmName("PByteDivDouble")
operator fun Byte.div(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this / a }

@JvmName("byteRemByte")
operator fun BoundProperty<Byte>.rem(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("byteRemByteP")
operator fun BoundProperty<Byte>.rem(other: Byte): BoundProperty<Int> =
    derive { a -> a % other }

@JvmName("PByteRemByte")
operator fun Byte.rem(other: BoundProperty<Byte>): BoundProperty<Int> =
    other.derive { a -> this % a }

@JvmName("byteRemShort")
operator fun BoundProperty<Byte>.rem(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("byteRemShortP")
operator fun BoundProperty<Byte>.rem(other: Short): BoundProperty<Int> =
    derive { a -> a % other }

@JvmName("PByteRemShort")
operator fun Byte.rem(other: BoundProperty<Short>): BoundProperty<Int> =
    other.derive { a -> this % a }

@JvmName("byteRemInt")
operator fun BoundProperty<Byte>.rem(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("byteRemIntP")
operator fun BoundProperty<Byte>.rem(other: Int): BoundProperty<Int> =
    derive { a -> a % other }

@JvmName("PByteRemInt")
operator fun Byte.rem(other: BoundProperty<Int>): BoundProperty<Int> =
    other.derive { a -> this % a }

@JvmName("byteRemLong")
operator fun BoundProperty<Byte>.rem(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a % b }

@JvmName("byteRemLongP")
operator fun BoundProperty<Byte>.rem(other: Long): BoundProperty<Long> =
    derive { a -> a % other }

@JvmName("PByteRemLong")
operator fun Byte.rem(other: BoundProperty<Long>): BoundProperty<Long> =
    other.derive { a -> this % a }

@JvmName("byteRemFloat")
operator fun BoundProperty<Byte>.rem(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("byteRemFloatP")
operator fun BoundProperty<Byte>.rem(other: Float): BoundProperty<Float> =
    derive { a -> a % other }

@JvmName("PByteRemFloat")
operator fun Byte.rem(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this % a }

@JvmName("byteRemDouble")
operator fun BoundProperty<Byte>.rem(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("byteRemDoubleP")
operator fun BoundProperty<Byte>.rem(other: Double): BoundProperty<Double> =
    derive { a -> a % other }

@JvmName("PByteRemDouble")
operator fun Byte.rem(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this % a }

@JvmName("byteUnaryPlus")
operator fun BoundProperty<Byte>.unaryPlus(): BoundProperty<Int> =
    derive { a -> a.toInt() }

@JvmName("byteUnaryMinus")
operator fun BoundProperty<Byte>.unaryMinus(): BoundProperty<Int> =
    derive { a -> -a.toInt() }

@JvmName("byteRangeToByte")
operator fun BoundProperty<Byte>.rangeTo(other: BoundProperty<Byte>): BoundProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("byteRangeToByteP")
operator fun BoundProperty<Byte>.rangeTo(other: Byte): BoundProperty<IntRange> =
    derive { a -> a..other }

@JvmName("PByteRangeToByte")
operator fun Byte.rangeTo(other: BoundProperty<Byte>): BoundProperty<IntRange> =
    other.derive { a -> this..a }

@JvmName("byteRangeToShort")
operator fun BoundProperty<Byte>.rangeTo(other: BoundProperty<Short>): BoundProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("byteRangeToShortP")
operator fun BoundProperty<Byte>.rangeTo(other: Short): BoundProperty<IntRange> =
    derive { a -> a..other }

@JvmName("PByteRangeToShort")
operator fun Byte.rangeTo(other: BoundProperty<Short>): BoundProperty<IntRange> =
    other.derive { a -> this..a }

@JvmName("byteRangeToInt")
operator fun BoundProperty<Byte>.rangeTo(other: BoundProperty<Int>): BoundProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("byteRangeToIntP")
operator fun BoundProperty<Byte>.rangeTo(other: Int): BoundProperty<IntRange> =
    derive { a -> a..other }

@JvmName("PByteRangeToInt")
operator fun Byte.rangeTo(other: BoundProperty<Int>): BoundProperty<IntRange> =
    other.derive { a -> this..a }

@JvmName("byteRangeToLong")
operator fun BoundProperty<Byte>.rangeTo(other: BoundProperty<Long>): BoundProperty<LongRange> =
    combine(other) { a, b -> a..b }

@JvmName("byteRangeToLongP")
operator fun BoundProperty<Byte>.rangeTo(other: Long): BoundProperty<LongRange> =
    derive { a -> a..other }

@JvmName("PByteRangeToLong")
operator fun Byte.rangeTo(other: BoundProperty<Long>): BoundProperty<LongRange> =
    other.derive { a -> this..a }

@JvmName("byteToByte")
fun BoundProperty<Byte>.toByte(): BoundProperty<Byte> = this

@JvmName("byteToChar")
fun BoundProperty<Byte>.toChar(): BoundProperty<Char> =
    derive { a -> a.toChar() }

@JvmName("byteToShort")
fun BoundProperty<Byte>.toShort(): BoundProperty<Short> =
    derive { a -> a.toShort() }

@JvmName("byteToInt")
fun BoundProperty<Byte>.toInt(): BoundProperty<Int> =
    derive { a -> a.toInt() }

@JvmName("byteToLong")
fun BoundProperty<Byte>.toLong(): BoundProperty<Long> =
    derive { a -> a.toLong() }

@JvmName("byteToFloat")
fun BoundProperty<Byte>.toFloat(): BoundProperty<Float> =
    derive { a -> a.toFloat() }

@JvmName("byteToDouble")
fun BoundProperty<Byte>.toDouble(): BoundProperty<Double> =
    derive { a -> a.toDouble() }
