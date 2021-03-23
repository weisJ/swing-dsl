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

// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.github.weisj.swingdsl.layout

interface SpacingConfiguration {
    /**
     * Horizontal space between two components (in terms of layout grid - cells).
     *
     * It is space between associated components (somehow relates to each other) - for example, combobox and button to delete items from combobox.
     * Since in most cases components in cells will be associated, it is not a space between two independent components.
     * Horizontal subgroups of components is not supported yet, that's why there is no property to define such space.
     */
    val horizontalGap: Int

    /**
     * Vertical space between two components (in terms of layout grid - rows).
     */
    val verticalGap: Int get() = componentVerticalGap * 2
    val componentVerticalGap: Int

    /**
     * Horizontal gap after label column.
     */
    val labelColumnHorizontalGap: Int

    val largeHorizontalGap: Int
    val largeVerticalGap: Int
    val radioGroupTitleVerticalGap: Int

    val shortTextWidth: Int
    val maxShortTextWidth: Int

    val commentVerticalTopGap: Int

    val dialogTopBottom: Int
    val dialogLeftRight: Int

    /**
     * The size of one indent level (when not overridden by specific control type, e.g. indent of checkbox comment row
     * is defined by checkbox icon size)
     */
    val indentLevel: Int
}

fun createDefaultSpacingConfiguration(): SpacingConfiguration {
    return object : SpacingConfiguration {
        override val horizontalGap = 6
        override val componentVerticalGap = 6
        override val labelColumnHorizontalGap = 6
        override val largeHorizontalGap = 16
        override val largeVerticalGap = 20
        override val radioGroupTitleVerticalGap = 8

        override val shortTextWidth = 250
        override val maxShortTextWidth = 350

        override val dialogTopBottom = 10
        override val dialogLeftRight = 12

        override val commentVerticalTopGap = 6

        override val indentLevel: Int = 15
    }
}
