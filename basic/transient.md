---
author: lunar
date: Wed 26 Aug 2020 09:23:41 PM CST
---

### Java transient关键字

在Java中，只要一个对象实现了Serilizable接口就可以被序列化。但是有时候我们不希望一个对象内部的一些信息被实例化，这时就可以使用`transient`关键字。

被`transient`修饰的变量不会被序列化，只存在与内存中。

#### transient使用小结

1. 一旦变量被`transient`修饰，变量将不再是对象持久化的一部分，该变量内容在序列化后将无法访问。
2. `transient`关键字只能修饰变量，而不能修饰方法和类。
3. 一个静态变量无论是否被`transient`修饰，都不能被序列化。
