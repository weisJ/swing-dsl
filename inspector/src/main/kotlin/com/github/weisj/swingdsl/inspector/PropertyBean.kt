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

import com.github.weisj.swingdsl.highlight.LayoutTag
import com.github.weisj.swingdsl.layout.miglayout.patched.PatchedMigLayout
import com.github.weisj.swingdsl.util.toCapitalized
import net.miginfocom.layout.AC
import net.miginfocom.layout.CC
import net.miginfocom.layout.ConstraintParser
import net.miginfocom.layout.LC
import net.miginfocom.swing.MigLayout
import java.awt.BorderLayout
import java.awt.CardLayout
import java.awt.Component
import java.awt.Container
import java.awt.GridBagLayout
import java.util.*
import javax.accessibility.Accessible
import javax.accessibility.AccessibleComponent
import javax.accessibility.AccessibleContext
import javax.swing.JComponent

internal data class PropertyBean(
    val name: String,
    val value: Any?,
    val changed: Boolean = false
)

internal data class ClickInfo(
    val properties: List<PropertyBean>,
    val tag: LayoutTag
)

internal class ComponentClassHierarchy private constructor(val hierarchy: List<Class<*>>) {

    @OptIn(ExperimentalStdlibApi::class)
    constructor(clazz: Class<*>) : this(
        buildList {
            val accessible = AccessibleComponent::class.java.isAssignableFrom(clazz)
            val jComponentClassName =
                if (accessible) JComponent.AccessibleJComponent::class.java.name else JComponent::class.java.name
            val componentClassName =
                if (accessible) "java.awt.Component\$AccessibleAWTContainer" else Container::class.java.name
            var cl: Class<*>? = clazz
            while (cl != null && cl.name != componentClassName && cl.name != jComponentClassName) {
                add(cl)
                cl = cl.superclass
            }
            if (cl != null) add(cl)
        }
    )
}

internal class Flags(val flags: List<Pair<String, Boolean>>)

internal class ComponentPropertyBeans(
    component: Component? = null,
    clickInfo: ClickInfo? = null,
    val properties: MutableMap<String, PropertyBean> = linkedMapOf()
) {
    private val index: MutableList<String> = mutableListOf()

    operator fun get(i: Int): PropertyBean? = properties[index[i]]

    val size: Int
        get() = properties.size

    companion object {
        private val PROPERTIES = listOf(
            "ui", "getLocation", "getLocationOnScreen",
            "getSize", "getBorder",
            "getForeground", "getBackground", "getFont",
            "getCellRenderer", "getCellEditor",
            "getMinimumSize", "getMaximumSize", "getPreferredSize",
            "getPreferredScrollableViewportSize",
            "getText", "isEditable", "getIcon",
            "getVisibleRect", "getLayout",
            "getAlignmentX", "getAlignmentY",
            "getTooltipText", "getToolTipText", "cursor",
            "getClientProperties", "getMouseListeners"
        )
        private val DISPLAY_FLAGS = listOf(
            "isEnabled", "isOpaque", "isVisible", "isShowing",
        )
        private val FOCUS_FLAGS = listOf(
            "isFocusable", "isFocusCycleRoot", "isFocusOwner",
        )
        private val STATE_FLAGS = listOf(
            "isEditable", "isValid", "isDisplayable", "isLightweight", "isDoubleBuffered"
        )
        private val CHECKERS = listOf(
            "isForegroundSet", "isBackgroundSet", "isFontSet",
            "isMinimumSizeSet", "isMaximumSizeSet", "isPreferredSizeSet"
        )

        private val ACCESSIBLE_CONTEXT_PROPERTIES = listOf(
            "getAccessibleRole", "getAccessibleName", "getAccessibleDescription",
            "getAccessibleAction", "getAccessibleChildrenCount",
            "getAccessibleIndexInParent", "getAccessibleRelationSet",
            "getAccessibleStateSet", "getAccessibleEditableText",
            "getAccessibleTable", "getAccessibleText",
            "getAccessibleValue", "accessibleChangeSupport"
        )
    }

    init {
        reload(component, clickInfo)
    }

    private fun add(bean: PropertyBean) {
        index.add(bean.name)
        properties[bean.name] = bean
    }

    fun reload(newComponent: Component?, clickInfo: ClickInfo?) {
        properties.clear()
        index.clear()
        if (clickInfo != null) {
            clickInfo.properties.forEach { add(it) }
        } else {
            loadProperties(newComponent!!)
        }
    }

    private fun loadProperties(component: Component) {
        addProperties("", component, PROPERTIES)
        addFlagProperty("flags.display", component, DISPLAY_FLAGS)
        addFlagProperty("flags.state", component, STATE_FLAGS)
        addFlagProperty("flags.focus", component, FOCUS_FLAGS)
        val isAccessible = component is Accessible
        add(PropertyBean("accessible", isAccessible))
        val context: AccessibleContext? = component.accessibleContext
        add(PropertyBean("accessibleContext", context))
        if (context != null) {
            addProperties("accessible.", context, ACCESSIBLE_CONTEXT_PROPERTIES)
        }
        if (component is Container) {
            addLayoutProperties(component)
        }
        component.parent?.let {
            val layout = it.layout
            if (layout is PatchedMigLayout) {
                val cc = layout.getComponentConstraints()[component]
                if (cc != null) addMigLayoutComponentConstraints(cc)
            }
        }
    }

    private fun addProperties(prefix: String, component: Any, methodNames: List<String>) {
        val clazz: Class<*> = component.javaClass
        add(PropertyBean("${prefix}hierarchy", ComponentClassHierarchy(clazz)))
        propertiesFromMethodNames(prefix, component, methodNames).forEach { add(it) }
    }

    private fun addFlagProperty(name: String, component: Component, flags: List<String>) {
        val parsedFlags =
            propertiesFromMethodNames("", component, flags).map { it.name to it.value as Boolean }.toList()
        add(PropertyBean(name, Flags(parsedFlags)))
    }

    private fun propertiesFromMethodNames(prefix: String, component: Any, methodNames: List<String>) = sequence {
        val clazz = component.javaClass.let { if (it.isAnonymousClass) it.superclass else it }
        for (name in methodNames) {
            try {
                val propertyName: String = getPropertyName(name) ?: name
                val propertyValue: Any = runCatching {
                    collectAllMethodsRecursively(clazz).first { it.name == name }.let {
                        it.isAccessible = true
                        it.invoke(component)
                    }
                }.getOrElse {
                    collectAllFieldsRecursively(clazz).first { it.name == name }.let {
                        it.isAccessible = true
                        it.get(component)
                    }
                }
                val changed = runCatching {
                    val checkerMethodName = "is${propertyName.toCapitalized()}Set"
                    if (CHECKERS.contains(checkerMethodName)) {
                        clazz.methods.first { it.name == checkerMethodName }.invoke(component) as? Boolean ?: false
                    } else {
                        false
                    }
                }.getOrDefault(false)
                yield(PropertyBean(prefix + propertyName, propertyValue, changed))
            } catch (e: Exception) {
            }
        }
    }

    private fun addLayoutProperties(component: Container) {
        when (val layout = component.layout) {
            is GridBagLayout -> {
                addGridBagLayoutProperties(layout)
            }
            is BorderLayout -> {
                addBorderLayoutProperties(layout)
            }
            is CardLayout -> {
                addCardLayoutProperties(layout)
            }
            is MigLayout -> {
                addMigLayoutProperties(layout)
            }
        }
    }

    private fun addGridBagLayoutProperties(layout: GridBagLayout) {
        add(PropertyBean("gridlayout.defaultConstraints)", getFieldValue(layout, "defaultConstrains")))
        add(PropertyBean("gridlayout.columnWidths", layout.columnWidths))
        add(PropertyBean("gridlayout.rowHeights", layout.rowHeights))
        add(PropertyBean("gridlayout.columnWeights", layout.columnWeights))
        add(PropertyBean("gridlayout.rowWeights", layout.rowWeights))
    }

    private fun addBorderLayoutProperties(layout: BorderLayout) {
        add(PropertyBean("borderlayout.constraints", "hgap - ${layout.hgap}, vgap - ${layout.vgap}"))
    }

    private fun addCardLayoutProperties(layout: CardLayout) {
        val currentCard: Int? = getFieldValue(layout, "currentCard")
        val vector: Vector<*>? = getFieldValue(layout, "vector")
        val cardDescription = if (vector != null && currentCard != null) {
            val card = vector[currentCard]
            getFieldValue(card.javaClass, String::class.java, card, "name")
        } else "???"
        add(PropertyBean("cardlayout.currentCard", cardDescription))
        add(PropertyBean("cardlayout.constraints", "hgap - ${layout.hgap}, vgap - ${layout.vgap}"))
    }

    private fun addMigLayoutProperties(layout: MigLayout) {
        var constraints: Any = layout.layoutConstraints
        if (constraints is String) {
            constraints = ConstraintParser.prepare(constraints)
            constraints = ConstraintParser.parseLayoutConstraint(constraints)
        }
        if (constraints is LC) {
            add(PropertyBean("miglayout.lc", constraints))
        }

        constraints = layout.columnConstraints
        if (constraints is String) {
            constraints = ConstraintParser.prepare(constraints)
            constraints = ConstraintParser.parseColumnConstraints(constraints)
        }
        if (constraints is AC) {
            add(PropertyBean("miglayout.columnConstraints", constraints))
        }

        constraints = layout.rowConstraints
        if (constraints is String) {
            constraints = ConstraintParser.prepare(constraints)
            constraints = ConstraintParser.parseRowConstraints(constraints)
        }
        if (constraints is AC) {
            add(PropertyBean("miglayout.rowConstraints", constraints))
        }
    }

    private fun addMigLayoutComponentConstraints(cc: CC) {
        add(PropertyBean("miglayout.component.constraint", cc))
        add(PropertyBean("miglayout.component.cc.horizontal", cc.horizontal))
        add(PropertyBean("miglayout.component.cc.vertical", cc.vertical))
    }
}
