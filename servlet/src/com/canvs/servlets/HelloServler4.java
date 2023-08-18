package com.canvs.servlets;

import javax.servlet.*;
import java.io.IOException;

public class HelloServler4 implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext context = servletConfig.getServletContext();
        //获取Web应用初始化参数
        String name = context.getInitParameter("name");
        System.out.println("全局初始化参数"+name);
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
