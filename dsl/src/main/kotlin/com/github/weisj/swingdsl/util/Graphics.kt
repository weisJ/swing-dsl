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
package com.github.weisj.swingdsl.util

import java.awt.BasicStroke
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Rectangle
import java.awt.RenderingHints
import java.awt.Shape
import java.awt.geom.Path2D
import java.awt.geom.Rectangle2D
import java.awt.geom.RoundRectangle2D
import kotlin.math.absoluteValue

fun Graphics.drawRect(rect: Rectangle, lineWidth: Int = 1) {
    drawRect(rect.x, rect.y, rect.width, rect.height, lineWidth)
}

fun Graphics.fillRect(rect: Rectangle) {
    fillRect(rect.x, rect.y, rect.width, rect.height)
}

fun Graphics.drawRect(x: Int, y: Int, w: Int, h: Int, lineWidth: Int = 1) {
    translate(x, y)
    fillRect(0, 0, w, lineWidth)
    fillRect(0, lineWidth, lineWidth, h - 2 * lineWidth)
    fillRect(w - lineWidth, lineWidth, lineWidth, h - 2 * lineWidth)
    fillRect(0, h - lineWidth, w, lineWidth)
    translate(-x, -y)
}

private const val EPSILON = 0.0001

val Graphics2D.strokeWidth: Float
    get() {
        val stroke = this.stroke
        return if (stroke is BasicStroke) stroke.lineWidth else 1f
    }

private fun useQuartz(): Boolean {
    return "true" == System.getProperty("apple.awt.graphics.UseQuartz")
}

fun Graphics2D.setupStrokePainting() {
    setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    setRenderingHint(
        RenderingHints.KEY_STROKE_CONTROL,
        if (useQuartz()) RenderingHints.VALUE_STROKE_PURE else RenderingHints.VALUE_STROKE_NORMALIZE
    )
}

fun Graphics2D.drawRoundedRectangle(
    bounds: Rectangle,
    arc: Float,
    lineWidth: Float = strokeWidth,
    inside: Boolean = true
) {
    drawRoundedRectangle(bounds.bounds2D, arc, lineWidth, inside)
}

fun Graphics2D.drawRoundedRectangle(
    bounds: Rectangle2D,
    arc: Float,
    lineWidth: Float = strokeWidth,
    inside: Boolean = true
) {
    drawRoundedRectangle(
        bounds.x.toFloat(), bounds.y.toFloat(), bounds.width.toFloat(), bounds.height.toFloat(),
        arc, lineWidth, inside
    )
}

fun Graphics2D.drawRoundedRectangle(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    arc: Float,
    lineWidth: Float = strokeWidth,
    inside: Boolean = true
) {
    setupStrokePainting()
    fill(createRoundedLinePath(x, y, width, height, arc, lineWidth, inside))
}

fun createRoundedLinePath(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    arc: Float,
    lw: Float,
    inside: Boolean = true
): Path2D {
    val outerRect: Shape
    val innerRect: Shape

    if (arc.absoluteValue < EPSILON) {
        outerRect = Rectangle2D.Float(x, y, width, height)
        innerRect = Rectangle2D.Float(x + lw, y + lw, width - 2 * lw, height - 2 * lw)
    } else {
        if (inside) {
            outerRect = RoundRectangle2D.Float(x, y, width, height, arc, arc)
            innerRect = RoundRectangle2D.Float(x + lw, y + lw, width - 2 * lw, height - 2 * lw, arc - lw, arc - lw)
        } else {
            outerRect = RoundRectangle2D.Float(x - lw, y - lw, width + 2 * lw, height + 2 * lw, arc + lw, arc + lw)
            innerRect = RoundRectangle2D.Float(x, y, width, height, arc, arc)
        }
    }
    val path: Path2D = Path2D.Float(Path2D.WIND_EVEN_ODD)
    path.append(outerRect, false)
    path.append(innerRect, false)
    return path
}

fun Graphics2D.drawDottedRectangle(rect: Rectangle) {
    val dash = floatArrayOf(1f, 2f)
    val oldStroke = stroke
    stroke = BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f)
    draw(rect)
    stroke = oldStroke
}

fun Graphics2D.drawCenteredString(
    rect: Rectangle,
    str: String,
    centerHorizontally: Boolean = true,
    centerVertically: Boolean = true
) {
    val fm = getFontMetrics(font)
    val textWidth = fm.stringWidth(str) - 1
    val x = if (centerHorizontally) rect.x.coerceAtLeast(rect.x + (rect.width - textWidth) / 2) else rect.x
    val y = if (centerVertically) rect.y.coerceAtLeast(rect.y + rect.height / 2 + fm.ascent * 2 / 5) else rect.y
    val oldClip = clip
    clip(rect)
    drawString(str, x, y)
    clip = oldClip
}
