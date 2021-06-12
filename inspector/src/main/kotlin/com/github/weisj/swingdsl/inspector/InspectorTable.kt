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
package com.github.weisj.swingdsl.inspector

import com.github.weisj.swingdsl.border.bottomBorder
import com.github.weisj.swingdsl.component.DefaultJPanel
import com.github.weisj.swingdsl.configureBorderLayout
import com.github.weisj.swingdsl.core.binding.ObservableMutableProperty
import com.github.weisj.swingdsl.core.binding.ObservableProperty
import com.github.weisj.swingdsl.core.binding.bind
import com.github.weisj.swingdsl.core.binding.derive
import com.github.weisj.swingdsl.core.binding.isInstance
import com.github.weisj.swingdsl.core.binding.observableProperty
import com.github.weisj.swingdsl.core.binding.plus
import com.github.weisj.swingdsl.core.condition.ObservableCondition
import com.github.weisj.swingdsl.core.condition.not
import com.github.weisj.swingdsl.core.text.Text
import com.github.weisj.swingdsl.core.text.unaryPlus
import com.github.weisj.swingdsl.layout.ModifiablePanel
import com.github.weisj.swingdsl.layout.Row
import com.github.weisj.swingdsl.layout.RowBuilder
import com.github.weisj.swingdsl.layout.miglayout.patched.PatchedMigLayout
import com.github.weisj.swingdsl.layout.noGrowY
import com.github.weisj.swingdsl.layout.panel
import com.github.weisj.swingdsl.scrollPane
import com.github.weisj.swingdsl.unaryPlus
import net.miginfocom.swing.MigLayout
import java.awt.BorderLayout
import java.awt.CardLayout
import java.awt.Component
import java.awt.Dimension
import java.awt.GridBagLayout
import java.util.*
import javax.swing.Icon
import javax.swing.JTable
import javax.swing.table.DefaultTableModel
import javax.swing.table.TableColumnModel
import kotlin.math.min

internal class InspectorTable(component: Component) : DefaultJPanel(BorderLayout()) {

    private val model = InspectorTableModel(component)
    private val table = object : JTable(model) {
        override fun isCellEditable(row: Int, column: Int): Boolean = false
    }
    private val dimensionsComponent = DimensionsComponent(component)

    private val comp: ObservableMutableProperty<Component?> = observableProperty(component)
    private val beans = observableProperty(ComponentPropertyBeans(component))

    @OptIn(ExperimentalStdlibApi::class)
    @Suppress("UNCHECKED_CAST")
    private val clientProperties: ObservableProperty<Vector<Vector<Any?>>?> = beans.derive {
        val arrayTable = it.properties["clientProperties"]?.value ?: return@derive null
        when (val table: Any? = getFieldValue(arrayTable.javaClass, Any::class.java, arrayTable, "table")) {
            is Hashtable<*, *> -> {
                val rows = Vector<Vector<Any?>>(table.size)
                table.forEach { key, value ->
                    rows.addElement(Vector(listOf(key, value)))
                }
                rows
            }
            is Array<*> -> {
                check(table.size % 2 == 0)
                val rows = Vector<Vector<Any?>>((table.size / 2) * 100)
                for (i in 0 until table.size / 2) {
                    rows.addElement(Vector(listOf(table[2 * i], table[2 * i + 1])))
                }
                rows
            }
            else -> null
        }
    }
    private val clientPropertyTable: JTable = createClientPropertyTable()

    init {
        comp.onChange {
            if (it != null) beans.set(ComponentPropertyBeans(it))
        }
        val columnModel: TableColumnModel = table.columnModel
        columnModel.getColumn(0).apply {
            minWidth = 220
            maxWidth = 200
            resizable = true
            cellRenderer = PropertyNameRenderer()
        }
        columnModel.getColumn(1).apply {
            minWidth = 200
            resizable = true
            cellRenderer = InspectorTableCellRenderer()
        }
        table.autoResizeMode = JTable.AUTO_RESIZE_LAST_COLUMN
        table.putClientProperty("JTable.renderBooleanAsCheckBox", false)
        configureBorderLayout(this) {
            center { scrollPane({ border = bottomBorder() }) { buildView() } }
            south { +dimensionsComponent }
        }
    }

    private fun createClientPropertyTable(): JTable = object : JTable() {
        override fun getPreferredScrollableViewportSize(): Dimension {
            val superSize = super.getPreferredScrollableViewportSize()
            val prefSize = preferredSize
            prefSize.width = min(superSize.width, prefSize.width)
            prefSize.height = min(superSize.height, prefSize.height)
            return prefSize
        }

        override fun isCellEditable(row: Int, column: Int): Boolean = false
    }.apply {
        val colNames = Vector(listOf("Key", "Value"))
        model = DefaultTableModel(colNames, 1)
        clientProperties.bind { props ->
            (model as DefaultTableModel).setDataVector(props, colNames)
        }
        val columnModel: TableColumnModel = table.columnModel
        columnModel.getColumn(0).apply {
            minWidth = 220
            maxWidth = 200
            resizable = true
        }
        columnModel.getColumn(1).apply {
            minWidth = 200
            resizable = true
            cellRenderer = ClientPropertyRenderer()
        }
        showHorizontalLines = false
        autoResizeMode = JTable.AUTO_RESIZE_LAST_COLUMN
        putClientProperty("JTable.renderBooleanAsCheckBox", false)
    }

    private fun Any?.renderAny(): Pair<String?, Icon?> =
        BeanRenderer.renderWithIcon(this, preserveNull = true)

    private fun beanValue(beanName: String) =
        beans.derive { it.properties[beanName].let { v -> v?.value.renderAny() } }

    private fun Row.beanValue(prop: ObservableProperty<Pair<String?, Icon?>>) {
        val initial = prop.get()
        label(initial.first ?: BeanRenderer.NULL_VALUE_STRING).applyToComponent {
            icon = initial.second
            prop.onChange {
                text = it.first ?: BeanRenderer.NULL_VALUE_STRING
                icon = it.second
            }
        }
    }

    private fun RowBuilder.property(
        name: String,
        beanName: String,
        hideIfNull: Boolean = false,
        hideIfBlank: Boolean = false
    ): ObservableProperty<Pair<String?, Icon?>> {
        val value = beanValue(beanName)
        row(name) {
            if (hideIfNull || hideIfBlank) {
                visibleIf(
                    value.derive {
                        val isNull = hideIfNull && it.first == null
                        val isBlank = hideIfBlank && it.first?.isBlank() != false
                        !isNull && !isBlank
                    }
                )
            }
            beanValue(value)
        }
        return value
    }

    private fun RowBuilder.fullEnableIf(prop: ObservableCondition) {
        enableIf(prop)
        subRowsEnabledIf(prop)
    }

    private fun RowBuilder.fullVisibleIf(prop: ObservableCondition) {
        visibleIf(prop)
        subRowsVisibleIf(prop)
    }

    private inline fun <reified T> RowBuilder.layoutRow(
        title: Text,
        prop: ObservableProperty<Any?>,
        noinline init: Row.() -> Unit
    ) {
        titledRow(title) {
            fullVisibleIf(prop.isInstance<T>())
            init()
        }
    }

    private fun buildView(): ModifiablePanel = panel {
        property("Name", "name", hideIfNull = true)
        property("Class Hierarchy", "hierarchy")
        property("UI", "ui")
        property("Text", "text", hideIfBlank = true)
        property("Icon", "icon", hideIfNull = true)
        property("Border", "border")
        property("Foreground", "foreground")
        property("Background", "background")
        property("Font", "font")
        property("Cursor", "cursor", hideIfNull = true)
        property("Display flags", "flags.display")
        property("Focus flags", "flags.focus")
        property("Extra flags", "flags.state")
        hideableRow("Layout Info") {
            property("Location on Screen", "locationOnScreen")
            property("Visible Rect", "visibleRect")
            property("Bounds", "bounds")
            property("Minimum Size", "minimumSize")
            property("Maximum Size", "maximumSize")
            property("Preferred Size", "preferredSize")
            property("Alignment X", "alignmentX")
            property("Alignment Y", "alignmentY")

            buildLayoutView()

            titledRow("MigLayout Component Constraints") {
                fullVisibleIf(comp.derive { it?.parent?.layout is PatchedMigLayout })
                property("constraints", "miglayout.component.constraint")
                property("cc.horizontal", "miglayout.component.constraint")
                property("cc.horizontal", "miglayout.component.constraint")
            }
        }
        hideableRow("Client Properties") {
            fullEnableIf(clientProperties.derive { !it.isNullOrEmpty() })
            row {
                scrollPane(clientPropertyTable).apply {
                    noGrowY()
                }
            }
        }
        buildAccessibilityView()
    }

    private fun RowBuilder.buildLayoutView() {
        val layoutBean = beans.derive { it.properties["layout"]?.value }
        row("Layout") {
            visibleIf(
                !layoutBean.derive {
                    it is MigLayout || it is GridBagLayout || it is CardLayout || it is BorderLayout
                }
            )
            beanValue(layoutBean.derive { it.renderAny() })
        }

        val layoutTitle = +"<html>Layout: " + layoutBean.derive { it?.javaClass?.render() }
        layoutRow<MigLayout>(layoutTitle, layoutBean) {
            property("layoutConstraints", "miglayout.lc")
            property("columnConstraints", "miglayout.columnConstraints")
            property("rowConstraints", "miglayout.rowConstraints")
        }
        layoutRow<GridBagLayout>(layoutTitle, layoutBean) {
            property("defaultConstraints", "gridlayout.defaultConstraints")
            property("columnWidths", "gridlayout.columnWidths")
            property("rowHeights", "gridlayout.rowHeights")
            property("columnWeights", "gridlayout.columnWeights")
            property("rowWeights", "gridlayout.rowWeights")
        }
        layoutRow<BorderLayout>(layoutTitle, layoutBean) {
            property("constraints", "borderlayout.constraints")
        }
        layoutRow<CardLayout>(layoutTitle, layoutBean) {
            property("currentCard", "cardlayout.currentCard")
            property("constraints", "cardlayout.constraints")
        }
    }

    private fun RowBuilder.buildAccessibilityView() {
        val accessibleProp = beans.derive { it.properties["accessible"]?.value as? Boolean ?: false }
        hideableRow(+"Accessible: " + accessibleProp) {
            fullEnableIf(accessibleProp)
            property("Accessible Context", "accessible.hierarchy")
            property("Name", "accessible.accessibleName", hideIfNull = true)
            property("Name", "accessible.accessibleDescription", hideIfNull = true)
            property("Role", "accessible.accessibleRole")
            property("Index in Parent", "accessible.accessibleIndexInParent")
            property("Children", "accessible.accessibleChildrenCount")
            property("Relation", "accessible.accessibleRelationSet", hideIfBlank = true)
            property("State", "accessible.accessibleStateSet")
        }
    }

    fun refresh(newComponent: Component? = null, clickInfo: ClickInfo? = null) {
        if (clickInfo != null) {
            beans.set(ComponentPropertyBeans(clickInfo = clickInfo))
        } else {
            comp.set(newComponent)
        }
        model.reload(newComponent, clickInfo)
        dimensionsComponent.update(newComponent)
    }
}
