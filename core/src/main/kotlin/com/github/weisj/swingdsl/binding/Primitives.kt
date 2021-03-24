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

package com.github.weisj.swingdsl.binding

@JvmName("byteComparedToByte")
infix fun BoundProperty<Byte>.comparedTo(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("byteLessThanByte")
infix fun BoundProperty<Byte>.lessThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("byteLessThanOrEqualsByte")
infix fun BoundProperty<Byte>.lessThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("byteGreaterThanByte")
infix fun BoundProperty<Byte>.greaterThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("byteGreaterThanOrEqualsByte")
infix fun BoundProperty<Byte>.greaterThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("byteComparedToShort")
infix fun BoundProperty<Byte>.comparedTo(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("byteLessThanShort")
infix fun BoundProperty<Byte>.lessThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("byteLessThanOrEqualsShort")
infix fun BoundProperty<Byte>.lessThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("byteGreaterThanShort")
infix fun BoundProperty<Byte>.greaterThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("byteGreaterThanOrEqualsShort")
infix fun BoundProperty<Byte>.greaterThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("byteComparedToInt")
infix fun BoundProperty<Byte>.comparedTo(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("byteLessThanInt")
infix fun BoundProperty<Byte>.lessThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("byteLessThanOrEqualsInt")
infix fun BoundProperty<Byte>.lessThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("byteGreaterThanInt")
infix fun BoundProperty<Byte>.greaterThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("byteGreaterThanOrEqualsInt")
infix fun BoundProperty<Byte>.greaterThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("byteComparedToLong")
infix fun BoundProperty<Byte>.comparedTo(other: BoundProperty<Long>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("byteLessThanLong")
infix fun BoundProperty<Byte>.lessThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("byteLessThanOrEqualsLong")
infix fun BoundProperty<Byte>.lessThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("byteGreaterThanLong")
infix fun BoundProperty<Byte>.greaterThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("byteGreaterThanOrEqualsLong")
infix fun BoundProperty<Byte>.greaterThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("byteComparedToFloat")
infix fun BoundProperty<Byte>.comparedTo(other: BoundProperty<Float>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("byteLessThanFloat")
infix fun BoundProperty<Byte>.lessThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("byteLessThanOrEqualsFloat")
infix fun BoundProperty<Byte>.lessThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("byteGreaterThanFloat")
infix fun BoundProperty<Byte>.greaterThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("byteGreaterThanOrEqualsFloat")
infix fun BoundProperty<Byte>.greaterThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("byteComparedToDouble")
infix fun BoundProperty<Byte>.comparedTo(other: BoundProperty<Double>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("byteLessThanDouble")
infix fun BoundProperty<Byte>.lessThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("byteLessThanOrEqualsDouble")
infix fun BoundProperty<Byte>.lessThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("byteGreaterThanDouble")
infix fun BoundProperty<Byte>.greaterThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("byteGreaterThanOrEqualsDouble")
infix fun BoundProperty<Byte>.greaterThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("bytePlusByte")
operator fun BoundProperty<Byte>.plus(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("bytePlusShort")
operator fun BoundProperty<Byte>.plus(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("bytePlusInt")
operator fun BoundProperty<Byte>.plus(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("bytePlusLong")
operator fun BoundProperty<Byte>.plus(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a + b }

@JvmName("bytePlusFloat")
operator fun BoundProperty<Byte>.plus(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("bytePlusDouble")
operator fun BoundProperty<Byte>.plus(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("byteMinusByte")
operator fun BoundProperty<Byte>.minus(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("byteMinusShort")
operator fun BoundProperty<Byte>.minus(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("byteMinusInt")
operator fun BoundProperty<Byte>.minus(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("byteMinusLong")
operator fun BoundProperty<Byte>.minus(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a - b }

@JvmName("byteMinusFloat")
operator fun BoundProperty<Byte>.minus(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("byteMinusDouble")
operator fun BoundProperty<Byte>.minus(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("byteTimesByte")
operator fun BoundProperty<Byte>.times(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("byteTimesShort")
operator fun BoundProperty<Byte>.times(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("byteTimesInt")
operator fun BoundProperty<Byte>.times(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("byteTimesLong")
operator fun BoundProperty<Byte>.times(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a * b }

@JvmName("byteTimesFloat")
operator fun BoundProperty<Byte>.times(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("byteTimesDouble")
operator fun BoundProperty<Byte>.times(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("byteDivByte")
operator fun BoundProperty<Byte>.div(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("byteDivShort")
operator fun BoundProperty<Byte>.div(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("byteDivInt")
operator fun BoundProperty<Byte>.div(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("byteDivLong")
operator fun BoundProperty<Byte>.div(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a / b }

@JvmName("byteDivFloat")
operator fun BoundProperty<Byte>.div(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("byteDivDouble")
operator fun BoundProperty<Byte>.div(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("byteRemByte")
operator fun BoundProperty<Byte>.rem(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("byteRemShort")
operator fun BoundProperty<Byte>.rem(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("byteRemInt")
operator fun BoundProperty<Byte>.rem(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("byteRemLong")
operator fun BoundProperty<Byte>.rem(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a % b }

@JvmName("byteRemFloat")
operator fun BoundProperty<Byte>.rem(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("byteRemDouble")
operator fun BoundProperty<Byte>.rem(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("byteUnaryPlus")
operator fun BoundProperty<Byte>.unaryPlus(): BoundProperty<Int> =
    derive { a -> a.toInt() }

@JvmName("byteUnaryMinus")
operator fun BoundProperty<Byte>.unaryMinus(): BoundProperty<Int> =
    derive { a -> -a.toInt() }

@JvmName("byteRangeToByte")
operator fun BoundProperty<Byte>.rangeTo(other: BoundProperty<Byte>): BoundProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("byteRangeToShort")
operator fun BoundProperty<Byte>.rangeTo(other: BoundProperty<Short>): BoundProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("byteRangeToInt")
operator fun BoundProperty<Byte>.rangeTo(other: BoundProperty<Int>): BoundProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("byteRangeToLong")
operator fun BoundProperty<Byte>.rangeTo(other: BoundProperty<Long>): BoundProperty<LongRange> =
    combine(other) { a, b -> a..b }

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

@JvmName("shortComparedToByte")
infix fun BoundProperty<Short>.comparedTo(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("shortLessThanByte")
infix fun BoundProperty<Short>.lessThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("shortLessThanOrEqualsByte")
infix fun BoundProperty<Short>.lessThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("shortGreaterThanByte")
infix fun BoundProperty<Short>.greaterThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("shortGreaterThanOrEqualsByte")
infix fun BoundProperty<Short>.greaterThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("shortComparedToShort")
infix fun BoundProperty<Short>.comparedTo(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("shortLessThanShort")
infix fun BoundProperty<Short>.lessThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("shortLessThanOrEqualsShort")
infix fun BoundProperty<Short>.lessThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("shortGreaterThanShort")
infix fun BoundProperty<Short>.greaterThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("shortGreaterThanOrEqualsShort")
infix fun BoundProperty<Short>.greaterThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("shortComparedToInt")
infix fun BoundProperty<Short>.comparedTo(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("shortLessThanInt")
infix fun BoundProperty<Short>.lessThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("shortLessThanOrEqualsInt")
infix fun BoundProperty<Short>.lessThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("shortGreaterThanInt")
infix fun BoundProperty<Short>.greaterThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("shortGreaterThanOrEqualsInt")
infix fun BoundProperty<Short>.greaterThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("shortComparedToLong")
infix fun BoundProperty<Short>.comparedTo(other: BoundProperty<Long>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("shortLessThanLong")
infix fun BoundProperty<Short>.lessThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("shortLessThanOrEqualsLong")
infix fun BoundProperty<Short>.lessThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("shortGreaterThanLong")
infix fun BoundProperty<Short>.greaterThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("shortGreaterThanOrEqualsLong")
infix fun BoundProperty<Short>.greaterThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("shortComparedToFloat")
infix fun BoundProperty<Short>.comparedTo(other: BoundProperty<Float>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("shortLessThanFloat")
infix fun BoundProperty<Short>.lessThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("shortLessThanOrEqualsFloat")
infix fun BoundProperty<Short>.lessThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("shortGreaterThanFloat")
infix fun BoundProperty<Short>.greaterThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("shortGreaterThanOrEqualsFloat")
infix fun BoundProperty<Short>.greaterThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("shortComparedToDouble")
infix fun BoundProperty<Short>.comparedTo(other: BoundProperty<Double>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("shortLessThanDouble")
infix fun BoundProperty<Short>.lessThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("shortLessThanOrEqualsDouble")
infix fun BoundProperty<Short>.lessThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("shortGreaterThanDouble")
infix fun BoundProperty<Short>.greaterThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("shortGreaterThanOrEqualsDouble")
infix fun BoundProperty<Short>.greaterThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("shortPlusByte")
operator fun BoundProperty<Short>.plus(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("shortPlusShort")
operator fun BoundProperty<Short>.plus(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("shortPlusInt")
operator fun BoundProperty<Short>.plus(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("shortPlusLong")
operator fun BoundProperty<Short>.plus(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a + b }

@JvmName("shortPlusFloat")
operator fun BoundProperty<Short>.plus(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("shortPlusDouble")
operator fun BoundProperty<Short>.plus(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("shortMinusByte")
operator fun BoundProperty<Short>.minus(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("shortMinusShort")
operator fun BoundProperty<Short>.minus(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("shortMinusInt")
operator fun BoundProperty<Short>.minus(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("shortMinusLong")
operator fun BoundProperty<Short>.minus(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a - b }

@JvmName("shortMinusFloat")
operator fun BoundProperty<Short>.minus(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("shortMinusDouble")
operator fun BoundProperty<Short>.minus(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("shortTimesByte")
operator fun BoundProperty<Short>.times(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("shortTimesShort")
operator fun BoundProperty<Short>.times(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("shortTimesInt")
operator fun BoundProperty<Short>.times(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("shortTimesLong")
operator fun BoundProperty<Short>.times(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a * b }

@JvmName("shortTimesFloat")
operator fun BoundProperty<Short>.times(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("shortTimesDouble")
operator fun BoundProperty<Short>.times(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("shortDivByte")
operator fun BoundProperty<Short>.div(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("shortDivShort")
operator fun BoundProperty<Short>.div(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("shortDivInt")
operator fun BoundProperty<Short>.div(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("shortDivLong")
operator fun BoundProperty<Short>.div(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a / b }

@JvmName("shortDivFloat")
operator fun BoundProperty<Short>.div(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("shortDivDouble")
operator fun BoundProperty<Short>.div(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("shortRemByte")
operator fun BoundProperty<Short>.rem(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("shortRemShort")
operator fun BoundProperty<Short>.rem(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("shortRemInt")
operator fun BoundProperty<Short>.rem(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("shortRemLong")
operator fun BoundProperty<Short>.rem(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a % b }

@JvmName("shortRemFloat")
operator fun BoundProperty<Short>.rem(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("shortRemDouble")
operator fun BoundProperty<Short>.rem(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("shortUnaryPlus")
operator fun BoundProperty<Short>.unaryPlus(): BoundProperty<Int> =
    derive { a -> a.toInt() }

@JvmName("shortUnaryMinus")
operator fun BoundProperty<Short>.unaryMinus(): BoundProperty<Int> =
    derive { a -> -a.toInt() }

@JvmName("shortRangeToByte")
operator fun BoundProperty<Short>.rangeTo(other: BoundProperty<Byte>): BoundProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("shortRangeToShort")
operator fun BoundProperty<Short>.rangeTo(other: BoundProperty<Short>): BoundProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("shortRangeToInt")
operator fun BoundProperty<Short>.rangeTo(other: BoundProperty<Int>): BoundProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("shortRangeToLong")
operator fun BoundProperty<Short>.rangeTo(other: BoundProperty<Long>): BoundProperty<LongRange> =
    combine(other) { a, b -> a..b }

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

@JvmName("intComparedToByte")
infix fun BoundProperty<Int>.comparedTo(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("intLessThanByte")
infix fun BoundProperty<Int>.lessThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("intLessThanOrEqualsByte")
infix fun BoundProperty<Int>.lessThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("intGreaterThanByte")
infix fun BoundProperty<Int>.greaterThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("intGreaterThanOrEqualsByte")
infix fun BoundProperty<Int>.greaterThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("intComparedToShort")
infix fun BoundProperty<Int>.comparedTo(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("intLessThanShort")
infix fun BoundProperty<Int>.lessThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("intLessThanOrEqualsShort")
infix fun BoundProperty<Int>.lessThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("intGreaterThanShort")
infix fun BoundProperty<Int>.greaterThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("intGreaterThanOrEqualsShort")
infix fun BoundProperty<Int>.greaterThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("intComparedToInt")
infix fun BoundProperty<Int>.comparedTo(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("intLessThanInt")
infix fun BoundProperty<Int>.lessThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("intLessThanOrEqualsInt")
infix fun BoundProperty<Int>.lessThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("intGreaterThanInt")
infix fun BoundProperty<Int>.greaterThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("intGreaterThanOrEqualsInt")
infix fun BoundProperty<Int>.greaterThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("intComparedToLong")
infix fun BoundProperty<Int>.comparedTo(other: BoundProperty<Long>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("intLessThanLong")
infix fun BoundProperty<Int>.lessThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("intLessThanOrEqualsLong")
infix fun BoundProperty<Int>.lessThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("intGreaterThanLong")
infix fun BoundProperty<Int>.greaterThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("intGreaterThanOrEqualsLong")
infix fun BoundProperty<Int>.greaterThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("intComparedToFloat")
infix fun BoundProperty<Int>.comparedTo(other: BoundProperty<Float>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("intLessThanFloat")
infix fun BoundProperty<Int>.lessThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("intLessThanOrEqualsFloat")
infix fun BoundProperty<Int>.lessThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("intGreaterThanFloat")
infix fun BoundProperty<Int>.greaterThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("intGreaterThanOrEqualsFloat")
infix fun BoundProperty<Int>.greaterThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("intComparedToDouble")
infix fun BoundProperty<Int>.comparedTo(other: BoundProperty<Double>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("intLessThanDouble")
infix fun BoundProperty<Int>.lessThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("intLessThanOrEqualsDouble")
infix fun BoundProperty<Int>.lessThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("intGreaterThanDouble")
infix fun BoundProperty<Int>.greaterThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("intGreaterThanOrEqualsDouble")
infix fun BoundProperty<Int>.greaterThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("intPlusByte")
operator fun BoundProperty<Int>.plus(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("intPlusShort")
operator fun BoundProperty<Int>.plus(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("intPlusInt")
operator fun BoundProperty<Int>.plus(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a + b }

@JvmName("intPlusLong")
operator fun BoundProperty<Int>.plus(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a + b }

@JvmName("intPlusFloat")
operator fun BoundProperty<Int>.plus(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("intPlusDouble")
operator fun BoundProperty<Int>.plus(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("intMinusByte")
operator fun BoundProperty<Int>.minus(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("intMinusShort")
operator fun BoundProperty<Int>.minus(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("intMinusInt")
operator fun BoundProperty<Int>.minus(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("intMinusLong")
operator fun BoundProperty<Int>.minus(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a - b }

@JvmName("intMinusFloat")
operator fun BoundProperty<Int>.minus(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("intMinusDouble")
operator fun BoundProperty<Int>.minus(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("intTimesByte")
operator fun BoundProperty<Int>.times(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("intTimesShort")
operator fun BoundProperty<Int>.times(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("intTimesInt")
operator fun BoundProperty<Int>.times(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a * b }

@JvmName("intTimesLong")
operator fun BoundProperty<Int>.times(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a * b }

@JvmName("intTimesFloat")
operator fun BoundProperty<Int>.times(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("intTimesDouble")
operator fun BoundProperty<Int>.times(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("intDivByte")
operator fun BoundProperty<Int>.div(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("intDivShort")
operator fun BoundProperty<Int>.div(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("intDivInt")
operator fun BoundProperty<Int>.div(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a / b }

@JvmName("intDivLong")
operator fun BoundProperty<Int>.div(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a / b }

@JvmName("intDivFloat")
operator fun BoundProperty<Int>.div(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("intDivDouble")
operator fun BoundProperty<Int>.div(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("intRemByte")
operator fun BoundProperty<Int>.rem(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("intRemShort")
operator fun BoundProperty<Int>.rem(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("intRemInt")
operator fun BoundProperty<Int>.rem(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a % b }

@JvmName("intRemLong")
operator fun BoundProperty<Int>.rem(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a % b }

@JvmName("intRemFloat")
operator fun BoundProperty<Int>.rem(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("intRemDouble")
operator fun BoundProperty<Int>.rem(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("intUnaryPlus")
operator fun BoundProperty<Int>.unaryPlus(): BoundProperty<Int> = this

@JvmName("intUnaryMinus")
operator fun BoundProperty<Int>.unaryMinus(): BoundProperty<Int> =
    derive { a -> -a.toInt() }

@JvmName("intRangeToByte")
operator fun BoundProperty<Int>.rangeTo(other: BoundProperty<Byte>): BoundProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("intRangeToShort")
operator fun BoundProperty<Int>.rangeTo(other: BoundProperty<Short>): BoundProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("intRangeToInt")
operator fun BoundProperty<Int>.rangeTo(other: BoundProperty<Int>): BoundProperty<IntRange> =
    combine(other) { a, b -> a..b }

@JvmName("intRangeToLong")
operator fun BoundProperty<Int>.rangeTo(other: BoundProperty<Long>): BoundProperty<LongRange> =
    combine(other) { a, b -> a..b }

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

@JvmName("longComparedToByte")
infix fun BoundProperty<Long>.comparedTo(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("longLessThanByte")
infix fun BoundProperty<Long>.lessThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("longLessThanOrEqualsByte")
infix fun BoundProperty<Long>.lessThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("longGreaterThanByte")
infix fun BoundProperty<Long>.greaterThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("longGreaterThanOrEqualsByte")
infix fun BoundProperty<Long>.greaterThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("longComparedToShort")
infix fun BoundProperty<Long>.comparedTo(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("longLessThanShort")
infix fun BoundProperty<Long>.lessThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("longLessThanOrEqualsShort")
infix fun BoundProperty<Long>.lessThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("longGreaterThanShort")
infix fun BoundProperty<Long>.greaterThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("longGreaterThanOrEqualsShort")
infix fun BoundProperty<Long>.greaterThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("longComparedToInt")
infix fun BoundProperty<Long>.comparedTo(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("longLessThanInt")
infix fun BoundProperty<Long>.lessThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("longLessThanOrEqualsInt")
infix fun BoundProperty<Long>.lessThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("longGreaterThanInt")
infix fun BoundProperty<Long>.greaterThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("longGreaterThanOrEqualsInt")
infix fun BoundProperty<Long>.greaterThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("longComparedToLong")
infix fun BoundProperty<Long>.comparedTo(other: BoundProperty<Long>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("longLessThanLong")
infix fun BoundProperty<Long>.lessThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("longLessThanOrEqualsLong")
infix fun BoundProperty<Long>.lessThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("longGreaterThanLong")
infix fun BoundProperty<Long>.greaterThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("longGreaterThanOrEqualsLong")
infix fun BoundProperty<Long>.greaterThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("longComparedToFloat")
infix fun BoundProperty<Long>.comparedTo(other: BoundProperty<Float>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("longLessThanFloat")
infix fun BoundProperty<Long>.lessThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("longLessThanOrEqualsFloat")
infix fun BoundProperty<Long>.lessThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("longGreaterThanFloat")
infix fun BoundProperty<Long>.greaterThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("longGreaterThanOrEqualsFloat")
infix fun BoundProperty<Long>.greaterThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("longComparedToDouble")
infix fun BoundProperty<Long>.comparedTo(other: BoundProperty<Double>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("longLessThanDouble")
infix fun BoundProperty<Long>.lessThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("longLessThanOrEqualsDouble")
infix fun BoundProperty<Long>.lessThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("longGreaterThanDouble")
infix fun BoundProperty<Long>.greaterThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("longGreaterThanOrEqualsDouble")
infix fun BoundProperty<Long>.greaterThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("longPlusByte")
operator fun BoundProperty<Long>.plus(other: BoundProperty<Byte>): BoundProperty<Long> =
    combine(other) { a, b -> a + b }

@JvmName("longPlusShort")
operator fun BoundProperty<Long>.plus(other: BoundProperty<Short>): BoundProperty<Long> =
    combine(other) { a, b -> a + b }

@JvmName("longPlusInt")
operator fun BoundProperty<Long>.plus(other: BoundProperty<Int>): BoundProperty<Long> =
    combine(other) { a, b -> a + b }

@JvmName("longPlusLong")
operator fun BoundProperty<Long>.plus(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a + b }

@JvmName("longPlusFloat")
operator fun BoundProperty<Long>.plus(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("longPlusDouble")
operator fun BoundProperty<Long>.plus(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("longMinusByte")
operator fun BoundProperty<Long>.minus(other: BoundProperty<Byte>): BoundProperty<Long> =
    combine(other) { a, b -> a - b }

@JvmName("longMinusShort")
operator fun BoundProperty<Long>.minus(other: BoundProperty<Short>): BoundProperty<Long> =
    combine(other) { a, b -> a - b }

@JvmName("longMinusInt")
operator fun BoundProperty<Long>.minus(other: BoundProperty<Int>): BoundProperty<Long> =
    combine(other) { a, b -> a - b }

@JvmName("longMinusLong")
operator fun BoundProperty<Long>.minus(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a - b }

@JvmName("longMinusFloat")
operator fun BoundProperty<Long>.minus(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("longMinusDouble")
operator fun BoundProperty<Long>.minus(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("longTimesByte")
operator fun BoundProperty<Long>.times(other: BoundProperty<Byte>): BoundProperty<Long> =
    combine(other) { a, b -> a * b }

@JvmName("longTimesShort")
operator fun BoundProperty<Long>.times(other: BoundProperty<Short>): BoundProperty<Long> =
    combine(other) { a, b -> a * b }

@JvmName("longTimesInt")
operator fun BoundProperty<Long>.times(other: BoundProperty<Int>): BoundProperty<Long> =
    combine(other) { a, b -> a * b }

@JvmName("longTimesLong")
operator fun BoundProperty<Long>.times(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a * b }

@JvmName("longTimesFloat")
operator fun BoundProperty<Long>.times(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("longTimesDouble")
operator fun BoundProperty<Long>.times(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("longDivByte")
operator fun BoundProperty<Long>.div(other: BoundProperty<Byte>): BoundProperty<Long> =
    combine(other) { a, b -> a / b }

@JvmName("longDivShort")
operator fun BoundProperty<Long>.div(other: BoundProperty<Short>): BoundProperty<Long> =
    combine(other) { a, b -> a / b }

@JvmName("longDivInt")
operator fun BoundProperty<Long>.div(other: BoundProperty<Int>): BoundProperty<Long> =
    combine(other) { a, b -> a / b }

@JvmName("longDivLong")
operator fun BoundProperty<Long>.div(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a / b }

@JvmName("longDivFloat")
operator fun BoundProperty<Long>.div(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("longDivDouble")
operator fun BoundProperty<Long>.div(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("longRemByte")
operator fun BoundProperty<Long>.rem(other: BoundProperty<Byte>): BoundProperty<Long> =
    combine(other) { a, b -> a % b }

@JvmName("longRemShort")
operator fun BoundProperty<Long>.rem(other: BoundProperty<Short>): BoundProperty<Long> =
    combine(other) { a, b -> a % b }

@JvmName("longRemInt")
operator fun BoundProperty<Long>.rem(other: BoundProperty<Int>): BoundProperty<Long> =
    combine(other) { a, b -> a % b }

@JvmName("longRemLong")
operator fun BoundProperty<Long>.rem(other: BoundProperty<Long>): BoundProperty<Long> =
    combine(other) { a, b -> a % b }

@JvmName("longRemFloat")
operator fun BoundProperty<Long>.rem(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("longRemDouble")
operator fun BoundProperty<Long>.rem(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("longUnaryPlus")
operator fun BoundProperty<Long>.unaryPlus(): BoundProperty<Long> = this

@JvmName("longUnaryMinus")
operator fun BoundProperty<Long>.unaryMinus(): BoundProperty<Long> =
    derive { a -> -a.toLong() }

@JvmName("longRangeToByte")
operator fun BoundProperty<Long>.rangeTo(other: BoundProperty<Byte>): BoundProperty<LongRange> =
    combine(other) { a, b -> a..b }

@JvmName("longRangeToShort")
operator fun BoundProperty<Long>.rangeTo(other: BoundProperty<Short>): BoundProperty<LongRange> =
    combine(other) { a, b -> a..b }

@JvmName("longRangeToInt")
operator fun BoundProperty<Long>.rangeTo(other: BoundProperty<Int>): BoundProperty<LongRange> =
    combine(other) { a, b -> a..b }

@JvmName("longRangeToLong")
operator fun BoundProperty<Long>.rangeTo(other: BoundProperty<Long>): BoundProperty<LongRange> =
    combine(other) { a, b -> a..b }

@JvmName("longToByte")
fun BoundProperty<Long>.toByte(): BoundProperty<Byte> =
    derive { a -> a.toByte() }

@JvmName("longToChar")
fun BoundProperty<Long>.toChar(): BoundProperty<Char> =
    derive { a -> a.toChar() }

@JvmName("longToShort")
fun BoundProperty<Long>.toShort(): BoundProperty<Short> =
    derive { a -> a.toShort() }

@JvmName("longToInt")
fun BoundProperty<Long>.toInt(): BoundProperty<Int> =
    derive { a -> a.toInt() }

@JvmName("longToLong")
fun BoundProperty<Long>.toLong(): BoundProperty<Long> = this

@JvmName("longToFloat")
fun BoundProperty<Long>.toFloat(): BoundProperty<Float> =
    derive { a -> a.toFloat() }

@JvmName("longToDouble")
fun BoundProperty<Long>.toDouble(): BoundProperty<Double> =
    derive { a -> a.toDouble() }

@JvmName("floatComparedToByte")
infix fun BoundProperty<Float>.comparedTo(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("floatLessThanByte")
infix fun BoundProperty<Float>.lessThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("floatLessThanOrEqualsByte")
infix fun BoundProperty<Float>.lessThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("floatGreaterThanByte")
infix fun BoundProperty<Float>.greaterThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("floatGreaterThanOrEqualsByte")
infix fun BoundProperty<Float>.greaterThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("floatComparedToShort")
infix fun BoundProperty<Float>.comparedTo(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("floatLessThanShort")
infix fun BoundProperty<Float>.lessThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("floatLessThanOrEqualsShort")
infix fun BoundProperty<Float>.lessThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("floatGreaterThanShort")
infix fun BoundProperty<Float>.greaterThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("floatGreaterThanOrEqualsShort")
infix fun BoundProperty<Float>.greaterThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("floatComparedToInt")
infix fun BoundProperty<Float>.comparedTo(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("floatLessThanInt")
infix fun BoundProperty<Float>.lessThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("floatLessThanOrEqualsInt")
infix fun BoundProperty<Float>.lessThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("floatGreaterThanInt")
infix fun BoundProperty<Float>.greaterThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("floatGreaterThanOrEqualsInt")
infix fun BoundProperty<Float>.greaterThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("floatComparedToLong")
infix fun BoundProperty<Float>.comparedTo(other: BoundProperty<Long>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("floatLessThanLong")
infix fun BoundProperty<Float>.lessThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("floatLessThanOrEqualsLong")
infix fun BoundProperty<Float>.lessThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("floatGreaterThanLong")
infix fun BoundProperty<Float>.greaterThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("floatGreaterThanOrEqualsLong")
infix fun BoundProperty<Float>.greaterThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("floatComparedToFloat")
infix fun BoundProperty<Float>.comparedTo(other: BoundProperty<Float>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("floatLessThanFloat")
infix fun BoundProperty<Float>.lessThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("floatLessThanOrEqualsFloat")
infix fun BoundProperty<Float>.lessThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("floatGreaterThanFloat")
infix fun BoundProperty<Float>.greaterThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("floatGreaterThanOrEqualsFloat")
infix fun BoundProperty<Float>.greaterThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("floatComparedToDouble")
infix fun BoundProperty<Float>.comparedTo(other: BoundProperty<Double>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("floatLessThanDouble")
infix fun BoundProperty<Float>.lessThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("floatLessThanOrEqualsDouble")
infix fun BoundProperty<Float>.lessThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("floatGreaterThanDouble")
infix fun BoundProperty<Float>.greaterThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("floatGreaterThanOrEqualsDouble")
infix fun BoundProperty<Float>.greaterThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("floatPlusByte")
operator fun BoundProperty<Float>.plus(other: BoundProperty<Byte>): BoundProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("floatPlusShort")
operator fun BoundProperty<Float>.plus(other: BoundProperty<Short>): BoundProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("floatPlusInt")
operator fun BoundProperty<Float>.plus(other: BoundProperty<Int>): BoundProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("floatPlusLong")
operator fun BoundProperty<Float>.plus(other: BoundProperty<Long>): BoundProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("floatPlusFloat")
operator fun BoundProperty<Float>.plus(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a + b }

@JvmName("floatPlusDouble")
operator fun BoundProperty<Float>.plus(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("floatMinusByte")
operator fun BoundProperty<Float>.minus(other: BoundProperty<Byte>): BoundProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("floatMinusShort")
operator fun BoundProperty<Float>.minus(other: BoundProperty<Short>): BoundProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("floatMinusInt")
operator fun BoundProperty<Float>.minus(other: BoundProperty<Int>): BoundProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("floatMinusLong")
operator fun BoundProperty<Float>.minus(other: BoundProperty<Long>): BoundProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("floatMinusFloat")
operator fun BoundProperty<Float>.minus(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a - b }

@JvmName("floatMinusDouble")
operator fun BoundProperty<Float>.minus(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("floatTimesByte")
operator fun BoundProperty<Float>.times(other: BoundProperty<Byte>): BoundProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("floatTimesShort")
operator fun BoundProperty<Float>.times(other: BoundProperty<Short>): BoundProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("floatTimesInt")
operator fun BoundProperty<Float>.times(other: BoundProperty<Int>): BoundProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("floatTimesLong")
operator fun BoundProperty<Float>.times(other: BoundProperty<Long>): BoundProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("floatTimesFloat")
operator fun BoundProperty<Float>.times(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a * b }

@JvmName("floatTimesDouble")
operator fun BoundProperty<Float>.times(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("floatDivByte")
operator fun BoundProperty<Float>.div(other: BoundProperty<Byte>): BoundProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("floatDivShort")
operator fun BoundProperty<Float>.div(other: BoundProperty<Short>): BoundProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("floatDivInt")
operator fun BoundProperty<Float>.div(other: BoundProperty<Int>): BoundProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("floatDivLong")
operator fun BoundProperty<Float>.div(other: BoundProperty<Long>): BoundProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("floatDivFloat")
operator fun BoundProperty<Float>.div(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a / b }

@JvmName("floatDivDouble")
operator fun BoundProperty<Float>.div(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("floatRemByte")
operator fun BoundProperty<Float>.rem(other: BoundProperty<Byte>): BoundProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("floatRemShort")
operator fun BoundProperty<Float>.rem(other: BoundProperty<Short>): BoundProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("floatRemInt")
operator fun BoundProperty<Float>.rem(other: BoundProperty<Int>): BoundProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("floatRemLong")
operator fun BoundProperty<Float>.rem(other: BoundProperty<Long>): BoundProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("floatRemFloat")
operator fun BoundProperty<Float>.rem(other: BoundProperty<Float>): BoundProperty<Float> =
    combine(other) { a, b -> a % b }

@JvmName("floatRemDouble")
operator fun BoundProperty<Float>.rem(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a % b }

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

@JvmName("doubleComparedToByte")
infix fun BoundProperty<Double>.comparedTo(other: BoundProperty<Byte>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("doubleLessThanByte")
infix fun BoundProperty<Double>.lessThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("doubleLessThanOrEqualsByte")
infix fun BoundProperty<Double>.lessThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("doubleGreaterThanByte")
infix fun BoundProperty<Double>.greaterThan(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("doubleGreaterThanOrEqualsByte")
infix fun BoundProperty<Double>.greaterThanOrEquals(other: BoundProperty<Byte>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("doubleComparedToShort")
infix fun BoundProperty<Double>.comparedTo(other: BoundProperty<Short>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("doubleLessThanShort")
infix fun BoundProperty<Double>.lessThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("doubleLessThanOrEqualsShort")
infix fun BoundProperty<Double>.lessThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("doubleGreaterThanShort")
infix fun BoundProperty<Double>.greaterThan(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("doubleGreaterThanOrEqualsShort")
infix fun BoundProperty<Double>.greaterThanOrEquals(other: BoundProperty<Short>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("doubleComparedToInt")
infix fun BoundProperty<Double>.comparedTo(other: BoundProperty<Int>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("doubleLessThanInt")
infix fun BoundProperty<Double>.lessThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("doubleLessThanOrEqualsInt")
infix fun BoundProperty<Double>.lessThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("doubleGreaterThanInt")
infix fun BoundProperty<Double>.greaterThan(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("doubleGreaterThanOrEqualsInt")
infix fun BoundProperty<Double>.greaterThanOrEquals(other: BoundProperty<Int>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("doubleComparedToLong")
infix fun BoundProperty<Double>.comparedTo(other: BoundProperty<Long>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("doubleLessThanLong")
infix fun BoundProperty<Double>.lessThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("doubleLessThanOrEqualsLong")
infix fun BoundProperty<Double>.lessThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("doubleGreaterThanLong")
infix fun BoundProperty<Double>.greaterThan(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("doubleGreaterThanOrEqualsLong")
infix fun BoundProperty<Double>.greaterThanOrEquals(other: BoundProperty<Long>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("doubleComparedToFloat")
infix fun BoundProperty<Double>.comparedTo(other: BoundProperty<Float>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("doubleLessThanFloat")
infix fun BoundProperty<Double>.lessThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("doubleLessThanOrEqualsFloat")
infix fun BoundProperty<Double>.lessThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("doubleGreaterThanFloat")
infix fun BoundProperty<Double>.greaterThan(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("doubleGreaterThanOrEqualsFloat")
infix fun BoundProperty<Double>.greaterThanOrEquals(other: BoundProperty<Float>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("doubleComparedToDouble")
infix fun BoundProperty<Double>.comparedTo(other: BoundProperty<Double>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("doubleLessThanDouble")
infix fun BoundProperty<Double>.lessThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a < b }

@JvmName("doubleLessThanOrEqualsDouble")
infix fun BoundProperty<Double>.lessThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a <= b }

@JvmName("doubleGreaterThanDouble")
infix fun BoundProperty<Double>.greaterThan(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a > b }

@JvmName("doubleGreaterThanOrEqualsDouble")
infix fun BoundProperty<Double>.greaterThanOrEquals(other: BoundProperty<Double>): BoundProperty<Boolean> =
    combine(other) { a, b -> a >= b }

@JvmName("doublePlusByte")
operator fun BoundProperty<Double>.plus(other: BoundProperty<Byte>): BoundProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("doublePlusShort")
operator fun BoundProperty<Double>.plus(other: BoundProperty<Short>): BoundProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("doublePlusInt")
operator fun BoundProperty<Double>.plus(other: BoundProperty<Int>): BoundProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("doublePlusLong")
operator fun BoundProperty<Double>.plus(other: BoundProperty<Long>): BoundProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("doublePlusFloat")
operator fun BoundProperty<Double>.plus(other: BoundProperty<Float>): BoundProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("doublePlusDouble")
operator fun BoundProperty<Double>.plus(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a + b }

@JvmName("doubleMinusByte")
operator fun BoundProperty<Double>.minus(other: BoundProperty<Byte>): BoundProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("doubleMinusShort")
operator fun BoundProperty<Double>.minus(other: BoundProperty<Short>): BoundProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("doubleMinusInt")
operator fun BoundProperty<Double>.minus(other: BoundProperty<Int>): BoundProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("doubleMinusLong")
operator fun BoundProperty<Double>.minus(other: BoundProperty<Long>): BoundProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("doubleMinusFloat")
operator fun BoundProperty<Double>.minus(other: BoundProperty<Float>): BoundProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("doubleMinusDouble")
operator fun BoundProperty<Double>.minus(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a - b }

@JvmName("doubleTimesByte")
operator fun BoundProperty<Double>.times(other: BoundProperty<Byte>): BoundProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("doubleTimesShort")
operator fun BoundProperty<Double>.times(other: BoundProperty<Short>): BoundProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("doubleTimesInt")
operator fun BoundProperty<Double>.times(other: BoundProperty<Int>): BoundProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("doubleTimesLong")
operator fun BoundProperty<Double>.times(other: BoundProperty<Long>): BoundProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("doubleTimesFloat")
operator fun BoundProperty<Double>.times(other: BoundProperty<Float>): BoundProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("doubleTimesDouble")
operator fun BoundProperty<Double>.times(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a * b }

@JvmName("doubleDivByte")
operator fun BoundProperty<Double>.div(other: BoundProperty<Byte>): BoundProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("doubleDivShort")
operator fun BoundProperty<Double>.div(other: BoundProperty<Short>): BoundProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("doubleDivInt")
operator fun BoundProperty<Double>.div(other: BoundProperty<Int>): BoundProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("doubleDivLong")
operator fun BoundProperty<Double>.div(other: BoundProperty<Long>): BoundProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("doubleDivFloat")
operator fun BoundProperty<Double>.div(other: BoundProperty<Float>): BoundProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("doubleDivDouble")
operator fun BoundProperty<Double>.div(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a / b }

@JvmName("doubleRemByte")
operator fun BoundProperty<Double>.rem(other: BoundProperty<Byte>): BoundProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("doubleRemShort")
operator fun BoundProperty<Double>.rem(other: BoundProperty<Short>): BoundProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("doubleRemInt")
operator fun BoundProperty<Double>.rem(other: BoundProperty<Int>): BoundProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("doubleRemLong")
operator fun BoundProperty<Double>.rem(other: BoundProperty<Long>): BoundProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("doubleRemFloat")
operator fun BoundProperty<Double>.rem(other: BoundProperty<Float>): BoundProperty<Double> =
    combine(other) { a, b -> a % b }

@JvmName("doubleRemDouble")
operator fun BoundProperty<Double>.rem(other: BoundProperty<Double>): BoundProperty<Double> =
    combine(other) { a, b -> a % b }

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

@JvmName("charComparedToChar")
infix fun BoundProperty<Char>.comparedTo(other: BoundProperty<Char>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("charPlusInt")
operator fun BoundProperty<Char>.plus(other: BoundProperty<Int>): BoundProperty<Char> =
    combine(other) { a, b -> a + b }

@JvmName("charMinusChar")
operator fun BoundProperty<Char>.minus(other: BoundProperty<Char>): BoundProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("charMinusInt")
operator fun BoundProperty<Char>.minus(other: BoundProperty<Int>): BoundProperty<Char> =
    combine(other) { a, b -> a - b }

@JvmName("charRangeToChar")
operator fun BoundProperty<Char>.rangeTo(other: BoundProperty<Char>): BoundProperty<CharRange> =
    combine(other) { a, b -> a..b }

@JvmName("charToByte")
fun BoundProperty<Char>.toByte(): BoundProperty<Byte> =
    derive { a -> a.toByte() }

@JvmName("charToChar")
fun BoundProperty<Char>.toChar(): BoundProperty<Char> = this

@JvmName("charToShort")
fun BoundProperty<Char>.toShort(): BoundProperty<Short> =
    derive { a -> a.toShort() }

@JvmName("charToInt")
fun BoundProperty<Char>.toInt(): BoundProperty<Int> =
    derive { a -> a.toInt() }

@JvmName("charToLong")
fun BoundProperty<Char>.toLong(): BoundProperty<Long> =
    derive { a -> a.toLong() }

@JvmName("charToFloat")
fun BoundProperty<Char>.toFloat(): BoundProperty<Float> =
    derive { a -> a.toFloat() }

@JvmName("charToDouble")
fun BoundProperty<Char>.toDouble(): BoundProperty<Double> =
    derive { a -> a.toDouble() }
