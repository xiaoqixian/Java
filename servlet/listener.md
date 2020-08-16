---
author: lunar
date: Sun 16 Aug 2020 11:42:09 AM CST
---

### Servlet 监听器

Listener的作用是监听web应用的创建和销毁，以及在其上attribute发生的变化。

web应用即ServletContext对象(jsp的隐式对象application)

#### 应用监听类ContextListener

监听类ContextListener需要实现ServletContextListener接口，里面有contextInitialized方法和contextDestroyed方法，分别监听web应用的创建与销毁。

#### 属性监听类ContextAttributeListener

属性监听类ContextAttributeListener需要实现ServletContextAttributeListener接口，包括
- 监听属性增加的方法：`attributeAdded(ServletContextAttributeEvent e)`
- 监听属性减少的方法：`attributeRemoved(ServletContextAttributeEvent e)`
- 监听属性替换的方法：`attributeReplaced(ServletContextAttributeEvent e)`

监听器同样需要注册web.xml
```xml
<listener>
    <listener-class>listener.ContextAttributeListener</listener-class>
</listener>
```

#### session监听类SessionListener

SessionListener需要实现HttpSessionListener接口。`sessionCreated(HttpSessionEvent e)`方法监听session的创建，`sessionDestroyed(HttpSessionEvent e)`监听session的销毁。

在session层面，实现了HttpSessionAttributeListener接口后同样可以监听session属性的增减替换。

#### request监听类RequestListener

RequestListener需要实现ServletRequestListener和ServletRequestAttributeListener接口。具体操作与上同。
