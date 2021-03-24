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

import com.github.weisj.swingdsl.condition.BoundCondition
import com.github.weisj.swingdsl.condition.isEqualTo
import com.github.weisj.swingdsl.condition.not

fun <T> BoundProperty<Array<T>>.size(): BoundProperty<Int> = derive(Array<T>::size)
fun <T> BoundProperty<Array<T>>.elementAt(index: Int): BoundProperty<T> = derive { it[index] }
fun <T> BoundProperty<Array<T>>.isEmpty(): BoundCondition = size() isEqualTo 0
fun <T> BoundProperty<Array<T>>.isNotEmpty(): BoundCondition = !isEmpty()

@JvmName("toListIntArray")
fun BoundProperty<IntArray>.toList(): BoundProperty<List<Int>> = derive { it.toList() }

@JvmName("sizeIntArray")
fun BoundProperty<IntArray>.size(): BoundProperty<Int> = derive { it.size }

@JvmName("elementAtIntArray")
fun BoundProperty<IntArray>.elementAt(index: Int): BoundProperty<Int> = derive { it[index] }

@JvmName("isEmptyIntArray")
fun BoundProperty<IntArray>.isEmpty(): BoundCondition = size() isEqualTo 0

@JvmName("isNotEmptyIntArray")
fun BoundProperty<IntArray>.isNotEmpty(): BoundCondition = !isEmpty()

@JvmName("toListBooleanArray")
fun BoundProperty<BooleanArray>.toList(): BoundProperty<List<Boolean>> = derive { it.toList() }

@JvmName("sizeBooleanArray")
fun BoundProperty<BooleanArray>.size(): BoundProperty<Int> = derive { it.size }

@JvmName("elementAtBooleanArray")
fun BoundProperty<BooleanArray>.elementAt(index: Int) = derive { it[index] }

@JvmName("isEmptyBooleanArray")
fun BoundProperty<BooleanArray>.isEmpty(): BoundCondition = size() isEqualTo 0

@JvmName("isNotEmptyBooleanArray")
fun BoundProperty<BooleanArray>.isNotEmpty(): BoundCondition = !isEmpty()

@JvmName("toListLongArray")
fun BoundProperty<LongArray>.toList(): BoundProperty<List<Long>> = derive { it.toList() }

@JvmName("sizeLongArray")
fun BoundProperty<LongArray>.size(): BoundProperty<Int> = derive { it.size }

@JvmName("elementAtLongArray")
fun BoundProperty<LongArray>.elementAt(index: Int): BoundProperty<Long> = derive { it[index] }

@JvmName("isEmptyLongArray")
fun BoundProperty<LongArray>.isEmpty(): BoundCondition = size() isEqualTo 0

@JvmName("isNotEmptyLongArray")
fun BoundProperty<LongArray>.isNotEmpty(): BoundCondition = !isEmpty()

@JvmName("toListFloatArray")
fun BoundProperty<FloatArray>.toList(): BoundProperty<List<Float>> = derive { it.toList() }

@JvmName("sizeFloatArray")
fun BoundProperty<FloatArray>.size(): BoundProperty<Int> = derive { it.size }

@JvmName("elementAtFloatArray")
fun BoundProperty<FloatArray>.elementAt(index: Int): BoundProperty<Float> = derive { it[index] }

@JvmName("isEmptyFloatArray")
fun BoundProperty<FloatArray>.isEmpty(): BoundCondition = size() isEqualTo 0

@JvmName("isNotEmptyFloatArray")
fun BoundProperty<FloatArray>.isNotEmpty(): BoundCondition = !isEmpty()

@JvmName("toListDoubleArray")
fun BoundProperty<DoubleArray>.toList(): BoundProperty<List<Double>> = derive { it.toList() }

@JvmName("sizeDoubleArray")
fun BoundProperty<DoubleArray>.size(): BoundProperty<Int> = derive { it.size }

@JvmName("elementAtDoubleArray")
fun BoundProperty<DoubleArray>.elementAt(index: Int): BoundProperty<Double> = derive { it[index] }

@JvmName("isEmptyDoubleArray")
fun BoundProperty<DoubleArray>.isEmpty(): BoundCondition = size() isEqualTo 0

@JvmName("isNotEmptyDoubleArray")
fun BoundProperty<DoubleArray>.isNotEmpty(): BoundCondition = !isEmpty()

@JvmName("toListByteArray")
fun BoundProperty<ByteArray>.toList(): BoundProperty<List<Byte>> = derive { it.toList() }

@JvmName("sizeByteArray")
fun BoundProperty<ByteArray>.size(): BoundProperty<Int> = derive { it.size }

@JvmName("elementAtByteArray")
fun BoundProperty<ByteArray>.elementAt(index: Int): BoundProperty<Byte> = derive { it[index] }

@JvmName("isEmptyByteArray")
fun BoundProperty<ByteArray>.isEmpty(): BoundCondition = size() isEqualTo 0

@JvmName("isNotEmptyByteArray")
fun BoundProperty<ByteArray>.isNotEmpty(): BoundCondition = !isEmpty()

@JvmName("toListCharArray")
fun BoundProperty<CharArray>.toList(): BoundProperty<List<Char>> = derive { it.toList() }

@JvmName("sizeCharArray")
fun BoundProperty<CharArray>.size(): BoundProperty<Int> = derive { it.size }

@JvmName("elementAtCharArray")
fun BoundProperty<CharArray>.elementAt(index: Int): BoundProperty<Char> = derive { it[index] }

@JvmName("isEmptyCharArray")
fun BoundProperty<CharArray>.isEmpty(): BoundCondition = size() isEqualTo 0

@JvmName("isNotEmptyCharArray")
fun BoundProperty<CharArray>.isNotEmpty(): BoundCondition = !isEmpty()

@JvmName("sizeofCollection")
fun <T> BoundProperty<Collection<T>>.size(): BoundProperty<Int> = derive { it.size }

@JvmName("isEmptyCollection")
fun <T> BoundProperty<Collection<T>>.isEmpty(): BoundCondition = size() isEqualTo 0

@JvmName("isNotEmptyCollection")
fun <T> BoundProperty<Collection<T>>.isNotEmpty(): BoundCondition = !isEmpty()

fun <T> BoundProperty<Iterable<T>>.contains(element: T): BoundCondition =
    derive { it.contains(element) }

fun <T> BoundProperty<Collection<T>>.containsAll(elements: Collection<T>): BoundCondition =
    derive { it.containsAll(elements) }
