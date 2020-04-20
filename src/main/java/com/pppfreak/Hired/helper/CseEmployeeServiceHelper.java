package com.pppfreak.Hired.helper;

import com.pppfreak.Hired.Entity.CseEmployee;
import com.pppfreak.Hired.form.request.CseEmployeeRequestForm;

import com.pppfreak.Hired.repository.CseEmployeeRepository;
import com.pppfreak.Hired.response.CseEmployeeResponse;
import com.pppfreak.Hired.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CseEmployeeServiceHelper {

    private final ModelMapper modelMapper;
    private final CseEmployeeRepository cseEmployeeRepository;

    @Autowired
    public CseEmployeeServiceHelper(ModelMapper modelMapper , CseEmployeeRepository cseEmployeeRepository ) {
        this.modelMapper            = modelMapper;
        this.cseEmployeeRepository  = cseEmployeeRepository;

    }

    public EmployeeResponse assignCseEmployee(CseEmployeeRequestForm cseEmployeeRequestForm){
        CseEmployee cseEmployee = modelMapper.map(cseEmployeeRequestForm,CseEmployee.class);
        cseEmployeeRepository.save(cseEmployee);
        return modelMapper.map(cseEmployee,EmployeeResponse.class);
    }
}
