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
package com.github.weisj.swingdsl

import com.github.weisj.swingdsl.core.condition.ObservableCondition
import com.github.weisj.swingdsl.core.condition.conditionOf
import com.github.weisj.swingdsl.laf.SelfWrappedComponent
import com.github.weisj.swingdsl.laf.WrappedComponent
import javax.swing.JComponent

interface Modifiable {
    fun apply()

    fun reset()

    fun isModified(): Boolean

    val modifiedCondition: ObservableCondition

    companion object {
        val NO_OP_IMPL: Modifiable = object : Modifiable {
            override fun apply() {
                /* Do nothing */
            }

            override fun reset() {
                /* Do nothing */
            }

            override fun isModified(): Boolean = false
            override val modifiedCondition: ObservableCondition
                get() = conditionOf(false)
        }
    }
}

interface ModifiableComponent<T : JComponent> : WrappedComponent<T>, Modifiable {

    companion object {
        operator fun <T : JComponent> invoke(component: T): ModifiableComponent<T> {
            return object :
                SelfWrappedComponent<T>(component),
                ModifiableComponent<T>,
                Modifiable by Modifiable.NO_OP_IMPL {}
        }
    }
}
