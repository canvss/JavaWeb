package com.canvs.ssm.listeners;

import com.canvs.ssm.ioc.BeanFactory;
import com.canvs.ssm.ioc.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //获取ServlertContext对象
        ServletContext application = servletContextEvent.getServletContext();
        //获取上下文初始化参数
        String path = application.getInitParameter("contextConfigLocation");
        //创建IOC容器
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);
        //将IOC容器保存到application作用域
        application.setAttribute("beanFactory",beanFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
