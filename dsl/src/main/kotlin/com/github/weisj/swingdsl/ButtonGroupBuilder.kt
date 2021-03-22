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
package com.github.weisj.swingdsl

import com.github.weisj.swingdsl.text.Text
import com.github.weisj.swingdsl.text.textOfNullable
import javax.swing.ButtonGroup

interface ButtonGroupBuilder {
    fun withButtonGroup(title: Text?, buttonGroup: ButtonGroup, body: () -> Unit)

    fun withButtonGroup(buttonGroup: ButtonGroup, body: () -> Unit) {
        withButtonGroup(null as Text?, buttonGroup, body)
    }

    fun buttonGroup(init: () -> Unit) {
        buttonGroup(null as Text?, init)
    }

    fun buttonGroup(title: Text? = null, init: () -> Unit) {
        withButtonGroup(title, ButtonGroup(), init)
    }

    fun withButtonGroup(title: String?, buttonGroup: ButtonGroup, body: () -> Unit) =
        withButtonGroup(textOfNullable(title), buttonGroup, body)

    fun buttonGroup(title: String? = null, init: () -> Unit) =
        buttonGroup(textOfNullable(title), init)
}
