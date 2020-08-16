---
author: lunar
date: Sun 16 Aug 2020 10:45:51 AM CST
---

### Servlet会话管理

Session通常与cookie一起使用。

在用户第一次登录的时候，服务器在响应时就会返回一个cookie。浏览器会将cookie保存下来。

当用户下一次登录时，浏览器就会将cookie发给服务器。服务器在检验cookie正确且没有过期的情况下，就会直接转servlet处理程序。否则转login.html
