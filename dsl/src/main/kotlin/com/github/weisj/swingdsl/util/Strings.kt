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
package com.github.weisj.swingdsl.util

private val REPLACES_REFS = listOf("&lt;", "&gt;", "&amp;", "&#39;", "&quot;")
private val REPLACES_DISP = listOf("<", ">", "&", "'", "\"")

fun String.escapeXmlEntities(): String = replace(this, REPLACES_DISP, REPLACES_REFS)
fun String.unescapeXmlEntities(): String = replace(this, REPLACES_REFS, REPLACES_DISP)

fun String.toHtml(wrapperTag: String? = null, tagOptions: String? = null, escapeExistingHtml: Boolean = true): String {
    val startTag = wrapperTag?.let { "<$it ${tagOptions ?: ""}>" } ?: ""
    val closeTag = wrapperTag?.let { "</$it>" } ?: ""
    return if (this.startsWith("<html>")) {
        replace(this, listOf("<html>", "</html>"), listOf("<html>$startTag", "$closeTag</html>"))
    } else {
        "<html>$startTag${if (escapeExistingHtml) this.escapeXmlEntities() else this}$closeTag</html>"
    }
}

fun replace(text: String, from: List<String>, to: List<String?>): String {
    assert(from.size == to.size)
    var result: StringBuilder? = null
    var i = 0
    val textLength = text.length
    outer@ while (i < textLength) {
        inner@ for ((toReplace, replaceWith) in from.zip(to)) {
            if (toReplace.isEmpty()) continue@inner
            val len = toReplace.length
            if (text.regionMatches(i, toReplace, 0, len)) {
                if (result == null) result = StringBuilder(text.length).append(text, 0, i)
                result!!.append(replaceWith)
                i += len
                continue@outer
            }
        }
        result?.append(text[i])
        i++
    }
    return result?.toString() ?: text
}
