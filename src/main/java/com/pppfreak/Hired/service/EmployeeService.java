package com.pppfreak.Hired.service;

import com.pppfreak.Hired.Entity.Employee;

import java.util.List;

public interface EmployeeService {


    Employee getEmployeeById(Integer id);
    List<Employee> getAllEmployee();
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(Integer id);
    void deleteEmployeeExperience(Integer employeeId,Integer experienceId);
}
