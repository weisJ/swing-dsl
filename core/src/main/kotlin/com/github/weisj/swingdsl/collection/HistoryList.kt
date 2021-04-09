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
package com.github.weisj.swingdsl.collection

class HistoryList<T : Any> {
    private var current: Node<T>? = null

    fun add(item: T) {
        val cur = current
        val node = Node(item, cur, null)
        val currentNext = cur?.next
        currentNext?.prev = null
        cur?.next = node
        current = node
    }

    fun isEmpty(): Boolean = current == null

    fun currentOrNull(): T? {
        return current?.item
    }

    fun current(): T {
        return currentOrNull()!!
    }

    /**
     * Go back in the history and return the new current element.
     */
    fun back(): T {
        val cur = current
        check(cur != null) { "History is empty" }
        current = cur.prev
        return current!!.item
    }

    fun peekBack(): T {
        return peekBackOrNull()!!
    }

    fun peekBackOrNull(): T? {
        return current?.prev?.item
    }

    fun backOrNull(): T? {
        return if (canGoBack()) back() else null
    }

    fun canGoBack(): Boolean = !isEmpty()

    /**
     * Move forward in the history and return the new current element.
     */
    fun forward(): T {
        val cur = current
        check(cur != null) { "History is empty " }
        check(cur.next != null) { "Can't move forward. No next element found." }
        current = cur.next
        return current!!.item
    }

    fun peekForward(): T {
        return peekForwardOrNull()!!
    }

    fun peekForwardOrNull(): T? {
        return current?.next?.item
    }

    fun forwardOrNull(): T? {
        return if (canGoBack()) forward() else null
    }

    fun canGoForward(): Boolean = current?.next != null
}

class Node<T>(val item: T, var prev: Node<T>?, var next: Node<T>?)
