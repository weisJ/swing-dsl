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

import com.github.weisj.swingdsl.core.binding.ObservableProperty
import javax.swing.JComponent

class ClientPropertyAccessor(@PublishedApi internal val comp: JComponent) {

    inline operator fun <reified T : Any> get(name: String): T? = comp.getClientProperty(name) as? T

    operator fun <T : Any?> set(name: String, value: T) {
        comp.putClientProperty(name, value)
    }
}

@BuilderMarker
class PropertyScope(@PublishedApi internal val comp: JComponent) {
    val client: ClientPropertyAccessor = ClientPropertyAccessor(comp)
}

fun JComponent.properties(init: PropertyScope.() -> Unit) {
    init(PropertyScope(this))
}

class ObservableComponentProperty<T, C : JComponent>(
    private val comp: C,
    private val key: String,
    private val accessor: C.() -> T
) : ObservableProperty<T> {
    override fun get(): T = comp.accessor()

    override fun onChange(callback: (T) -> Unit) {
        comp.addPropertyChangeListener(key) { callback(get()) }
    }
}
