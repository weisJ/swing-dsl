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
import com.github.weisj.swingdsl.component.ScrollRedirector
import com.github.weisj.swingdsl.config.CloseOperation
import com.github.weisj.swingdsl.config.JComponentConfiguration
import com.github.weisj.swingdsl.config.JFrameConfiguration
import com.github.weisj.swingdsl.config.JFrameConfigurationImpl
import com.github.weisj.swingdsl.laf.DefaultWrappedComponent
import com.github.weisj.swingdsl.laf.ScrollableView
import com.github.weisj.swingdsl.laf.SelfWrappedComponent
import com.github.weisj.swingdsl.laf.WrappedComponent
import com.github.weisj.swingdsl.style.UIFactory
import java.awt.BorderLayout
import java.awt.Component
import java.awt.Dimension
import java.awt.GridBagLayout
import java.lang.Integer.max
import java.lang.Integer.min
import javax.swing.JComponent
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JSplitPane

interface UIBuilder<T : JComponent> {
    fun build(): T
}

@DslMarker
annotation class BuilderMarker

@BuilderMarker
object ComponentBuilderScope

fun frame(init: JFrameConfiguration<JFrame>.() -> Unit): JFrame {
    val frame = JFrame()
    val config = object : JFrameConfigurationImpl<JFrame>(frame) {
        // Override and do nothing to ensure pack() can be called first.
        override var visible: Boolean = false
        override var locationRelativeTo: Component? = null

        init {
            defaultCloseOperation = CloseOperation.EXIT
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

fun verticalSplit(init: VerticalSplitPaneBuilder.() -> Unit): WrappedComponent<JSplitPane> {
    return +VerticalSplitPaneBuilder().apply(init).build()
}

fun horizontalSplit(init: HorizontalSplitPaneBuilder.() -> Unit): WrappedComponent<JSplitPane> {
    return +HorizontalSplitPaneBuilder().apply(init).build()
}

fun borderPanel(init: BorderLayoutBuilder.() -> Unit): WrappedComponent<JPanel> {
    return +BorderLayoutBuilder().apply(init).build()
}

fun configureBorderLayout(panel: JPanel, init: BorderLayoutBuilder.() -> Unit) {
    BorderLayoutBuilder(panel).init()
}

fun <T : JComponent> scrollPane(
    scrollInit: JScrollPane.() -> Unit = {},
    componentProvider: ComponentBuilderScope.() -> WrappedComponent<T>
): WrappedComponent<T> {
    val comp = componentProvider(ComponentBuilderScope)
    val scroll = UIFactory.createScrollPane(ScrollRedirector(comp.container))

    val scrollable: ScrollableView? = when {
        comp is ScrollableView -> comp
        comp.component is ScrollableView -> comp.component as ScrollableView
        comp.container is ScrollableView -> comp.container as ScrollableView
        else -> null
    }
    val scrollPane = scroll.component
    if (scrollable != null) {
        scrollPane.horizontalScrollBar.apply {
            unitIncrement = scrollable.horizontalUnitIncrement
            blockIncrement = scrollable.horizontalBlockIncrement
        }
        scrollPane.verticalScrollBar.apply {
            unitIncrement = scrollable.verticalUnitIncrement
            blockIncrement = scrollable.verticalBlockIncrement
        }
    }
    scrollPane.scrollInit()

    return DefaultWrappedComponent(comp.component, scroll.container)
}

fun <T : JComponent> wrap(
    init: JComponent.() -> Unit = {},
    componentProvider: ComponentBuilderScope.() -> WrappedComponent<T>
): WrappedComponent<T> {
    val wrapper = DefaultJPanel(BorderLayout())
    val comp = componentProvider(ComponentBuilderScope)
    wrapper.add(comp.container, BorderLayout.CENTER)
    wrapper.init()
    return DefaultWrappedComponent(comp.component, wrapper)
}

@Suppress("kotlin:S107")
// Most use cases won't actually make use of all parameters
fun <T : JComponent> clampSizes(
    maxMinWidth: Int = -1,
    maxMinHeight: Int = -1,
    minMaxWidth: Int = -1,
    minMaxHeight: Int = -1,
    usePreferredSizeForMinimum: Boolean = true,
    usePreferredSizeForMaximum: Boolean = true,
    clampBy: JComponent? = null,
    componentProvider: ComponentBuilderScope.() -> WrappedComponent<T>
): WrappedComponent<T> {
    val comp = componentProvider(ComponentBuilderScope)
    if (minMaxWidth < 0 && minMaxHeight < 0 && maxMinWidth < 0 && maxMinHeight < 0) return comp
    val clampComp = clampBy ?: comp.component
    val wrapper = object : DefaultJPanel(BorderLayout()) {
        override fun getMinimumSize(): Dimension {
            val baseSize = if (usePreferredSizeForMinimum) clampComp.preferredSize else clampComp.minimumSize
            if (maxMinWidth > 0) baseSize.width = min(baseSize.width, maxMinWidth)
            if (maxMinHeight > 0) baseSize.height = min(baseSize.height, maxMinHeight)
            return baseSize
        }

        override fun getMaximumSize(): Dimension {
            val baseSize = if (usePreferredSizeForMaximum) clampComp.preferredSize else clampComp.maximumSize
            if (minMaxWidth > 0) baseSize.width = max(baseSize.width, minMaxWidth)
            if (minMaxHeight > 0) baseSize.height = max(baseSize.height, minMaxHeight)
            return baseSize
        }
    }
    wrapper.add(comp.container, BorderLayout.CENTER)
    return DefaultWrappedComponent(comp.component, wrapper)
}

inline fun <T : JComponent> centered(compProvider: ComponentBuilderScope.() -> WrappedComponent<T>): WrappedComponent<T> {
    val comp = compProvider(ComponentBuilderScope)
    return DefaultWrappedComponent(
        comp.component,
        DefaultJPanel(GridBagLayout()).apply {
            add(comp.container)
        }
    )
}

fun <T : JComponent> layered(comp: WrappedComponent<T>, init: LayerBuilder.() -> Unit): WrappedComponent<T> {
    return DefaultWrappedComponent(comp.component, LayerBuilder(comp.container).also(init).build())
}

inline fun <T : JComponent> component(compProvider: () -> T): WrappedComponent<T> {
    return SelfWrappedComponent(compProvider())
}

@Suppress("UNCHECKED_CAST")
operator fun <T : JComponent> T.unaryPlus(): WrappedComponent<T> {
    return if (this is WrappedComponent<*> && this.component == this) this as WrappedComponent<T>
    else SelfWrappedComponent(this)
}

fun <T : JComponent> T.configure(action: JComponentConfiguration<T>.() -> Unit) {
    JComponentConfiguration(this).action()
}

fun <T : JComponent> WrappedComponent<T>.configureContainer(action: JComponentConfiguration<JComponent>.() -> Unit): WrappedComponent<T> {
    container.configure(action)
    return this
}

fun <T : JComponent> WrappedComponent<T>.configureComponent(action: JComponentConfiguration<T>.() -> Unit): WrappedComponent<T> {
    component.configure(action)
    return this
}
