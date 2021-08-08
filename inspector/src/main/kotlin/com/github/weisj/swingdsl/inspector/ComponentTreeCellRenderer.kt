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

import com.github.weisj.swingdsl.dsl.style.UIFactory
import com.github.weisj.swingdsl.util.asCSSProperty
import com.github.weisj.swingdsl.util.escapeXmlEntities
import java.awt.Component
import javax.swing.JTree
import javax.swing.tree.DefaultTreeCellRenderer

internal class ComponentTreeCellRenderer : DefaultTreeCellRenderer() {

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
        when (value) {
            is ComponentNode -> {
                val component = value.component
                isEnabled = component.isVisible
                value.text = buildString {
                    append("<html>")
                    append(getComponentName(component).escapeXmlEntities())
                    getClassAndFieldName(component)?.let { (theClass, fieldName) ->
                        append("($fieldName@${theClass.simpleName})".escapeXmlEntities())
                    }
                    append(": <small")
                    if (!sel || !hasFocus) {
                        append(" style=\"")
                        append(UIFactory.secondaryTextForeground.asCSSProperty())
                        append('"')
                    }
                    append('>')
                    append(component.bounds.render())
                    if (component.isOpaque) append(", opaque")
                    if (component.isDoubleBuffered) append(", double-buffered")
                    append("</small></html>")
                }
                icon = findIconFor(component)
                disabledIcon = icon
                text = value.text
            }
            is ClickInfoNode -> {
                text = value.toString()
                icon = getClickInfoIcon()
            }
        }
        return this
    }
}

internal val Any?.containingClassName: String
    get() = this?.javaClass?.let {
        if (it.isAnonymousClass) it.superclass?.simpleName else it.simpleName
    } ?: ""

fun getComponentName(component: Component?): String {
    var name: String = component.containingClassName
    val componentName: String = component?.name ?: ""
    if (componentName.isNotEmpty()) {
        name += " \"$componentName\""
    }
    return name
}
