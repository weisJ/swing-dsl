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
package com.github.weisj.swingdsl.layout

import com.github.weisj.swingdsl.ModifiableComponent
import com.github.weisj.swingdsl.condition.BoundCondition
import com.github.weisj.swingdsl.condition.ConditionCallback
import com.github.weisj.swingdsl.getWindow
import com.github.weisj.swingdsl.invokeLater
import com.github.weisj.swingdsl.text.Text
import com.github.weisj.swingdsl.text.textOfNullable
import java.awt.AWTEvent
import java.awt.BorderLayout
import java.awt.Component
import java.awt.Dialog
import java.awt.LayoutManager
import java.awt.Toolkit
import java.awt.event.AWTEventListener
import java.awt.event.KeyEvent
import java.awt.event.MouseEvent
import javax.swing.JComponent
import javax.swing.JDialog
import javax.swing.JPanel
import javax.swing.JWindow
import javax.swing.SwingUtilities

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

    val modifiedCondition: BoundCondition = ModifiedCondition(this)

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

    private class ModifiedCondition(private val panel: ModifiablePanel) : BoundCondition, AWTEventListener {

        private var modified = false
        private val listeners: MutableList<ConditionCallback> by lazy {
            Toolkit.getDefaultToolkit().addAWTEventListener(
                this, AWTEvent.KEY_EVENT_MASK or AWTEvent.MOUSE_EVENT_MASK
            )
            modified = panel.isModified()
            mutableListOf<ConditionCallback>()
        }

        override fun registerListener(callback: ConditionCallback) {
            listeners.add(callback)
        }

        override fun invoke(): Boolean {
            return modified
        }

        override fun eventDispatched(event: AWTEvent?) {
            event ?: return
            if (!panel.isVisible) return
            when (event.id) {
                MouseEvent.MOUSE_PRESSED, MouseEvent.MOUSE_RELEASED, MouseEvent.MOUSE_DRAGGED -> {
                    val me = event as MouseEvent
                    if (SwingUtilities.isDescendingFrom(me.component, panel) ||
                        isPopupOverEditor(me.component)
                    ) {
                        maybeModified()
                    }
                }
                KeyEvent.KEY_PRESSED, KeyEvent.KEY_RELEASED -> {
                    val ke = event as KeyEvent
                    if (SwingUtilities.isDescendingFrom(ke.component, panel)) {
                        maybeModified()
                    }
                }
            }
        }

        private fun maybeModified() {
            invokeLater {
                val newModified = panel.isModified()
                if (newModified == modified) return@invokeLater
                modified = newModified
                listeners.forEach { it(newModified) }
            }
        }

        private fun isPopupOverEditor(component: Component): Boolean {
            val editorWindow = panel.getWindow() ?: return false
            val popup = component.getWindow()
            // light-weight popup is located on the layered pane of the same window
            if (popup === editorWindow) {
                return true
            }
            // heavy-weight popup opens new window with the corresponding parent
            if (popup != null && editorWindow === popup.parent) {
                if (popup is JDialog) {
                    return Dialog.ModalityType.MODELESS == popup.modalityType
                }
                return popup is JWindow
            }
            return false
        }
    }
}