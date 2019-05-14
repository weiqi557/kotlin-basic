package com.weiqi.kotlin

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
    override val name:String get() = "$firstName $lastName"
}

data class Employee(
    override val firstName: String,
    override val lastName: String
) : PersonA


//Resolving overriding conflicts
//当我们在超类型列表中声明许多类型时，可能看起来我们继承了同一方法的多个实现。
interface A1{
    fun foo(){
        println("A")
    }
    fun bar()
}
interface B1{
    fun foo(){
        println("B")
    }
    fun bar(){
        println("bar")
    }
}

class C1 : A1{
    override fun bar() {
        print("bar")
    }
}

class D:A1,B1{
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

open class Outer1{
    private val a = 1
    protected open val b = 2
    internal val c = 3
    val d = 4

    protected class Nested{
        public val e: Int = 5
    }
}

class Subclass1 : Outer1() {
    // a is not visible
    // b,c and d are visible
    // Nested and e are visible

    override val b = 5
}

class Unrelated(o: Outer1){
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