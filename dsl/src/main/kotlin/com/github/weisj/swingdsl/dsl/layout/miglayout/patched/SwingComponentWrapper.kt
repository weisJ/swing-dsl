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

/*
 * License (BSD):
 * ==============
 *
 * Copyright (c) 2004, Mikael Grev, MiG InfoCom AB. (miglayout (at) miginfocom (dot) com)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or other
 * materials provided with the distribution.
 * Neither the name of the MiG InfoCom AB nor the names of its contributors may be
 * used to endorse or promote products derived from this software without specific
 * prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE.
 *
 * @version 1.0
 * @author Mikael Grev, MiG InfoCom AB
 *         Date: 2006-sep-08
 */
// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.github.weisj.swingdsl.dsl.layout.miglayout.patched

import com.github.weisj.swingdsl.dsl.getProperty
import com.github.weisj.swingdsl.dsl.util.getVisualPaddingsForComponent
import com.github.weisj.swingdsl.util.isEmpty
import net.miginfocom.layout.ComponentWrapper
import net.miginfocom.layout.ContainerWrapper
import net.miginfocom.layout.LayoutUtil
import net.miginfocom.layout.PlatformDefaults
import java.awt.*
import java.awt.geom.Point2D
import java.util.*
import javax.swing.JComponent
import javax.swing.JEditorPane
import javax.swing.JLabel
import javax.swing.JTextArea
import javax.swing.SwingUtilities
import javax.swing.border.LineBorder
import javax.swing.border.TitledBorder
import javax.swing.text.View

/** Debug color for component bounds outline.
 */
private val DB_COMP_OUTLINE = Color(0, 0, 200)
private val DB_COMP_BASELINE = Color(250, 100, 50)
private val DB_COMP_VISUAL_PADDING = Color(0, 200, 0)

internal open class SwingComponentWrapper(private val c: JComponent) : ComponentWrapper {
    private var hasBaseLine = ThreeState.UNSURE
    private var isPrefCalled = false

    private var visualPaddings: IntArray? = null

    companion object {
        private const val SAFE_BASELINE_SIZE = 8192
        private val FONT_METRICS_CACHE = IdentityHashMap<FontMetrics, Point2D.Float>(4)
        private val SUBST_FONT = Font("sansserif", Font.PLAIN, 11)
        private val isJava9orLater = try {
            // Java 9 version-String Scheme: http://openjdk.java.net/jeps/223
            val st = StringTokenizer(System.getProperty("java.version"), "._-+")
            val majorVersion = st.nextToken().toInt()
            majorVersion >= 9
        } catch (e: Exception) {
            // Java 8 or older
            false
        }
    }

    override fun getBaseline(width: Int, height: Int): Int {
        if (height == 0 && !isVisible) {
            // Prevent hidden component from having any size.
            return 0
        }
        var h = height
        val visualPaddings = visualPadding
        if (h < 0) {
            h = c.height
        } else if (visualPaddings != null) {
            h = height + visualPaddings[0] + visualPaddings[2]
        }
        val w = when {
            // The default implementation of getBaseline for html content in BasicHtml will report an incorrect
            // baseline sometimes. By using the component width we ensure the baseline actually resembles what is displayed.
            c is JLabel && c.getProperty<View>("html") != null -> if (c.width > 0) c.width else SAFE_BASELINE_SIZE
            width < 0 -> c.width
            else -> width
        }
        var baseLine = c.getBaseline(w, h)
        if (baseLine != -1 && visualPaddings != null) {
            baseLine -= visualPaddings[0]
        }
        return baseLine
    }

    override fun getComponent() = c

    override fun getPixelUnitFactor(isHor: Boolean): Float {
        // The usage of these metrics is discouraged but necessary when no GAP_PROVIDER is set.
        return when (PlatformDefaults.getLogicalPixelBase()) {
            PlatformDefaults.BASE_FONT_SIZE -> {
                val font = c.font
                val fm = c.getFontMetrics(font ?: SUBST_FONT)
                var p = FONT_METRICS_CACHE[fm]
                if (p == null) {
                    val r = fm.getStringBounds("X", c.graphics)
                    p = Point2D.Float(r.width.toFloat() / 6f, r.height.toFloat() / 13.27734375f)
                    FONT_METRICS_CACHE[fm] = p
                }
                if (isHor) p.x else p.y
            }
            PlatformDefaults.BASE_SCALE_FACTOR -> {
                val s = if (isHor) {
                    PlatformDefaults.getHorizontalScaleFactor()
                } else {
                    PlatformDefaults.getVerticalScaleFactor()
                }
                val scaleFactor = s ?: 1f

                // Swing in Java 9 scales automatically using the system scale factor(s) that the
                // user can change in the system settings (Windows: Control Panel; Mac: System Preferences).
                // Each connected screen may use its own scale factor
                // (e.g. 1.5 for primary 4K 40inch screen and 1.0 for secondary HD screen).
                val screenScale = when {
                    isJava9orLater -> 1f // use system scale factor(s)
                    isHor -> horizontalScreenDPI.toFloat() / PlatformDefaults.getDefaultDPI().toFloat()
                    else -> verticalScreenDPI.toFloat() / PlatformDefaults.getDefaultDPI().toFloat()
                }
                scaleFactor * screenScale
            }
            else -> 1f
        }
    }

    override fun getX() = c.x

    override fun getY() = c.y

    override fun getHeight() = c.height

    override fun getWidth() = c.width

    override fun getScreenLocationX(): Int {
        val p = Point()
        SwingUtilities.convertPointToScreen(p, c)
        return p.x
    }

    override fun getScreenLocationY(): Int {
        val p = Point()
        SwingUtilities.convertPointToScreen(p, c)
        return p.y
    }

    override fun getMinimumHeight(sz: Int): Int {
        if (!isPrefCalled) {
            c.preferredSize // To defeat a bug where the minimum size is different before and after the first call to getPreferredSize();
            isPrefCalled = true
        }
        return c.minimumSize.height
    }

    override fun getMinimumWidth(sz: Int): Int {
        if (!isPrefCalled) {
            c.preferredSize // To defeat a bug where the minimum size is different before and after the first call to getPreferredSize();
            isPrefCalled = true
        }
        return c.minimumSize.width
    }

    override fun getPreferredHeight(sz: Int): Int {
        // If the component has not gotten size yet and there is a size hint, trick Swing to return a better height.
        if (c.width == 0 && c.height == 0 && sz != -1) {
            c.setBounds(c.x, c.y, sz, 1)
        }
        return c.preferredSize.height
    }

    override fun getPreferredWidth(sz: Int): Int {
        // If the component has not gotten size yet and there is a size hint, trick Swing to return a better height.
        if (c.width == 0 && c.height == 0 && sz != -1) {
            c.setBounds(c.x, c.y, 1, sz)
        }
        return c.preferredSize.width
    }

    override fun getMaximumHeight(sz: Int) = if (c.isMaximumSizeSet) c.maximumSize.height else Integer.MAX_VALUE

    override fun getMaximumWidth(sz: Int) = if (c.isMaximumSizeSet) c.maximumSize.width else Integer.MAX_VALUE

    override fun getParent(): ContainerWrapper? {
        val p = c.parent as? JComponent ?: return null
        return SwingContainerWrapper(p)
    }

    override fun getHorizontalScreenDPI(): Int {
        return try {
            c.toolkit.screenResolution
        } catch (e: HeadlessException) {
            PlatformDefaults.getDefaultDPI()
        }
    }

    override fun getVerticalScreenDPI(): Int {
        return try {
            c.toolkit.screenResolution
        } catch (e: HeadlessException) {
            PlatformDefaults.getDefaultDPI()
        }
    }

    override fun getScreenWidth(): Int {
        return try {
            c.toolkit.screenSize.width
        } catch (ignore: HeadlessException) {
            1024
        }
    }

    override fun getScreenHeight(): Int {
        return try {
            c.toolkit.screenSize.height
        } catch (ignore: HeadlessException) {
            768
        }
    }

    override fun hasBaseline(): Boolean {
        if (hasBaseLine == ThreeState.UNSURE) {
            hasBaseLine = try {
                // do not use component dimensions since it made some components layout themselves to the minimum size
                // and that stuck after that. E.g. JLabel with HTML content and white spaces would be very tall.
                // Use large number but don't risk overflow or exposing size bugs with Integer.MAX_VALUE
                ThreeState.fromBoolean(getBaseline(SAFE_BASELINE_SIZE, SAFE_BASELINE_SIZE) > -1)
            } catch (ignore: Throwable) {
                ThreeState.NO
            }
        }
        return hasBaseLine.toBoolean()
    }

    override fun getLinkId(): String? = c.name

    override fun setBounds(x: Int, y: Int, width: Int, height: Int) {
        c.setBounds(x, y, width, height)
    }

    override fun isVisible() = c.isVisible

    override fun getVisualPadding(): IntArray? {
        visualPaddings?.let {
            return it
        }

        val component = c
        val border = component.border ?: return null
        if (border is LineBorder || border is TitledBorder) {
            return null
        }

        val paddings = getVisualPaddingsForComponent(c) ?: return null

        if (paddings.isEmpty()) {
            return null
        }

        visualPaddings = intArrayOf(paddings.top, paddings.left, paddings.bottom, paddings.right)
        return visualPaddings
    }

    override fun paintDebugOutline(showVisualPadding: Boolean) {
        if (!c.isShowing) {
            return
        }

        val g = c.graphics as? Graphics2D ?: return

        g.stroke = BasicStroke(1f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10f, floatArrayOf(2f, 4f), 0f)

        if (hasBaseline()) {
            g.color = DB_COMP_BASELINE
            val baseline = getBaseline(width, height)
            g.drawLine(0, baseline, width, baseline)
        }

        g.color = DB_COMP_OUTLINE
        g.drawRect(0, 0, width - 1, height - 1)

        if (showVisualPadding) {
            val padding = visualPadding
            if (padding != null) {
                g.color = DB_COMP_VISUAL_PADDING
                g.drawRect(
                    padding[1],
                    padding[0],
                    width - 1 - (padding[1] + padding[3]),
                    height - 1 - (padding[0] + padding[2])
                )
            }
        }
    }

    override fun getComponentType(disregardScrollPane: Boolean): Int {
        throw RuntimeException("Should be not called and used")
    }

    override fun getLayoutHashCode(): Int {
        var d = c.maximumSize
        var hash = d.width + (d.height shl 5)

        d = c.preferredSize
        hash += (d.width shl 10) + (d.height shl 15)

        d = c.minimumSize
        hash += (d.width shl 20) + (d.height shl 25)

        if (c.isVisible)
            hash += 1324511

        linkId?.let {
            hash += it.hashCode()
        }

        return hash
    }

    override fun hashCode() = component.hashCode()

    override fun equals(other: Any?) = other is ComponentWrapper && c == other.component

    override fun getContentBias(): Int {
        return when {
            c is JTextArea || c is JEditorPane || java.lang.Boolean.TRUE == c.getClientProperty("migLayout.dynamicAspectRatio") -> LayoutUtil.HORIZONTAL
            else -> -1
        }
    }
}

internal enum class ThreeState {
    UNSURE,
    YES,
    NO;

    fun toBoolean(): Boolean = when (this) {
        NO -> false
        YES -> true
        else -> throw IllegalStateException("Must be or YES, or NO")
    }

    companion object {
        fun fromBoolean(value: Boolean): ThreeState = if (value) YES else NO
    }
}
