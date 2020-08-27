---
author: lunar
date: Thu 27 Aug 2020 10:12:43 AM CST
---

### Java 包

C++程序员经常将import和#include弄混。实际上两者并没有共同之处。在C++中，必须使用#include将外部特性的声明加载进来，这是因为C++编译器无法查看任何文件的内部，除了正在编辑的文件以及在头文件中明确包含的文件。Java编译器可以查看其它文件的内部，只要告诉它到哪里查看就可以了。

在Java中，如果显式地给出包名，如`java.util.Date`，就可以避免import。而C++中必须include。

#### 静态导入

import语句不仅可以导入类，还增加了导入静态方法和静态域的功能

例如，如果在源文件的顶部，添加指令
`import static java.lang.System.*;`

就可以使用System 类的静态方法和静态域，而不必加类名前缀。
`out.println("Hello World");`


