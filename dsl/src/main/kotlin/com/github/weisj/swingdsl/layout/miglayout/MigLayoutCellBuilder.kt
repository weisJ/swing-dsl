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

// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.github.weisj.swingdsl.layout.miglayout

import com.github.weisj.swingdsl.bindEnabled
import com.github.weisj.swingdsl.bindVisible
import com.github.weisj.swingdsl.core.condition.ObservableCondition
import com.github.weisj.swingdsl.core.text.Text
import com.github.weisj.swingdsl.layout.CellBuilder
import com.github.weisj.swingdsl.layout.CheckboxCellBuilder
import com.github.weisj.swingdsl.layout.Constraint
import com.github.weisj.swingdsl.layout.GrowPolicy
import com.github.weisj.swingdsl.layout.ScrollPaneCellBuilder
import javax.swing.JComponent

internal class MigLayoutCellBuilder<T : JComponent>(
    private val builder: MigLayoutBuilder,
    private val row: MigLayoutRow,
    override val component: T
) : CellBuilder<T>, CheckboxCellBuilder, ScrollPaneCellBuilder {
    private var applyIfEnabled = false
    private val bindingUpdaters = mutableListOf<() -> Unit>()
    private var commitImmediately = row.commitImmediately

    init {
        if (commitImmediately) commitImmediately()
    }

    override fun comment(text: Text, maxLineLength: Int, forComponent: Boolean): CellBuilder<T> {
        row.addCommentRow(
            comment = text,
            maxLineLength = maxLineLength,
            forComponent = forComponent
        )
        return this
    }

    override fun commentComponent(component: JComponent, forComponent: Boolean): CellBuilder<T> {
        row.addCommentRow(component, forComponent, withLeftGap = true)
        return this
    }

    override fun onApply(callback: () -> Unit): CellBuilder<T> {
        builder.applyCallbacks.getOrPut(component) { mutableListOf() }.add(callback)
        return this
    }

    override fun onReset(callback: () -> Unit): CellBuilder<T> {
        builder.resetCallbacks.getOrPut(component) { mutableListOf() }.add(callback)
        return this
    }

    override fun onIsModified(callback: () -> Boolean): CellBuilder<T> {
        builder.isModifiedCallbacks.getOrPut(component) { mutableListOf() }.add(callback)
        return this
    }

    override fun enabled(isEnabled: Boolean) {
        component.isEnabled = isEnabled
    }

    override fun visible(isVisible: Boolean) {
        component.isVisible = isVisible
    }

    override fun enableIf(predicate: ObservableCondition): CellBuilder<T> {
        component.bindEnabled(predicate)
        return this
    }

    override fun visibleIf(predicate: ObservableCondition): CellBuilder<T> {
        component.bindVisible(predicate)
        return this
    }

    override fun shouldSaveOnApply(): Boolean {
        return !(applyIfEnabled && !component.isEnabled)
    }

    override fun actsAsLabel() {
        builder.updateComponentConstraints(component) { spanX = 1 }
    }

    override fun noGrowY() {
        builder.updateComponentConstraints(component) {
            growY(0.0f)
            pushY(0.0f)
        }
    }

    override fun sizeGroup(name: String): MigLayoutCellBuilder<T> {
        builder.updateComponentConstraints(component) {
            sizeGroup(name)
        }
        return this
    }

    override fun growPolicy(growPolicy: GrowPolicy): CellBuilder<T> {
        builder.updateComponentConstraints(component) {
            builder.defaultComponentConstraintCreator.applyGrowPolicy(this, growPolicy)
        }
        return this
    }

    override fun constraints(vararg constraints: Constraint): CellBuilder<T> {
        builder.updateComponentConstraints(component) {
            overrideFlags(this, constraints)
        }
        return this
    }

    override fun withLargeLeftGap(): CellBuilder<T> {
        builder.updateComponentConstraints(component) {
            horizontal.gapBefore = gapToBoundSize(builder.spacing.largeHorizontalGap, true)
        }
        return this
    }

    override fun withLeftGap(): CellBuilder<T> {
        builder.updateComponentConstraints(component) {
            horizontal.gapBefore = gapToBoundSize(builder.spacing.horizontalGap, true)
        }
        return this
    }

    override fun onImmedeateModeActivated(immediateModeUpdater: (() -> Unit)?) {
        bindingUpdaters.add(
            immediateModeUpdater ?: {
                error("Warning!: Immediate mode committing is not supported for $component")
            }
        )
        immediateModeUpdater?.let { updater ->
            if (commitImmediately) {
                updater()
            } else {
                bindingUpdaters.add(updater)
            }
        }
    }

    override fun commitImmediately() {
        if (commitImmediately) return
        commitImmediately = true
        bindingUpdaters.forEach { it() }
        bindingUpdaters.clear()
    }
}
