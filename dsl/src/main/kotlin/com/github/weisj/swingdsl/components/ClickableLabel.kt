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
package com.github.weisj.swingdsl.components

import com.github.weisj.swingdsl.core.text.Text
import com.github.weisj.swingdsl.core.text.TextLabel
import com.github.weisj.swingdsl.listeners.ClickListener
import com.github.weisj.swingdsl.on
import com.github.weisj.swingdsl.toKeyStroke
import com.github.weisj.swingdsl.util.toHtml
import java.awt.Color
import java.awt.Cursor
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.FocusEvent
import java.awt.event.FocusListener
import java.awt.event.KeyEvent
import java.awt.event.MouseEvent

class ClickableLabel(text: Text) : ClickableLabelBase<ActionEvent, ActionListener>(text) {
    override fun createEvent(): ActionEvent = ActionEvent(this@ClickableLabel, 0, "click")
    override fun ActionListener.dispatch(event: ActionEvent) {
        actionPerformed(event)
    }
}

abstract class ClickableLabelBase<E, L>(observableText: Text) :
    TextLabel(observableText) {

    private val listeners: MutableList<L> = mutableListOf()

    private var isHovered = false
    private var isFocused = false
    private var originalText: String? = observableText.get()
    var armedForeground: Color = foreground
    var normalForeground: Color = foreground
    var showHoverEffect: Boolean = true

    private fun setTrueText(value: String?) = super.setText(value)

    override fun setText(text: String?) {
        originalText = text
        updateText()
    }

    init {
        setupListener()
        isFocusable = true
    }

    private fun updateText() {
        if (isFocused || (isHovered && showHoverEffect)) {
            setTrueText(underlineTextInHtml(originalText))
        } else {
            setTrueText(originalText)
        }
    }

    private fun setupListener() {
        addFocusListener(object : FocusListener {
            override fun focusGained(e: FocusEvent?) {
                isFocused = true
                updateText()
            }

            override fun focusLost(e: FocusEvent?) {
                isFocused = false
                updateText()
            }
        })

        object : ClickListener() {
            override fun onClick(event: MouseEvent, clickCount: Int): Boolean {
                notifyClick()
                return true
            }

            override fun onHover(event: MouseEvent, isHovered: Boolean) {
                if (!showHoverEffect) return
                this@ClickableLabelBase.isHovered = isHovered
                cursor = if (isHovered) Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) else Cursor.getDefaultCursor()
                updateText()
            }

            override fun onArmed(event: MouseEvent, isArmed: Boolean) {
                updateForeground(isArmed)
            }
        }.installOn(this, listenForHover = true)
        on(KeyEvent.VK_SPACE.toKeyStroke()) { notifyClick() }
    }

    fun updateForeground(isArmed: Boolean) {
        foreground = if (isArmed) armedForeground else normalForeground
    }

    protected abstract fun createEvent(): E

    protected abstract fun L.dispatch(event: E)

    private fun notifyClick() {
        val event = createEvent()
        listeners.forEach { it.dispatch(event) }
    }

    private fun underlineTextInHtml(text: String?): String? {
        return text?.toHtml("u")
    }

    fun addListener(listener: L) {
        listeners.add(listener)
    }

    fun removeListener(listener: L) {
        listeners.remove(listener)
    }
}
