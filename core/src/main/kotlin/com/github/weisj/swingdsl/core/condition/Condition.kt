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
package com.github.weisj.swingdsl.core.condition

import com.github.weisj.swingdsl.core.binding.ObservableProperty
import com.github.weisj.swingdsl.core.binding.combine
import com.github.weisj.swingdsl.core.binding.derive

/**
 * Bound conditions promise to update their value as soon as a new invocation would return a different
 * boolean value than the previous.
 */
typealias ObservableCondition = ObservableProperty<Boolean>

/**
 * Condition with a constant value.
 */
class ConstantCondition(private val value: Boolean) : ObservableCondition {
    override fun onChange(observeKey: Any?, callback: (Boolean) -> Unit) {
        /* Do nothing. Value never changes */
    }

    override fun removeCallback(observeKey: Any?) {
        /* Never registered any callbacks */
    }

    override fun get(): Boolean = value

    override fun toString(): String {
        return "Condition($value)"
    }
}

internal class ObservablePropertyCondition<T>(
    private val property: ObservableProperty<T>,
    private val checker: (T) -> Boolean
) : ObservableCondition {
    override fun onChange(observeKey: Any?, callback: (Boolean) -> Unit) {
        property.onChange(observeKey) { callback(get()) }
    }

    override fun removeCallback(observeKey: Any?) {
        return property.removeCallback(observeKey)
    }

    override fun get(): Boolean = checker(property.get())

    override fun toString(): String {
        return "PropertyCondition($property)"
    }
}

private fun ObservableCondition.isConstant(value: Boolean): Boolean {
    return this is ConstantCondition && this.get() == value
}

/**
 * Create compound condition which value is true iff both conditions are met.
 */
infix fun ObservableCondition.and(other: ObservableCondition): ObservableCondition {
    return when {
        this.isConstant(true) -> other
        this.isConstant(false) -> this
        other.isConstant(false) -> other
        other.isConstant(true) -> this
        else -> combine(other) { a, b -> a && b }
    }
}

/**
 * Create compound condition which value is true iff both at least one conditions is met.
 */
infix fun ObservableCondition.or(other: ObservableCondition): ObservableCondition {
    return when {
        this.isConstant(true) -> this
        this.isConstant(false) -> other
        other.isConstant(false) -> this
        other.isConstant(true) -> other
        else -> combine(other) { a, b -> a || b }
    }
}

/**
 * Inverts the given condition.
 */
operator fun ObservableCondition.not(): ObservableCondition {
    return when {
        this.isConstant(true) -> conditionOf(false)
        this.isConstant(false) -> conditionOf(true)
        else -> derive { !it }
    }
}

/**
 * Create constant value condition.
 */
fun conditionOf(bool: Boolean): ObservableCondition = ConstantCondition(bool)

infix fun <T> ObservableProperty<T>.isEqualTo(value: T): ObservableCondition =
    ObservablePropertyCondition(this) { it == value }

infix fun <T> ObservableProperty<T>.isEqualTo(valueSupplier: () -> T): ObservableCondition =
    ObservablePropertyCondition(this) { it == valueSupplier() }

infix fun <T> ObservableProperty<T>.isEqualTo(other: ObservableProperty<T>): ObservableCondition =
    combine(other) { a, b -> a == b }

fun ObservableProperty<Boolean>.isTrue() = this isEqualTo true
fun ObservableProperty<Boolean>.isFalse() = this isEqualTo false
