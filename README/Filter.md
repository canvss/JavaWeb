### 过滤器

#### Filter的概念

Filter一个实现了特殊接口（Filter）的Java类，实现对请求资源（jsp,servlet,html）的过滤的功能，过滤器是一个运行在服务器的程序，优先于请求资源（Servlet，jsp，html）之前的执行，过滤器是Javaweb技术中最为实用的技术之一

#### Filter的作用

Filter的作用是对目标资源（Servlet、jsp）进行过滤，其应用场景有：登录权限检查，解决网站乱码，过滤敏感字符等等。

```java
@WebFilter("*.do")
public class CharacterEncodingFilter implements Filter {
    private String encoding = "UTF-8";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingStr = filterConfig.getInitParameter("encoding");
        if (Utils.isNotEmpty(encodingStr)){
            encoding = encodingStr;
        }
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }
    @Override
    public void destroy() {}
}
```

### Filter的生命周期

#### Servlet的创建时机

Servlet默认在第一次接收请求的时候创建，我们可以通过\<load-on-startup>标签配置Servlet在服务器启动的时候创建

#### Servlet的销毁时机

Servlet会在服务器关闭或者将项目从服务器上移除的时候销毁

#### Filter的生命周期和生命周期方法

| 生命周期阶段 | 执行时机         | 生命周期方法                             |
| ------------ | ---------------- | ---------------------------------------- |
| 创建对象     | Web应用启动时    | init方法，通常在该方法中做初始化工作     |
| 拦截请求     | 接收到匹配的请求 | doFilter方法，通常在该方法中执行拦截过滤 |
| 销毁         | Web应用卸载前    | destroy方法，通常在该方法中执行资源释放  |

### 过滤器匹配规则

#### 精确匹配

指定被拦截资源的完整路径

```xml
<!-- 配置Filter要拦截的目标资源 -->
<filter-mapping>
    <!-- 指定这个mapping对应的Filter名称 -->
    <filter-name>FilterDemo01</filter-name>

    <!-- 通过请求地址模式来设置要拦截的资源 -->
    <url-pattern>/demo01</url-pattern>
</filter-mapping>
```

#### 模糊匹配

相比精确匹配，使用模糊匹配可以让我们创建一个Filter就能够覆盖很多目标资源，不必专门为每一个目标资源都创建Filter，提高开发效率

```xml
<filter-mapping>
    <filter-name>Target02Filter</filter-name>

    <!-- 模糊匹配：前杠后星 -->
    <!--
        /user/demo01
        /user/demo02
        /user/demo03
		/demo04
    -->
    <url-pattern>/user/*</url-pattern>
</filter-mapping>
```

#### 扩展名匹配

```xml
<filter>
    <filter-name>Target04Filter</filter-name>
    <filter-class>com.atguigu.filter.filter.Target04Filter</filter-class>
</filter>
<filter-mapping>
    <filter-name>Target04Filter</filter-name>
    <url-pattern>*.png</url-pattern>
</filter-mapping>
```

### 过滤器链

一个请求可能被多个过滤器所过滤，只有当所有过滤器都放行。请求才能到达目标资源，如果有某一个过滤器没有放行，那么请求则无法到达后续过滤器以及目标资源，多个过滤器组成的链路就是过滤器链。

#### 过滤器链的顺序

过滤器链中每一个Filter执行的顺序是由web.xml中filter-mapping配置的顺序决定的。如果某个Filter是使用ServletName进行匹配规则的配置，那么这个Filter执行的优先级要更低

#### 