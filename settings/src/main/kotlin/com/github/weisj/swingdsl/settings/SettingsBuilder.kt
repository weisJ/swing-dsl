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

import com.github.weisj.swingdsl.core.binding.ObservableProperty
import com.github.weisj.swingdsl.core.condition.ObservableCondition
import com.github.weisj.swingdsl.core.condition.and
import com.github.weisj.swingdsl.core.condition.conditionOf
import com.github.weisj.swingdsl.core.condition.isFalse
import com.github.weisj.swingdsl.core.condition.isTrue
import com.github.weisj.swingdsl.core.text.Text
import com.github.weisj.swingdsl.core.text.textOf
import com.github.weisj.swingdsl.dsl.BuilderWithEnabledProperty
import java.util.*
import kotlin.reflect.KMutableProperty0

object IDGenerator {
    fun create(): String = UUID.randomUUID().toString()
}

@SettingsMarker
abstract class ElementBuilder<Parent : ContainerElement?, Type : ContainedElement<Parent>> internal constructor(
    private val parentBuilder: ElementBuilder<*, *>?,
    private val identifier: String,
    private val displayName: Text?,
    private var description: Text?
) : BuilderWithEnabledProperty<Unit> {
    private val displayState: DefaultDisplayState = DefaultDisplayState()
    internal lateinit var element: Type

    override fun visible(isVisible: Boolean) {
        visibleIf(conditionOf(isVisible))
    }

    override fun enabled(isEnabled: Boolean) {
        enableIf(conditionOf(isEnabled))
    }

    override fun visibleIf(predicate: ObservableCondition) {
        displayState.visible = predicate
    }

    override fun enableIf(predicate: ObservableCondition) {
        displayState.enabled = predicate
    }

    fun buildBaseElement(parent: Parent): ContainedElement<Parent> {
        displayState.originalEnabled = displayState.enabled
        displayState.originalVisible = displayState.visible
        if (parent != null) {
            displayState.visible = displayState.visible and parent.displayState.visible
            displayState.enabled = displayState.enabled and parent.displayState.enabled
        }
        return DefaultElement(parent, identifier, displayName, description, displayState)
    }

    fun doBuild(parent: Parent): Type {
        element = build(parent)
        return element
    }

    abstract fun build(parent: Parent): Type

    private fun ambiguous(prop: KMutableProperty0<*>, candidates: List<ValueBuilder<*>>): Nothing {
        error(
            buildString {
                append("Query for property $prop is ambiguous. There are ${candidates.size} candidates.")
                candidates.forEach {
                    append("\n")
                    append(it)
                }
            }
        )
    }

    private fun validateCandidates(prop: KMutableProperty0<*>, candidates: List<ValueBuilder<*>>): ValueBuilder<*>? {
        if (candidates.size > 1) ambiguous(prop, candidates)
        return candidates.firstOrNull()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> findValue(prop: KMutableProperty0<T>, searchSubGroups: Boolean): ValueBuilder<T>? {
        return when (this) {
            is GroupBuilder<*, *> -> {
                // First search direct children.
                var result = validateCandidates(prop, values.filter { it.backingProp == prop })
                if (result != null) return result as ValueBuilder<T>

                // Then go up one level. Don't include subgroups at first.
                result = parentBuilder!!.findValue(prop, false)
                if (result != null) return result

                // Lastly further down.
                if (searchSubGroups) {
                    result = validateCandidates(prop, subGroups.mapNotNull { findValue(prop, true) })
                    if (result != null) return result as ValueBuilder<T>
                }
                return null
            }
            is CategoryBuilder<*, *> -> null // Crossing category boundaries isn't allowed.
            else -> parentBuilder?.findValue(prop, searchSubGroups)
        }
    }

    // Binding queries
    fun <T> KMutableProperty0<T>.binding(): ObservableProperty<T> {
        return PreviewObservable {
            findValue(this@binding, false)?.element ?: error("Could not find property backed by ${this@binding}")
        }
    }

    fun KMutableProperty0<Boolean>.isTrue(): ObservableCondition = this.binding().isTrue()
    fun KMutableProperty0<Boolean>.isFalse(): ObservableCondition = this.binding().isFalse()
}

fun category(
    identifier: String,
    name: Text = textOf(identifier),
    description: Text? = null,
    init: TopLevelCategoryBuilder.() -> Unit = {}
): Category {
    val builder = TopLevelCategoryBuilder(null, identifier, name, description)
    builder.init()
    return builder.doBuild(null)
}

private class PreviewObservable<T>(resolver: () -> Value<T>) : ObservableProperty<T> {

    private val valueElement: Value<T> by lazy { resolver() }

    override fun get(): T = valueElement.preview.get()

    override fun onChange(observeKey: Any?, callback: (T) -> Unit) {
        valueElement.preview.onChange(observeKey, callback)
    }

    override fun removeCallback(observeKey: Any?) {
        valueElement.preview.removeCallback(observeKey)
    }
}
