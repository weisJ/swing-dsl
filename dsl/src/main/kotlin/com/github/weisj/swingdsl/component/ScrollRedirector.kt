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
package com.github.weisj.swingdsl.component

import com.github.weisj.swingdsl.ancestorOfType
import com.github.weisj.swingdsl.laf.ScrollableView
import com.github.weisj.swingdsl.util.ModificationLock
import java.awt.AWTEvent
import java.awt.Component
import java.awt.Dimension
import java.awt.event.MouseEvent
import java.awt.event.MouseWheelEvent
import javax.swing.JComponent
import javax.swing.JLayer
import javax.swing.JScrollBar
import javax.swing.JScrollPane
import javax.swing.SwingUtilities
import javax.swing.plaf.LayerUI

class DefaultScrollRedirector(target: JComponent) : ScrollRedirector(target)

class ScrollableViewScrollRedirector<T>(target: T) : ScrollRedirector(target), ScrollableView by target
        where T : JComponent, T : ScrollableView

sealed class ScrollRedirector private constructor(private val target: JComponent) : JComponent() {

    companion object {
        operator fun invoke(target: JComponent): ScrollRedirector = when (target) {
            is ScrollableView -> ScrollableViewScrollRedirector(target)
            else -> DefaultScrollRedirector(target)
        }
    }

    private val layer = JLayer(target, ScrollRedirectorUI())

    init {
        layer.layerEventMask = AWTEvent.MOUSE_WHEEL_EVENT_MASK
        this.add(layer)
    }

    override fun doLayout() {
        layer.setBounds(0, 0, width, height)
        target.setBounds(0, 0, width, height)
    }

    override fun getMinimumSize(): Dimension = target.minimumSize
    override fun getPreferredSize(): Dimension = target.preferredSize
    override fun getMaximumSize(): Dimension = target.maximumSize
}

private class ScrollRedirectorUI : LayerUI<JComponent>() {
    private var dispatchLock = ModificationLock()

    override fun processMouseWheelEvent(e: MouseWheelEvent?, l: JLayer<out JComponent>?) {
        super.processMouseWheelEvent(e, l)
        e ?: return
        if (!redirectIfNeeded(e, l)) {
            dispatchLock.withLock {
                var forward = e.component == l
                if (!forward) {
                    e.component?.dispatchEvent(e)
                    forward = !e.isConsumed
                }
                if (forward) {
                    e.component.ancestorOfType<JScrollPane>()?.dispatchEvent(e)
                }
            }
        }
    }

    private fun redirectIfNeeded(e: MouseWheelEvent, l: JLayer<out JComponent>?): Boolean {
        dispatchLock.withLock {
            val direction = if (e.wheelRotation < 0) -1 else 1
            val toScroll = when (val comp = e.component) {
                is JScrollBar -> comp
                is JScrollPane -> {
                    var bar: JScrollBar? = comp.verticalScrollBar
                    if (bar == null || !bar.isVisible || e.isShiftDown) {
                        bar = comp.horizontalScrollBar
                    }
                    bar
                }
                else -> null
            } ?: return false
            val valueModel = toScroll.model

            val forward = when {
                direction > 0 -> valueModel.value + valueModel.extent == valueModel.maximum
                direction < 0 -> valueModel.value == valueModel.minimum
                else -> false
            }

            if (forward) {
                val target = l?.ancestorOfType<JScrollPane>() ?: return false
                dispatch(e, target)
                e.consume()
                return true
            }
        }
        // If we are here then we are currently redispatching an Event. Skip in this case.
        return true
    }

    private fun dispatch(e: AWTEvent?, target: Component) {
        val event = if (e is MouseEvent) SwingUtilities.convertMouseEvent(e.component, e, target) else e
        target.dispatchEvent(event)
    }
}
