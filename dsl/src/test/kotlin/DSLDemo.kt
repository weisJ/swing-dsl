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
import com.github.weisj.darklaf.components.OverlayScrollPane
import com.github.weisj.darklaf.components.border.DarkBorders
import com.github.weisj.darklaf.theme.DarculaTheme
import com.github.weisj.swingdsl.CloseOperation
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
import com.github.weisj.swingdsl.laf.ComponentFactory
import com.github.weisj.swingdsl.laf.DefaultComponentFactory
import com.github.weisj.swingdsl.laf.DefaultWrappedComponent
import com.github.weisj.swingdsl.laf.WrappedComponent
import com.github.weisj.swingdsl.panel
import java.awt.Insets
import javax.swing.BorderFactory
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JScrollPane
import javax.swing.border.Border

class Model(initialBool: Boolean, initialText: String) : Observable<Model> by DefaultObservable() {
    var boolValue: Boolean by observable(initialBool)
    var textValue: String by observable(initialText)
}

fun main() {
    val model = Model(false, "This is a text field")
    invokeLater {
        LafManager.registerInitTask { _, defaults ->
            defaults[ComponentFactory.COMPONENT_FACTORY_KEY] = object : DefaultComponentFactory() {
                override fun createDividerBorder(title: String?): Border {
                    return BorderFactory.createTitledBorder(DarkBorders.createTopBorder(), title)
                }

                override fun createScrollPane(content: JComponent): WrappedComponent<JScrollPane> {
                    val overlayScroll = OverlayScrollPane(content)
                    return DefaultWrappedComponent(overlayScroll.scrollPane, overlayScroll)
                }
            }
            defaults["CheckBox.visualInsets"] = Insets(0, 20, 0, 0)
        }
        LafManager.installTheme(DarculaTheme())
        frame {
            defaultCloseOperation = CloseOperation.EXIT
            contentPane = borderPanel {
                north = centered { JLabel("North") }
                val content = panel {
                    hideableRow("Row 1", startHidden = false) {
                        row {
                            label("Hello Row 1")
                        }
                        row {
                            checkBox("Check", model::boolValue)
                        }
                        row {
                            textField(model::textValue)
                            commentRow(
                                """
                                This is a comment
                                This is a comment
                                This is a comment
                                This is a comment
                                """.trimIndent()
                            )
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
                center = content
                south = panel {
                    row {
                        right {
                            cell {
                                button("Apply") { content.apply() }
                                button("Reset") { content.reset() }
                            }
                        }
                    }
                }
            }
            locationByPlatform = true
            locationRelativeTo = null
            visible = true
        }
    }
}
