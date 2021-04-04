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

import com.github.weisj.swingdsl.invokeLater
import com.github.weisj.swingdsl.style.DynamicUI
import com.github.weisj.swingdsl.style.UIFactory
import java.awt.BorderLayout
import java.awt.CardLayout
import java.awt.Component
import java.awt.Dimension
import javax.swing.BorderFactory
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JSplitPane
import javax.swing.JTree
import javax.swing.event.TreeExpansionEvent
import javax.swing.event.TreeExpansionListener
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeModel
import javax.swing.tree.TreeCellRenderer
import javax.swing.tree.TreeModel
import javax.swing.tree.TreePath
import javax.swing.tree.TreeSelectionModel
import kotlin.math.max

class SettingsPanel(private val categories: List<Category>) : JPanel(), UIContext {
    private val categoryPanels = categories.associateWith { createCategoryPanel(it) }
    private var currentCategory: Category?

    private val cardLayout = CardLayout()
    private val contentPanel = JPanel(cardLayout)
    private val splitPane: JSplitPane
    private val categoryTree: CategoryTree

    init {
        layout = BorderLayout()
        categoryTree = CategoryTree(categories)
        currentCategory = categoryTree.currentCategory
        categoryTree.addCategorySelectionListener {
            it ?: return@addCategorySelectionListener
            navigateTo(it)
        }

        contentPanel.addCategories(categories)

        val treeScrollPane = UIFactory.createScrollPane(categoryTree)
        val treeWrapper = object : JPanel(BorderLayout()) {
            override fun getMinimumSize(): Dimension {
                return categoryTree.preferredSize.apply {
                    width = Integer.min(width, 1000)
                }
            }
        }
        treeWrapper.add(treeScrollPane.container, BorderLayout.CENTER)

        val contentScrollPane = UIFactory.createScrollPane(contentPanel)
        splitPane = object : JSplitPane(HORIZONTAL_SPLIT, treeWrapper, contentScrollPane.container) {
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
    }

    override fun navigateTo(category: Category) {
        if (currentCategory == category) return
        currentCategory = category
        cardLayout.show(contentPanel, category.layoutIdentifier())
        categoryTree.select(category)
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

class CategoryTree private constructor(private val nodeMap: Map<Category, DefaultMutableTreeNode>, model: TreeModel) :
    JTree(model) {

    val currentCategory: Category?
        get() = (selectionPath?.lastPathComponent as? DefaultMutableTreeNode)?.userObject as? Category

    fun addCategorySelectionListener(action: (Category?) -> Unit) {
        addTreeSelectionListener {
            action(currentCategory)
        }
    }

    fun select(category: Category) {
        nodeMap[category]?.let {
            val path = TreePath(it.path)
            expandPath(path)
            selectionPath = path
        }
    }

    companion object {
        private fun DefaultMutableTreeNode.addCategories(
            categories: List<Category>,
            nodeMap: MutableMap<Category, DefaultMutableTreeNode>
        ) {
            for (category in categories) {
                add(
                    DefaultMutableTreeNode(category).apply {
                        nodeMap[category] = this
                        addCategories(category.subCategories, nodeMap)
                    }
                )
            }
        }

        operator fun invoke(categories: List<Category>): CategoryTree {
            val root = DefaultMutableTreeNode()
            val nodeMap = mutableMapOf<Category, DefaultMutableTreeNode>()
            root.addCategories(categories, nodeMap)
            return CategoryTree(nodeMap, DefaultTreeModel(root)).also {
                it.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
                it.isRootVisible = false
                it.selectionModel.selectionMode = TreeSelectionModel.SINGLE_TREE_SELECTION
                it.setSelectionRow(0)
                it.setCellRenderer(CategoryTreeCellRenderer())
            }
        }
    }
}

private class CategoryTreeCellRenderer : JLabel(), TreeCellRenderer {

    init {
        DynamicUI.withBoldFont(this)
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
        value as DefaultMutableTreeNode
        val category = value.userObject as? Category
        text = category?.displayName?.get()
        return this
    }
}
