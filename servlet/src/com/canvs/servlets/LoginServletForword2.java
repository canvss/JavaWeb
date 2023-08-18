package com.canvs.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login3")
public class LoginServletForword2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String statusCode = (String) req.getAttribute("statusCode");
        System.out.println(statusCode);
        PrintWriter pw = resp.getWriter();
        if (statusCode == null) {
            pw.println("404 not found!");
        }
        if (statusCode.equals("success")) {
            pw.println("Login success!");
        } else if (statusCode.equals("failed")) {
            pw.println("Login failed");
        }
    }
}
