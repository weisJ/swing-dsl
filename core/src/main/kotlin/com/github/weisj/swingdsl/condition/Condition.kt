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
package com.github.weisj.swingdsl.condition

import com.github.weisj.swingdsl.binding.ObservableProperty
import com.github.weisj.swingdsl.binding.combine
import com.github.weisj.swingdsl.binding.derive

/**
 * Bound conditions promise to update their value as soon as a new invocation would return a different
 * boolean value than the previous.
 */
typealias ObservableCondition = ObservableProperty<Boolean>

/**
 * Condition with a constant value.
 */
class ConstantCondition(private val value: Boolean) : ObservableCondition {
    override fun onPropertyChange(callback: (Boolean) -> Unit) {
        /* Do nothing. Value never changes */
    }

    override fun get(): Boolean = value
}

internal class ObservablePropertyCondition<T>(
    private val property: ObservableProperty<T>,
    private val checker: (T) -> Boolean
) : ObservableCondition {
    override fun onPropertyChange(callback: (Boolean) -> Unit) {
        property.onPropertyChange { callback(get()) }
    }

    override fun get(): Boolean = checker(property.get())
}

/**
 * Create compound condition which value is true iff both conditions are met.
 */
infix fun ObservableCondition.and(other: ObservableCondition): ObservableCondition = combine(other) { a, b -> a && b }

/**
 * Create compound condition which value is true iff both at least one conditions is met.
 */
infix fun ObservableCondition.or(other: ObservableCondition): ObservableCondition = combine(other) { a, b -> a || b }

/**
 * Inverts the given condition.
 */
operator fun ObservableCondition.not(): ObservableCondition = derive { !it }

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
