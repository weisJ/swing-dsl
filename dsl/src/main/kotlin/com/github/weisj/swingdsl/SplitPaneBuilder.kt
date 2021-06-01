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

import com.github.weisj.swingdsl.config.JComponentConfiguration
import com.github.weisj.swingdsl.config.JComponentConfigurationImpl
import com.github.weisj.swingdsl.laf.WrappedComponent
import java.awt.Component
import javax.swing.JComponent
import javax.swing.JSplitPane
import javax.swing.event.AncestorEvent
import javax.swing.event.AncestorListener

sealed class SplitPaneBuilder constructor(internal val component: JSplitPane) :
    UIBuilder<JSplitPane>,
    JComponentConfiguration<JSplitPane> by JComponentConfigurationImpl(component) {

    private var initialPosition: Double = 0.5
    private val initialPositionListener = lazy {
        val listener = object : AncestorListener {
            override fun ancestorAdded(event: AncestorEvent?) {
                component.setDividerLocation(initialPosition)
                component.removeAncestorListener(this)
            }

            override fun ancestorRemoved(event: AncestorEvent?) = Unit

            override fun ancestorMoved(event: AncestorEvent?) = Unit
        }
        component.addAncestorListener(listener)
        listener
    }

    init {
        component.addPropertyChangeListener {
            if ("UI" == it.propertyName || "ancestor" == it.propertyName) {
                updateDividerLocation()
            }
        }
    }

    override fun build(): JSplitPane {
        return component
    }

    enum class ClampMode {
        DEFAULT,
        MIN_LOCATION,
        MAX_LOCATION
    }

    fun initialPosition(position: Double) {
        initialPosition = position
        if (!initialPositionListener.isInitialized())
            initialPositionListener.value
    }

    fun clampTo(mode: ClampMode) {
        clampTo = mode
    }

    private var clampTo: ClampMode = ClampMode.DEFAULT

    fun updateDividerLocation() {
        invokeLater {
            applyToComponent {
                dividerLocation = when (clampTo) {
                    ClampMode.DEFAULT -> dividerLocation.coerceIn(minimumDividerLocation, maximumDividerLocation)
                    ClampMode.MIN_LOCATION -> dividerLocation.coerceAtLeast(minimumDividerLocation)
                    ClampMode.MAX_LOCATION -> dividerLocation.coerceAtMost(maximumDividerLocation)
                }
            }
        }
    }
}

class HorizontalSplitPaneBuilder internal constructor(component: JSplitPane = JSplitPane()) :
    SplitPaneBuilder(component) {

    init {
        component.dividerLocation = JSplitPane.HORIZONTAL_SPLIT
    }

    var left: Component? by delegate(component::getLeftComponent, component::setLeftComponent)
    var right: Component? by delegate(component::getRightComponent, component::setRightComponent)

    inline fun <T : JComponent> left(componentProvider: ComponentBuilderScope.() -> WrappedComponent<T>): T {
        val comp = componentProvider(ComponentBuilderScope)
        left = comp.container
        return comp.component
    }

    inline fun <T : JComponent> right(componentProvider: ComponentBuilderScope.() -> WrappedComponent<T>): T {
        val comp = componentProvider(ComponentBuilderScope)
        right = comp.container
        return comp.component
    }
}

class VerticalSplitPaneBuilder internal constructor(component: JSplitPane = JSplitPane()) :
    SplitPaneBuilder(component) {

    init {
        component.dividerLocation = JSplitPane.VERTICAL_SPLIT
    }

    var top: Component? by delegate(component::getTopComponent, component::setTopComponent)
    var bottom: Component? by delegate(component::getBottomComponent, component::setBottomComponent)

    inline fun <T : JComponent> left(componentProvider: ComponentBuilderScope.() -> WrappedComponent<T>): T {
        val comp = componentProvider(ComponentBuilderScope)
        top = comp.container
        return comp.component
    }

    inline fun <T : JComponent> right(componentProvider: ComponentBuilderScope.() -> WrappedComponent<T>): T {
        val comp = componentProvider(ComponentBuilderScope)
        bottom = comp.container
        return comp.component
    }
}
