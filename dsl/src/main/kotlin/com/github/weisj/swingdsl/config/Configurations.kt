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
package com.github.weisj.swingdsl.config

import com.github.weisj.swingdsl.laf.WrappedComponent
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
import java.lang.IllegalArgumentException
import java.util.*
import javax.swing.JComponent
import javax.swing.JDialog
import javax.swing.JFrame
import javax.swing.JLayeredPane
import javax.swing.JMenuBar
import javax.swing.JPopupMenu
import javax.swing.JWindow
import javax.swing.border.Border

interface ComponentConfiguration<T : Component> {
    var name: String?

    var enabled: Boolean
    var visible: Boolean
    var focusable: Boolean

    var foreground: Color?
    var background: Color?
    var font: Font?

    var cursor: Cursor?
    var locale: Locale

    var preferredSize: Dimension
    var minimumSize: Dimension
    var maximumSize: Dimension

    var orientation: ComponentOrientation

    fun applyToComponent(task: T.() -> Unit)

    companion object {
        operator fun <T : Component> invoke(comp: T): ComponentConfiguration<T> = ComponentConfigurationImpl(comp)
    }
}

interface ContainerConfiguration<T : Container> : ComponentConfiguration<T> {
    val insets: Insets

    companion object {
        operator fun <T : Container> invoke(comp: T): ContainerConfiguration<T> = ContainerConfigurationImpl(comp)
    }
}

interface JComponentConfiguration<T : JComponent> : ContainerConfiguration<T> {
    var opaque: Boolean
    var border: Border?

    var popupMenu: JPopupMenu?
    var inheritsPopupMenu: Boolean

    var toolTipText: String?
    var alignmentX: Float
    var alignmentY: Float
    var autoScrolls: Boolean

    companion object {
        operator fun <T : JComponent> invoke(comp: T): JComponentConfiguration<T> = JComponentConfigurationImpl(comp)
    }
}

interface WindowConfiguration<T : Window> : ContainerConfiguration<T> {

    var alwaysOnTop: Boolean
    val alwaysOnTopSupported: Boolean
    var locationByPlatform: Boolean
    var locationRelativeTo: Component?

    var icons: List<Image>

    var shape: Shape?
    var opacity: Float

    companion object {
        operator fun <T : Window> invoke(comp: T): WindowConfiguration<T> = WindowConfigurationImpl(comp)
    }
}

enum class CloseOperation(val flag: Int) {
    EXIT(JFrame.EXIT_ON_CLOSE),
    DISPOSE(JFrame.DISPOSE_ON_CLOSE),
    HIDE(JFrame.HIDE_ON_CLOSE),
    DO_NOTHING(JFrame.DO_NOTHING_ON_CLOSE);

    companion object {
        internal operator fun invoke(flag: Int) = when (flag) {
            JFrame.EXIT_ON_CLOSE -> EXIT
            JFrame.DISPOSE_ON_CLOSE -> DISPOSE
            JFrame.HIDE_ON_CLOSE -> HIDE
            JFrame.DO_NOTHING_ON_CLOSE -> DO_NOTHING
            else -> throw IllegalArgumentException()
        }
    }
}

interface JFrameConfiguration<T : JFrame> : WindowConfiguration<T> {
    var title: String
    var defaultCloseOperation: CloseOperation
    var contentPane: Container
    var layeredPane: JLayeredPane
    var glassPane: Component
    var menuBar: JMenuBar?

    fun <T : JComponent> content(provider: () -> WrappedComponent<T>): T {
        val wrapped = provider()
        contentPane = wrapped.container
        return wrapped.component
    }

    fun centerOnScreen() {
        locationRelativeTo = null
    }

    companion object {
        operator fun <T : JFrame> invoke(comp: T): JFrameConfiguration<T> = JFrameConfigurationImpl(comp)
    }
}

interface JDialogConfiguration<T : JDialog> : WindowConfiguration<T> {
    var title: String
    var defaultCloseOperation: CloseOperation
    var contentPane: Container
    var layeredPane: JLayeredPane
    var glassPane: Component
    var menuBar: JMenuBar?

    companion object {
        operator fun <T : JDialog> invoke(comp: T): JDialogConfiguration<T> = JDialogConfigurationImpl(comp)
    }
}

interface JWindowConfiguration<T : JWindow> : WindowConfiguration<T> {
    var contentPane: Container
    var layeredPane: JLayeredPane
    var glassPane: Component

    companion object {
        operator fun <T : JWindow> invoke(comp: T): JWindowConfiguration<T> = JWindowConfigurationImpl(comp)
    }
}
