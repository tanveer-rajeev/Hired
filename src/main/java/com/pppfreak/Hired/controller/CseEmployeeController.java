package com.pppfreak.Hired.controller;

import com.pppfreak.Hired.Entity.CseEmployee;
import com.pppfreak.Hired.form.request.CseEmployeeRequestForm;
import com.pppfreak.Hired.response.CseEmployeeResponse;
import com.pppfreak.Hired.service.CseEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cseEmployees")
public class CseEmployeeController {

    private final CseEmployeeService cseEmployeeService;
    @Autowired
    public CseEmployeeController(CseEmployeeService cseEmployeeService ) {
        this.cseEmployeeService = cseEmployeeService;
    }

    @GetMapping
    public List<CseEmployee> getAllCseEmployee(){
        return cseEmployeeService.getAllCseEmployee();
    }

    @GetMapping("/{id}")
    public Optional<CseEmployee> getCseEmployeeById(@PathVariable Integer id){
        return cseEmployeeService.getCseEmployeeById(id);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public CseEmployeeResponse addCseEmployee(@RequestBody CseEmployeeRequestForm cseEmployeeRequestForm){
      return   cseEmployeeService.addCseEmployee(cseEmployeeRequestForm);
    }

    @PutMapping("/{id}")
    public void updateCseEmployee(@RequestBody CseEmployeeRequestForm cseEmployeeRequestForm,@PathVariable Integer id){
         cseEmployeeService.updateCseEmployee(cseEmployeeRequestForm,id);
    }

    @DeleteMapping("/{id}")
    public void deleteCseEmployee(@PathVariable Integer id){
        cseEmployeeService.deleteCseEmployee(id);
    }



}
