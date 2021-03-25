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

@JvmName("floatComparedToByte")
infix fun BoundProperty<Float>.comparedTo(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("floatComparedToByteP")
infix fun BoundProperty<Float>.comparedTo(other: Byte): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PFloatComparedToByte")
infix fun Float.comparedTo(other: BoundProperty<Byte>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("floatLessThanByte")
infix fun BoundProperty<Float>.lessThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("floatLessThanByteP")
infix fun BoundProperty<Float>.lessThan(other: Byte): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PFloatLessThanByte")
infix fun Float.lessThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("floatLessThanOrEqualsByte")
infix fun BoundProperty<Float>.lessThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("floatLessThanOrEqualsByteP")
infix fun BoundProperty<Float>.lessThanOrEquals(other: Byte): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PFloatLessThanOrEqualsByte")
infix fun Float.lessThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("floatGreaterThanByte")
infix fun BoundProperty<Float>.greaterThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("floatGreaterThanByteP")
infix fun BoundProperty<Float>.greaterThan(other: Byte): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PFloatGreaterThanByte")
infix fun Float.greaterThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("floatGreaterThanOrEqualsByte")
infix fun BoundProperty<Float>.greaterThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("floatGreaterThanOrEqualsByteP")
infix fun BoundProperty<Float>.greaterThanOrEquals(other: Byte): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PFloatGreaterThanOrEqualsByte")
infix fun Float.greaterThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("floatComparedToShort")
infix fun BoundProperty<Float>.comparedTo(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("floatComparedToShortP")
infix fun BoundProperty<Float>.comparedTo(other: Short): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PFloatComparedToShort")
infix fun Float.comparedTo(other: BoundProperty<Short>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("floatLessThanShort")
infix fun BoundProperty<Float>.lessThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("floatLessThanShortP")
infix fun BoundProperty<Float>.lessThan(other: Short): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PFloatLessThanShort")
infix fun Float.lessThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("floatLessThanOrEqualsShort")
infix fun BoundProperty<Float>.lessThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("floatLessThanOrEqualsShortP")
infix fun BoundProperty<Float>.lessThanOrEquals(other: Short): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PFloatLessThanOrEqualsShort")
infix fun Float.lessThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("floatGreaterThanShort")
infix fun BoundProperty<Float>.greaterThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("floatGreaterThanShortP")
infix fun BoundProperty<Float>.greaterThan(other: Short): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PFloatGreaterThanShort")
infix fun Float.greaterThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("floatGreaterThanOrEqualsShort")
infix fun BoundProperty<Float>.greaterThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("floatGreaterThanOrEqualsShortP")
infix fun BoundProperty<Float>.greaterThanOrEquals(other: Short): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PFloatGreaterThanOrEqualsShort")
infix fun Float.greaterThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("floatComparedToInt")
infix fun BoundProperty<Float>.comparedTo(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("floatComparedToIntP")
infix fun BoundProperty<Float>.comparedTo(other: Int): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PFloatComparedToInt")
infix fun Float.comparedTo(other: BoundProperty<Int>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("floatLessThanInt")
infix fun BoundProperty<Float>.lessThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("floatLessThanIntP")
infix fun BoundProperty<Float>.lessThan(other: Int): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PFloatLessThanInt")
infix fun Float.lessThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("floatLessThanOrEqualsInt")
infix fun BoundProperty<Float>.lessThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("floatLessThanOrEqualsIntP")
infix fun BoundProperty<Float>.lessThanOrEquals(other: Int): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PFloatLessThanOrEqualsInt")
infix fun Float.lessThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("floatGreaterThanInt")
infix fun BoundProperty<Float>.greaterThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("floatGreaterThanIntP")
infix fun BoundProperty<Float>.greaterThan(other: Int): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PFloatGreaterThanInt")
infix fun Float.greaterThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("floatGreaterThanOrEqualsInt")
infix fun BoundProperty<Float>.greaterThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("floatGreaterThanOrEqualsIntP")
infix fun BoundProperty<Float>.greaterThanOrEquals(other: Int): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PFloatGreaterThanOrEqualsInt")
infix fun Float.greaterThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("floatComparedToLong")
infix fun BoundProperty<Float>.comparedTo(other: BoundProperty<Long>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("floatComparedToLongP")
infix fun BoundProperty<Float>.comparedTo(other: Long): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PFloatComparedToLong")
infix fun Float.comparedTo(other: BoundProperty<Long>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("floatLessThanLong")
infix fun BoundProperty<Float>.lessThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("floatLessThanLongP")
infix fun BoundProperty<Float>.lessThan(other: Long): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PFloatLessThanLong")
infix fun Float.lessThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("floatLessThanOrEqualsLong")
infix fun BoundProperty<Float>.lessThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("floatLessThanOrEqualsLongP")
infix fun BoundProperty<Float>.lessThanOrEquals(other: Long): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PFloatLessThanOrEqualsLong")
infix fun Float.lessThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("floatGreaterThanLong")
infix fun BoundProperty<Float>.greaterThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("floatGreaterThanLongP")
infix fun BoundProperty<Float>.greaterThan(other: Long): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PFloatGreaterThanLong")
infix fun Float.greaterThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("floatGreaterThanOrEqualsLong")
infix fun BoundProperty<Float>.greaterThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("floatGreaterThanOrEqualsLongP")
infix fun BoundProperty<Float>.greaterThanOrEquals(other: Long): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PFloatGreaterThanOrEqualsLong")
infix fun Float.greaterThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("floatComparedToFloat")
infix fun BoundProperty<Float>.comparedTo(other: BoundProperty<Float>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("floatComparedToFloatP")
infix fun BoundProperty<Float>.comparedTo(other: Float): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PFloatComparedToFloat")
infix fun Float.comparedTo(other: BoundProperty<Float>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("floatLessThanFloat")
infix fun BoundProperty<Float>.lessThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("floatLessThanFloatP")
infix fun BoundProperty<Float>.lessThan(other: Float): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PFloatLessThanFloat")
infix fun Float.lessThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("floatLessThanOrEqualsFloat")
infix fun BoundProperty<Float>.lessThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("floatLessThanOrEqualsFloatP")
infix fun BoundProperty<Float>.lessThanOrEquals(other: Float): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PFloatLessThanOrEqualsFloat")
infix fun Float.lessThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("floatGreaterThanFloat")
infix fun BoundProperty<Float>.greaterThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("floatGreaterThanFloatP")
infix fun BoundProperty<Float>.greaterThan(other: Float): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PFloatGreaterThanFloat")
infix fun Float.greaterThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("floatGreaterThanOrEqualsFloat")
infix fun BoundProperty<Float>.greaterThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("floatGreaterThanOrEqualsFloatP")
infix fun BoundProperty<Float>.greaterThanOrEquals(other: Float): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PFloatGreaterThanOrEqualsFloat")
infix fun Float.greaterThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("floatComparedToDouble")
infix fun BoundProperty<Float>.comparedTo(other: BoundProperty<Double>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("floatComparedToDoubleP")
infix fun BoundProperty<Float>.comparedTo(other: Double): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PFloatComparedToDouble")
infix fun Float.comparedTo(other: BoundProperty<Double>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("floatLessThanDouble")
infix fun BoundProperty<Float>.lessThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("floatLessThanDoubleP")
infix fun BoundProperty<Float>.lessThan(other: Double): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PFloatLessThanDouble")
infix fun Float.lessThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("floatLessThanOrEqualsDouble")
infix fun BoundProperty<Float>.lessThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("floatLessThanOrEqualsDoubleP")
infix fun BoundProperty<Float>.lessThanOrEquals(other: Double): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PFloatLessThanOrEqualsDouble")
infix fun Float.lessThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("floatGreaterThanDouble")
infix fun BoundProperty<Float>.greaterThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("floatGreaterThanDoubleP")
infix fun BoundProperty<Float>.greaterThan(other: Double): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PFloatGreaterThanDouble")
infix fun Float.greaterThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("floatGreaterThanOrEqualsDouble")
infix fun BoundProperty<Float>.greaterThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("floatGreaterThanOrEqualsDoubleP")
infix fun BoundProperty<Float>.greaterThanOrEquals(other: Double): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PFloatGreaterThanOrEqualsDouble")
infix fun Float.greaterThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("floatPlusByte")
operator fun BoundProperty<Float>.plus(other: BoundProperty<Byte>): BoundProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("floatPlusByteP")
operator fun BoundProperty<Float>.plus(other: Byte): BoundProperty<Float> =
    derive { a -> a + other }

@JvmName("PFloatPlusByte")
operator fun Float.plus(other: BoundProperty<Byte>): BoundProperty<Float> =
    other.derive { a -> this + a }

@JvmName("floatPlusShort")
operator fun BoundProperty<Float>.plus(other: BoundProperty<Short>): BoundProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("floatPlusShortP")
operator fun BoundProperty<Float>.plus(other: Short): BoundProperty<Float> =
    derive { a -> a + other }

@JvmName("PFloatPlusShort")
operator fun Float.plus(other: BoundProperty<Short>): BoundProperty<Float> =
    other.derive { a -> this + a }

@JvmName("floatPlusInt")
operator fun BoundProperty<Float>.plus(other: BoundProperty<Int>): BoundProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("floatPlusIntP")
operator fun BoundProperty<Float>.plus(other: Int): BoundProperty<Float> =
    derive { a -> a + other }

@JvmName("PFloatPlusInt")
operator fun Float.plus(other: BoundProperty<Int>): BoundProperty<Float> =
    other.derive { a -> this + a }

@JvmName("floatPlusLong")
operator fun BoundProperty<Float>.plus(other: BoundProperty<Long>): BoundProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("floatPlusLongP")
operator fun BoundProperty<Float>.plus(other: Long): BoundProperty<Float> =
    derive { a -> a + other }

@JvmName("PFloatPlusLong")
operator fun Float.plus(other: BoundProperty<Long>): BoundProperty<Float> =
    other.derive { a -> this + a }

@JvmName("floatPlusFloat")
operator fun BoundProperty<Float>.plus(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("floatPlusFloatP")
operator fun BoundProperty<Float>.plus(other: Float): BoundProperty<Float> =
    derive { a -> a + other }

@JvmName("PFloatPlusFloat")
operator fun Float.plus(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this + a }

@JvmName("floatPlusDouble")
operator fun BoundProperty<Float>.plus(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("floatPlusDoubleP")
operator fun BoundProperty<Float>.plus(other: Double): BoundProperty<Double> =
    derive { a -> a + other }

@JvmName("PFloatPlusDouble")
operator fun Float.plus(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this + a }

@JvmName("floatMinusByte")
operator fun BoundProperty<Float>.minus(other: BoundProperty<Byte>): BoundProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("floatMinusByteP")
operator fun BoundProperty<Float>.minus(other: Byte): BoundProperty<Float> =
    derive { a -> a - other }

@JvmName("PFloatMinusByte")
operator fun Float.minus(other: BoundProperty<Byte>): BoundProperty<Float> =
    other.derive { a -> this - a }

@JvmName("floatMinusShort")
operator fun BoundProperty<Float>.minus(other: BoundProperty<Short>): BoundProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("floatMinusShortP")
operator fun BoundProperty<Float>.minus(other: Short): BoundProperty<Float> =
    derive { a -> a - other }

@JvmName("PFloatMinusShort")
operator fun Float.minus(other: BoundProperty<Short>): BoundProperty<Float> =
    other.derive { a -> this - a }

@JvmName("floatMinusInt")
operator fun BoundProperty<Float>.minus(other: BoundProperty<Int>): BoundProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("floatMinusIntP")
operator fun BoundProperty<Float>.minus(other: Int): BoundProperty<Float> =
    derive { a -> a - other }

@JvmName("PFloatMinusInt")
operator fun Float.minus(other: BoundProperty<Int>): BoundProperty<Float> =
    other.derive { a -> this - a }

@JvmName("floatMinusLong")
operator fun BoundProperty<Float>.minus(other: BoundProperty<Long>): BoundProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("floatMinusLongP")
operator fun BoundProperty<Float>.minus(other: Long): BoundProperty<Float> =
    derive { a -> a - other }

@JvmName("PFloatMinusLong")
operator fun Float.minus(other: BoundProperty<Long>): BoundProperty<Float> =
    other.derive { a -> this - a }

@JvmName("floatMinusFloat")
operator fun BoundProperty<Float>.minus(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("floatMinusFloatP")
operator fun BoundProperty<Float>.minus(other: Float): BoundProperty<Float> =
    derive { a -> a - other }

@JvmName("PFloatMinusFloat")
operator fun Float.minus(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this - a }

@JvmName("floatMinusDouble")
operator fun BoundProperty<Float>.minus(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("floatMinusDoubleP")
operator fun BoundProperty<Float>.minus(other: Double): BoundProperty<Double> =
    derive { a -> a - other }

@JvmName("PFloatMinusDouble")
operator fun Float.minus(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this - a }

@JvmName("floatTimesByte")
operator fun BoundProperty<Float>.times(other: BoundProperty<Byte>): BoundProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("floatTimesByteP")
operator fun BoundProperty<Float>.times(other: Byte): BoundProperty<Float> =
    derive { a -> a * other }

@JvmName("PFloatTimesByte")
operator fun Float.times(other: BoundProperty<Byte>): BoundProperty<Float> =
    other.derive { a -> this * a }

@JvmName("floatTimesShort")
operator fun BoundProperty<Float>.times(other: BoundProperty<Short>): BoundProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("floatTimesShortP")
operator fun BoundProperty<Float>.times(other: Short): BoundProperty<Float> =
    derive { a -> a * other }

@JvmName("PFloatTimesShort")
operator fun Float.times(other: BoundProperty<Short>): BoundProperty<Float> =
    other.derive { a -> this * a }

@JvmName("floatTimesInt")
operator fun BoundProperty<Float>.times(other: BoundProperty<Int>): BoundProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("floatTimesIntP")
operator fun BoundProperty<Float>.times(other: Int): BoundProperty<Float> =
    derive { a -> a * other }

@JvmName("PFloatTimesInt")
operator fun Float.times(other: BoundProperty<Int>): BoundProperty<Float> =
    other.derive { a -> this * a }

@JvmName("floatTimesLong")
operator fun BoundProperty<Float>.times(other: BoundProperty<Long>): BoundProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("floatTimesLongP")
operator fun BoundProperty<Float>.times(other: Long): BoundProperty<Float> =
    derive { a -> a * other }

@JvmName("PFloatTimesLong")
operator fun Float.times(other: BoundProperty<Long>): BoundProperty<Float> =
    other.derive { a -> this * a }

@JvmName("floatTimesFloat")
operator fun BoundProperty<Float>.times(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("floatTimesFloatP")
operator fun BoundProperty<Float>.times(other: Float): BoundProperty<Float> =
    derive { a -> a * other }

@JvmName("PFloatTimesFloat")
operator fun Float.times(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this * a }

@JvmName("floatTimesDouble")
operator fun BoundProperty<Float>.times(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("floatTimesDoubleP")
operator fun BoundProperty<Float>.times(other: Double): BoundProperty<Double> =
    derive { a -> a * other }

@JvmName("PFloatTimesDouble")
operator fun Float.times(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this * a }

@JvmName("floatDivByte")
operator fun BoundProperty<Float>.div(other: BoundProperty<Byte>): BoundProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("floatDivByteP")
operator fun BoundProperty<Float>.div(other: Byte): BoundProperty<Float> =
    derive { a -> a / other }

@JvmName("PFloatDivByte")
operator fun Float.div(other: BoundProperty<Byte>): BoundProperty<Float> =
    other.derive { a -> this / a }

@JvmName("floatDivShort")
operator fun BoundProperty<Float>.div(other: BoundProperty<Short>): BoundProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("floatDivShortP")
operator fun BoundProperty<Float>.div(other: Short): BoundProperty<Float> =
    derive { a -> a / other }

@JvmName("PFloatDivShort")
operator fun Float.div(other: BoundProperty<Short>): BoundProperty<Float> =
    other.derive { a -> this / a }

@JvmName("floatDivInt")
operator fun BoundProperty<Float>.div(other: BoundProperty<Int>): BoundProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("floatDivIntP")
operator fun BoundProperty<Float>.div(other: Int): BoundProperty<Float> =
    derive { a -> a / other }

@JvmName("PFloatDivInt")
operator fun Float.div(other: BoundProperty<Int>): BoundProperty<Float> =
    other.derive { a -> this / a }

@JvmName("floatDivLong")
operator fun BoundProperty<Float>.div(other: BoundProperty<Long>): BoundProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("floatDivLongP")
operator fun BoundProperty<Float>.div(other: Long): BoundProperty<Float> =
    derive { a -> a / other }

@JvmName("PFloatDivLong")
operator fun Float.div(other: BoundProperty<Long>): BoundProperty<Float> =
    other.derive { a -> this / a }

@JvmName("floatDivFloat")
operator fun BoundProperty<Float>.div(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("floatDivFloatP")
operator fun BoundProperty<Float>.div(other: Float): BoundProperty<Float> =
    derive { a -> a / other }

@JvmName("PFloatDivFloat")
operator fun Float.div(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this / a }

@JvmName("floatDivDouble")
operator fun BoundProperty<Float>.div(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("floatDivDoubleP")
operator fun BoundProperty<Float>.div(other: Double): BoundProperty<Double> =
    derive { a -> a / other }

@JvmName("PFloatDivDouble")
operator fun Float.div(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this / a }

@JvmName("floatRemByte")
operator fun BoundProperty<Float>.rem(other: BoundProperty<Byte>): BoundProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("floatRemByteP")
operator fun BoundProperty<Float>.rem(other: Byte): BoundProperty<Float> =
    derive { a -> a % other }

@JvmName("PFloatRemByte")
operator fun Float.rem(other: BoundProperty<Byte>): BoundProperty<Float> =
    other.derive { a -> this % a }

@JvmName("floatRemShort")
operator fun BoundProperty<Float>.rem(other: BoundProperty<Short>): BoundProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("floatRemShortP")
operator fun BoundProperty<Float>.rem(other: Short): BoundProperty<Float> =
    derive { a -> a % other }

@JvmName("PFloatRemShort")
operator fun Float.rem(other: BoundProperty<Short>): BoundProperty<Float> =
    other.derive { a -> this % a }

@JvmName("floatRemInt")
operator fun BoundProperty<Float>.rem(other: BoundProperty<Int>): BoundProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("floatRemIntP")
operator fun BoundProperty<Float>.rem(other: Int): BoundProperty<Float> =
    derive { a -> a % other }

@JvmName("PFloatRemInt")
operator fun Float.rem(other: BoundProperty<Int>): BoundProperty<Float> =
    other.derive { a -> this % a }

@JvmName("floatRemLong")
operator fun BoundProperty<Float>.rem(other: BoundProperty<Long>): BoundProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("floatRemLongP")
operator fun BoundProperty<Float>.rem(other: Long): BoundProperty<Float> =
    derive { a -> a % other }

@JvmName("PFloatRemLong")
operator fun Float.rem(other: BoundProperty<Long>): BoundProperty<Float> =
    other.derive { a -> this % a }

@JvmName("floatRemFloat")
operator fun BoundProperty<Float>.rem(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("floatRemFloatP")
operator fun BoundProperty<Float>.rem(other: Float): BoundProperty<Float> =
    derive { a -> a % other }

@JvmName("PFloatRemFloat")
operator fun Float.rem(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this % a }

@JvmName("floatRemDouble")
operator fun BoundProperty<Float>.rem(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("floatRemDoubleP")
operator fun BoundProperty<Float>.rem(other: Double): BoundProperty<Double> =
    derive { a -> a % other }

@JvmName("PFloatRemDouble")
operator fun Float.rem(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this % a }

@JvmName("floatUnaryPlus")
operator fun BoundProperty<Float>.unaryPlus(): BoundProperty<Float> = this

@JvmName("floatUnaryMinus")
operator fun BoundProperty<Float>.unaryMinus(): BoundProperty<Float> =
    derive { a -> -a.toFloat() }

@JvmName("floatToChar")
fun BoundProperty<Float>.toChar(): BoundProperty<Char> =
    derive { a -> a.toChar() }

@JvmName("floatToInt")
fun BoundProperty<Float>.toInt(): BoundProperty<Int> =
    derive { a -> a.toInt() }

@JvmName("floatToLong")
fun BoundProperty<Float>.toLong(): BoundProperty<Long> =
    derive { a -> a.toLong() }

@JvmName("floatToFloat")
fun BoundProperty<Float>.toFloat(): BoundProperty<Float> = this

@JvmName("floatToDouble")
fun BoundProperty<Float>.toDouble(): BoundProperty<Double> =
    derive { a -> a.toDouble() }
