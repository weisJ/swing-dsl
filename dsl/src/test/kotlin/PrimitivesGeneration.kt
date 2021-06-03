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
import com.github.weisj.swingdsl.core.binding.ObservableProperty
import com.github.weisj.swingdsl.util.toCapitalized
import java.io.File
import java.util.*
import kotlin.reflect.KClass

enum class Operation(
    val opName: String,
    val isOperator: Boolean,
    val impl: (String, String) -> String,
    val isUnary: Boolean = false,
) {
    IDENTITY("identity", true, { a, _ -> a }),

    ADD("plus", true, { a, b -> "$a + $b" }),
    MINUS("minus", true, { a, b -> "$a - $b" }),
    TIMES("times", true, { a, b -> "$a * $b" }),
    DIV("div", true, { a, b -> "$a / $b" }),
    REM("rem", true, { a, b -> "$a % $b" }),
    RANGE("rangeTo", true, { a, b -> "$a..$b" }),
    COMPARE("comparedTo", false, { a, b -> "$a.compareTo($b)" }),
    LESS("lessThan", false, { a, b -> "$a < $b" }),
    LESS_EQUALS("lessThanOrEquals", false, { a, b -> "$a <= $b" }),
    GREATER("greaterThan", false, { a, b -> "$a > $b" }),
    GREATER_EQUALS("greaterThanOrEquals", false, { a, b -> "$a >= $b" }),

    // Unary operations
    UNARY_PLUS("unaryPlus", true, { a, _ -> a }, isUnary = true),
    UNARY_MINUS("unaryMinus", true, { a, _ -> "-$a" }, isUnary = true),

    // Conversions
    TO_BYTE("toByte", false, { a, _ -> "$a.toByte()" }, isUnary = true),
    TO_CHAR("toChar", false, { a, _ -> "$a.toChar()" }, isUnary = true),
    TO_SHORT("toShort", false, { a, _ -> "$a.toShort()" }, isUnary = true),
    TO_INT("toInt", false, { a, _ -> "$a.toInt()" }, isUnary = true),
    TO_LONG("toLong", false, { a, _ -> "$a.toLong()" }, isUnary = true),
    TO_FLOAT("toFloat", false, { a, _ -> "$a.toFloat()" }, isUnary = true),
    TO_DOUBLE("toDouble", false, { a, _ -> "$a.toDouble()" }, isUnary = true),
}

fun getConversionTo(type: KClass<*>): Operation = when (type) {
    Byte::class -> Operation.TO_BYTE
    Char::class -> Operation.TO_CHAR
    Short::class -> Operation.TO_SHORT
    Int::class -> Operation.TO_INT
    Long::class -> Operation.TO_LONG
    Float::class -> Operation.TO_FLOAT
    Double::class -> Operation.TO_DOUBLE
    else -> error("Can't convert to $type")
}

fun Operation.isConversion(): Boolean = when (this) {
    Operation.TO_BYTE, Operation.TO_CHAR, Operation.TO_SHORT, Operation.TO_INT, Operation.TO_LONG, Operation.TO_FLOAT, Operation.TO_DOUBLE -> true
    else -> false
}

fun Operation.isUnary(): Boolean = when (this) {
    Operation.UNARY_PLUS, Operation.UNARY_MINUS -> true
    else -> false
}

val deprecatedOps = mapOf(
    Float::class to listOf(Operation.TO_BYTE, Operation.TO_SHORT, Operation.TO_CHAR),
    Double::class to listOf(Operation.TO_BYTE, Operation.TO_SHORT, Operation.TO_CHAR),
    Int::class to listOf(Operation.TO_CHAR),
    Byte::class to listOf(Operation.TO_CHAR),
    Short::class to listOf(Operation.TO_CHAR),
    Long::class to listOf(Operation.TO_CHAR),
    Char::class to listOf(Operation.TO_CHAR),
)

data class Op(val type: Operation, val inType: KClass<*>, val outType: KClass<*>)

val comparisons = listOf(
    Op(Operation.COMPARE, Byte::class, Int::class),
    Op(Operation.LESS, Byte::class, Boolean::class),
    Op(Operation.LESS_EQUALS, Byte::class, Boolean::class),
    Op(Operation.GREATER, Byte::class, Boolean::class),
    Op(Operation.GREATER_EQUALS, Byte::class, Boolean::class),
    Op(Operation.COMPARE, Short::class, Int::class),
    Op(Operation.LESS, Short::class, Boolean::class),
    Op(Operation.LESS_EQUALS, Short::class, Boolean::class),
    Op(Operation.GREATER, Short::class, Boolean::class),
    Op(Operation.GREATER_EQUALS, Short::class, Boolean::class),
    Op(Operation.COMPARE, Int::class, Int::class),
    Op(Operation.LESS, Int::class, Boolean::class),
    Op(Operation.LESS_EQUALS, Int::class, Boolean::class),
    Op(Operation.GREATER, Int::class, Boolean::class),
    Op(Operation.GREATER_EQUALS, Int::class, Boolean::class),
    Op(Operation.COMPARE, Long::class, Int::class),
    Op(Operation.LESS, Long::class, Boolean::class),
    Op(Operation.LESS_EQUALS, Long::class, Boolean::class),
    Op(Operation.GREATER, Long::class, Boolean::class),
    Op(Operation.GREATER_EQUALS, Long::class, Boolean::class),
    Op(Operation.COMPARE, Float::class, Int::class),
    Op(Operation.LESS, Float::class, Boolean::class),
    Op(Operation.LESS_EQUALS, Float::class, Boolean::class),
    Op(Operation.GREATER, Float::class, Boolean::class),
    Op(Operation.GREATER_EQUALS, Float::class, Boolean::class),
    Op(Operation.COMPARE, Double::class, Int::class),
    Op(Operation.LESS, Double::class, Boolean::class),
    Op(Operation.LESS_EQUALS, Double::class, Boolean::class),
    Op(Operation.GREATER, Double::class, Boolean::class),
    Op(Operation.GREATER_EQUALS, Double::class, Boolean::class),
)

inline fun <reified intType : Number, reified longType : Number, reified floatType : Number> defaultAdd() = listOf(
    Op(Operation.ADD, Byte::class, intType::class),
    Op(Operation.ADD, Short::class, intType::class),
    Op(Operation.ADD, Int::class, intType::class),
    Op(Operation.ADD, Long::class, longType::class),
    Op(Operation.ADD, Float::class, floatType::class),
    Op(Operation.ADD, Double::class, Double::class),
)

inline fun <reified intType : Number, reified longType : Number, reified floatType : Number> defaultMinus() = listOf(
    Op(Operation.MINUS, Byte::class, intType::class),
    Op(Operation.MINUS, Short::class, intType::class),
    Op(Operation.MINUS, Int::class, intType::class),
    Op(Operation.MINUS, Long::class, longType::class),
    Op(Operation.MINUS, Float::class, floatType::class),
    Op(Operation.MINUS, Double::class, Double::class),
)

inline fun <reified intType : Number, reified longType : Number, reified floatType : Number> defaultTimes() = listOf(
    Op(Operation.TIMES, Byte::class, intType::class),
    Op(Operation.TIMES, Short::class, intType::class),
    Op(Operation.TIMES, Int::class, intType::class),
    Op(Operation.TIMES, Long::class, longType::class),
    Op(Operation.TIMES, Float::class, floatType::class),
    Op(Operation.TIMES, Double::class, Double::class),
)

inline fun <reified intType : Number, reified longType : Number, reified floatType : Number> defaultDiv() = listOf(
    Op(Operation.DIV, Byte::class, intType::class),
    Op(Operation.DIV, Short::class, intType::class),
    Op(Operation.DIV, Int::class, intType::class),
    Op(Operation.DIV, Long::class, longType::class),
    Op(Operation.DIV, Float::class, floatType::class),
    Op(Operation.DIV, Double::class, Double::class),
)

inline fun <reified intType : Number, reified longType : Number, reified floatType : Number> defaultRem() = listOf(
    Op(Operation.REM, Byte::class, intType::class),
    Op(Operation.REM, Short::class, intType::class),
    Op(Operation.REM, Int::class, intType::class),
    Op(Operation.REM, Long::class, longType::class),
    Op(Operation.REM, Float::class, floatType::class),
    Op(Operation.REM, Double::class, Double::class),
)

inline fun <reified T : Number> defaultUnary() = listOf(
    Op(Operation.UNARY_PLUS, Void::class, T::class),
    Op(Operation.UNARY_MINUS, Void::class, T::class),
)

inline fun <reified T : ClosedRange<*>> defaultRanges() = listOf(
    Op(Operation.RANGE, Byte::class, T::class),
    Op(Operation.RANGE, Short::class, T::class),
    Op(Operation.RANGE, Int::class, T::class),
    Op(Operation.RANGE, Long::class, LongRange::class),
)

val conversions = listOf(
    Op(Operation.TO_BYTE, Void::class, Byte::class),
    Op(Operation.TO_CHAR, Void::class, Char::class),
    Op(Operation.TO_SHORT, Void::class, Short::class),
    Op(Operation.TO_INT, Void::class, Int::class),
    Op(Operation.TO_LONG, Void::class, Long::class),
    Op(Operation.TO_FLOAT, Void::class, Float::class),
    Op(Operation.TO_DOUBLE, Void::class, Double::class),
)

inline fun <reified unaryType : Number, reified intType : Number, reified longType : Number, reified floatType : Number, reified rangeType : ClosedRange<*>> makeOpList(
    includeRanges: Boolean = true,
) = listOf(
    comparisons,
    defaultAdd<intType, longType, floatType>(),
    defaultMinus<intType, longType, floatType>(),
    defaultTimes<intType, longType, floatType>(),
    defaultDiv<intType, longType, floatType>(),
    defaultRem<intType, longType, floatType>(),
    defaultUnary<unaryType>(),
    if (includeRanges) defaultRanges<rangeType>() else emptyList(),
    conversions
).flatten()

val defaultOperatorList = makeOpList<Int, Int, Long, Float, IntRange>()

fun main() {
    val types = mapOf(
        Byte::class to defaultOperatorList,
        Short::class to defaultOperatorList,
        Int::class to defaultOperatorList,
        Long::class to makeOpList<Long, Long, Long, Float, LongRange>(),
        Float::class to makeOpList<Float, Float, Float, Float, LongRange>(includeRanges = false),
        Double::class to makeOpList<Double, Double, Double, Double, LongRange>(includeRanges = false),
        Char::class to listOf(
            Op(Operation.COMPARE, Char::class, Int::class),
            Op(Operation.ADD, Int::class, Char::class),
            Op(Operation.MINUS, Char::class, Int::class),
            Op(Operation.MINUS, Int::class, Char::class),
            Op(Operation.RANGE, Char::class, CharRange::class),
        ) + conversions
    )

    types.forEach { (type, ops) ->
        val functionImplementations = arrayListOf<String>()
        ops.forEach { op ->
            val jvmName =
                "${type.simpleName?.lowercase()}${op.type.opName.toCapitalized()}${op.inType.let { if (it == Void::class) "" else it.simpleName }}"
            val typeName = type.simpleName!!
            val inTypeName = op.inType.simpleName!!
            val outTypeName = op.outType.simpleName!!
            if (deprecatedOps[type]?.let { op.type in it } != true) {
                functionImplementations.addAll(
                    when {
                        (op.type == Operation.UNARY_PLUS || op.type.isConversion()) && (type == op.outType) -> listOf(
                            createMethodImpl(
                                name = op.type.opName,
                                jvmName = jvmName,
                                isOperator = op.type.isOperator,
                                receiver = makePropertyTypeStr(typeName),
                                params = "",
                                returnType = makePropertyTypeStr(outTypeName),
                                implementation = "this",
                                inlineImpl = true
                            )
                        )
                        op.type.isUnary() -> {
                            val conversionOp = when (if (type == Char::class) Int::class else type) {
                                op.outType -> Operation.IDENTITY
                                else -> getConversionTo(op.outType)
                            }
                            val impl = if (op.type == Operation.UNARY_MINUS) {
                                { a, _ -> conversionOp.impl("-$a", "") }
                            } else {
                                conversionOp.impl
                            }
                            createMethods(
                                name = op.type.opName,
                                jvmName = jvmName,
                                receiverType = typeName,
                                inputType = inTypeName,
                                outputType = outTypeName,
                                isOperator = op.type.isOperator,
                                isUnary = op.type.isUnary,
                                impl = impl
                            )
                        }
                        else -> {
                            val impl = when {
                                op.type.isConversion() && type == Char::class && op.outType == Int::class ->
                                    { a, _ -> "$a.code" }
                                op.type.isConversion() && type == Char::class ->
                                    { a, b -> op.type.impl("$a.code", b) }
                                else -> op.type.impl
                            }
                            createMethods(
                                name = op.type.opName,
                                jvmName = jvmName,
                                receiverType = typeName,
                                inputType = inTypeName,
                                outputType = outTypeName,
                                isOperator = op.type.isOperator,
                                isUnary = op.type.isUnary,
                                impl = impl
                            )
                        }
                    }
                )
            } else {
                System.err.println("Skipping $op for $type due to deprecation.")
            }
        }
        val packageName = "package ${ObservableProperty::class.java.packageName}"
        File("Primitives${type.simpleName}.kt").run {
            if (exists()) delete()
            fun append(text: String) = appendText(text, charset = Charsets.UTF_8)

            append("// Auto-generated file. DO NOT EDIT!\n@file:Suppress(\"unused\")\n\n")
            append(packageName)
            append("\n\n")
            functionImplementations.forEach {
                append(it)
                append("\n\n")
            }
        }
    }
}

fun createMethods(
    name: String,
    jvmName: String,
    receiverType: String,
    inputType: String,
    outputType: String,
    isOperator: Boolean,
    isUnary: Boolean,
    impl: (String, String) -> String,
): List<String> {
    val receiver = makePropertyTypeStr(receiverType)
    val params = if (isUnary) "" else "other : ${makePropertyTypeStr(inputType)}"
    val returnType = makePropertyTypeStr(outputType)
    val implCombiner = if (isUnary) "a -> ${impl("a", "")}" else "a, b -> ${impl("a", "b")}"
    val implStr = buildString {
        if (isUnary) append("derive") else append("combine(other)")
        append(" { ")
        append(implCombiner)
        append(" }")
    }
    val method = createMethodImpl(name, jvmName, isOperator, receiver, params, returnType, implStr)
    if (isUnary) return listOf(method)
    val primitiveParams = "other : $inputType"
    val primitiveImpl = "derive { a -> ${impl("a", "other")} }"

    val primitiveImpl2 = "other.derive { a -> ${impl("this", "a")} }"
    return listOf(
        method,
        createMethodImpl(name, "${jvmName}P", isOperator, receiver, primitiveParams, returnType, primitiveImpl),
        createMethodImpl(
            name,
            "P${jvmName.toCapitalized()}",
            isOperator,
            receiverType,
            params,
            returnType,
            primitiveImpl2
        ),
    )
}

fun createMethodImpl(
    name: String,
    jvmName: String,
    isOperator: Boolean,
    receiver: String,
    params: String,
    returnType: String,
    implementation: String,
    inlineImpl: Boolean = false
): String {
    val implSpace = if (inlineImpl) " " else "\n        "
    val prefix = when {
        isOperator -> "operator "
        params.isEmpty() -> ""
        else -> "infix "
    }
    return """
    @JvmName("$jvmName")
    ${prefix}fun $receiver.$name($params) : $returnType =$implSpace$implementation
    """.trimIndent()
}

fun makePropertyTypeStr(typeName: String) = "${ObservableProperty::class.simpleName}<$typeName>"
