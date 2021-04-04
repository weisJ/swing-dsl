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

import com.github.weisj.swingdsl.binding.KPropertyBackedProperty
import com.github.weisj.swingdsl.binding.MutableProperty
import com.github.weisj.swingdsl.binding.PropertyBinding
import com.github.weisj.swingdsl.binding.container.ObservableList
import com.github.weisj.swingdsl.binding.toProperty
import com.github.weisj.swingdsl.layout.Row
import com.github.weisj.swingdsl.text.Text
import com.github.weisj.swingdsl.text.textOf
import javax.swing.JComponent
import kotlin.reflect.KMutableProperty0

class ValueBuilder<T>(
    parentBuilder: ElementBuilder<*, *>,
    private val value: MutableProperty<T>,
    identifier: String,
    private val displayName: Text,
    description: Text?,
    private val valueConstructor: (ContainedElement<Group>, Text, MutableProperty<T>) -> Value<T>
) : ElementBuilder<Group, Value<T>>(parentBuilder, identifier, displayName, description) {

    internal val backingProp: KMutableProperty0<T>? = (value as? KPropertyBackedProperty)?.prop

    override fun build(parent: Group): Value<T> {
        return valueConstructor(buildBaseElement(parent), displayName, value)
    }
}

fun <T> GroupBuilder<*, *>.value(
    value: MutableProperty<T>,
    identifier: String = IDGenerator.create(),
    displayName: Text = textOf(identifier),
    description: Text? = null,
    init: ValueBuilder<T>.() -> Unit = {}
) {
    valueImpl(value, identifier, displayName, description, init, ::DefaultValue)
}

fun <T> GroupBuilder<*, *>.value(
    value: KMutableProperty0<T>,
    identifier: String = value.name,
    displayName: Text = textOf(identifier),
    description: Text? = null,
    init: ValueBuilder<T>.() -> Unit = {}
) {
    value(value.toProperty(), identifier, displayName, description, init)
}

fun GroupBuilder<*, *>.string(
    value: MutableProperty<String>,
    identifier: String = IDGenerator.create(),
    displayName: Text = textOf(identifier),
    description: Text? = null,
    init: ValueBuilder<String>.() -> Unit = {}
) {
    valueImpl(value, identifier, displayName, description, init, ::StringValue)
}

fun GroupBuilder<*, *>.string(
    value: KMutableProperty0<String>,
    identifier: String = value.name,
    displayName: Text = textOf(identifier),
    description: Text? = null,
    init: ValueBuilder<String>.() -> Unit = {}
) {
    string(value.toProperty(), identifier, displayName, description, init)
}

fun GroupBuilder<*, *>.boolean(
    value: MutableProperty<Boolean>,
    identifier: String = IDGenerator.create(),
    displayName: Text = textOf(identifier),
    description: Text? = null,
    init: ValueBuilder<Boolean>.() -> Unit = {}
) {
    valueImpl(value, identifier, displayName, description, init, ::BoolValue)
}

fun GroupBuilder<*, *>.boolean(
    value: KMutableProperty0<Boolean>,
    identifier: String = value.name,
    displayName: Text = textOf(identifier),
    description: Text? = null,
    init: ValueBuilder<Boolean>.() -> Unit = {}
) {
    boolean(value.toProperty(), identifier, displayName, description, init)
}

fun GroupBuilder<*, *>.int(
    value: MutableProperty<Int>,
    identifier: String = IDGenerator.create(),
    displayName: Text = textOf(identifier),
    description: Text? = null,
    low: Int,
    high: Int,
    step: Int = 1,
    init: ValueBuilder<Int>.() -> Unit = {}
) {
    valueImpl(value, identifier, displayName, description, init) { a, b, c ->
        IntValue(a, b, c, low, high, step)
    }
}

fun GroupBuilder<*, *>.int(
    value: KMutableProperty0<Int>,
    identifier: String = value.name,
    displayName: Text = textOf(identifier),
    description: Text? = null,
    low: Int,
    high: Int,
    step: Int = 1,
    init: ValueBuilder<Int>.() -> Unit = {}
) {
    int(value.toProperty(), identifier, displayName, description, low, high, step, init)
}

fun <T : Any> GroupBuilder<*, *>.choice(
    value: MutableProperty<T>,
    identifier: String = IDGenerator.create(),
    displayName: Text = textOf(identifier),
    description: Text? = null,
    choices: ObservableList<T>,
    renderer: (T) -> String = { it.toString() },
    init: ValueBuilder<T>.() -> Unit = {}
) {
    valueImpl(value, identifier, displayName, description, init) { a, b, c ->
        ChoiceValue(a, b, c, choices, renderer)
    }
}

fun <T : Any> GroupBuilder<*, *>.choice(
    value: KMutableProperty0<T>,
    identifier: String = IDGenerator.create(),
    displayName: Text = textOf(identifier),
    description: Text? = null,
    choices: ObservableList<T>,
    renderer: (T) -> String = { it.toString() },
    init: ValueBuilder<T>.() -> Unit = {}
) {
    choice(value.toProperty(), identifier, displayName, description, choices, renderer, init)
}

fun <T : Any> GroupBuilder<*, *>.choice(
    value: MutableProperty<T>,
    identifier: String = IDGenerator.create(),
    displayName: Text = textOf(identifier),
    description: Text? = null,
    choices: List<T>,
    unwrapLimit: Int = 2,
    renderer: (T) -> String = { it.toString() },
    init: ValueBuilder<T>.() -> Unit = {}
) {
    valueImpl(value, identifier, displayName, description, init) { a, b, c ->
        StaticChoiceValue(a, b, c, choices, renderer, unwrapLimit)
    }
}

fun <T : Any> GroupBuilder<*, *>.choice(
    value: KMutableProperty0<T>,
    identifier: String = IDGenerator.create(),
    displayName: Text = textOf(identifier),
    description: Text? = null,
    choices: List<T>,
    unwrapLimit: Int = 2,
    renderer: (T) -> String = { it.toString() },
    init: ValueBuilder<T>.() -> Unit = {}
) {
    choice(value.toProperty(), identifier, displayName, description, choices, unwrapLimit, renderer, init)
}

fun GroupBuilder<*, *>.custom(
    identifier: String = IDGenerator.create(),
    componentProvider: () -> JComponent,
    init: ValueBuilder<Any?>.() -> Unit
) {
    valueImpl(PropertyBinding({ null }, {}), identifier, init = init) { a, b, c ->
        object : DefaultValue<Any?>(a, b, c) {
            private val thisRef: DefaultValue<Any?> = this
            override fun createUI(row: Row, context: UIContext) {
                row.row {
                    bindDisplayStatus(thisRef)
                    component(componentProvider())
                }
            }
        }
    }
}
