---
author: lunar
date: Wed 26 Aug 2020 10:17:26 PM CST
---

### JVM, JRE, JDK

JVM: Java Virtual Machine的缩写。JVM只对.class文件进行操作。

JRE: Java Runtime Environment，Java运行时环境。光有JVM还不能对.class文件进行执行，因为解释class的时候JVM需要调用解释所需要的类库lib。在JDK的安装后目录里面可以找到jre目录，里面有bin目录和lib目录。bin目录就是JVM，lib目录就是所需要的类库。JVM和lib加起来就是JRE.

JDK: Java Development Kit，Java开发工具包。JRE只能保证正常运行编译好的.class文件，但是如果想要从头开始写Java代码后编译运行，还需要借助JDK。

JDK的目录包括:
- bin: 最主要的是编译器(javac.exe)
- include: java和JVM交互用的头文件
- lib: 类库
- jre

eclipse、IDEA等IDE有自己的编译器而不是用JDK bin里面的，所以在安装时发现它们只要求选jre路径就OK了。
