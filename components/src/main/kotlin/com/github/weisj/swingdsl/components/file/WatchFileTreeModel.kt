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
package com.github.weisj.swingdsl.dsl.components.file

import java.io.IOException
import java.nio.file.*
import java.util.*
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import java.util.logging.Logger
import javax.swing.filechooser.FileSystemView

class WatchFileTreeModel(
    fsv: FileSystemView,
    isShowHiddenFiles: Boolean,
    roots: List<FileNode>?
) : FileTreeModel(fsv, isShowHiddenFiles, roots) {

    // Gets initialized in beforeInit, which amounts to an early initialization.
    private lateinit var nodeMap: MutableMap<Watchable, FileTreeNode>
    private var watchService: WatchService? = null

    private val isScheduled = AtomicBoolean(false)
    private var watchTask: ScheduledFuture<*>? = null

    override fun beforeInit() {
        watchService = createWatchService()
        nodeMap = Collections.synchronizedMap(HashMap())
    }

    fun startWatching() {
        synchronized(this) {
            if (watchTask != null) return
            isScheduled.set(true)
            watchTask = SCHEDULER.schedule(this::watch, 0, TimeUnit.SECONDS)
        }
    }

    fun stopWatching() {
        synchronized(this) {
            if (watchTask != null) {
                isScheduled.set(false)
                watchTask!!.cancel(true)
                watchTask = null
            }
        }
    }

    private fun watch() {
        while (isScheduled.get()) {
            val key: WatchKey = try {
                watchService!!.take()
            } catch (x: InterruptedException) {
                x.printStackTrace()
                return
            }
            val parent = nodeMap[key.watchable()]
            if (parent != null) {
                LOGGER.fine { "Event for \"$parent\"" }
                parent.parent?.reload(1)
                parent.reload(0)
            }
            val watchEventList = key.pollEvents()
            for (event in watchEventList) {
                val kind = event.kind()
                val path = event.context() as Path
                if (kind === StandardWatchEventKinds.OVERFLOW) {
                    continue
                }
                LOGGER.finer("Event Type " + kind.name())
                val node = nodeMap[(key.watchable() as Path).resolve(path)]
                LOGGER.finer { "Affected node \"$node\"" }
                node?.reload(0)
            }
            key.reset()
        }
    }

    override fun register(node: FileTreeNode) {
        synchronized(this) {
            val ws = watchService
            if (ws == null || !node.fileNode.isDirectory) return
            val path = node.fileNode.path ?: return
            if (nodeMap.containsKey(path)) return
            try {
                LOGGER.finer { "Register watch service for \"$node\"" }
                node.watchKey = path.register(
                    ws, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY
                )
                nodeMap.put(path, node)
            } catch (ignored: IOException) {
                // Theres nothing we can do. File is simply not watched.
            }
        }
    }

    override fun unregister(node: FileTreeNode) {
        synchronized(this) {
            val key = node.watchKey ?: return
            LOGGER.finer { "Unregister watch service for \"$node\"" }
            node.fileNode.path?.let {
                nodeMap.remove(it)
            }
            key.cancel()
        }
    }

    companion object {
        private val LOGGER = Logger.getLogger(WatchFileTreeModel::class.java.name)
        private val SCHEDULER = createScheduler()
        private fun createWatchService(): WatchService? = kotlin.runCatching {
            FileSystems.getDefault().newWatchService()
        }.getOrNull()

        private fun createScheduler(): ScheduledExecutorService {
            val executor = ScheduledThreadPoolExecutor(1) { r: Runnable? ->
                val thread = Thread(r, "File Tree Watch Thread")
                thread.isDaemon = true
                thread.priority = Thread.MIN_PRIORITY
                thread
            }
            executor.continueExistingPeriodicTasksAfterShutdownPolicy = false
            executor.executeExistingDelayedTasksAfterShutdownPolicy = false
            return executor
        }
    }
}
