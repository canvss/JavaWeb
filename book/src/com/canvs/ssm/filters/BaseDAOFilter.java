package com.canvs.ssm.filters;

import com.canvs.ssm.ioc.BaseDAOPackageInjection;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("*.do")
public class BaseDAOFilter implements Filter {
    ServletContext servletContext = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        servletContext = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String baseDAOType = servletContext.getInitParameter("baseDAOType");
        String pojoPath = servletContext.getInitParameter("pojoPath");
        try {
            BaseDAOPackageInjection.injection(pojoPath,baseDAOType);
        } catch (Exception e) {
           throw new RuntimeException("baseDAO注入失败");
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
