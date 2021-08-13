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

import com.github.weisj.swingdsl.components.BreadcrumbBar
import com.github.weisj.swingdsl.components.DefaultBreadCrumbRenderer
import com.github.weisj.swingdsl.components.ListBreadcrumbModel
import com.github.weisj.swingdsl.core.binding.ObservableProperty
import com.github.weisj.swingdsl.core.binding.bind
import com.github.weisj.swingdsl.core.condition.ObservableCondition
import com.github.weisj.swingdsl.core.text.emptyText
import com.github.weisj.swingdsl.core.text.unaryPlus
import com.github.weisj.swingdsl.dsl.FocusState
import com.github.weisj.swingdsl.dsl.SplitPaneBuilder
import com.github.weisj.swingdsl.dsl.bindVisible
import com.github.weisj.swingdsl.dsl.border.dialogSpacing
import com.github.weisj.swingdsl.dsl.border.emptyBorder
import com.github.weisj.swingdsl.dsl.border.topBorder
import com.github.weisj.swingdsl.dsl.borderPanel
import com.github.weisj.swingdsl.dsl.clampSizes
import com.github.weisj.swingdsl.dsl.components.DefaultJPanel
import com.github.weisj.swingdsl.dsl.components.HyperlinkLabel
import com.github.weisj.swingdsl.dsl.components.SearchField
import com.github.weisj.swingdsl.dsl.configureBorderLayout
import com.github.weisj.swingdsl.dsl.highlight.DefaultSearchContext
import com.github.weisj.swingdsl.dsl.highlight.LayoutTag
import com.github.weisj.swingdsl.dsl.highlight.SearchContext
import com.github.weisj.swingdsl.dsl.highlight.createLayoutTag
import com.github.weisj.swingdsl.dsl.horizontalSplit
import com.github.weisj.swingdsl.dsl.invokeLater
import com.github.weisj.swingdsl.dsl.layout.getDefaultSpacingConfiguration
import com.github.weisj.swingdsl.dsl.layout.makeDefaultButton
import com.github.weisj.swingdsl.dsl.layout.panel
import com.github.weisj.swingdsl.dsl.on
import com.github.weisj.swingdsl.dsl.properties
import com.github.weisj.swingdsl.dsl.scrollPane
import com.github.weisj.swingdsl.dsl.style.DynamicUI
import com.github.weisj.swingdsl.dsl.style.UIFactory
import com.github.weisj.swingdsl.dsl.style.backgroundColorOf
import com.github.weisj.swingdsl.dsl.style.stripUIResource
import com.github.weisj.swingdsl.dsl.toKeyStroke
import com.github.weisj.swingdsl.dsl.unaryPlus
import com.github.weisj.swingdsl.dsl.wrap
import com.github.weisj.swingdsl.laf.WrappedComponent
import com.github.weisj.swingdsl.settings.Category
import com.github.weisj.swingdsl.settings.DefaultElement
import com.github.weisj.swingdsl.settings.DefaultTopLevelCategory
import com.github.weisj.swingdsl.settings.Element
import com.github.weisj.swingdsl.settings.UIContext
import com.github.weisj.swingdsl.settings.getNearestCategory
import com.github.weisj.swingdsl.settings.getPath
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
                                backgroundColorOf(categoryTree).bind(setter = ::setBackground)
                            }) { createSearchField() }
                        }
                        center {
                            clampSizes(maxMinWidth = 500, clampBy = categoryTree) {
                                scrollPane({ border = emptyBorder() }) { +categoryTree }
                            }
                        }
                    }
                }
                right {
                    borderPanel {
                        north { createBanner() }
                        center {
                            searchHandler.wrapInHighlightLayer(
                                scrollPane({ border = emptyBorder() }) { +categoriesPanel }
                            )
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
            properties {
                client["focusParent"] = this@SettingsPanel
            }
            addCategorySelectionListener {
                it ?: return@addCategorySelectionListener
                val includeInHistory =
                    abs(getRowForCategory(it) - getRowForCategory(navigationManager.currentPosition.get().category)) > 1
                reveal(it, includeInNavigationHistory = includeInHistory)
            }
        }
    }

    private fun createBreadCrumbBar(): BreadcrumbBar<*, Element> {
        val model = ListBreadcrumbModel<Element>()
        return BreadcrumbBar(model).apply {
            val spaceConfig = getDefaultSpacingConfiguration()
            margin.left = spaceConfig.dialogLeftRight
            margin.right = spaceConfig.dialogLeftRight
            renderer = DynamicUI.withBoldFont(
                DefaultBreadCrumbRenderer(
                    stringFunc = {
                        it.displayName?.get() ?: ""
                    }
                )
            )
            currentPosition.bind { model.nodes = it.category.getPath() }
            addNavigationListener { _, _, item ->
                if (item is Category) reveal(item)
                item is Category
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
