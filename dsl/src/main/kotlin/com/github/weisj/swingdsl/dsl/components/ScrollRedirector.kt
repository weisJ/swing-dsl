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
package com.github.weisj.swingdsl.dsl.components

import com.github.weisj.swingdsl.dsl.util.ModificationLock
import com.github.weisj.swingdsl.dsl.util.ancestorMatching
import com.github.weisj.swingdsl.laf.ScrollableView
import java.awt.AWTEvent
import java.awt.Component
import java.awt.Dimension
import java.awt.Rectangle
import java.awt.event.MouseEvent
import java.awt.event.MouseWheelEvent
import javax.swing.JComponent
import javax.swing.JLayer
import javax.swing.JScrollBar
import javax.swing.JScrollPane
import javax.swing.Scrollable
import javax.swing.SwingUtilities
import javax.swing.plaf.LayerUI

class DefaultScrollRedirector(target: JComponent) : ScrollRedirector(target)

class ScrollableViewScrollRedirector<T>(target: T) : ScrollRedirector(target), ScrollableView by target
        where T : JComponent, T : ScrollableView

sealed class ScrollRedirector private constructor(private val target: JComponent) : JComponent(), Scrollable {

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

    override fun setBounds(x: Int, y: Int, width: Int, height: Int) {
        super.setBounds(x, y, width, height)
        doLayout()
    }

    override fun setSize(width: Int, height: Int) {
        super.setSize(width, height)
        doLayout()
    }

    override fun getMinimumSize(): Dimension = target.minimumSize
    override fun getPreferredSize(): Dimension = target.preferredSize
    override fun getMaximumSize(): Dimension = target.maximumSize

    override fun getPreferredScrollableViewportSize(): Dimension? =
        layer.preferredScrollableViewportSize

    override fun getScrollableBlockIncrement(visibleRect: Rectangle, orientation: Int, direction: Int): Int =
        layer.getScrollableBlockIncrement(visibleRect, orientation, direction)

    override fun getScrollableUnitIncrement(visibleRect: Rectangle?, orientation: Int, direction: Int): Int =
        layer.getScrollableUnitIncrement(visibleRect, orientation, direction)

    override fun getScrollableTracksViewportHeight(): Boolean =
        layer.scrollableTracksViewportHeight

    override fun getScrollableTracksViewportWidth(): Boolean =
        layer.scrollableTracksViewportWidth
}

private class ScrollRedirectorUI : LayerUI<JComponent>() {
    private var dispatchLock = ModificationLock()

    override fun processMouseWheelEvent(e: MouseWheelEvent?, l: JLayer<out JComponent>?) {
        super.processMouseWheelEvent(e, l)
        e ?: return
        dispatchLock.withLock {
            val (scrollTarget, toScroll) = getScrollBar(e.component, e)

            val direction = if (e.wheelRotation < 0) -1 else 1
            val valueModel = toScroll?.model

            val canRedirect = valueModel != null && when {
                direction > 0 -> valueModel.value + valueModel.extent == valueModel.maximum
                direction < 0 -> valueModel.value == valueModel.minimum
                else -> false
            }

            e.component?.dispatchEvent(e)
            if (e.isConsumed) return

            scrollTarget ?: return
            if (canRedirect) {
                val target = when (scrollTarget) {
                    is JScrollBar -> null
                    is JScrollPane -> scrollTarget.parent
                    else -> scrollTarget
                }
                target?.nextScrollPaneAncestor()?.let {
                    dispatch(e, it)
                }
            } else {
                dispatch(e, scrollTarget)
            }
        }
    }

    private fun getScrollBar(comp: Component?, e: MouseEvent): Pair<Component?, JScrollBar?> {
        return when (comp) {
            is JScrollBar -> comp to comp
            is JScrollPane -> {
                var bar: JScrollBar? = comp.verticalScrollBar
                if (bar == null || !bar.isVisible || e.isShiftDown) {
                    bar = comp.horizontalScrollBar
                }
                comp to bar
            }
            is Component -> getScrollBar(comp.nextScrollPaneAncestor(), e)
            else -> comp to null
        }
    }

    private fun Component.nextScrollPaneAncestor(): Component? =
        ancestorMatching { it is JScrollPane && it !is ScrollRedirector }

    private fun dispatch(e: AWTEvent?, target: Component) {
        val event = if (e is MouseEvent) SwingUtilities.convertMouseEvent(e.component, e, target) else e
        target.dispatchEvent(event)
    }
}
