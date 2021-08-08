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
package com.github.weisj.swingdsl.dsl.config

import com.github.weisj.swingdsl.dsl.delegate
import java.awt.Color
import java.awt.Component
import java.awt.ComponentOrientation
import java.awt.Container
import java.awt.Cursor
import java.awt.Dimension
import java.awt.Font
import java.awt.Image
import java.awt.Insets
import java.awt.Shape
import java.awt.Window
import java.util.*
import javax.swing.JComponent
import javax.swing.JDialog
import javax.swing.JFrame
import javax.swing.JLayeredPane
import javax.swing.JMenuBar
import javax.swing.JPopupMenu
import javax.swing.JWindow
import javax.swing.border.Border

internal open class ComponentConfigurationImpl<T : Component>(private val comp: T) : ComponentConfiguration<T> {
    override var name by delegate<String?>(comp::getName, comp::setName)

    override var enabled by delegate(comp::isEnabled, comp::setEnabled)
    override var visible by delegate(comp::isVisible, comp::setVisible)
    override var focusable by delegate(comp::isFocusable, comp::setFocusable)

    override var foreground by delegate<Color?>(comp::getForeground, comp::setForeground)
    override var background by delegate<Color?>(comp::getBackground, comp::setBackground)
    override var font by delegate<Font?>(comp::getFont, comp::setFont)

    override var cursor by delegate<Cursor?>(comp::getCursor, comp::setCursor)
    override var locale by delegate<Locale>(comp::getLocale, comp::setLocale)

    override var preferredSize by delegate<Dimension>(comp::getPreferredSize, comp::setPreferredSize)
    override var minimumSize by delegate<Dimension>(comp::getMinimumSize, comp::setMinimumSize)
    override var maximumSize by delegate<Dimension>(comp::getMaximumSize, comp::setMaximumSize)

    override var orientation by delegate<ComponentOrientation>(
        comp::getComponentOrientation,
        comp::setComponentOrientation
    )

    override fun applyToComponent(task: T.() -> Unit) {
        task(comp)
    }
}

internal open class ContainerConfigurationImpl<T : Container>(private val comp: T) :
    ComponentConfigurationImpl<T>(comp),
    ContainerConfiguration<T> {
    override val insets by delegate<Insets>(comp::getInsets)
}

internal class JComponentConfigurationImpl<T : JComponent>(private val comp: T) :
    ContainerConfiguration<T> by ContainerConfigurationImpl(comp), JComponentConfiguration<T> {
    override var opaque by delegate(comp::isOpaque, comp::setOpaque)
    override var border by delegate<Border?>(comp::getBorder, comp::setBorder)

    override var popupMenu by delegate<JPopupMenu?>(comp::getComponentPopupMenu, comp::setComponentPopupMenu)
    override var inheritsPopupMenu by delegate(comp::getInheritsPopupMenu, comp::setInheritsPopupMenu)

    override var toolTipText by delegate<String?>(comp::getToolTipText, comp::setToolTipText)

    override var alignmentX by delegate(comp::getAlignmentX, comp::setAlignmentX)
    override var alignmentY by delegate(comp::getAlignmentY, comp::setAlignmentY)
    override var autoScrolls by delegate(comp::getAutoscrolls, comp::setAutoscrolls)
}

internal open class WindowConfigurationImpl<T : Window>(private val window: T) :
    ContainerConfigurationImpl<T>(window),
    WindowConfiguration<T> {

    override var alwaysOnTop by delegate(window::isAlwaysOnTop, window::setAlwaysOnTop)
    override val alwaysOnTopSupported by delegate(window::isAlwaysOnTopSupported)
    override var locationByPlatform by delegate(window::isLocationByPlatform, window::setLocationByPlatform)

    override var locationRelativeTo: Component? = null
        set(value) {
            window.setLocationRelativeTo(value)
            field = value
        }

    override var icons by delegate<List<Image>>(window::getIconImages, window::setIconImages)

    override var shape by delegate<Shape?>(window::getShape, window::setShape)
    override var opacity by delegate(window::getOpacity, window::setOpacity)
}

internal open class JFrameConfigurationImpl<T : JFrame>(private val frame: T) :
    WindowConfigurationImpl<T>(frame),
    JFrameConfiguration<T> {

    override var defaultCloseOperation: CloseOperation = CloseOperation(frame.defaultCloseOperation)
        set(value) {
            frame.defaultCloseOperation = value.flag
            field = value
        }

    init {
        if (frame.title == null) frame.title = ""
    }

    override var title by delegate<String>(frame::getTitle, frame::setTitle)
    override var contentPane by delegate<Container>(frame::getContentPane, frame::setContentPane)
    override var layeredPane by delegate<JLayeredPane>(frame::getLayeredPane, frame::setLayeredPane)
    override var glassPane by delegate<Component>(frame::getGlassPane, frame::setGlassPane)
    override var menuBar by delegate<JMenuBar?>(frame::getJMenuBar, frame::setJMenuBar)
}

internal open class JDialogConfigurationImpl<T : JDialog>(private val dialog: T) :
    WindowConfigurationImpl<T>(dialog),
    JDialogConfiguration<T> {

    override var defaultCloseOperation: CloseOperation = CloseOperation(dialog.defaultCloseOperation)
        set(value) {
            dialog.defaultCloseOperation = value.flag
            field = value
        }

    init {
        if (dialog.title == null) dialog.title = ""
    }

    override var title by delegate<String>(dialog::getTitle, dialog::setTitle)
    override var contentPane by delegate<Container>(dialog::getContentPane, dialog::setContentPane)
    override var layeredPane by delegate<JLayeredPane>(dialog::getLayeredPane, dialog::setLayeredPane)
    override var glassPane by delegate<Component>(dialog::getGlassPane, dialog::setGlassPane)
    override var menuBar by delegate<JMenuBar?>(dialog::getJMenuBar, dialog::setJMenuBar)
}

internal open class JWindowConfigurationImpl<T : JWindow>(private val window: T) :
    WindowConfigurationImpl<T>(window),
    JWindowConfiguration<T> {

    override var contentPane by delegate<Container>(window::getContentPane, window::setContentPane)
    override var layeredPane by delegate<JLayeredPane>(window::getLayeredPane, window::setLayeredPane)
    override var glassPane by delegate<Component>(window::getGlassPane, window::setGlassPane)
}
