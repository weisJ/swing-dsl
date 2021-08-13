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
package com.github.weisj.swingdsl.core.text

/**
 * Text that delegates to a resource bundle hence changes its value based on the current locale.
 */
data class InternationalizedText(private val bundle: DynamicResourceBundle, private val resourceString: String) : Text {
    /**
     * Creates a new [InternationalizedText] which changes its value based on the current locale.
     *
     * @param bundleName the resource bundle name
     * @param resourceString the resource key in the bundle
     */
    constructor(bundleName: String, resourceString: String) : this(DynamicResourceBundle(bundleName), resourceString)

    override fun toString(): String = get()

    override fun get(): String = bundle.bundle.getString(resourceString)

    override fun onChange(observeKey: Any?, callback: (String) -> Unit) {
        Locales.registerListener(observeKey ?: callback) { callback(get()) }
    }

    override fun removeCallback(observeKey: Any?) {
        Locales.removeListener(observeKey ?: Any())
    }
}
