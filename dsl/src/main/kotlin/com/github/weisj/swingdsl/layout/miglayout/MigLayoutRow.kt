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
import com.github.weisj.swingdsl.core.binding.Property
import com.github.weisj.swingdsl.core.binding.bind
import com.github.weisj.swingdsl.core.condition.ObservableCondition
import com.github.weisj.swingdsl.core.text.HasTextProperty
import com.github.weisj.swingdsl.core.text.Text
import com.github.weisj.swingdsl.core.text.textProperty
import com.github.weisj.swingdsl.highlight.LayoutTag
import com.github.weisj.swingdsl.highlight.StringSearchPointSink
import com.github.weisj.swingdsl.highlight.createLayoutTag
import com.github.weisj.swingdsl.invokeLater
import com.github.weisj.swingdsl.laf.CollapsibleComponent
import com.github.weisj.swingdsl.laf.WrappedComponent
import com.github.weisj.swingdsl.layout.CellBuilder
import com.github.weisj.swingdsl.layout.IndentationPolicy
import com.github.weisj.swingdsl.layout.ModifiablePanel
import com.github.weisj.swingdsl.layout.PanelBuilder
import com.github.weisj.swingdsl.layout.Row
import com.github.weisj.swingdsl.layout.SpacingConfiguration
import com.github.weisj.swingdsl.layout.createLayoutBuilder
import com.github.weisj.swingdsl.style.DynamicUI
import com.github.weisj.swingdsl.style.UIFactory
import com.github.weisj.swingdsl.style.asTextProperty
import com.github.weisj.swingdsl.style.asUIResource
import com.github.weisj.swingdsl.util.SharedLazyComponents
import com.github.weisj.swingdsl.util.getTextPropertyForComponent
import com.github.weisj.swingdsl.width
import net.miginfocom.layout.BoundSize
import net.miginfocom.layout.CC
import net.miginfocom.layout.ConstraintParser
import net.miginfocom.layout.DimConstraint
import net.miginfocom.layout.LayoutUtil
import java.awt.Dimension
import java.awt.Insets
import java.lang.Integer.min
import javax.swing.*
import javax.swing.border.LineBorder
import javax.swing.text.JTextComponent
import kotlin.collections.ArrayList
import kotlin.math.max
import kotlin.reflect.KMutableProperty0

internal class MigLayoutRow(
    private val parent: MigLayoutRow?,
    override val builder: MigLayoutBuilder,
    private val labeled: Boolean = false,
    val noGrid: Boolean = false,
    private val indentationLevel: Int,
    private val incrementsIndentationLevel: Boolean = parent != null
) : Row() {

    companion object {
        private const val HIDE_MODE_ZERO_SIZE = 2
        private const val HIDE_MODE_IGNORE_CELL = 3
        internal const val COMPONENT_ENABLED_STATE_KEY = "MigLayoutRow.enabled"
        internal const val COMPONENT_VISIBLE_STATE_KEY = "MigLayoutRow.visible"

        // as static method to ensure that members of current row are not used
        private fun createCommentRow(
            parent: MigLayoutRow,
            component: JComponent,
            indent: Int,
            isParentRowLabeled: Boolean,
            forComponent: Boolean,
            columnIndex: Int,
            withLeftGap: Boolean,
        ) {
            val cc = CC()
            val commentRow = parent.createChildRow()
            parent.getOrCreateAssociatedRows().add(commentRow)
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
        }

        // as static method to ensure that members of current row are not used
        // Returns the layout tag corresponding to the title.
        private fun configureSeparatorRow(row: MigLayoutRow, title: Text?) {
            val separatorSpec = UIFactory.createSeparatorComponent(title?.asTextProperty())
            val comp = if (separatorSpec.providesCustomComponent()) {
                separatorSpec.provided!!.also {
                    if (title != null) row.issueSearchTag(title, it.createLayoutTag())
                }
            } else {
                val separator = TitledSeparator(title)
                if (title != null) row.issueSearchTag(title, separator.label.createLayoutTag())
                DynamicUI.withDynamic(separator) {
                    val dividerColor = UIFactory.dividerColor
                    it.color = dividerColor.enabled
                    it.disabledColor = dividerColor.disabled
                }
            }
            row.addTitleComponent(comp, isEmpty = title == null)
        }

        private fun configureCollapsibleSeparatorRow(row: MigLayoutRow, title: Text): CollapsibleComponent {
            val separatorSpec = UIFactory.createCollapsibleSeparatorComponent(title.asTextProperty())

            val separator = if (separatorSpec.providesCustomComponent()) {
                separatorSpec.provided!!.also {
                    row.issueSearchTag(title, it.component.createLayoutTag())
                }
            } else {
                DynamicUI.withDynamic(CollapsibleTitledSeparator(title)) {
                    val dividerColor = UIFactory.dividerColor
                    val expandedIcon = UIFactory.expandedIcon
                    val collapsedIcon = UIFactory.collapsedIcon
                    it.color = dividerColor.enabled
                    it.disabledColor = dividerColor.disabled
                    it.expandedIcon = expandedIcon.enabled
                    it.collapsedIcon = collapsedIcon.enabled
                    it.disabledExpandedIcon = expandedIcon.disabled
                    it.disabledCollapsedIcon = collapsedIcon.disabled
                }.also {
                    row.issueSearchTag(title, it.label.createLayoutTag())
                }
            }
            row.addTitleComponent(separator.component, isEmpty = false)
            return separator
        }
    }

    internal val components: MutableList<JComponent> = mutableListOf()
    private val childCells: MutableList<MigLayoutCellBuilder<*>> = mutableListOf()
    internal var rightIndex = Int.MAX_VALUE

    private var columnIndex = -1
    internal var subRows: MutableList<MigLayoutRow>? = null
        private set

    private var associatedRows: MutableList<MigLayoutRow>? = null

    private fun getOrCreateAssociatedRows(): MutableList<MigLayoutRow> {
        var rows = associatedRows
        if (rows == null) {
            // commentRows in most cases == 1
            rows = mutableListOf()
            this.associatedRows = rows
        }
        return rows
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
        set(value) {
            field = value
            rowConstraints?.gapAfter = if (value == null) null
            else ConstraintParser.parseBoundSize(value, true, false)
        }

    internal var rowConstraints: DimConstraint? = null

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

    // Returns whether the state needs to be updated.
    private fun JComponent.updateStateFlag(
        value: Boolean,
        componentValue: Boolean,
        key: String,
        preserve: Boolean = true
    ): Boolean {
        // Current state of row is the opposite of value
        if (!value) {
            if (!componentValue) {
                // current state of component differs from current row state
                // - preserve current state to apply it when row state will be changed
                putClientProperty(key, false)
            }
        } else {
            if (getClientProperty(key) == false) {
                // remove because for active row component state can be changed and we don't want to
                // add listener to update value accordingly
                putClientProperty(key, if (preserve) null else true)
                // do not set to true, preserve old component state
                return false
            }
        }
        return true
    }

    override var enabled = parent?.subRowsEnabled ?: true
        set(value) {
            if (field == value) return
            field = value

            for (c in components) {
                if (c.updateStateFlag(value, c.isEnabled, COMPONENT_ENABLED_STATE_KEY)) {
                    c.isEnabled = value
                }
            }
            associatedRows?.let {
                for (row in it) {
                    row.enabled = value
                }
            }
        }

    private fun updateHideMode(c: JComponent, index: Int, value: Boolean) {
        builder.componentConstraints[c]?.hideMode =
            if (index == components.size - 1 && value) HIDE_MODE_ZERO_SIZE else HIDE_MODE_IGNORE_CELL
    }

    internal fun JComponent.safelySetVisible(value: Boolean, index: Int) {
        putClientProperty(COMPONENT_VISIBLE_STATE_KEY, value)
        isVisible = value && !collapsed
        if (index >= 0) updateHideMode(this, index, value)
        invalidate()
    }

    override var visible = parent?.subRowsVisible ?: true
        set(value) {
            if (field == value) return

            field = value
            // Only update if the row isn't hidden or if we make the row hidden anyway.
            for ((index, c) in components.withIndex()) {
                c.safelySetVisible(value, index)
            }
            associatedRows?.let {
                for (row in it) {
                    row.visible = value
                }
            }
        }

    private fun setCollapsedState(value: Boolean) {
        collapsedRoot = value
        collapsed = value
    }

    private var collapsedRoot = false
    private var collapsed = false
        set(v) {
            val value = v || collapsedRoot
            if (field == value) return
            field = v

            val visible = !value
            for ((index, c) in components.withIndex()) {
                if (c.updateStateFlag(visible, c.isVisible, COMPONENT_VISIBLE_STATE_KEY, preserve = false)) {
                    c.isVisible = visible
                    updateHideMode(c, index, visible)
                }
            }

            subRows?.forEach {
                it.collapsed = value
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
                if (it != subRows!!.last()) {
                    it.gapAfter = if (value) null else "0px!"
                }
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

    private var _searchSink: StringSearchPointSink? = null
    private val searchSink: StringSearchPointSink?
        get() = _searchSink ?: parent?.searchSink

    private fun issueSearchTag(prop: Property<String>, tag: LayoutTag) {
        searchSink?.onSearchPointCreated(prop, tag)
    }

    override fun setSearchPointSink(sink: StringSearchPointSink?) {
        _searchSink = sink
    }

    override fun withSearchPointSink(sink: StringSearchPointSink, init: Row.() -> Unit) {
        val currentSink = _searchSink
        setSearchPointSink(sink)
        this.init()
        setSearchPointSink(currentSink)
    }

    override fun createChildRow(
        label: WrappedComponent<JLabel>?,
        isSeparated: Boolean,
        isIndented: IndentationPolicy,
        noGrid: Boolean,
        title: Text?
    ): MigLayoutRow {
        return createChildRow(indentationLevel, label, noGrid, isIndented)
    }

    private fun createChildRow(
        indent: Int,
        label: WrappedComponent<JLabel>? = null,
        noGrid: Boolean = false,
        isIndented: IndentationPolicy = IndentationPolicy.DEFAULT,
        incrementsIndent: Boolean = true,
    ): MigLayoutRow {
        val subRows = getOrCreateSubRowsList()
        val newIndent =
            if (isIndented.toBool() ?: this.incrementsIndentationLevel) indent + spacing.indentLevel else indent

        val row = MigLayoutRow(
            this, builder,
            labeled = label != null,
            noGrid = noGrid,
            indentationLevel = if (subRowIndentationLevel >= 0) subRowIndentationLevel * spacing.indentLevel else newIndent,
            incrementsIndentationLevel = incrementsIndent
        )

        var insertIndex = subRows.size
        if (insertIndex > 0 && subRows[insertIndex - 1].isTrailingSeparator) {
            insertIndex--
        }
        if (insertIndex > 0 && subRows[insertIndex - 1].isComment) {
            insertIndex--
        }
        subRows.add(insertIndex, row)

        if (label != null) {
            issueSearchTag(label.component.textProperty(), label.createLayoutTag())
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
        addComponent(titleComponent, cc, createSearchTag = false)
    }

    override fun titledRow(title: Text, init: Row.() -> Unit): Row {
        return createBlockRow(title, true, init)
    }

    override fun blockRow(init: Row.() -> Unit): Row {
        return createBlockRow(null, false, init)
    }

    private fun createBlockRow(title: Text?, isSeparated: Boolean, init: Row.() -> Unit): Row {
        val separatorRow = if (isSeparated) {
            val separatorRow = createChildRow(indent = 0, isIndented = IndentationPolicy.NO)
            configureSeparatorRow(separatorRow, title)
            separatorRow
        } else null
        val parentRow = createChildRow(
            indent = indentationLevel,
            isIndented = IndentationPolicy.YES,
            incrementsIndent = false
        )
        if (separatorRow != null) {
            parentRow.getOrCreateAssociatedRows().add(separatorRow)
        }
        parentRow.init()

        val result = parentRow.createChildRow()
        result.placeholder()
        result.largeGapAfter()
        return parentRow
    }

    override fun hideableRow(title: Text, startHidden: Boolean, init: Row.() -> Unit): Row {
        val separatorRow = createChildRow(indent = 0, isIndented = IndentationPolicy.NO)
        val collapsibleSeparator = configureCollapsibleSeparatorRow(separatorRow, title)
        builder.hideableRowNestingLevel++
        try {
            val panelRow = createChildRow(
                indent = indentationLevel,
                isIndented = IndentationPolicy.YES,
                incrementsIndent = false
            )
            panelRow.getOrCreateAssociatedRows().add(separatorRow)
            panelRow.noIndent(init)
            collapsibleSeparator.setCollapseCallback {
                panelRow.setCollapsedState(true)
            }
            collapsibleSeparator.setExpandCallback {
                panelRow.setCollapsedState(false)
            }
            if (startHidden || !panelRow.enabled) {
                collapsibleSeparator.collapse()
            } else {
                collapsibleSeparator.expand()
            }
            return panelRow
        } finally {
            builder.hideableRowNestingLevel--
        }
    }

    override fun nestedPanel(title: Text?, init: PanelBuilder.() -> Unit): CellBuilder<ModifiablePanel> {
        val nestedBuilder = createLayoutBuilder()
        nestedBuilder.init()

        val panel = ModifiablePanel(title, layout = null, topLevel = false)
        nestedBuilder.builder.build(panel, arrayOf())
        mergeCallbacks(builder.applyCallbacks, nestedBuilder.builder.applyCallbacks)
        mergeCallbacks(builder.resetCallbacks, nestedBuilder.builder.resetCallbacks)
        mergeCallbacks(builder.isModifiedCallbacks, nestedBuilder.builder.isModifiedCallbacks)

        lateinit var panelBuilder: CellBuilder<ModifiablePanel>
        row {
            @Suppress("UNCHECKED_CAST", "USELESS_CAST")
            panelBuilder = (panel as JComponent)(constraints = arrayOf(growX)) as CellBuilder<ModifiablePanel>
        }
        return panelBuilder
    }

    private fun <K, V> mergeCallbacks(map: MutableMap<K, MutableList<V>>, nestedMap: Map<K, List<V>>) {
        for ((requester, callbacks) in nestedMap) {
            map.getOrPut(requester) { mutableListOf() }.addAll(callbacks)
        }
    }

    override fun <T : JComponent> component(component: T): CellBuilder<T> {
        addComponent(component)
        return createAndAddCell(component, components.size - 1)
    }

    override fun <T : JComponent> component(wrappedComponent: WrappedComponent<T>): CellBuilder<T> {
        addComponent(wrappedComponent.container)
        return createAndAddCell(wrappedComponent.component, components.size - 1)
    }

    private fun <T : JComponent> createAndAddCell(comp: T, componentIndex: Int): CellBuilder<T> {
        return MigLayoutCellBuilder(builder, this, comp, componentIndex).also { childCells.add(it) }
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

    internal fun addComponent(component: JComponent, cc: CC = CC(), createSearchTag: Boolean = true) {
        if (createSearchTag) {
            getTextPropertyForComponent(component)?.let {
                issueSearchTag(it, component.createLayoutTag())
            }
        }
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

        builder.defaultComponentConstraintCreator.addGrowIfNeeded(cc, component, spacing)

        if (!noGrid && indentationLevel > 0 && components.size == 1) {
            cc.horizontal.gapBefore = gapToBoundSize(indentationLevel, true)
        }

        cc.hideMode = HIDE_MODE_IGNORE_CELL // Hidden cells shouldn't take up any cell at all

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
        component.safelySetVisible(visible && component.isVisible, -1)
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
        withLeftGap: Boolean,
    ) {
        return addCommentRow(
            createCommentComponent(comment, maxLineLength),
            forComponent,
            withLeftGap
        )
    }

    override fun commentRow(text: Text, maxLineLength: Int, withLeftGap: Boolean) {
        return addCommentRow(
            comment = text,
            maxLineLength,
            forComponent = false,
            withLeftGap
        )
    }

    fun addCommentRow(component: JComponent, forComponent: Boolean, withLeftGap: Boolean) {
        gapAfter = "${spacing.commentVerticalTopGap}px!"

        val isParentRowLabeled = labeled
        createCommentRow(
            this,
            component,
            indentationLevel,
            isParentRowLabeled,
            forComponent,
            columnIndex,
            withLeftGap
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
        getOrCreateAssociatedRows().add(row)
        row.addComponent(component, cc)
        return row
    }

    private fun createCommentComponent(text: Text, maxLineLength: Int): JComponent {
        val textArea = ConstrainedTextArea(text, maxLineLength)
        textArea.isEditable = false
        textArea.selectable = false
        textArea.margin = Insets(0, 0, 0, 0)
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
        subRowsEnabledIf(result.component)
        return result
    }

    override fun radioButton(
        text: Text,
        prop: KMutableProperty0<Boolean>,
        comment: Text?
    ): CellBuilder<JRadioButton> {
        return super.radioButton(text, prop, comment).also { subRowsEnabledIf(it.component) }
    }

    override fun onGlobalApply(callback: () -> Unit): Row {
        builder.applyCallbacks.getOrPut(null) { mutableListOf() }.add(callback)
        return this
    }

    override fun onGlobalReset(callback: () -> Unit): Row {
        builder.resetCallbacks.getOrPut(null) { mutableListOf() }.add(callback)
        return this
    }

    override fun onGlobalIsModified(callback: () -> Boolean): Row {
        builder.isModifiedCallbacks.getOrPut(null) { mutableListOf() }.add(callback)
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

    override fun enableIf(predicate: ObservableCondition): MigLayoutRow {
        predicate.bind { this.enabled = it && (parent?.subRowsEnabled ?: true) }
        return this
    }

    override fun visibleIf(predicate: ObservableCondition): MigLayoutRow {
        predicate.bind { this.visible = it && (parent?.subRowsVisible ?: true) }
        return this
    }

    override fun commitImmediately() {
        if (commitImmediately) return
        commitImmediately = true
        // Update child rows
        getOrCreateSubRowsList().forEach { it.commitImmediately() }
        childCells.forEach { it.commitImmediately() }
    }

    private class ConstrainedTextArea(override val textProp: Text, private val maxLineLength: Int) :
        JTextArea(),
        HasTextProperty {
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

        override fun updateUI() {
            super.updateUI()
            font = SharedLazyComponents.label.font
        }

        init {
            minimumSize = Dimension(10, 10)
            textProp.bind {
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
