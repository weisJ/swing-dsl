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

fun ObservableProperty<CharSequence>.length(): ObservableProperty<Int> = derive { it.length }
fun ObservableProperty<CharSequence>.isEmpty(): ObservableCondition = derive { it.isEmpty() }
fun ObservableProperty<CharSequence>.isNotEmpty(): ObservableCondition = derive { it.isNotEmpty() }
fun ObservableProperty<CharSequence>.isBlank(): ObservableCondition = derive { it.isBlank() }
fun ObservableProperty<CharSequence>.isNotBlank(): ObservableCondition = derive { it.isNotBlank() }
