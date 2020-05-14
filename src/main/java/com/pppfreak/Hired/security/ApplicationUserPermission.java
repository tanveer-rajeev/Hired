package com.pppfreak.Hired.security;

public enum  ApplicationUserPermission {
    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_WRITE("employee:write"),
    COMPANY_READ("company:read"),
    COMPANY_WRITE("company:write");
    private final String permission;

     ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
