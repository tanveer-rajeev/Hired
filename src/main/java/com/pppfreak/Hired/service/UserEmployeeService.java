package com.pppfreak.Hired.service;

import com.pppfreak.Hired.Entity.UserEmployee;
import com.pppfreak.Hired.form.request.UserEmployeeRequestForm;
import com.pppfreak.Hired.response.EmployeeRegistrationResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserEmployeeService extends UserDetailsService {

     UserEmployee getUserByEmail(String  email);
     UserEmployee getUserByUserId(String userId);
     EmployeeRegistrationResponse register(UserEmployeeRequestForm employee);
}
