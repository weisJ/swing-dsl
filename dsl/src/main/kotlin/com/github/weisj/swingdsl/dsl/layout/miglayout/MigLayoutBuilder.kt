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
package com.github.weisj.swingdsl.dsl.layout.miglayout

import com.github.weisj.swingdsl.dsl.layout.Cell.Companion.UNBOUND_RADIO_BUTTON
import com.github.weisj.swingdsl.dsl.layout.Cell.Companion.pushY
import com.github.weisj.swingdsl.dsl.layout.LCFlags
import com.github.weisj.swingdsl.dsl.layout.ModifiablePanel
import com.github.weisj.swingdsl.dsl.layout.PanelBuilderImpl
import com.github.weisj.swingdsl.dsl.layout.SpacingConfiguration
import com.github.weisj.swingdsl.dsl.layout.miglayout.patched.PatchedMigLayout
import com.github.weisj.swingdsl.laf.WrappedComponent
import net.miginfocom.layout.AC
import net.miginfocom.layout.BoundSize
import net.miginfocom.layout.CC
import net.miginfocom.layout.LC
import java.awt.Component
import java.awt.Container
import java.util.*
import javax.swing.ButtonGroup
import javax.swing.JCheckBox
import javax.swing.JComboBox
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPasswordField
import javax.swing.JTextField

internal class MigLayoutBuilder(val spacing: SpacingConfiguration) : PanelBuilderImpl {
    companion object {
        private var hRelatedGap = -1
        private var vRelatedGap = -1

        private fun setRelatedGap(h: Int, v: Int) {
            if (hRelatedGap == h && vRelatedGap == v) {
                return
            }

            hRelatedGap = h
            vRelatedGap = v
        }
    }

    init {
        setRelatedGap(spacing.horizontalGap, spacing.verticalGap)
    }

    internal var parentContainer: Container? = null

    /**
     * Map of component to constraints shared among rows (since components are unique)
     */
    internal val componentConstraints: MutableMap<Component, CC> = IdentityHashMap()
    private val buttonGroupStack: MutableList<ButtonGroup> = mutableListOf()
    internal val topButtonGroup: ButtonGroup?
        get() = buttonGroupStack.lastOrNull()
    internal var hideableRowNestingLevel = 0

    override val rootRow = MigLayoutRow(parent = null, builder = this, indentationLevel = 0)
    override var applyCallbacks: MutableMap<JComponent?, MutableList<() -> Unit>> = linkedMapOf()
    override var resetCallbacks: MutableMap<JComponent?, MutableList<() -> Unit>> = linkedMapOf()
    override var isModifiedCallbacks: MutableMap<JComponent?, MutableList<() -> Boolean>> = linkedMapOf()

    override fun withButtonGroup(buttonGroup: ButtonGroup, body: () -> Unit) {
        buttonGroupStack.add(buttonGroup)
        try {
            body()

            resetCallbacks.getOrPut(null) { mutableListOf() }.add {
                selectRadioButtonInGroup(buttonGroup)
            }
        } finally {
            buttonGroupStack.removeAt(buttonGroupStack.size - 1)
        }
    }

    private fun selectRadioButtonInGroup(buttonGroup: ButtonGroup) {
        if (buttonGroup.selection == null && buttonGroup.buttonCount > 0) {
            val e = buttonGroup.elements
            while (e.hasMoreElements()) {
                val radioButton = e.nextElement()
                if (radioButton.getClientProperty(UNBOUND_RADIO_BUTTON) != null) {
                    buttonGroup.setSelected(radioButton.model, true)
                    return
                }
            }

            buttonGroup.setSelected(buttonGroup.elements.nextElement().model, true)
        }
    }

    internal val defaultComponentConstraintCreator = DefaultComponentConstraintCreator(spacing)

    // keep in mind - MigLayout always creates one more than need column constraints (i.e. for 2 will be 3)
    // it doesn't lead to any issue.
    val columnConstraints = AC()

    // MigLayout in any case always creates CC, so, create instance even if it is not required
    private val Component.constraints: CC
        get() = componentConstraints.getOrPut(this) { CC() }

    fun updateComponentConstraints(wrapped: WrappedComponent<*>, callback: CC.() -> Unit) {
        wrapped.container.constraints.callback()
    }

    override fun build(container: Container, isNested: Boolean, layoutConstraints: Array<out LCFlags>) {
        if (!isNested) {
            // If a component has a specified pushY with hideMode > 1 (=HIDE_MODE_ZERO_SIZE_WITH_GAP) then
            // it will impose the default push constraints on all components if it isn't visible. This results
            // In invisible components being resized.
            // See Grid#286, Grid#395, Grid#getDefaultPushWeights
            // Forcing a final placeholder specifying pushY, which is always visible solves this issue.
            // In this case only the specified components who specified pushY will resize with more space.
            // If we are nested the parent panel takes the responsibility to provide the spacer.
            rootRow.row {
                placeholder().constraints(pushY)() {
                    updateComponentConstraints(wrappedComponent) {
                        // Ensure other components that specify grow get the space first.
                        growPrioY(-100)
                    }
                }
                // Remove gap from row. It shouldn't take up any space if not needed.
                (this as MigLayoutRow).gapAfter = "0px!"
            }
        }

        val lc = createLayoutConstraints()
        lc.gridGapY = gapToBoundSize(spacing.verticalGap, false)
        if (layoutConstraints.isEmpty()) {
            lc.fillX()
            // not fillY because it leads to enormously large cells - we use cc `push` in addition to cc `grow` as a more robust and easy solution
        } else {
            lc.apply(layoutConstraints)
        }

        /**
         * With darklaf input fields (text fields, checkboxes, buttons and so on) have focus ring that drawn outside of component border.
         * If reported component dimensions will be equals to visible (when unfocused) component dimensions, focus ring will be clipped.
         *
         * Since LaF cannot control component environment (host component), default safe strategy is to report component dimensions including focus ring.
         * But it leads to an issue - spacing specified for visible component borders, not to compensated. For example, if horizontal space must be 8px,
         * this 8px must be between one visible border of component to another visible border (in the case of IntelliJ Light theme, gray 1px borders).
         * Exactly 8px.
         *
         * So, advanced layout engine, e.g. MigLayout, offers a way to compensate visual padding on the layout container level, not on component level, as a solution.
         */
        lc.isVisualPadding = true

        val rowConstraints = AC()

        var isLayoutInsetsAdjusted = false
        container.layout = object : PatchedMigLayout(lc, columnConstraints, rowConstraints) {
            override fun layoutContainer(parent: Container) {
                if (!isLayoutInsetsAdjusted) {
                    isLayoutInsetsAdjusted = true
                    if ((container as? JComponent)?.getClientProperty(ModifiablePanel.DIALOG_CONTENT_PANEL_PROPERTY) != null) {
                        // since we compensate visual padding, child components should be not clipped,
                        // so, we do not use content pane DialogWrapper border (returns null),
                        // but instead set insets to our content panel (so, child components are not clipped)
                        lc.setInsets(spacing.dialogTopBottom, spacing.dialogLeftRight)
                    }
                }
                super.layoutContainer(parent)
            }
        }

        configureGapBetweenColumns(rootRow)

        val physicalRows = collectPhysicalRows(rootRow)

        configureGapsBetweenRows(physicalRows)

        val isNoGrid = layoutConstraints.contains(LCFlags.noGrid)
        if (isNoGrid) {
            physicalRows.flatMap { it.components }.forEach { component ->
                container.add(component, component.constraints)
            }
        } else {
            configureGridRowConstraints(physicalRows, rowConstraints, container)
        }

        traverseRows(rootRow) {
            it.saveInitialValues()
        }

        // do not hold components
        componentConstraints.clear()
        container.doLayout()
        parentContainer = container
    }

    private fun configureGridRowConstraints(
        physicalRows: List<MigLayoutRow>,
        rowConstraints: AC,
        container: Container
    ) {
        for ((rowIndex, row) in physicalRows.withIndex()) {
            row.rowConstraints = rowConstraints.index(rowIndex).constaints[rowIndex]
            if (row.noGrid) {
                rowConstraints.noGrid(rowIndex)
            } else {
                row.gapAfter?.let {
                    rowConstraints.gap(it, rowIndex)
                }
            }
            // if constraint specified only for rows 0 and 1, MigLayout will use constraint 1 for any rows with index 1+ (see LayoutUtil.getIndexSafe - use last element if index > size)
            // so, we set for each row to make sure that constraints from previous row will be not applied
            rowConstraints.align("baseline", rowIndex)

            for ((index, component) in row.components.withIndex()) {
                val cc = component.constraints

                // we cannot use columnCount as an indicator of whether to use spanX/wrap or not because component can share cell with another component,
                // in any case MigLayout is smart enough and unnecessary spanX doesn't harm
                if (index == row.components.size - 1) {
                    cc.spanX()
                    cc.isWrap = true
                }

                if (index >= row.rightIndex) {
                    configureRightAlignment(row, index, cc)
                }

                container.add(component, cc)
            }
        }
    }

    private fun configureRightAlignment(
        row: MigLayoutRow,
        index: Int,
        cc: CC
    ) {
        val cellModeSpan = row.cellModeSpans.find { it.contains(index) }
        if (cellModeSpan == null || index == cellModeSpan.start || cellModeSpan.isVerticalFlow) {
            // If we aren't in a joined cell simply add the spacing.
            // If we are in a joined cell which is vertical we need to add the spacer to all components.
            // Otherwise we need to make sure to only add the spacing to the first component.
            cc.horizontal.gapBefore = BoundSize(null, null, null, true, null)
        }
    }

    private fun collectPhysicalRows(rootRow: MigLayoutRow): List<MigLayoutRow> {
        val result = mutableListOf<MigLayoutRow>()
        traverseRows(rootRow) {
            // skip synthetic rows that don't have components (e.g. titled row that contains only sub rows)
            if (it.components.isNotEmpty()) {
                result.add(it)
            }
        }
        return result
    }

    private fun traverseRows(
        rootRow: MigLayoutRow,
        action: (MigLayoutRow) -> Unit
    ) {
        fun traverse(subRows: List<MigLayoutRow>?) {
            subRows?.forEach { row ->
                action(row)
                traverse(row.subRows)
            }
        }
        traverse(rootRow.subRows)
    }

    private fun configureGapBetweenColumns(rootRow: MigLayoutRow) {
        var startColumnIndexToApplyHorizontalGap = 0
        if (rootRow.isLabeledIncludingSubRows) {
            // using columnConstraints instead of component gap allows easy debug (proper painting of debug grid)
            columnConstraints.gap("${spacing.labelColumnHorizontalGap}px!", 0)
            columnConstraints.grow(0f, 0)
            startColumnIndexToApplyHorizontalGap = 1
        }

        val gapAfter = "${spacing.horizontalGap}px!"
        for (i in startColumnIndexToApplyHorizontalGap until rootRow.columnIndexIncludingSubRows) {
            columnConstraints.gap(gapAfter, i)
        }
    }

    private fun configureGapsBetweenRows(physicalRows: List<MigLayoutRow>) {
        for (rowIndex in physicalRows.indices) {
            if (rowIndex == 0) continue

            val prevRow = physicalRows[rowIndex - 1]
            val nextRow = physicalRows[rowIndex]

            val prevRowType = getRowType(prevRow)
            val nextRowType = getRowType(nextRow)
            if (prevRowType.isCheckboxRow && nextRowType.isCheckboxRow &&
                (prevRowType == RowType.CHECKBOX_TALL || nextRowType == RowType.CHECKBOX_TALL)
            ) {
                configureGapsBetweenTallCheckBoxRows(prevRow, nextRow)
            } else if (prevRowType == RowType.NESTED_PANEL) {
                prevRow.gapAfter = "0px!"
            }
        }
    }

    private fun configureGapsBetweenTallCheckBoxRows(prevRow: MigLayoutRow, nextRow: MigLayoutRow) {
        // ugly patching to make UI pretty IDEA-234078
        if (prevRow.gapAfter == null &&
            prevRow.components.all { it.constraints.vertical.gapAfter == null } &&
            nextRow.components.all { it.constraints.vertical.gapBefore == null }
        ) {
            prevRow.gapAfter = "0px!"

            for ((index, component) in prevRow.components.withIndex()) {
                if (index == 0) {
                    component.constraints.gapBottom("${spacing.componentVerticalGap}px!")
                } else {
                    component.constraints.gapBottom("${component.insets.bottom}px!")
                }
            }
            for ((index, component) in nextRow.components.withIndex()) {
                if (index == 0) {
                    component.constraints.gapTop("${spacing.componentVerticalGap}px!")
                } else {
                    component.constraints.gapTop("${component.insets.top}px!")
                }
            }
        }
    }

    private fun getRowType(row: MigLayoutRow): RowType {
        if (row.components[0] is JCheckBox) {
            when {
                row.components.all {
                    it is JCheckBox || it is JLabel
                } -> return RowType.CHECKBOX
                row.components.all {
                    it is JCheckBox || it is JLabel || it is JTextField ||
                        it is JPasswordField || it is JComboBox<*>
                } -> return RowType.CHECKBOX_TALL
            }
        }
        if (row.components.singleOrNull() is ModifiablePanel) {
            return RowType.NESTED_PANEL
        }
        return RowType.GENERIC
    }

    private enum class RowType {
        GENERIC, CHECKBOX, CHECKBOX_TALL, NESTED_PANEL;

        val isCheckboxRow get() = this == CHECKBOX || this == CHECKBOX_TALL
    }
}

private fun LC.apply(flags: Array<out LCFlags>): LC {
    for (flag in flags) {
        @Suppress("NON_EXHAUSTIVE_WHEN")
        when (flag) {
            LCFlags.noGrid -> isNoGrid = true

            LCFlags.flowY -> isFlowX = false

            LCFlags.fill -> fill()
            LCFlags.fillX -> isFillX = true
            LCFlags.fillY -> isFillY = true

            LCFlags.debug -> debug()
        }
    }
    return this
}
