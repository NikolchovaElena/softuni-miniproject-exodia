package org.softuni.exodia.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/view/home.jsf", "/view/print.jsf", "/view/schedule.jsf", "/view/details.jsf"})
public class AuthorizationGuestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String userId = (String) ((HttpServletRequest) servletRequest).getSession().getAttribute("user-id");

        if (userId == null) {
            ((HttpServletResponse) servletResponse).sendRedirect("/view/login.jsf");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
