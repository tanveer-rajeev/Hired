package com.pppfreak.Hired.service;

import com.pppfreak.Hired.Entity.CseEmployee;
import com.pppfreak.Hired.Entity.Employee;
import com.pppfreak.Hired.Entity.JobCircular;
import com.pppfreak.Hired.form.request.EmployeeRequestForm;
import com.pppfreak.Hired.response.EmployeeResponse;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EmployeeService {

    Employee getEmployeeById(Integer id);

    Employee getUserByEmail(String email);

    EmployeeResponse getUserByUserId(String userId);

    EmployeeResponse signUp(EmployeeRequestForm employee);

    List<Employee> getALlEmployee();

    EmployeeResponse updateEmployee(EmployeeRequestForm employeeRequestForm, String id);

    String deleteEmployeeByUserId(String userId);

    String deleteEmployeeById(Integer employeeId);

    List<CseEmployee> getCseEmployeeByEmployeeUserId(String userId);

    Set<JobCircular> getAppliedJobCircular(Integer employeeId);

    String deleteJobApplyForm(Integer id);
}
