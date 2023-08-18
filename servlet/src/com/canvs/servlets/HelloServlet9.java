package com.canvs.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello9")
public class HelloServlet9 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Conten-Type","text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        pw.println("Servlet response!");
        pw.println("Hello World!");
        pw.println("你好，世界！");
        pw.close();
    }
}
