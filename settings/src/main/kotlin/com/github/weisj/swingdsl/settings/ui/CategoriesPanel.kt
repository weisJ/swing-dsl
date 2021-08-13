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
package com.github.weisj.swingdsl.settings.ui

import com.github.weisj.swingdsl.components.DefaultScrollableView
import com.github.weisj.swingdsl.core.binding.bind
import com.github.weisj.swingdsl.core.condition.ObservableCondition
import com.github.weisj.swingdsl.core.condition.conditionOf
import com.github.weisj.swingdsl.core.condition.or
import com.github.weisj.swingdsl.dsl.ModifiableComponent
import com.github.weisj.swingdsl.dsl.centered
import com.github.weisj.swingdsl.dsl.components.DefaultJPanel
import com.github.weisj.swingdsl.dsl.highlight.LayoutTag
import com.github.weisj.swingdsl.dsl.invokeLater
import com.github.weisj.swingdsl.dsl.style.DynamicUI
import com.github.weisj.swingdsl.dsl.unaryPlus
import com.github.weisj.swingdsl.laf.ScrollableView
import com.github.weisj.swingdsl.settings.Category
import com.github.weisj.swingdsl.settings.UIContext
import com.github.weisj.swingdsl.settings.createCategoryPanel
import java.awt.CardLayout
import java.awt.Container
import java.awt.Dimension
import java.awt.Font
import javax.swing.JComponent
import javax.swing.JLabel

private fun Category.layoutIdentifier(): String = "$identifier${hashCode()}"

class CategoriesPanel private constructor(
    private val context: UIContext,
    categories: List<Category>,
    fallbackCategory: Category,
    private val cardLayout: ScrollCardLayout
) : DefaultJPanel(cardLayout), ScrollableView by DefaultScrollableView(20, 100) {

    private val categoryPanels = mutableMapOf<Category, Lazy<ModifiableComponent<out JComponent>>>()

    private val defaultCategory = fallbackCategory

    private val modifiedListeners = mutableMapOf<Any, (Boolean) -> Unit>()
    private lateinit var modifiedConditionInternal: ObservableCondition

    private fun addModifiedCondition(modifiedCondition: ObservableCondition) {
        modifiedListeners.forEach { (k, _), -> modifiedConditionInternal.removeCallback(k) }
        modifiedConditionInternal = modifiedConditionInternal or modifiedCondition
        modifiedListeners.forEach { (k, v) -> modifiedConditionInternal.onChange(k, v) }
    }

    val modifiedCondition: ObservableCondition = object : ObservableCondition {
        override fun get(): Boolean {
            return modifiedConditionInternal.get()
        }

        override fun onChange(observeKey: Any?, callback: (Boolean) -> Unit) {
            // Ensure all callbacks have keys that aren't a lambda.
            modifiedListeners[observeKey ?: Any()] = callback
            modifiedConditionInternal.onChange(observeKey, callback)
        }

        override fun removeCallback(observeKey: Any?) {
            modifiedListeners.remove(observeKey)
            modifiedConditionInternal.removeCallback(observeKey)
        }
    }

    constructor(context: UIContext, categories: List<Category>, fallbackCategory: Category) :
        this(context, categories, fallbackCategory, ScrollCardLayout())

    init {
        addCategories(categories)
        modifiedConditionInternal = categoryPanels.values.fold(conditionOf(false)) { result, panel ->
            if (panel.isInitialized()) {
                result or panel.value.modifiedCondition
            } else {
                result
            }
        }
        context.currentPosition.bind {
            reveal(it.category, it.tag)
        }
    }

    private fun addCategories(categories: List<Category>) {
        addCategory(defaultCategory) {
            ModifiableComponent(
                centered {
                    +DynamicUI.withTransformedFont(JLabel(":(")) {
                        it.deriveFont(Font.BOLD, 26f)
                    }
                }.container
            )
        }
        for (category in categories) {
            addCategory(category) { createCategoryPanel(category, context) }
            addCategories(category.subCategories)
        }
    }

    private fun addCategory(category: Category, panelProvider: () -> ModifiableComponent<out JComponent>) {
        categoryPanels.getOrPut(category) {
            lazy {
                val panel = panelProvider()
                addModifiedCondition(panel.modifiedCondition)
                panel
            }
        }
    }

    private fun reveal(category: Category, tag: LayoutTag? = null) {
        val container = categoryPanels[category]?.value?.container ?: return
        val identifier = category.layoutIdentifier()
        if (container.parent != this) {
            add(container, identifier)
        }
        cardLayout.showCard(this, identifier, container)
        if (tag != null) {
            invokeLater { scrollRectToVisible(tag.getBoundsIn(this)) }
        }
    }

    fun reset(): Unit = categoryPanels.forEach { (_, panel) -> panel.ifPresent { it.reset() } }

    fun apply(): Unit = categoryPanels.forEach { (_, panel) -> panel.ifPresent { it.apply() } }

    private inline fun <T> Lazy<T>.ifPresent(block: (T) -> Unit) {
        if (isInitialized()) block(value)
    }
}

private class ScrollCardLayout : CardLayout() {

    private var current: JComponent? = null

    fun showCard(parent: Container, identifier: String, comp: JComponent?) {
        super.show(parent, identifier)
        current = comp
    }

    override fun preferredLayoutSize(parent: Container?): Dimension {
        return if (current != null) current!!.preferredSize else super.preferredLayoutSize(parent)
    }

    override fun minimumLayoutSize(parent: Container?): Dimension {
        return if (current != null) current!!.minimumSize else super.minimumLayoutSize(parent)
    }
}
