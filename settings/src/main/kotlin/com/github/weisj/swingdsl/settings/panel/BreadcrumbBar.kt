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
package com.github.weisj.swingdsl.settings.panel

import com.github.weisj.swingdsl.border.emptyBorder
import com.github.weisj.swingdsl.listeners.ClickListener
import com.github.weisj.swingdsl.mouseLocation
import com.github.weisj.swingdsl.style.DynamicUI
import com.github.weisj.swingdsl.util.toHtml
import java.awt.Component
import java.awt.Container
import java.awt.Dimension
import java.awt.Graphics
import java.awt.LayoutManager
import java.awt.Point
import java.awt.Rectangle
import java.awt.event.MouseEvent
import java.lang.Integer.max
import java.util.Collections.emptyList
import javax.swing.CellRendererPane
import javax.swing.JComponent
import javax.swing.JLabel

fun interface BreadcrumbRenderer<in T> {

    fun getRendererComponent(value: T, hovered: Boolean): JComponent
}

fun interface NavigationListener<T> {
    fun onClick(index: Int, item: T)
}

class BreadcrumbBar<T> : JComponent() {

    var padding: Int = 10
        set(pad) {
            field = pad
            doLayout()
            repaint()
        }
    private val navigationListeners: MutableList<NavigationListener<T>> = mutableListOf()
    private var clickListeners: List<ClickListener> = emptyList()
    var breadCrumbs: List<T> = emptyList()
        set(value) {
            field = value
            doLayout()
            revalidate()
            val point = mouseLocation() ?: Point(Int.MIN_VALUE, Int.MIN_VALUE)
            hoverStates = BooleanArray(value.size) {
                breadcrumbLayout.layoutRects[it].contains(point)
            }
            clickListeners.forEach { it.uninstall(this) }
            clickListeners = breadCrumbs.mapIndexed { i, crumb ->
                val listener = object : ClickListener() {
                    override fun onClick(event: MouseEvent, clickCount: Int): Boolean {
                        navigationListeners.forEach { it.onClick(i, crumb) }
                        return true
                    }

                    override fun onHover(event: MouseEvent, isHovered: Boolean) {
                        hoverStates[i] = isHovered
                        repaint(breadcrumbLayout.layoutRects[i])
                    }
                }
                listener.installOn(this, listenForHover = true) {
                    if (i !in breadcrumbLayout.layoutRects.indices) false
                    else breadcrumbLayout.layoutRects[i].contains(it)
                }
                listener
            }
            repaint()
        }
    var hoverStates: BooleanArray = BooleanArray(breadCrumbs.size)

    var renderer: BreadcrumbRenderer<T> = DefaultBreadCrumbRenderer()
    var rendererPane = CellRendererPane()
    private val breadcrumbLayout = BreadcrumbLayout<T>()
    var separator: JComponent = JLabel("\u276F").apply {
        border = emptyBorder(top = padding / 2, padding, bottom = padding / 2, padding)
        DynamicUI.withBoldFont(this)
    }

    init {
        layout = breadcrumbLayout
        add(rendererPane)
    }

    fun addNavigationListener(listener: NavigationListener<T>) {
        navigationListeners.add(listener)
    }

    fun removeNavigationListener(listener: NavigationListener<T>) {
        navigationListeners.remove(listener)
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        g ?: return
        val ySep = (height - breadcrumbLayout.separatorSize.height) / 2
        val wSep = breadcrumbLayout.separatorSize.width
        val hSep = breadcrumbLayout.separatorSize.height
        for (i in breadCrumbs.indices) {
            val item = breadCrumbs[i]
            val rect = breadcrumbLayout.layoutRects[i]
            val comp = renderer.getRendererComponent(item, hoverStates[i])
            rendererPane.paintComponent(g, comp, this, rect.x, rect.y, rect.width, rect.height)
            if (i < breadCrumbs.size - 1) {
                rendererPane.paintComponent(g, separator, this, rect.x + rect.width, ySep, wSep, hSep)
            }
        }
        rendererPane.removeAll()
    }
}

@Suppress("UNCHECKED_CAST")
private class BreadcrumbLayout<T> : LayoutManager {

    lateinit var layoutRects: List<Rectangle>
    lateinit var separatorSize: Dimension

    private fun <R> getSizes(parent: BreadcrumbBar<T>, selector: (JComponent) -> R): Sequence<R> {
        return parent.breadCrumbs.asSequence()
            .mapNotNull { parent.renderer.getRendererComponent(it, false) }
            .onEach { parent.rendererPane.add(it) }
            .map(selector)
            .also {
                parent.rendererPane.removeAll()
            }
    }

    override fun preferredLayoutSize(parent: Container?): Dimension {
        parent as BreadcrumbBar<T>
        parent.rendererPane.add(parent.separator)
        val size = parent.separator.preferredSize
        val separatorSpace = if (parent.breadCrumbs.isEmpty()) 0 else {
            (parent.breadCrumbs.size - 1) * size.width
        }
        val separatorSize = if (parent.breadCrumbs.isEmpty()) 0 else size.height
        val dimensions = getSizes(parent) { it.preferredSize }
        return Dimension(
            dimensions.sumBy { it.width } + separatorSpace,
            max(dimensions.maxOfOrNull { it.height } ?: 0, separatorSize)
        )
    }

    override fun minimumLayoutSize(parent: Container?): Dimension {
        parent as BreadcrumbBar<T>
        return parent.breadCrumbs.lastOrNull()?.let {
            val comp = parent.renderer.getRendererComponent(it, false)
            parent.rendererPane.add(comp)
            val size = comp.preferredSize
            parent.rendererPane.removeAll()
            size
        } ?: Dimension(0, 0)
    }

    override fun addLayoutComponent(name: String?, comp: Component?) {}

    override fun removeLayoutComponent(comp: Component?) {}

    override fun layoutContainer(parent: Container?) {
        parent as BreadcrumbBar<T>
        val availableWidth = parent.width
        val availableHeight = parent.height
        parent.rendererPane.add(parent.separator)
        layoutRects = getSizes(parent) { Rectangle(it.preferredSize) }.toList()
        var x = parent.padding
        separatorSize = parent.separator.preferredSize
        val separatorAdvance = separatorSize.width
        for (rect in layoutRects) {
            rect.x = x
            if (availableHeight < rect.height) rect.height = availableHeight
            rect.y = max(0, (availableHeight - rect.height) / 2)
            x += rect.width + separatorAdvance
        }
        // Adjust for separator size after last item.
        if (layoutRects.isNotEmpty()) x -= separatorAdvance

        x += parent.padding
        if (x > availableWidth) {
            val extra = x - availableWidth
            // Shift to the left.
            layoutRects.forEach { it.x -= extra }
        }
    }
}

open class DefaultBreadCrumbRenderer<T>(private val stringFunc: (T) -> String = { it.toString() }) :
    JLabel(),
    BreadcrumbRenderer<T> {
    override fun getRendererComponent(value: T, hovered: Boolean): JComponent {
        text = if (hovered) stringFunc(value).toHtml("u") else stringFunc(value)
        return this
    }
}
