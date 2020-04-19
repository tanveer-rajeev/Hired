package com.pppfreak.Hired.controller;

import com.pppfreak.Hired.form.request.UserEmployeeRequestForm;
import com.pppfreak.Hired.response.EmployeeRegistrationResponse;
import com.pppfreak.Hired.response.EmployeeResponse;
import com.pppfreak.Hired.form.request.TextileEmployeeRequestForm;
import com.pppfreak.Hired.service.EmployeeService;
import com.pppfreak.Hired.Entity.TextileEmployee;
import com.pppfreak.Hired.service.UserEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    //   hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')

    private final EmployeeService employeeService;
    private final UserEmployeeService userEmployeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService , UserEmployeeService userEmployeeService) {
        this.employeeService     = employeeService;
        this.userEmployeeService = userEmployeeService;
    }


    @PostMapping("/registration")
    public String registrationEmployee(
            @RequestBody UserEmployeeRequestForm userEmployeeRequestForm) {
        EmployeeRegistrationResponse employeeRegistrationResponse=userEmployeeService.register(userEmployeeRequestForm);
        String department = employeeRegistrationResponse.getDept();
        String profileFormURI = ServletUriComponentsBuilder.fromCurrentContextPath()
                             .path("/profile")
                             .path(department)
                             .toUriString();
        return profileFormURI;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('employee:read')")
    public List<TextileEmployee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('employee:read')")
    public TextileEmployee getEmployeeByUserId(@PathVariable String userId) {

        return employeeService.getEmployeeByUserId(userId);
    }


    @PostMapping(path = "/profile/{dept}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeResponse addEmployee(@RequestBody TextileEmployeeRequestForm employee,@PathVariable String dept) {

        // dept wise employee get a  form

        return employeeService.addEmployee(employee);

    }

    @PutMapping
    @PreAuthorize("hasAuthority('employee:write')")
    public void updateEmployee(@RequestBody TextileEmployee textileEmployee) {
        employeeService.updateEmployee(textileEmployee);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }

    @DeleteMapping("{employeeId}/experiences/{experienceId}")
    @PreAuthorize("hasAuthority('employee:write')")
    public void deleteEmployeeExperience(@PathVariable Integer employeeId , @PathVariable Integer experienceId) {
        employeeService.deleteEmployeeExperience(employeeId , experienceId);
    }


}
