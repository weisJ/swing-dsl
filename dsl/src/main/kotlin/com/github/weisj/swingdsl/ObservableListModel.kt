package com.github.weisj.swingdsl

import com.github.weisj.swingdsl.binding.container.ObservableList
import javax.swing.ListModel
import javax.swing.event.ListDataEvent
import javax.swing.event.ListDataListener

class ObservableListModel<T>(private val list: ObservableList<T>) : ListModel<T> {
    private val listeners = mutableListOf<ListDataListener>()

    init {
        list.onAdd { index, _ -> onIntervalAdded(index, index) }
        list.onRemove { index, _ -> onIntervalRemoved(index, index) }
        list.onSet { index, _, _ -> onContentChanged(index, index) }
    }

    override fun getSize(): Int = list.size

    override fun getElementAt(index: Int): T = list[index]

    override fun addListDataListener(l: ListDataListener?) {
        l ?: return
        listeners.add(l)
    }

    override fun removeListDataListener(l: ListDataListener?) {
        l ?: return
        listeners.remove(l)
    }

    private fun onIntervalAdded(start: Int, end: Int) {
        val event = ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, start, end)
        listeners.forEach { it.intervalAdded(event) }
    }

    private fun onIntervalRemoved(start: Int, end: Int) {
        val event = ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, start, end)
        listeners.forEach { it.intervalRemoved(event) }
    }

    private fun onContentChanged(start: Int, end: Int) {
        val event = ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, start, end)
        listeners.forEach { it.contentsChanged(event) }
    }
}
