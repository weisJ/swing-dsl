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
package com.github.weisj.swingdsl.settings.ui

import com.github.weisj.swingdsl.core.binding.ObservableMutableProperty
import com.github.weisj.swingdsl.core.binding.ObservableProperty
import com.github.weisj.swingdsl.core.binding.bind
import com.github.weisj.swingdsl.core.binding.observableProperty
import com.github.weisj.swingdsl.highlight.ComponentHighlighter
import com.github.weisj.swingdsl.highlight.MaskedOvalPainter
import com.github.weisj.swingdsl.highlight.SearchPresenter
import com.github.weisj.swingdsl.highlight.SearchResult
import com.github.weisj.swingdsl.highlight.SearchResultItem
import com.github.weisj.swingdsl.highlight.search
import com.github.weisj.swingdsl.laf.WrappedComponent
import com.github.weisj.swingdsl.layered
import com.github.weisj.swingdsl.scrollPane
import com.github.weisj.swingdsl.settings.Category
import com.github.weisj.swingdsl.settings.Element
import com.github.weisj.swingdsl.settings.UIContext
import com.github.weisj.swingdsl.settings.getNearestCategory
import com.github.weisj.swingdsl.style.UIFactory
import com.github.weisj.swingdsl.unaryPlus
import javax.swing.JComponent
import javax.swing.JLayeredPane

class SettingsSearchResult internal constructor(
    override val entries: List<SearchResultItem<Element>>,
    val categories: Set<Category>,
    val bestMatch: SearchResultItem<Element>?
) : SearchResult<Element> {
    override fun toString(): String = "SettingsSearch[$entries]"
}

class SearchHandler(
    private val context: UIContext
) : SearchPresenter<Element, SettingsSearchResult> {
    private val highlighter: ComponentHighlighter = ComponentHighlighter(
        MaskedOvalPainter(lineColor = UIFactory.warningColor)
    )
    private val _result: ObservableMutableProperty<SettingsSearchResult?> = observableProperty(null)
    val result: ObservableProperty<SettingsSearchResult?> = _result

    init {
        highlighter.isVisible = false
        result.bind {
            if (it == null) {
                highlighter.targets = emptyList()
                highlighter.isVisible = false
            } else {
                highlighter.isVisible = true
                showHighlightsOf(context.currentPosition.get().category)
            }
        }
        context.currentPosition.onChange {
            if (!highlighter.isVisible) return@onChange
            showHighlightsOf(it.category)
        }
    }

    fun wrapInHighlightLayer(comp: JComponent): WrappedComponent<out JComponent> {
        return layered {
            layers[JLayeredPane.DEFAULT_LAYER] = scrollPane({
                verticalScrollBar.unitIncrement = 20
                horizontalScrollBar.unitIncrement = 100
            }) { +comp }
            layers[JLayeredPane.PALETTE_LAYER] = highlighter
        }
    }

    fun search(searchTerm: String): SettingsSearchResult {
        val result = context.search(searchTerm)
        val categories: Set<Category> = result.entries
            .asSequence()
            .mapNotNull { it.searchable.data.getNearestCategory() }
            .toSet()

        val bestMatch = if (result.entries.isEmpty()) null else {
            val firstResult = result.entries.first()
            var match: SearchResultItem<Element> = firstResult
            for (entry in result.entries) {
                if (entry.score < firstResult.score) break
                val category = entry.searchable.data.getNearestCategory()
                if (category == context.currentPosition.get().category) {
                    match = entry
                    break
                }
            }
            match
        }

        return SettingsSearchResult(result.entries, categories, bestMatch)
    }

    private fun showHighlightsOf(category: Category) {
        val searchResult = result.get() ?: return
        val highlightTargets = searchResult.entries.filter {
            it.searchable.data.getNearestCategory() == category
        }
        highlighter.targets = highlightTargets.map { it.searchable.tag }
    }

    override fun hideSearch() {
        _result.set(null)
    }

    override fun displaySearchResult(result: SettingsSearchResult) {
        _result.set(result)
    }
}
