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
import com.github.weisj.darklaf.theme.spec.PreferredThemeStyle
import com.github.weisj.swingdsl.core.binding.ObservableProperty
import com.github.weisj.swingdsl.core.binding.inc
import com.github.weisj.swingdsl.core.binding.observableProperty
import com.github.weisj.swingdsl.core.binding.plus
import com.github.weisj.swingdsl.core.condition.isEqualTo
import com.github.weisj.swingdsl.core.condition.isTrue
import com.github.weisj.swingdsl.core.text.textOf
import com.github.weisj.swingdsl.dsl.borderPanel
import com.github.weisj.swingdsl.dsl.centered
import com.github.weisj.swingdsl.dsl.config.CloseOperation
import com.github.weisj.swingdsl.dsl.frame
import com.github.weisj.swingdsl.dsl.invokeLater
import com.github.weisj.swingdsl.dsl.layout.ModifiablePanel
import com.github.weisj.swingdsl.dsl.layout.observableSelected
import com.github.weisj.swingdsl.dsl.layout.observableText
import com.github.weisj.swingdsl.dsl.layout.panel
import com.github.weisj.swingdsl.dsl.scrollPane
import com.github.weisj.swingdsl.dsl.unaryPlus
import javax.swing.JLabel

data class Model(var boolValue: Boolean, var textValue: String)

fun main() {
    val model = Model(false, "This is a text field")
    invokeLater {
        LafManager.installTheme(
            LafManager.getPreferredThemeStyle().let {
                PreferredThemeStyle(it.contrastRule, it.colorToneRule)
            }
        )
        frame {
            content {
                lateinit var modifiablePanel: ModifiablePanel
                borderPanel {
                    north {
                        centered {
                            +JLabel("North").apply {
                                font = font.deriveFont(20f)
                            }
                        }
                    }
                    modifiablePanel = center {
                        scrollPane {
                            +panel {
                                lateinit var boolProp: ObservableProperty<Boolean>
                                lateinit var stringProp: ObservableProperty<String>
                                hideableRow("Row 1", startHidden = false) {
                                    row {
                                        val initial = 0
                                        val counter = observableProperty(initial)
                                        onGlobalIsModified { counter.get() != initial }
                                        onGlobalReset { counter.set(initial) }
                                        button("Say Hello!") {
                                            counter.inc()
                                        }
                                        label(textOf("Hello Counter ") + counter)
                                    }
                                    row { boolProp = checkBox("Check", model::boolValue).observableSelected() }
                                    row { stringProp = textField(model::textValue).observableText() }
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
                    }
                    south {
                        panel {
                            row {
                                right {
                                    cell {
                                        button("Apply") { modifiablePanel.apply() }
                                        button("Reset") { modifiablePanel.reset() }() {
                                            enableIf(modifiablePanel.modifiedCondition)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            centerOnScreen()
            defaultCloseOperation = CloseOperation.EXIT
            visible = true
        }
    }
}
