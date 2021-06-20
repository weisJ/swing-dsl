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

import com.github.weisj.swingdsl.border.emptyBorder
import com.github.weisj.swingdsl.bottom
import com.github.weisj.swingdsl.left
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
import java.util.*
import java.util.Collections.emptyList
import javax.swing.CellRendererPane
import javax.swing.Icon
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JMenuItem
import javax.swing.JPopupMenu
import javax.swing.event.PopupMenuEvent
import javax.swing.event.PopupMenuListener

interface BreadcrumbRenderer<in T> {

    fun getRendererComponent(value: T, hovered: Boolean): JComponent

    fun createPopupMenuItem(value: T): JMenuItem
}

fun interface NavigationListener<NodeType, T> : EventListener {
    fun onClick(index: Int, node: NodeType, item: T): Boolean

    fun onClickInPopup(node: NodeType, item: T) {}
}

class BreadcrumbBar<NodeType, T>(
    model: BreadcrumbModel<NodeType, T>
) : JComponent() {

    var padding: Int = 10
        set(pad) {
            field = pad
            doLayout()
            repaint()
        }
    private var clickListeners: List<ClickListener> = emptyList()

    var model: BreadcrumbModel<NodeType, T> = model
        set(value) {
            field.removeBreadCrumbModelListener(updater)
            field = value
            field.addBreadCrumbModelListener(updater)
        }
    internal var breadCrumbs: List<NodeType> = model.breadcrumbs

    private var popupMenu: JPopupMenu = JPopupMenu().apply {
        addPopupMenuListener(object : PopupMenuListener {
            override fun popupMenuWillBecomeVisible(e: PopupMenuEvent?) {}

            override fun popupMenuWillBecomeInvisible(e: PopupMenuEvent?) {
                popupIndex = -1
            }

            override fun popupMenuCanceled(e: PopupMenuEvent?) {
                popupIndex = -1
            }
        })
    }
    private var popupIndex: Int = -1
    private var popupLocation: Point = Point(-1, -1)
    private var popupCrumb: NodeType? = null

    @Suppress("UNCHECKED_CAST")
    private fun buildPopupMenu(isNewPopup: Boolean = false) {
        if (popupIndex < 0 && !isNewPopup) return
        popupMenu.removeAll()
        val selectedRow = if (isNewPopup) 0 else popupMenu.selectionModel.selectedIndex
        val crumb = popupCrumb ?: return
        model.getChildren(crumb).toMutableList().forEach { node ->
            val value = model.getValue(node)
            val item = renderer.createPopupMenuItem(value)
            item.addActionListener { _ ->
                listenerList.getListeners(NavigationListener::class.java).forEach {
                    (it as NavigationListener<NodeType, T>).onClickInPopup(node, value)
                }
            }
            popupMenu.add(item)
        }
        popupMenu.selectionModel.apply {
            if (popupMenu.componentCount > 0) {
                selectedIndex = selectedRow.coerceIn(0, popupMenu.componentCount - 1)
            }
        }
        val index = popupIndex
        popupMenu.isVisible = false
        if (popupMenu.componentCount > 0) {
            popupMenu.show(this, popupLocation.x, popupLocation.y)
        }
        popupIndex = index
    }

    @Suppress("UNCHECKED_CAST")
    private fun refreshClickListeners() {
        clickListeners.forEach { it.uninstall(this@BreadcrumbBar) }

        val point = mouseLocation() ?: Point(Int.MIN_VALUE, Int.MIN_VALUE)
        hoverStates = BooleanArray(breadCrumbs.size) {
            breadcrumbLayout.layoutRects[it].contains(point)
        }

        clickListeners = breadCrumbs.mapIndexed { i, crumb ->
            val listener = object : ClickListener() {
                override fun onClick(event: MouseEvent, clickCount: Int): Boolean {
                    if (popupIndex != i) {
                        val listeners = listenerList.getListeners(NavigationListener::class.java)
                        val consumeCount = listeners.count {
                            (it as NavigationListener<NodeType, T>).onClick(i, crumb, model.getValue(crumb))
                        }
                        if (consumeCount == 0) {
                            // No listener has consumed the event.
                            popupMenu.isVisible = false
                            popupIndex = i
                            popupCrumb = crumb
                            val rect = breadcrumbLayout.layoutRects[i]
                            popupLocation = Point(rect.left(), rect.bottom())
                            buildPopupMenu(isNewPopup = true)
                        }
                    } else {
                        popupMenu.isVisible = false
                    }
                    return true
                }

                override fun onHover(event: MouseEvent, isHovered: Boolean) {
                    hoverStates[i] = isHovered
                    repaint(breadcrumbLayout.layoutRects[i])
                }
            }
            listener.installOn(this@BreadcrumbBar, listenForHover = true) {
                breadcrumbLayout.layoutRects.getOrNull(i)?.contains(it) ?: false
            }
            listener
        }
    }

    private val updater = object : BreadCrumbModelListener {
        override fun nodesChanged() {
            breadCrumbs = model.breadcrumbs
            doLayout()
            revalidate()
            refreshClickListeners()
            repaint()
            buildPopupMenu(isNewPopup = false)
        }
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
        this.model = model
        layout = breadcrumbLayout
        add(rendererPane)
        updater.nodesChanged()
    }

    override fun updateUI() {
        super.updateUI()
        separator.updateUI()
        renderer.let {
            if (it is JComponent) it.updateUI()
        }
    }

    fun addNavigationListener(listener: NavigationListener<NodeType, T>) {
        listenerList.add(NavigationListener::class.java, listener)
    }

    fun removeNavigationListener(listener: NavigationListener<NodeType, T>) {
        listenerList.remove(NavigationListener::class.java, listener)
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        g ?: return
        // Give some extra room to avoid the text being cut off.
        val wSep = breadcrumbLayout.separatorSize.width + 1
        val hSep = breadcrumbLayout.separatorSize.height
        val ySep = (height - hSep) / 2
        for (i in breadCrumbs.indices) {
            val item = breadCrumbs[i]
            val rect = breadcrumbLayout.layoutRects[i]
            val comp = renderer.getRendererComponent(model.getValue(item), hoverStates[i])
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

    private fun <R> getSizes(parent: BreadcrumbBar<Any?, T>, selector: (JComponent) -> R): Sequence<R> {
        return parent.breadCrumbs.asSequence()
            .mapNotNull { parent.renderer.getRendererComponent(parent.model.getValue(it), false) }
            .onEach { parent.rendererPane.add(it) }
            .map(selector)
            .also {
                parent.rendererPane.removeAll()
            }
    }

    override fun preferredLayoutSize(parent: Container?): Dimension {
        parent as BreadcrumbBar<Any?, T>
        parent.rendererPane.add(parent.separator)
        val size = parent.separator.preferredSize
        val separatorSpace = if (parent.breadCrumbs.isEmpty()) 0 else {
            (parent.breadCrumbs.size - 1) * size.width
        }
        val separatorSize = if (parent.breadCrumbs.isEmpty()) 0 else size.height
        val dimensions = getSizes(parent) { it.preferredSize }
        return Dimension(
            dimensions.sumOf { it.width } + separatorSpace,
            max(dimensions.maxOfOrNull { it.height } ?: 0, separatorSize)
        )
    }

    override fun minimumLayoutSize(parent: Container?): Dimension {
        parent as BreadcrumbBar<Any?, T>
        return parent.breadCrumbs.lastOrNull()?.let {
            val comp = parent.renderer.getRendererComponent(parent.model.getValue(it), false)
            parent.rendererPane.add(comp)
            val size = comp.preferredSize
            parent.rendererPane.removeAll()
            size
        } ?: Dimension(0, 0)
    }

    override fun addLayoutComponent(name: String?, comp: Component?) {
        /* Nothing to do here */
    }

    override fun removeLayoutComponent(comp: Component?) {
        /* Nothing to do here */
    }

    override fun layoutContainer(parent: Container?) {
        parent as BreadcrumbBar<Any?, T>
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

open class DefaultBreadCrumbRenderer<T>(
    private val stringFunc: (T) -> String = { it.toString() },
    private val iconFunc: (T) -> Icon? = { null },
    private val underlineOnHover: Boolean = true
) : JLabel(), BreadcrumbRenderer<T> {
    override fun getRendererComponent(value: T, hovered: Boolean): JComponent {
        text = if (hovered && underlineOnHover) stringFunc(value).toHtml("u") else stringFunc(value).toHtml()
        icon = iconFunc(value)
        return this
    }

    override fun createPopupMenuItem(value: T): JMenuItem {
        return JMenuItem(stringFunc(value), iconFunc(value))
    }
}
