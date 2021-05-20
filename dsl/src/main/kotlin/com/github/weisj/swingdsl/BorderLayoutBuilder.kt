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

import com.github.weisj.swingdsl.component.DefaultJPanel
import com.github.weisj.swingdsl.config.JComponentConfiguration
import com.github.weisj.swingdsl.config.JComponentConfigurationImpl
import com.github.weisj.swingdsl.laf.WrappedComponent
import java.awt.BorderLayout
import javax.swing.JComponent
import javax.swing.JPanel
import kotlin.reflect.KProperty

@BuilderMarker
class BorderLayoutBuilder internal constructor(private val component: JPanel) :
    UIBuilder<JPanel>, JComponentConfiguration<JPanel> by JComponentConfigurationImpl(component) {

    init {
        component.layout = BorderLayout()
    }

    val layout: BorderLayout = component.layout as BorderLayout

    constructor() : this(DefaultJPanel(BorderLayout()))

    override fun build(): JPanel = component

    private class LayoutDelegate(val constraint: String, val component: JComponent, val layout: BorderLayout) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): JComponent {
            var comp: JComponent? = layout.getLayoutComponent(constraint) as? JComponent
            if (comp == null) {
                comp = DefaultJPanel()
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

    inline fun <T : JComponent> north(componentProvider: ComponentBuilderScope.() -> WrappedComponent<T>): T {
        val comp = componentProvider(ComponentBuilderScope)
        north = comp.container
        return comp.component
    }

    inline fun <T : JComponent> south(componentProvider: ComponentBuilderScope.() -> WrappedComponent<T>): T {
        val comp = componentProvider(ComponentBuilderScope)
        south = comp.container
        return comp.component
    }

    inline fun <T : JComponent> east(componentProvider: ComponentBuilderScope.() -> WrappedComponent<T>): T {
        val comp = componentProvider(ComponentBuilderScope)
        east = comp.container
        return comp.component
    }

    inline fun <T : JComponent> west(componentProvider: ComponentBuilderScope.() -> WrappedComponent<T>): T {
        val comp = componentProvider(ComponentBuilderScope)
        west = comp.container
        return comp.component
    }

    inline fun <T : JComponent> center(componentProvider: ComponentBuilderScope.() -> WrappedComponent<T>): T {
        val comp = componentProvider(ComponentBuilderScope)
        center = comp.container
        return comp.component
    }

    inline fun <T : JComponent> pageStart(componentProvider: ComponentBuilderScope.() -> WrappedComponent<T>): T {
        val comp = componentProvider(ComponentBuilderScope)
        pageStart = comp.container
        return comp.component
    }

    inline fun <T : JComponent> pageEnd(componentProvider: ComponentBuilderScope.() -> WrappedComponent<T>): T {
        val comp = componentProvider(ComponentBuilderScope)
        pageEnd = comp.container
        return comp.component
    }

    inline fun <T : JComponent> lineStart(componentProvider: ComponentBuilderScope.() -> WrappedComponent<T>): T {
        val comp = componentProvider(ComponentBuilderScope)
        lineStart = comp.container
        return comp.component
    }

    inline fun <T : JComponent> lineEnd(componentProvider: ComponentBuilderScope.() -> WrappedComponent<T>): T {
        val comp = componentProvider(ComponentBuilderScope)
        lineEnd = comp.container
        return comp.component
    }
}
