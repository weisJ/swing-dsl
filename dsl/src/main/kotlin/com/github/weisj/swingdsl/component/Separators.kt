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

import com.github.weisj.swingdsl.bindEvent
import com.github.weisj.swingdsl.border.emptyBorder
import com.github.weisj.swingdsl.core.text.Text
import com.github.weisj.swingdsl.core.text.TextLabel
import com.github.weisj.swingdsl.core.text.emptyText
import com.github.weisj.swingdsl.laf.CollapsibleComponent
import com.github.weisj.swingdsl.laf.VisualPaddingProvider
import com.github.weisj.swingdsl.laf.VisualPaddingProvider.VISUAL_PADDING_PROP
import com.github.weisj.swingdsl.layout.Row
import com.github.weisj.swingdsl.listeners.ClickListener
import com.github.weisj.swingdsl.toKeyStroke
import java.awt.Color
import java.awt.Component
import java.awt.Cursor
import java.awt.Graphics
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import java.awt.event.FocusEvent
import java.awt.event.FocusListener
import java.awt.event.KeyEvent
import java.awt.event.MouseEvent
import javax.swing.Icon
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JSeparator
import javax.swing.SwingConstants

open class TitledSeparator(title: Text?) : JComponent() {

    val label: JLabel = TextLabel(title ?: emptyText())
    private val separator = JSeparator(SwingConstants.HORIZONTAL)
    var color: Color = label.foreground ?: Color.BLACK
    var disabledColor: Color = color

    init {
        isOpaque = false
        layout = GridBagLayout()
        border = emptyBorder(7, 0, 5, 0)
        add(
            label,
            GridBagConstraints(
                0, 0, 1, 1, 0.0, 1.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                Insets(0, 0, 0, 0), 0, 0
            )
        )
        add(
            separator,
            GridBagConstraints(
                1, 0, GridBagConstraints.REMAINDER, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                Insets(2, 6, 0, 0), 0, 0
            )
        )
        isEnabled = true
    }

    final override fun add(comp: Component, constraints: Any?) {
        super.add(comp, constraints)
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        label.isEnabled = enabled
        separator.isEnabled = enabled
        separator.foreground = if (enabled) color else disabledColor
    }
}

class CollapsibleTitledSeparator(title: Text) : TitledSeparator(title), CollapsibleComponent {

    private var isExpanded: Boolean = true
    lateinit var row: Row

    var expandedIcon: Icon = EmptyIcon
    var collapsedIcon: Icon = EmptyIcon
    var disabledExpandedIcon: Icon = EmptyIcon
    var disabledCollapsedIcon: Icon = EmptyIcon

    private lateinit var collapseCallback: Runnable
    private lateinit var expandCallback: Runnable

    override fun setCollapseCallback(callback: Runnable) {
        collapseCallback = callback
    }

    override fun setExpandCallback(callback: Runnable) {
        expandCallback = callback
    }

    override fun getComponent(): JComponent = this

    override fun expand() = update(true)

    override fun collapse() = update(false)

    override fun setEnabled(enabled: Boolean) {
        val collapse = !enabled && isEnabled && isExpanded
        super.setEnabled(enabled)
        if (collapse) collapse()
    }

    init {
        isFocusable = true
        addFocusListener(object : FocusListener {
            override fun focusGained(e: FocusEvent?) = repaint()
            override fun focusLost(e: FocusEvent?) = repaint()
        })
        cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        updateIcon(isExpanded)
        object : ClickListener() {
            override fun onClick(event: MouseEvent, clickCount: Int): Boolean {
                if (!isEnabled) return false
                update(!isExpanded)
                return true
            }
        }.installOn(this)
        bindEvent("toggle", KeyEvent.VK_ENTER.toKeyStroke()) {
            if (isExpanded) collapse() else expand()
        }
        bindEvent("collapse", KeyEvent.VK_LEFT.toKeyStroke(), KeyEvent.VK_UP.toKeyStroke()) {
            collapse()
        }
        bindEvent("expand", KeyEvent.VK_RIGHT.toKeyStroke(), KeyEvent.VK_DOWN.toKeyStroke()) {
            expand()
        }
    }

    private fun update(expand: Boolean) {
        isExpanded = expand
        if (expand) {
            if (::expandCallback.isInitialized) expandCallback.run()
        } else {
            if (::collapseCallback.isInitialized) collapseCallback.run()
        }
        updateIcon(expand)
        doLayout()
        repaint()
    }

    private fun updateIcon(expand: Boolean) {
        label.icon = if (expand) expandedIcon else collapsedIcon
        label.disabledIcon = if (expand) disabledExpandedIcon else disabledCollapsedIcon
        updateVisualPadding()
    }

    override fun setVisible(aFlag: Boolean) {
        super.setVisible(aFlag)
        updateVisualPadding()
    }

    private fun updateVisualPadding() {
        val leftPad = when (val targetIcon = if (isEnabled) label.icon else label.disabledIcon) {
            is VisualPaddingProvider -> targetIcon.getVisualPaddings(label).left
            else -> targetIcon.iconWidth / 4
        }
        putClientProperty(VISUAL_PADDING_PROP, Insets(0, leftPad, 0, 0))
    }
}

object EmptyIcon : Icon {
    override fun paintIcon(c: Component?, g: Graphics?, x: Int, y: Int) {
        /* do nothing*/
    }

    override fun getIconWidth(): Int = 0

    override fun getIconHeight(): Int = 0
}
