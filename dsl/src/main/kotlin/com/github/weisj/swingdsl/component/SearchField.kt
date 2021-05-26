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
package com.github.weisj.swingdsl.component

import com.github.weisj.swingdsl.FocusState
import com.github.weisj.swingdsl.addDocumentChangeListener
import com.github.weisj.swingdsl.highlight.SearchPresenter
import com.github.weisj.swingdsl.highlight.SearchResult
import com.github.weisj.swingdsl.on
import com.github.weisj.swingdsl.properties
import com.github.weisj.swingdsl.toKeyStroke
import com.github.weisj.swingdsl.yieldFocus
import java.awt.event.KeyEvent
import javax.swing.JTextField

class SearchField<T, ResultType : SearchResult<T>>(
    private val searchFunction: (String) -> ResultType,
    private val searchPresenter: SearchPresenter<T, ResultType>
) : JTextField() {

    init {
        columns = 10
        minimumSize = preferredSize
        properties {
            client["JTextField.variant"] = "search"
        }
        addDocumentChangeListener {
            val searchResult = searchFunction(text)
            if (searchResult.entries.isEmpty() && text.length < 5) {
                searchPresenter.hideSearch()
                return@addDocumentChangeListener
            }
            searchPresenter.displaySearchResult(searchResult)
        }
        on(KeyEvent.VK_F.toKeyStroke(KeyEvent.CTRL_DOWN_MASK), focusState = FocusState.IN_FOCUSED_WINDOW) {
            requestFocus(true)
        }
        on(KeyEvent.VK_ESCAPE.toKeyStroke(), focusState = FocusState.FOCUSED) {
            yieldFocus()
        }
    }
}
