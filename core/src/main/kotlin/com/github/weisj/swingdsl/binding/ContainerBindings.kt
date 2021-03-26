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
package com.github.weisj.swingdsl.binding

import com.github.weisj.swingdsl.condition.ObservableCondition
import com.github.weisj.swingdsl.condition.isEqualTo
import com.github.weisj.swingdsl.condition.not

fun <T> ObservableProperty<Array<T>>.size(): ObservableProperty<Int> = derive(Array<T>::size)
fun <T> ObservableProperty<Array<T>>.elementAt(index: Int): ObservableProperty<T> = derive { it[index] }
fun <T> ObservableProperty<Array<T>>.isEmpty(): ObservableCondition = size() isEqualTo 0
fun <T> ObservableProperty<Array<T>>.isNotEmpty(): ObservableCondition = !isEmpty()

@JvmName("toListIntArray")
fun ObservableProperty<IntArray>.toList(): ObservableProperty<List<Int>> = derive { it.toList() }

@JvmName("sizeIntArray")
fun ObservableProperty<IntArray>.size(): ObservableProperty<Int> = derive { it.size }

@JvmName("elementAtIntArray")
fun ObservableProperty<IntArray>.elementAt(index: Int): ObservableProperty<Int> = derive { it[index] }

@JvmName("isEmptyIntArray")
fun ObservableProperty<IntArray>.isEmpty(): ObservableCondition = size() isEqualTo 0

@JvmName("isNotEmptyIntArray")
fun ObservableProperty<IntArray>.isNotEmpty(): ObservableCondition = !isEmpty()

@JvmName("toListBooleanArray")
fun ObservableProperty<BooleanArray>.toList(): ObservableProperty<List<Boolean>> = derive { it.toList() }

@JvmName("sizeBooleanArray")
fun ObservableProperty<BooleanArray>.size(): ObservableProperty<Int> = derive { it.size }

@JvmName("elementAtBooleanArray")
fun ObservableProperty<BooleanArray>.elementAt(index: Int) = derive { it[index] }

@JvmName("isEmptyBooleanArray")
fun ObservableProperty<BooleanArray>.isEmpty(): ObservableCondition = size() isEqualTo 0

@JvmName("isNotEmptyBooleanArray")
fun ObservableProperty<BooleanArray>.isNotEmpty(): ObservableCondition = !isEmpty()

@JvmName("toListLongArray")
fun ObservableProperty<LongArray>.toList(): ObservableProperty<List<Long>> = derive { it.toList() }

@JvmName("sizeLongArray")
fun ObservableProperty<LongArray>.size(): ObservableProperty<Int> = derive { it.size }

@JvmName("elementAtLongArray")
fun ObservableProperty<LongArray>.elementAt(index: Int): ObservableProperty<Long> = derive { it[index] }

@JvmName("isEmptyLongArray")
fun ObservableProperty<LongArray>.isEmpty(): ObservableCondition = size() isEqualTo 0

@JvmName("isNotEmptyLongArray")
fun ObservableProperty<LongArray>.isNotEmpty(): ObservableCondition = !isEmpty()

@JvmName("toListFloatArray")
fun ObservableProperty<FloatArray>.toList(): ObservableProperty<List<Float>> = derive { it.toList() }

@JvmName("sizeFloatArray")
fun ObservableProperty<FloatArray>.size(): ObservableProperty<Int> = derive { it.size }

@JvmName("elementAtFloatArray")
fun ObservableProperty<FloatArray>.elementAt(index: Int): ObservableProperty<Float> = derive { it[index] }

@JvmName("isEmptyFloatArray")
fun ObservableProperty<FloatArray>.isEmpty(): ObservableCondition = size() isEqualTo 0

@JvmName("isNotEmptyFloatArray")
fun ObservableProperty<FloatArray>.isNotEmpty(): ObservableCondition = !isEmpty()

@JvmName("toListDoubleArray")
fun ObservableProperty<DoubleArray>.toList(): ObservableProperty<List<Double>> = derive { it.toList() }

@JvmName("sizeDoubleArray")
fun ObservableProperty<DoubleArray>.size(): ObservableProperty<Int> = derive { it.size }

@JvmName("elementAtDoubleArray")
fun ObservableProperty<DoubleArray>.elementAt(index: Int): ObservableProperty<Double> = derive { it[index] }

@JvmName("isEmptyDoubleArray")
fun ObservableProperty<DoubleArray>.isEmpty(): ObservableCondition = size() isEqualTo 0

@JvmName("isNotEmptyDoubleArray")
fun ObservableProperty<DoubleArray>.isNotEmpty(): ObservableCondition = !isEmpty()

@JvmName("toListByteArray")
fun ObservableProperty<ByteArray>.toList(): ObservableProperty<List<Byte>> = derive { it.toList() }

@JvmName("sizeByteArray")
fun ObservableProperty<ByteArray>.size(): ObservableProperty<Int> = derive { it.size }

@JvmName("elementAtByteArray")
fun ObservableProperty<ByteArray>.elementAt(index: Int): ObservableProperty<Byte> = derive { it[index] }

@JvmName("isEmptyByteArray")
fun ObservableProperty<ByteArray>.isEmpty(): ObservableCondition = size() isEqualTo 0

@JvmName("isNotEmptyByteArray")
fun ObservableProperty<ByteArray>.isNotEmpty(): ObservableCondition = !isEmpty()

@JvmName("toListCharArray")
fun ObservableProperty<CharArray>.toList(): ObservableProperty<List<Char>> = derive { it.toList() }

@JvmName("sizeCharArray")
fun ObservableProperty<CharArray>.size(): ObservableProperty<Int> = derive { it.size }

@JvmName("elementAtCharArray")
fun ObservableProperty<CharArray>.elementAt(index: Int): ObservableProperty<Char> = derive { it[index] }

@JvmName("isEmptyCharArray")
fun ObservableProperty<CharArray>.isEmpty(): ObservableCondition = size() isEqualTo 0

@JvmName("isNotEmptyCharArray")
fun ObservableProperty<CharArray>.isNotEmpty(): ObservableCondition = !isEmpty()

@JvmName("sizeofCollection")
fun <T> ObservableProperty<Collection<T>>.size(): ObservableProperty<Int> = derive { it.size }

@JvmName("isEmptyCollection")
fun <T> ObservableProperty<Collection<T>>.isEmpty(): ObservableCondition = size() isEqualTo 0

@JvmName("isNotEmptyCollection")
fun <T> ObservableProperty<Collection<T>>.isNotEmpty(): ObservableCondition = !isEmpty()

fun <T> ObservableProperty<Iterable<T>>.contains(element: T): ObservableCondition =
    derive { it.contains(element) }

fun <T> ObservableProperty<Collection<T>>.containsAll(elements: Collection<T>): ObservableCondition =
    derive { it.containsAll(elements) }
