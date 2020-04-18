package com.pppfreak.Hired.service;

import com.pppfreak.Hired.Entity.TextileEmployee;
import com.pppfreak.Hired.response.EmployeeResponse;
import com.pppfreak.Hired.form.request.EmployeeRequestForm;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface EmployeeService extends UserDetailsService {


     TextileEmployee getEmployeeByUserId(String userId) ;
    List<TextileEmployee> getAllEmployee();
    EmployeeResponse addEmployee(EmployeeRequestForm employee);
    void updateEmployee(TextileEmployee textileEmployee);
    void deleteEmployee(Integer id);
    void deleteEmployeeExperience(Integer employeeId,Integer experienceId);
    TextileEmployee getEmployee(String email);
}
