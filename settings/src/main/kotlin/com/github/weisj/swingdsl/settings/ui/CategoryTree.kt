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

import com.github.weisj.swingdsl.component.HideableTree
import com.github.weisj.swingdsl.component.HideableTreeModel
import com.github.weisj.swingdsl.component.HideableTreeNode
import com.github.weisj.swingdsl.properties
import com.github.weisj.swingdsl.settings.Category
import com.github.weisj.swingdsl.settings.DefaultDisplayState
import com.github.weisj.swingdsl.settings.TopLevel
import com.github.weisj.swingdsl.settings.getPath
import com.github.weisj.swingdsl.style.stripUIResource
import java.awt.Component
import java.awt.Font
import javax.swing.JTree
import javax.swing.tree.DefaultTreeCellRenderer
import javax.swing.tree.TreePath

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

    fun setMask(mask: Set<Category>) {
        // We need to make sure all categories along the way are displayed too.
        val visibleCategories = mask.flatMap { it.getPath() }
        nodeMap.forEach { (t, u) ->
            setNodeVisible(u, t in visibleCategories, setForcedValue = true)
        }
    }

    fun resetMask() {
        val currentSelection = selectionPaths
        nodeMap.forEach { (_, u) ->
            setNodeVisible(u, true, setForcedValue = true)
        }
        // This is save to do, as selected paths need to be completely visible.
        // Either way their path won't change by making other nodes visible as well.
        selectionPaths = currentSelection
    }

    fun isCategoryVisible(category: Category): Boolean {
        return nodeMap[category]?.isVisible ?: false
    }

    fun getRowForCategory(category: Category): Int {
        return nodeMap[category]?.let { getRowForPath(TreePath(it.path)) } ?: -1
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
                // We need to apply the binding for the node alone.
                // Coercion with parent states is handled through the nature of the
                // tree structure alone. This way we minimize the needed modifications of the layout.
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
            return tree.apply {
                setSelectionRow(0)
                setCellRenderer(CategoryTreeCellRenderer())
                properties {
                    client["JTree.lineStyle"] = "none"
                }
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
        if (value is HideableTreeNode<*> && value.value is Category) {
            val category = value.value as Category
            icon = null
            disabledIcon = null
            text = category.displayName.get()
            updateFonts()
            font = if (category is TopLevel) boldFont else normalFont
        }
        background = null
        isOpaque = false
        return this
    }
}
