---
author: lunar
date: Fri 14 Aug 2020 12:40:23 PM CST
---

MVC是一种分层的设计模式

servlet和JSP各自均有弊端，在servlet中写Java代码非常方便，或者说本身就是Java代码。但是要准备响应的HTML代码可读性非常差。同样在JSP中写Java代码又非常不方便。

所以需要结合servlet和JSP。

可以在servlet中从request中获取请求，从数据库获取数据，然后在request中setAttribute，然后在服务器内跳转到JSP页面中。由于属于同一请求，所以在JSP中可以通过request取出来。

servlet层面，示例代码：
```java
protected void service(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException{
    int id = Integer.parseInt(request.getParamter("id"));
    Data data = new DataDao().get(id);
    request.setAttribute("data", data);
    request.getRequestDispatcher("data.jsp").forward(request, response);
}
```

JSP层面，如果运用EL表达式，则可以直接通过`${data}`将值取出来。

### MVC设计模式

上例中结合JSP和servlet的模式就是MVC的思想。

MVC即Model(模型), View(视图), Control(控制器)

模型即数据库中的数据在Java层面的显示，通常包含数据表映射的JavaBean和操作数据的DAO(Data Access Object)。DAO通常通过jdbc对数据库进行增删改查。

视图即JSP代码

控制器用来将不同的数据显示在不同的视图上，对应servlet程序。
