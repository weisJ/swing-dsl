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

import java.util.concurrent.atomic.AtomicInteger
import javax.swing.SwingWorker

fun interface BackgroundTask<T> {
    fun run(publisher: (T) -> Unit)
}

class BackgroundWorker {
    @PublishedApi
    internal val counter: AtomicInteger = AtomicInteger(0)
    val isBusy: Boolean
        get() = counter.get() > 0

    inline fun <T> run(
        task: BackgroundTask<T>,
        crossinline processor: (T) -> Unit,
        crossinline finalizer: () -> Unit = {}
    ) {
        runAggregated(task, { it.forEach(processor) }, finalizer)
    }

    inline fun <T> runAggregated(
        task: BackgroundTask<T>,
        crossinline processor: (List<T>) -> Unit,
        crossinline finalizer: () -> Unit = {}
    ) {
        counter.getAndIncrement()
        val worker = object : SwingWorker<Unit, T>() {
            override fun doInBackground() {
                task.run { publish(it) }
            }

            override fun process(chunks: MutableList<T>?) {
                chunks ?: return
                processor(chunks)
            }

            override fun done() {
                counter.getAndDecrement()
                finalizer()
            }
        }
        worker.execute()
    }
}
