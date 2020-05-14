package com.pppfreak.Hired.service;

import com.pppfreak.Hired.Entity.CseEmployee;
import com.pppfreak.Hired.Entity.Employee;
import com.pppfreak.Hired.form.request.EmployeeRequestForm;
import com.pppfreak.Hired.response.EmployeeResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

     Employee getEmployeeById(Integer id);
     Employee getUserByEmail(String  email);
     EmployeeResponse getUserByUserId(String userId);
     EmployeeResponse signUp(EmployeeRequestForm employee);
     List<Employee>  getALlEmployee();
     EmployeeResponse updateEmployee(EmployeeRequestForm employeeRequestForm,String id);
     String deleteEmployee(String id);
     String deleteEmployeeById(Integer id);
     void subscribeCompany( Integer id,  Integer companyId)   ;
     List<CseEmployee> getCseEmployeeByEmployeeUserId(String userId);
}
