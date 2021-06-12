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

import java.beans.Introspector
import java.lang.reflect.Field
import java.lang.reflect.Method

internal fun collectAllMethodsRecursively(clazz: Class<*>): Sequence<Method> = sequence {
    var cl: Class<*>? = clazz
    while (cl != null) {
        for (declaredMethod in cl.declaredMethods) {
            yield(declaredMethod)
        }
        cl = cl.superclass
    }
}

internal fun collectAllFieldsRecursivelyWithClass(clazz: Class<*>): Sequence<Pair<Field, Class<*>>> = sequence {
    var cl: Class<*>? = clazz
    while (cl != null) {
        for (field in cl.declaredFields) {
            yield(field to cl)
        }
        cl = cl.superclass
    }
}

internal fun collectAllFieldsRecursively(clazz: Class<*>): Sequence<Field> = sequence {
    var cl: Class<*>? = clazz
    while (cl != null) {
        for (field in cl.declaredFields) {
            yield(field)
        }
        cl = cl.superclass
    }
}

internal inline fun <reified Target : Any, reified Value : Any> getFieldValue(obj: Target, name: String): Value? {
    return getFieldValue(Target::class.java, Value::class.java, obj, name)
}

internal fun <Target : Any, Value : Any> getFieldValue(
    targetClass: Class<Target>,
    valueClass: Class<Value>,
    obj: Target,
    name: String
): Value? {
    return collectAllFieldsRecursively(targetClass)
        .firstOrNull { it.name == name && valueClass.isAssignableFrom(it.type) }
        ?.runCatching {
            isAccessible = true
            valueClass.cast(get(obj))
        }?.getOrNull()
}

fun getPropertyName(methodName: String): String? {
    if (methodName.startsWith("get")) {
        return Introspector.decapitalize(methodName.substring(3))
    }
    if (methodName.startsWith("is")) {
        return Introspector.decapitalize(methodName.substring(2))
    }
    return if (methodName.startsWith("set")) {
        Introspector.decapitalize(methodName.substring(3))
    } else null
}
