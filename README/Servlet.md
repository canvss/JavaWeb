### Servlet

Servlet（Server Applet）是Java Servlet的简称，称为小服务程序或服务连接器，用Java编写的服务器端程序，具有独立于平台和协议的特性，主要功能在于交互式地浏览和生成数据，生成动态Web内容。 是java实现前后端交互的主要机制.

##### AddServlet

```Java
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        Customer cust = new Customer(Integer.parseInt(id), name, email, new Date(16737635569L));
        CustomerDAOImpl dao = new CustomerDAOImpl();
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            dao.insert(conn,cust);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(conn,null);
        }
    }
}
```

##### web.xml配置文件

```xml
 	<servlet>
        <servlet-name>AddServlet</servlet-name>
        <servlet-class>com.canvs.servlets.AddServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>AddServlet</servlet-name>
        <url-pattern>/add</url-pattern>
    </servlet-mapping>
```

##### add.html文件

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<form action="add" method="post">
    编号:  <input type="text" name="id"/><br/>
    姓名: <input type="text" name="name"/><br/>
    邮箱: <input type="text" name="email"/><br/>
    生日: <input type="text" name="birth"/><br/>
    <input type="submit" value="提交">
</form>
</body>
</html>
```

#### Servlet声明周期

- 执行构造方法：用户第一次访问时执行，创建Servlet对象
- init方法：当用户访问时，执行完构造方法之后自动调用，一般用来指默认值
- service：用户的业务操作，调用业务就会执行
- destroy方法：当tomcat服务器关闭前执行销毁动作释放资源

