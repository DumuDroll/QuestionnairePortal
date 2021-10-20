package com.dddd.questionnaireportal.beans.filter;

import com.dddd.questionnaireportal.beans.managedBeans.LoginMB;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {


        public static final String LOGIN_PAGE = "/login.xhtml";

        @Override
        public void doFilter(ServletRequest servletRequest,
                             ServletResponse servletResponse, FilterChain filterChain)
                throws IOException, ServletException {

            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

            LoginMB loginMB = (LoginMB) httpServletRequest
                    .getSession().getAttribute("LoginMB");

            if (loginMB != null) {
                if (loginMB.isLoggedIn()) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    httpServletResponse.sendRedirect(httpServletRequest
                            .getContextPath() + LOGIN_PAGE);
                }
            } else {
                httpServletResponse.sendRedirect(httpServletRequest
                        .getContextPath() + LOGIN_PAGE);
            }
        }

        @Override
        public void init(FilterConfig arg0) throws ServletException {
        }

        @Override
        public void destroy() {
        }
    }

