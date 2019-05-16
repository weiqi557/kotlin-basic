package com.weiqi.kotlin.basic

fun main() {

    //if 语句
    TestIf().foo()

    //When 语句
    TestWhen().foo()

    //For 语句
    TestFor().foo()

    //While 语句
    TestWhile().foo()

}


class TestIf{

    init {
        println("If Control")
    }

    fun foo() {
        println("a and b max is " + foo1(1, 2))
        println("a and b max is " + foo2(1, 2))
        println("a and b max is " + foo3(1, 2))
    }

    fun foo1(a:Int,b:Int):Int{
        var max = a
        if (a < b) max = b
        return max
    }

    fun foo2(a: Int, b: Int):Int {
        var max:Int
        if (a > b) {
            max = a
        }else{
            max = b
        }
        return max
    }

    fun foo3(a:Int,b:Int):Int = if (a > b) a else b
}

class TestWhen{

    init {
        println("When Control")
    }

    fun foo(){
        foo1(1)
        foo2(3)
        foo3("xxxfasf")
    }

    fun foo1(x: Int){
        when (x) {
            1 -> println("x == 1")
            2 -> println("x == 2")
            else -> {
                println("x is neither 1 or 2")
            }
        }
    }

    fun foo2(x:Int) {
        when (x) {
            0, 1 -> println("x == 0 or x ==1")
            else -> println("otherwise")
        }
    }

    fun foo3(any: Any) {
        when (any) {
            in 1..10 -> println("x is in the range")
            is String -> println("${any.startsWith("xxx")}")
            else -> println("any")
        }
    }
}


class TestFor{

    init {
        println("For Control")
    }

    fun foo(){
        foo1()
        foo2()
        foo3()
    }

    fun foo1() {
        val list = listOf(1,2,3)
        for (item:Int in list) {
            print("$item ")
        }
    }

    fun foo2(){
        for (i in 1..3) {
            print("$i ")
        }
    }

    fun foo3(){
        for(i in 10 downTo 0 step 2){
            print("$i ")
        }
    }

}

class TestWhile{

    init {
        println("While Control")
    }

    fun foo(){
        var x = 10
        while (x > 0) {
            x--
            print("$x ")
        }
    }
}