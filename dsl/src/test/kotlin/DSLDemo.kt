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
import com.github.weisj.darklaf.theme.DarculaTheme
import com.github.weisj.swingdsl.CloseOperation
import com.github.weisj.swingdsl.Modifiable
import com.github.weisj.swingdsl.borderPanel
import com.github.weisj.swingdsl.centered
import com.github.weisj.swingdsl.condition.DefaultObservable
import com.github.weisj.swingdsl.condition.Observable
import com.github.weisj.swingdsl.condition.isEqualTo
import com.github.weisj.swingdsl.condition.isTrue
import com.github.weisj.swingdsl.condition.observable
import com.github.weisj.swingdsl.condition.on
import com.github.weisj.swingdsl.frame
import com.github.weisj.swingdsl.invokeLater
import com.github.weisj.swingdsl.panel
import javax.swing.JLabel

class Model(initialBool: Boolean, initialText: String) : Observable<Model> by DefaultObservable() {
    var boolValue: Boolean by observable(initialBool)
    var textValue: String by observable(initialText)
}

fun main() {
    val model = Model(false, "This is a text field")
    invokeLater {
        LafManager.installTheme(DarculaTheme())
        frame {
            content {
                borderPanel {
                    north {
                        centered { JLabel("North") }
                    }
                    center {
                        panel {
                            hideableRow("Row 1", startHidden = false) {
                                row {
                                    label("Hello Row 1")
                                }
                                row {
                                    checkBox("Check", model::boolValue)
                                }
                                row {
                                    textField(model::textValue)
                                }
                                row {
                                    enableIf((Model::boolValue on model).isTrue())
                                    label("Enabled if checkbox is enabled")
                                }
                                row {
                                    label("Enabled if text field has value 'Hello'")
                                    enableIf(Model::textValue on model isEqualTo "Hello")
                                }
                                commitImmediately()
                            }
                        }
                    }
                    south {
                        panel {
                            row {
                                right {
                                    cell {
                                        val centerPanel = center as Modifiable
                                        button("Apply") { centerPanel.apply() }
                                        button("Reset") { centerPanel.reset() }
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
