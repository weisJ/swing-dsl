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
package com.github.weisj.swingdsl.highlight

import com.github.weisj.swingdsl.laf.WrappedComponent
import java.awt.Component
import java.awt.Rectangle
import javax.swing.JComponent
import javax.swing.SwingUtilities

interface LayoutTag {
    fun getBoundsIn(comp: Component): Rectangle
}

internal sealed class ComponentLayoutTag<T : Component>(protected val anchor: T) : LayoutTag {

    abstract fun getBounds(): Rectangle

    override fun getBoundsIn(comp: Component): Rectangle {
        return SwingUtilities.convertRectangle(anchor, getBounds(), comp)
    }
}

internal class ComponentBoundsLayoutTag<T : Component>(anchor: T) : ComponentLayoutTag<T>(anchor) {
    override fun getBounds(): Rectangle = Rectangle(0, 0, anchor.width, anchor.height)
}

internal class ComponentRegionBoundsLayoutTag<T : Component>(
    anchor: T,
    private val regionSupplier: (T) -> Rectangle
) : ComponentLayoutTag<T>(anchor) {
    override fun getBounds(): Rectangle = regionSupplier(anchor)
}

private object EmptyLayoutTag : LayoutTag {
    override fun getBoundsIn(comp: Component): Rectangle {
        return Rectangle(0, 0, 0, 0)
    }
}

fun emptyLayoutTag(): LayoutTag = EmptyLayoutTag

fun <T : JComponent> T.createLayoutTag(regionSupplier: (T) -> Rectangle): LayoutTag =
    ComponentRegionBoundsLayoutTag(this, regionSupplier)

fun <T : JComponent> T.createLayoutTag(): LayoutTag =
    ComponentBoundsLayoutTag(this)

fun <T : JComponent> WrappedComponent<T>.createContainerLayoutTag(regionSupplier: (JComponent) -> Rectangle): LayoutTag =
    container.createLayoutTag(regionSupplier)

fun <T : JComponent> WrappedComponent<T>.createComponentLayoutTag(regionSupplier: (T) -> Rectangle): LayoutTag =
    component.createLayoutTag(regionSupplier)

fun <T : JComponent> WrappedComponent<T>.createLayoutTag(): LayoutTag = container.createLayoutTag()
