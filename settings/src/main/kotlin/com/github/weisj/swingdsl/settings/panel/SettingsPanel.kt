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
package com.github.weisj.swingdsl.settings.panel

import com.github.weisj.swingdsl.FocusState
import com.github.weisj.swingdsl.SplitPaneBuilder
import com.github.weisj.swingdsl.addDocumentChangeListener
import com.github.weisj.swingdsl.bindVisible
import com.github.weisj.swingdsl.binding.bind
import com.github.weisj.swingdsl.border.dialogSpacing
import com.github.weisj.swingdsl.border.emptyBorder
import com.github.weisj.swingdsl.border.topBorder
import com.github.weisj.swingdsl.borderPanel
import com.github.weisj.swingdsl.clampSizes
import com.github.weisj.swingdsl.component.DefaultJPanel
import com.github.weisj.swingdsl.component.HyperlinkLabel
import com.github.weisj.swingdsl.condition.conditionOf
import com.github.weisj.swingdsl.condition.or
import com.github.weisj.swingdsl.configureBorderLayout
import com.github.weisj.swingdsl.highlight.DefaultSearchContext
import com.github.weisj.swingdsl.highlight.LayoutTag
import com.github.weisj.swingdsl.highlight.SearchContext
import com.github.weisj.swingdsl.highlight.createLayoutTag
import com.github.weisj.swingdsl.horizontalSplit
import com.github.weisj.swingdsl.invokeLater
import com.github.weisj.swingdsl.laf.WrappedComponent
import com.github.weisj.swingdsl.laf.focus.FocusParentHelper
import com.github.weisj.swingdsl.layered
import com.github.weisj.swingdsl.layout.ModifiablePanel
import com.github.weisj.swingdsl.layout.getDefaultSpacingConfiguration
import com.github.weisj.swingdsl.layout.makeDefaultButton
import com.github.weisj.swingdsl.layout.panel
import com.github.weisj.swingdsl.on
import com.github.weisj.swingdsl.properties
import com.github.weisj.swingdsl.scrollPane
import com.github.weisj.swingdsl.settings.Category
import com.github.weisj.swingdsl.settings.DefaultElement
import com.github.weisj.swingdsl.settings.DefaultTopLevelCategory
import com.github.weisj.swingdsl.settings.Element
import com.github.weisj.swingdsl.settings.UIContext
import com.github.weisj.swingdsl.settings.createCategoryPanel
import com.github.weisj.swingdsl.settings.getNearestCategory
import com.github.weisj.swingdsl.style.DynamicUI
import com.github.weisj.swingdsl.style.UIFactory
import com.github.weisj.swingdsl.style.backgroundColorOf
import com.github.weisj.swingdsl.style.stripUIResource
import com.github.weisj.swingdsl.text.emptyText
import com.github.weisj.swingdsl.text.unaryPlus
import com.github.weisj.swingdsl.toKeyStroke
import com.github.weisj.swingdsl.unaryPlus
import com.github.weisj.swingdsl.wrap
import com.github.weisj.swingdsl.yieldFocus
import java.awt.CardLayout
import java.awt.Color
import java.awt.Container
import java.awt.Dimension
import java.awt.event.KeyEvent
import javax.swing.JComponent
import javax.swing.JLayeredPane
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.event.TreeExpansionEvent
import javax.swing.event.TreeExpansionListener
import kotlin.math.abs

class SettingsPanel(private val categories: List<Category>) :
    DefaultJPanel(),
    UIContext,
    SearchContext<Element> by DefaultSearchContext() {

    private val categoryPanels = mutableMapOf<Category, WrappedComponent<ModifiablePanel>>()

    private val emptyPageCategory = DefaultTopLevelCategory(
        DefaultElement(null, "Empty fallback page"), emptyText()
    )

    private val cardLayout = ScrollCardLayout()
    private val categoriesCardPanel = DefaultJPanel(cardLayout).apply { addCategories(categories) }

    private val categoryTree = createCategoryTree()
    private val breadcrumbBar = createBreadCrumbBar()

    private val searchHandler = SearchHandler(this)
    private val navigationManager = NavigationManager(emptyPageCategory, this::navigateImpl) {
        categoriesCardPanel.createLayoutTag(categoriesCardPanel.visibleRect)
    }

    private val modifiedCondition = categoryPanels.values.fold(conditionOf(false)) { result, panel ->
        result or panel.component.modifiedCondition
    }

    init {
        categoryPanels[emptyPageCategory] = panel {}.also { it.component.background = Color.GREEN }
        configureContentArea()
        navigationManager.navigateTo(categoryTree.currentCategory, reversible = false)
        on(KeyEvent.VK_LEFT.toKeyStroke(KeyEvent.ALT_DOWN_MASK), focusState = FocusState.IN_FOCUSED_WINDOW) {
            navigationManager.goBack()
        }
        on(KeyEvent.VK_RIGHT.toKeyStroke(KeyEvent.ALT_DOWN_MASK), focusState = FocusState.IN_FOCUSED_WINDOW) {
            navigationManager.goForward()
        }
    }

    private fun configureContentArea() = configureBorderLayout(this) {
        center {
            horizontalSplit {
                border = emptyBorder()
                left {
                    borderPanel {
                        north {
                            wrap({
                                border = getDefaultSpacingConfiguration().run { emptyBorder(horizontalGap) }
                                isOpaque = true
                                backgroundColorOf(categoryTree).bind(::setBackground)
                            }) { createSearchField() }
                        }
                        center {
                            clampSizes(maxMinWidth = 500, clampBy = categoryTree) {
                                scrollPane {
                                    +categoryTree
                                }
                            }
                        }
                    }
                }
                right {
                    borderPanel {
                        north { createBanner() }
                        center {
                            layered {
                                layers[JLayeredPane.DEFAULT_LAYER] = scrollPane({
                                    verticalScrollBar.unitIncrement = 20
                                    horizontalScrollBar.unitIncrement = 100
                                }) { +categoriesCardPanel }
                                layers[JLayeredPane.PALETTE_LAYER] = searchHandler.highlighter
                            }
                        }
                    }
                }
                clampTo(SplitPaneBuilder.ClampMode.DEFAULT)
                categoryTree.addTreeExpansionListener(object : TreeExpansionListener {
                    override fun treeExpanded(event: TreeExpansionEvent?) = updateDividerLocation()
                    override fun treeCollapsed(event: TreeExpansionEvent?) = updateDividerLocation()
                })
                properties {
                    client["JSplitPane.style"] = "invisible"
                }
            }
        }
        south {
            createButtonPanel()
        }
    }

    private fun createButtonPanel(): WrappedComponent<out JComponent> = panel {
        row {
            right {
                cell {
                    // TODO: Bind this to close/hide actions as well
                    button(+"OK") { apply() }.makeDefaultButton(requestFocus = false).sizeGroup("buttons")
                    button(+"Cancel") { reset() }.sizeGroup("buttons")
                    button(+"Apply") { apply() }.enableIf(modifiedCondition).sizeGroup("buttons")
                }
            }
        }
    }.apply {
        container.border = topBorder(UIFactory::getBorderColor)
    }

    private fun createBanner(): WrappedComponent<JPanel> {
        return borderPanel {
            border = getDefaultSpacingConfiguration().run { emptyBorder(componentVerticalGap, 0, 0, dialogLeftRight) }
            center { +breadcrumbBar }
            east {
                +DynamicUI.withBoldFont(HyperlinkLabel(+"Reset")).apply {
                    addListener { reset() }
                    bindVisible(modifiedCondition)
                }
            }
        }
    }

    private fun createCategoryTree(): CategoryTree {
        return CategoryTree(categories).apply {
            DynamicUI.withDynamic(this) {
                it.background = UIFactory.colorBackgroundColor.stripUIResource()
            }
            border = dialogSpacing(left = true, right = true)
            properties {
                client["JTree.lineStyle"] = "none"
            }
            invokeLater {
                requestFocusInWindow()
            }
            // Todo: Somehow this should be possible to be done either LaF agnostic or supported fully by Darklaf alone.
            FocusParentHelper.setFocusParent(this@apply, this@SettingsPanel) {
                it.repaint()
            }
            addCategorySelectionListener {
                it ?: return@addCategorySelectionListener
                val includeInHistory =
                    abs(getRowForCategory(it) - getRowForCategory(navigationManager.currentCategory)) > 1
                reveal(it, includeInNavigationHistory = includeInHistory)
            }
        }
    }

    private fun createBreadCrumbBar(): BreadcrumbBar<Element> {
        return BreadcrumbBar<Element>().apply {
            padding = getDefaultSpacingConfiguration().dialogLeftRight
            renderer = DynamicUI.withBoldFont(
                DefaultBreadCrumbRenderer {
                    it.displayName?.get() ?: ""
                }
            )
            addNavigationListener { _, item ->
                if (item is Category) reveal(item)
            }
        }
    }

    private fun createSearchField(): WrappedComponent<out JComponent> {
        return +JTextField().apply {
            columns = 10
            minimumSize = preferredSize
            properties {
                client["JTextField.variant"] = "search"
            }
            addDocumentChangeListener {
                val searchResult = search(text)
                if (searchResult.entries.isEmpty() && text.length < 5) {
                    hideSearch()
                    return@addDocumentChangeListener
                }
                showSearch(searchResult)
            }
            on(KeyEvent.VK_F.toKeyStroke(KeyEvent.CTRL_DOWN_MASK), focusState = FocusState.IN_FOCUSED_WINDOW) {
                requestFocus(true)
            }
            on(KeyEvent.VK_ESCAPE.toKeyStroke(), focusState = FocusState.FOCUSED) {
                yieldFocus()
            }
        }
    }

    fun search(searchTerm: String): SettingsSearchResult = searchHandler.search(searchTerm)

    fun hideSearch() {
        if (!navigationManager.searchMode) return
        navigationManager.searchMode = false
        categoryTree.resetMask()
        searchHandler.result = null
    }

    fun showSearch(result: SettingsSearchResult) {
        navigationManager.searchMode = true
        searchHandler.result = result
        categoryTree.setMask(result.categories)
        val firstResult = result.entries.firstOrNull()?.searchable
        val firstCategory = firstResult?.data?.getNearestCategory()
        if (firstResult == null || firstCategory == null) {
            reveal(emptyPageCategory)
        } else {
            reveal(firstCategory, firstResult.tag)
        }
        searchHandler.showHighlightsOf(navigationManager.currentCategory)
    }

    private fun reset() {
        categoryPanels.forEach { (_, panel) -> panel.component.reset() }
    }

    private fun apply() {
        categoryPanels.forEach { (_, panel) -> panel.component.apply() }
    }

    private fun navigateImpl(category: Category, tag: LayoutTag?) {
        cardLayout.showCard(categoriesCardPanel, category.layoutIdentifier(), categoryPanels[category]?.container)
        categoryTree.select(category)
        breadcrumbBar.breadCrumbs = category.getPath()
        searchHandler.showHighlightsOf(category)
        if (tag != null) {
            invokeLater {
                val targetBounds = tag.getBoundsIn(categoriesCardPanel)
                categoriesCardPanel.scrollRectToVisible(targetBounds)
            }
        }
    }

    override fun reveal(category: Category, tag: LayoutTag?, includeInNavigationHistory: Boolean) =
        navigationManager.navigateTo(category, tag, includeInHistory = includeInNavigationHistory)

    private fun JPanel.addCategories(categories: List<Category>) {
        for (category in categories) {
            add(
                categoryPanels.getOrPut(category) { createCategoryPanel(category) }.container,
                category.layoutIdentifier()
            )
            addCategories(category.subCategories)
        }
    }

    private fun Category.layoutIdentifier(): String = "$identifier${hashCode()}"
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
