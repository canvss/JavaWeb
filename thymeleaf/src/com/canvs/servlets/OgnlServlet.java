package com.canvs.servlets;

import com.canvs.bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet("/ognl")
public class OgnlServlet extends ViewBaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //封装User对象
        User user = new User(1001,"Canvs");
        req.setAttribute("user",user);

        //封装List集合
        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(new User(1002,"Tom"));
        req.setAttribute("list",list);

        //封装map集合
        Map<String,User> map = new HashMap<>();
        map.put("user",user);
        req.setAttribute("map",map);
        this.processTemplate("ognl",req,resp);
    }
}
