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

import java.awt.BorderLayout
import javax.swing.JComponent
import javax.swing.JPanel
import kotlin.reflect.KProperty

class BorderLayoutBuilder private constructor(override val component: JPanel) :
    UIBuilder<JPanel>, ContainerConfiguration<JPanel> by ContainerConfigurationImpl(component) {
    val layout: BorderLayout = component.layout as BorderLayout

    constructor() : this(JPanel(BorderLayout()))

    private class LayoutDelegate(val constraint: String, val component: JComponent, val layout: BorderLayout) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): JComponent {
            var comp: JComponent? = layout.getLayoutComponent(constraint) as? JComponent
            if (comp == null) {
                comp = JPanel()
                component.add(comp, constraint)
            }
            return comp
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: JComponent) {
            component.add(value, constraint)
        }
    }

    private fun layout(constraint: String) = LayoutDelegate(constraint, component, layout)

    var north: JComponent by layout(BorderLayout.NORTH)
    var south: JComponent by layout(BorderLayout.SOUTH)
    var east: JComponent by layout(BorderLayout.EAST)
    var west: JComponent by layout(BorderLayout.WEST)
    var center: JComponent by layout(BorderLayout.CENTER)
    var pageStart: JComponent by layout(BorderLayout.PAGE_START)
    var pageEnd: JComponent by layout(BorderLayout.PAGE_END)
    var lineStart: JComponent by layout(BorderLayout.LINE_START)
    var lineEnd: JComponent by layout(BorderLayout.LINE_END)
}
