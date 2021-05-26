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

@JvmName("charComparedToChar")
infix fun ObservableProperty<Char>.comparedTo(other: ObservableProperty<Char>): ObservableProperty<Int> =
    combine(other) { a, b -> a.compareTo(b) }

@JvmName("charComparedToCharP")
infix fun ObservableProperty<Char>.comparedTo(other: Char): ObservableProperty<Int> =
    derive { a -> a.compareTo(other) }

@JvmName("PCharComparedToChar")
infix fun Char.comparedTo(other: ObservableProperty<Char>): ObservableProperty<Int> =
    other.derive { a -> this.compareTo(a) }

@JvmName("charPlusInt")
operator fun ObservableProperty<Char>.plus(other: ObservableProperty<Int>): ObservableProperty<Char> =
    combine(other) { a, b -> a + b }

@JvmName("charPlusIntP")
operator fun ObservableProperty<Char>.plus(other: Int): ObservableProperty<Char> =
    derive { a -> a + other }

@JvmName("PCharPlusInt")
operator fun Char.plus(other: ObservableProperty<Int>): ObservableProperty<Char> =
    other.derive { a -> this + a }

@JvmName("charMinusChar")
operator fun ObservableProperty<Char>.minus(other: ObservableProperty<Char>): ObservableProperty<Int> =
    combine(other) { a, b -> a - b }

@JvmName("charMinusCharP")
operator fun ObservableProperty<Char>.minus(other: Char): ObservableProperty<Int> =
    derive { a -> a - other }

@JvmName("PCharMinusChar")
operator fun Char.minus(other: ObservableProperty<Char>): ObservableProperty<Int> =
    other.derive { a -> this - a }

@JvmName("charMinusInt")
operator fun ObservableProperty<Char>.minus(other: ObservableProperty<Int>): ObservableProperty<Char> =
    combine(other) { a, b -> a - b }

@JvmName("charMinusIntP")
operator fun ObservableProperty<Char>.minus(other: Int): ObservableProperty<Char> =
    derive { a -> a - other }

@JvmName("PCharMinusInt")
operator fun Char.minus(other: ObservableProperty<Int>): ObservableProperty<Char> =
    other.derive { a -> this - a }

@JvmName("charRangeToChar")
operator fun ObservableProperty<Char>.rangeTo(other: ObservableProperty<Char>): ObservableProperty<CharRange> =
    combine(other) { a, b -> a..b }

@JvmName("charRangeToCharP")
operator fun ObservableProperty<Char>.rangeTo(other: Char): ObservableProperty<CharRange> =
    derive { a -> a..other }

@JvmName("PCharRangeToChar")
operator fun Char.rangeTo(other: ObservableProperty<Char>): ObservableProperty<CharRange> =
    other.derive { a -> this..a }

@JvmName("charToByte")
fun ObservableProperty<Char>.toByte(): ObservableProperty<Byte> =
    derive { a -> a.toByte() }

@JvmName("charToChar")
fun ObservableProperty<Char>.toChar(): ObservableProperty<Char> = this

@JvmName("charToShort")
fun ObservableProperty<Char>.toShort(): ObservableProperty<Short> =
    derive { a -> a.toShort() }

@JvmName("charToInt")
fun ObservableProperty<Char>.toInt(): ObservableProperty<Int> =
    derive { a -> a.toInt() }

@JvmName("charToLong")
fun ObservableProperty<Char>.toLong(): ObservableProperty<Long> =
    derive { a -> a.toLong() }

@JvmName("charToFloat")
fun ObservableProperty<Char>.toFloat(): ObservableProperty<Float> =
    derive { a -> a.toFloat() }

@JvmName("charToDouble")
fun ObservableProperty<Char>.toDouble(): ObservableProperty<Double> =
    derive { a -> a.toDouble() }
