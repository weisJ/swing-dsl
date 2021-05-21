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

import com.github.weisj.swingdsl.binding.bind
import com.github.weisj.swingdsl.binding.container.ObservableList
import com.github.weisj.swingdsl.binding.onChange
import com.github.weisj.swingdsl.condition.ObservableCondition
import com.github.weisj.swingdsl.model.CollectionComboBoxModel
import com.github.weisj.swingdsl.model.CollectionListModel
import com.github.weisj.swingdsl.model.ObservableComboBoxModel
import com.github.weisj.swingdsl.model.ObservableListModel
import java.awt.Component
import java.awt.Insets
import java.awt.MouseInfo
import java.awt.Point
import java.awt.Window
import java.awt.event.ActionEvent
import javax.swing.AbstractAction
import javax.swing.Action
import javax.swing.ActionMap
import javax.swing.ComboBoxModel
import javax.swing.DefaultListModel
import javax.swing.InputMap
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.KeyStroke
import javax.swing.ListModel
import javax.swing.ListSelectionModel
import javax.swing.SwingUtilities
import javax.swing.event.AncestorEvent
import javax.swing.event.AncestorListener
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener
import javax.swing.text.JTextComponent

enum class FocusState(val magicValue: Int) {
    FOCUSED(JComponent.WHEN_FOCUSED),
    ANCESTOR_OF_FOCUSED_COMPONENT(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT),
    IN_FOCUSED_WINDOW(JComponent.WHEN_IN_FOCUSED_WINDOW)
}

fun Int.toKeyStroke(modifiers: Int = 0): KeyStroke = KeyStroke.getKeyStroke(this, modifiers)
fun String.toKeyStroke(): KeyStroke = KeyStroke.getKeyStroke(this)

fun <T : JComponent> T.on(
    vararg keyCode: KeyStroke,
    focusState: FocusState = FocusState.FOCUSED,
    action: T.(ActionEvent?) -> Unit
) {
    bindEvent(Any(), *keyCode, focusState = focusState, action = { this.action((it)) })
}

fun JComponent.bindEvent(
    actionKey: Any,
    vararg keyCode: KeyStroke,
    focusState: FocusState = FocusState.FOCUSED,
    action: (ActionEvent?) -> Unit
) {
    keyCode.forEach { getInputMap(focusState.magicValue)[it] = actionKey }
    actionMap[actionKey] = createAction(actionKey, action)
}

fun createAction(name: Any? = null, action: (ActionEvent?) -> Unit): Action =
    object : AbstractAction(name?.toString()) {
        override fun actionPerformed(e: ActionEvent?) = action(e)
    }

operator fun InputMap.set(keyStroke: KeyStroke, actionObj: Any) = put(keyStroke, actionObj)
operator fun ActionMap.set(actionObj: Any, action: Action) = put(actionObj, action)

fun JComponent.mouseLocation(): Point? {
    return MouseInfo.getPointerInfo()?.location?.let {
        SwingUtilities.convertPointFromScreen(it, this@mouseLocation)
        it
    }
}

fun JComponent.bindVisible(condition: ObservableCondition) {
    condition.bind { isVisible = it }
}

fun JComponent.bindEnabled(condition: ObservableCondition) {
    condition.bind { isEnabled = it }
}

fun JButton.makeDefaultButton() {
    check(isDefaultCapable) { "The button is not default capable." }
    val root = rootPane
    if (root != null) {
        root.defaultButton = this
    } else {
        addAncestorListener(object : AncestorListener {
            override fun ancestorAdded(event: AncestorEvent?) {
                removeAncestorListener(this)
                rootPane!!.defaultButton = this@makeDefaultButton
                this@makeDefaultButton.requestFocusInWindow()
            }

            override fun ancestorRemoved(event: AncestorEvent?) {}

            override fun ancestorMoved(event: AncestorEvent?) {}
        })
    }
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

val ListSelectionModel.selectedIndicesArray: IntArray
    get() {
        val iMin = minSelectionIndex
        val iMax = maxSelectionIndex

        if ((iMin < 0) || (iMax < 0)) {
            return IntArray(size = 0)
        }

        val rvTmp = IntArray(size = 1 + (iMax - iMin))
        var n = 0
        for (i in iMin..iMax) {
            if (isSelectedIndex(i)) {
                rvTmp[n++] = i
            }
        }
        val rv = IntArray(n)
        rvTmp.copyInto(rv, 0, 0, n)
        return rv
    }

fun JTextComponent.addDocumentChangeListener(listener: (DocumentEvent?) -> Unit) =
    document.addDocumentListener(DocumentChangeListener(listener))

fun <T> listModelOf(list: ObservableList<T>): ListModel<T> = ObservableListModel(list)
fun <T> listModelOf(list: List<T>): ListModel<T> = CollectionListModel(list)

fun <T : Any> comboBoxModelOf(list: ObservableList<T>): ComboBoxModel<T> = ObservableComboBoxModel(list)
fun <T : Any> comboBoxModelOf(list: List<T>): ComboBoxModel<T> = CollectionComboBoxModel(list)

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
