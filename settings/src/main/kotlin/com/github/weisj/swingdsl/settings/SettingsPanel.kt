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

import com.github.weisj.swingdsl.bindEnabled
import com.github.weisj.swingdsl.collection.UndoRedoList
import com.github.weisj.swingdsl.component.HideableTree
import com.github.weisj.swingdsl.component.HideableTreeModel
import com.github.weisj.swingdsl.component.HideableTreeNode
import com.github.weisj.swingdsl.invokeLater
import com.github.weisj.swingdsl.style.DynamicUI
import com.github.weisj.swingdsl.style.UIFactory
import com.github.weisj.swingdsl.style.stripUIResource
import java.awt.BorderLayout
import java.awt.CardLayout
import java.awt.Component
import java.awt.Dimension
import java.awt.Font
import java.util.*
import javax.swing.BorderFactory
import javax.swing.Box
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JSplitPane
import javax.swing.JTree
import javax.swing.event.TreeExpansionEvent
import javax.swing.event.TreeExpansionListener
import javax.swing.tree.DefaultTreeCellRenderer
import javax.swing.tree.TreePath
import kotlin.math.max

class SettingsPanel(private val categories: List<Category>) : JPanel(), UIContext {
    private val categoryPanels = categories.associateWith { createCategoryPanel(it) }

    private var currentCategory: Category?
    private val navigationHistory = UndoRedoList()

    private val cardLayout = CardLayout()
    private val categoriesPanel = JPanel(cardLayout)
    private val splitPane: JSplitPane
    private val categoryTree: CategoryTree
    private val breadcrumbBar: BreadcrumbBar<Element>

    init {
        layout = BorderLayout()
        categoryTree = CategoryTree(categories)
        categoryTree.addCategorySelectionListener {
            it ?: return@addCategorySelectionListener
            navigateTo(it)
        }

        categoriesPanel.addCategories(categories)

        val treeScrollPane = UIFactory.createScrollPane(categoryTree)
        val treeWrapper = object : JPanel(BorderLayout()) {
            override fun getMinimumSize(): Dimension {
                return categoryTree.preferredSize.apply {
                    width = Integer.min(width, 1000)
                }
            }
        }
        treeWrapper.add(treeScrollPane.container, BorderLayout.CENTER)

        breadcrumbBar = BreadcrumbBar()
        breadcrumbBar.renderer = DynamicUI.withBoldFont(
            DefaultBreadCrumbRenderer {
                it.displayName?.get() ?: ""
            }
        )
        breadcrumbBar.addNavigationListener { _, item ->
            if (item is Category) navigateTo(item)
        }

        val banner = Box.createHorizontalBox()
        banner.add(breadcrumbBar)
        banner.add(Box.createHorizontalGlue())
        banner.add(
            JButton("<-").apply {
                border = BorderFactory.createEmptyBorder()
                isContentAreaFilled = false
                bindEnabled(navigationHistory.observable.canUndo)
                addActionListener { navigationHistory.undo() }
            }
        )
        banner.add(
            JButton("->").apply {
                border = BorderFactory.createEmptyBorder()
                isContentAreaFilled = false
                bindEnabled(navigationHistory.observable.canRedo)
                addActionListener { navigationHistory.redo() }
            }
        )

        val contentPanel = JPanel(BorderLayout())
        contentPanel.add(UIFactory.createScrollPane(categoriesPanel).container, BorderLayout.CENTER)
        contentPanel.add(banner, BorderLayout.NORTH)

        splitPane = object : JSplitPane(HORIZONTAL_SPLIT, treeWrapper, contentPanel) {
            override fun addNotify() {
                super.addNotify()
                updateDividerLocation()
            }

            override fun updateUI() {
                super.updateUI()
                updateDividerLocation()
            }
        }
        categoryTree.addTreeExpansionListener(object : TreeExpansionListener {
            override fun treeExpanded(event: TreeExpansionEvent?) {
                invokeLater { splitPane.updateDividerLocation() }
            }

            override fun treeCollapsed(event: TreeExpansionEvent?) {
                invokeLater { splitPane.updateDividerLocation() }
            }
        })

        add(splitPane, BorderLayout.CENTER)

        currentCategory = null
        categoryTree.currentCategory?.let { navigateTo(it) }
    }

    private fun navigateImpl(category: Category) {
        if (currentCategory == category) return
        currentCategory = category
        cardLayout.show(categoriesPanel, category.layoutIdentifier())
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
            add(createCategoryPanel(category).container, category.layoutIdentifier())
            addCategories(category.subCategories)
        }
    }

    private fun Category.layoutIdentifier(): String = "$identifier${hashCode()}"

    private fun JSplitPane.updateDividerLocation() {
        dividerLocation = max(minimumDividerLocation, dividerLocation)
    }
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
                it.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
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
        return this
    }
}
