package com.pppfreak.Hired.controller;

import com.pppfreak.Hired.Entity.CseEmployee;
import com.pppfreak.Hired.form.request.CseEmployeeRequestForm;
import com.pppfreak.Hired.response.CseEmployeeResponse;
import com.pppfreak.Hired.service.CseEmployeeService;
import com.sun.javafx.css.CssError;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cseEmployee")
public class CseEmployeeController {

    private final CseEmployeeService cseEmployeeService;
    private final ModelMapper modelMapper;
    @Autowired
    public CseEmployeeController(CseEmployeeService cseEmployeeService , ModelMapper modelMapper) {
        this.cseEmployeeService = cseEmployeeService;
        this.modelMapper        = modelMapper;
    }

    @GetMapping
    public List<CseEmployee> getAllEmployee(){
        return cseEmployeeService.getAllEmployee();
    }

    @PostMapping("/{employeeId}")
    public CseEmployeeResponse addCseEmployee(@RequestBody CseEmployeeRequestForm cseEmployeeRequestForm
                                              ,@PathVariable Integer employeeId){
      return   cseEmployeeService.addCseEmployee(cseEmployeeRequestForm,employeeId);
    }
}
