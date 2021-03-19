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

import kotlin.reflect.KProperty0

/**
 * Condition whose value can be observed.
 */
interface Condition : () -> Boolean, Observable<Condition> {
    var value: Boolean

    /**
     * Build the condition (Specifically important for more complex conditions which are
     * bound to property value).
     */
    fun build() {
        this()
    }
}

/**
 * Condition with a constant value.
 */
class ConstantCondition(value: Boolean) : Condition, Observable<Condition> by DefaultObservable() {
    override var value: Boolean = value
        set(_) = throw UnsupportedOperationException()

    override fun invoke(): Boolean {
        return value
    }
}

class DefaultCondition(initial: Boolean, private val cond: () -> Boolean = { initial }) :
    Condition,
    Observable<Condition> by DefaultObservable() {
    override var value: Boolean by observable(initial)

    override operator fun invoke(): Boolean {
        value = cond()
        return value
    }
}

class CompoundCondition(
    private val first: Condition,
    private val second: Condition,
    private val combinator: (Boolean, Boolean) -> Boolean
) : Condition, Observable<Condition> by first {
    override var value: Boolean by observable(combinator(first.value, second.value))

    override fun invoke(): Boolean {
        value = combinator(first(), second())
        return value
    }

    override fun build() {
        first.build()
        second.build()
        first.registerListener(Condition::value) { _, _ ->
            value = combinator(first.value, second())
        }
        second.registerListener(Condition::value) { _, _ ->
            value = combinator(first(), second.value)
        }
    }
}

/**
 * Create compound condition which value is true iff both conditions are met.
 */
infix fun Condition.and(other: Condition): Condition = CompoundCondition(this, other, Boolean::and)

/**
 * Create compound condition which value is true iff both at least one conditions is met.
 */
infix fun Condition.or(other: Condition): Condition = CompoundCondition(this, other, Boolean::or)

/**
 * Inverts the given condition.
 */
fun not(cond: Condition): Condition = CompoundCondition(cond, conditionOf(true)) { a, _ -> !a }

/**
 * Create constant value condition.
 */
fun conditionOf(bool: Boolean): Condition = ConstantCondition(bool)

/**
 * Create condition form a Boolean provider..
 */
fun conditionOf(cond: () -> Boolean): Condition = DefaultCondition(cond(), cond)

/**
 * Create condition from a boolean property.
 */
fun conditionOf(prop: KProperty0<Boolean>): Condition = DefaultCondition(prop.get()) { prop.get() }
