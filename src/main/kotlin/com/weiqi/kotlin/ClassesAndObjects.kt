package com.weiqi.kotlin

import java.awt.event.ActionListener
import java.util.function.BinaryOperator
import java.util.function.IntBinaryOperator
import kotlin.math.exp

fun main() {

    Person4("Tomcat")

    Person7("Tomcat", "ABC")

    println()
    Derived1("Hello")

    StaticDemo.LOAN_TITLE

    println()
    C5().caller(D5())
    C6().caller(D5())
    C5().caller(D6())
}

//<editor-fold desc="Classes and Inheritance">

class Invoice1 {}

//Constructors
//主构造函数没有注解或者可见性说明，constructor可以隐藏
class Person2 constructor(firstName: String)

class Person3(firstName: String)

//主构造函数不能包含任意代码，初始化代码可以放在init 做前缀的初始化块内
//在实例初始化期间，初始化程序块的执行顺序与它们在类主体中出现的顺序相同，
//与属性初始化程序交错
class Person4(firstName: String) {

    val firstProperty = "First property: $firstName".also {
        println("First property：$firstName")
    }

    init {
        println("First initializer block that prints $firstName")
    }

    val secondProperty = "Second property：${firstName.length}".also {
        println("Second property: ${firstName.length}")
    }

    init {
        println("Second initializer block that prints $firstName")
    }
}

//主构造函数的参数可以用在初始化块内，
//也可以用在类的属性初始化声明处
class Customer1(name: String) {
    val customerKry = name.toUpperCase()
}

class Customer2(val firstName: String, val lastName: String, var age: Int)


//二级构造函数
//需要添加前缀 constructor
class Person5 {
    constructor(person: Person3) {

    }
}

//如果类具有主构造函数，
//则每个二级构造函数需要直接或者间接通过另一个二级函数代理主构函数
//使用this完成代理
class Persion6(val name: String) {
    constructor(name: String, person: Person3) : this(name) {

    }
}

//所有初始化代码块在二级构造函数之前执行
class Person7(val name: String) {

    init {
        println("First init block")
    }

    constructor(name: String, lastName: String) : this(name) {
        println("constructor block")
    }

    init {
        println("Second init block")
    }
}

//构造函数可见性默认public
//要设置非默认可见性，需要在constructor 前添加可见性
class DontCreateMe private constructor()


//Class Members
//* Constructors and initializer blocks 构造函数和初始化块
//* Functions 函数
//* Properties 属性
//* Nested and Inner Classes 内部类
//* Object Declarations 对象声明


//Inheritance
class Example : Any()// 默认继承Any

//kotlin 默认情况下，所有类都是final，所以需要继承必须使用open

//在构造派生类的新实例期间，基类初始化作为第一步完成（仅在评估基类构造函数的参数之前），
//因此在运行派生类的初始化逻辑之前发生。
open class Base1(p: String) {
    open fun v() {}
    open fun vb() {}
    fun nv() {}

    open val x: Int = p.length.also {
        println("initializing size in base1")
    }
    val y: Int = 0

    init {
        println("initializing base1")
    }

}

open class Derived1(p: String) : Base1(p.capitalize().also {
    println("argument for base1")
}) {
    override fun v() {
        super.v()
    }

    init {
        println("Derived1 init")
    }

    //标记为override 的成员是open的，它可以在子类中背复写
    //如果不想被复写就要加final
    final override fun vb() {
        super.vb()
    }

    override val x: Int = (super.x + p.length).also {
        println("initializing size derived1 ")
    }

    class Derived2(p: String) : Derived1(p) {
        override fun v() {
            super.v()
        }
    }

}


//派生类中的代码可以使用super关键字调用其超类函数和属性访问器实现：
open class Foo1 {
    open fun f() {
        println("Foo.f()")
    }

    open val x: Int
        get() = 1
}

class Bar2 : Foo1() {
    override fun f() {
        super.f()
        println("Bar2.f()")
    }

    override val x: Int
        get() = super.x + 1
}

//在内部类中，访问外部类的超类是使用外部类名限定的super关键字完成的
//super@Outer

class Bar3 : Foo1() {
    override fun f() {
        super.f()
    }

    override val x: Int
        get() = super.x

    inner class Baz {
        fun g() {
            super@Bar3.f()
        }
    }
}


open class A2 {
    open fun f() {
        println("A")
    }

    fun a() {
        println("a")
    }
}

interface B2 {
    fun f() {
        print("B")
    }

    fun b() {
        print("b")
    }
}

class C() : A2(), B2 {
    override fun f() {
        super<A2>.f()
        super<B2>.f()
    }
}


//Abstract Classes 抽象类
//一个抽象方法在它的类中没有实现方法
//抽象类不需要添加open ，默认带着的
open class Base3 {
    open fun f() {}
}

abstract class Derived2 : Base3() {
    abstract override fun f()
}

//Companion Objects 伴随对象
class StaticDemo {
    companion object {
        val LOAN_TYPE = "loan_TYPE"
        val LOAN_TITLE = "loan_TITle"
    }

}

//</editor-fold>


//<editor-fold desc="Properties and Fields">

//val:只读
//var:可变
class Address1 {
    var name: String = ""
    val city: String = "HANGZHOU"

    var isEmpty: Boolean = false
        get() = name == ""
        set(value) {
            field = value || true
        }
}

//Backing Fields 备用字段
class Clazz1 {
    var counter = 0
        set(value) {
            if (value > 0) {
                //field 只能用于属性的访问器
                field = value
            }
        }
}

//Backing Properties 备用属性
private var _table: Map<String, Int>? = null
public val table: Map<String, Int>?
    get() {
        if (_table == null) {
            _table = HashMap()
            return _table ?: throw AssertionError(
                "Set to null by an\n" +
                        "other thread"
            )
        }
        return _table
    }


//Compile-Time Constants 编译时常量
//* 在top-level声明的或者一个object成员
//* 以String 或基本类型进行初始化
//* 没有自定义getter
const val SUBSYSTEM_DEPRECATED: String = "This is deprecated"

@Deprecated(SUBSYSTEM_DEPRECATED)
fun foo6() {
}


//Late-Initialized Properties and Variables 延迟初始化属性
//那些被定义为拥有非空类型的属性，都需要在构造器中初始化
//但有时候并没有那么方便
//例如：单元测试中，属性应该通过依赖注入进行初始化
//这种情况下，不能在构造器中提供一个非空的初始化语句

//Overriding Properties 代理属性


//</editor-fold>


//<editor-fold desc="Interfaces">

//Kotlin中的接口与Java 8非常相似。
//它们可以包含抽象方法的声明以及方法实现。
//使它们与抽象类不同的是接口无法存储状态。
//它们可以具有属性，但这些属性需要是抽象的或提供访问器实现。
interface MyInterface {
    fun bar()
    fun foo() {

    }
}

//implementing interfaces
class Child : MyInterface {
    override fun bar() {

    }
}

//Properties in Interfaces
//您可以在接口中声明属性。
//在接口中声明的属性可以是抽象的，也可以为访问器提供实现。
//在接口中声明的属性不能具有支持字段，因此在接口中声明的访问器不能引用它们。
interface MyInterface1 {
    val prop: Int // abstract
    val propertyWithImplementation: String
        get() = "foo"

    fun foo() {
        print(prop)
    }
}

class Child1 : MyInterface1 {
    override val prop: Int = 29
}


//Interfaces Inheritance
//接口可以从其他接口派生，
//因此它们都为其成员提供实现并声明新的功能和属性。
//很自然地，实现这样的接口的类只需要定义缺少的实现：
interface Named {
    val name: String
}

interface PersonA : Named {
    val firstName: String
    val lastName: String
    override val name: String get() = "$firstName $lastName"
}

data class Employee(
    override val firstName: String,
    override val lastName: String
) : PersonA


//Resolving overriding conflicts
//当我们在超类型列表中声明许多类型时，可能看起来我们继承了同一方法的多个实现。
interface A1 {
    fun foo() {
        println("A")
    }

    fun bar()
}

interface B1 {
    fun foo() {
        println("B")
    }

    fun bar() {
        println("bar")
    }
}

class C1 : A1 {
    override fun bar() {
        print("bar")
    }
}

class D : A1, B1 {
    override fun foo() {
        super<A1>.foo()
        super<B1>.foo()
    }

    override fun bar() {
        super.bar()
    }
}

// </editor-fold>


// <editor-fold desc="Visibility Modifiers">

//类，对象，接口，构造函数，函数，属性及其setter可以具有可见性修饰符。
//（Getters始终具有与属性相同的可见性。）
// Kotlin中有四个可见性修饰符：private，protected，internal和public。
// 如果没有显式修饰符，则使用默认可见性是公共的。


// Package
// * 如果未指定任何可见性修饰符，则默认使用public，这意味着您的声明将随处可见;
// * 如果将声明标记为私有，则只会在包含声明的文件中显示;
// * 如果您将其标记为内部，则在同一模块中的任何位置都可以看到它;
// * 保护不适用于顶级声明

// Class or Interface
// * private表示仅在此类中可见（包括其所有成员）;
// * protected - 与子类中的private + visible相同;
// * internal - 此模块中看到声明类的任何客户端都会看到其内部成员;
// * public - 任何看到声明类的客户都会看到其公共成员。

open class Outer1 {
    private val a = 1
    protected open val b = 2
    internal val c = 3
    val d = 4

    protected class Nested {
        public val e: Int = 5
    }
}

class Subclass1 : Outer1() {
    // a is not visible
    // b,c and d are visible
    // Nested and e are visible

    override val b = 5
}

class Unrelated(o: Outer1) {
    //o.a,o.b are not visible
    //o.c and o.d are visible(same module)
    //Outer1.Nested is not visible, and Nested::e is not visible either
}

//Constructors
//要指定类的主要构造函数的可见性，请使用以下语法（请注意，您需要添加显式构造函数关键字）：
class C2 private constructor(a: Int)
//这里的构造函数是私有的。默认情况下，所有构造函数都是公共的，
//这实际上相当于它们在类可见的任何地方都可见（即内部类的构造函数只在同一模块中可见）

//Local declarations
//局部变量，函数和类不能具有可见性修饰符。


// </editor-fold>

//<editor-fold desc="Extensions">

//类似于C＃和Gosu，Kotlin提供了使用新功能扩展类的能力，而无需继承类或使用任何类型的设计模式，如Decorator。
//这是通过称为扩展的特殊声明来完成的。 Kotlin支持扩展功能和扩展属性。

//扩展实际上并没有修改它所扩展的类
//定义一个扩展，你并没有在类中插入一个新的成员，只是让这个类的实例能够通过`.`调用新的函数
fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

fun testSwap() {
    val l = mutableListOf(1, 2, 3)
    l.swap(0, 2)
}

//扩展程序是静态解析的

open class C3 {

    //如果有同名同参的成员函数和扩展函数
    //调用的时候必然会使用成员函数
    fun foo() {
        print("message")
    }
}

class D3 : C3() {
    fun C3.foo() = "c"
    fun D3.foo() = "d"

    fun printFoo(c: C3) {
        println(c.foo())
    }
}


//Nullable Receiver 可空的接收者
fun Any?.toString(): String {
    if (this == null) {
        return "null"
    }
    return toString()
}

//Extension Properties 扩展属性
//扩展并不会真正给类添加成员属性，
//因此也没有办法让扩展属性拥有一个备份字段
class Foo7 {

}

val Foo7.bar
    get() = 2


val <T> List<T>.lastIndex: Int
    get() = size - 1

//Companion Object Extensions 伴随字段扩展
//如果一个对象定义了伴随对象，你也可以给伴随对象添加扩展函数或扩展属性
class MyClass {
    companion object {}
}

fun MyClass.Companion.foo() {

}

//Scope of Extensions 扩展域
//大多数时候我们在top level 定义扩展，就在包下面直接定义
//为了在除声明的包外使用这个扩展，我们需要在import时导入
fun Bar3.Baz.goo() {

}

//Declaring Extensions as Members 扩展成员变量
//在类中，可以为另一个类声明扩展
//可以在没有限定符的情况下访问其成员
class D4 {
    fun bar() {}
}

class C4 {
    fun baz() {}

    fun D4.foo() {
        bar()
        baz()
        //如果调度接收器的成员和扩展接收器之间存在名称冲突，则扩展接收器优先
        toString()
        this@C4.toString()
    }

    fun caller(d: D4) {
        d.foo()
    }
}


open class D5 {}
class D6 : D5() {}

open class C5 {
    open fun D5.foo() {
        println("D5.foo in C5")
    }

    open fun D6.foo() {
        println("D6.foo in C5")
    }

    fun caller(d: D5) {
        d.foo()
    }
}

class C6 : C5() {
    override fun D5.foo() {
        println("D5.foo in C6")
    }

    override fun D6.foo() {
        println("D6.foo in C6")
    }
}

//Motivation 动机
fun <T> List<T>.swap() {

}

val list2 = listOf(1, 2, 3).swap()

//</editor-fold>


//<editor-fold desc="Data Classes">
data class User(val name: String, val age: Int)

//编译器会自动根据主构造函数中声明的所有属性添加如下方法
//equals()、toString()、copy()、[compontN()functions]

//* 主构造函数应该至少有一个参数
//* 主构造函数的所有参数必须标注为 `val` 或者 `var`
//* 数据类不能时abstract、open、sealed、inner
//* 数据量不能继承其他的类（但可以实现接口）
//* 在JVM 中如果构造函数时无参的，则所有的属性必须有默认的值
data class User1(val name: String = "", val age: Int = 0)

data class User2(val name: String) {
    var age: Int = 0
}

val jack = User1(name = "jack", age = 1)
val olderJack = jack.copy(age = 2)


//<editor-fold>


//<editor-fold desc="Sealed Classes">

sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

//密封类本身是抽象的，它不能直接实例化，并且可以具有抽象成员
//密封类不允许具有非私有构造函数，默认情况下它们的构造函数是私有的
//扩展密封类的子类的类可以放在任何位置，而不必放在统一文件中

//当在when表达式中使用密封类时，使用密封类的主要发挥作用
fun eval(expr: Expr): Double = when (expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
}
//</editor-fold>

//<editor-fold desc="Generics">

class Box<T>(t: T){
    var value = t
}

val box: Box<Int> = Box(1)


//Variance 变型
//out：T的生产者
abstract class  Source<out T>{
    abstract fun nextT(): T
}

fun demo(strs: Source<String>) {
    val objects: Source<Any> = strs
}

//in：T的消费者
abstract class Comparable<in T>{
    abstract fun compareTo(other: T): Int
}

fun demo1(x: Comparable<Number>) {
    x.compareTo(1.0)
    val y: Comparable<Double> = x
}

//Type projections 类型投影

//class Array<T>(val size: Int){
//    fun get(index:Int){
//
//    }
//    fun set(index: Int, value: T) {
//
//    }
//}

fun copy(from: Array<Any>, to: Array<Any>) {
    assert(from.size == to.size)
    for (i in from.indices) {
        to[i] = from[i]
    }
}

//<editor-fold>

//<editor-fold desc="Nested and Inner Classes">

//嵌套类
class Outer{
    private val bar:Int = 1
    class Nested{
        fun foo() = 3
    }
}

val demo = Outer.Nested().foo()


//内部类
//类可以标记为inner 这样就可以访问外部类的成员。成员类拥有外部类的一个对象引用

class Outer2{
    private val bar: Int = 1
    inner class Inner{
        fun foo() = bar
    }
}

val demo1 = Outer2().Inner().foo()


//匿名内部类
//匿名内部类的实例通过对象表达式创建的
val listener = ActionListener{
    println("clicked")
}

//<editor-fold>


//<editor-fold desc="Enum Classes">

//枚举类最基本的用法就是实现类型安全的枚举
enum class Direction{
    NORTH,SOUTH,WEST
}

//每个枚举类都是枚举类的一个实例，它们是可以初始化的
enum class Color(val rgb: Int){
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

//匿名类
//枚举实例也可以声明自己的匿名类
enum class ProtocolState{
    WAITING{
        override fun signal() = Taking
    },
    Taking{
        override fun signal() = WAITING
    };
    abstract fun signal():ProtocolState
}

//在枚举类中实现接口
enum class IntArithmetics: BinaryOperator<Int>,IntBinaryOperator{

    PLUS{
        override fun apply(t:Int,u:Int): Int = t + u
    },
    TIMES{
        override fun apply(t:Int,u:Int): Int = t * u
    };

    override fun applyAsInt(left: Int, right: Int) = apply(left,right)
}

//使用枚举常量
enum class RGB{
    RED,GREEN,BLUE
}

inline fun <reified T : Enum<T>> printAllValues(){
    print(enumValues<T>().joinToString { it.name })
}

fun printRGB(){
    printAllValues<RGB>()
}

//</editor-fold>

//<editor-fold desc="Object Expressions and Declarations">


//</editor-fold>