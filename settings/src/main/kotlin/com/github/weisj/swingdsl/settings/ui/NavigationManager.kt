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
package com.github.weisj.swingdsl.settings.ui

import com.github.weisj.swingdsl.core.binding.ObservableProperty
import com.github.weisj.swingdsl.core.binding.observableProperty
import com.github.weisj.swingdsl.core.collection.UndoRedoList
import com.github.weisj.swingdsl.highlight.LayoutTag
import com.github.weisj.swingdsl.settings.Category
import com.github.weisj.swingdsl.util.ModificationLock

data class NavigationPosition(
    val category: Category,
    val tag: LayoutTag?
)

class NavigationManager(
    private val defaultCategory: Category,
    private val createTagForCurrentPosition: () -> LayoutTag
) {
    private var navigationLock = ModificationLock()

    private var _currentPosition = observableProperty(NavigationPosition(defaultCategory, null))
    val currentPosition: ObservableProperty<NavigationPosition> = _currentPosition

    private var unsavedNavigationMode = false
    private var savedPosition: NavigationPosition = currentPosition.get()

    private val defaultHistory = UndoRedoList()
    private val searchModeHistory = UndoRedoList()
    private var context = defaultHistory

    var searchMode: Boolean = false
        set(value) {
            if (field == value) return
            field = value
            context = if (value) searchModeHistory else defaultHistory
            if (!value) searchModeHistory.clear()
            if (!value && currentPosition.get().category == defaultCategory) {
                defaultHistory.currentOrNull()?.doAction?.invoke()
            }
        }

    private fun createExactCurrentLocation(): NavigationPosition =
        NavigationPosition(currentPosition.get().category, createTagForCurrentPosition())

    private fun doNavigation(position: NavigationPosition) {
        _currentPosition.set(position)
    }

    fun navigateTo(
        category: Category?,
        tag: LayoutTag? = null,
        reversible: Boolean = true,
        includeInHistory: Boolean = true
    ) {
        navigationLock.withLock {
            val target = category ?: defaultCategory
            if (currentPosition.get().category == category && tag == currentPosition.get().tag) return
            val targetPosition = NavigationPosition(target, tag)
            val exactCurrentPosition = createExactCurrentLocation()
            if (includeInHistory) {
                navigateWithHistory(targetPosition, exactCurrentPosition, reversible)
            } else {
                navigateWithoutHistory(targetPosition, exactCurrentPosition)
            }
            unsavedNavigationMode = !includeInHistory
        }
    }

    private fun navigateWithoutHistory(
        targetPosition: NavigationPosition,
        exactCurrentPosition: NavigationPosition
    ) {
        if (!unsavedNavigationMode && targetPosition.category != defaultCategory) {
            savedPosition = exactCurrentPosition
        }
        doNavigation(targetPosition)
    }

    private fun navigateWithHistory(
        targetPosition: NavigationPosition,
        exactCurrentPosition: NavigationPosition,
        reversible: Boolean
    ) {
        // If we have a saved position we use it instead of the current location.
        // This is relevant if there have been navigation actions not included in the history.
        if (targetPosition.category != defaultCategory) {
            savedPosition = targetPosition
        }
        context.add(
            doAction = { doNavigation(targetPosition) },
            undoAction = if (reversible) {
                { doNavigation(exactCurrentPosition) }
            } else null
        )
    }

    fun goBack() {
        navigationLock.withLock {
            if (unsavedNavigationMode || currentPosition.get() != savedPosition) {
                // Undoing unsaved navigation should create a saved position when undone.
                // Only do this if there are no re-doable actions.
                unsavedNavigationMode = false
                if (context.canRedo()) {
                    doNavigation(savedPosition)
                } else {
                    val exactLocation = createExactCurrentLocation()
                    context.add(
                        doAction = { doNavigation(exactLocation) },
                        undoAction = { doNavigation(savedPosition) },
                        executeAction = false
                    )
                    context.undoIfPossible()
                }
            } else {
                context.undoIfPossible()
            }
        }
    }

    fun goForward() {
        navigationLock.withLock { context.redoIfPossible() }
    }
}
