package com.weiqi.kotlin.basic

import java.util.Date as dDate

public fun main() {

    //数字下划线易读性
    TestNumber().foo()

    //表达方式
    TestRepresentation().foo()
    TestRepresentation().foo1()

    //字符转换
    TestFloat().foo()

    //数组遍历
    TestArrays().foo()

    //字符串
    TestStrings().foo()
    TestStrings().foo1()
}

class TestNumber {
    private val oneMillio = 1_000_000
    private val creditCardNumber = 1234_5678_9012_3456L
    private val socialSecurityNumber = 999_99_9999L
    private val hexBytes = 0xFF_EC_DE_5E
    private val bytes = 0b11010010_01101001_10010100_10010010

    fun foo() {
        println("数字下划线易读性")
        println(
            "oneMillio = $oneMillio \n" +
                    "creditCardNumber = $creditCardNumber \n" +
                    "socialSecurityNumber = $socialSecurityNumber \n" +
                    "hexBytes = $hexBytes \n" +
                    "bytes = $bytes"
        )
    }
}

class TestRepresentation{


    fun foo() {

        println("表达方式")
        val a: Int = 10000
        println("a === a is ${a === a}")

        val boxedA: Int? = a
        val anotherBoxedA: Int? = a
        println("boxedA === anotherBoxedA is ${boxedA === anotherBoxedA}")
    }

    fun foo1(){
        println("表达方式")
        val a: Int = 10000
        println("a === a is ${a == a}")

        val boxedA: Int? = a
        val anotherBoxedA: Int? = a
        println("boxedA === anotherBoxedA is ${boxedA == anotherBoxedA}")
    }
}


class TestFloat{

    fun foo() {
        println("字符转换")
        println("fool('8') = ${foo1('8')}")
    }

    private fun foo1(c: Char):Int {
        if (c !in '0'..'9') {
            throw IllegalArgumentException("Out of Range")
        }
        return c.toInt() - '0'.toInt()
    }
}

class TestArrays{
    fun foo() {

        println("数组创建")
        val asc = Array(5)
        { i -> (i * i).toString()}
        asc.forEach { print("$it ") }
    }
}

class TestStrings{
    init {
        println("字符串")
    }

    fun foo(){
        val str = "abcd"
        for (c in str) {
            print("$c ")
        }
    }

    fun foo1(){
        //trimMargin 去除前导空格
        val text = """
            |Tell me and I forget.
            |Teach me and I remember.
            |Involve me and I learn.
            |(Benjamin Franklin)
        """.trimMargin()
        println(text)
    }
}



