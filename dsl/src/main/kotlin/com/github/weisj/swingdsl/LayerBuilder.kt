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

import com.github.weisj.swingdsl.components.FillingLayeredPane
import com.github.weisj.swingdsl.config.JComponentConfiguration
import com.github.weisj.swingdsl.config.JComponentConfigurationImpl
import com.github.weisj.swingdsl.laf.WrappedComponent
import java.awt.Component
import javax.swing.JComponent
import javax.swing.JLayeredPane

@BuilderMarker
class LayerBuilder private constructor(
    private val component: FillingLayeredPane
) : UIBuilder<JLayeredPane>,
    JComponentConfiguration<JLayeredPane> by JComponentConfigurationImpl(component) {

    val layers: LayerAccessor = LayerAccessor(component)

    constructor(comp: JComponent) : this(FillingLayeredPane(comp))

    override fun build(): JLayeredPane = component
}

class LayerAccessor(private val component: JLayeredPane) {

    operator fun get(layer: Int): Array<Component> {
        return component.getComponentsInLayer(layer)
    }

    operator fun get(position: Pair<Int, Int>): Component {
        return this[position.first][position.second]
    }

    operator fun <T : JComponent> set(layer: Int, comp: WrappedComponent<T>) {
        set(layer, comp.container)
    }

    operator fun <T : JComponent> set(position: Pair<Int, Int>, comp: WrappedComponent<T>) {
        set(position, comp.container)
    }

    operator fun set(layer: Int, comp: Component) {
        component.add(comp, layer, -1)
    }

    operator fun set(position: Pair<Int, Int>, comp: Component) {
        component.add(comp, position.first, -1)
        component.setLayer(comp, position.first, position.second)
    }
}
