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

// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.github.weisj.swingdsl.miglayout

import net.miginfocom.layout.BoundSize
import net.miginfocom.layout.LC
import net.miginfocom.layout.UnitValue

internal fun createLayoutConstraints(horizontalGap: Int, verticalGap: Int): LC {
    val lc = LC()
    lc.gridGapX = gapToBoundSize(horizontalGap, isHorizontal = true)
    lc.gridGapY = gapToBoundSize(verticalGap, isHorizontal = false)
    lc.setInsets(0)
    return lc
}

internal fun gapToBoundSize(value: Int, isHorizontal: Boolean): BoundSize {
    val unitValue = createUnitValue(value, isHorizontal)
    return BoundSize(unitValue, unitValue, null, false, null)
}

internal fun createLayoutConstraints(): LC {
    val lc = LC()
    lc.gridGapX = gapToBoundSize(0, true)
    lc.setInsets(0)
    return lc
}

internal fun LC.setInsets(value: Int) = setInsets(value, value)

internal fun LC.setInsets(topBottom: Int, leftRight: Int) {
    val h = createUnitValue(leftRight, isHorizontal = true)
    val v = createUnitValue(topBottom, isHorizontal = false)
    insets = arrayOf(v, h, v, h)
}

internal fun createUnitValue(value: Int, isHorizontal: Boolean): UnitValue {
    return UnitValue(value.toFloat(), "px", isHorizontal, UnitValue.STATIC, null)
}
