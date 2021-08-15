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
package com.github.weisj.swingdsl.inspector

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class ClassNameRenderTest {

    @Test
    fun `render simple class`() {
        assertEquals("TestClass", TestClass::class.java.readableClassName)
    }

    @Test
    fun `render nested class`() {
        assertEquals("TestClass2.Nested", TestClass2.Nested::class.java.readableClassName)
    }

    @Test
    fun `render anonymous class`() {
        assertEquals("TestClass in TestClass.Companion", TestClass.getClass()::class.java.readableClassName)
    }

    @Test
    fun `render anonymous class in nested`() {
        assertEquals(
            "TestClass in TestClass2.Nested.Companion",
            TestClass2.Nested.getClass()::class.java.readableClassName
        )
    }

    @Test
    fun `render anonymous nested class`() {
        assertEquals(
            "TestClass2.Nested in TestClass.Companion",
            TestClass.getNestedClass()::class.java.readableClassName
        )
    }

    @Test
    fun `render anonymous nested class in nested`() {
        assertEquals(
            "TestClass2.Nested in TestClass2.Nested.Companion",
            TestClass2.Nested.getNestedClass()::class.java.readableClassName
        )
    }

    @Test
    fun `render anonymous in anonymous`() {
        assertEquals(
            "TestClass2.Nested in TestClass",
            TestClass2.Nested.getClass().getNestedClass()::class.java.readableClassName
        )
    }
}

internal open class TestClass {
    companion object {
        fun getClass() = object : TestClass() {}
        fun getNestedClass() = object : TestClass2.Nested() {}
    }

    fun getNestedClass(): TestClass2.Nested {
        return object : TestClass2.Nested() {}
    }
}

internal class TestClass2 {
    open class Nested {
        companion object {
            fun getClass() = object : TestClass() {}
            fun getNestedClass() = object : Nested() {}
        }
    }
}
