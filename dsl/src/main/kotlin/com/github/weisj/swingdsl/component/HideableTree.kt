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
package com.github.weisj.swingdsl.component

import com.github.weisj.swingdsl.core.condition.ObservableCondition
import com.github.weisj.swingdsl.onSwingThread
import java.awt.Component
import javax.swing.JTree
import javax.swing.event.TreeExpansionEvent
import javax.swing.event.TreeWillExpandListener
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeModel
import javax.swing.tree.DefaultTreeSelectionModel
import javax.swing.tree.ExpandVetoException
import javax.swing.tree.TreeCellRenderer
import javax.swing.tree.TreeNode
import javax.swing.tree.TreePath
import javax.swing.tree.TreeSelectionModel

private val TreePath?.enabled: Boolean
    get() = (this?.lastPathComponent as? HideableTreeNode<*>)?.realEnabled != false

@Suppress("LeakingThis")
open class HideableTree(
    model: HideableTreeModel
) : JTree(model) {

    init {
        setSelectionModel(HideableTreeSelectionModel(this))
        addTreeWillExpandListener(object : TreeWillExpandListener {
            override fun treeWillExpand(event: TreeExpansionEvent?) {
                event?.let {
                    if (!it.path.enabled) throw ExpandVetoException(event)
                }
            }

            override fun treeWillCollapse(event: TreeExpansionEvent?) {
                /* nothing happens */
            }
        })
        selectionModel.selectionMode = TreeSelectionModel.SINGLE_TREE_SELECTION
        isRootVisible = false
    }

    final override fun setCellRenderer(x: TreeCellRenderer?) {
        super.setCellRenderer(
            when {
                x is HideableTreeCellRendererWrapper -> x
                x != null -> HideableTreeCellRendererWrapper(x)
                else -> x
            }
        )
    }

    fun setNodeVisible(node: HideableTreeNode<*>, visible: Boolean, setForcedValue: Boolean = false) {
        val expanded = getExpandedDescendants(TreePath((node.parent!!.path)))?.asSequence()
        val selectedPaths = selectionPaths
        if (!setForcedValue) {
            if (node.isVisible == visible) return
            node.isVisible = visible
        } else {
            if (node.forceHide == !visible) return
            node.forceHide = !visible
        }
        (model as HideableTreeModel).nodeStructureChanged(node.parent)
        expanded?.forEach { expandPath(TreePath(it.path)) }
        selectionPaths = selectedPaths
    }

    fun setNodeEnabled(node: HideableTreeNode<*>, enabled: Boolean, setForcedValue: Boolean = false) {
        if (!setForcedValue) {
            if (node.isEnabled == enabled) return
            node.isEnabled = enabled
        } else {
            if (node.forceDisable == !enabled) return
            node.forceDisable = !enabled
        }
        if (!enabled) collapsePath(TreePath(node.path))
        (model as HideableTreeModel).nodeChanged(node)
    }
}

class HideableTreeSelectionModel(
    private val tree: JTree,
    private val delegate: TreeSelectionModel = DefaultTreeSelectionModel()
) : TreeSelectionModel by delegate {

    private fun navigateOverDisabled(path: TreePath?) {
        val currentSelectionRow = leadSelectionRow
        val newRow = tree.getRowForPath(path)
        when (currentSelectionRow) {
            newRow - 1 -> {
                // Tried to move up
                val max = tree.rowCount
                if (currentSelectionRow > max - 3) return
                var i = currentSelectionRow + 2
                var p = tree.getPathForRow(i)
                while (i < max && !p.enabled) {
                    i++
                    if (i < max) p = tree.getPathForRow(i)
                }
                if (p.enabled) tree.selectionPath = p
            }
            newRow + 1 -> {
                // Tried to move down
                if (currentSelectionRow < 2) return
                var i = currentSelectionRow - 2
                var p = tree.getPathForRow(i)
                while (i >= 0 && !p.enabled) {
                    i++
                    if (i >= 0) p = tree.getPathForRow(i)
                }
                if (p.enabled) tree.selectionPath = p
            }
        }
    }

    override fun setSelectionPath(path: TreePath?) {
        if (!path.enabled) {
            navigateOverDisabled(path)
            return
        }
        delegate.selectionPath = path
    }

    override fun setSelectionPaths(pPaths: Array<out TreePath>?) {
        if (pPaths != null && pPaths.size == 1 && !pPaths.first().enabled) {
            navigateOverDisabled(pPaths.first())
        }
        val newPaths = pPaths?.filter { it.enabled }?.toTypedArray()
        if (newPaths?.isEmpty() == true) return
        delegate.selectionPaths = newPaths
    }

    override fun addSelectionPath(path: TreePath?) {
        if (!path.enabled) return
        delegate.addSelectionPath(path)
    }

    override fun addSelectionPaths(paths: Array<out TreePath>?) {
        val newPaths = paths?.filter { it.enabled }?.toTypedArray()
        if (newPaths?.isEmpty() == true) return
        delegate.addSelectionPaths(newPaths)
    }
}

class HideableTreeModel(
    root: TreeNode?,
    asksAllowsChildren: Boolean = false,
    private var isActivatedFilter: Boolean = true
) : DefaultTreeModel(root, asksAllowsChildren) {

    override fun getChild(parent: Any, index: Int): Any {
        if (isActivatedFilter) {
            if (parent is HideableTreeNode<*>) {
                return parent.getChildAt(index, isActivatedFilter)
            }
        }
        return (parent as TreeNode).getChildAt(index)
    }

    override fun getChildCount(parent: Any): Int {
        if (isActivatedFilter) {
            if (parent is HideableTreeNode<*>) {
                return parent.getChildCount(isActivatedFilter)
            }
        }
        return (parent as TreeNode).childCount
    }
}

class HideableTreeNode<T>(
    val value: T,
    allowsChildren: Boolean = true,
) : DefaultMutableTreeNode(value, allowsChildren) {

    override fun getParent(): DefaultMutableTreeNode? {
        return super.getParent() as DefaultMutableTreeNode?
    }

    val realVisible: Boolean
        get() = isVisible && !forceHide
    val realEnabled: Boolean
        get() = isEnabled && !forceDisable

    internal var isVisible: Boolean = true
    internal var forceHide: Boolean = false
    internal var isEnabled: Boolean = true
    internal var forceDisable: Boolean = false

    fun getChildAt(index: Int, filterIsActive: Boolean): TreeNode {
        if (!filterIsActive) return super.getChildAt(index)
        if (children == null) error("node has no children")
        var realIndex = -1
        var visibleIndex = -1
        for (it in children.elements().asIterator()) {
            it as HideableTreeNode<*>
            if (it.realVisible) visibleIndex++
            realIndex++
            if (visibleIndex == index) {
                return children.elementAt(realIndex) as TreeNode
            }
        }
        throw ArrayIndexOutOfBoundsException("index unmatched")
    }

    fun getChildCount(filterIsActive: Boolean): Int {
        if (!filterIsActive) return super.getChildCount()
        if (children == null) return 0
        return children.elements().asSequence().count { (it as HideableTreeNode<*>).realVisible }
    }

    private fun bindVisible(
        node: HideableTreeNode<*>,
        tree: HideableTree,
        visibleBinding: ObservableCondition?,
    ) {
        visibleBinding ?: return
        node.isVisible = visibleBinding.get()
        visibleBinding.onChange {
            onSwingThread {
                tree.setNodeVisible(node, it)
            }
        }
    }

    private fun bindEnabled(
        node: HideableTreeNode<*>,
        tree: HideableTree,
        enabledBinding: ObservableCondition?,
    ) {
        enabledBinding ?: return
        node.isEnabled = enabledBinding.get()
        enabledBinding.onChange {
            onSwingThread {
                tree.setNodeEnabled(node, it)
            }
        }
    }

    fun addWithBinding(
        node: HideableTreeNode<*>,
        tree: HideableTree,
        visibleBinding: ObservableCondition? = null,
        enabledBinding: ObservableCondition? = null
    ) {
        add(node)
        bindVisible(node, tree, visibleBinding)
        bindEnabled(node, tree, enabledBinding)
    }
}

class HideableTreeCellRendererWrapper(var delegate: TreeCellRenderer) : TreeCellRenderer {
    override fun getTreeCellRendererComponent(
        tree: JTree?,
        value: Any?,
        selected: Boolean,
        expanded: Boolean,
        leaf: Boolean,
        row: Int,
        hasFocus: Boolean
    ): Component {
        val comp = delegate.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus)
        comp.isEnabled = (value as? HideableTreeNode<*>)?.realEnabled ?: true
        return comp
    }
}
