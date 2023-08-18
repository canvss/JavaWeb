package com.canvs.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelloServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init...");
        String url = servletConfig.getInitParameter("url");
        String driverClass = servletConfig.getInitParameter("driverClass");
        String user = servletConfig.getInitParameter("user");
        String password = servletConfig.getInitParameter("password");
        System.out.println("url = "+url);
        System.out.println("driverClass = "+driverClass);
        System.out.println("user = "+user);
        System.out.println("password = "+password);
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("hello service...");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("destroy...");
    }
}
