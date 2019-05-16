package com.weiqi.kotlin

import com.sun.corba.se.impl.orbutil.graph.Graph
import sun.security.provider.certpath.Vertex


//<editor-fold desc="Functions">

//kotlin 中用关键字`fun` 声明函数
fun double(x:Int):Int{
    return 2 * x
}

//函数用法
val result = double(2)

class Sample(){
    fun foo(){}
}

val result1 = Sample().foo()

//参数
fun powerOf(number: Int, exponent: Int) {

}

//默认参数
fun read(b: Array<Byte>, off: Int = 0, len: Int = b.size) {}

//覆盖方法必须省略默认参数
open class A{
    open fun foo(i: Int = 1) {}
}

class B : A(){
    override fun foo(i: Int) {}
}

//如果默认参数在参数之前没有默认值，
//则只能通过使用命名参数调用函数来使用默认值
fun foo(bar: Int = 0, baz: Int) {

}

val result2 = foo(baz = 1)


//如果默认参数之后的最后一个参数是lambda，可以作为命名参数传递，也可以在括号之外传递
fun foo2(bar: Int = 0, baz: Int = 1, qux: () -> Unit) {

}

val result3 = foo2(1) { println("hello") }
val result4 = foo2(qux = { println("hello")})
val result5 = foo2{ println("hello")}

//命名参数,命名参数语法不能被调用与Java函数
fun reformat1(
    str:String,
    normalizeCase:Boolean = true,
    upperCaseFirstLetter:Boolean = true,
    divideByCamelHumps:Boolean = false,
    wordSeparator:Char = ' '
){}

val result6 = reformat1("hello")
val result7 = reformat1("hello", true, true, true, '_')
val result8 = reformat1(
    "hello",
    normalizeCase = false,
    upperCaseFirstLetter = true,
    divideByCamelHumps = false,
    wordSeparator = '_'
)


//不带返回值的参数
//Unit 可以省略
fun printHello(name:String?):Unit{
    if (name != null) {
        println("Hello $name")
    }else{
        println("Hi there!")
    }
}

//单表达式函数
//当函数只返回单个表达式时，大括号可以省略并在 = 后面定义函数体
fun double2(x: Int) = x * 2

//明确返回类型
//具有块体的函数必须明确指明返回类型，Unit可以省略


//可变长度参数
//只有一个参数可以被vararg 修饰进行标记，通常时最后一个参数
fun <T> asList1(vararg ts:T):List<T>{
    val result = ArrayList<T>()
    for (t in ts) {
        result.add(t)
    }
    return result
}

val list3 = asList1(1,2,3)
val a = arrayOf(1, 2, 3)
val list4 = asList1(-1,2,*a,4)


//infix notation 中缀表达式


//函数范围

//局部函数

//一个函数包含另一个函数
//局部函数可以访问外部函数的局部变量
fun foo(int:Int) {

    val abc = "QWE"

    fun foo1(int: Int, string: String) {
        println(abc)
    }
    foo1(int,"ABC")
}




//</editor-fold>