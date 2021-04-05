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

import com.github.weisj.swingdsl.binding.MutableProperty
import com.github.weisj.swingdsl.text.Text

abstract class GroupBuilder<C : ContainerElement, T> internal constructor(
    parentBuilder: ElementBuilder<*, *>?,
    identifier: String = IDGenerator.create(),
    displayName: Text?,
    description: Text?
) : ElementBuilder<C, T>(parentBuilder, identifier, displayName, description)
        where T : Group, T : ContainedElement<C> {

    internal val subGroups = mutableListOf<SubGroupBuilder>()
    internal val values = mutableListOf<ValueBuilder<*>>()

    fun <Impl : DefaultGroup<C>> initImpl(group: Impl): Impl {
        group.subGroups = subGroups.map { it.doBuild(group) }
        group.values = values.map { it.doBuild(group) }
        return group
    }

    fun group(
        identifier: String = IDGenerator.create(),
        name: Text? = null,
        description: Text? = null,
        init: SubGroupBuilder.() -> Unit = {}
    ) {
        subGroups.add(SubGroupBuilder(this, identifier, name, description).also(init))
    }

    internal fun <T> valueImpl(
        value: MutableProperty<T>,
        identifier: String = IDGenerator.create(),
        name: Text,
        description: Text? = null,
        init: ValueBuilder<T>.() -> Unit = {},
        valueConstructor: (ContainedElement<Group>, Text, MutableProperty<T>) -> Value<T>
    ) {
        values.add(ValueBuilder(this, value, identifier, name, description, valueConstructor).also(init))
    }
}

class TopLevelGroupBuilder(
    parentBuilder: ElementBuilder<*, *>,
    identifier: String,
    displayName: Text?,
    description: Text?
) : GroupBuilder<Category, TopLevelGroup>(parentBuilder, identifier, displayName, description) {

    override fun build(parent: Category): TopLevelGroup =
        initImpl(DefaultTopLevelGroup(buildBaseElement(parent)))
}

class SubGroupBuilder(
    parentBuilder: ElementBuilder<*, *>,
    identifier: String,
    displayName: Text?,
    description: Text?
) : GroupBuilder<Group, SubGroup>(parentBuilder, identifier, displayName, description) {

    override fun build(parent: Group): SubGroup =
        initImpl(DefaultSubGroup(buildBaseElement(parent)))
}
