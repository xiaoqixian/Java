---
author: lunar
date: Wed 12 Aug 2020 05:55:12 PM CST
---

### **Java注解**

注解是一种为代码使用者提供提示的东西。比如被标注了注解`@Deprecated`的API在被调用时，调用者就会收到此API即将被弃用的消息。

同样，注解也能为写代码的人提供帮助。比如注解`@Override`，当要重载的方法在父类中没有定义时，就无法通过编译。

#### @SuppressWarnings

此注解用于忽略警告信息，常用的警告信息有：

1. deprecation：使用了不赞成使用的类或方法时的警告(使用@Deprecated使得编译器产生的警告)；
2. unchecked：执行了未检查的转换时的警告，例如当使用集合时没有用泛型 (Generics) 来指定集合保存的类型; 关闭编译器警告
3. fallthrough：当 Switch 程序块直接通往下一种情况而没有 Break 时的警告;
4. path：在类路径、源文件路径等中有不存在的路径时的警告;
5. serial：当在可序列化的类上缺少 serialVersionUID 定义时的警告;
6. finally：任何 finally 子句不能正常完成时的警告;
7. rawtypes 泛型类型未指明
8. unused 引用定义了，但是没有被使用
9. all：关于以上所有情况的警告。

使用方法为: `@SuppressWarnings({"deprecation", "unchecked"})`

就是将需要忽略的警告信息加入到集合中。

#### @SafeVarargs

当使用可变长参数，参数的类型又是泛型T的时候会产生警告，使用此注解可忽略警告。

#### @FunctionInterface

约定函数式接口。当某个接口只定义了一个抽象抽象方法（可以包含多个默认方法和静态方法）时，那么这个接口被称之为函数式接口。函数式接口主要是配合lambda表达式一起使用。

### **自定义注解**

#### 创建注解

创建注解，使用`@interface`进行标识。
`public @interface Annotation`

#### 元注解

元注解在定义注解时使用，用于定义一些注解的属性。

常见的有：
- @Target({METHOD,TYPE}) 表示这个注解可以用用在类/接口上，还可以用在方法上。
    可以选择的位置列表如下：
    ElementType.TYPE：能修饰类、接口或枚举类型
    ElementType.FIELD：能修饰成员变量
    ElementType.METHOD：能修饰方法
    ElementType.PARAMETER：能修饰参数
    ElementType.CONSTRUCTOR：能修饰构造器
    ElementType.LOCAL_VARIABLE：能修饰局部变量
    ElementType.ANNOTATION_TYPE：能修饰注解
    ElementType.PACKAGE：能修饰包
- @Retention(RetentionPolicy.RUNTIME) 表示这是一个运行时注解，即运行起来之后，才获取注解中的相关信息，而不像基本注解如@Override 那种不用运行，在编译时eclipse就可以进行相关工作的编译时注解。
    - RentationPolicy.SOURCE: 注解只存在于源码中，编译成class之后就没了。如`@Override`
    - RentatioinPolicy.CLASS: 注解在源码和class文件中均存在，但是运行起来就没了
    - RentationPolicy.RUNTIME: 运行后依然有，程序可以通过反射获取相关信息。自定义注解就是这样。


- @Inherited 表示这个注解可以被子类继承

- @Documented 表示当执行javadoc的时候，本注解会生成相关文档

#### 注解元素

使用注解时常常可以加入多个参数，这些需要事先在注解中定义。

如，要定义一个数据库相关的注解：

```java
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
 
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
 
@Target({METHOD,TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface JDBCConfig {
     String ip();
     int port() default 3306;
     String database();
     String encoding();
     String loginName();
     String password();
}
```

#### 注解方式

```java
import JDBCConfig;
 
@JDBCConfig(ip = "127.0.0.1", database = "test", encoding = "UTF-8", loginName = "root", password = "admin")
public class DBUtil {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
 
}
```

#### 解析注解

接下来可以通过注解对象，获取`DBUtil`这个类的注解对象。
`JDBCConfig config = DBUtil.class.getAnnotation(JDBCConfig.class);`

各个值就可以直接通过方法调用就好了。如`String ip = config.ip();`
