---
author: lunar
date: Wed 12 Aug 2020 09:17:24 AM CST
---

### **Java反射**

#### 类对象

在Java中（事实上在很多面向对象的语言中皆是如此），每定义一个类，就会在存在一个与类绑定的类对象。类对象只在该类第一次实例化时创建一次。

类对象通常用于包含一些类属性，与对象属性相区别。如静态方法和静态变量就属于类属性，类属性为每个实例对象所共享。

#### 获取类对象的三种方法

假设定义了`Test`类

获取类对象有三种方式：

1. `Class.forName("Test");`

2. `Test.class;`

3. `new Test().getClass();` 

这三种方式获得的类对象都是相同的，我们可以通过代码来验证一下。

```java
package reflection;
 
import charactor.Hero;
 
public class TestReflection {
 
    public static void main(String[] args) {
            String className = "charactor.Hero";
            try {
                Class pClass1=Class.forName(className);
                Class pClass2=Hero.class;
                Class pClass3=new Hero().getClass();
                System.out.println(pClass1==pClass2);
                System.out.println(pClass1==pClass3);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
}
```

结果输出都是`true`。

当用`synchronized`修饰静态方法时，同步对象就是类的类对象。

#### 通过反射机制创建对象

对象不但可以通过`new`创建，还可以通过类对象获取构造器对象，再通过构造器创建一个对象。

代码即为(创建Test类的实例)：

```java
Class pClass = Class.forName(className);
Constructor c = pClass.getConstructor();
Test t = (Test)c.newInstance();
```

#### 通过反射机制访问类的属性

只有类的公有成员才能直接访问，如果是私有的，需要使用`setAccessible(true)`才能访问和修改。

访问属性的方法有两个：`getField`和`getDeclaredField`

两者的区别是：`getField`只能获取public属性，包括从父类继承来的属性。`getDeclaredField`可以获取public和private的属性，但是不能获取继承来的属性。而且，这里说的访问私有属性，是只能知道有哪些私有属性，并不能获取到相应的值。除非`setAccessible(true)`。

#### 通过反射机制为类的属性赋值

示例（为Test类的name属性赋值）:

```java
String name = "java";
Class pClass = Class.forName("Test");
Contructor c = pClass.getConstructor();
Test t = (Test)c.newInstance();
Field nameField = pClass.getField("name");
nameField.set(t, name);
```

**注意**：在使用反射为Java Bean设置值的时候，Java Bean中一定要设置setter和getter方法，否则会显示找不到属性。

#### 通过反射机制调用类的方法

属性和方法属于不同的类，属性属于`Field`类，而方法属于`Method`类。

类的方法可以通过`Class`类的`getMethod`方法获取到,`getMethod`的格式为：

`getMethod(methodName, object<?>... params);`

第一个参数自然是方法的名字，第二个参数是可变长参数，对应方法的参数类型和参数顺序。因为在一个类中一个方法可能被多次重载，只有一个方法名可能无法唯一定位一个唯一的方法。如果方法没有参数，则为null。

一个`Method`实例可以通过`invoke`方法进行调用，`invoke`的第一个参数必须是方法隶属的类的实例。

示例：

```java
Class pClass = Class.forName("Test");
Contructor c = pClass.getConstructor();
Test t = (Test)c.newInstance();
Method m = pClass.getMethod("say", null);
m.invoke(t);
```

#### 反射的作用

反射的一个最大作用，就是使得类的使用避免被硬编码。在一些情况下，可能需要某个类的某个方法，有时可能需要调用另一个类的另一个方法。如果每次都修改代码的话就要多次编译。而如果将相应的类和方法写入配置文件，那么程序就只需要固定的读取配置文件就好了。而不需要每次都在代码级别进行修改。

#### 静态代理

代理是一种封装底层代码的方式。代理分为静态代理和动态代理。

在静态代理中，代理类和被代理类实现同一个接口。同一个接口的方法，在被代理类真正的实现，而在代理类中调用被代理类的这个方法。只对别人展现代理类，这样被代理类的方法就被封装了起来。

代码示例：

```java
public class Main2 {
	//这里传入的是接口类型的对象，方便向上转型，实现多态
	public static void consumer(ProxyInterface pi){
		pi.say();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		consumer(new ProxyObject());
	}
}

//代理接口
interface ProxyInterface{
	public void say();
}


//被代理者
class RealObject implements ProxyInterface{
	//实现接口方法
	@Override
	public void say() {
		// TODO Auto-generated method stub
		System.out.println("say");
	}
	
}


//代理者
class ProxyObject implements ProxyInterface{

	@Override
	public void say() {
		// TODO Auto-generated method stub
		//dosomething for example
		System.out.println("hello proxy");
		new RealObject().say();
		System.out.println("this is method end");
	}
	
}
```

#### 动态代理

动态代理依赖于InvocationHandler接口，实现了该接口的类需要重载`invoke`方法。

该方法的格式为：
`public Object invoke(Object proxy, Method method, Object[] args);`

- `proxy`代表被代理的对象
- `method`代表相应被代理的方法
- `args`自然是方法的参数

示例代码：
```java
public interface AppService {
    void createApp(String name);
    void deleteApp(String name);
}
 
//代理类（比如微商代理）
public class AppServiceImpl implements AppService{
 
    @Override
    public void createApp(String name) {
        System.out.print("App["+name+"] has been created.");
    }
 
    @Override
    public void deleteApp(String name) {
        System.out.print("App["+name+"] has been delete.");
    }
}
 
 
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
 
public class LoggerInterceptor implements InvocationHandler {
    private Object target; //委托类(被代理类)的实例，比如厂家
    public LoggerInterceptor(Object target){ 
        this.target = target; 
    } 
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
        System.out.println("Entered "+target.getClass().getName()+"-"+method.getName()+",with arguments{"+args[0]+"}"); 
        Object result = method.invoke(target, args);
       //调用目标对象的方法  （调用厂家的方法（createApp）及参数（Kevin Test））
        System.out.println("Before return:"+result); 
        return result; 
    }
 
}
```

#### 动态代理和静态代理的区别

静态代理：在编译期间代理类和被代理类就已经存在，即实现已经设置了彼此的关系。在上面的例子中，如果没有实现定义被代理类或者没有在代理类中调用被代理类的方法，代理就无法成立。并且，代理类与被代理类还进行了绑定。

动态代理：动态代理是通过接口来实现的，所以代理类不需要与被代理类相关联。当某个类需要被代理时，只需要在代理类中注册相应实例就可以了。所以一个代理类定义后可以代理多个类。显然动态代理要灵活得多。
