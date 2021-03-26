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
