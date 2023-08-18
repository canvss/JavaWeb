package com.canvs.servlets;

import javax.management.relation.Relation;
import javax.servlet.*;
import java.io.IOException;

public class HelloServlet3 implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext context = servletConfig.getServletContext();
        //获取index.html本地路径 index.html虚拟路径是/index.html;/表示当前web应用的根目录即WebContent目录
        String realPath = context.getRealPath("/index.html");
        System.out.println("relation"+realPath);
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
