package com.canvs.ssm.base.mvc;

import com.canvs.ssm.ioc.BeanFactory;
import com.canvs.ssm.ioc.ClassPathXmlApplicationContext;
import com.canvs.ssm.utils.Utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {
    private BeanFactory beanFactory;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext servletContext = getServletContext();
        Object beanFactoryObj = servletContext.getAttribute("beanFactory");
        if (beanFactoryObj == null){
            throw new RuntimeException("IOC容器获取失败");
        }
        beanFactory = (BeanFactory) beanFactoryObj;
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        servletPath = servletPath.split("/")[1].split(".do")[0];
        String methodName = request.getParameter("method");
        Object controllerBeanObj = beanFactory.BeanClass(servletPath);
        if (Utils.isEmpty(methodName)) {
            methodName = "customerList";
        }
        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (methodName.equals(method.getName())) {
                    // 统一获取请求参数
                    Parameter[] parameters = method.getParameters();
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        String parameterName = parameters[i].getName();
                        if ("request".equals(parameterName)){
                            parameterValues[i] = request;
                        }else if ("response".equals(parameterName)){
                            parameterValues[i] = response;
                        }else if ("session".equals(parameterName)){
                            parameterValues[i] = request.getSession();
                        }else {
                            String typeName = parameters[i].getType().getName();
                            String parameterValue = request.getParameter(parameterName);
                            Object parameterObj = parameterValue;
                            if (parameterObj != null){
                                if ("java.lang.Integer".equals(typeName)){
                                    parameterObj = Integer.parseInt(parameterValue);
                                }
                            }
                            parameterValues[i] = parameterObj;
                        }
                    }
                    // controller组件中的方法调用
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBeanObj, parameterValues);
                    // 视图处理
                    String methodReturnStr = (String) returnObj;
                    if (methodReturnStr.startsWith("redirect:")) {
                        String redirectStr = methodReturnStr.substring(("redirect:").length());
                        response.sendRedirect(redirectStr);
                    } else {
                        this.processTemplate(methodReturnStr, request, response);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("DispatcherServlet出错");
        }
    }
}
