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

@JvmName("intComparedToByte")
infix fun ObservableProperty<Int>.comparedTo(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("intComparedToByteP")
infix fun ObservableProperty<Int>.comparedTo(other: Byte): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PIntComparedToByte")
infix fun Int.comparedTo(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("intLessThanByte")
infix fun ObservableProperty<Int>.lessThan(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("intLessThanByteP")
infix fun ObservableProperty<Int>.lessThan(other: Byte): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PIntLessThanByte")
infix fun Int.lessThan(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("intLessThanOrEqualsByte")
infix fun ObservableProperty<Int>.lessThanOrEquals(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("intLessThanOrEqualsByteP")
infix fun ObservableProperty<Int>.lessThanOrEquals(other: Byte): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PIntLessThanOrEqualsByte")
infix fun Int.lessThanOrEquals(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("intGreaterThanByte")
infix fun ObservableProperty<Int>.greaterThan(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("intGreaterThanByteP")
infix fun ObservableProperty<Int>.greaterThan(other: Byte): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PIntGreaterThanByte")
infix fun Int.greaterThan(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("intGreaterThanOrEqualsByte")
infix fun ObservableProperty<Int>.greaterThanOrEquals(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("intGreaterThanOrEqualsByteP")
infix fun ObservableProperty<Int>.greaterThanOrEquals(other: Byte): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PIntGreaterThanOrEqualsByte")
infix fun Int.greaterThanOrEquals(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("intComparedToShort")
infix fun ObservableProperty<Int>.comparedTo(other: ObservableProperty<Short>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("intComparedToShortP")
infix fun ObservableProperty<Int>.comparedTo(other: Short): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PIntComparedToShort")
infix fun Int.comparedTo(other: ObservableProperty<Short>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("intLessThanShort")
infix fun ObservableProperty<Int>.lessThan(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("intLessThanShortP")
infix fun ObservableProperty<Int>.lessThan(other: Short): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PIntLessThanShort")
infix fun Int.lessThan(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("intLessThanOrEqualsShort")
infix fun ObservableProperty<Int>.lessThanOrEquals(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("intLessThanOrEqualsShortP")
infix fun ObservableProperty<Int>.lessThanOrEquals(other: Short): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PIntLessThanOrEqualsShort")
infix fun Int.lessThanOrEquals(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("intGreaterThanShort")
infix fun ObservableProperty<Int>.greaterThan(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("intGreaterThanShortP")
infix fun ObservableProperty<Int>.greaterThan(other: Short): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PIntGreaterThanShort")
infix fun Int.greaterThan(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("intGreaterThanOrEqualsShort")
infix fun ObservableProperty<Int>.greaterThanOrEquals(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("intGreaterThanOrEqualsShortP")
infix fun ObservableProperty<Int>.greaterThanOrEquals(other: Short): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PIntGreaterThanOrEqualsShort")
infix fun Int.greaterThanOrEquals(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("intComparedToInt")
infix fun ObservableProperty<Int>.comparedTo(other: ObservableProperty<Int>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("intComparedToIntP")
infix fun ObservableProperty<Int>.comparedTo(other: Int): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PIntComparedToInt")
infix fun Int.comparedTo(other: ObservableProperty<Int>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("intLessThanInt")
infix fun ObservableProperty<Int>.lessThan(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("intLessThanIntP")
infix fun ObservableProperty<Int>.lessThan(other: Int): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PIntLessThanInt")
infix fun Int.lessThan(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("intLessThanOrEqualsInt")
infix fun ObservableProperty<Int>.lessThanOrEquals(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("intLessThanOrEqualsIntP")
infix fun ObservableProperty<Int>.lessThanOrEquals(other: Int): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PIntLessThanOrEqualsInt")
infix fun Int.lessThanOrEquals(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("intGreaterThanInt")
infix fun ObservableProperty<Int>.greaterThan(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("intGreaterThanIntP")
infix fun ObservableProperty<Int>.greaterThan(other: Int): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PIntGreaterThanInt")
infix fun Int.greaterThan(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("intGreaterThanOrEqualsInt")
infix fun ObservableProperty<Int>.greaterThanOrEquals(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("intGreaterThanOrEqualsIntP")
infix fun ObservableProperty<Int>.greaterThanOrEquals(other: Int): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PIntGreaterThanOrEqualsInt")
infix fun Int.greaterThanOrEquals(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("intComparedToLong")
infix fun ObservableProperty<Int>.comparedTo(other: ObservableProperty<Long>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("intComparedToLongP")
infix fun ObservableProperty<Int>.comparedTo(other: Long): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PIntComparedToLong")
infix fun Int.comparedTo(other: ObservableProperty<Long>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("intLessThanLong")
infix fun ObservableProperty<Int>.lessThan(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("intLessThanLongP")
infix fun ObservableProperty<Int>.lessThan(other: Long): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PIntLessThanLong")
infix fun Int.lessThan(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("intLessThanOrEqualsLong")
infix fun ObservableProperty<Int>.lessThanOrEquals(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("intLessThanOrEqualsLongP")
infix fun ObservableProperty<Int>.lessThanOrEquals(other: Long): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PIntLessThanOrEqualsLong")
infix fun Int.lessThanOrEquals(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("intGreaterThanLong")
infix fun ObservableProperty<Int>.greaterThan(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("intGreaterThanLongP")
infix fun ObservableProperty<Int>.greaterThan(other: Long): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PIntGreaterThanLong")
infix fun Int.greaterThan(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("intGreaterThanOrEqualsLong")
infix fun ObservableProperty<Int>.greaterThanOrEquals(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("intGreaterThanOrEqualsLongP")
infix fun ObservableProperty<Int>.greaterThanOrEquals(other: Long): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PIntGreaterThanOrEqualsLong")
infix fun Int.greaterThanOrEquals(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("intComparedToFloat")
infix fun ObservableProperty<Int>.comparedTo(other: ObservableProperty<Float>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("intComparedToFloatP")
infix fun ObservableProperty<Int>.comparedTo(other: Float): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PIntComparedToFloat")
infix fun Int.comparedTo(other: ObservableProperty<Float>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("intLessThanFloat")
infix fun ObservableProperty<Int>.lessThan(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("intLessThanFloatP")
infix fun ObservableProperty<Int>.lessThan(other: Float): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PIntLessThanFloat")
infix fun Int.lessThan(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("intLessThanOrEqualsFloat")
infix fun ObservableProperty<Int>.lessThanOrEquals(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("intLessThanOrEqualsFloatP")
infix fun ObservableProperty<Int>.lessThanOrEquals(other: Float): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PIntLessThanOrEqualsFloat")
infix fun Int.lessThanOrEquals(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("intGreaterThanFloat")
infix fun ObservableProperty<Int>.greaterThan(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("intGreaterThanFloatP")
infix fun ObservableProperty<Int>.greaterThan(other: Float): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PIntGreaterThanFloat")
infix fun Int.greaterThan(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("intGreaterThanOrEqualsFloat")
infix fun ObservableProperty<Int>.greaterThanOrEquals(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("intGreaterThanOrEqualsFloatP")
infix fun ObservableProperty<Int>.greaterThanOrEquals(other: Float): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PIntGreaterThanOrEqualsFloat")
infix fun Int.greaterThanOrEquals(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("intComparedToDouble")
infix fun ObservableProperty<Int>.comparedTo(other: ObservableProperty<Double>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("intComparedToDoubleP")
infix fun ObservableProperty<Int>.comparedTo(other: Double): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PIntComparedToDouble")
infix fun Int.comparedTo(other: ObservableProperty<Double>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("intLessThanDouble")
infix fun ObservableProperty<Int>.lessThan(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("intLessThanDoubleP")
infix fun ObservableProperty<Int>.lessThan(other: Double): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PIntLessThanDouble")
infix fun Int.lessThan(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("intLessThanOrEqualsDouble")
infix fun ObservableProperty<Int>.lessThanOrEquals(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("intLessThanOrEqualsDoubleP")
infix fun ObservableProperty<Int>.lessThanOrEquals(other: Double): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PIntLessThanOrEqualsDouble")
infix fun Int.lessThanOrEquals(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("intGreaterThanDouble")
infix fun ObservableProperty<Int>.greaterThan(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("intGreaterThanDoubleP")
infix fun ObservableProperty<Int>.greaterThan(other: Double): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PIntGreaterThanDouble")
infix fun Int.greaterThan(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("intGreaterThanOrEqualsDouble")
infix fun ObservableProperty<Int>.greaterThanOrEquals(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("intGreaterThanOrEqualsDoubleP")
infix fun ObservableProperty<Int>.greaterThanOrEquals(other: Double): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PIntGreaterThanOrEqualsDouble")
infix fun Int.greaterThanOrEquals(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("intPlusByte")
operator fun ObservableProperty<Int>.plus(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("intPlusByteP")
operator fun ObservableProperty<Int>.plus(other: Byte): ObservableProperty<Int> =
    derive { a -> a + other }

@JvmName("PIntPlusByte")
operator fun Int.plus(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    other.derive { a -> this + a }

@JvmName("intPlusShort")
operator fun ObservableProperty<Int>.plus(other: ObservableProperty<Short>): ObservableProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("intPlusShortP")
operator fun ObservableProperty<Int>.plus(other: Short): ObservableProperty<Int> =
    derive { a -> a + other }

@JvmName("PIntPlusShort")
operator fun Int.plus(other: ObservableProperty<Short>): ObservableProperty<Int> =
    other.derive { a -> this + a }

@JvmName("intPlusInt")
operator fun ObservableProperty<Int>.plus(other: ObservableProperty<Int>): ObservableProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("intPlusIntP")
operator fun ObservableProperty<Int>.plus(other: Int): ObservableProperty<Int> =
    derive { a -> a + other }

@JvmName("PIntPlusInt")
operator fun Int.plus(other: ObservableProperty<Int>): ObservableProperty<Int> =
    other.derive { a -> this + a }

@JvmName("intPlusLong")
operator fun ObservableProperty<Int>.plus(other: ObservableProperty<Long>): ObservableProperty<Long> =
    combine(other) { a, b -> a + b }

@JvmName("intPlusLongP")
operator fun ObservableProperty<Int>.plus(other: Long): ObservableProperty<Long> =
    derive { a -> a + other }

@JvmName("PIntPlusLong")
operator fun Int.plus(other: ObservableProperty<Long>): ObservableProperty<Long> =
    other.derive { a -> this + a }

@JvmName("intPlusFloat")
operator fun ObservableProperty<Int>.plus(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("intPlusFloatP")
operator fun ObservableProperty<Int>.plus(other: Float): ObservableProperty<Float> =
    derive { a -> a + other }

@JvmName("PIntPlusFloat")
operator fun Int.plus(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this + a }

@JvmName("intPlusDouble")
operator fun ObservableProperty<Int>.plus(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("intPlusDoubleP")
operator fun ObservableProperty<Int>.plus(other: Double): ObservableProperty<Double> =
    derive { a -> a + other }

@JvmName("PIntPlusDouble")
operator fun Int.plus(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this + a }

@JvmName("intMinusByte")
operator fun ObservableProperty<Int>.minus(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("intMinusByteP")
operator fun ObservableProperty<Int>.minus(other: Byte): ObservableProperty<Int> =
    derive { a -> a - other }

@JvmName("PIntMinusByte")
operator fun Int.minus(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    other.derive { a -> this - a }

@JvmName("intMinusShort")
operator fun ObservableProperty<Int>.minus(other: ObservableProperty<Short>): ObservableProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("intMinusShortP")
operator fun ObservableProperty<Int>.minus(other: Short): ObservableProperty<Int> =
    derive { a -> a - other }

@JvmName("PIntMinusShort")
operator fun Int.minus(other: ObservableProperty<Short>): ObservableProperty<Int> =
    other.derive { a -> this - a }

@JvmName("intMinusInt")
operator fun ObservableProperty<Int>.minus(other: ObservableProperty<Int>): ObservableProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("intMinusIntP")
operator fun ObservableProperty<Int>.minus(other: Int): ObservableProperty<Int> =
    derive { a -> a - other }

@JvmName("PIntMinusInt")
operator fun Int.minus(other: ObservableProperty<Int>): ObservableProperty<Int> =
    other.derive { a -> this - a }

@JvmName("intMinusLong")
operator fun ObservableProperty<Int>.minus(other: ObservableProperty<Long>): ObservableProperty<Long> =
    combine(other) { a, b -> a - b }

@JvmName("intMinusLongP")
operator fun ObservableProperty<Int>.minus(other: Long): ObservableProperty<Long> =
    derive { a -> a - other }

@JvmName("PIntMinusLong")
operator fun Int.minus(other: ObservableProperty<Long>): ObservableProperty<Long> =
    other.derive { a -> this - a }

@JvmName("intMinusFloat")
operator fun ObservableProperty<Int>.minus(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("intMinusFloatP")
operator fun ObservableProperty<Int>.minus(other: Float): ObservableProperty<Float> =
    derive { a -> a - other }

@JvmName("PIntMinusFloat")
operator fun Int.minus(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this - a }

@JvmName("intMinusDouble")
operator fun ObservableProperty<Int>.minus(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("intMinusDoubleP")
operator fun ObservableProperty<Int>.minus(other: Double): ObservableProperty<Double> =
    derive { a -> a - other }

@JvmName("PIntMinusDouble")
operator fun Int.minus(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this - a }

@JvmName("intTimesByte")
operator fun ObservableProperty<Int>.times(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("intTimesByteP")
operator fun ObservableProperty<Int>.times(other: Byte): ObservableProperty<Int> =
    derive { a -> a * other }

@JvmName("PIntTimesByte")
operator fun Int.times(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    other.derive { a -> this * a }

@JvmName("intTimesShort")
operator fun ObservableProperty<Int>.times(other: ObservableProperty<Short>): ObservableProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("intTimesShortP")
operator fun ObservableProperty<Int>.times(other: Short): ObservableProperty<Int> =
    derive { a -> a * other }

@JvmName("PIntTimesShort")
operator fun Int.times(other: ObservableProperty<Short>): ObservableProperty<Int> =
    other.derive { a -> this * a }

@JvmName("intTimesInt")
operator fun ObservableProperty<Int>.times(other: ObservableProperty<Int>): ObservableProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("intTimesIntP")
operator fun ObservableProperty<Int>.times(other: Int): ObservableProperty<Int> =
    derive { a -> a * other }

@JvmName("PIntTimesInt")
operator fun Int.times(other: ObservableProperty<Int>): ObservableProperty<Int> =
    other.derive { a -> this * a }

@JvmName("intTimesLong")
operator fun ObservableProperty<Int>.times(other: ObservableProperty<Long>): ObservableProperty<Long> =
    combine(other) { a, b -> a * b }

@JvmName("intTimesLongP")
operator fun ObservableProperty<Int>.times(other: Long): ObservableProperty<Long> =
    derive { a -> a * other }

@JvmName("PIntTimesLong")
operator fun Int.times(other: ObservableProperty<Long>): ObservableProperty<Long> =
    other.derive { a -> this * a }

@JvmName("intTimesFloat")
operator fun ObservableProperty<Int>.times(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("intTimesFloatP")
operator fun ObservableProperty<Int>.times(other: Float): ObservableProperty<Float> =
    derive { a -> a * other }

@JvmName("PIntTimesFloat")
operator fun Int.times(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this * a }

@JvmName("intTimesDouble")
operator fun ObservableProperty<Int>.times(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("intTimesDoubleP")
operator fun ObservableProperty<Int>.times(other: Double): ObservableProperty<Double> =
    derive { a -> a * other }

@JvmName("PIntTimesDouble")
operator fun Int.times(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this * a }

@JvmName("intDivByte")
operator fun ObservableProperty<Int>.div(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("intDivByteP")
operator fun ObservableProperty<Int>.div(other: Byte): ObservableProperty<Int> =
    derive { a -> a / other }

@JvmName("PIntDivByte")
operator fun Int.div(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    other.derive { a -> this / a }

@JvmName("intDivShort")
operator fun ObservableProperty<Int>.div(other: ObservableProperty<Short>): ObservableProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("intDivShortP")
operator fun ObservableProperty<Int>.div(other: Short): ObservableProperty<Int> =
    derive { a -> a / other }

@JvmName("PIntDivShort")
operator fun Int.div(other: ObservableProperty<Short>): ObservableProperty<Int> =
    other.derive { a -> this / a }

@JvmName("intDivInt")
operator fun ObservableProperty<Int>.div(other: ObservableProperty<Int>): ObservableProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("intDivIntP")
operator fun ObservableProperty<Int>.div(other: Int): ObservableProperty<Int> =
    derive { a -> a / other }

@JvmName("PIntDivInt")
operator fun Int.div(other: ObservableProperty<Int>): ObservableProperty<Int> =
    other.derive { a -> this / a }

@JvmName("intDivLong")
operator fun ObservableProperty<Int>.div(other: ObservableProperty<Long>): ObservableProperty<Long> =
    combine(other) { a, b -> a / b }

@JvmName("intDivLongP")
operator fun ObservableProperty<Int>.div(other: Long): ObservableProperty<Long> =
    derive { a -> a / other }

@JvmName("PIntDivLong")
operator fun Int.div(other: ObservableProperty<Long>): ObservableProperty<Long> =
    other.derive { a -> this / a }

@JvmName("intDivFloat")
operator fun ObservableProperty<Int>.div(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("intDivFloatP")
operator fun ObservableProperty<Int>.div(other: Float): ObservableProperty<Float> =
    derive { a -> a / other }

@JvmName("PIntDivFloat")
operator fun Int.div(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this / a }

@JvmName("intDivDouble")
operator fun ObservableProperty<Int>.div(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("intDivDoubleP")
operator fun ObservableProperty<Int>.div(other: Double): ObservableProperty<Double> =
    derive { a -> a / other }

@JvmName("PIntDivDouble")
operator fun Int.div(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this / a }

@JvmName("intRemByte")
operator fun ObservableProperty<Int>.rem(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("intRemByteP")
operator fun ObservableProperty<Int>.rem(other: Byte): ObservableProperty<Int> =
    derive { a -> a % other }

@JvmName("PIntRemByte")
operator fun Int.rem(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    other.derive { a -> this % a }

@JvmName("intRemShort")
operator fun ObservableProperty<Int>.rem(other: ObservableProperty<Short>): ObservableProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("intRemShortP")
operator fun ObservableProperty<Int>.rem(other: Short): ObservableProperty<Int> =
    derive { a -> a % other }

@JvmName("PIntRemShort")
operator fun Int.rem(other: ObservableProperty<Short>): ObservableProperty<Int> =
    other.derive { a -> this % a }

@JvmName("intRemInt")
operator fun ObservableProperty<Int>.rem(other: ObservableProperty<Int>): ObservableProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("intRemIntP")
operator fun ObservableProperty<Int>.rem(other: Int): ObservableProperty<Int> =
    derive { a -> a % other }

@JvmName("PIntRemInt")
operator fun Int.rem(other: ObservableProperty<Int>): ObservableProperty<Int> =
    other.derive { a -> this % a }

@JvmName("intRemLong")
operator fun ObservableProperty<Int>.rem(other: ObservableProperty<Long>): ObservableProperty<Long> =
    combine(other) { a, b -> a % b }

@JvmName("intRemLongP")
operator fun ObservableProperty<Int>.rem(other: Long): ObservableProperty<Long> =
    derive { a -> a % other }

@JvmName("PIntRemLong")
operator fun Int.rem(other: ObservableProperty<Long>): ObservableProperty<Long> =
    other.derive { a -> this % a }

@JvmName("intRemFloat")
operator fun ObservableProperty<Int>.rem(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("intRemFloatP")
operator fun ObservableProperty<Int>.rem(other: Float): ObservableProperty<Float> =
    derive { a -> a % other }

@JvmName("PIntRemFloat")
operator fun Int.rem(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this % a }

@JvmName("intRemDouble")
operator fun ObservableProperty<Int>.rem(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("intRemDoubleP")
operator fun ObservableProperty<Int>.rem(other: Double): ObservableProperty<Double> =
    derive { a -> a % other }

@JvmName("PIntRemDouble")
operator fun Int.rem(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this % a }

@JvmName("intUnaryPlus")
operator fun ObservableProperty<Int>.unaryPlus(): ObservableProperty<Int> = this

@JvmName("intUnaryMinus")
operator fun ObservableProperty<Int>.unaryMinus(): ObservableProperty<Int> =
    derive { a -> -a }

@JvmName("intRangeToByte")
operator fun ObservableProperty<Int>.rangeTo(other: ObservableProperty<Byte>): ObservableProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("intRangeToByteP")
operator fun ObservableProperty<Int>.rangeTo(other: Byte): ObservableProperty<IntRange> =
    derive { a -> a..other }

@JvmName("PIntRangeToByte")
operator fun Int.rangeTo(other: ObservableProperty<Byte>): ObservableProperty<IntRange> =
    other.derive { a -> this..a }

@JvmName("intRangeToShort")
operator fun ObservableProperty<Int>.rangeTo(other: ObservableProperty<Short>): ObservableProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("intRangeToShortP")
operator fun ObservableProperty<Int>.rangeTo(other: Short): ObservableProperty<IntRange> =
    derive { a -> a..other }

@JvmName("PIntRangeToShort")
operator fun Int.rangeTo(other: ObservableProperty<Short>): ObservableProperty<IntRange> =
    other.derive { a -> this..a }

@JvmName("intRangeToInt")
operator fun ObservableProperty<Int>.rangeTo(other: ObservableProperty<Int>): ObservableProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("intRangeToIntP")
operator fun ObservableProperty<Int>.rangeTo(other: Int): ObservableProperty<IntRange> =
    derive { a -> a..other }

@JvmName("PIntRangeToInt")
operator fun Int.rangeTo(other: ObservableProperty<Int>): ObservableProperty<IntRange> =
    other.derive { a -> this..a }

@JvmName("intRangeToLong")
operator fun ObservableProperty<Int>.rangeTo(other: ObservableProperty<Long>): ObservableProperty<LongRange> =
    combine(other) { a, b -> a..b }

@JvmName("intRangeToLongP")
operator fun ObservableProperty<Int>.rangeTo(other: Long): ObservableProperty<LongRange> =
    derive { a -> a..other }

@JvmName("PIntRangeToLong")
operator fun Int.rangeTo(other: ObservableProperty<Long>): ObservableProperty<LongRange> =
    other.derive { a -> this..a }

@JvmName("intToByte")
fun ObservableProperty<Int>.toByte(): ObservableProperty<Byte> =
    derive { a -> a.toByte() }

@JvmName("intToShort")
fun ObservableProperty<Int>.toShort(): ObservableProperty<Short> =
    derive { a -> a.toShort() }

@JvmName("intToInt")
fun ObservableProperty<Int>.toInt(): ObservableProperty<Int> = this

@JvmName("intToLong")
fun ObservableProperty<Int>.toLong(): ObservableProperty<Long> =
    derive { a -> a.toLong() }

@JvmName("intToFloat")
fun ObservableProperty<Int>.toFloat(): ObservableProperty<Float> =
    derive { a -> a.toFloat() }

@JvmName("intToDouble")
fun ObservableProperty<Int>.toDouble(): ObservableProperty<Double> =
    derive { a -> a.toDouble() }
