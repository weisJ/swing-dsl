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
package com.github.weisj.swingdsl.highlight

import com.github.weisj.swingdsl.laf.WrappedComponent
import com.github.weisj.swingdsl.subtract
import com.github.weisj.swingdsl.util.getVisualPaddingsForComponent
import java.awt.Component
import java.awt.Point
import java.awt.Rectangle
import javax.swing.JComponent
import javax.swing.JList
import javax.swing.JTable
import javax.swing.JTree
import javax.swing.SwingUtilities

interface LayoutTag {
    fun getBoundsIn(comp: Component): Rectangle
}

interface AnchoredLayoutTag<T : Component> : LayoutTag {
    val anchor: T
}

internal sealed class ComponentLayoutTag<T : Component>(override val anchor: T) : AnchoredLayoutTag<T> {

    abstract fun getBounds(): Rectangle

    override fun getBoundsIn(comp: Component): Rectangle {
        val anchorBounds = getBounds()
        if (comp === anchor) return anchorBounds
        return SwingUtilities.convertRectangle(anchor, anchorBounds, comp)
    }

    override fun toString(): String {
        return "LayoutTag[$anchor]"
    }
}

internal class ComponentBoundsLayoutTag<T : Component>(anchor: T) : ComponentLayoutTag<T>(anchor) {
    override fun getBounds(): Rectangle {
        val paddings = if (anchor is JComponent) getVisualPaddingsForComponent(anchor) else null
        val bounds = Rectangle(0, 0, anchor.width, anchor.height)
        return if (paddings != null) bounds.subtract(paddings, inPlace = true) else bounds
    }

    override fun hashCode(): Int {
        return anchor.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is ComponentBoundsLayoutTag<*>) return false
        return (this::class == other::class) && anchor == other.anchor
    }
}

internal class ComponentRegionBoundsLayoutTag<T : Component>(
    anchor: T,
    private val regionSupplier: (T) -> Rectangle
) : ComponentLayoutTag<T>(anchor) {
    override fun getBounds(): Rectangle = regionSupplier(anchor)
}

internal class ComponentFixedRegionBoundsLayoutTag(
    anchor: Component,
    private val anchorBounds: Rectangle
) : ComponentLayoutTag<Component>(anchor) {
    override fun getBounds(): Rectangle = anchorBounds

    override fun hashCode(): Int {
        return anchor.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is ComponentFixedRegionBoundsLayoutTag) return false
        return (this::class == other::class) && anchor == other.anchor && anchorBounds == other.anchorBounds
    }

    override fun toString(): String {
        return "LayoutTag[$anchorBounds, $anchor]"
    }
}

private object EmptyLayoutTag : LayoutTag {
    override fun getBoundsIn(comp: Component): Rectangle {
        return Rectangle(0, 0, 0, 0)
    }
}

fun emptyLayoutTag(): LayoutTag = EmptyLayoutTag

fun <T : Component> T.createLayoutTag(bounds: Rectangle): LayoutTag =
    ComponentFixedRegionBoundsLayoutTag(this, bounds)

fun <T : Component> T.createLayoutTag(regionSupplier: (T) -> Rectangle): LayoutTag =
    ComponentRegionBoundsLayoutTag(this, regionSupplier)

fun <T : Component> T.createLayoutTag(): LayoutTag =
    ComponentBoundsLayoutTag(this)

fun <T : Component> T.createLayoutTag(p: Point): LayoutTag = when (this) {
    is JTree -> {
        val path = getPathForLocation(p.x, p.y)
        if (path != null) {
            createLayoutTag { getPathBounds(path) ?: Rectangle() }
        } else createLayoutTag()
    }
    is JTable -> {
        val row = rowAtPoint(p)
        val column = columnAtPoint(p)
        if (row != -1 && column != -1) {
            createLayoutTag { getCellRect(row, column, true) }
        } else createLayoutTag()
    }
    is JList<*> -> {
        val row = ui.locationToIndex(this, p)
        if (row != -1) {
            createLayoutTag {
                getCellBounds(row, row)
            }
        } else createLayoutTag()
    }
    else -> ComponentBoundsLayoutTag(this)
}

fun <T : JComponent> WrappedComponent<T>.createContainerLayoutTag(): LayoutTag =
    container.createLayoutTag()

fun <T : JComponent> WrappedComponent<T>.createComponentLayoutTag(): LayoutTag =
    component.createLayoutTag()

fun <T : JComponent> WrappedComponent<T>.createLayoutTag(): LayoutTag = createContainerLayoutTag()
