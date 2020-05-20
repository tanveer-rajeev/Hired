package com.pppfreak.Hired.service;

import com.pppfreak.Hired.Entity.CseEmployee;
import com.pppfreak.Hired.Entity.Employee;
import com.pppfreak.Hired.Entity.JobCircular;
import com.pppfreak.Hired.form.request.EmployeeRequestForm;
import com.pppfreak.Hired.response.EmployeeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

     Employee getEmployeeById(Integer id);
     Employee getUserByEmail(String  email);
     EmployeeResponse getUserByUserId(String userId);
     EmployeeResponse signUp(EmployeeRequestForm employee);
     List<Employee>  getALlEmployee();
     EmployeeResponse updateEmployee(EmployeeRequestForm employeeRequestForm,String id);
     String deleteEmployeeByUserId(String userId);
     String deleteEmployeeById(Integer employeeId);

     List<CseEmployee> getCseEmployeeByEmployeeUserId(String userId);
     List<JobCircular> getAppliedJobCircular(Integer employeeId);
     String deleteJobApplyForm(Integer id);
}
