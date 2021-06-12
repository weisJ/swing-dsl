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
package com.github.weisj.swingdsl.inspector

import com.github.weisj.swingdsl.getWindow
import com.github.weisj.swingdsl.highlight.AnchoredLayoutTag
import com.github.weisj.swingdsl.highlight.ComponentHighlighter
import com.github.weisj.swingdsl.highlight.LayoutTag
import com.github.weisj.swingdsl.highlight.RegionPainter
import com.github.weisj.swingdsl.highlight.createLayoutTag
import com.github.weisj.swingdsl.invokeLater
import com.github.weisj.swingdsl.util.drawRect
import com.github.weisj.swingdsl.util.fillRect
import java.awt.AWTEvent
import java.awt.Color
import java.awt.Component
import java.awt.Container
import java.awt.Graphics2D
import java.awt.KeyboardFocusManager
import java.awt.MouseInfo
import java.awt.Point
import java.awt.Rectangle
import java.awt.Toolkit
import java.awt.event.AWTEventListener
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.awt.event.FocusEvent
import java.awt.event.InputEvent
import java.awt.event.KeyEvent
import java.awt.event.MouseEvent
import java.util.concurrent.atomic.AtomicReference
import javax.swing.JComponent
import javax.swing.JLayeredPane
import javax.swing.JList
import javax.swing.JTable
import javax.swing.JTree
import javax.swing.ListCellRenderer
import javax.swing.RootPaneContainer
import javax.swing.SwingUtilities
import javax.swing.tree.TreePath

internal class InspectionHighlightPainter : RegionPainter {
    private val outerBorderColor: Color = Color(0, 27, 200)
    private val innerBorderColor: Color = Color(200, 0, 0)
    private val fillColor: Color = Color(255, 0, 0, 50)

    override fun paint(g: Graphics2D, comp: Component, tags: List<LayoutTag>) {
        for (tag in tags) {
            val rect = tag.getBoundsIn(comp)
            g.color = fillColor
            g.fillRect(rect)
            g.color = innerBorderColor
            g.drawRect(rect)
            if (tag is AnchoredLayoutTag<*>) {
                val anchor = tag.anchor
                val outer = SwingUtilities.convertRectangle(anchor, Rectangle(anchor.size), comp)
                if (outer != rect) {
                    g.color = outerBorderColor
                    g.drawRect(outer)
                }
            }
        }
    }
}

internal class Inspector : AWTEventListener {

    companion object {
        private val HIGHLIGHT_LAYER = JLayeredPane.POPUP_LAYER + 1
        private var inspector: AtomicReference<Inspector?> = AtomicReference(null)
        fun install() {
            inspector.getAndUpdate {
                if (it == null) {
                    val newInspector = Inspector()
                    newInspector.installImpl()
                    newInspector
                } else it
            }
        }

        fun uninstall() {
            inspector.getAndUpdate {
                it?.uninstallImpl()
                null
            }
        }
    }

    private val highlighter = ComponentHighlighter(
        painter = InspectionHighlightPainter(),
        fitToParent = true
    ).apply {
        isVisible = false
    }
    private lateinit var inspectorWindow: InspectorWindow

    private var currentWindow: RootPaneContainer? = null
    private var currentHoverWindow: RootPaneContainer? = null
    private var lastClickTarget: Component? = null
    private var lastHighlightTarget: Component? = null

    private var highlightKeyComboPressed: Boolean = false
    private var inspectorWindowFocused: Boolean = false

    private val highlightUpdater = object : ComponentAdapter() {
        override fun componentMoved(e: ComponentEvent?) = update()

        override fun componentResized(e: ComponentEvent?) = update()

        fun update() = invokeLater {
            highlighter.repaint()
            val p = MouseInfo.getPointerInfo()?.location
            val c = lastHighlightTarget
            if (p != null && c != null) {
                SwingUtilities.convertPointFromScreen(p, lastHighlightTarget)
                updateHighlightTarget(c, p)
            }
        }
    }

    private fun installImpl() {
        val mask =
            AWTEvent.MOUSE_EVENT_MASK or AWTEvent.MOUSE_MOTION_EVENT_MASK or AWTEvent.KEY_EVENT_MASK or AWTEvent.FOCUS_EVENT_MASK
        Toolkit.getDefaultToolkit().addAWTEventListener(this, mask)
    }

    private fun uninstallImpl() {
        Toolkit.getDefaultToolkit().removeAWTEventListener(this)
        if (this::inspectorWindow.isInitialized) {
            hideHighlight()
            inspectorWindow.isVisible = false
            inspectorWindow.dispose()
        }
        hideHighlight()
    }

    override fun eventDispatched(event: AWTEvent?) = when (event?.id) {
        MouseEvent.MOUSE_CLICKED -> onMouseClick(event as MouseEvent)
        MouseEvent.MOUSE_MOVED, MouseEvent.MOUSE_EXITED -> onMouseMoved(event as MouseEvent)
        KeyEvent.KEY_RELEASED, KeyEvent.KEY_PRESSED -> onKeyEvent(event as KeyEvent)
        FocusEvent.FOCUS_LOST, FocusEvent.FOCUS_GAINED -> onFocusEvent()
        else -> Unit
    }

    private fun updateHighlighterVisibility() {
        highlighter.isVisible = inspectorWindowFocused || highlightKeyComboPressed
    }

    private fun onFocusEvent() {
        inspectorWindowFocused =
            ::inspectorWindow.isInitialized && KeyboardFocusManager.getCurrentKeyboardFocusManager().focusedWindow == inspectorWindow
        updateHighlighterVisibility()
    }

    private fun onKeyEvent(e: KeyEvent) {
        highlightKeyComboPressed = e.isHighlightKeyComboPressed()
        updateHighlighterVisibility()
    }

    private fun InputEvent.isHighlightKeyComboPressed() = isAltDown && isControlDown

    private fun getTargetComponent(comp: Component?, p: Point): Component? {
        var component = comp
        if (component is Container) {
            component = if (component is RootPaneContainer) {
                convertTarget(p, component, component.contentPane) ?: comp
            } else {
                SwingUtilities.getDeepestComponentAt(component, p.x, p.y)
            }
        }
        return component
    }

    private fun convertTarget(p: Point, component: Component?, contentPane: Component?): Component? {
        if (component == null || contentPane == null) return component
        p.location = SwingUtilities.convertPoint(component, p, contentPane)
        return SwingUtilities.getDeepestComponentAt(contentPane, p.x, p.y)
    }

    private fun onMouseClick(e: MouseEvent) {
        if (!e.isHighlightKeyComboPressed()) return

        val p = e.point
        val targetComponent = getTargetComponent(e.component, e.point)
        if (targetComponent != null) {
            var clickInfo: ClickInfo? = null
            if (targetComponent is JComponent) {
                clickInfo = ClickInfo(
                    getClickInfoProperties(e, targetComponent),
                    targetComponent.createLayoutTag(p)
                )
            }
            lastClickTarget = targetComponent
            showInspector(targetComponent, clickInfo)
            e.consume()
        }
    }

    private fun showInspector(target: Component, clickInfo: ClickInfo?) {
        ensureWindowInitialized(target)
        if (target.getWindow() == inspectorWindow) return

        if (clickInfo != null && clickInfo.properties.isNotEmpty()) {
            inspectorWindow.switchClickInfo(target, clickInfo)
        } else {
            inspectorWindow.switchComponentInfo(target)
        }
        if (!inspectorWindow.isVisible) {
            inspectorWindow.pack()
            inspectorWindow.isVisible = true
        }
        inspectorWindow.toFront()
        inspectorWindow.requestFocus()
    }

    private fun ensureWindowInitialized(target: Component) {
        if (!::inspectorWindow.isInitialized) {
            inspectorWindow = InspectorWindow(target, this)
        }
    }

    internal fun updateHighlightTarget(comp: Component?, p: Point?, tag: LayoutTag? = null) {
        lastHighlightTarget?.removeComponentListener(highlightUpdater)
        val targetComponent = if (p != null) getTargetComponent(comp, p) else comp
        targetComponent?.addComponentListener(highlightUpdater)
        highlighter.targets = when {
            tag != null -> listOf(tag)
            targetComponent != null -> {
                val targetPoint = if (p != null) SwingUtilities.convertPoint(comp, p, targetComponent) else null
                val layoutTag =
                    if (targetPoint != null) targetComponent.createLayoutTag(targetPoint) else targetComponent.createLayoutTag()
                listOf(layoutTag)
            }
            else -> emptyList()
        }
        lastHighlightTarget = targetComponent
    }

    private fun onMouseMoved(e: MouseEvent) {
        if (inspectorWindowFocused) return
        showHighlight(e, e.point)
    }

    private fun showHighlight(e: InputEvent, p: Point) {
        updateWindow(e)
        if (currentHoverWindow !is InspectorWindow) {
            updateHighlightTarget(e.component, p)
        }
    }

    private fun hideHighlight() {
        updateHighlightTarget(null, Point(0, 0))
    }

    private fun updateWindow(e: InputEvent) {
        val window = e.component.getWindow() as? RootPaneContainer
        if (window != currentWindow && window !is InspectorWindow) {
            highlighter.parent?.remove(highlighter)
            currentWindow?.layeredPane?.invalidate()
            currentWindow = window
            window?.layeredPane?.add(highlighter, -1, HIGHLIGHT_LAYER)
        }
        currentHoverWindow = window
    }
}

@Suppress("UNCHECKED_CAST")
private fun getClickInfoProperties(e: MouseEvent, component: Component): List<PropertyBean> {
    if (e.component == null) return emptyList()
    val me = SwingUtilities.convertMouseEvent(e.component, e, component)
    val clickInfo: MutableList<PropertyBean> = mutableListOf()
    when (component) {
        is JList<*> -> {
            val row = component.ui.locationToIndex(component, me.point)
            if (row != -1) {
                val rendererComponent = (component.cellRenderer as ListCellRenderer<Any>)
                    .getListCellRendererComponent(
                        component, component.model.getElementAt(row),
                        row, component.selectionModel.isSelectedIndex(row),
                        component.hasFocus()
                    )
                clickInfo.addAll(ComponentPropertyBeans(rendererComponent).properties.values)
            }
        }
        is JTable -> {
            val row = component.rowAtPoint(me.point)
            val column = component.columnAtPoint(me.point)
            if (row != -1 && column != -1) {
                val rendererComponent = component.getCellRenderer(row, column)
                    .getTableCellRendererComponent(
                        component, component.getValueAt(row, column), component.selectionModel.isSelectedIndex(row),
                        component.hasFocus(), row, column
                    )
                clickInfo.addAll(ComponentPropertyBeans(rendererComponent).properties.values)
            }
        }
        is JTree -> {
            val path: TreePath? = component.getClosestPathForLocation(me.x, me.y)
            if (path != null) {
                val value: Any = path.lastPathComponent
                val rendererComponent = component.cellRenderer.getTreeCellRendererComponent(
                    component, value, component.selectionModel.isPathSelected(path),
                    component.isExpanded(path), component.model.isLeaf(value),
                    component.getRowForPath(path), component.hasFocus()
                )
                clickInfo.addAll(ComponentPropertyBeans(rendererComponent).properties.values)
            }
        }
    }
    return clickInfo
}

fun installInspector() {
    Inspector.install()
}

fun uninstallInspector() {
    Inspector.uninstall()
}
