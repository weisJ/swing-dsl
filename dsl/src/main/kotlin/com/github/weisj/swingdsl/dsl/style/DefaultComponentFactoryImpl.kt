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
package com.github.weisj.swingdsl.dsl.style

import com.github.weisj.swingdsl.core.text.Text
import com.github.weisj.swingdsl.core.text.TextButton
import com.github.weisj.swingdsl.core.text.TextCheckBox
import com.github.weisj.swingdsl.core.text.TextLabel
import com.github.weisj.swingdsl.core.text.TextRadioButton
import com.github.weisj.swingdsl.dsl.icons.ArrowDownIcon
import com.github.weisj.swingdsl.dsl.icons.ArrowRightIcon
import com.github.weisj.swingdsl.dsl.util.SharedLazyComponents
import com.github.weisj.swingdsl.dsl.util.mix
import com.github.weisj.swingdsl.laf.CollapsibleComponent
import com.github.weisj.swingdsl.laf.ComponentFactory
import com.github.weisj.swingdsl.laf.ComponentSpec
import com.github.weisj.swingdsl.laf.ComponentSpec.createDefaultImpl
import com.github.weisj.swingdsl.laf.SelfWrappedComponent
import com.github.weisj.swingdsl.laf.StateValue
import com.github.weisj.swingdsl.laf.TextProperty
import com.github.weisj.swingdsl.laf.WrappedComponent
import java.awt.Color
import java.util.function.Consumer
import javax.swing.Icon
import javax.swing.JButton
import javax.swing.JCheckBox
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JList
import javax.swing.JRadioButton
import javax.swing.JScrollPane
import javax.swing.JSplitPane
import javax.swing.ListModel
import javax.swing.UIManager

internal class TextPropertyWrapper(internal val text: Text) : TextProperty {
    override fun get(): String = text.get()

    override fun onChange(callback: Consumer<String>) {
        text.onChange { callback.accept(it) }
    }
}

internal class TextWrapper(internal val textProp: TextProperty) : Text {
    override fun get(): String = textProp.get()

    override fun onChange(callback: (String) -> Unit) {
        textProp.onChange(callback)
    }
}

fun Text.asTextProperty(): TextProperty = if (this is TextWrapper) this.textProp else TextPropertyWrapper(this)
fun TextProperty.asText(): Text = if (this is TextPropertyWrapper) this.text else TextWrapper(this)

@Suppress("kotlin:S1192")
// Extracting Strings makes no sense here, as they are conceptually independent of each other
class DefaultComponentFactoryImpl : ComponentFactory {

    override fun createLabel(text: TextProperty, icon: Icon?): WrappedComponent<JLabel> {
        return SelfWrappedComponent(TextLabel(text.asText(), icon))
    }

    override fun createButton(text: TextProperty, icon: Icon?): WrappedComponent<JButton> {
        return SelfWrappedComponent(TextButton(text.asText(), icon))
    }

    override fun createCheckBox(text: TextProperty, icon: Icon?): WrappedComponent<JCheckBox> {
        return SelfWrappedComponent(TextCheckBox(text.asText(), icon))
    }

    override fun createRadioButton(text: TextProperty, icon: Icon?): WrappedComponent<JRadioButton> {
        return SelfWrappedComponent(TextRadioButton(text.asText(), icon))
    }

    override fun createScrollPane(content: JComponent): WrappedComponent<JScrollPane> {
        return SelfWrappedComponent(JScrollPane(content))
    }

    override fun <T> createList(content: ListModel<T>): WrappedComponent<JList<T>> {
        return SelfWrappedComponent(JList(content))
    }

    override fun createSplitPane(left: JComponent, right: JComponent): WrappedComponent<JSplitPane> {
        return SelfWrappedComponent(JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right))
    }

    override fun createSeparatorComponent(label: TextProperty?): ComponentSpec<JComponent> {
        return createDefaultImpl()
    }

    override fun createCollapsibleSeparatorComponent(label: TextProperty?): ComponentSpec<CollapsibleComponent> {
        return createDefaultImpl()
    }

    override fun getDividerColor(): StateValue<Color> {
        return StateValue(getDividerColor(true), getDividerColor(false))
    }

    private fun getDividerColor(enabled: Boolean): Color {
        return if (enabled) {
            UIManager.getColor("borderSecondary")
                ?: UIManager.getColor("Label.foreground")
                ?: Color.BLACK
        } else {
            UIManager.getColor("Label.disabledForeground")
                ?: Color.GRAY
        }
    }

    override fun getBorderColor(): Color {
        return UIManager.getColor("border")
            ?: UIManager.getColor("Label.foreground")
            ?: Color.BLACK
    }

    override fun getHyperlinkColor(): Color {
        return UIManager.getColor("hyperlink") ?: Color.BLUE.brighter()
    }

    override fun getHyperlinkClickColor(): Color {
        return UIManager.getColor("hyperlinkClick") ?: Color.ORANGE.darker()
    }

    override fun getSecondaryTextForeground(): Color {
        return UIManager.getColor("textForegroundSecondary")
            ?: (UIManager.getColor("Label.foreground") ?: Color.BLACK).mix(Color.WHITE, 0.80f)
    }

    override fun getColorBackgroundColor(): Color {
        return UIManager.getColor("backgroundColorful")
            ?: SharedLazyComponents.tree.background
    }

    override fun getWarningColor(): Color {
        return UIManager.getColor("warning")
            ?: Color(199, 134, 7)
    }

    override fun getErrorColor(): Color {
        return UIManager.getColor("error")
            ?: Color(199, 7, 7)
    }

    override fun getExpandedIcon(): StateValue<Icon> {
        return StateValue(ArrowDownIcon(getDividerColor(true)), ArrowDownIcon(getDividerColor(false)))
    }

    override fun getCollapsedIcon(): StateValue<Icon> {
        return StateValue(ArrowRightIcon(getDividerColor(true)), ArrowRightIcon(getDividerColor(false)))
    }
}
