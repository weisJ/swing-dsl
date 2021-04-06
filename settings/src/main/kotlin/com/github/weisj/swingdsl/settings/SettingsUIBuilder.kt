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
package com.github.weisj.swingdsl.settings

import com.github.weisj.swingdsl.BuilderWithEnabledProperty
import com.github.weisj.swingdsl.component.HyperlinkLabel
import com.github.weisj.swingdsl.laf.WrappedComponent
import com.github.weisj.swingdsl.layout.ModifiablePanel
import com.github.weisj.swingdsl.layout.Row
import com.github.weisj.swingdsl.layout.RowBuilder
import com.github.weisj.swingdsl.layout.panel
import com.github.weisj.swingdsl.text.Text
import com.github.weisj.swingdsl.text.isConstantNullOrEmpty
import javax.swing.JComponent

fun createSettingsPanel(categories: List<Category>): JComponent = SettingsPanel(categories)
fun createSettingsPanel(vararg categories: Category): JComponent = SettingsPanel(listOf(*categories))

fun SettingsPanel.createCategoryPanel(category: Category): WrappedComponent<ModifiablePanel> {
    return panel {
        row {
            indent(false)
            category.createUI(this, this@createCategoryPanel)
        }
    }
}

fun Row.addCategoryOverview(category: Category, context: UIContext) {
    category.description?.let { left { commentRow(it) } }
    row {
        cell(isVerticalFlow = true) {
            category.subCategories.forEach { cat ->
                component(
                    HyperlinkLabel(cat.displayName).apply {
                        addHyperlinkListener { context.navigateTo(cat) }
                    }
                )
            }
        }
    }
}

fun Row.addCategory(category: Category, context: UIContext) {
    bindDisplayStatus(category)
    if (category.groups.isEmpty()) {
        if (category.subCategories.isNotEmpty()) {
            addCategoryOverview(category, context)
        }
    } else {
        category.groups.forEach {
            row(isIndented = false) {
                it.createUI(this, context)
            }
        }
    }
}

fun RowBuilder.addGroup(group: Group, context: UIContext) {
    maybeTitledRow(group.displayName) {
        bindDisplayStatus(group)
        noIndent {
            row { group.description?.let { commentRow(it, withLeftGap = false) } }
            group.values.forEach { it.createUI(this, context) }
        }
        group.subGroups.forEach { it.createUI(this, context) }
    }
}

fun <T> Row.addValue(value: DefaultValue<T>) {
    valueRow(value) {
        bindDisplayStatus(value)
        value.createValueUI(this)() {
            if (value.showDescription) value.description?.let {
                if (it.get().length < 50) {
                    commentNoWrap(it)
                } else {
                    comment(it, forComponent = true)
                }
            }
        }
        onGlobalApply { value.value.set(value.preview.get()) }
    }
}

private fun RowBuilder.maybeTitledRow(name: Text?, init: Row.() -> Unit): Row {
    return if (!name.isConstantNullOrEmpty()) {
        titledRow(name, init)
    } else {
        row(init)
    }
}

private fun Row.valueRow(value: DefaultValue<*>, init: Row.() -> Unit): Row {
    return if (value.showTitle) {
        row(value.displayName, init = init)
    } else {
        if (value.parent is TopLevel && value.parent.displayName.isConstantNullOrEmpty()) {
            lateinit var r: Row
            left {
                r = row(init)
            }
            r
        } else {
            row(init = init)
        }
    }
}

fun BuilderWithEnabledProperty<*>.bindDisplayStatus(element: Element) {
    enableIf(element.displayState.enabled)
    visibleIf(element.displayState.visible)
}

interface UIContext {
    fun navigateTo(category: Category)
}

interface UIParticipant {
    fun createUI(row: Row, context: UIContext)
}
