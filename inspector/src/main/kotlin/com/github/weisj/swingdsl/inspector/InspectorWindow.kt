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

import com.github.weisj.darklaf.properties.icons.IconLoader
import com.github.weisj.swingdsl.border.emptyBorder
import com.github.weisj.swingdsl.borderPanel
import com.github.weisj.swingdsl.horizontalSplit
import com.github.weisj.swingdsl.invokeLater
import com.github.weisj.swingdsl.scrollPane
import com.github.weisj.swingdsl.unaryPlus
import java.awt.Component
import javax.swing.JDialog

internal class InspectorWindow(
    private val initialComponent: Component,
    private val inspector: Inspector
) : JDialog() {
    private var currentComponent = initialComponent
    private val propertyTable = InspectorTable(initialComponent)
    private val hierarchyTree = object : HierarchyTree(initialComponent) {
        override fun onComponentChanged(component: Component) {
            switchComponentInfo(component, navigateTree = false)
        }

        override fun onClickInfoChanged(info: ClickInfo) {
            switchClickInfo(currentComponent, info, navigateTree = false)
        }
    }

    init {
        title = "Inspector"
        defaultCloseOperation = HIDE_ON_CLOSE
        setIconImage(IconLoader.createFrameIcon(getInspectorIcon(), this))
        contentPane = borderPanel {
            center {
                horizontalSplit {
                    border = emptyBorder()
                    properties {
                        client["JSplitPane.style"] = "line"
                    }
                    initialPosition(0.5)
                    left {
                        scrollPane({ border = emptyBorder() }) {
                            +(hierarchyTree.apply { border = emptyBorder(5, 5, 5, 0) })
                        }
                    }
                    right {
                        +(propertyTable.apply { border = emptyBorder(5, 0, 0, 0) })
                    }
                }
            }
        }.container
    }

    override fun toFront() {
        super.toFront()
        invokeLater { hierarchyTree.requestFocusInWindow() }
    }

    fun switchComponentInfo(targetComponent: Component) {
        switchComponentInfo(targetComponent, navigateTree = true)
    }

    private fun switchComponentInfo(targetComponent: Component, navigateTree: Boolean) {
        title = targetComponent.javaClass.name
        propertyTable.refresh(targetComponent)
        if (navigateTree) {
            hierarchyTree.reload(newComponent = targetComponent)
        }
        setCurrentComponent(targetComponent, null)
    }

    fun switchClickInfo(targetComponent: Component, clickInfo: ClickInfo) {
        switchClickInfo(targetComponent, clickInfo, navigateTree = true)
    }

    private fun switchClickInfo(targetComponent: Component, clickInfo: ClickInfo, navigateTree: Boolean) {
        title = "Click Info"
        propertyTable.refresh(newComponent = targetComponent, clickInfo = clickInfo)
        if (navigateTree) {
            hierarchyTree.reload(newComponent = targetComponent, clickInfo = clickInfo)
        }
        setCurrentComponent(targetComponent, clickInfo)
    }

    private fun setCurrentComponent(comp: Component, clickInfo: ClickInfo?) {
        currentComponent = comp
        inspector.updateHighlightTarget(comp, p = null, tag = clickInfo?.tag)
        invalidate()
    }
}
