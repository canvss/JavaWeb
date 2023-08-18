package com.canvs.servlets;

import org.apache.catalina.connector.Request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hello5")
public class HelloServlet5 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取url地址参数
        String contextPath = req.getContextPath();
        System.out.println("contextPath"+contextPath);
        System.out.println("端口号: "+req.getServerPort());
        System.out.println("主机名： "+req.getServerName());
        System.out.println("协议： "+req.getScheme());

        //获取请求头信息
        String header = req.getHeader("User-Agent");
        System.out.println("User-Agent = "+header);
        System.out.println("上个页面的地址 = "+req.getHeader("Referer"));
    }
}
