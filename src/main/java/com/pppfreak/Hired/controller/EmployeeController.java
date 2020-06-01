package com.pppfreak.Hired.controller;

import com.pppfreak.Hired.Entity.CseEmployee;
import com.pppfreak.Hired.Entity.Employee;
import com.pppfreak.Hired.Entity.JobCircular;
import com.pppfreak.Hired.form.request.EmployeeRequestForm;
import com.pppfreak.Hired.response.EmployeeResponse;
import com.pppfreak.Hired.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    //   hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Employee> getALlEmployee(){
        return employeeService.getALlEmployee();
    }

    @GetMapping(path = "/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeResponse getEmployeeByUserId(@PathVariable String userId){
        return employeeService.getUserByUserId(userId);
    }

    @GetMapping("cseEmployees/{userId}")
    public List<CseEmployee> getAllCseEmployee(@PathVariable String userId){
        return employeeService.getCseEmployeeByEmployeeUserId(userId);
    }

    @PostMapping("/signUp")
    public EmployeeResponse signUp(@RequestBody EmployeeRequestForm employeeRequestForm) {
        return employeeService.signUp(employeeRequestForm);
    }

    @PutMapping("/{userId}")
    public EmployeeResponse updateEmployee(@RequestBody EmployeeRequestForm employeeRequestForm, @PathVariable String userId){
        return employeeService.updateEmployee(employeeRequestForm,userId);
    }

    @DeleteMapping("/{userId}")
    public String deleteEmployeeByUserId(@PathVariable String userId){
        return employeeService.deleteEmployeeByUserId(userId);
    }

    @GetMapping("appliedJobs/{employeeId}")
    public Set<JobCircular> getAllAppliedJobs(@PathVariable Integer employeeId){
     return    employeeService.getAppliedJobCircular(employeeId);
    }
}
