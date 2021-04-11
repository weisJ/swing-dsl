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

import com.github.weisj.swingdsl.config.ContainerConfiguration
import com.github.weisj.swingdsl.config.ContainerConfigurationImpl
import com.github.weisj.swingdsl.laf.WrappedComponent
import javax.swing.JComponent
import javax.swing.JSplitPane

sealed class SplitPaneBuilder constructor(internal val component: JSplitPane) :
    UIBuilder<JSplitPane>,
    ContainerConfiguration<JSplitPane> by ContainerConfigurationImpl(component) {

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
        NONE,
        MIN_LOCATION,
        MAX_LOCATION
    }

    fun clampTo(mode: ClampMode) {
        clampTo = mode
    }

    private var clampTo: ClampMode = ClampMode.NONE

    fun updateDividerLocation() {
        invokeLater {
            applyToComponent {
                dividerLocation = when (clampTo) {
                    ClampMode.NONE -> dividerLocation.coerceIn(minimumDividerLocation, maximumDividerLocation)
                    ClampMode.MIN_LOCATION -> minimumDividerLocation
                    ClampMode.MAX_LOCATION -> maximumDividerLocation
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

    var left by delegate(component::getLeftComponent, component::setLeftComponent)
    var right by delegate(component::getRightComponent, component::setRightComponent)

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

    var top by delegate(component::getTopComponent, component::setTopComponent)
    var bottom by delegate(component::getBottomComponent, component::setBottomComponent)

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
