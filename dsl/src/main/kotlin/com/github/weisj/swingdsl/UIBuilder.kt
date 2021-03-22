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

import java.awt.Component
import java.awt.GridBagLayout
import javax.swing.JComponent
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.SwingUtilities

interface UIBuilder<T : JComponent> {
    val component: T
}

fun frame(init: JFrameConfiguration<JFrame>.() -> Unit): JFrame {
    val frame = JFrame()
    val config = object : JFrameConfiguration<JFrame> by JFrameConfiguration(frame) {
        // Override and do nothing to ensure pack() can be called first.
        override var visible: Boolean = false
        override var locationRelativeTo: Component? = null
    }
    config.init()
    frame.pack()
    // setLocationRelativeTo needs to be called after packing for correct placement.
    frame.setLocationRelativeTo(config.locationRelativeTo)
    frame.isVisible = config.visible
    return frame
}

fun invokeLater(action: () -> Unit) = SwingUtilities.invokeLater(action)

fun borderPanel(init: BorderLayoutBuilder.() -> Unit): JPanel {
    return BorderLayoutBuilder().apply(init).component
}

fun centered(compProvider: () -> JComponent): JPanel = centered(compProvider())

fun centered(comp: JComponent): JPanel {
    return JPanel(GridBagLayout()).apply {
        add(comp)
    }
}
