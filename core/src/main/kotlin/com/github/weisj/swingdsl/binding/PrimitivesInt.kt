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

@JvmName("intComparedToByte")
infix fun BoundProperty<Int>.comparedTo(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("intComparedToByteP")
infix fun BoundProperty<Int>.comparedTo(other: Byte): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PIntComparedToByte")
infix fun Int.comparedTo(other: BoundProperty<Byte>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("intLessThanByte")
infix fun BoundProperty<Int>.lessThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("intLessThanByteP")
infix fun BoundProperty<Int>.lessThan(other: Byte): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PIntLessThanByte")
infix fun Int.lessThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("intLessThanOrEqualsByte")
infix fun BoundProperty<Int>.lessThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("intLessThanOrEqualsByteP")
infix fun BoundProperty<Int>.lessThanOrEquals(other: Byte): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PIntLessThanOrEqualsByte")
infix fun Int.lessThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("intGreaterThanByte")
infix fun BoundProperty<Int>.greaterThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("intGreaterThanByteP")
infix fun BoundProperty<Int>.greaterThan(other: Byte): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PIntGreaterThanByte")
infix fun Int.greaterThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("intGreaterThanOrEqualsByte")
infix fun BoundProperty<Int>.greaterThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("intGreaterThanOrEqualsByteP")
infix fun BoundProperty<Int>.greaterThanOrEquals(other: Byte): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PIntGreaterThanOrEqualsByte")
infix fun Int.greaterThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("intComparedToShort")
infix fun BoundProperty<Int>.comparedTo(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("intComparedToShortP")
infix fun BoundProperty<Int>.comparedTo(other: Short): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PIntComparedToShort")
infix fun Int.comparedTo(other: BoundProperty<Short>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("intLessThanShort")
infix fun BoundProperty<Int>.lessThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("intLessThanShortP")
infix fun BoundProperty<Int>.lessThan(other: Short): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PIntLessThanShort")
infix fun Int.lessThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("intLessThanOrEqualsShort")
infix fun BoundProperty<Int>.lessThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("intLessThanOrEqualsShortP")
infix fun BoundProperty<Int>.lessThanOrEquals(other: Short): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PIntLessThanOrEqualsShort")
infix fun Int.lessThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("intGreaterThanShort")
infix fun BoundProperty<Int>.greaterThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("intGreaterThanShortP")
infix fun BoundProperty<Int>.greaterThan(other: Short): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PIntGreaterThanShort")
infix fun Int.greaterThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("intGreaterThanOrEqualsShort")
infix fun BoundProperty<Int>.greaterThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("intGreaterThanOrEqualsShortP")
infix fun BoundProperty<Int>.greaterThanOrEquals(other: Short): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PIntGreaterThanOrEqualsShort")
infix fun Int.greaterThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("intComparedToInt")
infix fun BoundProperty<Int>.comparedTo(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("intComparedToIntP")
infix fun BoundProperty<Int>.comparedTo(other: Int): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PIntComparedToInt")
infix fun Int.comparedTo(other: BoundProperty<Int>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("intLessThanInt")
infix fun BoundProperty<Int>.lessThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("intLessThanIntP")
infix fun BoundProperty<Int>.lessThan(other: Int): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PIntLessThanInt")
infix fun Int.lessThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("intLessThanOrEqualsInt")
infix fun BoundProperty<Int>.lessThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("intLessThanOrEqualsIntP")
infix fun BoundProperty<Int>.lessThanOrEquals(other: Int): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PIntLessThanOrEqualsInt")
infix fun Int.lessThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("intGreaterThanInt")
infix fun BoundProperty<Int>.greaterThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("intGreaterThanIntP")
infix fun BoundProperty<Int>.greaterThan(other: Int): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PIntGreaterThanInt")
infix fun Int.greaterThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("intGreaterThanOrEqualsInt")
infix fun BoundProperty<Int>.greaterThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("intGreaterThanOrEqualsIntP")
infix fun BoundProperty<Int>.greaterThanOrEquals(other: Int): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PIntGreaterThanOrEqualsInt")
infix fun Int.greaterThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("intComparedToLong")
infix fun BoundProperty<Int>.comparedTo(other: BoundProperty<Long>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("intComparedToLongP")
infix fun BoundProperty<Int>.comparedTo(other: Long): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PIntComparedToLong")
infix fun Int.comparedTo(other: BoundProperty<Long>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("intLessThanLong")
infix fun BoundProperty<Int>.lessThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("intLessThanLongP")
infix fun BoundProperty<Int>.lessThan(other: Long): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PIntLessThanLong")
infix fun Int.lessThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("intLessThanOrEqualsLong")
infix fun BoundProperty<Int>.lessThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("intLessThanOrEqualsLongP")
infix fun BoundProperty<Int>.lessThanOrEquals(other: Long): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PIntLessThanOrEqualsLong")
infix fun Int.lessThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("intGreaterThanLong")
infix fun BoundProperty<Int>.greaterThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("intGreaterThanLongP")
infix fun BoundProperty<Int>.greaterThan(other: Long): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PIntGreaterThanLong")
infix fun Int.greaterThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("intGreaterThanOrEqualsLong")
infix fun BoundProperty<Int>.greaterThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("intGreaterThanOrEqualsLongP")
infix fun BoundProperty<Int>.greaterThanOrEquals(other: Long): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PIntGreaterThanOrEqualsLong")
infix fun Int.greaterThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("intComparedToFloat")
infix fun BoundProperty<Int>.comparedTo(other: BoundProperty<Float>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("intComparedToFloatP")
infix fun BoundProperty<Int>.comparedTo(other: Float): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PIntComparedToFloat")
infix fun Int.comparedTo(other: BoundProperty<Float>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("intLessThanFloat")
infix fun BoundProperty<Int>.lessThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("intLessThanFloatP")
infix fun BoundProperty<Int>.lessThan(other: Float): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PIntLessThanFloat")
infix fun Int.lessThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("intLessThanOrEqualsFloat")
infix fun BoundProperty<Int>.lessThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("intLessThanOrEqualsFloatP")
infix fun BoundProperty<Int>.lessThanOrEquals(other: Float): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PIntLessThanOrEqualsFloat")
infix fun Int.lessThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("intGreaterThanFloat")
infix fun BoundProperty<Int>.greaterThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("intGreaterThanFloatP")
infix fun BoundProperty<Int>.greaterThan(other: Float): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PIntGreaterThanFloat")
infix fun Int.greaterThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("intGreaterThanOrEqualsFloat")
infix fun BoundProperty<Int>.greaterThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("intGreaterThanOrEqualsFloatP")
infix fun BoundProperty<Int>.greaterThanOrEquals(other: Float): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PIntGreaterThanOrEqualsFloat")
infix fun Int.greaterThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("intComparedToDouble")
infix fun BoundProperty<Int>.comparedTo(other: BoundProperty<Double>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("intComparedToDoubleP")
infix fun BoundProperty<Int>.comparedTo(other: Double): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PIntComparedToDouble")
infix fun Int.comparedTo(other: BoundProperty<Double>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("intLessThanDouble")
infix fun BoundProperty<Int>.lessThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("intLessThanDoubleP")
infix fun BoundProperty<Int>.lessThan(other: Double): BoundProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PIntLessThanDouble")
infix fun Int.lessThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("intLessThanOrEqualsDouble")
infix fun BoundProperty<Int>.lessThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("intLessThanOrEqualsDoubleP")
infix fun BoundProperty<Int>.lessThanOrEquals(other: Double): BoundProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PIntLessThanOrEqualsDouble")
infix fun Int.lessThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("intGreaterThanDouble")
infix fun BoundProperty<Int>.greaterThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("intGreaterThanDoubleP")
infix fun BoundProperty<Int>.greaterThan(other: Double): BoundProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PIntGreaterThanDouble")
infix fun Int.greaterThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("intGreaterThanOrEqualsDouble")
infix fun BoundProperty<Int>.greaterThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("intGreaterThanOrEqualsDoubleP")
infix fun BoundProperty<Int>.greaterThanOrEquals(other: Double): BoundProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PIntGreaterThanOrEqualsDouble")
infix fun Int.greaterThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("intPlusByte")
operator fun BoundProperty<Int>.plus(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("intPlusByteP")
operator fun BoundProperty<Int>.plus(other: Byte): BoundProperty<Int> =
    derive { a -> a + other }

@JvmName("PIntPlusByte")
operator fun Int.plus(other: BoundProperty<Byte>): BoundProperty<Int> =
    other.derive { a -> this + a }

@JvmName("intPlusShort")
operator fun BoundProperty<Int>.plus(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("intPlusShortP")
operator fun BoundProperty<Int>.plus(other: Short): BoundProperty<Int> =
    derive { a -> a + other }

@JvmName("PIntPlusShort")
operator fun Int.plus(other: BoundProperty<Short>): BoundProperty<Int> =
    other.derive { a -> this + a }

@JvmName("intPlusInt")
operator fun BoundProperty<Int>.plus(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("intPlusIntP")
operator fun BoundProperty<Int>.plus(other: Int): BoundProperty<Int> =
    derive { a -> a + other }

@JvmName("PIntPlusInt")
operator fun Int.plus(other: BoundProperty<Int>): BoundProperty<Int> =
    other.derive { a -> this + a }

@JvmName("intPlusLong")
operator fun BoundProperty<Int>.plus(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a + b }

@JvmName("intPlusLongP")
operator fun BoundProperty<Int>.plus(other: Long): BoundProperty<Long> =
    derive { a -> a + other }

@JvmName("PIntPlusLong")
operator fun Int.plus(other: BoundProperty<Long>): BoundProperty<Long> =
    other.derive { a -> this + a }

@JvmName("intPlusFloat")
operator fun BoundProperty<Int>.plus(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("intPlusFloatP")
operator fun BoundProperty<Int>.plus(other: Float): BoundProperty<Float> =
    derive { a -> a + other }

@JvmName("PIntPlusFloat")
operator fun Int.plus(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this + a }

@JvmName("intPlusDouble")
operator fun BoundProperty<Int>.plus(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("intPlusDoubleP")
operator fun BoundProperty<Int>.plus(other: Double): BoundProperty<Double> =
    derive { a -> a + other }

@JvmName("PIntPlusDouble")
operator fun Int.plus(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this + a }

@JvmName("intMinusByte")
operator fun BoundProperty<Int>.minus(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("intMinusByteP")
operator fun BoundProperty<Int>.minus(other: Byte): BoundProperty<Int> =
    derive { a -> a - other }

@JvmName("PIntMinusByte")
operator fun Int.minus(other: BoundProperty<Byte>): BoundProperty<Int> =
    other.derive { a -> this - a }

@JvmName("intMinusShort")
operator fun BoundProperty<Int>.minus(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("intMinusShortP")
operator fun BoundProperty<Int>.minus(other: Short): BoundProperty<Int> =
    derive { a -> a - other }

@JvmName("PIntMinusShort")
operator fun Int.minus(other: BoundProperty<Short>): BoundProperty<Int> =
    other.derive { a -> this - a }

@JvmName("intMinusInt")
operator fun BoundProperty<Int>.minus(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("intMinusIntP")
operator fun BoundProperty<Int>.minus(other: Int): BoundProperty<Int> =
    derive { a -> a - other }

@JvmName("PIntMinusInt")
operator fun Int.minus(other: BoundProperty<Int>): BoundProperty<Int> =
    other.derive { a -> this - a }

@JvmName("intMinusLong")
operator fun BoundProperty<Int>.minus(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a - b }

@JvmName("intMinusLongP")
operator fun BoundProperty<Int>.minus(other: Long): BoundProperty<Long> =
    derive { a -> a - other }

@JvmName("PIntMinusLong")
operator fun Int.minus(other: BoundProperty<Long>): BoundProperty<Long> =
    other.derive { a -> this - a }

@JvmName("intMinusFloat")
operator fun BoundProperty<Int>.minus(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("intMinusFloatP")
operator fun BoundProperty<Int>.minus(other: Float): BoundProperty<Float> =
    derive { a -> a - other }

@JvmName("PIntMinusFloat")
operator fun Int.minus(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this - a }

@JvmName("intMinusDouble")
operator fun BoundProperty<Int>.minus(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("intMinusDoubleP")
operator fun BoundProperty<Int>.minus(other: Double): BoundProperty<Double> =
    derive { a -> a - other }

@JvmName("PIntMinusDouble")
operator fun Int.minus(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this - a }

@JvmName("intTimesByte")
operator fun BoundProperty<Int>.times(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("intTimesByteP")
operator fun BoundProperty<Int>.times(other: Byte): BoundProperty<Int> =
    derive { a -> a * other }

@JvmName("PIntTimesByte")
operator fun Int.times(other: BoundProperty<Byte>): BoundProperty<Int> =
    other.derive { a -> this * a }

@JvmName("intTimesShort")
operator fun BoundProperty<Int>.times(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("intTimesShortP")
operator fun BoundProperty<Int>.times(other: Short): BoundProperty<Int> =
    derive { a -> a * other }

@JvmName("PIntTimesShort")
operator fun Int.times(other: BoundProperty<Short>): BoundProperty<Int> =
    other.derive { a -> this * a }

@JvmName("intTimesInt")
operator fun BoundProperty<Int>.times(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("intTimesIntP")
operator fun BoundProperty<Int>.times(other: Int): BoundProperty<Int> =
    derive { a -> a * other }

@JvmName("PIntTimesInt")
operator fun Int.times(other: BoundProperty<Int>): BoundProperty<Int> =
    other.derive { a -> this * a }

@JvmName("intTimesLong")
operator fun BoundProperty<Int>.times(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a * b }

@JvmName("intTimesLongP")
operator fun BoundProperty<Int>.times(other: Long): BoundProperty<Long> =
    derive { a -> a * other }

@JvmName("PIntTimesLong")
operator fun Int.times(other: BoundProperty<Long>): BoundProperty<Long> =
    other.derive { a -> this * a }

@JvmName("intTimesFloat")
operator fun BoundProperty<Int>.times(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("intTimesFloatP")
operator fun BoundProperty<Int>.times(other: Float): BoundProperty<Float> =
    derive { a -> a * other }

@JvmName("PIntTimesFloat")
operator fun Int.times(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this * a }

@JvmName("intTimesDouble")
operator fun BoundProperty<Int>.times(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("intTimesDoubleP")
operator fun BoundProperty<Int>.times(other: Double): BoundProperty<Double> =
    derive { a -> a * other }

@JvmName("PIntTimesDouble")
operator fun Int.times(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this * a }

@JvmName("intDivByte")
operator fun BoundProperty<Int>.div(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("intDivByteP")
operator fun BoundProperty<Int>.div(other: Byte): BoundProperty<Int> =
    derive { a -> a / other }

@JvmName("PIntDivByte")
operator fun Int.div(other: BoundProperty<Byte>): BoundProperty<Int> =
    other.derive { a -> this / a }

@JvmName("intDivShort")
operator fun BoundProperty<Int>.div(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("intDivShortP")
operator fun BoundProperty<Int>.div(other: Short): BoundProperty<Int> =
    derive { a -> a / other }

@JvmName("PIntDivShort")
operator fun Int.div(other: BoundProperty<Short>): BoundProperty<Int> =
    other.derive { a -> this / a }

@JvmName("intDivInt")
operator fun BoundProperty<Int>.div(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("intDivIntP")
operator fun BoundProperty<Int>.div(other: Int): BoundProperty<Int> =
    derive { a -> a / other }

@JvmName("PIntDivInt")
operator fun Int.div(other: BoundProperty<Int>): BoundProperty<Int> =
    other.derive { a -> this / a }

@JvmName("intDivLong")
operator fun BoundProperty<Int>.div(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a / b }

@JvmName("intDivLongP")
operator fun BoundProperty<Int>.div(other: Long): BoundProperty<Long> =
    derive { a -> a / other }

@JvmName("PIntDivLong")
operator fun Int.div(other: BoundProperty<Long>): BoundProperty<Long> =
    other.derive { a -> this / a }

@JvmName("intDivFloat")
operator fun BoundProperty<Int>.div(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("intDivFloatP")
operator fun BoundProperty<Int>.div(other: Float): BoundProperty<Float> =
    derive { a -> a / other }

@JvmName("PIntDivFloat")
operator fun Int.div(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this / a }

@JvmName("intDivDouble")
operator fun BoundProperty<Int>.div(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("intDivDoubleP")
operator fun BoundProperty<Int>.div(other: Double): BoundProperty<Double> =
    derive { a -> a / other }

@JvmName("PIntDivDouble")
operator fun Int.div(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this / a }

@JvmName("intRemByte")
operator fun BoundProperty<Int>.rem(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("intRemByteP")
operator fun BoundProperty<Int>.rem(other: Byte): BoundProperty<Int> =
    derive { a -> a % other }

@JvmName("PIntRemByte")
operator fun Int.rem(other: BoundProperty<Byte>): BoundProperty<Int> =
    other.derive { a -> this % a }

@JvmName("intRemShort")
operator fun BoundProperty<Int>.rem(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("intRemShortP")
operator fun BoundProperty<Int>.rem(other: Short): BoundProperty<Int> =
    derive { a -> a % other }

@JvmName("PIntRemShort")
operator fun Int.rem(other: BoundProperty<Short>): BoundProperty<Int> =
    other.derive { a -> this % a }

@JvmName("intRemInt")
operator fun BoundProperty<Int>.rem(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("intRemIntP")
operator fun BoundProperty<Int>.rem(other: Int): BoundProperty<Int> =
    derive { a -> a % other }

@JvmName("PIntRemInt")
operator fun Int.rem(other: BoundProperty<Int>): BoundProperty<Int> =
    other.derive { a -> this % a }

@JvmName("intRemLong")
operator fun BoundProperty<Int>.rem(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a % b }

@JvmName("intRemLongP")
operator fun BoundProperty<Int>.rem(other: Long): BoundProperty<Long> =
    derive { a -> a % other }

@JvmName("PIntRemLong")
operator fun Int.rem(other: BoundProperty<Long>): BoundProperty<Long> =
    other.derive { a -> this % a }

@JvmName("intRemFloat")
operator fun BoundProperty<Int>.rem(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("intRemFloatP")
operator fun BoundProperty<Int>.rem(other: Float): BoundProperty<Float> =
    derive { a -> a % other }

@JvmName("PIntRemFloat")
operator fun Int.rem(other: BoundProperty<Float>): BoundProperty<Float> =
    other.derive { a -> this % a }

@JvmName("intRemDouble")
operator fun BoundProperty<Int>.rem(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("intRemDoubleP")
operator fun BoundProperty<Int>.rem(other: Double): BoundProperty<Double> =
    derive { a -> a % other }

@JvmName("PIntRemDouble")
operator fun Int.rem(other: BoundProperty<Double>): BoundProperty<Double> =
    other.derive { a -> this % a }

@JvmName("intUnaryPlus")
operator fun BoundProperty<Int>.unaryPlus(): BoundProperty<Int> = this

@JvmName("intUnaryMinus")
operator fun BoundProperty<Int>.unaryMinus(): BoundProperty<Int> =
    derive { a -> -a.toInt() }

@JvmName("intRangeToByte")
operator fun BoundProperty<Int>.rangeTo(other: BoundProperty<Byte>): BoundProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("intRangeToByteP")
operator fun BoundProperty<Int>.rangeTo(other: Byte): BoundProperty<IntRange> =
    derive { a -> a..other }

@JvmName("PIntRangeToByte")
operator fun Int.rangeTo(other: BoundProperty<Byte>): BoundProperty<IntRange> =
    other.derive { a -> this..a }

@JvmName("intRangeToShort")
operator fun BoundProperty<Int>.rangeTo(other: BoundProperty<Short>): BoundProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("intRangeToShortP")
operator fun BoundProperty<Int>.rangeTo(other: Short): BoundProperty<IntRange> =
    derive { a -> a..other }

@JvmName("PIntRangeToShort")
operator fun Int.rangeTo(other: BoundProperty<Short>): BoundProperty<IntRange> =
    other.derive { a -> this..a }

@JvmName("intRangeToInt")
operator fun BoundProperty<Int>.rangeTo(other: BoundProperty<Int>): BoundProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("intRangeToIntP")
operator fun BoundProperty<Int>.rangeTo(other: Int): BoundProperty<IntRange> =
    derive { a -> a..other }

@JvmName("PIntRangeToInt")
operator fun Int.rangeTo(other: BoundProperty<Int>): BoundProperty<IntRange> =
    other.derive { a -> this..a }

@JvmName("intRangeToLong")
operator fun BoundProperty<Int>.rangeTo(other: BoundProperty<Long>): BoundProperty<LongRange> =
    combine(other) { a, b -> a..b }

@JvmName("intRangeToLongP")
operator fun BoundProperty<Int>.rangeTo(other: Long): BoundProperty<LongRange> =
    derive { a -> a..other }

@JvmName("PIntRangeToLong")
operator fun Int.rangeTo(other: BoundProperty<Long>): BoundProperty<LongRange> =
    other.derive { a -> this..a }

@JvmName("intToByte")
fun BoundProperty<Int>.toByte(): BoundProperty<Byte> =
    derive { a -> a.toByte() }

@JvmName("intToChar")
fun BoundProperty<Int>.toChar(): BoundProperty<Char> =
    derive { a -> a.toChar() }

@JvmName("intToShort")
fun BoundProperty<Int>.toShort(): BoundProperty<Short> =
    derive { a -> a.toShort() }

@JvmName("intToInt")
fun BoundProperty<Int>.toInt(): BoundProperty<Int> = this

@JvmName("intToLong")
fun BoundProperty<Int>.toLong(): BoundProperty<Long> =
    derive { a -> a.toLong() }

@JvmName("intToFloat")
fun BoundProperty<Int>.toFloat(): BoundProperty<Float> =
    derive { a -> a.toFloat() }

@JvmName("intToDouble")
fun BoundProperty<Int>.toDouble(): BoundProperty<Double> =
    derive { a -> a.toDouble() }
