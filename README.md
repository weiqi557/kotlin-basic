# Kotlin Study

## Basic

### Package 
> 不需要匹配目录和包：源文件可以任意放置在文件系统中。

```kotlin
package com.weiqi.kotlin

import foo.Bar
import bar.Bar as bBar

```

### Function
> 使用`fun` 作为关键字声明方法

使用：
```kotlin
val result = double(2)

//使用成员变量点表达式
Sample().foo()

//参数可用默认值
fun read(b : Array<Byte>, off: Int = 0,len : Int = b.size){}

//如果参数最后一位是lambda表达式，可以作为命名参数传递
fun foo(bar : Int = 0, baz : Int = 1,qux : () -> Unit){}

foo(1){println("hello")} // uses the default value baz = 1
foo(qux = {println{"hello"}}) // uses both default values bar = 0 and baz = 1
foo(println("hello")) //uses both default values bar = 0 and baz = 1

//vararg 可传递可变数量的参数
fun foo(vararg strings: String){}
foo(strings = *arrayOf("a","b","c"))

```

* Local Function: 本地函数，即另一个函数的函数
    闭包：本地函数可以访问外部函数的局部变量
* Member Function：成员函数，即类或对象类定义的函数
* Generic Function：泛型函数，可以具有泛型参数，这些参数在函数名称之前使用尖括号指定
* Inline Function：内联函数
* Extension Function：扩展函数
* Higher-Order Functions and Lambdas：高级函数和Lambda
* Tail recursive Functions：尾递归函数


### Variables

* val：定义局部变量，只能被赋值一次
* var：可重新赋值变量


### Comments
> 注释，与Java 和 JavaScript 一样的注释方法
```
//This is an end-of line comment

/*
* This is a block comment on multiple lines.
*/
```


### Using string templates
> 使用字符串模块

### Using Conditional expressions
> 使用条件表达式

### Using nullable values and Checking for null
> 使用可空值并检查null

引用如果可为空，必须明确表明可空


### Using type checks and automatic casts
> 使用类检测以及自动转换

### Use a for Loop

### Use a While Loop

### Use When expression

### Use Ranges

### Use Collections

### Creating basic classes and their instance