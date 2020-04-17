package com.pppfreak.Hired.service;

import com.pppfreak.Hired.Entity.Employee;
import com.pppfreak.Hired.request_response_Model.Employee_Response;
import com.pppfreak.Hired.request_response_Model.EmployeeRequestModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface EmployeeService extends UserDetailsService {


     Employee getEmployeeByUserId(String userId) ;
    List<Employee> getAllEmployee();
    Employee_Response addEmployee(EmployeeRequestModel employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(Integer id);
    void deleteEmployeeExperience(Integer employeeId,Integer experienceId);
    Employee getEmployee(String email);
}
