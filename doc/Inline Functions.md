# 内联函数

使用高阶函数会带来一些运行时的损耗，每个函数都是一个对象，并且会获取一个闭包，即那些在函数体内会访问到的变量，内存分配和虚拟调用会引入运行时间的开销。

但许多情况下通过内联化lambda 表达式可以消除这类的开销。