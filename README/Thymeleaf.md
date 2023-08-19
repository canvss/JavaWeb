### Thymeleaf

Thymeleaf是一款用于渲染XML/XHTML/HTML5内容的模版引擎。类似于JSP、Velocity等

#### Thymeleaf优势

- SpringBoot官方推荐使用的视图模版技术，和SpringBoot完美结合
- 不经过服务器运算仍然可以直接查看原始值

### 物理视图和逻辑视图

#### 物理视图

在Servlet中，将请求转发到一个HTML页面文件时，使用的完整的转发路径就是物理视图

```xml
/pages/user/login.html
/pages/user/login_success.html
/pages/user/regist.html
/pages/user/regist_success.html
……
```

- 视图前缀：/pages/user/

- 视图后缀：.html

#### 逻辑视图

物理视图=视图前缀+逻辑视图+视图后缀

| 视图前缀     | 逻辑视图      | 视图后缀 | 物理视图                       |
| ------------ | ------------- | -------- | ------------------------------ |
| /pages/user/ | login         | .html    | /pages/user/login.html         |
| /pages/user/ | login_success | .html    | /pages/user/login_success.html |

### Thymeleaf入门demo

#### 导入jar包

3.0.12.RELEASE版本

#### 配置上下文参数

**web.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!--  视图前缀  -->
    <context-param>
        <param-name>view-prefix</param-name>
        <param-value>/WEB-INF/view/</param-value>
    </context-param>
    <!--  视图后缀  -->
    <context-param>
        <param-name>view-suffix</param-name>
        <param-value>.html</param-value>
    </context-param>
</web-app>
```

> param-value中设置的前缀、后缀的值不是必须叫这个名字，可以根据实际情况和需求进行修改
>
> WEB-INF目录不允许浏览器直接访问，所以视图模版文件放在这个目录下，是一种保护。以免外界可以随意访问视图模版文件
>
> 访问WEB-INF目录下的页面，都必须通过Servlet转发过来

#### 创建Servlet基类

```java
public class ViewBaseServlet extends HttpServlet {
    private TemplateEngine templateEngine;
    @Override
    public void init() throws ServletException {
        //1.获取ServletContext对象
        ServletContext servletContext = this.getServletContext();
        //2.创建Thymeleaf解析器对象
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        //3.给解析器对象设置参数
        //3.1HTML是默认模式，明确设置是为了代码更容易理解
        templateResolver.setTemplateMode(TemplateMode.HTML);
        //3.2设置前缀
        String viewPrefix = servletContext.getInitParameter("view-prefix");
        templateResolver.setPrefix(viewPrefix);
        //3.3设置后缀
        String viewSuffix = servletContext.getInitParameter("view-suffix");
        templateResolver.setSuffix(viewSuffix);
        //3.4设置缓存过期时间（毫秒）
        templateResolver.setCacheTTLMs(60000L);
        //3.5设置是否缓存
        templateResolver.setCacheable(true);
        //3.6设置服务器端编码方式
        templateResolver.setCharacterEncoding("utf-8");
        //4 创建模版引擎对象
        templateEngine = new TemplateEngine();
        //5 给模版引擎对象设置模版解析器
        templateEngine.setTemplateResolver(templateResolver);
    }
    protected void processTemplate(String templateName, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1 设置响应体内容类型和字符集
        resp.setContentType("text/html;charset=UTF-8");
        //2 创建WebContext对象
        WebContext webContext = new WebContext(req, resp, getServletContext());
        //3.处理模版数据
        templateEngine.process(templateName,webContext,resp.getWriter());
    }
}
```

#### 创建index.html页面

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>index</title>
    <base href="http://127.0.0.1:8080/thymeleaf/"/>
</head>
<body>
    <a href="helloServlet"> 跳转到helloServlet </a>
</body>
</html>
```

#### 创建HelloServlet

```java
@WebServlet("/helloServlet")
public class HelloServlet extends ViewBaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("已经访问了Servlet了。。。");
        req.setAttribute("msg","我是服务器数据");
        this.processTemplate("hello",req,resp);
    }
}

```

#### 创建Thymeleaf

**WEB-INF/view/hello.html**

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>hello</title>
</head>
<body>
  <h1 th:text="${msg}"></h1>
</body>
</html>
```

### Thymeleaf基本语法

#### th名称空间

![](imgs/826be8cba4ad4043b726c203683002d5.png)

#### th:text作用

```html
<p th:text="标签体新值">标签体原始值</p>
```

- 不经过服务器解析，直接用浏览器打开HTML文件，看到的是[标签体原始值]
- 经过服务器解析，Thymeleaf引擎根据th:text属性指定的[标签体新值]去替换[标签体原始值]

#### 修改指定属性值

语法：任何HTML标签原有的属性，前面加上th: 就都可以通过Thymeleaf来设定新值

```html
<input type="text" name="username" th:value="文本框新值" value="文本框旧的值" />
```

#### 解析URL地址

```html
<a th:href="@{/index.html}">访问index.html</a>
```

- 经过解析后得到：/thymeleaf/index.html

- 所以@{}的作用是在字符串前面附加上下文

> 这个语法好处就是：实际开发过程中，项目在不同的环境部署时，Web应用的名字有可能发生变化。所以上下文路径不能写死。而通过@{}动态获取上下文路径后。

#### 关于index.html访问说明

- 为什么在index.html使用th标签失效
- 如果我们直接访问index.html本身，那么index.html是不需要通过Servlet，当然也不经过模版引擎，所以index.html上的Thymeleaf的任何表达式都不会被解析。
- 解决办法：通过Servlet访问index.html

> 通过上面的案列可以看到，所有和业务功能相关的请求都能够确保它们通过Servlet来处理，这样就方便我们统一对这些请求进行特定规则的限定



