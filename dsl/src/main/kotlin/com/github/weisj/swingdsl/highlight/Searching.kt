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
package com.github.weisj.swingdsl.highlight

import com.github.weisj.swingdsl.binding.Property
import me.xdrop.fuzzywuzzy.ratios.PartialRatio

interface Searchable<T> {
    val data: T
    val tag: LayoutTag
    fun evaluateMatch(searchQuery: String): Int
}

data class StringSearchable<T>(
    val property: Property<String>,
    override val data: T,
    override val tag: LayoutTag
) : Searchable<T> {

    companion object {
        private val matchingAlgorithm = PartialRatio()
    }

    override fun evaluateMatch(searchQuery: String): Int {
        // Note: The #toLowerCase calls could probably be optimized away.
        return matchingAlgorithm.apply(searchQuery.toLowerCase(), property.get().toLowerCase())
    }
}

interface SearchContext<T> {
    val searchIndex: List<Searchable<T>>
    fun register(searchable: Searchable<T>)
}

interface SearchPresenter<T, ResultType : SearchResult<T>> {
    fun hideSearch()
    fun displaySearchResult(result: ResultType)
}

interface SearchPointSink<T> {
    fun onSearchPointCreated(prop: T, tag: LayoutTag)
}

typealias StringSearchPointSink = SearchPointSink<Property<String>>

internal class ContextBasedStringSearchPointSink<T>(
    private val context: SearchContext<T>,
    private val element: T
) : StringSearchPointSink {
    override fun onSearchPointCreated(prop: Property<String>, tag: LayoutTag) {
        context.register(StringSearchable(prop, element, tag))
    }
}

fun <T> SearchContext<T>.createSink(element: T): StringSearchPointSink =
    ContextBasedStringSearchPointSink(this, element)

class DefaultSearchContext<T> : SearchContext<T> {
    private val searchIndexInternal: MutableList<Searchable<T>> = mutableListOf()
    override val searchIndex: List<Searchable<T>>
        get() = searchIndexInternal

    override fun register(searchable: Searchable<T>) {
        searchIndexInternal.add(searchable)
    }
}

data class SearchResultItem<T>(
    val searchable: Searchable<T>,
    val score: Int
)

interface SearchResult<T> {
    val entries: List<SearchResultItem<T>>
}

data class DefaultSearchResult<T>(
    override val entries: List<SearchResultItem<T>>
) : SearchResult<T>

fun <T> SearchContext<T>.search(searchTerm: String, scoreThreshold: Int = 70): SearchResult<T> {
    val aggregate = mutableListOf<SearchResultItem<T>>()
    searchIndex.forEach {
        val score = it.evaluateMatch(searchTerm)
        if (score > scoreThreshold) aggregate.add(SearchResultItem(it, score))
    }
    aggregate.sortByDescending { it.score }
    return DefaultSearchResult(aggregate)
}
