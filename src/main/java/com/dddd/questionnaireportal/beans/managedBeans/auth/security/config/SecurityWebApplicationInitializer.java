package com.dddd.questionnaireportal.beans.managedBeans.auth.security.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
        public SecurityWebApplicationInitializer(){
            super(SecurityConfig.class);
        }
}
