package com.weiqi.kotlin.function


fun <T, R> Collection<T>.fold(initial: R, combine: (acc: R, nextElement: T) -> R): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}

fun testFold() {
    val items = listOf(1, 2, 3, 4, 5)

    //lambda 是用大括号括起来的代码块
    items.fold(
        0,
        //当一个lambda 表达式有参数时，先出现，之后时 ->
        { acc: Int, i: Int ->
            print("acc = $acc, i = $i, ")
            val result = acc + i
            println("result = $result")
            //lambda 最后一个表达式被视为返回值
            result
        }
    )

    val joinedToString = items.fold("Elements",
        { acc, i -> "$acc $i" })
    println("joinedToString = $joinedToString")

    //times：阶乘
    //函数引用也可用于高阶函数调用
    val product = items.fold(1, Int::times)
    println("product = $product")
}

fun testFunctionType() {
    val repeatFun: String.(Int) -> String = {
            times -> this.repeat(times)
    }
    val twoParameters: (String, Int) -> String = repeatFun

    fun runTransformation(f: (String,Int) -> String): String{
        return f("Hello", 3)
    }
    val result = runTransformation(twoParameters)

    println("result = $result")
}

fun main() {

    testFold()

    testFunctionType()

    val stringPlus: (String,String) -> String = String::plus
    val intPlus: Int.(Int) -> Int = Int::plus

    println(stringPlus.invoke("<-","->"))
    println(stringPlus("Hello,", "world!"))

    println(intPlus.invoke(1, 1))
    println(intPlus(1, 2))
    println(2.intPlus(3))

}