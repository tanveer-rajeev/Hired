package com.pppfreak.Hired.helper;

import com.pppfreak.Hired.Entity.CseEmployee;
import com.pppfreak.Hired.Entity.TextileEmployee;
import com.pppfreak.Hired.form.request.CseEmployeeRequestForm;
import com.pppfreak.Hired.form.request.TextileEmployeeRequestForm;
import com.pppfreak.Hired.repository.CseEmployeeRepository;
import com.pppfreak.Hired.repository.TextileEmployeeRepository;
import com.pppfreak.Hired.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextileEmployeeServiceHelper {

    private final ModelMapper modelMapper;
    private final TextileEmployeeRepository textileEmployeeRepository;

    @Autowired
    public TextileEmployeeServiceHelper(ModelMapper modelMapper , TextileEmployeeRepository textileEmployeeRepository) {
        this.modelMapper            = modelMapper;
        this.textileEmployeeRepository  = textileEmployeeRepository;

    }

    public EmployeeResponse assignTextileEmployee(TextileEmployeeRequestForm textileEmployeeRequestForm){
        TextileEmployee textileEmployee = modelMapper.map(textileEmployeeRequestForm,TextileEmployee.class);
        textileEmployeeRepository.save(textileEmployee);
        return modelMapper.map(textileEmployee,EmployeeResponse.class);
    }
}
