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

import java.awt.Color

fun Color.mix(other: Color, ratio: Float = 0.5f): Color {
    val inv = 1f - ratio
    return Color(
        (this.red * ratio + inv * (other.red - this.red)).toInt().coerceIn(0, 255),
        (this.green * ratio + inv * (other.green - this.green)).toInt().coerceIn(0, 255),
        (this.blue * ratio + inv * (other.blue - this.blue)).toInt().coerceIn(0, 255),
        (this.alpha * ratio + inv * (other.alpha - this.alpha)).toInt().coerceIn(0, 255),
    )
}

fun Color.toHex(includeAlpha: Boolean = true): String {
    return if (includeAlpha) {
        String.format("%02X%02X%02X%02X", red, green, blue, alpha)
    } else {
        String.format("%02X%02X%02X", red, green, blue)
    }
}

fun Color.asCSSProperty(colorTag: String = "color", opacityTag: String = "opacity"): String = buildString {
    append(colorTag)
    append(':')
    append(toHex(includeAlpha = false))
    append(';')
    if (alpha < 255) {
        append(opacityTag)
        append(':')
        append((alpha / 255f).coerceIn(0f, 1f))
        append(';')
    }
}
