package com.weiqi.kotlin

import com.sun.corba.se.impl.orbutil.graph.Graph
import sun.security.provider.certpath.Vertex
import java.util.Date as dDate


//全局变量/Top-level variables
val PI = 3.14
var x = 2

val fruits = listOf("apple", "banana", "pear")

const val SUBSYSTEM_DEPRECATED = "This subsystem is deprecated"

fun main() {

    println(dDate())

    //sum1
    print("sum1 of 3 and 5 is ")
    println(sum1(3, 5))

    //sum2
    println("sum2 of 5 and 11 is ${sum2(5, 11)}")

    //sum3
    sum3(-1, 10)

    println()
    val a: Int = 1
    val b = 2
    val c: Int
    c = 3
    println("a = $a, b = $b, c = $c")

    var d = 5
    println("x = $d")
    d += 1
    println("x = $d")

    stringTemplate()

    println()
    println("max1 of 1 and 4 is ${maxOf1(1, 4)}")
    println("max2 of 1 and 4 is ${maxOf2(1, 4)}")

    println()
    printProduct("2", "5")
    printProduct("2", "2")

    println()
    printLength("Incompreajlsfjafas")
    printLength(1000)

    println()
    loop1()
    println()
    loop2()
    println()
    loop3()

    println()
    printDescribe(1)
    printDescribe("Hello")
    printDescribe(1L)
    printDescribe('c')
    printDescribe("fjaklsjf")

    println()
    inRange()
    outRange()
    iterateRange()
    iterateRangeWithStep()

    println()
    checkInFruits()
    useLambda()

    println()
    val rectangle = Rectangle(5.0, 2.0)
    val triangle = Triangle(3.0, 4.0, 5.0)
    println("Area of rectangle is ${rectangle.calculateArea()}, its perimeter is ${rectangle.perimeter}")
    println("Area of triangle is ${triangle.calculateArea()}, its perimeter is ${triangle.perimeter}")

    println()
    foo(1) {}
    foo(qux = { println("hello") })
    foo {
        println("Hello")
    }

    reformat("x")
    reformat("x", true, false, false, '_')
    reformat(
        str = "x",
        normalizeCase = true,
        upperCaseFirstLetter = false,
        divideByCameHumps = true,
        wordSeparator = '_'
    )
    reformat("x", wordSeparator = 'a')

    foo(strings = *arrayOf("a", "b", "c"))
    val list = asList(1, 2, 3)

    val aa = arrayOf(1, 2, 3)
    val bb = asList(-1, 0, *aa, 4)
    println("bb is $bb")

    1 sh1 2
    1.sh1(2)
}

//函数有两个Int参数和Int返回类型：
fun sum1(a: Int, b: Int): Int {
    return a + b
}

//具有表达式主体和推断返回类型的函数
fun sum2(a: Int, b: Int) = a + b

//函数返回没有意义的值
//Unit 可以省略
//fun sum3(a:Int,b:Int):Unit{
fun sum3(a: Int, b: Int) {
    println("sum3 of $a and $b is ${a + b}")
}

fun incrementX() {
    x += 1
}

fun stringTemplate() {
    var a = 0
    val s1 = "a is $a"
    println(s1)

    a = 2
    val s2 = "${s1.replace("is", "was")},but now is $a"
    println(s2)
}


fun maxOf1(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}

fun maxOf2(a: Int, b: Int) = if (a > b) a else b

//可为空时，必须明确表明
fun parseInt(str: String): Int? {
    return if (str == "2") 2 else null
}

fun printProduct(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)

    if (x != null && y != null) {
        println(x * y)
    } else {
        println("either '$arg1' or '$arg2' is not a number")
    }
}

//类型判断
fun getStringLength(obj: Any): Int? {
    if (obj is String && obj.length > 0) {
        return obj.length
    } else {
        return null
    }
}

fun printLength(obj: Any) {
    println("'$obj' String length is ${getStringLength(obj) ?: "...error, not a String"}'")
}

fun loop1() {
    for (item in fruits) {
        println(item)
    }
}

fun loop2() {
    for (index in fruits.indices) {
        println("item at $index is ${fruits[index]}")
    }
}

fun loop3() {
    var index = 0
    while (index < fruits.size) {
        println("item at $index is ${fruits[index]}")
        index++
    }
}

fun describe(obj: Any): String =
    when (obj) {
        1 -> "One"
        "Hello" -> "Greening"
        is Long -> "Long"
        !is String -> "Not a String"
        else -> "Unknown"
    }

fun printDescribe(obj: Any) {
    println("'$obj' when describe is ${describe(obj)}")
}

//in
fun inRange() {
    val x = 10
    val y = 9
    if (x in 1..y + 1) {
        println("fits is range")
    }
}

//!in
fun outRange() {
    val list = listOf("a", "b", "c")

    if (-1 !in 0..list.lastIndex) {
        println("-1 is out of range")
    }
    if (list.size !in list.indices) {
        println("list size is out of valid list indices range, too")
    }
}

fun iterateRange() {
    for (x in 0..5) {
        print(x)
    }
    println()
}

//step
//downTo
fun iterateRangeWithStep() {
    for (x in 0..5 step 2) {
        print(x)
    }
    println()
    for (x in 9 downTo 0 step 2) {
        print(x)
    }
}

fun checkInFruits() {
    when {
        "apple" in fruits -> println("apple")
        "watermelon" in fruits -> println("watermelon not in fruits")
    }
}

fun useLambda() {
    val items = listOf("banana", "avocado", "apple", "kiwifruit")
    items.filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.toUpperCase() }
        .forEach { println(it) }
}

fun CreateClss() {

}

abstract class Shape(val sides: List<Double>) {
    val perimeter: Double get() = sides.sum()
    abstract fun calculateArea(): Double
}

interface RectangleProperties {
    val isSquare: Boolean
}

class Rectangle(var height: Double, var length: Double) :
    Shape(listOf(height, length, height, length)), RectangleProperties {
    override val isSquare: Boolean
        get() = length == height

    override fun calculateArea(): Double {
        return height * height
    }
}

class Triangle(
    var sideA: Double,
    var sideB: Double,
    var sideC: Double
) : Shape(listOf(sideA, sideB, sideC)) {
    override fun calculateArea(): Double {
        val s = perimeter / 2
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC))
    }
}

fun foo(bar: Int = 0, baz: Int = 1, qux: () -> Unit) {
    println("'bar' is $bar, 'baz' is $baz, 'qux' is $qux")
}

fun reformat(
    str: String,
    normalizeCase: Boolean = true,
    upperCaseFirstLetter: Boolean = false,
    divideByCameHumps: Boolean = false,
    wordSeparator: Char = ' '
) {
    println("str = $str, normalizeCase = $normalizeCase,upperCaseFirstLetter = $upperCaseFirstLetter,divideByCamelHumps = $divideByCameHumps,wordSepartor = $wordSeparator")

}

fun foo(vararg strings: String) {
    for (s in strings) {
        println(s)
    }
}

@Deprecated(SUBSYSTEM_DEPRECATED)
fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts) {
        result.add(t)
    }
    return result
}

infix fun Int.sh1(x: Int): Int {
    return x
}


class MyStringCollection() {

    infix fun add(s: String) {

    }

    fun build() {
        this add "abc"
        add("abc")
//        add "abc"
    }
}


class Address {
    var name: String = ""
    var street: String = ""
    var city: String = ""
    var state: String = ""
    var zip: String = ""
    var sim: Boolean
        get() = this.zip != ""
        set(value) {

        }


    var counter = 0
        set(value) {
            if (value >= 0) field = value
        }


}
