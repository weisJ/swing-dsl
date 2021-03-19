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

import com.github.weisj.swingdsl.text.textOf
import com.github.weisj.swingdsl.text.textOfNullable
import java.awt.event.ActionEvent
import javax.swing.ButtonGroup
import javax.swing.JButton
import javax.swing.JCheckBox
import javax.swing.JComponent
import javax.swing.JRadioButton
import kotlin.reflect.KMutableProperty0

fun BaseBuilder.withButtonGroup(title: String?, buttonGroup: ButtonGroup, body: () -> Unit) =
    withButtonGroup(textOfNullable(title), buttonGroup, body)

fun BaseBuilder.buttonGroup(title: String? = null, init: () -> Unit) =
    buttonGroup(textOfNullable(title), init)

fun RowBuilder.row(label: String?, separated: Boolean = false, init: Row.() -> Unit): Row {
    return row(textOfNullable(label), separated, init)
}

fun RowBuilder.titledRow(title: String, init: Row.() -> Unit): Row = titledRow(textOf(title), init)

fun RowBuilder.commentRow(text: String) = commentRow(textOf(text))

fun Cell.button(text: String, actionListener: (event: ActionEvent) -> Unit): CellBuilder<JButton> =
    button(textOf(text), actionListener)

@JvmOverloads
fun Cell.checkBox(
    text: String,
    isSelected: Boolean = false,
    comment: String? = null,
    actionListener: (event: ActionEvent, component: JCheckBox) -> Unit
): CellBuilder<JCheckBox> = checkBox(textOf(text), isSelected, textOfNullable(comment), actionListener)

@JvmOverloads
fun Cell.checkBox(
    text: String,
    isSelected: Boolean = false,
    comment: String? = null
): CellBuilder<JCheckBox> = checkBox(textOf(text), isSelected, textOfNullable(comment))

fun Cell.checkBox(
    text: String,
    prop: KMutableProperty0<Boolean>,
    comment: String? = null
): CellBuilder<JCheckBox> {
    return checkBox(textOf(text), prop, textOfNullable(comment))
}

@JvmOverloads
fun Cell.checkBox(
    text: String,
    getter: () -> Boolean,
    setter: (Boolean) -> Unit,
    comment: String? = null
): CellBuilder<JCheckBox> {
    return checkBox(textOf(text), getter, setter, textOfNullable(comment))
}

fun Cell.radioButton(text: String, comment: String? = null): CellBuilder<JRadioButton> =
    radioButton(textOf(text), textOfNullable(comment))

fun Cell.radioButton(
    text: String,
    getter: () -> Boolean,
    setter: (Boolean) -> Unit,
    comment: String? = null
): CellBuilder<JRadioButton> = radioButton(textOf(text), getter, setter, textOfNullable(comment))

fun Cell.radioButton(
    text: String,
    prop: KMutableProperty0<Boolean>,
    comment: String? = null
): CellBuilder<JRadioButton> = radioButton(textOf(text), prop, textOfNullable(comment))

fun <T : JComponent> CellBuilder<T>.comment(
    text: String,
    maxLineLength: Int = 70,
    forComponent: Boolean = false
): CellBuilder<T> = comment(textOf(text), maxLineLength, forComponent)
