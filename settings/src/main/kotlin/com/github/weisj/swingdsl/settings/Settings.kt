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
import com.github.weisj.swingdsl.binding.ObservableMutableProperty
import com.github.weisj.swingdsl.binding.observableProperty
import com.github.weisj.swingdsl.condition.ObservableCondition
import com.github.weisj.swingdsl.condition.conditionOf
import com.github.weisj.swingdsl.layout.CellBuilder
import com.github.weisj.swingdsl.layout.Row
import com.github.weisj.swingdsl.text.Text
import com.github.weisj.swingdsl.text.isConstantNullOrEmpty
import java.awt.Color

@DslMarker
annotation class SettingsMarker

private const val INDENT = "  "

private fun String.indentFormat(level: Int = 1): String {
    val indentation = INDENT.repeat(level)
    return indentation + this.replace("\n", "\n$indentation")
}

private fun <T : Any> List<T>.joinIndented(title: String): String {
    return if (this.isEmpty()) "" else {
        buildString {
            append('\n')
            append(title)
            append(":\n")
            for (it in this@joinIndented) {
                append(it.toString().indentFormat())
                append("\n")
            }
        }.indentFormat().trimEnd()
    }
}

interface DisplayState {
    val enabled: ObservableCondition
    val visible: ObservableCondition
}

@SettingsMarker
interface Element {
    val parent: Element?

    val identifier: String
    val displayName: Text?
    val description: Text?

    // Annotating this method with @JvmDefault, will make it such
    // that it is dispatched on the implementing class instead of a
    // possible delegate. Hence in this context 'this' actually refers
    // to the desired object
    @JvmDefault
    fun getPath(): List<Element> {
        val path = mutableListOf(this)
        var p: Element? = parent
        while (p != null) {
            path.add(0, p)
            p = p.parent
        }
        return path
    }

    val displayState: DisplayState
}

interface TopLevel

interface ContainerElement : Element

interface ContainedElement<out T : ContainerElement?> : Element {
    override val parent: T
}

interface Category : ContainerElement, UIParticipant {
    val groups: List<TopLevelGroup>
    val subCategories: List<Category>
    override val displayName: Text
}

interface TopLevelCategory : ContainedElement<Category?>, Category, TopLevel
interface SubCategory : ContainedElement<Category>, Category

interface Group : ContainerElement, UIParticipant {
    val subGroups: List<SubGroup>
    val values: List<Value<*>>
}

interface TopLevelGroup : ContainedElement<Category>, Group, TopLevel
interface SubGroup : ContainedElement<Group>, Group

interface Value<T> : ContainedElement<Group>, UIParticipant {
    override val parent: Group
    override val displayName: Text

    val value: MutableProperty<T>
    val preview: ObservableMutableProperty<T>
}

fun Element.getNearestCategory(): Category? {
    if (this is Category) return this
    return parent?.getNearestCategory()
}

data class DefaultDisplayState(
    override var enabled: ObservableCondition = conditionOf(true),
    var originalEnabled: ObservableCondition = enabled,
    override var visible: ObservableCondition = conditionOf(true),
    var originalVisible: ObservableCondition = visible,
) : DisplayState

class DefaultElement<T : ContainerElement?>(
    override val parent: T,
    override val identifier: String,
    override val displayName: Text? = null,
    override val description: Text? = null,
    override val displayState: DisplayState = DefaultDisplayState()
) : ContainedElement<T> {

    override fun toString(): String = toString(true)

    fun toString(short: Boolean): String {
        if (short) return "$identifier $displayName"
        val descriptionString = description?.let { ", description = ${it.get()}" } ?: ""
        val displayNameString = displayName?.get() ?: ""
        val nameEqualsIdentifier = displayNameString == identifier
        return when {
            nameEqualsIdentifier && descriptionString.isEmpty() -> "[$identifier]"
            nameEqualsIdentifier -> "[$identifier]($descriptionString)"
            !nameEqualsIdentifier && descriptionString.isEmpty() -> "[$identifier](displayName = $displayNameString)"
            else -> "[$identifier](displayName = $displayNameString}$descriptionString)"
        }
    }
}

sealed class DefaultCategory<T : Category?>(
    private val element: ContainedElement<T>,
    override val displayName: Text,
) : Category, ContainedElement<T> by element {

    override lateinit var subCategories: List<SubCategory>
    override lateinit var groups: List<TopLevelGroup>

    override fun toString(): String = toString(true)

    fun toString(short: Boolean): String {
        if (short) return element.toString()
        val grpStr = groups.joinIndented("groups")
        val catStr = subCategories.joinIndented("subcategories")
        val end = if (grpStr.isEmpty() && catStr.isEmpty()) "" else "\n"
        return "$element {$grpStr$catStr$end}"
    }

    override fun createUI(row: Row, context: UIContext) {
        row.addCategory(this, context)
    }
}

class DefaultSubCategory(
    element: ContainedElement<Category>,
    displayName: Text,
) : DefaultCategory<Category>(element, displayName), SubCategory

class DefaultTopLevelCategory(
    element: ContainedElement<Category?>,
    displayName: Text,
) : DefaultCategory<Category?>(element, displayName), TopLevelCategory

sealed class DefaultGroup<T : ContainerElement>(
    private val element: ContainedElement<T>,
) : Group, ContainedElement<T> by element {
    override lateinit var subGroups: List<SubGroup>
    override lateinit var values: List<Value<*>>

    override fun toString(): String = toString(true)

    fun toString(short: Boolean): String {
        if (short) return element.toString()
        val valStr = values.joinIndented("values")
        val grpStr = subGroups.joinIndented("subgroups")
        val end = if (grpStr.isEmpty() && valStr.isEmpty()) "" else "\n"
        return "$element {$valStr$grpStr$end}"
    }

    override fun createUI(row: Row, context: UIContext) {
        row.addGroup(this, context)
    }
}

class DefaultSubGroup(
    element: ContainedElement<Group>,
) : DefaultGroup<Group>(element), SubGroup

class DefaultTopLevelGroup(
    element: ContainedElement<Category>,
) : DefaultGroup<Category>(element), TopLevelGroup

open class DefaultValue<T>(
    private val element: ContainedElement<Group>,
    override val displayName: Text,
    final override val value: MutableProperty<T>,
) : Value<T>, ContainedElement<Group> by element {
    override val preview = observableProperty(value.get())

    override fun toString(): String {
        return "$element ${value.get()}"
    }

    open fun createValueUI(row: Row, context: UIContext): CellBuilder<*> {
        return row.label("Not Implemented").applyToComponent {
            foreground = Color.RED
        }
    }

    open val showTitle
        get() = !displayName.isConstantNullOrEmpty()
    open val showDescription = true

    override fun createUI(row: Row, context: UIContext) {
        row.addValue(this, context)
    }
}
