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

import com.github.weisj.swingdsl.binding.BoundProperty
import com.github.weisj.swingdsl.binding.combine
import com.github.weisj.swingdsl.binding.derive

/**
 * Bound conditions promise to update their value as soon as a new invocation would return a different
 * boolean value than the previous.
 */
typealias BoundCondition = BoundProperty<Boolean>

/**
 * Condition with a constant value.
 */
class ConstantCondition(private val value: Boolean) : BoundCondition {
    override fun onPropertyChange(callback: (Boolean) -> Unit) {
        /* Do nothing. Value never changes */
    }

    override fun get(): Boolean = value
}

internal class BoundPropertyCondition<T>(
    private val property: BoundProperty<T>,
    private val checker: (T) -> Boolean
) : BoundCondition {
    override fun onPropertyChange(callback: (Boolean) -> Unit) {
        property.onPropertyChange { callback(get()) }
    }

    override fun get(): Boolean = checker(property.get())
}

/**
 * Create compound condition which value is true iff both conditions are met.
 */
infix fun BoundCondition.and(other: BoundCondition): BoundCondition = combine(other) { a, b -> a && b }

/**
 * Create compound condition which value is true iff both at least one conditions is met.
 */
infix fun BoundCondition.or(other: BoundCondition): BoundCondition = combine(other) { a, b -> a || b }

/**
 * Inverts the given condition.
 */
operator fun BoundCondition.not(): BoundCondition = derive { !it }

/**
 * Create constant value condition.
 */
fun conditionOf(bool: Boolean): BoundCondition = ConstantCondition(bool)

infix fun <T> BoundProperty<T>.isEqualTo(value: T): BoundCondition =
    BoundPropertyCondition(this) { it == value }

infix fun <T> BoundProperty<T>.isEqualTo(valueSupplier: () -> T): BoundCondition =
    BoundPropertyCondition(this) { it == valueSupplier() }

infix fun <T> BoundProperty<T>.isEqualTo(other: BoundProperty<T>): BoundCondition =
    combine(other) { a, b -> a == b }

fun BoundProperty<Boolean>.isTrue() = this isEqualTo true
fun BoundProperty<Boolean>.isFalse() = this isEqualTo false
