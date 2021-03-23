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
import kotlin.reflect.KProperty0

/**
 * Condition whose value can be observed.
 */
interface Condition : () -> Boolean

interface ConditionCallback : (Boolean) -> Unit

/**
 * Bound conditions promise to update their value as soon as a new invocation would return a different
 * boolean value than the previous.
 */
interface BoundCondition : Condition {

    fun registerListener(callback: ConditionCallback)
    fun registerListener(callback: (Boolean) -> Unit) = registerListener(object : ConditionCallback {
        override fun invoke(b: Boolean) = callback(b)
    })
}

/**
 * Condition with a constant value.
 */
class ConstantCondition(private val value: Boolean) : BoundCondition {
    override fun registerListener(callback: ConditionCallback) {
        /* Do nothing. Value never changes */
    }

    override fun invoke(): Boolean = value
}

class DefaultCondition(private val cond: () -> Boolean) : Condition {
    override operator fun invoke() = cond()
}

internal open class CompoundCondition<C1 : Condition, C2 : Condition>(
    internal val first: C2,
    internal val second: C1,
    internal val combinator: (Boolean, Boolean) -> Boolean
) : Condition {
    override fun invoke(): Boolean {
        return combinator(first(), second())
    }
}

internal class BoundCompoundCondition(
    first: BoundCondition,
    second: BoundCondition,
    combinator: (Boolean, Boolean) -> Boolean
) : CompoundCondition<BoundCondition, BoundCondition>(first, second, combinator), BoundCondition {

    override fun registerListener(callback: ConditionCallback) {
        first.registerListener(callback)
        second.registerListener(callback)
    }
}

internal class BoundPropertyCondition<T>(
    private val property: BoundProperty<T>,
    private val checker: (T) -> Boolean
) : BoundCondition {
    override fun registerListener(callback: ConditionCallback) {
        property.onPropertyChange {
            callback(invoke())
        }
    }

    override fun invoke(): Boolean =
        checker(property.get())
}

/**
 * Create compound condition which value is true iff both conditions are met.
 */
infix fun Condition.and(other: Condition): Condition = CompoundCondition(this, other, Boolean::and)
infix fun BoundCondition.and(other: BoundCondition): BoundCondition = BoundCompoundCondition(this, other, Boolean::and)

/**
 * Create compound condition which value is true iff both at least one conditions is met.
 */
infix fun Condition.or(other: Condition): Condition = CompoundCondition(this, other, Boolean::or)
infix fun BoundCondition.or(other: BoundCondition): BoundCondition = BoundCompoundCondition(this, other, Boolean::or)

/**
 * Inverts the given condition.
 */
fun not(cond: Condition): Condition = CompoundCondition(cond, conditionOf(true)) { a, _ -> !a }
fun not(cond: BoundCondition): BoundCondition = BoundCompoundCondition(cond, conditionOf(true)) { a, _ -> !a }

/**
 * Create constant value condition.
 */
fun conditionOf(bool: Boolean): BoundCondition = ConstantCondition(bool)

/**
 * Create condition form a Boolean provider..
 */
fun conditionOf(cond: () -> Boolean): Condition = DefaultCondition(cond)

/**
 * Create condition from a boolean property.
 */
fun conditionOf(prop: KProperty0<Boolean>): Condition = DefaultCondition(prop::get)

infix fun <T> BoundProperty<T>.isEqualTo(value: T): BoundCondition = BoundPropertyCondition(this) { it == value }
infix fun <T> BoundProperty<T>.isEqualTo(valueSupplier: () -> T): BoundCondition =
    BoundPropertyCondition(this) { it == valueSupplier() }

fun BoundProperty<Boolean>.isTrue() = this isEqualTo true
fun BoundProperty<Boolean>.isFalse() = this isEqualTo false
