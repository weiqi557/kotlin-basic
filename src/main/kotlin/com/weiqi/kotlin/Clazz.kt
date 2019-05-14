package com.weiqi.kotlin

public class Invoice {

}

class Person constructor(name: String, email: String)

//如果主构造函数没有任何注释或可见性修饰符,constructor 可省略
class Person1(name: String, email: String)

class InitOrderDemo(name: String) {
    val firstProperty = "First property: $name".also { println("First") }

    init {
        println("First initializer block that prints $name")
    }

    val secondProperty = "Second property: ${name.length}".also { println("Second") }

    init {
        println("Second initializer block that prints ${name.length}")
    }
}

class A {
    init {
        println("init")
    }

    constructor() {
        println("cons")
    }
}

class B {
    constructor() {
        println("cons")
    }

    init {
        println("init")
    }

}


open class Base(p: Int) {
    open fun v() {}
    fun nv() {}
    open val x: Int = 0
}

open class Derived(p: Int) : Base(p) {
    final override fun v() {
        super.v()
    }
    override val x: Int = 1
}

class Clazz(p: Int) : Derived(p){
    override val x: Int
        get() = super.x
}


interface Foo{
    val count:Int
}

class Bar1(override val count: Int) : Foo
class Bars2 :Foo {
    override val count: Int = 0
}

fun main() {
    InitOrderDemo("Hello")

    A()
    B()
}

