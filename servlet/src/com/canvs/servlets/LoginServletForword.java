package com.canvs.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login2")
public class LoginServletForword extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username.equals("canvs")&&password.equals("123456")){
            req.setAttribute("statusCode","success");
            req.getRequestDispatcher("/login3").forward(req,resp);
        }else {
            req.setAttribute("statusCode","failed");
            req.getRequestDispatcher("/login3").forward(req,resp);
        }
    }
}
