package com.weiqi.kotlin.basic

fun main() {

    //标签
    TestLabel().foo()
}

class TestLabel{

    fun foo(){
        foo1()
        foo2()
    }

    fun foo1(){
        println()
        loop@ for (i in 1..10) {
            for (j in 1..10) {
                if (j > 5) {
                    break@loop
                }
                print("i = $i,j = $j ")
            }
            println()
        }
    }

    fun foo2(){
        println()
        val list = listOf(1, 2, 3, 4, 5)
        list.forEach lab@{
            if (it == 3) {
                return@lab
            }
            print("$it ")
        }
        println(" done with explicit label")
    }
}

