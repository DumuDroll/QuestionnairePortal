package com.dddd.questionnaireportal.beans.managedBeans.auth.security.config;

import com.dddd.questionnaireportal.beans.filter.AuthorizationFilter;
import com.dddd.questionnaireportal.beans.managedBeans.auth.security.userDetails.UserDetailsServiceImpl;
import com.dddd.questionnaireportal.common.util.MD5Util.MD5Util;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return MD5Util.getSecurePassword(charSequence.toString());
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return MD5Util.getSecurePassword(charSequence.toString()).equals(s);
            }
        };
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean("AuthenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers("/javax.faces.resource/**",
//                "/resources/**",
//                "/login",
//                "/signUp",
//                "/",
//                "/newPassConfirm",
//                "/passChangeActivation",
//                "/registrationActivation",
//                "/success",
//                "/forgotPass");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .cors()
                .disable()
                .addFilterAfter(
                        new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/javax.faces.resource/**",
                "/resources/**",
                "/login",
                "/signUp",
                "/",
                "/newPassConfirm",
                "/passChangeActivation",
                "/registrationActivation",
                "/success",
                "/forgotPass").anonymous()
                .anyRequest()
                .authenticated();

    }
}