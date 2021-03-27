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
package com.github.weisj.swingdsl.text

import java.util.*

/**
 * Wrapper class to dynamically update a [ResourceBundle] based on the current [Locale]
 */
class DynamicResourceBundle(private val bundleName: String) {
    private var currentLocale: Locale = Locale.getDefault()
    private var _bundle: ResourceBundle = ResourceBundle.getBundle(bundleName, currentLocale)
    val bundle: ResourceBundle
        get() = fetchBundle()

    /**
     * Returns the bundle for the current [Locale] as determined by [Locale.getDefault].
     *
     * @return the resource bundle for the current [Locale].
     */
    private fun fetchBundle(): ResourceBundle {
        synchronized(this) {
            val defaultLocale = Locale.getDefault()
            if (defaultLocale != currentLocale) {
                _bundle = ResourceBundle.getBundle(bundleName, defaultLocale)
                currentLocale = defaultLocale
            }
            return bundle
        }
    }

    override fun toString(): String {
        return "DynamicResourceBundle{bundleName='$bundleName'}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as DynamicResourceBundle
        return bundleName == that.bundleName
    }

    override fun hashCode(): Int {
        return Objects.hashCode(bundleName)
    }
}
