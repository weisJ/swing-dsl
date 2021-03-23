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

// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.github.weisj.swingdsl.layout

import com.github.weisj.swingdsl.layout.miglayout.MigLayoutBuilder
import com.github.weisj.swingdsl.text.Text
import com.github.weisj.swingdsl.text.textOfNullable
import java.awt.Container
import java.awt.event.ActionListener
import javax.swing.ButtonGroup
import javax.swing.JComponent

open class PanelBuilder @PublishedApi internal constructor(@PublishedApi internal val builder: PanelBuilderImpl) :
    RowBuilder by builder.rootRow {
    override fun withButtonGroup(title: Text?, buttonGroup: ButtonGroup, body: () -> Unit) {
        builder.withButtonGroup(buttonGroup, body)
    }

    inline fun buttonGroup(
        crossinline elementActionListener: () -> Unit,
        crossinline init: PanelBuilder.() -> Unit
    ): ButtonGroup {
        val group = ButtonGroup()

        builder.withButtonGroup(group) {
            PanelBuilder(builder).init()
        }

        val listener = ActionListener { elementActionListener() }
        for (button in group.elements) {
            button.addActionListener(listener)
        }
        return group
    }
}

@PublishedApi
internal interface PanelBuilderImpl {
    val rootRow: Row
    fun withButtonGroup(buttonGroup: ButtonGroup, body: () -> Unit)

    fun build(container: Container, layoutConstraints: Array<out LCFlags>)

    val applyCallbacks: Map<JComponent?, List<() -> Unit>>
    val resetCallbacks: Map<JComponent?, List<() -> Unit>>
    val isModifiedCallbacks: Map<JComponent?, List<() -> Boolean>>
}

/**
 * Claims all available space in the container for the columns ([LCFlags.fillX], if `constraints` is
 * passed, `fillX` will be not applied - add it explicitly if need).
 * At least one component need to have a [Row.grow] constraint for it to fill the container.
 */
@JvmOverloads
fun panel(
    vararg constraints: LCFlags = emptyArray(),
    title: Text? = null,
    init: PanelBuilder.() -> Unit
): ModifiablePanel {
    val builder = createLayoutBuilder()
    builder.init()

    val panel = ModifiablePanel(title, layout = null)
    builder.builder.build(panel, constraints)
    initPanel(builder, panel)
    return panel
}

@JvmOverloads
fun panel(
    vararg constraints: LCFlags = emptyArray(),
    title: String,
    init: PanelBuilder.() -> Unit
): ModifiablePanel {
    return panel(*constraints, title = textOfNullable(title), init = init)
}

@PublishedApi
internal fun createLayoutBuilder(): PanelBuilder {
    return PanelBuilder(MigLayoutBuilder(createDefaultSpacingConfiguration()))
}

@PublishedApi
internal fun initPanel(builder: PanelBuilder, panel: ModifiablePanel) {
    panel.applyCallbacks = builder.builder.applyCallbacks
    panel.resetCallbacks = builder.builder.resetCallbacks
    panel.isModifiedCallbacks = builder.builder.isModifiedCallbacks
}
