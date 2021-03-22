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
import java.awt.BorderLayout
import java.awt.LayoutManager
import javax.swing.JComponent
import javax.swing.JPanel

class ModifiablePanel(val title: Text? = null, layout: LayoutManager? = BorderLayout()) : JPanel(layout),
    ModifiableComponent<JPanel> {

    constructor(title: String?, layout: LayoutManager? = BorderLayout()) : this(textOfNullable(title), layout)

    companion object {
        internal const val DIALOG_CONTENT_PANEL_PROPERTY = "dialogContentPanel"
    }

    init {
        putClientProperty(DIALOG_CONTENT_PANEL_PROPERTY, true)
    }

    override fun getComponent(): JPanel = this
    override fun getContainer(): JComponent = this

    var applyCallbacks: Map<JComponent?, List<() -> Unit>> = emptyMap()
    var resetCallbacks: Map<JComponent?, List<() -> Unit>> = emptyMap()
    var isModifiedCallbacks: Map<JComponent?, List<() -> Boolean>> = emptyMap()

    override fun apply() {
        for ((component, callbacks) in applyCallbacks.entries) {
            if (component == null) continue

            val modifiedCallbacks = isModifiedCallbacks[component]
            if (modifiedCallbacks.isNullOrEmpty() || modifiedCallbacks.any { it() }) {
                callbacks.forEach { it() }
            }
        }
        applyCallbacks[null]?.forEach { it() }
    }

    override fun reset() {
        for ((component, callbacks) in resetCallbacks.entries) {
            if (component == null) continue

            callbacks.forEach { it() }
        }
        resetCallbacks[null]?.forEach { it() }
    }

    override fun isModified(): Boolean {
        return isModifiedCallbacks.values.any { list -> list.any { it() } }
    }
}
