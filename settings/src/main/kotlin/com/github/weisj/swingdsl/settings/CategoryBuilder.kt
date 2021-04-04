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

import com.github.weisj.swingdsl.text.Text
import com.github.weisj.swingdsl.text.textOf

abstract class CategoryBuilder<C : Category?, T> internal constructor(
    parentBuilder: ElementBuilder<*, *>?,
    identifier: String,
    internal val displayName: Text,
    description: Text? = null
) : ElementBuilder<C, T>(parentBuilder, identifier, displayName, description)
        where T : Category, T : ContainedElement<C> {

    private val subContainers = mutableListOf<SubCategoryBuilder>()
    private val groups = mutableListOf<TopLevelGroupBuilder>()

    fun <Impl : DefaultCategory<C>> initImpl(category: Impl): Impl {
        category.subCategories = subContainers.map { it.doBuild(category) }
        category.groups = groups.map { it.doBuild(category) }
        return category
    }

    fun category(
        identifier: String,
        displayName: Text = textOf(identifier),
        description: Text? = null,
        init: CategoryBuilder<Category, SubCategory>.() -> Unit
    ) {
        subContainers.add(SubCategoryBuilder(this, identifier, displayName, description).also(init))
    }

    fun group(
        identifier: String = IDGenerator.create(),
        displayName: Text? = null,
        description: Text? = null,
        init: TopLevelGroupBuilder.() -> Unit
    ) {
        groups.add(TopLevelGroupBuilder(this, identifier, displayName, description).also(init))
    }
}

class TopLevelCategoryBuilder(
    parentBuilder: ElementBuilder<*, *>?,
    identifier: String,
    displayName: Text,
    description: Text?
) : CategoryBuilder<Category?, TopLevelCategory>(parentBuilder, identifier, displayName, description) {

    override fun build(parent: Category?): TopLevelCategory {
        assert(parent == null)
        return initImpl(DefaultTopLevelCategory(buildBaseElement(parent), displayName))
    }
}

class SubCategoryBuilder(
    parentBuilder: ElementBuilder<*, *>,
    identifier: String,
    displayName: Text,
    description: Text?
) : CategoryBuilder<Category, SubCategory>(parentBuilder, identifier, displayName, description) {

    override fun build(parent: Category): SubCategory =
        initImpl(DefaultSubCategory(buildBaseElement(parent), displayName))
}
