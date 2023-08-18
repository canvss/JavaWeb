package com.canvs.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
public class HelloServlet2 implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //获取ServletContext全局上下文对象
        ServletContext context = servletConfig.getServletContext();
        System.out.println("context = "+context);
        String contextPath = context.getContextPath();
        System.out.println("contextPath = "+contextPath);  //contextPath = /servlet
    }
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
