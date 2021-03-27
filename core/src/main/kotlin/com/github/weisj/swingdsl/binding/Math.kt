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
package com.github.weisj.swingdsl.binding

@JvmName("incInt")
operator fun ObservableMutableProperty<Int>.inc(): ObservableMutableProperty<Int> {
    set(get() + 1)
    return this
}

@JvmName("decInt")
operator fun ObservableMutableProperty<Int>.dec(): ObservableMutableProperty<Int> {
    set(get() - 1)
    return this
}

@JvmName("incByte")
operator fun ObservableMutableProperty<Byte>.inc(): ObservableMutableProperty<Byte> {
    set((get() + 1).toByte())
    return this
}

@JvmName("decByte")
operator fun ObservableMutableProperty<Byte>.dec(): ObservableMutableProperty<Byte> {
    set((get() - 1).toByte())
    return this
}

@JvmName("incShort")
operator fun ObservableMutableProperty<Short>.inc(): ObservableMutableProperty<Short> {
    set((get() + 1).toShort())
    return this
}

@JvmName("decShort")
operator fun ObservableMutableProperty<Short>.dec(): ObservableMutableProperty<Short> {
    set((get() - 1).toShort())
    return this
}

@JvmName("incLong")
operator fun ObservableMutableProperty<Long>.inc(): ObservableMutableProperty<Long> {
    set((get() + 1))
    return this
}

@JvmName("decLong")
operator fun ObservableMutableProperty<Long>.dec(): ObservableMutableProperty<Long> {
    set((get() - 1))
    return this
}
