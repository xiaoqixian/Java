---
author: lunar
date: Thu 27 Aug 2020 11:54:50 AM CST
---

### Java 杂项

#### final, finally, finalize的区别

final: final修饰的类无法被继承，final修饰的方法无法被重写，final修饰的变量无法被修改。

finally: 在异常捕捉代码块中使用，无论是否发生异常，finally代码块中的代码都要执行。

finallize: Java允许使用finalize()方法在垃圾收集器将对象从内存清除出去之前做必要的清理工作。类似于C++中的析构方法。一般此方法由JVM调用，程序员不要调用。

#### ==和equals的区别和联系

"=="的使用情况如下：
1. 基本类型，比较值
2. 引用类型，比较地址
3. 不能比较没有父子关系的两个对象

equals()方法使用如下:
1. 系统类一般已经覆盖了equals()，比较的是内容
2. 用户自定义类如果没有覆盖equals()，则调用父类的equals()
3. 用户自定义类需要覆盖父类的equals()
