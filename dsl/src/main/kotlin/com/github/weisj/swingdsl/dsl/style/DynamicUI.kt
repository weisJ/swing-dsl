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
package com.github.weisj.swingdsl.dsl.style

import com.github.weisj.swingdsl.core.text.Text
import java.awt.Color
import java.awt.Component
import java.awt.Font
import java.beans.PropertyChangeEvent
import java.util.*
import java.util.function.BiFunction
import javax.swing.JComponent
import javax.swing.UIManager
import javax.swing.plaf.ColorUIResource
import javax.swing.plaf.FontUIResource
import javax.swing.plaf.UIResource

private typealias UIUpdate = (Component) -> Unit

class DynamicUI private constructor() {
    companion object {
        private class IndexedListener(private val key: Any, val listener: () -> Unit) {
            override fun hashCode(): Int = key.hashCode()
            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other !is IndexedListener) return false
                return key == other.key
            }
        }
        private val freeListeners: MutableSet<IndexedListener> = Collections.newSetFromMap(WeakHashMap())
        private val listeners: MutableMap<Component, MutableList<UIUpdate>> = WeakHashMap()

        /**
         * Attaches a configuration action that is executed when Look and Feel changes.
         *
         *
         * Note: the action is executed when `withDynamic` is called, and the action is executed even
         * if the new and the old LaFs are the same.
         *
         *
         * @param component component to update
         * @param onUpdateUi action to run (immediately and when look and feel changes)
         * @param <T> type of the component
         * @return input component
         */
        @JvmStatic
        fun <T : Component> withDynamic(
            component: T,
            onUpdateUi: (T) -> Unit
        ): T {
            // Explicit component update is required since the component already exists
            // and we can't want to wait for the next LaF change
            onUpdateUi(component)
            synchronized(listeners) {
                listeners.compute(
                    component,
                    BiFunction { _, v ->
                        @Suppress("UNCHECKED_CAST")
                        v ?: return@BiFunction mutableListOf(onUpdateUi as UIUpdate)
                        val res: MutableList<UIUpdate> = if (v.size == 1) ArrayList(v) else v
                        // noinspection unchecked
                        @Suppress("UNCHECKED_CAST")
                        res.add(onUpdateUi as UIUpdate)
                        res
                    }
                )
            }
            return component
        }

        fun registerUIListener(key: Any, onLafChange: () -> Unit) {
            freeListeners.add(IndexedListener(key, onLafChange))
        }

        fun unregisterUIListener(key: Any) {
            freeListeners.add(IndexedListener(key) { })
        }

        fun <T : Component> withBoldFont(component: T): T {
            return withTransformedFont(component) { f: Font -> f.deriveFont(Font.BOLD) }
        }

        fun <T : Component> withTransformedFont(
            component: T,
            fontMapper: (Font) -> Font
        ): T {
            // Ensure the font is a UIResource to guarantee it gets replaced when changing the LaF.
            return withDynamic(component) { c: T ->
                c.font = fontMapper(c.font).asUIResource()
            }
        }

        fun <T : JComponent> withTooltipText(component: T, text: Text): T {
            return withDynamic(component) { c: T -> c.toolTipText = text.get() }
        }

        private fun updateComponent(component: Component) {
            synchronized(listeners) {
                val list = listeners[component]
                    ?: return
                for (action in list) {
                    action(component)
                }
            }
        }

        fun updateAll() {
            freeListeners.forEach { it.listener() }
            listeners.forEach { (t, u) ->
                u.forEach { it(t) }
            }
        }

        init {
            UIManager.addPropertyChangeListener { e: PropertyChangeEvent? ->
                e ?: return@addPropertyChangeListener
                val key: String = e.propertyName
                if ("lookAndFeel" == key) {
                    listeners.keys.forEach { updateComponent(it) }
                }
            }
        }
    }
}

fun Font.asUIResource(): Font = if (this is UIResource) this else FontUIResource(this)
fun Font.stripUIResource(): Font = if (this !is UIResource) this else NonUIResourceFont(this)

fun Color.asUIResource(): Color = if (this is UIResource) this else ColorUIResource(this)
fun Color.stripUIResource(): Color = if (this !is UIResource) this else Color(red, green, blue, alpha)

private class NonUIResourceFont(font: Font) : Font(font)
