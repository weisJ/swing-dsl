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
package com.github.weisj.swingdsl.core.binding.container

import com.github.weisj.swingdsl.core.binding.ChangeTracker
import com.github.weisj.swingdsl.core.binding.ObservableProperty
import net.pearx.okservable.collection.ObservableListHandler
import net.pearx.okservable.collection.observableList

interface CollectionObservables<T> {
    val size: ObservableProperty<Int>
}

interface ListObservables<T> : CollectionObservables<T> {
    operator fun get(index: Int): ObservableProperty<T?>
}

interface ObservableList<T> : MutableList<T> {

    val observables: ListObservables<T>

    fun onClear(observeKey: Any? = null, block: (Collection<T>) -> Unit)
    fun onAdd(observeKey: Any? = null, block: (Int, T) -> Unit)
    fun onRemove(observeKey: Any? = null, block: (Int, T) -> Unit)
    fun onSet(observeKey: Any? = null, block: (Int, T, T) -> Unit)

    fun removeAllAt(indices: IntRange) {
        for (i in indices.last downTo indices.first) {
            removeAt(i)
        }
    }

    fun removeAllAt(indices: IntArray) {
        indices.asSequence().sortedDescending().forEach { removeAt(it) }
    }
}

fun <T> ObservableProperty<List<T>>.toObservableList(): ObservableList<T> = observableListOf<T>().apply {
    addAll(this@toObservableList.get())
    this@toObservableList.onChange {
        clear()
        addAll(it)
    }
}

fun <T> observableListOf(list: MutableList<T>): ObservableList<T> = ObservableListImpl(list)
fun <T> observableListOf(vararg elements: T): ObservableList<T> = ObservableListImpl(mutableListOf(*elements))
fun <T> observableListOf(): ObservableList<T> = ObservableListImpl(mutableListOf())

internal class ObservableListHandlerImpl<T> : ObservableListHandler<T> {
    internal val onClearCallbacks by lazy { mutableMapOf<Any, (Collection<T>) -> Unit>() }
    internal val onAddCallbacks by lazy { mutableMapOf<Any, (Int, T) -> Unit>() }
    internal val onRemoveCallbacks by lazy { mutableMapOf<Any, (Int, T) -> Unit>() }
    internal val onSetCallbacks by lazy { mutableMapOf<Any, (Int, T, T) -> Unit>() }

    override fun onClear(elements: Collection<T>) {
        onClearCallbacks.forEach { (_, it) -> it(elements) }
    }

    override fun onAdd(index: Int, element: T) {
        onAddCallbacks.forEach { (_, it) -> it(index, element) }
    }

    override fun onRemove(index: Int, element: T) {
        onRemoveCallbacks.forEach { (_, it) -> it(index, element) }
    }

    override fun onSet(index: Int, prevValue: T, newValue: T) {
        onSetCallbacks.forEach { (_, it) -> it(index, prevValue, newValue) }
    }
}

internal class ObservableListImpl<T> internal constructor(
    private val list: MutableList<T>,
    private val handler: ObservableListHandlerImpl<T> = ObservableListHandlerImpl()
) : ObservableList<T>, MutableList<T> by list.observableList(handler) {

    override val observables by lazy {
        object : ListObservables<T> {
            override val size: ObservableProperty<Int> =
                object : ObservableListProperty<Int>(this@ObservableListImpl) {
                    override fun getImpl(): Int = this@ObservableListImpl.size
                }

            override fun get(index: Int): ObservableProperty<T?> {
                check(index >= 0)
                return object : ObservableListProperty<T?>(this@ObservableListImpl) {
                    override fun getImpl(): T? =
                        if (index < this@ObservableListImpl.size) this@ObservableListImpl[index] else null
                }
            }
        }
    }

    override fun onClear(observeKey: Any?, block: (Collection<T>) -> Unit) {
        handler.onClearCallbacks[observeKey ?: block] = block
    }

    override fun onAdd(observeKey: Any?, block: (Int, T) -> Unit) {
        handler.onAddCallbacks[observeKey ?: block] = block
    }

    override fun onRemove(observeKey: Any?, block: (Int, T) -> Unit) {
        handler.onRemoveCallbacks[observeKey ?: block] = block
    }

    override fun onSet(observeKey: Any?, block: (Int, T, T) -> Unit) {
        handler.onSetCallbacks[observeKey ?: block] = block
    }
}

private abstract class ObservableListProperty<T>(private val observableList: ObservableList<*>) :
    ObservableProperty<T> {
    @Suppress("LeakingThis")
    private val changeTracker = ChangeTracker(this::getImpl)
    protected abstract fun getImpl(): T
    override fun get(): T = changeTracker.value

    override fun onChange(observeKey: Any?, callback: (T) -> Unit) {
        changeTracker.register(
            observeKey, callback,
            { key, call -> observableList.onClear(key) { call() } },
            { key, call -> observableList.onAdd(key) { _, _ -> call() } },
            { key, call -> observableList.onRemove(key) { _, _ -> call() } },
        )
    }

    override fun removeCallback(observeKey: Any?) {
        throw UnsupportedOperationException()
    }
}
