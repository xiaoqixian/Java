---
author: lunar
date: Sun 16 Aug 2020 11:01:27 AM CST
---

### Servlet 过滤器

如果在servlet与用户请求之间加上过滤器，那么所有的请求都需要经过过滤器后被servlet处理。

这样的设计便于集中对请求进行一些前置处理，比如打印日志神马的。

所有的过滤器类都需要实现servlet-api中的Filter接口

```java
package filter;
 
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebFilter(filterName="FirstFilter", urlPatterns="/*")
public class FirstFilter implements Filter {
 
    @Override
    public void destroy() {
 
    }
 
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
 
        String ip = request.getRemoteAddr();
        String url = request.getRequestURL().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date();
        String date = sdf.format(d);
 
        System.out.printf("%s %s 访问了 %s%n", date, ip, url);
        chain.doFilter(request, response);
    }
 
    @Override
    public void init(FilterConfig arg0) throws ServletException {
 
    }
 
}
```

注解中的urlPatterns用于匹配需要使用过滤器的访问路径，"/\*"表示过滤所有路径。

如果想要使用web.xml进行配置，配置示例为
```xml
<filter>
    <filter-name>FirstFilter</filter-name>
    <filter-class>filter.FirstFilter</filter-class>
</filter>
 
<filter-mapping>
    <filter-name>FirstFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

#### filter进行中文处理

servlet要处理需要对请求进行utf8编码处理。但是要每个servlet都这样处理一边未免过于累赘。所以可以放在过滤器进行处理。

同样还有session管理和cookie验证。在赘述了。
