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

import com.github.weisj.swingdsl.FocusState
import com.github.weisj.swingdsl.SplitPaneBuilder
import com.github.weisj.swingdsl.bindEnabled
import com.github.weisj.swingdsl.bindVisible
import com.github.weisj.swingdsl.binding.onChange
import com.github.weisj.swingdsl.border.empty
import com.github.weisj.swingdsl.border.topBorder
import com.github.weisj.swingdsl.borderPanel
import com.github.weisj.swingdsl.clampSizes
import com.github.weisj.swingdsl.collection.UndoRedoList
import com.github.weisj.swingdsl.component.HideableTree
import com.github.weisj.swingdsl.component.HideableTreeModel
import com.github.weisj.swingdsl.component.HideableTreeNode
import com.github.weisj.swingdsl.component.HyperlinkLabel
import com.github.weisj.swingdsl.condition.conditionOf
import com.github.weisj.swingdsl.condition.or
import com.github.weisj.swingdsl.configureBorderLayout
import com.github.weisj.swingdsl.configureContainer
import com.github.weisj.swingdsl.horizontalSplit
import com.github.weisj.swingdsl.laf.WrappedComponent
import com.github.weisj.swingdsl.layout.ModifiablePanel
import com.github.weisj.swingdsl.layout.makeDefaultButton
import com.github.weisj.swingdsl.layout.panel
import com.github.weisj.swingdsl.on
import com.github.weisj.swingdsl.properties
import com.github.weisj.swingdsl.scrollPane
import com.github.weisj.swingdsl.style.DynamicUI
import com.github.weisj.swingdsl.style.UIFactory
import com.github.weisj.swingdsl.style.backgroundColorOf
import com.github.weisj.swingdsl.style.stripUIResource
import com.github.weisj.swingdsl.text.unaryPlus
import com.github.weisj.swingdsl.toKeyStroke
import com.github.weisj.swingdsl.unaryPlus
import com.github.weisj.swingdsl.wrap
import java.awt.AWTEvent
import java.awt.CardLayout
import java.awt.Component
import java.awt.Container
import java.awt.Dimension
import java.awt.Font
import java.awt.Toolkit
import java.awt.event.KeyEvent
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.Box
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.JTree
import javax.swing.KeyStroke
import javax.swing.event.TreeExpansionEvent
import javax.swing.event.TreeExpansionListener
import javax.swing.tree.DefaultTreeCellRenderer
import javax.swing.tree.TreePath

class SettingsPanel(private val categories: List<Category>) : JPanel(), UIContext {
    private val categoryPanels = mutableMapOf<Category, WrappedComponent<ModifiablePanel>>()

    private var currentCategory: Category? = null
    private val navigationHistory = UndoRedoList()

    private val cardLayout = ScrollCardLayout()
    private val categoriesPanel = JPanel(cardLayout).apply { addCategories(categories) }
    private val categoryTree = createCategoryTree()
    private val breadcrumbBar = createBreadCrumbBar()

    private val modifiedCondition = categoryPanels.values.fold(conditionOf(false)) { result, panel ->
        result or panel.component.modifiedCondition
    }

    init {
        configureBorderLayout(this) {
            center {
                horizontalSplit {
                    left {
                        borderPanel {
//                            north {
//                                wrap({
//                                    border = empty(10)
//                                    isOpaque = true
//                                    backgroundColorOf(categoryTree).onChange(invokeOnce = true) {
//                                        background = it
//                                    }
//                                }) {
//                                    +JTextField().apply {
//                                        columns = 10
//                                        minimumSize = preferredSize
//                                        properties {
//                                            client["JTextField.variant"] = "search"
//                                        }
//                                    }
//                                }
//                            }
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
                                scrollPane({
                                    verticalScrollBar.unitIncrement = 20
                                    horizontalScrollBar.unitIncrement = 100
                                }) {
                                    +categoriesPanel
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
        categoryTree.currentCategory?.let { navigateTo(it) }
        on(KeyEvent.VK_LEFT.toKeyStroke(KeyEvent.ALT_DOWN_MASK), focusState = FocusState.IN_FOCUSED_WINDOW) {
            navigationHistory.undoIfPossible()
        }
        on(KeyEvent.VK_RIGHT.toKeyStroke(KeyEvent.ALT_DOWN_MASK), focusState = FocusState.IN_FOCUSED_WINDOW) {
            navigationHistory.redoIfPossible()
        }
    }

    private fun createButtonPanel(): WrappedComponent<out JComponent> = panel {
        row {
            right {
                cell {
                    //TODO: Bind this to close/hide actions as well
                    button(+"OK") { apply() }.makeDefaultButton().sizeGroup("buttons")
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
            center { +breadcrumbBar }
            east {
                +DynamicUI.withBoldFont(HyperlinkLabel(+"Reset")).apply {
                    addListener { reset() }
                    border = empty(5, 5, 5, 5)
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
            border = empty(0, 10, 0, 10)
            properties {
                client["JTree.lineStyle"] = "none"
                client["focusParent"] = categoriesPanel
            }
            addCategorySelectionListener {
                it ?: return@addCategorySelectionListener
                navigateTo(it)
            }
        }
    }

    private fun createBreadCrumbBar(): BreadcrumbBar<Element> {
        return BreadcrumbBar<Element>().apply {
            renderer = DynamicUI.withBoldFont(
                DefaultBreadCrumbRenderer {
                    it.displayName?.get() ?: ""
                }
            )
            addNavigationListener { _, item ->
                if (item is Category) navigateTo(item)
            }
        }
    }

    private fun reset() {
        categoryPanels.forEach { (_, panel) -> panel.component.reset() }
    }

    private fun apply() {
        categoryPanels.forEach { (_, panel) -> panel.component.apply() }
    }

    private fun navigateImpl(category: Category) {
        if (currentCategory == category) return
        currentCategory = category
        cardLayout.showCard(categoriesPanel, category.layoutIdentifier(), categoryPanels[category]?.container)
        categoryTree.select(category)
        breadcrumbBar.breadCrumbs = category.getPath()
    }

    override fun navigateTo(category: Category) {
        if (currentCategory == category) return
        val current = currentCategory
        navigationHistory.add(
            { navigateImpl(category) },
            current?.let { { navigateImpl(it) } }
        )
    }

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

val TreePath.category: Category?
    get() = (lastPathComponent as? HideableTreeNode<*>)?.value as? Category

class CategoryTree private constructor(
    private val nodeMap: Map<Category, HideableTreeNode<*>>,
    model: HideableTreeModel
) : HideableTree(model) {

    val currentCategory: Category?
        get() = selectionPath?.category

    fun addCategorySelectionListener(action: (Category?) -> Unit) {
        addTreeSelectionListener {
            action(currentCategory)
        }
    }

    fun select(category: Category) {
        nodeMap[category]?.let {
            val path = TreePath(it.path)
            scrollPathToVisible(path)
            selectionPath = path
        }
    }

    companion object {
        private fun HideableTreeNode<*>.addCategories(
            categories: List<Category>,
            tree: HideableTree,
            nodeMap: MutableMap<Category, HideableTreeNode<*>>
        ) {
            for (category in categories) {
                val node = HideableTreeNode(category)
                nodeMap[category] = node
                val visibleProp = category.displayState.let {
                    if (it is DefaultDisplayState) it.originalVisible
                    else it.visible
                }
                val enabledProp = category.displayState.let {
                    if (it is DefaultDisplayState) it.originalEnabled
                    else it.enabled
                }
                addWithBinding(node, tree, visibleProp, enabledProp)
                node.addCategories(category.subCategories, tree, nodeMap)
            }
        }

        operator fun invoke(categories: List<Category>): CategoryTree {
            val root = HideableTreeNode(null)
            val model = HideableTreeModel(root)
            val nodeMap = mutableMapOf<Category, HideableTreeNode<*>>()
            val tree = CategoryTree(nodeMap, model)
            root.addCategories(categories, tree, nodeMap)
            model.reload()
            return tree.also {
                it.setSelectionRow(0)
                it.setCellRenderer(CategoryTreeCellRenderer())
            }
        }
    }
}

private class CategoryTreeCellRenderer : DefaultTreeCellRenderer() {

    private var normalFont: Font? = font
    private var boldFont: Font? = font

    override fun updateUI() {
        super.updateUI()
        updateFonts()
    }

    fun updateFonts() {
        font ?: return
        if (normalFont == null) {
            normalFont = font.stripUIResource()
            boldFont = normalFont!!.deriveFont(Font.BOLD).stripUIResource()
        }
    }

    override fun getTreeCellRendererComponent(
        tree: JTree?,
        value: Any?,
        sel: Boolean,
        expanded: Boolean,
        leaf: Boolean,
        row: Int,
        hasFocus: Boolean
    ): Component {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus)
        if (value is HideableTreeNode<*>) {
            val category = value.value as Category
            icon = null
            disabledIcon = null
            text = category.displayName.get()
            updateFonts()
            font = if (category is TopLevel) boldFont else normalFont
        }
        background = null
        return this
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
