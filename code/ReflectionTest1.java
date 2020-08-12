/**********************************************
  > File Name		: reflectionTest1.java
  > Author			: lunar
  > Email			: lunar_ubuntu@qq.com
  > Created Time	: Wed 12 Aug 2020 09:27:05 AM CST
 **********************************************/

class Test1 {
    public void get() {

    }
}

public class ReflectionTest1 {
    public static void main(String[] args) throws ClassNotFoundException{
        String className = "Test1";
        Class pClass1 = Class.forName(className);
        Class pClass2 = Test1.class;
        Class pClass3 = new Test1().getClass();
        System.out.println(pClass1==pClass2);
        System.out.println(pClass1==pClass3);
    }
}
