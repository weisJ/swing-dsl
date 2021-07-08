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
package com.github.weisj.swingdsl.util

import java.awt.Insets
import java.awt.Rectangle

fun Insets(pad: Int) = Insets(pad, pad, pad, pad)

fun Insets.isEmpty(): Boolean =
    top == 0 && bottom == 0 && left == 0 && right == 0

val Insets.width
    get() = left + right

val Insets.height
    get() = top + bottom

fun Rectangle.subtract(insets: Insets, inPlace: Boolean = false): Rectangle {
    val target = if (inPlace) this else Rectangle()
    target.setBounds(x + insets.left, y + insets.top, width - insets.width, height - insets.height)
    return target
}

fun Rectangle.add(insets: Insets, inPlace: Boolean = false): Rectangle {
    val target = if (inPlace) this else Rectangle()
    target.setBounds(x - insets.left, y - insets.top, width + insets.width, height + insets.height)
    return target
}

fun Rectangle.xCenter(): Int = x + width / 2
fun Rectangle.yCenter(): Int = y + height / 2
fun Rectangle.bottom(): Int = y + height
fun Rectangle.left(): Int = x
fun Rectangle.right(): Int = x + width
fun Rectangle.top(): Int = y
