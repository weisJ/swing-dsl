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
package com.github.weisj.swingdsl.core.binding

import com.github.weisj.swingdsl.core.condition.isEqualTo
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class BindingTests {

    @Test
    fun `combining observables doesn't increase listener count`() {
        val o1 = TestObservable()
        val o2 = combinedProperty(o1, TestObservable()) { l, _ -> l }
        val o3 = combinedProperty(o2, TestObservable()) { l, _ -> l }
        val o4 = combinedProperty(o3, TestObservable()) { l, _ -> l }

        assertEquals(0, o1.listenerKeys.size)

        val key = Any()
        o4.onChange(key) { }
        assertEquals(1, o1.listenerKeys.size)

        o4.onChange(key) { }
        assertEquals(1, o1.listenerKeys.size)

        o4.onChange(Any()) { }
        assertEquals(1, o1.listenerKeys.size)

        o3.onChange(Any()) { }
        assertEquals(1, o1.listenerKeys.size)

        o2.onChange(key) { }
        assertEquals(1, o1.listenerKeys.size)

        o1.onChange(Any()) { }
        assertEquals(2, o1.listenerKeys.size)
    }

    @Test
    fun `deriving observables doesn't increase listener count`() {
        val o1 = TestObservable()
        val o2 = o1.derive { it }
        val o3 = o2.derive { it }
        val o4 = o3.derive { it }

        assertEquals(0, o1.listenerKeys.size)

        val key = Any()
        o4.onChange(key) { }
        assertEquals(1, o1.listenerKeys.size)

        o4.onChange(key) { }
        assertEquals(1, o1.listenerKeys.size)

        o4.onChange(Any()) { }
        assertEquals(1, o1.listenerKeys.size)

        o3.onChange(Any()) { }
        assertEquals(1, o1.listenerKeys.size)

        o2.onChange(key) { }
        assertEquals(1, o1.listenerKeys.size)

        o1.onChange(Any()) { }
        assertEquals(2, o1.listenerKeys.size)
    }

    @Test
    fun `removing listener from combined property removes change tracker`() {
        val o1 = TestObservable()
        val o2 = combinedProperty(o1, TestObservable()) { l, _ -> l }
        val o3 = combinedProperty(o2, TestObservable()) { l, _ -> l }

        val key = Any()
        o2.onChange(key) { }
        assertEquals(1, o1.listenerKeys.size)

        o2.removeCallback(key)
        assertEquals(0, o1.listenerKeys.size)

        o3.onChange(key) { }
        assertEquals(1, o1.listenerKeys.size)

        val key2 = Any()
        o2.onChange(key2) { }
        assertEquals(1, o1.listenerKeys.size)

        o3.removeCallback(key)
        assertEquals(1, o1.listenerKeys.size)

        o2.removeCallback(key2)
        assertEquals(0, o1.listenerKeys.size)
    }

    @Test
    fun `removing listener from derived property removes change tracker`() {
        val o1 = TestObservable()
        val o2 = o1.derive { it }
        val o3 = o2.derive { it }

        val key = Any()
        o2.onChange(key) { }
        assertEquals(1, o1.listenerKeys.size)

        o2.removeCallback(key)
        assertEquals(0, o1.listenerKeys.size)

        o3.onChange(key) { }
        assertEquals(1, o1.listenerKeys.size)

        val key2 = Any()
        o2.onChange(key2) { }
        assertEquals(1, o1.listenerKeys.size)

        o3.removeCallback(key)
        assertEquals(1, o1.listenerKeys.size)

        o2.removeCallback(key2)
        assertEquals(0, o1.listenerKeys.size)
    }

    @Test
    fun `combined property reports correct value`() {
        val o1 = observableProperty("A")
        val o2 = observableProperty("A")
        val o3 = o1 + o2
        var value: String? = null
        o3.onChange { value = it }

        o1.set("B")
        assertEquals("BA", value)
        o2.set("C")
        assertEquals("BC", value)

        val o4 = o3 + o3
        var value2: String? = null
        o4.onChange { value2 = it }
        o1.set("D")
        assertEquals("DCDC", value2)
    }

    @Test
    fun `derived cache reports correct value`() {
        val expected = "BBBBB"
        val o1 = observableProperty("A")
        val o2 = o1.derive { it.length }
        val o3 = o2 isEqualTo expected.length

        var value2: Int? = null
        var value3: Boolean? = null
        o3.onChange { value3 = it }
        o2.onChange { value2 = it }

        o1.set("CCC")
        assertEquals(3, value2)
        assertEquals(false, value3)

        o1.set(expected)
        assertEquals(expected.length, value2)
        assertEquals(true, value3)
    }

    internal class TestObservable : ObservableMutableProperty<Any> {
        internal val listenerKeys = mutableSetOf<Any?>()
        private var value = Any()
        var log = false
        override fun onChange(observeKey: Any?, callback: (Any) -> Unit) {
            if (listenerKeys.add(observeKey) and log) {
                println(observeKey)
                Thread.dumpStack()
            }
        }

        override fun removeCallback(observeKey: Any?) {
            listenerKeys.remove(observeKey)
        }

        override fun get(): Any = value

        override fun set(value: Any) {
            this.value = value
        }
    }
}
