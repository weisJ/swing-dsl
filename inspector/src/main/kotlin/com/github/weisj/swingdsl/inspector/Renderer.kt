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

import com.github.weisj.darklaf.icons.DarkSVGIcon
import com.github.weisj.swingdsl.icons.SolidColorIcon
import com.github.weisj.swingdsl.style.UIFactory
import com.github.weisj.swingdsl.util.asCSSProperty
import com.github.weisj.swingdsl.util.toHex
import com.github.weisj.swingdsl.util.toHtml
import net.miginfocom.layout.AC
import net.miginfocom.layout.BoundSize
import net.miginfocom.layout.CC
import net.miginfocom.layout.DimConstraint
import net.miginfocom.layout.LC
import net.miginfocom.layout.LayoutUtil
import java.awt.Color
import java.awt.Cursor
import java.awt.Dimension
import java.awt.Font
import java.awt.GridBagConstraints
import java.awt.Insets
import java.awt.Point
import java.awt.Rectangle
import java.awt.font.TextAttribute
import javax.swing.Icon
import javax.swing.border.Border
import javax.swing.border.CompoundBorder
import javax.swing.border.EmptyBorder
import javax.swing.border.TitledBorder
import javax.swing.plaf.UIResource
import kotlin.math.abs

internal interface Renderer<T> {
    fun render(value: T): String
    fun getIcon(value: T): Icon? = null
}

private fun Float?.roughlyEqual(other: Float?): Boolean {
    if (this == null || other == null) return this === other
    return abs(other - this) < 0.0001f
}

private fun Int.render(pad: Int? = null): String = when (this) {
    Int.MAX_VALUE -> "MAX"
    Int.MIN_VALUE -> "MIN"
    else -> {
        if (pad != null && pad > 0) {
            "%0${pad}d".format(this)
        } else toString()
    }
}

internal fun Point.render(): String =
    "${x.render()}:${y.render()}"

internal class PointRenderer : Renderer<Point> {
    override fun render(value: Point): String = value.render()
}

internal fun Dimension.render(): String =
    "${width.render()}×${height.render()}"

internal class DimensionRenderer : Renderer<Dimension> {
    override fun render(value: Dimension): String = value.render()
}

internal fun Insets.render(): String =
    "top:${top.render()} left:${left.render()} bottom:${bottom.render()} right:${right.render()}"

internal class InsetsRenderer : Renderer<Insets> {
    override fun render(value: Insets): String = value.render()
}

internal fun Rectangle.render(): String =
    "[${x.render()},${y.render()},${width.render()},${height.render()}]"

internal class RectangleRenderer : Renderer<Rectangle> {
    override fun render(value: Rectangle): String = value.render()
}

internal class ColorRenderer : Renderer<Color> {
    override fun render(value: Color): String = buildString {
        append("r:")
        append(value.red.render(pad = 3))
        append(" g:")
        append(value.green.render(pad = 3))
        append(" b:")
        append(value.blue.render(pad = 3))
        append(" argb:#")
        append(value.toHex(includeAlpha = true))
        if (value is UIResource) {
            append(" UIResource")
        }
    }

    override fun getIcon(value: Color): Icon =
        SolidColorIcon(value, size = Dimension(28, 16), insets = Insets(1, 0, 1, 2))
}

internal class FontRenderer : Renderer<Font> {
    override fun render(value: Font): String = buildString {
        append(value.name)
        append(" (").append(value.family).append("), ")
        append(value.size).append("px")
        if (Font.BOLD == Font.BOLD and value.style) append(" bold")
        if (Font.ITALIC == Font.ITALIC and value.style) append(" italic")
        if (value is UIResource) append(" UIResource")

        value.attributes.entries.asSequence()
            .filter {
                it.value != null && it.key !== TextAttribute.FAMILY && it.key !== TextAttribute.SIZE
            }
            .joinTo(this, prefix = "{", postfix = "}") {
                val name: String = getFieldValue(it.key, "name") ?: it.key.toString()
                "$name=${it.value}"
            }
        if (endsWith("{}")) {
            deleteRange(length - 2, length)
        }
    }
}

internal class BooleanRenderer : Renderer<Boolean> {
    override fun render(value: Boolean): String = if (value) "Yes" else "No"
}

internal class IconRenderer : Renderer<Icon> {
    override fun render(value: Icon): String {
        if (value is DarkSVGIcon) {
            val text = value.svgIcon.svgURI.toASCIIString()
            if (text.startsWith("jar:") && text.contains("!")) {
                val fgProp = UIFactory.secondaryTextForeground.asCSSProperty()
                val index: Int = text.lastIndexOf("!")
                val jarFile: String = text.substring(4, index).let {
                    it.substring(it.lastIndexOf('/') + 1)
                }
                val path: String = text.substring(index + 1)
                return "$path <br><span style='$fgProp'>(in $jarFile)".toHtml(escapeExistingHtml = false)
            }
        }
        return value.javaClass.render().toHtml(escapeExistingHtml = false)
    }

    override fun getIcon(value: Icon): Icon = value
}

internal class BorderRenderer : Renderer<Border> {
    override fun render(value: Border): String = getTextDescription(value).toString()

    private fun getTextDescription(value: Border?, builder: StringBuilder = StringBuilder()): StringBuilder {
        if (value == null) {
            builder.append("null")
            return builder
        }
        return builder.apply {
            append(value.containingClassName)
            if (value is TitledBorder) {
                append(" title='").append(value.title).append("'")
                if (value is CompoundBorder) {
                    append(" inside={")
                    getTextDescription(value.insideBorder, builder = this)
                    append(" outside={")
                    getTextDescription(value.outsideBorder, builder = this)
                    append("}")
                }
                if (value is EmptyBorder) {
                    val insets = value.borderInsets
                    append(" insets={top=").append(insets.top)
                    append(" left=").append(insets.left)
                    append(" bottom=").append(insets.bottom)
                    append(" right=").append(insets.right)
                    append("}")
                }
                if (value is UIResource) append(" UIResource")
            }
        }
    }
}

internal class ClassHierarchyRenderer : Renderer<ComponentClassHierarchy> {

    companion object {
        private const val TL_ANGLE = "\u2570"
    }

    override fun render(value: ComponentClassHierarchy): String = buildString {
        var level = 0
        value.hierarchy.forEach {
            if (level != 0) {
                append("<br>").append("&nbsp;&nbsp;&nbsp;".repeat(level)).append(TL_ANGLE).append(" ")
            }
            append(it.render())
            level++
        }
    }.toHtml(escapeExistingHtml = false)
}

internal class GridBagConstraintRenderer : Renderer<GridBagConstraints> {

    override fun render(value: GridBagConstraints): String = buildString {
        appendFieldValue(value, "gridx")
        appendFieldValue(value, "gridy")
        appendFieldValue(value, "gridwidth")
        appendFieldValue(value, "gridheight")
        appendFieldValue(value, "weightx")
        appendFieldValue(value, "weighty")
        appendFieldValue(value, "anchor")
        appendFieldValue(value, "fill")
        appendFieldValue(value, "insets")
        appendFieldValue(value, "ipadx")
        appendFieldValue(value, "ipady")
    }

    private fun Appendable.appendFieldValue(constraints: GridBagConstraints, field: String) {
        val value: Any? = getFieldValue(constraints, field)
        val defaultValue: Any? = getFieldValue(GridBagConstraints(), field)
        if (value is Float && defaultValue is Float) {
            if (!value.roughlyEqual(defaultValue)) append("$field=$value")
        } else {
            if (value != defaultValue) append("$field=$value")
        }
    }
}

private fun IntArray.render(): String = contentToString()
private fun DoubleArray.render(): String = contentToString()

internal class IntArrayRenderer : Renderer<IntArray> {
    override fun render(value: IntArray): String = value.render()
}

internal class DoubleArrayRenderer : Renderer<DoubleArray> {
    override fun render(value: DoubleArray): String = value.render()
}

internal class ClassRenderer : Renderer<Class<*>> {
    override fun render(value: Class<*>): String = value.render().toHtml(escapeExistingHtml = false)
}

internal fun <T> Class<T>.render(): String {
    val target = if (isAnonymousClass) enclosingClass else this
    val fgProp = UIFactory.secondaryTextForeground.asCSSProperty()
    val displayStr = "${target.simpleName} <span style=\"$fgProp\">(${target.packageName})</span>"
    return if (isAnonymousClass) {
        "${superclass.simpleName} in $displayStr"
    } else displayStr
}

internal class ObjectClassRenderer : Renderer<Any> {
    override fun render(value: Any): String = value.javaClass.render().toHtml(escapeExistingHtml = false)
}

internal abstract class TableBasedRenderer<T> : Renderer<T> {

    protected fun StringBuilder.addValue(
        name: String,
        value: Any?,
        skipNull: Boolean = true,
    ) {
        if (value == null && skipNull) return
        append("<tr>")
        append("<td>").append(name).append("</td>")
        append("<td>&nbsp;=&nbsp;</td>")
        append("<td>").append(value).append("</td>")
        append("</tr>")
    }

    protected abstract fun StringBuilder.buildTable(value: T)

    abstract val defaultValueString: String

    override fun render(value: T): String {
        val content = StringBuilder().also { it.buildTable(value) }
        return if (content.isBlank()) {
            defaultValueString
        } else {
            buildString {
                append("<table border='0' cellpadding='0' cellspacing='0' style='border:none;border-collapse:collapse'>")
                append(content)
                append("</table>")
            }.toHtml(escapeExistingHtml = false)
        }
    }
}

internal class LCRenderer : TableBasedRenderer<LC>() {

    override val defaultValueString: String
        get() = "default LC"

    override fun StringBuilder.buildTable(value: LC) {
        addValue("isFlowX", value.isFlowX)
        addValue("leftToRight", value.leftToRight)
        addValue("noGrid", value.isNoGrid)
        addValue("hideMode", value.hideMode)
        addValue("visualPadding", value.isVisualPadding)
        addValue("topToBottom", value.isTopToBottom)
        addValue("noCache", value.isNoCache)
        addValue("insets", value.insets?.contentToString())
        val alignX = value.alignX
        val alignY = value.alignY
        if (alignX != null || alignY != null)
            addValue("align", "x: $alignX; y: $alignY")
        val width = value.width
        val height = value.height
        if (width !== BoundSize.NULL_SIZE || height !== BoundSize.NULL_SIZE)
            addValue("size", "width: $width; height: $height")
        val gridX = value.gridGapX
        val gridY = value.gridGapY
        if (gridX != null || gridY != null) {
            addValue("gridGap", "x: $gridX; y: $gridY")
        }
        val fillX = value.isFillX
        val fillY = value.isFillY
        if (fillX || fillY)
            addValue("fill", "x: $fillX; y: $fillY")
        val packWidth = value.packWidth
        val packHeight = value.packHeight
        if (packWidth !== BoundSize.NULL_SIZE || packHeight !== BoundSize.NULL_SIZE) {
            addValue(
                "pack",
                "width:$packWidth<br>height:$packHeight<br>widthAlign:${value.packWidthAlign}<br>heightAlight:${value.packHeightAlign}"
            )
        }
    }
}

internal class ACRenderer(private val dimRenderer: Renderer<DimConstraint>) : Renderer<AC> {

    override fun render(value: AC): String = buildString {
        val constraints = value.constaints
        for (i in constraints.indices) {
            append("[$i]")
            append(dimRenderer.render(constraints[i]))
            if (i < constraints.size - 1) {
                append('\n')
            }
        }
    }
}

internal class CCRenderer : TableBasedRenderer<CC>() {
    companion object {
        private val emptyCC = CC()
    }

    override val defaultValueString: String
        get() = "default CC"

    override fun StringBuilder.buildTable(value: CC) {
        if (value.skip != emptyCC.skip)
            addValue("skip", value.skip)
        if (value.spanX != emptyCC.spanX)
            addValue("spanX", if (value.spanX == LayoutUtil.INF) "INF" else value.spanX)
        if (value.spanY != emptyCC.spanY)
            addValue("spanX", if (value.spanY == LayoutUtil.INF) "INF" else value.spanY)
        addValue("pushX", value.pushX)
        addValue("pushY", value.pushY)
        if (value.split != emptyCC.split)
            addValue("split", value.split)
        if (value.isWrap) {
            addValue("wrap", value.wrapGapSize ?: true)
        }
        if (value.isNewline) {
            addValue("newline", value.newlineGapSize ?: true)
        }
    }
}

internal class DimConstraintsRenderer : TableBasedRenderer<DimConstraint>() {
    companion object {
        private val emptyConstraint = DimConstraint()
    }

    override val defaultValueString: String
        get() = "default DimConstraint"

    override fun StringBuilder.buildTable(value: DimConstraint) {
        if (!value.grow.roughlyEqual(emptyConstraint.grow))
            addValue("grow", value.grow)
        if (value.growPriority != emptyConstraint.growPriority)
            addValue("growPrio", value.growPriority)
        if (!value.shrink.roughlyEqual(emptyConstraint.shrink))
            addValue("shrink", value.shrink)
        if (value.shrinkPriority != emptyConstraint.shrinkPriority)
            addValue("shrinkPriority", value.shrinkPriority)
        if (value.isFill != emptyConstraint.isFill)
            addValue("fill", value.isFill)
        if (value.isNoGrid != emptyConstraint.isNoGrid)
            addValue("noGrid", value.isNoGrid)
        if (value.sizeGroup != emptyConstraint.sizeGroup)
            addValue("sizeGroup", value.sizeGroup)
        if (value.endGroup != emptyConstraint.endGroup)
            addValue("endGroup", value.endGroup)
        addValue("size", value.size)
        addValue("align", value.align)
        addValue("gapBefore", value.gapBefore)
        addValue("gapAfter", value.gapAfter)
    }
}

internal class FlagsRenderer : Renderer<Flags> {
    override fun render(value: Flags): String = value.flags.asSequence()
        .filter { it.second }
        .map { it.first }
        .joinToString()
}

internal class CursorRenderer : Renderer<Cursor> {
    override fun render(value: Cursor): String =
        "${value.name} ${value.javaClass.render()}".toHtml(escapeExistingHtml = false)
}
