# Higher-Order Functions

> Kotlin函数是一流的，这意味着它们可以存储在变量和数据结构中，作为参数传递给其他高阶函数并从其返回。您可以以任何可能的其他非功能值的方式操作函数。





## 高阶函数

> 高阶函数是将函数作为参数或返回函数的函数



### 函数类型（Function Type）

> 一个函数要接受另一个函数作为参数，我们要给它指定一个类型

kotlin 使用一系列函数类型，如`(Int) -> String`声明：`val onClick:() -> Unit = ...`

这些类型有一个特殊的符号，对应于函数的签名，即它们的参数和返回值


* 所有函数类型都有一个带括号的参数类型列表和一个返回类型 `(A,B) -> C` 表示一个类型，它表示带有类型A 和B 的两个参数并返回类型C 的值的函数，参数类型列表可能是空，如`() -> A `,单位返回类型不能省略
* 函数类型可以选择有一个额外的接收器类型，它在符号中的点之前制定： `A.(B) -> C ` 表示可以在A 的接收器对象上调用的函数，参数为B 并返回C 类型的值，带有接收者的函数值通常与这些类型一起使用
* 挂起函数属于特殊类型的函数类型，在符号中有一个挂起修饰符，例如： `suspend() -> Unit`、 `suspend A.(B) -> C`

函数类型表示法表示可以选择包含函数参数的名称
`(x: Int, y: Int) -> Point `,这些名称用于纪录参数的含义


指定函数类型可为空：
```kotlin
((Int,Int) -> Int)?.
```

使用括号组合函数类型：
```kotlin
(Int) -> (
	(Int) -> Unit
)```

箭头符号是右关联的：
​```kotlin
(Int) -> (Int) -> Unit
与
(Int) -> ((Int) -> Unit)
相等

与
((Int) -> (Int)) -> Unit
不同
```

使用类型别名为函数类型指定备用名称：

```kotlin
typealias ClickHandler = (Button,ClickEvent) -> Unit
```



### 实例化类型（Instantiating a function type）

获取函数类型的方法

* 使用代码块
	* lambda 表达式
	
	```kotlin
	{a,b -> a+b}
	```

	* 匿名方法
	
	```kotlin
	fun(s: String): Int{
		return s.toIntOrNull() ?: 0
	}
	```


带接收器的函数文字可以用作带接收器的函数类型的值

* 使用对已有声明的可调用引用
	* 方法 top-level、local、member、extension
	
	```kotlin
	::isOdd
	String::toInt
	```

	* 属性 top-level、member、extension
		
	```kotlin
	List<Int>:size
	```

	* 构造函数

	```kotlin
	::Regex
	```


这些包括绑定可调用引用，指向特定实例的成员：`foo::toString`


* 使用实现函数类型作为接口的自定义类的实例

	```
	class IntTransformer: (Int) -> Int {
		override operator fun invoke(x: Int): Int = TODO()
	}

	val intFunction: (Int) -> Int = IntTransformer()
	```

如果有足够的信息，编译器可以推断变量的函数类型

```
val a = {i: Int -> i + 1}
```

具有和不具有接收器的功能类型的非文字值是可互换的，因此接收器可以代表第一个参数，反之亦然，例如：`(A,B)-> C`的值可以传递或分配，`A.(B) -> C` 是预期的，反之亦然



> Note：默认情况下，会推断出没有接收器的函数类型，即使通过引用扩展函数初始化变量也是如此。要更改它，请明确指定变量类型



### 函数类型实例调用

函数类型的值通过 `invoke()` 操作符调用，`f.invoke(x)` 或 `f(x)`

如果该值具有接收者类型，那么应该将接受对象作为第一个参数传递。调用带有接收者的函数类型值的另一个方式是在其前面加上接受者对象，就好比该值是一个扩展函数，`1.foo(2)`