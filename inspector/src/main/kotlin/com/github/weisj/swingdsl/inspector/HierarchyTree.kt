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
package com.github.weisj.swingdsl.inspector

import com.github.weisj.swingdsl.dsl.components.HideableTree
import com.github.weisj.swingdsl.dsl.components.HideableTreeModel
import com.github.weisj.swingdsl.dsl.components.HideableTreeNode
import com.github.weisj.swingdsl.dsl.components.addWithBinding
import com.github.weisj.swingdsl.dsl.getWindow
import com.github.weisj.swingdsl.dsl.properties
import com.github.weisj.swingdsl.dsl.visibleBinding
import java.awt.Component
import java.awt.Container
import java.awt.Window
import javax.swing.event.TreeSelectionEvent
import javax.swing.event.TreeSelectionListener
import javax.swing.tree.TreePath

@Suppress("LeakingThis")
internal abstract class HierarchyTree private constructor(
    private val component: Component,
    private var model: HideableTreeModel
) : HideableTree(model), TreeSelectionListener {

    private var currentRoot: Component = component.getWindow()!!
    private var currentClickNode: ClickInfoNode? = null

    constructor(component: Component) : this(component, HideableTreeModel(null))

    init {
        properties {
            client["JTree.lineStyle"] = "none"
        }
        isRootVisible = true
        model.setRoot(ComponentNode(currentRoot, tree = this))
        setCellRenderer(ComponentTreeCellRenderer())
        selectionModel.addTreeSelectionListener(this)
    }

    override fun convertValueToText(
        value: Any,
        selected: Boolean,
        expanded: Boolean,
        leaf: Boolean,
        row: Int,
        hasFocus: Boolean
    ): String {
        if (value is ComponentNode) {
            val pair: Pair<Class<*>, String>? = getClassAndFieldName(value.component)
            return pair?.let { "${it.first.simpleName}.${it.second}" } ?: component.javaClass.name
        }
        return super.convertValueToText(value, selected, expanded, leaf, row, hasFocus)
    }

    override fun valueChanged(e: TreeSelectionEvent) {
        val paths: Array<out TreePath> = selectionPaths ?: return
        val clickInfo = paths
            .asSequence()
            .map { it.lastPathComponent }
            .filterIsInstance<ClickInfoNode>()
            .map { it.info }
            .firstOrNull()
        if (clickInfo != null) {
            onClickInfoChanged(clickInfo)
            return
        }
        val component: Component = paths.asSequence()
            .map { it.lastPathComponent }
            .filterIsInstance<ComponentNode>()
            .map { it.component }
            .firstOrNull() ?: return
        onComponentChanged(component)
    }

    abstract fun onClickInfoChanged(info: ClickInfo)

    abstract fun onComponentChanged(component: Component)

    @OptIn(ExperimentalStdlibApi::class)
    fun reload(newComponent: Component, clickInfo: ClickInfo? = null) {
        val newRoot = newComponent.getWindow()
        if (newRoot != null && newRoot != currentRoot) {
            currentRoot = newRoot
            model.setRoot(ComponentNode(newRoot))
        }
        val newComponentPath = TreePath(
            buildList {
                val isLeaf = (newComponent !is Container || newComponent.componentCount == 0) && clickInfo != null
                add(ComponentNode(newComponent, StubNode(isLeaf)))

                val stubNode = StubNode(isLeaf = false)
                var current: Component? = newComponent.parent
                while (current != null && current != currentRoot) {
                    add(ComponentNode(current, stubNode))
                    current = current.parent
                }
                add(ComponentNode(currentRoot, stubNode))
            }.asReversed().toTypedArray()
        )

        makeVisible(newComponentPath)
        var realPath = getPathForRow(getRowForPath(newComponentPath))
        if (clickInfo != null) {
            val oldClickNode = currentClickNode
            if (oldClickNode != null) {
                val parent = oldClickNode.parent
                if (parent != null) {
                    parent.remove(oldClickNode)
                    model.reload(parent)
                }
            }

            val node = realPath.lastPathComponent as ComponentNode
            val clickNode = ClickInfoNode(clickInfo)
            node.add(clickNode)
            model.reload(node)
            realPath = realPath.pathByAddingChild(clickNode)
            makeVisible(realPath)

            currentClickNode = clickNode
        }
        selectionPath = realPath
    }
}

internal fun getClassAndFieldName(
    component: Component,
    maxDeepness: Int = 1
): Pair<Class<*>, String>? {
    if (maxDeepness == 0) return null
    val parent = component.parent ?: return null
    val fieldAndClass = collectAllFieldsRecursivelyWithClass(parent.javaClass)
        .firstOrNull { (field, _) ->
            runCatching {
                field.isAccessible = true
                field.get(parent) === component
            }.getOrDefault(false)
        }
    if (fieldAndClass != null) {
        val (field, clazz) = fieldAndClass
        return Pair(clazz, field.name)
    }
    return getClassAndFieldName(parent, maxDeepness - 1)
}

internal data class StubNode(
    val isLeaf: Boolean
)

internal class ComponentNode constructor(
    val component: Component,
    private val stubNode: StubNode? = null,
    tree: HierarchyTree? = null
) : HideableTreeNode<Component>(component) {

    internal var text: String? = null

    init {
        if (stubNode == null) {
            createChildren(tree)
        }
    }

    override fun isLeaf(): Boolean {
        return stubNode?.isLeaf ?: super.isLeaf()
    }

    override fun toString(): String = text ?: component.javaClass.name

    override fun equals(other: Any?): Boolean {
        return other is ComponentNode && other.component === component
    }

    override fun hashCode(): Int = component.hashCode()
}

private fun ComponentNode.createChildren(tree: HierarchyTree?) {
    tree ?: return
    if (component is Container) {
        for (child in component.components) {
            addWithBinding(
                ComponentNode(child, tree = tree), tree,
                visibleBinding = child.visibleBinding()
            )
        }
    }
    if (component is Window) {
        for (child in component.ownedWindows) {
            if (child is InspectorWindow) continue
            addWithBinding(
                ComponentNode(child, tree = tree), tree,
                visibleBinding = child.visibleBinding()
            )
        }
    }
}

internal class ClickInfoNode(val info: ClickInfo) : HideableTreeNode<Any?>(null, allowsChildren = false) {

    override fun toString(): String {
        return "Clicked Info"
    }

    override fun isLeaf(): Boolean {
        return true
    }

    override fun hashCode(): Int = info.hashCode()
    override fun equals(other: Any?): Boolean {
        return other is ClickInfoNode && info == other.info
    }
}
