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
infix fun ObservableProperty<Float>.comparedTo(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("floatComparedToByteP")
infix fun ObservableProperty<Float>.comparedTo(other: Byte): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PFloatComparedToByte")
infix fun Float.comparedTo(other: ObservableProperty<Byte>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("floatLessThanByte")
infix fun ObservableProperty<Float>.lessThan(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("floatLessThanByteP")
infix fun ObservableProperty<Float>.lessThan(other: Byte): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PFloatLessThanByte")
infix fun Float.lessThan(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("floatLessThanOrEqualsByte")
infix fun ObservableProperty<Float>.lessThanOrEquals(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("floatLessThanOrEqualsByteP")
infix fun ObservableProperty<Float>.lessThanOrEquals(other: Byte): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PFloatLessThanOrEqualsByte")
infix fun Float.lessThanOrEquals(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("floatGreaterThanByte")
infix fun ObservableProperty<Float>.greaterThan(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("floatGreaterThanByteP")
infix fun ObservableProperty<Float>.greaterThan(other: Byte): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PFloatGreaterThanByte")
infix fun Float.greaterThan(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("floatGreaterThanOrEqualsByte")
infix fun ObservableProperty<Float>.greaterThanOrEquals(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("floatGreaterThanOrEqualsByteP")
infix fun ObservableProperty<Float>.greaterThanOrEquals(other: Byte): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PFloatGreaterThanOrEqualsByte")
infix fun Float.greaterThanOrEquals(other: ObservableProperty<Byte>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("floatComparedToShort")
infix fun ObservableProperty<Float>.comparedTo(other: ObservableProperty<Short>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("floatComparedToShortP")
infix fun ObservableProperty<Float>.comparedTo(other: Short): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PFloatComparedToShort")
infix fun Float.comparedTo(other: ObservableProperty<Short>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("floatLessThanShort")
infix fun ObservableProperty<Float>.lessThan(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("floatLessThanShortP")
infix fun ObservableProperty<Float>.lessThan(other: Short): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PFloatLessThanShort")
infix fun Float.lessThan(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("floatLessThanOrEqualsShort")
infix fun ObservableProperty<Float>.lessThanOrEquals(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("floatLessThanOrEqualsShortP")
infix fun ObservableProperty<Float>.lessThanOrEquals(other: Short): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PFloatLessThanOrEqualsShort")
infix fun Float.lessThanOrEquals(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("floatGreaterThanShort")
infix fun ObservableProperty<Float>.greaterThan(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("floatGreaterThanShortP")
infix fun ObservableProperty<Float>.greaterThan(other: Short): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PFloatGreaterThanShort")
infix fun Float.greaterThan(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("floatGreaterThanOrEqualsShort")
infix fun ObservableProperty<Float>.greaterThanOrEquals(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("floatGreaterThanOrEqualsShortP")
infix fun ObservableProperty<Float>.greaterThanOrEquals(other: Short): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PFloatGreaterThanOrEqualsShort")
infix fun Float.greaterThanOrEquals(other: ObservableProperty<Short>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("floatComparedToInt")
infix fun ObservableProperty<Float>.comparedTo(other: ObservableProperty<Int>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("floatComparedToIntP")
infix fun ObservableProperty<Float>.comparedTo(other: Int): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PFloatComparedToInt")
infix fun Float.comparedTo(other: ObservableProperty<Int>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("floatLessThanInt")
infix fun ObservableProperty<Float>.lessThan(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("floatLessThanIntP")
infix fun ObservableProperty<Float>.lessThan(other: Int): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PFloatLessThanInt")
infix fun Float.lessThan(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("floatLessThanOrEqualsInt")
infix fun ObservableProperty<Float>.lessThanOrEquals(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("floatLessThanOrEqualsIntP")
infix fun ObservableProperty<Float>.lessThanOrEquals(other: Int): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PFloatLessThanOrEqualsInt")
infix fun Float.lessThanOrEquals(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("floatGreaterThanInt")
infix fun ObservableProperty<Float>.greaterThan(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("floatGreaterThanIntP")
infix fun ObservableProperty<Float>.greaterThan(other: Int): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PFloatGreaterThanInt")
infix fun Float.greaterThan(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("floatGreaterThanOrEqualsInt")
infix fun ObservableProperty<Float>.greaterThanOrEquals(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("floatGreaterThanOrEqualsIntP")
infix fun ObservableProperty<Float>.greaterThanOrEquals(other: Int): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PFloatGreaterThanOrEqualsInt")
infix fun Float.greaterThanOrEquals(other: ObservableProperty<Int>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("floatComparedToLong")
infix fun ObservableProperty<Float>.comparedTo(other: ObservableProperty<Long>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("floatComparedToLongP")
infix fun ObservableProperty<Float>.comparedTo(other: Long): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PFloatComparedToLong")
infix fun Float.comparedTo(other: ObservableProperty<Long>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("floatLessThanLong")
infix fun ObservableProperty<Float>.lessThan(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("floatLessThanLongP")
infix fun ObservableProperty<Float>.lessThan(other: Long): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PFloatLessThanLong")
infix fun Float.lessThan(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("floatLessThanOrEqualsLong")
infix fun ObservableProperty<Float>.lessThanOrEquals(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("floatLessThanOrEqualsLongP")
infix fun ObservableProperty<Float>.lessThanOrEquals(other: Long): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PFloatLessThanOrEqualsLong")
infix fun Float.lessThanOrEquals(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("floatGreaterThanLong")
infix fun ObservableProperty<Float>.greaterThan(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("floatGreaterThanLongP")
infix fun ObservableProperty<Float>.greaterThan(other: Long): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PFloatGreaterThanLong")
infix fun Float.greaterThan(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("floatGreaterThanOrEqualsLong")
infix fun ObservableProperty<Float>.greaterThanOrEquals(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("floatGreaterThanOrEqualsLongP")
infix fun ObservableProperty<Float>.greaterThanOrEquals(other: Long): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PFloatGreaterThanOrEqualsLong")
infix fun Float.greaterThanOrEquals(other: ObservableProperty<Long>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("floatComparedToFloat")
infix fun ObservableProperty<Float>.comparedTo(other: ObservableProperty<Float>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("floatComparedToFloatP")
infix fun ObservableProperty<Float>.comparedTo(other: Float): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PFloatComparedToFloat")
infix fun Float.comparedTo(other: ObservableProperty<Float>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("floatLessThanFloat")
infix fun ObservableProperty<Float>.lessThan(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("floatLessThanFloatP")
infix fun ObservableProperty<Float>.lessThan(other: Float): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PFloatLessThanFloat")
infix fun Float.lessThan(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("floatLessThanOrEqualsFloat")
infix fun ObservableProperty<Float>.lessThanOrEquals(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("floatLessThanOrEqualsFloatP")
infix fun ObservableProperty<Float>.lessThanOrEquals(other: Float): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PFloatLessThanOrEqualsFloat")
infix fun Float.lessThanOrEquals(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("floatGreaterThanFloat")
infix fun ObservableProperty<Float>.greaterThan(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("floatGreaterThanFloatP")
infix fun ObservableProperty<Float>.greaterThan(other: Float): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PFloatGreaterThanFloat")
infix fun Float.greaterThan(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("floatGreaterThanOrEqualsFloat")
infix fun ObservableProperty<Float>.greaterThanOrEquals(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("floatGreaterThanOrEqualsFloatP")
infix fun ObservableProperty<Float>.greaterThanOrEquals(other: Float): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PFloatGreaterThanOrEqualsFloat")
infix fun Float.greaterThanOrEquals(other: ObservableProperty<Float>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("floatComparedToDouble")
infix fun ObservableProperty<Float>.comparedTo(other: ObservableProperty<Double>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("floatComparedToDoubleP")
infix fun ObservableProperty<Float>.comparedTo(other: Double): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PFloatComparedToDouble")
infix fun Float.comparedTo(other: ObservableProperty<Double>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("floatLessThanDouble")
infix fun ObservableProperty<Float>.lessThan(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("floatLessThanDoubleP")
infix fun ObservableProperty<Float>.lessThan(other: Double): ObservableProperty<Boolean> =
    derive { a -> a < other }

@JvmName("PFloatLessThanDouble")
infix fun Float.lessThan(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    other.derive { a -> this < a }

@JvmName("floatLessThanOrEqualsDouble")
infix fun ObservableProperty<Float>.lessThanOrEquals(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("floatLessThanOrEqualsDoubleP")
infix fun ObservableProperty<Float>.lessThanOrEquals(other: Double): ObservableProperty<Boolean> =
    derive { a -> a <= other }

@JvmName("PFloatLessThanOrEqualsDouble")
infix fun Float.lessThanOrEquals(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    other.derive { a -> this <= a }

@JvmName("floatGreaterThanDouble")
infix fun ObservableProperty<Float>.greaterThan(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("floatGreaterThanDoubleP")
infix fun ObservableProperty<Float>.greaterThan(other: Double): ObservableProperty<Boolean> =
    derive { a -> a > other }

@JvmName("PFloatGreaterThanDouble")
infix fun Float.greaterThan(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    other.derive { a -> this > a }

@JvmName("floatGreaterThanOrEqualsDouble")
infix fun ObservableProperty<Float>.greaterThanOrEquals(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("floatGreaterThanOrEqualsDoubleP")
infix fun ObservableProperty<Float>.greaterThanOrEquals(other: Double): ObservableProperty<Boolean> =
    derive { a -> a >= other }

@JvmName("PFloatGreaterThanOrEqualsDouble")
infix fun Float.greaterThanOrEquals(other: ObservableProperty<Double>): ObservableProperty<Boolean> =
    other.derive { a -> this >= a }

@JvmName("floatPlusByte")
operator fun ObservableProperty<Float>.plus(other: ObservableProperty<Byte>): ObservableProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("floatPlusByteP")
operator fun ObservableProperty<Float>.plus(other: Byte): ObservableProperty<Float> =
    derive { a -> a + other }

@JvmName("PFloatPlusByte")
operator fun Float.plus(other: ObservableProperty<Byte>): ObservableProperty<Float> =
    other.derive { a -> this + a }

@JvmName("floatPlusShort")
operator fun ObservableProperty<Float>.plus(other: ObservableProperty<Short>): ObservableProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("floatPlusShortP")
operator fun ObservableProperty<Float>.plus(other: Short): ObservableProperty<Float> =
    derive { a -> a + other }

@JvmName("PFloatPlusShort")
operator fun Float.plus(other: ObservableProperty<Short>): ObservableProperty<Float> =
    other.derive { a -> this + a }

@JvmName("floatPlusInt")
operator fun ObservableProperty<Float>.plus(other: ObservableProperty<Int>): ObservableProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("floatPlusIntP")
operator fun ObservableProperty<Float>.plus(other: Int): ObservableProperty<Float> =
    derive { a -> a + other }

@JvmName("PFloatPlusInt")
operator fun Float.plus(other: ObservableProperty<Int>): ObservableProperty<Float> =
    other.derive { a -> this + a }

@JvmName("floatPlusLong")
operator fun ObservableProperty<Float>.plus(other: ObservableProperty<Long>): ObservableProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("floatPlusLongP")
operator fun ObservableProperty<Float>.plus(other: Long): ObservableProperty<Float> =
    derive { a -> a + other }

@JvmName("PFloatPlusLong")
operator fun Float.plus(other: ObservableProperty<Long>): ObservableProperty<Float> =
    other.derive { a -> this + a }

@JvmName("floatPlusFloat")
operator fun ObservableProperty<Float>.plus(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("floatPlusFloatP")
operator fun ObservableProperty<Float>.plus(other: Float): ObservableProperty<Float> =
    derive { a -> a + other }

@JvmName("PFloatPlusFloat")
operator fun Float.plus(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this + a }

@JvmName("floatPlusDouble")
operator fun ObservableProperty<Float>.plus(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("floatPlusDoubleP")
operator fun ObservableProperty<Float>.plus(other: Double): ObservableProperty<Double> =
    derive { a -> a + other }

@JvmName("PFloatPlusDouble")
operator fun Float.plus(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this + a }

@JvmName("floatMinusByte")
operator fun ObservableProperty<Float>.minus(other: ObservableProperty<Byte>): ObservableProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("floatMinusByteP")
operator fun ObservableProperty<Float>.minus(other: Byte): ObservableProperty<Float> =
    derive { a -> a - other }

@JvmName("PFloatMinusByte")
operator fun Float.minus(other: ObservableProperty<Byte>): ObservableProperty<Float> =
    other.derive { a -> this - a }

@JvmName("floatMinusShort")
operator fun ObservableProperty<Float>.minus(other: ObservableProperty<Short>): ObservableProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("floatMinusShortP")
operator fun ObservableProperty<Float>.minus(other: Short): ObservableProperty<Float> =
    derive { a -> a - other }

@JvmName("PFloatMinusShort")
operator fun Float.minus(other: ObservableProperty<Short>): ObservableProperty<Float> =
    other.derive { a -> this - a }

@JvmName("floatMinusInt")
operator fun ObservableProperty<Float>.minus(other: ObservableProperty<Int>): ObservableProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("floatMinusIntP")
operator fun ObservableProperty<Float>.minus(other: Int): ObservableProperty<Float> =
    derive { a -> a - other }

@JvmName("PFloatMinusInt")
operator fun Float.minus(other: ObservableProperty<Int>): ObservableProperty<Float> =
    other.derive { a -> this - a }

@JvmName("floatMinusLong")
operator fun ObservableProperty<Float>.minus(other: ObservableProperty<Long>): ObservableProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("floatMinusLongP")
operator fun ObservableProperty<Float>.minus(other: Long): ObservableProperty<Float> =
    derive { a -> a - other }

@JvmName("PFloatMinusLong")
operator fun Float.minus(other: ObservableProperty<Long>): ObservableProperty<Float> =
    other.derive { a -> this - a }

@JvmName("floatMinusFloat")
operator fun ObservableProperty<Float>.minus(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("floatMinusFloatP")
operator fun ObservableProperty<Float>.minus(other: Float): ObservableProperty<Float> =
    derive { a -> a - other }

@JvmName("PFloatMinusFloat")
operator fun Float.minus(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this - a }

@JvmName("floatMinusDouble")
operator fun ObservableProperty<Float>.minus(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("floatMinusDoubleP")
operator fun ObservableProperty<Float>.minus(other: Double): ObservableProperty<Double> =
    derive { a -> a - other }

@JvmName("PFloatMinusDouble")
operator fun Float.minus(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this - a }

@JvmName("floatTimesByte")
operator fun ObservableProperty<Float>.times(other: ObservableProperty<Byte>): ObservableProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("floatTimesByteP")
operator fun ObservableProperty<Float>.times(other: Byte): ObservableProperty<Float> =
    derive { a -> a * other }

@JvmName("PFloatTimesByte")
operator fun Float.times(other: ObservableProperty<Byte>): ObservableProperty<Float> =
    other.derive { a -> this * a }

@JvmName("floatTimesShort")
operator fun ObservableProperty<Float>.times(other: ObservableProperty<Short>): ObservableProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("floatTimesShortP")
operator fun ObservableProperty<Float>.times(other: Short): ObservableProperty<Float> =
    derive { a -> a * other }

@JvmName("PFloatTimesShort")
operator fun Float.times(other: ObservableProperty<Short>): ObservableProperty<Float> =
    other.derive { a -> this * a }

@JvmName("floatTimesInt")
operator fun ObservableProperty<Float>.times(other: ObservableProperty<Int>): ObservableProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("floatTimesIntP")
operator fun ObservableProperty<Float>.times(other: Int): ObservableProperty<Float> =
    derive { a -> a * other }

@JvmName("PFloatTimesInt")
operator fun Float.times(other: ObservableProperty<Int>): ObservableProperty<Float> =
    other.derive { a -> this * a }

@JvmName("floatTimesLong")
operator fun ObservableProperty<Float>.times(other: ObservableProperty<Long>): ObservableProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("floatTimesLongP")
operator fun ObservableProperty<Float>.times(other: Long): ObservableProperty<Float> =
    derive { a -> a * other }

@JvmName("PFloatTimesLong")
operator fun Float.times(other: ObservableProperty<Long>): ObservableProperty<Float> =
    other.derive { a -> this * a }

@JvmName("floatTimesFloat")
operator fun ObservableProperty<Float>.times(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("floatTimesFloatP")
operator fun ObservableProperty<Float>.times(other: Float): ObservableProperty<Float> =
    derive { a -> a * other }

@JvmName("PFloatTimesFloat")
operator fun Float.times(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this * a }

@JvmName("floatTimesDouble")
operator fun ObservableProperty<Float>.times(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("floatTimesDoubleP")
operator fun ObservableProperty<Float>.times(other: Double): ObservableProperty<Double> =
    derive { a -> a * other }

@JvmName("PFloatTimesDouble")
operator fun Float.times(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this * a }

@JvmName("floatDivByte")
operator fun ObservableProperty<Float>.div(other: ObservableProperty<Byte>): ObservableProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("floatDivByteP")
operator fun ObservableProperty<Float>.div(other: Byte): ObservableProperty<Float> =
    derive { a -> a / other }

@JvmName("PFloatDivByte")
operator fun Float.div(other: ObservableProperty<Byte>): ObservableProperty<Float> =
    other.derive { a -> this / a }

@JvmName("floatDivShort")
operator fun ObservableProperty<Float>.div(other: ObservableProperty<Short>): ObservableProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("floatDivShortP")
operator fun ObservableProperty<Float>.div(other: Short): ObservableProperty<Float> =
    derive { a -> a / other }

@JvmName("PFloatDivShort")
operator fun Float.div(other: ObservableProperty<Short>): ObservableProperty<Float> =
    other.derive { a -> this / a }

@JvmName("floatDivInt")
operator fun ObservableProperty<Float>.div(other: ObservableProperty<Int>): ObservableProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("floatDivIntP")
operator fun ObservableProperty<Float>.div(other: Int): ObservableProperty<Float> =
    derive { a -> a / other }

@JvmName("PFloatDivInt")
operator fun Float.div(other: ObservableProperty<Int>): ObservableProperty<Float> =
    other.derive { a -> this / a }

@JvmName("floatDivLong")
operator fun ObservableProperty<Float>.div(other: ObservableProperty<Long>): ObservableProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("floatDivLongP")
operator fun ObservableProperty<Float>.div(other: Long): ObservableProperty<Float> =
    derive { a -> a / other }

@JvmName("PFloatDivLong")
operator fun Float.div(other: ObservableProperty<Long>): ObservableProperty<Float> =
    other.derive { a -> this / a }

@JvmName("floatDivFloat")
operator fun ObservableProperty<Float>.div(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("floatDivFloatP")
operator fun ObservableProperty<Float>.div(other: Float): ObservableProperty<Float> =
    derive { a -> a / other }

@JvmName("PFloatDivFloat")
operator fun Float.div(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this / a }

@JvmName("floatDivDouble")
operator fun ObservableProperty<Float>.div(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("floatDivDoubleP")
operator fun ObservableProperty<Float>.div(other: Double): ObservableProperty<Double> =
    derive { a -> a / other }

@JvmName("PFloatDivDouble")
operator fun Float.div(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this / a }

@JvmName("floatRemByte")
operator fun ObservableProperty<Float>.rem(other: ObservableProperty<Byte>): ObservableProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("floatRemByteP")
operator fun ObservableProperty<Float>.rem(other: Byte): ObservableProperty<Float> =
    derive { a -> a % other }

@JvmName("PFloatRemByte")
operator fun Float.rem(other: ObservableProperty<Byte>): ObservableProperty<Float> =
    other.derive { a -> this % a }

@JvmName("floatRemShort")
operator fun ObservableProperty<Float>.rem(other: ObservableProperty<Short>): ObservableProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("floatRemShortP")
operator fun ObservableProperty<Float>.rem(other: Short): ObservableProperty<Float> =
    derive { a -> a % other }

@JvmName("PFloatRemShort")
operator fun Float.rem(other: ObservableProperty<Short>): ObservableProperty<Float> =
    other.derive { a -> this % a }

@JvmName("floatRemInt")
operator fun ObservableProperty<Float>.rem(other: ObservableProperty<Int>): ObservableProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("floatRemIntP")
operator fun ObservableProperty<Float>.rem(other: Int): ObservableProperty<Float> =
    derive { a -> a % other }

@JvmName("PFloatRemInt")
operator fun Float.rem(other: ObservableProperty<Int>): ObservableProperty<Float> =
    other.derive { a -> this % a }

@JvmName("floatRemLong")
operator fun ObservableProperty<Float>.rem(other: ObservableProperty<Long>): ObservableProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("floatRemLongP")
operator fun ObservableProperty<Float>.rem(other: Long): ObservableProperty<Float> =
    derive { a -> a % other }

@JvmName("PFloatRemLong")
operator fun Float.rem(other: ObservableProperty<Long>): ObservableProperty<Float> =
    other.derive { a -> this % a }

@JvmName("floatRemFloat")
operator fun ObservableProperty<Float>.rem(other: ObservableProperty<Float>): ObservableProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("floatRemFloatP")
operator fun ObservableProperty<Float>.rem(other: Float): ObservableProperty<Float> =
    derive { a -> a % other }

@JvmName("PFloatRemFloat")
operator fun Float.rem(other: ObservableProperty<Float>): ObservableProperty<Float> =
    other.derive { a -> this % a }

@JvmName("floatRemDouble")
operator fun ObservableProperty<Float>.rem(other: ObservableProperty<Double>): ObservableProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("floatRemDoubleP")
operator fun ObservableProperty<Float>.rem(other: Double): ObservableProperty<Double> =
    derive { a -> a % other }

@JvmName("PFloatRemDouble")
operator fun Float.rem(other: ObservableProperty<Double>): ObservableProperty<Double> =
    other.derive { a -> this % a }

@JvmName("floatUnaryPlus")
operator fun ObservableProperty<Float>.unaryPlus(): ObservableProperty<Float> = this

@JvmName("floatUnaryMinus")
operator fun ObservableProperty<Float>.unaryMinus(): ObservableProperty<Float> =
    derive { a -> -a.toFloat() }

@JvmName("floatToChar")
fun ObservableProperty<Float>.toChar(): ObservableProperty<Char> =
    derive { a -> a.toChar() }

@JvmName("floatToInt")
fun ObservableProperty<Float>.toInt(): ObservableProperty<Int> =
    derive { a -> a.toInt() }

@JvmName("floatToLong")
fun ObservableProperty<Float>.toLong(): ObservableProperty<Long> =
    derive { a -> a.toLong() }

@JvmName("floatToFloat")
fun ObservableProperty<Float>.toFloat(): ObservableProperty<Float> = this

@JvmName("floatToDouble")
fun ObservableProperty<Float>.toDouble(): ObservableProperty<Double> =
    derive { a -> a.toDouble() }
