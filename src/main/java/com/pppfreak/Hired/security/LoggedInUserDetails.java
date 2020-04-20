package com.pppfreak.Hired.security;

import com.pppfreak.Hired.Entity.UserEmployee;
import com.pppfreak.Hired.SpringApplicationContext;
import com.pppfreak.Hired.service.UserEmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoggedInUserDetails {

    public static UserEmployee getUserEntity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //  String userEmail = ((UserEntity)authentication.getPrincipal()).getUsername();
        String userEmail = authentication.getName();
        System.out.println(userEmail);

        UserEmployeeService  userEmployeeService=
                (UserEmployeeService) SpringApplicationContext.getBean("userEmployeeServiceImpl");
        return userEmployeeService.getUserByEmail(userEmail);

    }

}
