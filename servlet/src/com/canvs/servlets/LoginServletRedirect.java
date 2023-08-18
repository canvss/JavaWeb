package com.canvs.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServletRedirect extends HttpServlet {
    //登录重定向
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username.equals("canvs") && password.equals("123456")){
           resp.sendRedirect("/servlet/success.html");
        }else {
            resp.sendRedirect("/servlet/failed.html");
        }
    }
}
