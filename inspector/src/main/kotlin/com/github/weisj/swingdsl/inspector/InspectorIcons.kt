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
package com.github.weisj.swingdsl.inspector

import com.github.weisj.darklaf.icons.IconLoader
import java.awt.Color
import java.awt.Component
import java.awt.Window
import javax.swing.*

private object ComponentIconMappings {

    private val LOADER: IconLoader = IconLoader.get(Inspector::class.java)

    private val PROPERTY_MAP: Map<Any, Any> = mapOf(
        "inspectorWarningHighlight" to Color(0xF26522),
        "inspectorWarningOpacity" to 80,
        "fallback.inspectorBackground" to Color(0x9AA7B0),
        "fallback.inspectorForeground" to Color(0x231F20),
    )

    private fun load(name: String): Icon = LOADER.loadSVGIcon(name, 16, 16, true, PROPERTY_MAP)

    val INSPECTOR = load("inspector.svg")
    val UNKNOWN = load("unknown.svg")
    val CLICK_INFO = load("clickInfo.svg")
    val MAPPING = mapOf(
        JLabel::class.java to load("label.svg"),
        JPanel::class.java to load("panel.svg"),
        JButton::class.java to load("button.svg"),
        JCheckBox::class.java to load("checkBox.svg"),
        JRadioButton::class.java to load("radioButton.svg"),
        JComboBox::class.java to load("comboBox.svg"),
        JList::class.java to load("list.svg"),
        JProgressBar::class.java to load("progressbar.svg"),
        JScrollBar::class.java to load("scrollbar.svg"),
        JScrollPane::class.java to load("scrollPane.svg"),
        JSeparator::class.java to load("separator.svg"),
        JToolBar.Separator::class.java to load("toolbarSeparator.svg"),
        JSlider::class.java to load("slider.svg"),
        JSpinner::class.java to load("spinner.svg"),
        JSplitPane::class.java to load("splitPane.svg"),
        JTabbedPane::class.java to load("tabbedPane.svg"),
        JTable::class.java to load("table.svg"),
        JTextArea::class.java to load("textArea.svg"),
        JTextField::class.java to load("textField.svg"),
        JFormattedTextField::class.java to load("formattedTextField.svg"),
        JPasswordField::class.java to load("passwordField.svg"),
        JTextPane::class.java to load("textPane.svg"),
        JEditorPane::class.java to load("editorPane.svg"),
        JToolBar::class.java to load("toolbar.svg"),
        JTree::class.java to load("tree.svg"),
        JLayeredPane::class.java to load("layeredPane.svg"),
        Window::class.java to load("window.svg")
    )
}

internal fun getInspectorIcon(): Icon = ComponentIconMappings.INSPECTOR
internal fun getClickInfoIcon(): Icon = ComponentIconMappings.CLICK_INFO

internal fun findIconFor(component: Component): Icon {
    var aClass: Class<*>? = component.javaClass
    var icon: Icon? = null
    while (icon == null && aClass != null) {
        icon = ComponentIconMappings.MAPPING[aClass]
        aClass = aClass.superclass
    }
    if (icon == null) icon = ComponentIconMappings.UNKNOWN
    return icon
}
