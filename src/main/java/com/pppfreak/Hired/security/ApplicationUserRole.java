package com.pppfreak.Hired.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.pppfreak.Hired.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    EMPLOYEE(Sets.newHashSet(EMPLOYEE_WRITE,EMPLOYEE_READ)),
    ADMIN(Sets.newHashSet(EMPLOYEE_WRITE, EMPLOYEE_READ));

    private final Set<ApplicationUserPermission> roles;


    ApplicationUserRole(Set<ApplicationUserPermission> roles) {
        this.roles = roles;
    }

    public Set<ApplicationUserPermission> getRoles() {
        return roles;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> simpleGrantedAuthority =
                getRoles().stream()
                          .map(role -> new SimpleGrantedAuthority(role.getPermission()))
                          .collect(Collectors.toSet());
        simpleGrantedAuthority.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return simpleGrantedAuthority;
    }
}
