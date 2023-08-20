package com.canvs.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/domain")
public class DomainServlet extends ViewBaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setAttribute("requestMsg","request域中的数据");
        req.getSession().setAttribute("sessionMsg","session域中的数据");
        req.getServletContext().setAttribute("contextMsg","context域中的数据");
        this.processTemplate("domain",req,resp);
    }
}
