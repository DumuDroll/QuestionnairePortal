package com.dddd.questionnaireportal.beans.managedBeans.auth.security.userDetails;

import com.dddd.questionnaireportal.database.entity.Authority;
import com.dddd.questionnaireportal.database.entity.User;
import com.dddd.questionnaireportal.database.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = UserService.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        List<GrantedAuthority> authorities = buildUserAuthority(user.getAuthorities());

        return new MyUserDetails(buildUserForAuthentication(user, authorities));
    }

    private CurrentUser buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {

        String username = user.getEmail();

        String password = user.getPassword();

        CurrentUser currentUser = new CurrentUser(username, password, true, true,
                true, true, authorities);
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        return currentUser;
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Authority> authorities) {

        Set<GrantedAuthority> setAuths = new HashSet<>();

        for (Authority authority : authorities) {
            setAuths.add(new SimpleGrantedAuthority(authority.getName()));
        }

        return new ArrayList<>(setAuths);
    }

}