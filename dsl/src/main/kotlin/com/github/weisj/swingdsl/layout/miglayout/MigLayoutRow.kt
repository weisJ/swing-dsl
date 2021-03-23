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

import com.github.weisj.swingdsl.component.CollapsibleTitledSeparator
import com.github.weisj.swingdsl.component.TitledSeparator
import com.github.weisj.swingdsl.condition.BoundCondition
import com.github.weisj.swingdsl.laf.WrappedComponent
import com.github.weisj.swingdsl.layout.CellBuilder
import com.github.weisj.swingdsl.layout.Row
import com.github.weisj.swingdsl.layout.SpacingConfiguration
import com.github.weisj.swingdsl.style.DynamicUI
import com.github.weisj.swingdsl.style.UIFactory
import com.github.weisj.swingdsl.text.Text
import com.github.weisj.swingdsl.text.TextLabel
import javax.swing.*
import javax.swing.border.LineBorder
import javax.swing.text.JTextComponent
import kotlin.math.max
import kotlin.reflect.KMutableProperty0
import net.miginfocom.layout.BoundSize
import net.miginfocom.layout.CC
import net.miginfocom.layout.LayoutUtil

internal class MigLayoutRow(
    private val parent: MigLayoutRow?,
    override val builder: MigLayoutBuilder,
    val labeled: Boolean = false,
    val noGrid: Boolean = false,
    private val indentationLevel: Int,
    private val incrementsIndentationLevel: Boolean = parent != null
) : Row() {
    companion object {
        private const val COMPONENT_ENABLED_STATE_KEY = "MigLayoutRow.enabled"

        // as static method to ensure that members of current row are not used
        private fun createCommentRow(
            parent: MigLayoutRow,
            component: JComponent,
            indent: Int,
            isParentRowLabeled: Boolean,
            forComponent: Boolean,
            columnIndex: Int
        ) {
            val cc = CC()
            val commentRow = parent.createChildRow()
            commentRow.isComment = true
            commentRow.addComponent(component, cc)
            when {
                forComponent -> {
                    cc.horizontal.gapBefore = BoundSize.NULL_SIZE
                    cc.skip = columnIndex
                }
                isParentRowLabeled -> {
                    cc.horizontal.gapBefore = BoundSize.NULL_SIZE
                    cc.skip()
                }
                else -> {
                    cc.horizontal.gapBefore = gapToBoundSize(indent + parent.spacing.indentLevel, true)
                }
            }
        }

        // as static method to ensure that members of current row are not used
        private fun configureSeparatorRow(row: MigLayoutRow, title: Text?) {
            val separatorSpec = UIFactory.createSeparatorComponent(title)
            val comp = if (separatorSpec.providesCustomComponent()) {
                separatorSpec.provided!!
            } else {
                val spec = separatorSpec.defaultImplSpec!!
                DynamicUI.withDynamic(TitledSeparator(title)) {
                    it.color = spec.color
                    it.disabledColor = spec.disabledColor
                }
            }
            row.addTitleComponent(comp, isEmpty = title == null)
        }
    }

    internal val components: MutableList<JComponent> = mutableListOf()
    private val childCells: MutableList<MigLayoutCellBuilder<*>> = mutableListOf()
    internal var rightIndex = Int.MAX_VALUE

    private var columnIndex = -1
    internal var subRows: MutableList<MigLayoutRow>? = null
        private set

    private fun getOrCreateSubRowsList(): MutableList<MigLayoutRow> {
        var subRows = subRows
        if (subRows == null) {
            // subRows in most cases > 1
            subRows = ArrayList()
            this.subRows = subRows
        }
        return subRows
    }

    internal var gapAfter: String? = null

    private val spacing: SpacingConfiguration
        get() = builder.spacing

    private var isTrailingSeparator = false
    private var isComment = false

    override fun withButtonGroup(title: Text?, buttonGroup: ButtonGroup, body: () -> Unit) {
        if (title != null) {
            label(title)
            gapAfter = "${spacing.radioGroupTitleVerticalGap}px!"
        }
        builder.withButtonGroup(buttonGroup, body)
    }

    override var enabled = true
        set(value) {
            if (field == value) return

            field = value
            for (c in components) {
                if (!value) {
                    if (!c.isEnabled) {
                        // current state of component differs from current row state
                        // - preserve current state to apply it when row state will be changed
                        c.putClientProperty(COMPONENT_ENABLED_STATE_KEY, false)
                    }
                } else {
                    if (c.getClientProperty(COMPONENT_ENABLED_STATE_KEY) == false) {
                        // remove because for active row component state can be changed and we don't want to
                        // add listener to update value accordingly
                        c.putClientProperty(COMPONENT_ENABLED_STATE_KEY, null)
                        // do not set to true, preserve old component state
                        continue
                    }
                }
                c.isEnabled = value
            }
        }

    override var visible = true
        set(value) {
            if (field == value) return

            field = value
            for (c in components) {
                c.isVisible = value
            }
        }

    override var subRowsEnabled = true
        set(value) {
            if (field == value) return

            field = value
            subRows?.forEach {
                it.enabled = value
                it.subRowsEnabled = value
            }
        }

    override var subRowsVisible = true
        set(value) {
            if (field == value) return

            field = value
            subRows?.forEach {
                it.visible = value
                it.subRowsVisible = value
            }
        }

    override var subRowIndentationLevel: Int = -1

    internal val isLabeledIncludingSubRows: Boolean
        get() = labeled || (subRows?.any { it.isLabeledIncludingSubRows } ?: false)

    internal val columnIndexIncludingSubRows: Int
        get() = max(columnIndex, subRows?.asSequence()?.map { it.columnIndexIncludingSubRows }?.max() ?: -1)

    internal var commitImmediately: Boolean = parent?.commitImmediately ?: false

    internal data class CellModeSpan(var start: Int, var end: Int = -1, var isVerticalFlow: Boolean = false) {
        val length: Int
            get() = end - start

        operator fun contains(index: Int): Boolean {
            if (start == -1) return false
            if (end == -1) return index >= start
            return index in start..end
        }
    }

    internal var cellModeSpans = mutableListOf(CellModeSpan(start = -1, end = -1))

    override fun createChildRow(label: JLabel?, isSeparated: Boolean, noGrid: Boolean, title: Text?): MigLayoutRow {
        return createChildRow(indentationLevel, label, isSeparated, noGrid, title)
    }

    private fun createChildRow(
        indent: Int,
        label: JLabel? = null,
        isSeparated: Boolean = false,
        noGrid: Boolean = false,
        title: Text? = null,
        incrementsIndent: Boolean = true
    ): MigLayoutRow {
        val newIndent = if (!this.incrementsIndentationLevel) indent else indent + spacing.indentLevel
        val subRows = getOrCreateSubRowsList()
        val row = MigLayoutRow(
            this, builder,
            labeled = label != null,
            noGrid = noGrid,
            indentationLevel = if (subRowIndentationLevel >= 0) subRowIndentationLevel * spacing.indentLevel else newIndent,
            incrementsIndentationLevel = incrementsIndent
        )

        if (isSeparated) {
            val separatorRow = MigLayoutRow(this, builder, indentationLevel = newIndent, noGrid = true)
            configureSeparatorRow(separatorRow, title)
            separatorRow.enabled = subRowsEnabled
            separatorRow.subRowsEnabled = subRowsEnabled
            separatorRow.visible = subRowsVisible
            separatorRow.subRowsVisible = subRowsVisible
            row.getOrCreateSubRowsList().add(separatorRow)
        }

        var insertIndex = subRows.size
        if (insertIndex > 0 && subRows[insertIndex - 1].isTrailingSeparator) {
            insertIndex--
        }
        if (insertIndex > 0 && subRows[insertIndex - 1].isComment) {
            insertIndex--
        }
        subRows.add(insertIndex, row)

        row.enabled = subRowsEnabled
        row.subRowsEnabled = subRowsEnabled
        row.visible = subRowsVisible
        row.subRowsVisible = subRowsVisible

        if (label != null) {
            row.addComponent(label)
        }

        return row
    }

    private fun <T : JComponent> addTitleComponent(titleComponent: T, isEmpty: Boolean) {
        val cc = CC()
        if (isEmpty) {
            cc.vertical.gapAfter = gapToBoundSize(spacing.verticalGap * 2, false)
            isTrailingSeparator = true
        } else {
            cc.growX()
        }
        addComponent(titleComponent, cc)
    }

    override fun titledRow(title: Text, init: Row.() -> Unit): Row {
        return createBlockRow(title, true, init)
    }

    override fun blockRow(init: Row.() -> Unit): Row {
        return createBlockRow(null, false, init)
    }

    private fun createBlockRow(title: Text?, isSeparated: Boolean, init: Row.() -> Unit): Row {
        val parentRow = createChildRow(
            indent = indentationLevel,
            title = title,
            isSeparated = isSeparated,
            incrementsIndent = isSeparated
        )
        parentRow.init()
        val result = parentRow.createChildRow()
        result.placeholder()
        result.largeGapAfter()
        return result
    }

    override fun hideableRow(title: Text, startHidden: Boolean, init: Row.() -> Unit): Row {
        val separatorSpec = UIFactory.createCollapsibleSeparatorComponent(title)

        val separator = if (separatorSpec.providesCustomComponent()) {
            separatorSpec.provided!!
        } else {
            val spec = separatorSpec.defaultImplSpec!!
            DynamicUI.withDynamic(CollapsibleTitledSeparator(title)) {
                it.color = spec.color
                it.disabledColor = spec.disabledColor
                it.expandedIcon = spec.expandedIcon
                it.collapsedIcon = spec.collapsedIcon
                it.disabledExpandedIcon = spec.disabledExpandedIcon
                it.disabledCollapsedIcon = spec.disabledCollapsedIcon
            }
        }

        val separatorRow = createChildRow()
        separatorRow.addTitleComponent(separator.component, isEmpty = false)
        builder.hideableRowNestingLevel++
        try {
            val panelRow = createChildRow(indentationLevel + spacing.indentLevel)
            panelRow.init()
            separator.setCollapseCallback {
                panelRow.visible = false
                panelRow.subRowsVisible = false
            }
            separator.setExpandCallback {
                panelRow.visible = true
                panelRow.subRowsVisible = true
            }
            if (startHidden) {
                separator.collapse()
            } else {
                separator.expand()
            }
            return panelRow
        } finally {
            builder.hideableRowNestingLevel--
        }
    }

    override fun <T : JComponent> component(component: T): CellBuilder<T> {
        addComponent(component)
        return addCell(component)
    }

    override fun <T : JComponent> component(wrappedComponent: WrappedComponent<T>): CellBuilder<T> {
        addComponent(wrappedComponent.container)
        return addCell(wrappedComponent.component)
    }

    private fun <T : JComponent> addCell(comp: T): CellBuilder<T> {
        return MigLayoutCellBuilder(builder, this, comp).also { childCells.add(it) }
    }

    override fun setCellMode(value: Boolean, isVerticalFlow: Boolean, fullWidth: Boolean) {
        if (value) {
            assert(cellModeSpans.isNotEmpty() && cellModeSpans.last().start == -1)
            cellModeSpans.last().also {
                it.start = components.size
                it.isVerticalFlow = isVerticalFlow
            }
        } else {
            val span = cellModeSpans.last()
            span.end = components.size

            cellModeSpans.add(CellModeSpan(start = -1, end = -1))

            val componentCount = span.length
            if (componentCount == 0) return
            val component = components[span.start]
            val cc = component.constraints

            // do not add split if cell empty or contains the only component
            if (componentCount > 1) {
                cc.split(componentCount)
            }
            if (fullWidth) {
                cc.spanX(LayoutUtil.INF)
            }
            if (isVerticalFlow) {
                cc.flowY()
                // because when vertical buttons placed near scroll pane, it wil be centered by baseline (and baseline not applicable for grow elements, so, will be centered)
                cc.alignY("top")
            }
        }
    }

    internal fun addComponent(component: JComponent, cc: CC = CC()) {
        components.add(component)
        builder.componentConstraints[component] = cc

        configureComponentState(component)
        val currentCellSpan = cellModeSpans.last()
        if (currentCellSpan.start == -1 || currentCellSpan.start == components.size - 1) {
            columnIndex++
        }

        if (labeled && components.size == 2 && component.border is LineBorder) {
            builder.componentConstraints[components.first()]?.vertical?.gapBefore =
                builder.defaultComponentConstraintCreator.vertical1pxGap
        }

        if (component is JRadioButton) {
            builder.topButtonGroup?.add(component)
        }

        builder.defaultComponentConstraintCreator.addGrowIfNeeded(cc, component)

        if (!noGrid && indentationLevel > 0 && components.size == 1) {
            cc.horizontal.gapBefore = gapToBoundSize(indentationLevel, true)
        }

        // if this row is not labeled and:
        // a. some previous row is labeled and first component is a checkbox, span
        //    (since this checkbox should span across label and content cells)
        if (!labeled && components.size == 1 && component is JCheckBox) {
            configureCheckBoxLabel(cc)
        }

        // MigLayout doesn't check baseline if component has grow
        if (labeled && component is JScrollPane && component.viewport.view is JTextComponent) {
            configureScrollableTextComponent(component)
        }
    }

    private fun configureComponentState(component: JComponent) {
        if (!visible) {
            component.isVisible = false
        }
        if (!enabled) {
            component.isEnabled = false
        }
    }

    private fun configureCheckBoxLabel(cc: CC) {
        val siblings = parent!!.subRows
        if (siblings != null && siblings.size > 1 && siblings.any { it.labeled }) {
            cc.spanX(2)
        }
    }

    private fun configureScrollableTextComponent(component: JScrollPane) {
        val labelCC = components[0].constraints
        labelCC.alignY("top")
        val labelTop = component.border?.getBorderInsets(component)?.top ?: 0
        if (labelTop != 0) {
            labelCC.vertical.gapBefore = gapToBoundSize(labelTop, false)
        }
    }

    private val JComponent.constraints: CC
        get() = builder.componentConstraints.getOrPut(this) { CC() }

    fun addCommentRow(comment: Text, maxLineLength: Int = 70, forComponent: Boolean) {
        addCommentRow(createCommentComponent(comment, maxLineLength), forComponent)
    }

    override fun commentRow(text: Text) {
        addCommentRow(text, forComponent = false)
    }

    override fun createCommentRow(component: JComponent): Row {
        val cc = CC()
        cc.vertical.gapBefore = gapToBoundSize(
            if (subRows == null) {
                spacing.verticalGap
            } else {
                spacing.largeVerticalGap
            },
            false
        )
        cc.vertical.gapAfter = gapToBoundSize(spacing.verticalGap, false)

        val row = createChildRow(label = null, noGrid = true)
        row.addComponent(component, cc)
        return row
    }

    private fun createCommentComponent(text: Text, maxLineLength: Int): JTextComponent {
        val textArea = ConstrainedTextArea(text, maxLineLength)
        textArea.isEditable = false
        return textArea
    }

    fun addCommentRow(component: JComponent, forComponent: Boolean) {
        gapAfter = "${spacing.commentVerticalTopGap}px!"

        val isParentRowLabeled = labeled
        createCommentRow(this, component, indentationLevel, isParentRowLabeled, forComponent, columnIndex)
    }

    override fun alignRight() {
        if (rightIndex != Int.MAX_VALUE) {
            throw IllegalStateException("right allowed only once")
        }
        rightIndex = components.size
    }

    override fun largeGapAfter() {
        gapAfter = "${spacing.largeVerticalGap}px!"
    }

    override fun createRow(label: Text?): Row {
        return createChildRow(label = label?.let { TextLabel(it) })
    }

    override fun radioButton(text: Text, comment: Text?): CellBuilder<JRadioButton> {
        val result = super.radioButton(text, comment)
        attachSubRowsEnabled(result.component)
        return result
    }

    override fun radioButton(
        text: Text,
        prop: KMutableProperty0<Boolean>,
        comment: Text?
    ): CellBuilder<JRadioButton> {
        return super.radioButton(text, prop, comment).also { attachSubRowsEnabled(it.component) }
    }

    override fun onGlobalApply(callback: () -> Unit): Row {
        builder.applyCallbacks.getOrPut(null, { mutableListOf() }).add(callback)
        return this
    }

    override fun onGlobalReset(callback: () -> Unit): Row {
        builder.resetCallbacks.getOrPut(null, { mutableListOf() }).add(callback)
        return this
    }

    override fun onGlobalIsModified(callback: () -> Boolean): Row {
        builder.isModifiedCallbacks.getOrPut(null, { mutableListOf() }).add(callback)
        return this
    }

    override fun row(label: Text?, separated: Boolean, init: Row.() -> Unit): Row {
        val newRow = super.row(label, separated, init)
        if (newRow is MigLayoutRow && newRow.labeled && (newRow.components.size == 2)) {
            var rowLabel = newRow.components[0]
            if (rowLabel is JLabel) {
                rowLabel.labelFor = newRow.components[1]
            } else {
                rowLabel = newRow.components[1]
                if (rowLabel is JLabel) {
                    rowLabel.labelFor = newRow.components[0]
                }
            }
        }
        return newRow
    }

    override fun enabled(isEnabled: Boolean) {
        this.enabled = isEnabled
    }

    override fun visible(isVisible: Boolean) {
        this.visible = isVisible
    }

    override fun enableIf(predicate: BoundCondition): MigLayoutRow {
        this.enabled = predicate()
        predicate.registerListener { this.enabled = it }
        return this
    }

    override fun visibleIf(predicate: BoundCondition): MigLayoutRow {
        this.visible = predicate()
        predicate.registerListener { this.visible = it }
        return this
    }

    override fun commitImmediately() {
        if (commitImmediately) return
        commitImmediately = true
        // Update child rows
        getOrCreateSubRowsList().forEach { it.commitImmediately() }
        childCells.forEach { it.commitImmediately() }
    }

    private class ConstrainedTextArea(val boundText: Text, val maxLineLength: Int) : JTextArea() {
        init {
            DynamicUI.withDynamic(this) {
                it.text = boundText.text
                it.columns = 0
                val prefWidthWithColumns = it.insets.run { left + right } + columnWidth * maxLineLength
                val preWidthWithText = it.preferredSize.width
                if (preWidthWithText > prefWidthWithColumns) {
                    it.columns = maxLineLength
                }
            }
        }
    }
}