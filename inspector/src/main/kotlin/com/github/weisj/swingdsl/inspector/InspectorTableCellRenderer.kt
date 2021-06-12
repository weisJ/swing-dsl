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

import com.github.weisj.swingdsl.style.UIFactory
import com.github.weisj.swingdsl.util.SharedLazyComponents
import net.miginfocom.layout.AC
import net.miginfocom.layout.CC
import net.miginfocom.layout.DimConstraint
import net.miginfocom.layout.LC
import java.awt.Color
import java.awt.Component
import java.awt.Cursor
import java.awt.Dimension
import java.awt.Font
import java.awt.GridBagConstraints
import java.awt.Insets
import java.awt.LayoutManager
import java.awt.Point
import java.awt.Rectangle
import javax.swing.Icon
import javax.swing.JLabel
import javax.swing.JTable
import javax.swing.border.Border
import javax.swing.plaf.ComponentUI
import javax.swing.table.DefaultTableCellRenderer

internal class PropertyNameRenderer : DefaultTableCellRenderer() {
    override fun getTableCellRendererComponent(
        table: JTable,
        value: Any,
        isSelected: Boolean,
        hasFocus: Boolean,
        row: Int,
        column: Int
    ): Component {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column)
        val model = table.model
        var changed = false
        if (model is InspectorTableModel) {
            changed = model.properties[row]?.changed ?: false
        }
        if ((!isSelected || !hasFocus) && changed) {
            foreground = UIFactory.hyperlinkColor
        }
        font = SharedLazyComponents.label.font
        if (changed) {
            font = font.deriveFont(Font.BOLD)
        }
        return this
    }
}

internal class ClientPropertyRenderer : DefaultTableCellRenderer() {
    override fun getTableCellRendererComponent(
        table: JTable,
        value: Any,
        isSelected: Boolean,
        hasFocus: Boolean,
        row: Int,
        column: Int
    ): Component {
        val (realValue, icon) = BeanRenderer.renderWithIcon(value)
        super.getTableCellRendererComponent(table, realValue, isSelected, hasFocus, row, column)
        this.icon = icon
        return this
    }
}

internal object BeanRenderer {
    private infix fun <T> Class<T>.renderedBy(renderer: Renderer<in T>) = this to renderer

    private val RENDERERS = kotlin.run {
        val dimRenderer = DimConstraintsRenderer()
        val objectClassRenderer = ObjectClassRenderer()
        mapOf(
            Point::class.java renderedBy PointRenderer(),
            Dimension::class.java renderedBy DimensionRenderer(),
            Insets::class.java renderedBy InsetsRenderer(),
            Rectangle::class.java renderedBy RectangleRenderer(),
            Color::class.java renderedBy ColorRenderer(),
            Font::class.java renderedBy FontRenderer(),
            Boolean::class.java renderedBy BooleanRenderer(),
            Icon::class.java renderedBy IconRenderer(),
            Border::class.java renderedBy BorderRenderer(),
            ComponentClassHierarchy::class.java renderedBy ClassHierarchyRenderer(),
            Class::class.java renderedBy ClassRenderer(),
            Flags::class.java renderedBy FlagsRenderer(),
            LayoutManager::class.java renderedBy objectClassRenderer,
            ComponentUI::class.java renderedBy objectClassRenderer,
            GridBagConstraints::class.java renderedBy GridBagConstraintRenderer(),
            LC::class.java renderedBy LCRenderer(),
            CC::class.java renderedBy CCRenderer(),
            AC::class.java renderedBy ACRenderer(dimRenderer),
            DimConstraint::class.java renderedBy dimRenderer,
            IntArray::class.java renderedBy IntArrayRenderer(),
            DoubleArray::class.java renderedBy DoubleArrayRenderer(),
            Cursor::class.java renderedBy CursorRenderer(),
        )
    }
    private val DEFAULT_RENDERER: Renderer<Any> = object : Renderer<Any> {
        override fun render(value: Any): String = value.toString()
    }
    const val NULL_VALUE_STRING = "null"

    fun render(value: Any?, preserveNull: Boolean = false): String? = when (value) {
        null -> nullString(preserveNull)
        else -> (getRenderer(value.javaClass) ?: DEFAULT_RENDERER).render(value)
    }

    private fun nullString(preserveNull: Boolean) = if (preserveNull) null else NULL_VALUE_STRING

    fun renderWithIcon(value: Any?, preserveNull: Boolean = false): Pair<String?, Icon?> =
        when (value) {
            null -> nullString(preserveNull) to null
            else -> (getRenderer(value.javaClass) ?: DEFAULT_RENDERER).let {
                it.render(value) to it.getIcon(value)
            }
        }

    @Suppress("UNCHECKED_CAST")
    private fun getRenderer(clazz: Class<*>): Renderer<Any>? {
        var renderer = RENDERERS[clazz]
        if (renderer != null) return renderer as Renderer<Any>
        val interfaces = clazz.interfaces
        for (aClass in interfaces) {
            renderer = getRenderer(aClass)
            if (renderer != null) {
                return renderer
            }
        }
        return clazz.superclass?.let { getRenderer(it) }
    }
}

internal class InspectorTableCellRenderer : DefaultTableCellRenderer() {

    override fun getTableCellRendererComponent(
        table: JTable,
        value: Any?,
        isSelected: Boolean,
        hasFocus: Boolean,
        row: Int,
        column: Int
    ): JLabel {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column)
        val (text, icon) = BeanRenderer.renderWithIcon(value)
        this.text = text
        this.icon = icon
        return this
    }
}
