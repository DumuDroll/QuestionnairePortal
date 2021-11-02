package com.dddd.questionnaireportal.beans.managedBeans.auth.security.userDetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails  implements UserDetails {

    private final CurrentUser user;

    public MyUserDetails(CurrentUser user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authoritySet = user.getAuthorities();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (GrantedAuthority authority : authoritySet) {
            authorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }

        return authorities;
    }

    public String getFirstName(){
        return  user.getFirstName();
    }

    public String getLastName(){
        return  user.getLastName();
    }

    public void setFirstName(String firstName){
        user.setFirstName(firstName);
    }

    public void setLastName(String lastname){
        user.setLastName(lastname);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

}
