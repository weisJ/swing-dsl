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
import com.github.weisj.darklaf.LafManager
import com.github.weisj.darklaf.theme.IntelliJTheme
import com.github.weisj.swingdsl.core.binding.observableProperty
import com.github.weisj.swingdsl.core.condition.conditionOf
import com.github.weisj.swingdsl.core.text.unaryPlus
import com.github.weisj.swingdsl.frame
import com.github.weisj.swingdsl.inspector.installInspector
import com.github.weisj.swingdsl.layout.Row
import com.github.weisj.swingdsl.layout.RowBuilder
import com.github.weisj.swingdsl.layout.panel
import com.github.weisj.swingdsl.onSwingThread
import com.github.weisj.swingdsl.scrollPane
import net.miginfocom.layout.LayoutUtil
import java.awt.Dimension
import javax.swing.JLabel

fun RowBuilder.demoRow(label: String = "Label", value: String = "Value"): Row = row(label) {
    label(value)
}

var rowVisible = observableProperty(false)

fun main() = test2()

fun test2() = onSwingThread {
    LafManager.install(IntelliJTheme())
    installInspector()
    frame {
        content {
            scrollPane {
                panel {
                    // 10
                    row {
                        checkBox(+"Rows visible", rowVisible).commitImmediately()
                    }
                    // 12
                    hideableRow("Hideable", startHidden = true) {
                        // 12 from separator row
                        titledRow("Titled1") {
                            // 12 from title
                            row {
                                label("Label1")
                                visible(true)
                            }
                            // 12
                        }
                        // 20 from gap row
                        repeat(5) {
                            titledRow("Titled2") {
                                // 0/12 from title
                                visibleIf(rowVisible)
                                subRowsVisibleIf(rowVisible)
                                row {
                                    label("Label2.1")
                                    visibleIf(conditionOf(true))
                                }
                                row {
                                    label("Label2.1")
                                    visibleIf(conditionOf(true))
                                }
                                row {
                                    label("Label2.1")
                                    visibleIf(conditionOf(true))
                                }
                                row {
                                    label("Label2.1")
                                    visibleIf(conditionOf(true))
                                }
                                // 0/12
                            }
                            // 0/20 from gap row
                            row {
                                label("Test")
                            }
                        }
                        row {
                            label("Label3")
                            visible(true)
                        }
                        // 12
                    }
                    hideableRow("End", startHidden = false) {
                        // 12 from separator row
//                        label("Test")
                        scrollPane(JLabel("Test"))
                        visible(true)
                        // 0
                    }
                    // 10 from root row
                }
            }
        }
        centerOnScreen()
        preferredSize = Dimension(500, 500)
        visible = true
    }
    LayoutUtil.setGlobalDebugMillis(300)
}

fun test() = onSwingThread {
    LafManager.install(IntelliJTheme())
    installInspector()
    frame {
        content {
            scrollPane {
                panel {
                    commentRow("This is a comment", withLeftGap = false)
                    row {
                        demoRow()
                    }
                    row {
                        row("MultiCell") {
                            cell(isVerticalFlow = true) {
                                label("Label 1")
                                label("Label 2")
                                label("Label 3")
                                label("Label 4")
                                label("Label 5")
                            }
                        }
                    }
                    demoRow()
                    row { checkBox(+"Row Visible", rowVisible).commitImmediately() }
                    demoRow("Level 0").visibleIf(rowVisible)
                    row {
                        demoRow("Label1", "Level 1")
                        row {
                            demoRow("Label2", "Level 2")
                            row {
                                demoRow("Label3", "Level 3")
                            }
                        }
                    }
                    titledRow("Title1") {
                        demoRow("TitledL 1", "Level 1")
                        titledRow("Title2") {
                            demoRow("TitledL 2", "Level 2")
                            titledRow("Title3") {
                                demoRow("TitledL 3", "Level 3")
                            }
                        }
                    }
                    hideableRow("Hide1", startHidden = false) {
                        demoRow("HideabL 1", "Level 1")
                        hideableRow("Hide2", startHidden = false) {
                            demoRow("HideabL 2", "Level 2")
                            hideableRow("Hide3", startHidden = false) {
                                demoRow("HideabL 3", "Level 3")
                            }
                        }
                    }
                }
            }
        }
        centerOnScreen()
        preferredSize = Dimension(500, 500)
        visible = true
    }
    LayoutUtil.setGlobalDebugMillis(300)
}
