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

import com.github.weisj.swingdsl.binding.bind
import com.github.weisj.swingdsl.binding.onChange
import com.github.weisj.swingdsl.component.CollapsibleTitledSeparator
import com.github.weisj.swingdsl.component.TitledSeparator
import com.github.weisj.swingdsl.condition.ObservableCondition
import com.github.weisj.swingdsl.highlight.LayoutTag
import com.github.weisj.swingdsl.highlight.createLayoutTag
import com.github.weisj.swingdsl.highlight.emptyLayoutTag
import com.github.weisj.swingdsl.invokeLater
import com.github.weisj.swingdsl.laf.WrappedComponent
import com.github.weisj.swingdsl.layout.CellBuilder
import com.github.weisj.swingdsl.layout.Row
import com.github.weisj.swingdsl.layout.SpacingConfiguration
import com.github.weisj.swingdsl.style.DynamicUI
import com.github.weisj.swingdsl.style.UIFactory
import com.github.weisj.swingdsl.style.asTextProperty
import com.github.weisj.swingdsl.style.asUIResource
import com.github.weisj.swingdsl.text.Text
import com.github.weisj.swingdsl.width
import net.miginfocom.layout.BoundSize
import net.miginfocom.layout.CC
import net.miginfocom.layout.LayoutUtil
import java.awt.Dimension
import java.lang.Integer.min
import javax.swing.*
import javax.swing.border.LineBorder
import javax.swing.text.JTextComponent
import kotlin.math.max
import kotlin.reflect.KMutableProperty0

internal class MigLayoutRow(
    private val parent: MigLayoutRow?,
    override val builder: MigLayoutBuilder,
    private val labeled: Boolean = false,
    private var layoutTag: LayoutTag,
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
            withLeftGap: Boolean,
            columnIndex: Int
        ): LayoutTag {
            val cc = CC()
            val commentRow = parent.createChildRow()
            parent.getOrCreateCommentRowsList().add(commentRow)
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
                    val extraSpace = if (withLeftGap) parent.spacing.indentLevel else 0
                    cc.horizontal.gapBefore = gapToBoundSize(indent + extraSpace, true)
                }
            }
            return component.createLayoutTag()
        }

        // as static method to ensure that members of current row are not used
        // Returns the layout tag corresponding to the title.
        private fun configureSeparatorRow(row: MigLayoutRow, title: Text?): LayoutTag {
            val separatorSpec = UIFactory.createSeparatorComponent(title?.asTextProperty())
            lateinit var titleLayoutTag: LayoutTag
            val comp = if (separatorSpec.providesCustomComponent()) {
                separatorSpec.provided!!.also {
                    titleLayoutTag = it.createLayoutTag()
                }
            } else {
                DynamicUI.withDynamic(TitledSeparator(title)) {
                    titleLayoutTag = it.label.createLayoutTag()
                    val dividerColor = UIFactory.dividerColor
                    it.color = dividerColor.enabled
                    it.disabledColor = dividerColor.disabled
                }
            }
            row.addTitleComponent(comp, isEmpty = title == null)
            return titleLayoutTag
        }
    }

    internal val components: MutableList<JComponent> = mutableListOf()
    private val childCells: MutableList<MigLayoutCellBuilder<*>> = mutableListOf()
    internal var rightIndex = Int.MAX_VALUE

    private var columnIndex = -1
    internal var subRows: MutableList<MigLayoutRow>? = null
        private set

    private var commentRows: MutableList<MigLayoutRow>? = null

    private fun getOrCreateCommentRowsList(): MutableList<MigLayoutRow> {
        var commentRows = commentRows
        if (commentRows == null) {
            // commentRows in most cases == 1
            commentRows = mutableListOf()
            this.commentRows = commentRows
        }
        return commentRows
    }

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
            commentRows?.let {
                for (row in it) {
                    row.enabled = value
                }
            }
        }

    override var visible = true
        set(value) {
            if (field == value) return

            field = value
            for (c in components) {
                c.isVisible = value
            }
            commentRows?.let {
                for (row in it) {
                    row.visible = value
                }
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
        get() = max(columnIndex, subRows?.asSequence()?.maxOfOrNull { it.columnIndexIncludingSubRows } ?: -1)

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

    override fun createLayoutTag(): LayoutTag = layoutTag

    override fun createChildRow(
        label: WrappedComponent<JLabel>?,
        isSeparated: Boolean,
        isIndented: Boolean,
        noGrid: Boolean,
        title: Text?
    ): MigLayoutRow {
        return createChildRow(indentationLevel, label, isSeparated, noGrid, title, isIndented)
    }

    private fun createChildRow(
        indent: Int,
        label: WrappedComponent<JLabel>? = null,
        isSeparated: Boolean = false,
        noGrid: Boolean = false,
        title: Text? = null,
        isIndented: Boolean = true,
        incrementsIndent: Boolean = true,
        childLayoutTag: LayoutTag = label?.createLayoutTag() ?: emptyLayoutTag()
    ): MigLayoutRow {
        val newIndent = if (!this.incrementsIndentationLevel || !isIndented) indent else indent + spacing.indentLevel
        val subRows = getOrCreateSubRowsList()
        val row = MigLayoutRow(
            this, builder,
            labeled = label != null,
            layoutTag = childLayoutTag,
            noGrid = noGrid,
            indentationLevel = if (subRowIndentationLevel >= 0) subRowIndentationLevel * spacing.indentLevel else newIndent,
            incrementsIndentationLevel = incrementsIndent
        )

        if (isSeparated) {
            val separatorRow = MigLayoutRow(
                parent = this,
                builder = builder,
                layoutTag = emptyLayoutTag(), // Is set further down.
                indentationLevel = newIndent,
                noGrid = true
            )
            val titleLayoutTag = configureSeparatorRow(separatorRow, title)
            separatorRow.layoutTag = titleLayoutTag
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
            row.addComponent(label.container)
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
        val separatorSpec = UIFactory.createCollapsibleSeparatorComponent(title.asTextProperty())

        lateinit var titleLayoutTag: LayoutTag
        val separator = if (separatorSpec.providesCustomComponent()) {
            separatorSpec.provided!!.also { titleLayoutTag = it.component.createLayoutTag() }
        } else {
            DynamicUI.withDynamic(CollapsibleTitledSeparator(title)) {
                titleLayoutTag = it.label.createLayoutTag()
                val dividerColor = UIFactory.dividerColor
                val expandedIcon = UIFactory.expandedIcon
                val collapsedIcon = UIFactory.collapsedIcon
                it.color = dividerColor.enabled
                it.disabledColor = dividerColor.disabled
                it.expandedIcon = expandedIcon.enabled
                it.collapsedIcon = collapsedIcon.enabled
                it.disabledExpandedIcon = expandedIcon.disabled
                it.disabledCollapsedIcon = collapsedIcon.disabled
            }
        }

        val separatorRow = createChildRow()
        separatorRow.addTitleComponent(separator.component, isEmpty = false)
        builder.hideableRowNestingLevel++
        try {
            val panelRow =
                createChildRow(indent = indentationLevel + spacing.indentLevel, childLayoutTag = titleLayoutTag)
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
        return createAndAddCell(component)
    }

    override fun <T : JComponent> component(wrappedComponent: WrappedComponent<T>): CellBuilder<T> {
        addComponent(wrappedComponent.container)
        return createAndAddCell(wrappedComponent.component)
    }

    private fun <T : JComponent> createAndAddCell(comp: T): CellBuilder<T> {
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
                // because when vertical buttons placed near scroll pane, it wil be centered by baseline
                // (and baseline not applicable for grow elements, so, will be centered)
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

    fun addCommentRow(
        comment: Text,
        maxLineLength: Int,
        forComponent: Boolean,
        withLeftGap: Boolean = true
    ): LayoutTag {
        return addCommentRow(createCommentComponent(comment, maxLineLength), forComponent, withLeftGap)
    }

    override fun commentRow(text: Text, maxLineLength: Int, withLeftGap: Boolean): LayoutTag {
        return addCommentRow(
            comment = text,
            maxLineLength,
            forComponent = false,
            withLeftGap = withLeftGap
        )
    }

    override fun commentNoWrap(text: Text): CellBuilder<JLabel> {
        return component(createNoWrapCommentComponent(text)).withLeftGap()
    }

    override fun createCommentRow(component: JComponent): Row {
        val cc = CC()
        cc.vertical.gapBefore = gapToBoundSize(
            if (subRows == null) {
                spacing.verticalGap
            } else {
                spacing.largeVerticalGap
            },
            isHorizontal = false
        )
        cc.vertical.gapAfter = gapToBoundSize(spacing.verticalGap, false)

        val row = createChildRow(label = null, noGrid = true)
        getOrCreateCommentRowsList().add(row)
        row.addComponent(component, cc)
        return row
    }

    private fun createCommentComponent(text: Text, maxLineLength: Int): JComponent {
        val textArea = ConstrainedTextArea(text, maxLineLength)
        textArea.isEditable = false
        textArea.selectable = false
        DynamicUI.withDynamic(textArea) {
            it.foreground = UIFactory.secondaryTextForeground.asUIResource()
            it.isOpaque = false
        }
        return textArea
    }

    private fun createNoWrapCommentComponent(text: Text): WrappedComponent<JLabel> {
        val wrapped = UIFactory.createLabel(text)
        DynamicUI.withDynamic(wrapped.component) {
            it.foreground = UIFactory.secondaryTextForeground.asUIResource()
            it.isOpaque = false
        }
        return wrapped
    }

    fun addCommentRow(component: JComponent, forComponent: Boolean, withLeftGap: Boolean = true): LayoutTag {
        gapAfter = "${spacing.commentVerticalTopGap}px!"

        val isParentRowLabeled = labeled
        return createCommentRow(
            this,
            component,
            indentationLevel,
            isParentRowLabeled,
            forComponent,
            withLeftGap,
            columnIndex
        )
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
        return createChildRow(label = label?.let { UIFactory.createLabel(it) })
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

    override fun row(label: Text?, separated: Boolean, isIndented: Boolean, init: Row.() -> Unit): Row {
        val newRow = super.row(label, separated, isIndented, init)
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

    override fun enableIf(predicate: ObservableCondition): MigLayoutRow {
        this.enabled = predicate.get()
        predicate.onChange { this.enabled = it }
        return this
    }

    override fun visibleIf(predicate: ObservableCondition): MigLayoutRow {
        this.visible = predicate.get()
        predicate.onChange { this.visible = it }
        return this
    }

    override fun commitImmediately() {
        if (commitImmediately) return
        commitImmediately = true
        // Update child rows
        getOrCreateSubRowsList().forEach { it.commitImmediately() }
        childCells.forEach { it.commitImmediately() }
    }

    private class ConstrainedTextArea(boundText: Text, private val maxLineLength: Int) : JTextArea() {
        private var oldHighlighter = highlighter
        var selectable: Boolean = true
            set(value) {
                isFocusable = value
                highlighter = if (!value) null else oldHighlighter
                field = value
            }
        private var prefWidth: Int = 0

        override fun getPreferredSize(): Dimension {
            return super.getPreferredSize().apply { width = if (isShowing) 0 else prefWidth }
        }

        override fun addNotify() {
            super.addNotify()
            invokeLater {
                lineWrap = true
            }
        }

        init {
            minimumSize = Dimension(10, 10)
            boundText.bind {
                text = it

                val oldWrap = lineWrap
                lineWrap = false
                columns = 0

                prefWidth = super.getPreferredSize().width

                val maxLength = maxLineLength
                val prefWidthWithColumns = insets.width + columnWidth * maxLength
                val preWidthWithText = super.getPreferredSize().width
                if (preWidthWithText > prefWidthWithColumns) {
                    columns = max(0, maxLength)
                }
                maximumSize = if (columns > 0) {
                    val w = insets.width + columnWidth * columns
                    prefWidth = min(prefWidth, w)
                    Dimension(w, Int.MAX_VALUE)
                } else null

                lineWrap = oldWrap
            }
        }
    }
}
