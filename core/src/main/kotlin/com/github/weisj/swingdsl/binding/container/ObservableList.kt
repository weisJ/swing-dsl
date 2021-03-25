package com.github.weisj.swingdsl.binding.container

import com.github.weisj.swingdsl.binding.BoundProperty
import net.pearx.okservable.collection.ObservableListHandler
import net.pearx.okservable.collection.observableList

interface ObservableList<T> : MutableList<T> {

    val boundSize: BoundProperty<Int>

    fun onClear(block: (Collection<T>) -> Unit)
    fun onAdd(block: (Int, T) -> Unit)
    fun onRemove(block: (Int, T) -> Unit)
    fun onSet(block: (Int, T, T) -> Unit)

    fun removeAllAt(indices: IntRange) {
        for (i in indices.last downTo indices.first) {
            removeAt(i)
        }
    }

    fun removeAllAt(indices: IntArray) {
        indices.asSequence().sortedDescending().forEach { removeAt(it) }
    }
}

fun <T> observableListOf(list: MutableList<T>): ObservableList<T> = ObservableListImpl(list)
fun <T> observableListOf(vararg elements: T): ObservableList<T> = ObservableListImpl(mutableListOf(*elements))
fun <T> observableListOf(): ObservableList<T> = ObservableListImpl(mutableListOf())

internal class ObservableListHandlerImpl<T> : ObservableListHandler<T> {
    internal val onClearCallbacks by lazy { mutableListOf<(Collection<T>) -> Unit>() }
    internal val onAddCallbacks by lazy { mutableListOf<(Int, T) -> Unit>() }
    internal val onRemoveCallbacks by lazy { mutableListOf<(Int, T) -> Unit>() }
    internal val onSetCallbacks by lazy { mutableListOf<(Int, T, T) -> Unit>() }

    override fun onClear(elements: Collection<T>) {
        onClearCallbacks.forEach { it(elements) }
    }

    override fun onAdd(index: Int, element: T) {
        onAddCallbacks.forEach { it(index, element) }
    }

    override fun onRemove(index: Int, element: T) {
        onRemoveCallbacks.forEach { it(index, element) }
    }

    override fun onSet(index: Int, prevValue: T, newValue: T) {
        onSetCallbacks.forEach { it(index, prevValue, newValue) }
    }
}

internal class ObservableListImpl<T> internal constructor(
    private val list: MutableList<T>,
    private val handler: ObservableListHandlerImpl<T> = ObservableListHandlerImpl()
) : ObservableList<T>, MutableList<T> by list.observableList(handler) {

    override val boundSize: BoundProperty<Int> = object : BoundProperty<Int> {
        override fun get(): Int = size

        override fun onPropertyChange(callback: (Int) -> Unit) {
            onClear { callback(get()) }
            onAdd { _, _ -> callback(get()) }
            onSet { _, _, _ -> callback(get()) }
        }
    }

    override fun onClear(block: (Collection<T>) -> Unit) {
        handler.onClearCallbacks.add(block)
    }

    override fun onAdd(block: (Int, T) -> Unit) {
        handler.onAddCallbacks.add(block)
    }

    override fun onRemove(block: (Int, T) -> Unit) {
        handler.onRemoveCallbacks.add(block)
    }

    override fun onSet(block: (Int, T, T) -> Unit) {
        handler.onSetCallbacks.add(block)
    }
}
