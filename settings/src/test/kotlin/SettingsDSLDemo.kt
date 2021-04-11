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
import com.github.weisj.swingdsl.frame
import com.github.weisj.swingdsl.invokeLater
import com.github.weisj.swingdsl.settings.Category
import com.github.weisj.swingdsl.settings.SubCategoryBuilder
import com.github.weisj.swingdsl.settings.boolean
import com.github.weisj.swingdsl.settings.category
import com.github.weisj.swingdsl.settings.choice
import com.github.weisj.swingdsl.settings.createSettingsPanel
import com.github.weisj.swingdsl.settings.int
import com.github.weisj.swingdsl.settings.string
import com.github.weisj.swingdsl.text.textOf
import com.github.weisj.swingdsl.unaryPlus
import java.awt.Dimension
import java.util.*

class TestData {

    enum class EnumValue {
        ENUM_1,
        ENUM_2,
        ENUM_3,
        ENUM_4,
        ENUM_5
    }

    enum class EnumValueSmall {
        ENUM_SMALL_1,
        ENUM_SMALL_2,
        ENUM_SMALL_3
    }

    var b1 = true
    var b2 = true
    var b3 = false

    var s1 = "Hello World!"
    var s2 = "Hello Settings!"
    var s3 = "Item 1"

    var i1 = 0
    var i2 = 2

    var e1 = EnumValue.ENUM_1
    var e2 = EnumValueSmall.ENUM_SMALL_1
}

fun makeCategory(name: String): Category {
    val data = TestData()
    return category(name) {
        group(identifier = "G0") {
            boolean(
                value = data::b1,
                name = textOf("String 2 enabled"),
                description = textOf("Test description")
            )
            boolean(value = data::b2, name = textOf("String Choice Group enabled"))
            boolean(value = data::b3, name = textOf("Group 3 enabled"))

            group(identifier = "G1", name = textOf("Group 1")) {
                string(name = textOf("String 1"), value = data::s1)
                string(
                    name = textOf("String 2"),
                    description = textOf(
                        """
                        Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod
                        tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.
                        At vero eos et accusam et
                        """.trimIndent()
                    ),
                    value = data::s2
                ) {
                    enableIf(data::b1.isTrue())
                }

                int(name = textOf("Integer"), value = data::i1, low = 0, high = 100)
            }
            group(
                identifier = "G2",
                name = textOf("Group 2 Choices"),
                description = textOf(
                    """
                    Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod
                    tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.
                    At vero eos et accusam et
                    """.trimIndent()
                )
            ) {
                choice(
                    name = textOf("Integer Choice"),
                    value = data::i2,
                    choices = (0..42).toList()
                )
                group(identifier = "G2.1") {
                    enableIf(data::b2.isTrue())
                    choice(
                        name = textOf("String choice"),
                        value = data::s3,
                        choices = (0..10).asSequence().map { "Item $it" }.toList()
                    )
                }
                choice(
                    name = textOf("Enum Choice"),
                    value = data::e1,
                    choices = TestData.EnumValue.values().asList()
                )
            }
            group(identifier = "G3", name = textOf("Group 3")) {
                enableIf(data::b3.isTrue())
                choice(
                    name = textOf("Small Enum Choice"),
                    value = data::e2,
                    choices = TestData.EnumValueSmall.values().asList(),
                    unwrapLimit = 3
                )
                string(name = textOf("String 1 again"), value = data::s1)
            }
        }
    }
}

fun SubCategoryBuilder.addNested(level: Int) {
    if (level == 0) return
    category("Level $level") {
        addNested(level - 1)
    }
}

fun main() {
    invokeLater {
        LafManager.install(DarculaTheme())
        frame {
            content {
                +createSettingsPanel(
                    category("Root") {
                        ('A'..'Z').forEach {
                            category(it.toString()) {
                                addNested(5)
                            }
                        }
                    },
                    makeCategory("Demo")
                )
            }
            minimumSize = Dimension(600, 300)
            visible = true
            // LayoutUtil.setGlobalDebugMillis(300)
        }
    }
}
