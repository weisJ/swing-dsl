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
package com.github.weisj.swingdsl.collection

import com.github.weisj.swingdsl.binding.MutableProperty
import com.github.weisj.swingdsl.binding.observableProperty
import com.github.weisj.swingdsl.condition.ObservableCondition

class Action(val doAction: () -> Unit, val undoAction: (() -> Unit)?)

class UndoRedoListObservable internal constructor() {
    val canUndo: ObservableCondition = observableProperty(false)
    val canRedo: ObservableCondition = observableProperty(false)
}

class UndoRedoList {
    private val history = HistoryList<Action>()
    val observable: UndoRedoListObservable = UndoRedoListObservable()

    @Suppress("UNCHECKED_CAST")
    private fun updateState() {
        (observable.canUndo as MutableProperty<Boolean>).set(canUndo())
        (observable.canRedo as MutableProperty<Boolean>).set(canRedo())
    }

    fun add(doAction: () -> Unit, undoAction: (() -> Unit)?, executeAction: Boolean = true) {
        val action = Action(doAction, undoAction)
        history.add(action)
        if (executeAction) {
            action.doAction()
        }
        updateState()
    }

    fun undo() {
        history.current().undoAction!!()
        history.back()
        updateState()
    }

    fun redo() {
        history.forward().doAction()
        updateState()
    }

    fun undoIfPossible() {
        if (canUndo()) undo()
    }

    fun redoIfPossible() {
        if (canRedo()) redo()
    }

    fun canUndo(): Boolean = history.canGoBack() && history.currentOrNull()?.undoAction != null

    fun canRedo(): Boolean = history.canGoForward()
}
