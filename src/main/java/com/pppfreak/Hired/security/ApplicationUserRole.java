package com.pppfreak.Hired.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.pppfreak.Hired.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    EMPLOYEE(Sets.newHashSet(EMPLOYEE_WRITE, EMPLOYEE_READ)),
    ADMIN(Sets.newHashSet(EMPLOYEE_READ, COMPANY_READ)),
    COMPANY(Sets.newHashSet(COMPANY_READ, COMPANY_WRITE));

    private final Set<ApplicationUserPermission> roles;


    ApplicationUserRole(Set<ApplicationUserPermission> roles) {
        this.roles = roles;
    }

    public Set<ApplicationUserPermission> getRoles() {
        return roles;
    }

    public List<SimpleGrantedAuthority> getGrantedAuthorities() {
        List<SimpleGrantedAuthority> simpleGrantedAuthority =
                getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getPermission()))
                        .collect(Collectors.toList());
        simpleGrantedAuthority.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return simpleGrantedAuthority;
    }
}
