Java的内省 Introspector 是如何获取到所有到PropertyDescriptor的？

> Java通过遍历所有的方法，如果方法的名字是`is`或者`get`开头的，则把`is`和`get`后面的字符串当作为一个成员变量。