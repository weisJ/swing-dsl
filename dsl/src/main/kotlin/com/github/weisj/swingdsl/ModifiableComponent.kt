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

import com.github.weisj.swingdsl.laf.SelfWrappedComponent
import com.github.weisj.swingdsl.laf.WrappedComponent
import com.github.weisj.swingdsl.style.UIFactory
import javax.swing.JComponent
import javax.swing.JSplitPane

interface Modifiable {
    fun apply()

    fun reset()

    fun isModified(): Boolean

    companion object {
        val NO_OP_IMPL = object : Modifiable {
            override fun apply() {}
            override fun reset() {}
            override fun isModified(): Boolean = false
        }
    }
}

interface ModifiableComponent<T : JComponent> : WrappedComponent<T>, Modifiable {

    companion object {
        operator fun <T : JComponent> invoke(component: T): ModifiableComponent<T> {
            return object : SelfWrappedComponent<T>(component), ModifiableComponent<T>,
                Modifiable by Modifiable.NO_OP_IMPL {}
        }

        operator fun <T : JComponent> invoke(
            component: T,
            onApply: () -> Unit,
            onReset: () -> Unit,
            onIsModified: () -> Boolean
        ): ModifiableComponent<T> {
            return object : SelfWrappedComponent<T>(component), ModifiableComponent<T> {
                override fun apply() {
                    onApply()
                }

                override fun reset() {
                    onReset()
                }

                override fun isModified(): Boolean = onIsModified()
            }
        }
    }
}

class ModifiableSplitPane<T : JComponent>(
    val leftComponent: ModifiableComponent<T>,
    val rightComponent: ModifiableComponent<T>
) : ModifiableComponent<JSplitPane>,
    WrappedComponent<JSplitPane> by UIFactory.createSplitPane(leftComponent.container, rightComponent.container) {

    constructor(left: T, right: T) : this(ModifiableComponent(left), ModifiableComponent(right))

    override fun apply() {
        leftComponent.apply()
        rightComponent.apply()
    }

    override fun reset() {
        leftComponent.reset()
        rightComponent.reset()
    }

    override fun isModified(): Boolean = leftComponent.isModified() || rightComponent.isModified()
}
