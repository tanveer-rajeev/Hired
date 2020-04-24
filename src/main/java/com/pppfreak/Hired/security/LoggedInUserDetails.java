package com.pppfreak.Hired.security;

import com.pppfreak.Hired.Entity.Employee;
import com.pppfreak.Hired.SpringApplicationContext;
import com.pppfreak.Hired.service.EmployeeService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoggedInUserDetails {

    public static Employee getUserEntity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //  String userEmail = ((UserEntity)authentication.getPrincipal()).getUsername();
        String userEmail = authentication.getName();

        EmployeeService employeeService =
                (EmployeeService) SpringApplicationContext.getBean("userEmployeeServiceImpl");
        return employeeService.getUserByEmail(userEmail);

    }

}
