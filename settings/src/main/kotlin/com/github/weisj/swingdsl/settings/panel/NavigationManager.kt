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
package com.github.weisj.swingdsl.settings.panel

import com.github.weisj.swingdsl.collection.UndoRedoList
import com.github.weisj.swingdsl.highlight.LayoutTag
import com.github.weisj.swingdsl.settings.Category
import com.github.weisj.swingdsl.util.ModificationLock

internal data class NavigationPosition(
    val category: Category,
    val tag: LayoutTag?
)

class NavigationManager(
    private val defaultCategory: Category,
    private val navigationImplementation: (Category, LayoutTag?) -> Unit,
    private val createTagForCurrentPosition: () -> LayoutTag
) {
    private val defaultHistory = UndoRedoList()
    private val searchModeHistory = UndoRedoList()
    private var context = defaultHistory

    private var navigationLock = ModificationLock()

    private var currentPosition: NavigationPosition = NavigationPosition(defaultCategory, null)
    val currentCategory: Category
        get() = currentPosition.category

    private var unsavedNavigationMode = false
    private var savedPosition: NavigationPosition = currentPosition

    var searchMode: Boolean = false
        set(value) {
            if (field == value) return
            field = value
            context = if (value) {
                searchModeHistory
            } else {
                defaultHistory
            }
        }

    private fun createExactCurrentLocation(): NavigationPosition =
        NavigationPosition(currentCategory, createTagForCurrentPosition())

    private fun doNavigation(position: NavigationPosition) {
        currentPosition = position
        navigationImplementation(position.category, position.tag)
    }

    fun navigateTo(
        category: Category?,
        tag: LayoutTag? = null,
        reversible: Boolean = true,
        includeInHistory: Boolean = true
    ) {
        navigationLock.withLock {
            val target = category ?: defaultCategory
            if (currentCategory == category && tag == currentPosition.tag) return
            val targetPosition = NavigationPosition(target, tag)
            val exactCurrentPosition = createExactCurrentLocation()
            if (includeInHistory) {
                // If we have a saved position we use it instead of the current location.
                // This is relevant if there have been navigation actions not included in the history.
                savedPosition = targetPosition
                context.add(
                    doAction = { doNavigation(targetPosition) },
                    undoAction = if (reversible) {
                        { doNavigation(exactCurrentPosition) }
                    } else null
                )
            } else {
                if (!unsavedNavigationMode) {
                    savedPosition = exactCurrentPosition
                }
                doNavigation(targetPosition)
            }
            unsavedNavigationMode = !includeInHistory
        }
    }

    fun goBack() {
        navigationLock.withLock {
            if (unsavedNavigationMode) {
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
