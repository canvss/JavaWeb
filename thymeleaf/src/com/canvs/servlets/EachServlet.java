package com.canvs.servlets;

import com.canvs.bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/each")
public class EachServlet extends ViewBaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> list = new ArrayList<>();
        list.add(new User(1001,"Tom"));
        list.add(new User(1002,"Jerry"));
        list.add(new User(1003,"Canvs"));
        list.add(new User(1004,"Lisa"));
        req.setAttribute("list",list);
        this.processTemplate("each",req,resp);
    }
}
