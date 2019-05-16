# Kotlin 笔记

## Basic

### 基本类型

* 数字（Numbers）

	* Double
	* Float
	* Long
	* Int
	* Short
	* Byte

> kotlin 中字符不是数字

```kotlin
数字字面值可以使用下划线来表达易读性

val oneMillion = 1_000_000
val creditCardNumber = 1234_5678_11

```

在Java 平台数字是物理存储为JVM 的原声类型，除非我们需要一个可空的引用`?` 或泛型，
后者情况下会把数字装箱,__数字装箱不一定保留同一性,但保证相等性__。

较小的类型不能隐式转换为较大的类型。


* 字符(Characters)
* 布尔(Booleans)
* 数组(Arrays)
* 无符号整型(Unsigned integers)

> kotlin 1.3使用，且处于实验性

TODO

* 字符串(Strings)

字符串是不可变的，字符串的元素可以通过`[]`访问

字符串字面值(String Literals)
* 转义字符串可以有转义字符
* 原始字符串可以包换换行以及任意文本
```
val text = """
	for(c in "foo")
		print(c)
"""
```

### 包

关键字: `import`

kotlin 中不包含`import static`



### 控制流

* if
* When
* For
* While


### 返回和跳转

* return：默认从最直接包围它的函数或者匿名函数返回
* break：终止最直接包围它的循环
* continue：继续下一次最直接包围它的循环


任何表达式都可以用标签(label)标记

```
loop@ for (i in 1..100){}
```


