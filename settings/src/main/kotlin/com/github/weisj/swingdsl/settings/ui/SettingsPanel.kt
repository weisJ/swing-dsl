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

import com.github.weisj.swingdsl.FocusState
import com.github.weisj.swingdsl.SplitPaneBuilder
import com.github.weisj.swingdsl.bindVisible
import com.github.weisj.swingdsl.border.dialogSpacing
import com.github.weisj.swingdsl.border.emptyBorder
import com.github.weisj.swingdsl.border.topBorder
import com.github.weisj.swingdsl.borderPanel
import com.github.weisj.swingdsl.clampSizes
import com.github.weisj.swingdsl.component.BreadcrumbBar
import com.github.weisj.swingdsl.component.DefaultBreadCrumbRenderer
import com.github.weisj.swingdsl.component.DefaultJPanel
import com.github.weisj.swingdsl.component.HyperlinkLabel
import com.github.weisj.swingdsl.component.SearchField
import com.github.weisj.swingdsl.configureBorderLayout
import com.github.weisj.swingdsl.core.binding.ObservableProperty
import com.github.weisj.swingdsl.core.binding.bind
import com.github.weisj.swingdsl.core.condition.ObservableCondition
import com.github.weisj.swingdsl.core.text.emptyText
import com.github.weisj.swingdsl.core.text.unaryPlus
import com.github.weisj.swingdsl.highlight.DefaultSearchContext
import com.github.weisj.swingdsl.highlight.LayoutTag
import com.github.weisj.swingdsl.highlight.SearchContext
import com.github.weisj.swingdsl.highlight.createLayoutTag
import com.github.weisj.swingdsl.horizontalSplit
import com.github.weisj.swingdsl.invokeLater
import com.github.weisj.swingdsl.laf.WrappedComponent
import com.github.weisj.swingdsl.laf.focus.FocusParentHelper
import com.github.weisj.swingdsl.layout.getDefaultSpacingConfiguration
import com.github.weisj.swingdsl.layout.makeDefaultButton
import com.github.weisj.swingdsl.layout.panel
import com.github.weisj.swingdsl.on
import com.github.weisj.swingdsl.scrollPane
import com.github.weisj.swingdsl.settings.Category
import com.github.weisj.swingdsl.settings.DefaultElement
import com.github.weisj.swingdsl.settings.DefaultTopLevelCategory
import com.github.weisj.swingdsl.settings.Element
import com.github.weisj.swingdsl.settings.UIContext
import com.github.weisj.swingdsl.settings.getNearestCategory
import com.github.weisj.swingdsl.style.DynamicUI
import com.github.weisj.swingdsl.style.UIFactory
import com.github.weisj.swingdsl.style.backgroundColorOf
import com.github.weisj.swingdsl.style.stripUIResource
import com.github.weisj.swingdsl.toKeyStroke
import com.github.weisj.swingdsl.unaryPlus
import com.github.weisj.swingdsl.wrap
import java.awt.event.KeyEvent
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.event.TreeExpansionEvent
import javax.swing.event.TreeExpansionListener
import kotlin.math.abs

class SettingsPanel(private val categories: List<Category>) :
    DefaultJPanel(), UIContext, SearchContext<Element> by DefaultSearchContext() {

    private val fallbackCategory = DefaultTopLevelCategory(DefaultElement(null, "Empty fallback page"), emptyText())
    private val navigationManager = NavigationManager(fallbackCategory) {
        categoriesPanel.createLayoutTag(categoriesPanel.visibleRect)
    }
    override val currentPosition: ObservableProperty<NavigationPosition>
        get() = navigationManager.currentPosition

    private val searchHandler = SearchHandler(this)
    private val currentSearchResult: ObservableProperty<SettingsSearchResult?>
        get() = searchHandler.result

    private val categoriesPanel = CategoriesPanel(this, categories, fallbackCategory)
    private val categoryTree = createCategoryTree()
    private val breadcrumbBar = createBreadCrumbBar()

    private val modifiedCondition: ObservableCondition = categoriesPanel.modifiedCondition

    init {
        configureContentArea()

        currentSearchResult.bind { result ->
            navigationManager.searchMode = result != null
            result ?: return@bind
            val target = result.bestMatch?.searchable
            reveal(target?.data?.getNearestCategory(), target?.tag)
        }

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
                                scrollPane { +categoryTree }
                            }
                        }
                    }
                }
                right {
                    borderPanel {
                        north { createBanner() }
                        center { searchHandler.wrapInHighlightLayer(categoriesPanel) }
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
            currentPosition.onChange { select(it.category) }
            currentSearchResult.onChange {
                if (it == null) resetMask() else setMask(it.categories)
            }
            border = dialogSpacing(left = true, right = true)
            DynamicUI.withDynamic(this) {
                it.background = UIFactory.colorBackgroundColor.stripUIResource()
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
                    abs(getRowForCategory(it) - getRowForCategory(navigationManager.currentPosition.get().category)) > 1
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
            currentPosition.bind { breadCrumbs = it.category.getPath() }
            addNavigationListener { _, item ->
                if (item is Category) reveal(item)
            }
        }
    }

    private fun createSearchField(): WrappedComponent<out JComponent> {
        return +SearchField(searchHandler::search, this)
    }

    override fun hideSearch() = searchHandler.hideSearch()

    override fun displaySearchResult(result: SettingsSearchResult) = searchHandler.displaySearchResult(result)

    private fun reset(): Unit = categoriesPanel.reset()

    private fun apply(): Unit = categoriesPanel.apply()

    override fun reveal(category: Category?, tag: LayoutTag?, includeInNavigationHistory: Boolean) =
        navigationManager.navigateTo(category, tag, includeInHistory = includeInNavigationHistory)
}
