package com.pppfreak.Hired.controller;

import com.pppfreak.Hired.Entity.Employee_Response;
import com.pppfreak.Hired.request_response_Model.EmployeeRequestModel;
import com.pppfreak.Hired.service.EmployeeService;
import com.pppfreak.Hired.Entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

//   hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping(path = "/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('employee:read')")
    public Employee getEmployeeByUserId(@PathVariable String userId) {

        return employeeService.getEmployeeByUserId(userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee_Response addEmployee(@RequestBody EmployeeRequestModel employee) {

        return  employeeService.addEmployee(employee);

    }

    @PutMapping
    @PreAuthorize("hasAuthority('employee:write')")
    public void updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }

    @DeleteMapping("{employeeId}/experiences/{experienceId}")
    @PreAuthorize("hasAuthority('employee:write')")
    public void deleteEmployeeExperience(@PathVariable Integer employeeId, @PathVariable Integer experienceId) {
        employeeService.deleteEmployeeExperience(employeeId, experienceId);
    }


}
