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
package com.github.weisj.swingdsl

import com.github.weisj.swingdsl.binding.container.ObservableList
import java.awt.Component
import java.awt.Insets
import java.awt.Window
import java.awt.event.ActionEvent
import javax.swing.AbstractAction
import javax.swing.Action
import javax.swing.ActionMap
import javax.swing.DefaultListModel
import javax.swing.InputMap
import javax.swing.JComponent
import javax.swing.KeyStroke
import javax.swing.ListModel
import javax.swing.SwingUtilities
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener
import javax.swing.event.ListDataListener
import javax.swing.text.JTextComponent

fun Int.toKeyStroke(): KeyStroke = KeyStroke.getKeyStroke(this, 0)

fun JComponent.on(vararg keyCode: KeyStroke, action: (ActionEvent?) -> Unit) {
    bindEvent(Any(), *keyCode, action = action)
}

fun JComponent.bindEvent(actionKey: Any, vararg keyCode: KeyStroke, action: (ActionEvent?) -> Unit) {
    keyCode.forEach { inputMap[it] = actionKey }
    actionMap[actionKey] = createAction(actionKey, action)
}

operator fun InputMap.set(keyStroke: KeyStroke, actionObj: Any) = put(keyStroke, actionObj)
operator fun ActionMap.set(actionObj: Any, action: Action) = put(actionObj, action)

fun createAction(name: Any? = null, action: (ActionEvent?) -> Unit): Action =
    object : AbstractAction(name?.toString()) {
        override fun actionPerformed(e: ActionEvent?) = action(e)
    }

val Insets.width
    get() = left + right

val Insets.height
    get() = top + bottom

fun Component?.getWindow(): Window? {
    this ?: return null
    if (this is Window) return this
    var parent = this.parent
    while (parent !is Window && parent != null) {
        parent = parent.parent
    }
    return parent as? Window
}

fun invokeLater(action: () -> Unit) = SwingUtilities.invokeLater(action)

fun onSwingThread(action: () -> Unit) {
    if (SwingUtilities.isEventDispatchThread()) {
        action()
    } else {
        invokeLater(action)
    }
}

fun JTextComponent.addDocumentChangeListener(listener: (DocumentEvent?) -> Unit) =
    document.addDocumentListener(DocumentChangeListener(listener))

fun <T> listModelOf(list: ObservableList<T>) = ObservableListModel(list)

operator fun <T> ListModel<T>.get(index: Int): T = getElementAt(index)
fun <T> DefaultListModel<T>.add(value: T) {
    add(size(), value)
}

inline fun <reified T> Component.getProperty(key: Any): T? {
    if (this !is JComponent) return null
    return getClientProperty(key) as? T
}

private class DocumentChangeListener(val onChange: (DocumentEvent?) -> Unit) : DocumentListener {

    override fun insertUpdate(e: DocumentEvent?) = onChange(e)

    override fun removeUpdate(e: DocumentEvent?) = onChange(e)

    override fun changedUpdate(e: DocumentEvent?) = onChange(e)
}
