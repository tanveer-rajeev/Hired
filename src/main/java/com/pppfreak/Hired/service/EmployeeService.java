package com.pppfreak.Hired.service;

import com.pppfreak.Hired.Entity.Employee;
import com.pppfreak.Hired.form.request.EmployeeRequestForm;
import com.pppfreak.Hired.response.EmployeeResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface EmployeeService extends UserDetailsService {

     Employee getUserByEmail(String  email);
     Employee getUserByUserId(String userId);
     EmployeeResponse signUp(EmployeeRequestForm employee);
     List<Employee>  getALlEmployee();
     EmployeeResponse updateEmployee(EmployeeRequestForm employeeRequestForm,String id);
     String deleteEmployee(String id);
}
