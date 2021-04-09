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
import com.github.weisj.darklaf.theme.info.PreferredThemeStyle
import com.github.weisj.swingdsl.binding.ObservableProperty
import com.github.weisj.swingdsl.binding.container.observableListOf
import com.github.weisj.swingdsl.binding.ensureMaxCapacity
import com.github.weisj.swingdsl.binding.getValue
import com.github.weisj.swingdsl.binding.greaterThan
import com.github.weisj.swingdsl.binding.isEmpty
import com.github.weisj.swingdsl.binding.isNotEmpty
import com.github.weisj.swingdsl.binding.lessThan
import com.github.weisj.swingdsl.binding.observableProperty
import com.github.weisj.swingdsl.binding.plus
import com.github.weisj.swingdsl.binding.setValue
import com.github.weisj.swingdsl.binding.withFallback
import com.github.weisj.swingdsl.condition.and
import com.github.weisj.swingdsl.config.CloseOperation
import com.github.weisj.swingdsl.frame
import com.github.weisj.swingdsl.invokeLater
import com.github.weisj.swingdsl.layout.observableIntValue
import com.github.weisj.swingdsl.layout.observableSelection
import com.github.weisj.swingdsl.layout.panel
import com.github.weisj.swingdsl.listModelOf
import com.github.weisj.swingdsl.observableSelection
import com.github.weisj.swingdsl.text.textOf
import com.github.weisj.swingdsl.toKeyStroke
import java.awt.event.KeyEvent
import java.util.*

class GroceryList {
    val notes = observableListOf("A", "B", "C", "D")
    var input by observableProperty("")

    private fun addNote(note: String) {
        notes.add(note)
    }

    fun addCurrentNote() {
        val txt = input.trim()
        if (txt.isNotEmpty()) {
            addNote(txt)
        }
        input = ""
    }
}

fun main() {
    val groceryList = GroceryList()
    invokeLater {
        LafManager.installTheme(
            LafManager.getPreferredThemeStyle().let {
                PreferredThemeStyle(it.contrastRule, it.colorToneRule)
            }
        )
        frame {
            content {
                panel {
                    lateinit var listSelection: ObservableProperty<IntArray>
                    lateinit var maxCount: ObservableProperty<Int>
                    row {
                        cell {
                            label("List capacity:")
                            maxCount = spinner(initial = 3, minValue = 0, maxValue = Int.MAX_VALUE).observableIntValue()
                            groceryList.notes.ensureMaxCapacity(maxCount)
                        }
                    }
                    row {
                        label(textOf("Currently Todo: ") + groceryList.notes.observables[0].withFallback(""))
                    }
                    row {
                        val list = list(listModelOf(groceryList.notes)).component
                        listSelection = list.observableSelection()
                        (listSelection.isEmpty() and (groceryList.notes.observables.size greaterThan 0)).onChange {
                            list.selectedIndex = 0
                        }
                    }
                    row {
                        cell {
                            val sizeRestriction = groceryList.notes.observables.size lessThan maxCount
                            textField(groceryList::input)() {
                                commitImmediately()
                                on(KeyEvent.VK_ENTER.toKeyStroke()) { groceryList.addCurrentNote() }
                                enableIf(sizeRestriction)
                            }
                            button("Add") {
                                groceryList.addCurrentNote()
                            }.enableIf(sizeRestriction)
                            button("Remove") {
                                groceryList.notes.removeAllAt(listSelection.get())
                            }.enableIf(listSelection.isNotEmpty())
                        }
                    }
                }
            }
            title = "Grocery List"
            defaultCloseOperation = CloseOperation.EXIT
            centerOnScreen()
            visible = true
        }
    }
}
