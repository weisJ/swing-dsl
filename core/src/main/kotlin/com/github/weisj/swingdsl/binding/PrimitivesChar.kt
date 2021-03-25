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

@JvmName("charComparedToChar")
infix fun BoundProperty<Char>.comparedTo(other: BoundProperty<Char>): BoundProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("charComparedToCharP")
infix fun BoundProperty<Char>.comparedTo(other: Char): BoundProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PCharComparedToChar")
infix fun Char.comparedTo(other: BoundProperty<Char>): BoundProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("charPlusInt")
operator fun BoundProperty<Char>.plus(other: BoundProperty<Int>): BoundProperty<Char> =
    combine(other) { a, b -> a + b }

@JvmName("charPlusIntP")
operator fun BoundProperty<Char>.plus(other: Int): BoundProperty<Char> =
    derive { a -> a + other }

@JvmName("PCharPlusInt")
operator fun Char.plus(other: BoundProperty<Int>): BoundProperty<Char> =
    other.derive { a -> this + a }

@JvmName("charMinusChar")
operator fun BoundProperty<Char>.minus(other: BoundProperty<Char>): BoundProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("charMinusCharP")
operator fun BoundProperty<Char>.minus(other: Char): BoundProperty<Int> =
    derive { a -> a - other }

@JvmName("PCharMinusChar")
operator fun Char.minus(other: BoundProperty<Char>): BoundProperty<Int> =
    other.derive { a -> this - a }

@JvmName("charMinusInt")
operator fun BoundProperty<Char>.minus(other: BoundProperty<Int>): BoundProperty<Char> =
    combine(other) { a, b -> a - b }

@JvmName("charMinusIntP")
operator fun BoundProperty<Char>.minus(other: Int): BoundProperty<Char> =
    derive { a -> a - other }

@JvmName("PCharMinusInt")
operator fun Char.minus(other: BoundProperty<Int>): BoundProperty<Char> =
    other.derive { a -> this - a }

@JvmName("charRangeToChar")
operator fun BoundProperty<Char>.rangeTo(other: BoundProperty<Char>): BoundProperty<CharRange> =
    combine(other) { a, b -> a..b }

@JvmName("charRangeToCharP")
operator fun BoundProperty<Char>.rangeTo(other: Char): BoundProperty<CharRange> =
    derive { a -> a..other }

@JvmName("PCharRangeToChar")
operator fun Char.rangeTo(other: BoundProperty<Char>): BoundProperty<CharRange> =
    other.derive { a -> this..a }

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
