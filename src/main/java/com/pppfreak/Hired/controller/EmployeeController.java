package com.pppfreak.Hired.controller;

import com.pppfreak.Hired.Entity.Employee;
import com.pppfreak.Hired.form.request.EmployeeRequestForm;
import com.pppfreak.Hired.response.EmployeeResponse;
import com.pppfreak.Hired.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Employee> getALlEmployee(){
        return employeeService.getALlEmployee();
    }

    @GetMapping(value = "/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getEmployeeByUserId(String userId){
        return employeeService.getUserByUserId(userId);
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
    public String deleteEmployeeByUserId(String userId){
        return employeeService.deleteEmployee(userId);
    }

//    @GetMapping("/{dept}")
//    @PreAuthorize("hasAuthority('employee:read')")
//    public List getAllEmployee(@PathVariable String department) {
//        return cseEmployeeService.getAllEmployee();
//    }

}
