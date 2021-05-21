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

import java.awt.Graphics
import java.awt.Rectangle

fun Graphics.drawRect(rect: Rectangle) {
    drawRect(rect.x, rect.y, rect.width, rect.height, lineWidth = 1)
}

fun Graphics.fillRect(rect: Rectangle) {
    fillRect(rect.x, rect.y, rect.width, rect.height)
}

fun Graphics.drawRect(x: Int, y: Int, w: Int, h: Int, lineWidth: Int = 1) {
    translate(x, y)
    fillRect(0, 0, w, lineWidth)
    fillRect(0, lineWidth, lineWidth, h - 2 * lineWidth)
    fillRect(w - lineWidth, lineWidth, lineWidth, h - 2 * lineWidth)
    fillRect(0, h - lineWidth, w, lineWidth)
    translate(-x, -y)
}
