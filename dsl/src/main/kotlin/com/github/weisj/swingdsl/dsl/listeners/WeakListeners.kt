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
package com.github.weisj.swingdsl.dsl.listeners

import java.awt.AWTEvent
import java.awt.Toolkit
import java.awt.event.AWTEventListener
import java.lang.ref.WeakReference

internal class WeakAWTEventListener(owner: Any, listener: AWTEventListener) : AWTEventListener {

    private val ownerRef = WeakReference(owner)
    private val listenerRef = WeakReference(listener)

    override fun eventDispatched(event: AWTEvent?) {
        val owner = ownerRef.get()
        val listener = listenerRef.get()
        if (owner == null || listener == null) {
            Toolkit.getDefaultToolkit().removeAWTEventListener(this)
        } else {
            listener.eventDispatched(event)
        }
    }
}

fun registerAWTEventListener(mask: Long, listener: AWTEventListener, owner: Any = listener) {
    Toolkit.getDefaultToolkit().addAWTEventListener(WeakAWTEventListener(owner, listener), mask)
}
