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
package com.github.weisj.swingdsl.settings.panel

import com.github.weisj.swingdsl.highlight.ComponentHighlighter
import com.github.weisj.swingdsl.highlight.MaskedOvalPainter
import com.github.weisj.swingdsl.highlight.SearchContext
import com.github.weisj.swingdsl.highlight.SearchResult
import com.github.weisj.swingdsl.highlight.SearchResultItem
import com.github.weisj.swingdsl.highlight.Searchable
import com.github.weisj.swingdsl.highlight.search
import com.github.weisj.swingdsl.settings.Category
import com.github.weisj.swingdsl.settings.Element
import com.github.weisj.swingdsl.settings.UIContext
import com.github.weisj.swingdsl.settings.getNearestCategory
import java.awt.Color

typealias SettingsSearchContext = SearchContext<Element>

class SettingsSearchResult internal constructor(
    override val entries: List<SearchResultItem<Element>>,
    val categories: Set<Category>
) : SearchResult<Element> {
    override fun toString(): String = "SettingsSearch[$entries]"
}

class SearchHandler(
    private val searchContext: UIContext
) {
    val highlighter: ComponentHighlighter = ComponentHighlighter(
        MaskedOvalPainter(
            lineColor = Color(199, 134, 7, 170)
        )
    )
    var result: SettingsSearchResult? = null
        set(value) {
            field = value
            if (value == null) {
                highlighter.targets = emptyList()
                highlighter.isVisible = false
            } else {
                highlighter.isVisible = true
            }
        }

    init {
        highlighter.isVisible = false
    }

    fun search(searchTerm: String): SettingsSearchResult {
        val result = searchContext.search(searchTerm)
        val categories: Set<Category> = result.entries
            .asSequence()
            .mapNotNull { it.searchable.data.getNearestCategory() }
            .toSet()
        return SettingsSearchResult(result.entries, categories)
    }

    fun showHighlightsOf(category: Category) {
        val searchResult = result ?: return
        val highlightTargets = searchResult.entries.filter {
            it.searchable.data.getNearestCategory() == category
        }
        highlighter.targets = highlightTargets.map { it.searchable.tag }
    }
}
