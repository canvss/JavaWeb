package com.canvs.ssm.filters;

import com.canvs.ssm.utils.Utils;

import javax.security.auth.login.Configuration;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = "/*", initParams = {
        @WebInitParam(name = "white", value = "/")
})
public class SessionAccessControllerFilter implements Filter {
    private List<String> whiteList = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String white = filterConfig.getInitParameter("white");
        String whiteList1 = filterConfig.getServletContext().getInitParameter("whiteList").replaceAll("#", "&");
        if (Utils.isNotEmpty(whiteList1)) {
            white = white + "," + whiteList1;
        }
        String[] whiteArr = white.split(",");
        whiteList = Arrays.asList(whiteArr);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(whiteList);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        if (Utils.isNotEmpty(queryString)) {
            uri = uri + "?" + queryString;
        }
        if (uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".jpg") || uri.endsWith(".png") || whiteList.contains(uri)) {
            filterChain.doFilter(request, response);
        } else {
            HttpSession session = request.getSession();
            Object currUser = session.getAttribute("currUser");
            if (currUser == null) {
                response.sendRedirect("/");
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
