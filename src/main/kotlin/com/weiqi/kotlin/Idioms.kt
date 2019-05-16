package com.weiqi.kotlin

import java.io.File
import java.lang.ArithmeticException
import java.util.*
import javax.xml.crypto.Data

//Creating DTOs 数据传输对象
data class Customer(val name: String, val email: String)

//函数参数默认值
fun foo(a: Int = 0, b: String = "") {

}

//Filtering a list 列表过滤
val list = listOf("apple", "money", "car")
val positives = list.filter { it.startsWith("a") }
val positives2 = list.filter { s -> s.startsWith("a") }

//String Interpolation 字符串插值
fun stringIntepolation() {
    val name = "Hello Kotlin"
    println("name is $name")
}

//Instance Checks
fun instanceChecks(x: Any) {
    when (x) {
        is Date -> println()
        is Data -> println()
        else -> println()
    }
}

fun TraversingMap(map: Map<Any, Any>) {
    for ((k, v) in map) {
        println("$k -> $v")
    }
}

fun usingRanges() {
    for (i in 1..100) {
    }
    for (i in 1 until 100) {
    }
    for (i in 2..10 step 2) {
    }
    for (i in 10 downTo 1) {
    }
}

val list1 = listOf("a", "b", "c")

var map = mapOf("a" to 1, "b" to 2, "c" to 3)

fun accessMap() {
    println(map["a"])
//    map["a"] = 5
}

fun String.spaceToCamelCase(){

}

object Recsouce{
    val name = "Name"
}

fun nullShort() {
    val files = File("Test").listFiles()
    println(files?.size)
}

fun nullElseShort() {
    val files = File("Test").listFiles()
    println(files?.size ?: "empty")
}

val value = mapOf("a" to "n")
val email = value["a"] ?: throw IllegalStateException("Email is missing!")

val email1 = "afjskldfja@jaskfj"
val mainEmail = email1.firstOrNull() ?: ""

fun test(){
    val result = try {

    } catch (e: ArithmeticException) {
        throw IllegalStateException(e)
    }
}


