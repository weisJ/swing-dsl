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

import com.github.weisj.swingdsl.getProperty
import com.github.weisj.swingdsl.laf.WrappedComponent
import com.github.weisj.swingdsl.layout.CCFlags
import com.github.weisj.swingdsl.layout.ComponentPurpose
import com.github.weisj.swingdsl.layout.Constraint
import com.github.weisj.swingdsl.layout.GrowPolicy
import com.github.weisj.swingdsl.layout.SpacingConfiguration
import com.github.weisj.swingdsl.layout.isPurpose
import net.miginfocom.layout.BoundSize
import net.miginfocom.layout.CC
import net.miginfocom.layout.ConstraintParser
import java.awt.Component
import javax.swing.JComponent
import javax.swing.JScrollPane
import javax.swing.JTextArea
import javax.swing.JTextField
import javax.swing.text.JTextComponent

internal fun overrideFlags(cc: CC, flags: Array<out Constraint>) {
    for (flag in flags) {
        val w = flag.weight
        when (flag.flag) {
            CCFlags.grow -> if (w != null) cc.grow(w, w) else cc.grow()
            CCFlags.growX -> cc.growX(w ?: 1000f)
            CCFlags.growY -> cc.growY(w ?: 1000f)

            // If you have more than one component in a cell the alignment keywords will not work since the behavior would be Nondeterministic.
            // You can however accomplish the same thing by setting a gap before and/or after the components.
            // That gap may have a minimum size of 0 and a preferred size of a really large value to create a "pushing" gap.
            // There is even a keyword for this: "push". So "gapleft push" will be the same as "align right" and work for multi-component cells as well.
            // CCFlags.right -> horizontal.gapBefore = BoundSize(null, null, null, true, null)

            CCFlags.push -> if (w != null) cc.push(w, w) else cc.push()
            CCFlags.pushX -> if (w != null) cc.pushX(w) else cc.pushX()
            CCFlags.pushY -> if (w != null) cc.pushY(w) else cc.pushY()
        }
    }
}

internal class DefaultComponentConstraintCreator(spacing: SpacingConfiguration) {
    private val shortTextSizeSpec =
        ConstraintParser.parseBoundSize("${spacing.shortTextWidth}px!", false, true)
    private val mediumTextSizeSpec =
        ConstraintParser.parseBoundSize("${spacing.shortTextWidth}px::${spacing.maxShortTextWidth}px", false, true)

    val vertical1pxGap: BoundSize = ConstraintParser.parseBoundSize("${1}px!", true, false)

    fun addGrowIfNeeded(cc: CC, component: WrappedComponent<*>) {
        if (!addGrowIfNeededRaw(cc, component.component)) {
            when {
                component.container.isPurpose(ComponentPurpose.ScrollPane) -> {
                    cc.grow().pushY()
                    val view = component.container.getView()
                    if (view is JTextArea && view.rows == 0) {
                        // set min size to 2 lines (yes, for some reasons it means that rows should be set to 3)
                        view.rows = 3
                    }
                }
            }
        }
    }

    private fun addGrowIfNeededRaw(cc: CC, component: JComponent): Boolean {
        when {
            component is JTextField && component.columns != 0 -> return true
            component is JTextComponent -> {
                cc.growX()
                if (component is JTextArea && component.lineWrap) {
                    cc.growY()
                }
            }
            component.isPurpose(ComponentPurpose.ScrollPane) -> {
                cc.grow().pushY()
                val view = component.getView()
                if (view is JTextArea && view.rows == 0) {
                    // set min size to 2 lines (yes, for some reasons it means that rows should be set to 3)
                    view.rows = 3
                }
            }
            else -> return false
        }
        return true
    }

    private fun Component.getView(): Component? = if (this is JScrollPane) {
        this.viewport
    } else {
        this.getProperty<JScrollPane>(ComponentPurpose.ScrollPane)?.viewport?.view
    }

    fun applyGrowPolicy(cc: CC, growPolicy: GrowPolicy) {
        cc.horizontal.size = when (growPolicy) {
            GrowPolicy.SHORT_TEXT -> shortTextSizeSpec
            GrowPolicy.MEDIUM_TEXT -> mediumTextSizeSpec
        }
    }
}
