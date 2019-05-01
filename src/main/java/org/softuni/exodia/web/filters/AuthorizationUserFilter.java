package org.softuni.exodia.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/view/login.jsf", "/view/register.jsf", "/view/index.jsf"})
public class AuthorizationUserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String userId = (String) ((HttpServletRequest) servletRequest).getSession().getAttribute("user-id");

        if (userId != null) {
            ((HttpServletResponse) servletResponse).sendRedirect("/view/home.jsf");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
