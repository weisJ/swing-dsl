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
package com.github.weisj.swingdsl.components.file

import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.stream.Stream
import javax.swing.Icon
import javax.swing.filechooser.FileSystemView

open class FileNode internal constructor(
    file: File?,
    val path: Path?,
    private val pathName: String? = file?.absolutePath
) : Comparable<FileNode> {

    companion object {
        private val LOCK_COUNT = AtomicInteger(0)
    }

    constructor(path: Path) : this(null, path)
    constructor(file: File) : this(file, file.safeToPath())

    private var safeFile: File? = file
    val file: File?
        get() {
            if (safeFile == null) {
                safeFile = getFile(true)
            }
            return safeFile
        }

    private fun getFile(withLock: Boolean): File? {
        if (safeFile != null) return safeFile
        return if (!withLock || LOCK_COUNT.get() == 0) {
            path?.toFile()
        } else null
    }

    init {
        safeFile = file
    }

    internal fun isSafeToPreload(): Boolean {
        /*
         * FileNodes without a path may block the file system from
         * listing other directories or supplying icons.
         * This is especially common for network drives.
         */
        if (path == null) return false
        // Special case iCloud for now. Listing its content causes online only files to be downloaded.
        if (path.name == "iCloud Drive") return false
        return true
    }

    private var isEmpty: Boolean = false
    private var isValid: Boolean = false
        set(value) {
            field = value
            if (!value) {
                icon = null
            }
        }

    val isDirectory: Boolean
        get() = if (path != null) Files.isDirectory(path) else safeFile?.isDirectory == true

    val isExistent: Boolean
        get() = if (path != null) Files.exists(path) else safeFile?.exists() == true

    val isNonExistent: Boolean
        get() = if (path != null) Files.notExists(path) else safeFile?.exists() == false

    val isReadable: Boolean
        get() = if (path != null) Files.isReadable(path) else safeFile?.canRead() == true

    val isHidden: Boolean
        get() = path.isHidden() || (file?.isHidden == true)

    internal fun isHiddenImpl(needsLocking: Boolean = true): Boolean {
        return if (needsLocking) {
            path.isHidden() || (file?.isHidden == true)
        } else {
            path.isHidden() || (getFile(false)?.isHidden == true)
        }
    }

    fun isEmpty(showHiddenFiles: Boolean): Boolean {
        return if (path != null && validateEmptyFlag(showHiddenFiles)) {
            isEmpty
        } else safeFile?.isDirectory != true
    }

    private fun validateEmptyFlag(showHiddenFiles: Boolean): Boolean {
        if (!isValid && path != null) {
            runCatching {
                Files.list(path).use { s ->
                    isEmpty = s
                        .filter { Files.isReadable(it) }
                        .noneMatch { showHiddenFiles || !it.isHidden() }
                    isValid = true
                }
            }
        }
        return isValid
    }

    @Throws(IOException::class)
    fun list(model: FileTreeModel): Stream<FileNode> = when {
        path != null -> Files.list(path).map { FileNode(it) }
        safeFile != null -> {
            LOCK_COUNT.getAndIncrement()
            val files = model.fsv.getFiles(file, !model.isShowHiddenFiles)
            Arrays.stream(files).map { FileNode(it) }.onClose { LOCK_COUNT.getAndDecrement() }
        }
        else -> Stream.empty()
    }

    // Appearance properties
    private var icon: Icon? = null
    fun getSystemDisplayName(fsv: FileSystemView): String? {
        val f: File = file ?: return path?.fileName?.toString() ?: pathName
        return fsv.getSystemDisplayName(f)
    }

    fun getSystemIcon(fsv: FileSystemView): Icon? {
        if (icon == null) {
            icon = fsv.getSystemIcon(file)
        }
        return icon
    }

    fun invalidate() {
        isValid = false
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val on = other as FileNode
        return if (path != null && on.path != null) path == on.path else pathName == other.pathName
    }

    override fun hashCode(): Int {
        return path?.hashCode() ?: Objects.hashCode(pathName)
    }

    override fun toString(): String {
        return path?.toString() ?: pathName ?: "null node"
    }

    override operator fun compareTo(other: FileNode): Int {
        return if (path != null && other.path != null) {
            path.compareTo(other.path)
        } else pathName!!.compareTo(other.pathName!!)
    }
}

open class NullFileNode internal constructor(name: String? = null) :
    FileNode(null, null, pathName = name)

private fun File?.safeToPath(): Path? = runCatching { this?.toPath() }.getOrNull()

internal fun Path?.isHidden(): Boolean = runCatching {
    if (this != null) Files.isHidden(this) else false
}.getOrElse { e ->
    e.printStackTrace()
    false
}

private val Path.name: String
    get() = fileName?.toString().orEmpty()
