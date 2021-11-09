package com.dddd.questionnaireportal.common.filter;

import com.dddd.questionnaireportal.common.contants.Constants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String url = httpServletRequest.getRequestURI();
        boolean pageInFreeAccess = !(url.equals(Constants.FIELDS_URL) || url.equals(Constants.RESPONSES_URL)
                || url.equals(Constants.CHANGE_PASSWORD_URL) || url.equals(Constants.EDIT_USER_URL));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication.isAuthenticated()) {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } else {
                httpServletResponse.sendRedirect(Constants.LOGIN_URL);
            }
        } else {
            if (pageInFreeAccess) {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } else {
                httpServletResponse.sendRedirect(Constants.LOGIN_URL);
            }
        }
    }
}

