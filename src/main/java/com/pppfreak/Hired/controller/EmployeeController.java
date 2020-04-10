package com.pppfreak.Hired.controller;

import com.pppfreak.Hired.Entity.Response;
import com.pppfreak.Hired.service.EmployeeService;
import com.pppfreak.Hired.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("{id}")
    public Employee getEmployeeById(@PathVariable Integer id){
        return employeeService.getEmployeeById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public Response addEmployee(@RequestBody Employee employee ) throws IOException {

        employeeService.addEmployee(employee);
        return new Response(employee.getName(),employee.getUniversity(),employee.getDepartment(),employee.getResumeURL());
    }

    @PutMapping
    public void updateEmployee(@RequestBody Employee employee){
        employeeService.updateEmployee(employee);
    }

    @DeleteMapping("{id}")
    public void deleteEmployee(@PathVariable Integer id){
         employeeService.deleteEmployee(id);
    }

    @DeleteMapping("{employeeId}/experiences/{experienceId}")
    public void deleteEmployeeExperience(@PathVariable Integer employeeId,@PathVariable Integer experienceId){
        employeeService.deleteEmployeeExperience(employeeId,experienceId);
    }


}
