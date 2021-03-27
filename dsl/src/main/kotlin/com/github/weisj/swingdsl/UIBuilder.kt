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
package com.github.weisj.swingdsl

import com.github.weisj.swingdsl.config.JFrameConfiguration
import com.github.weisj.swingdsl.config.JFrameConfigurationImpl
import com.github.weisj.swingdsl.laf.DefaultWrappedComponent
import com.github.weisj.swingdsl.laf.SelfWrappedComponent
import com.github.weisj.swingdsl.laf.WrappedComponent
import com.github.weisj.swingdsl.style.UIFactory
import java.awt.Component
import java.awt.GridBagLayout
import javax.swing.JComponent
import javax.swing.JFrame
import javax.swing.JPanel

interface UIBuilder<T : JComponent> {
    val component: T
}

@DslMarker
annotation class BuilderMarker

fun frame(init: JFrameConfiguration<JFrame>.() -> Unit): JFrame {
    val frame = JFrame()
    val config = object : JFrameConfigurationImpl<JFrame>(frame) {
        // Override and do nothing to ensure pack() can be called first.
        override var visible: Boolean = false
        override var locationRelativeTo: Component? = null

        init {
            locationByPlatform = true
        }
    }
    config.init()
    frame.pack()
    // setLocationRelativeTo needs to be called after packing for correct placement.
    frame.setLocationRelativeTo(config.locationRelativeTo)
    frame.isVisible = config.visible
    return frame
}

fun borderPanel(init: BorderLayoutBuilder.() -> Unit): WrappedComponent<JPanel> {
    return +BorderLayoutBuilder().apply(init).component
}

fun <T : JComponent> scrollPane(componentProvider: () -> WrappedComponent<T>): WrappedComponent<T> {
    val comp = componentProvider()
    return DefaultWrappedComponent(
        comp.component,
        UIFactory.createScrollPane(comp.container).container
    )
}

inline fun <T : JComponent> centered(compProvider: () -> T): WrappedComponent<T> {
    val comp = compProvider()
    return DefaultWrappedComponent(
        comp,
        JPanel(GridBagLayout()).apply {
            add(comp)
        }
    )
}

inline fun <T : JComponent> component(compProvider: () -> T): WrappedComponent<T> {
    return SelfWrappedComponent(compProvider())
}

operator fun <T : JComponent> T.unaryPlus(): WrappedComponent<T> = SelfWrappedComponent(this)
