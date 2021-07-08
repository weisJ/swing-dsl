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
package com.github.weisj.swingdsl.components

import com.github.weisj.swingdsl.core.condition.ObservableCondition
import com.github.weisj.swingdsl.onSwingThread

private fun <T> HideableTreeNode<T>.bindVisible(
    node: HideableTreeNode<*>,
    tree: HideableTree,
    visibleBinding: ObservableCondition?,
) {
    visibleBinding ?: return
    node.isVisibleBase = visibleBinding.get()
    visibleBinding.onChange {
        onSwingThread {
            tree.setNodeVisible(node, it)
        }
    }
}

private fun <T> HideableTreeNode<T>.bindEnabled(
    node: HideableTreeNode<*>,
    tree: HideableTree,
    enabledBinding: ObservableCondition?,
) {
    enabledBinding ?: return
    node.isEnabledBase = enabledBinding.get()
    enabledBinding.onChange {
        onSwingThread {
            tree.setNodeEnabled(node, it)
        }
    }
}

fun <T> HideableTreeNode<T>.addWithBinding(
    node: HideableTreeNode<*>,
    tree: HideableTree,
    visibleBinding: ObservableCondition? = null,
    enabledBinding: ObservableCondition? = null
) {
    add(node)
    bindVisible(node, tree, visibleBinding)
    bindEnabled(node, tree, enabledBinding)
}
