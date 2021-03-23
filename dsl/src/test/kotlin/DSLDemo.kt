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
import com.github.weisj.swingdsl.CloseOperation
import com.github.weisj.swingdsl.binding.BoundProperty
import com.github.weisj.swingdsl.borderPanel
import com.github.weisj.swingdsl.centered
import com.github.weisj.swingdsl.condition.isEqualTo
import com.github.weisj.swingdsl.condition.isTrue
import com.github.weisj.swingdsl.frame
import com.github.weisj.swingdsl.invokeLater
import com.github.weisj.swingdsl.layout.ModifiablePanel
import com.github.weisj.swingdsl.layout.panel
import com.github.weisj.swingdsl.layout.selectionStatus
import com.github.weisj.swingdsl.layout.textValue
import javax.swing.JLabel

data class Model(var boolValue: Boolean, var textValue: String)

fun main() {
    val model = Model(false, "This is a text field")
    invokeLater {
        LafManager.installTheme(IntelliJTheme())
        frame {
            content {
                borderPanel {
                    north {
                        centered {
                            JLabel("North").apply {
                                font = font.deriveFont(20f)
                            }
                        }
                    }
                    center {
                        panel {
                            hideableRow("Row 1", startHidden = false) {
                                lateinit var boolProp: BoundProperty<Boolean>
                                lateinit var stringProp: BoundProperty<String>
                                row { label("Hello Row 1") }
                                row { boolProp = checkBox("Check", model::boolValue).selectionStatus() }
                                row { stringProp = textField(model::textValue).textValue() }
                                row {
                                    enableIf(boolProp.isTrue())
                                    label("Enabled if checkbox is enabled")
                                }
                                row {
                                    label("Enabled if text field has value 'Hello'")
                                    enableIf(stringProp isEqualTo "Hello")
                                }
                            }
                        }
                    }
                    south {
                        panel {
                            row {
                                right {
                                    cell {
                                        val centerPanel = center as ModifiablePanel
                                        button("Apply") { centerPanel.apply() }
                                        button("Reset") { centerPanel.reset() }() {
                                            enableIf(centerPanel.modifiedCondition)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            defaultCloseOperation = CloseOperation.EXIT
            locationByPlatform = true
            locationRelativeTo = null
            visible = true
        }
    }
}
