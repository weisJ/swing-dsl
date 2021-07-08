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
package com.github.weisj.swingdsl.components

import java.awt.Component
import java.awt.Point
import java.awt.Toolkit
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.SwingUtilities
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.math.abs

abstract class ClickListener {

    companion object {
        const val EPS = 4
    }

    private var listener: MouseAdapter? = null

    abstract fun onClick(event: MouseEvent, clickCount: Int): Boolean

    open fun onHover(event: MouseEvent, isHovered: Boolean) {
        /* Do nothing */
    }

    open fun onArmed(event: MouseEvent, isArmed: Boolean) {
        /* Do nothing */
    }

    fun installOn(
        c: Component,
        allowDragWhileClicking: Boolean = false,
        listenForHover: Boolean = false,
        hitTest: (Point) -> Boolean = { true }
    ) {
        listener = ClickListenerImpl(this, allowDragWhileClicking, listenForHover, hitTest)
        if (listenForHover) c.addMouseMotionListener(listener)
        c.addMouseListener(listener)
    }

    fun uninstall(c: Component) {
        c.removeMouseListener(listener)
    }
}

private fun isWithinEps(releasedAt: Point, clickedAt: Point): Boolean {
    return abs(clickedAt.x - releasedAt.x) < ClickListener.EPS &&
        abs(clickedAt.y - releasedAt.y) < ClickListener.EPS
}

internal class ClickListenerImpl(
    private val listener: ClickListener,
    private val allowDragWhileClicking: Boolean = false,
    private val listenForHover: Boolean = false,
    private val hitTest: (Point) -> Boolean = { true }
) : MouseAdapter() {
    private var pressPoint: Point? = null
    private var lastClickPoint: Point? = null
    private var lastTimeClicked: Long = -1
    private var clickCount = 0
    private var isHovered = false
    private var isArmed = false

    fun checkInside(e: MouseEvent) {
        if (!listenForHover) return
        val inside = hitTest(e.point)
        if (isHovered != inside) listener.onHover(e, inside)
        isHovered = inside
    }

    override fun mouseEntered(e: MouseEvent) {
        checkInside(e)
    }

    override fun mouseMoved(e: MouseEvent) {
        checkInside(e)
    }

    override fun mouseExited(e: MouseEvent) {
        if (!listenForHover) return
        isHovered = false
        listener.onHover(e, false)
    }

    override fun mousePressed(e: MouseEvent) {
        val point: Point = e.point
        if (!hitTest(point)) return
        SwingUtilities.convertPointToScreen(point, e.component)
        if (abs(lastTimeClicked - e.getWhen()) > getMultiClickInterval() ||
            lastClickPoint != null && !isWithinEps(lastClickPoint!!, point)
        ) {
            clickCount = 0
            lastClickPoint = null
        }
        clickCount++
        lastTimeClicked = e.getWhen()
        if (!e.isPopupTrigger) {
            pressPoint = point
            isArmed = true
            listener.onArmed(e, isArmed)
        }
    }

    override fun mouseReleased(e: MouseEvent) {
        val releasedAt: Point = e.point
        if (!hitTest(releasedAt)) return
        SwingUtilities.convertPointToScreen(releasedAt, e.component)
        val clickedAt: Point? = pressPoint
        lastClickPoint = pressPoint
        pressPoint = null
        isArmed = false
        listener.onArmed(e, isArmed)
        if (shouldIgnoreEvent(e, clickedAt)) return
        if (shouldConsume(releasedAt, clickedAt) && listener.onClick(e, clickCount)) e.consume()
    }

    private fun shouldConsume(releasedAt: Point, clickedAt: Point) =
        (allowDragWhileClicking || isWithinEps(releasedAt, clickedAt))

    @OptIn(ExperimentalContracts::class)
    private fun shouldIgnoreEvent(e: MouseEvent, clickedAt: Point?): Boolean {
        contract {
            returns(false) implies (clickedAt != null)
        }
        return e.isConsumed || clickedAt == null || e.isPopupTrigger || !e.component.contains(e.point)
    }
}
fun getMultiClickInterval(): Int {
    val property: Any = Toolkit.getDefaultToolkit().getDesktopProperty("awt.multiClickInterval")
    return property as? Int ?: 500
}
