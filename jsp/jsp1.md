---
author: lunar
date: Fri 14 Aug 2020 10:12:14 AM CST
---

### JSP

#### 为什么JSP可以在HTML中运行Java代码？

因为JSP代码被转译成了servlet，是由tomcat完成的。然后再将转译后的Java代码进行编译执行。

#### JSP的7种页面元素

1. 静态内容
即HTML，CSS，JavaScript等内容

2. 指令
以`<%@`开始,`%>`结尾，比如`<%@page import="java.util.*"%>`

3. 表达式
`<%=%>`，用于输出一段HTML。HTML代码要用双引号括住

4. scriptlet
在`<%%>`之间，可以写任何Java代码

5. 声明
在`<%!%>`之间，可以写任何Java代码

6. 动作
`<jsp:include page="filename">`，在JSP页面中包含另一个页面

7. 注释
`<%-- --%>`

来看一个综合示例：
```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%
    List<String> words = new ArrayList<String>();
    words.add("today");
    words.add("is");
    words.add("a");
    words.add("great");
    words.add("day");
%>
  
<table width="200px" align="center" border="1" cellspacing="0">
  
<%for (String word : words) {%>
 
<tr>
    <td><%=word%></td>
</tr>
 
<%}%>
  
</table>
```

#### 两种包含动作及区别

在JSP页面中要包含另一个页面，有两种方式。

指令include: `<%@include file="file.jsp"%>`

动作include: `<jsp:include page="file.jsp"/>`

区别：

如果是指令include，则file.jsp的内容会被插入到本jsp文件中，只生成一个Java文件。

如果是动作include，则不会插入，而是生成两个java文件，然后在服务端访问file.java，将结果嵌入到响应中。

**传参问题**

因为指令<%@include 会导致两个jsp合并成为同一个java文件，所以就不存在传参的问题，在发出hello.jsp 里定义的变量，直接可以在file.jsp中访问。

而动作\<jsp:include /\>其实是对file.jsp进行了一次独立的访问，那么就有传参的需要。

代码示例：
hello.jsp
```jsp
<%@page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
  
你好 JSP
<%=new Date().toLocaleString()%>
 
<jsp:include page="footer.jsp">
    <jsp:param  name="year" value="2017" />
</jsp:include>
```

file.jsp
```jsp
<hr>
    <p style="text-align:center">copyright@<%=request.getParameter("year")%>
</p>
```

#### 服务端跳转和客户端跳转

网站间的跳转是常有的事，跳转分为服务端跳转和客户端跳转。服务端跳转即在服务器内部跳转，服务端和客户端只进行一次通信。而在客户端跳转需要服务器将需要跳转的消息和跳转到的网址发回给客户端，然后客户端再将新的请求发送过来。所以需要进行两次通信。

**客户端跳转**
jsp的客户端跳转与servlet一致，或者说就是写Java代码
`<% response.sendRedirect("hello.jsp");`

**服务端跳转**
`<% request.getRequestDispatcher("hello.jsp").forward(request,response);`

或者使用动作，简化代码
`<jsp:forward page="hello.jsp"/>`

#### JSP cookie

cookie的作用想必不用多说了，在一定的时间内保持免登录状态。

创建cookie代码
```jsp
<%
    Cookie c = new Cookie("name", "Gareen");
    c.setMaxAge(24*60*60);
    c.setPath("/");
    response.addCookie(c);
%>
```
创建一个cookie，名字是name，值是Gareen。保留时间是一天。path表示需要提交cookie的路径，根路径表示所有的页面都提交cookie。

#### JSP 作用域

JSP有4个作用域，分别是
- pageContext 当前页面
- requestContext 一次请求，如果是在服务器内部发生了跳转，还是算一次请求
- sessionContext 当前会话，从用户打开网站起，到关闭网页，均属于一个会话。
- applicationContext 全局，为所有用户所共享

#### 九种隐式对象

隐式对象是指不需要定义就可以直接使用的对象，JSP中一共有九种。

分别是：

1. request

2. response

3. out

4. pageContext

5. session

6. application

7. page
page对象即表示当前对象，JSP会被编译为一个Servlet类，运行的时候是一个Servlet实例，page即代表this

8. config：获取一些在web.xml中初始化的参数

9. exception: 该对象只有在当前页面的`<%@page`指令设置`isErrorPage="true"的时候才可以使用`。指定之后该页面就是专门用于处理异常的页面，然后在其它页面的指令中需要指定`errorPage="catch.jsp"`来指定处理错误的页面。

#### JSP 标准标签库

JSP标准标签库也成为JSTL，JSTL允许开发人员像使用HTML标签一样在JSP中开发Java功能。

1. 导入jar包
需要导入jstl.jar和standar.jar

#### JSP EL表达式语言
