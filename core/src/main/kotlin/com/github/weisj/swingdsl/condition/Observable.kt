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
package com.github.weisj.swingdsl.condition

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1

typealias BiConsumer<T> = (T, T) -> Unit

class ObservableManager<T> {
    private val properties = mutableMapOf<String, Any?>()
    private val listeners = mutableMapOf<String, MutableList<BiConsumer<Any?>>>()

    private fun updateValue(name: String, old: Any?, new: Any?) {
        if (old != new) {
            listeners[name]?.forEach { it(old, new) }
        }
    }

    private fun updateValue(name: String, value: Any?) {
        updateValue(name, properties.put(name, value), value)
    }

    fun registerListener(name: String, biConsumer: BiConsumer<Any?>) {
        listeners.getOrPut(name) { mutableListOf() }.add(biConsumer)
        properties[name]?.let { biConsumer(it, it) }
    }

    fun removeListeners(name: String) {
        properties.remove(name)
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <V> registerListener(property: KProperty1<T, V>, crossinline consumer: BiConsumer<V>) =
        registerListener(property.name) { old, new -> consumer(old as V, new as V) }

    inline fun <reified V> removeListeners(property: KProperty1<T, V>) =
        removeListeners(property.name)

    inner class WrappingRWProperty<V>(
        prop: KProperty<*>,
        value: V
    ) : ReadWriteProperty<T, V> {

        init {
            properties[prop.name] = value
        }

        @Suppress("UNCHECKED_CAST")
        override operator fun getValue(thisRef: T, property: KProperty<*>) = properties[property.name] as V

        override operator fun setValue(thisRef: T, property: KProperty<*>, value: V) =
            updateValue(property.name, value)
    }

    inner class WrappingDelegateRWProperty<V>(
        private val delegate: KMutableProperty0<V>
    ) : ReadWriteProperty<T, V> {

        override operator fun getValue(thisRef: T, property: KProperty<*>) = delegate.get()

        override operator fun setValue(thisRef: T, property: KProperty<*>, value: V) {
            val old = delegate.get()
            delegate.set(value)
            updateValue(property.name, old, value)
        }
    }
}

interface DelegateProvider<T, V> {
    operator fun provideDelegate(
        thisRef: T,
        prop: KProperty<*>
    ): ReadWriteProperty<T, V>
}

class ObservableValue<T, V>(
    private val delegate: ObservableManager<T>,
    private val value: V
) : DelegateProvider<T, V> {
    override operator fun provideDelegate(
        thisRef: T,
        prop: KProperty<*>
    ): ReadWriteProperty<T, V> = delegate.WrappingRWProperty(prop, value)
}

class ObservablePropertyValue<T, V>(
    private val delegate: ObservableManager<T>,
    private val property: KMutableProperty0<V>
) : DelegateProvider<T, V> {
    override operator fun provideDelegate(
        thisRef: T,
        prop: KProperty<*>
    ): ReadWriteProperty<T, V> = delegate.WrappingDelegateRWProperty(property)
}

/**
 * Classes which implement this interface can provide values that can be observed through listeners.
 */
interface Observable<T> {
    val manager: ObservableManager<T>
}

open class DefaultObservable<T> : Observable<T> {
    override val manager = ObservableManager<T>()
}

/**
 * Create an observable property with the given initial value.
 */
fun <T, V> Observable<T>.observable(value: V): DelegateProvider<T, V> =
    ObservableValue(manager, value)

/**
 * Create an observable property which is delegated to the given property.
 */
fun <T, V> Observable<T>.observable(prop: KMutableProperty0<V>): DelegateProvider<T, V> =
    ObservablePropertyValue(manager, prop)

/**
 * Add a listener to receive events when a property has changed.
 */
inline fun <T, V> Observable<T>.registerListener(
    property: KProperty1<T, V>,
    crossinline consumer: BiConsumer<V>
) = manager.registerListener(property, consumer)

/**
 * Remove all listeners registered to a given property.
 */
inline fun <T, reified V> Observable<T>.removeListeners(
    property: KProperty1<T, V>
) = manager.removeListeners(property)

data class ObservableInstanceProperty<T, R : Observable<R>>(val receiver: R, val property: KProperty1<R, T>)

infix fun <T, R : Observable<R>> KProperty1<R, T>.on(receiver: R) = ObservableInstanceProperty(receiver, this)
