package com.pppfreak.Hired.helper;

import com.pppfreak.Hired.Entity.TextileEmployee;
import com.pppfreak.Hired.customise.MassageConstant;
import com.pppfreak.Hired.form.request.TextileEmployeeRequestForm;
import com.pppfreak.Hired.repository.TextileEmployeeRepository;
import com.pppfreak.Hired.response.TextileEmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TextileEmployeeServiceHelper {

    private final ModelMapper modelMapper;
    private final TextileEmployeeRepository textileEmployeeRepository;

    @Autowired
    public TextileEmployeeServiceHelper(ModelMapper modelMapper , TextileEmployeeRepository textileEmployeeRepository) {
        this.modelMapper            = modelMapper;
        this.textileEmployeeRepository  = textileEmployeeRepository;

    }

    public TextileEmployeeResponse assignTextileEmployee(TextileEmployeeRequestForm textileEmployeeRequestForm){
        TextileEmployee textileEmployee = modelMapper.map(textileEmployeeRequestForm,TextileEmployee.class);
        textileEmployeeRepository.save(textileEmployee);
        return modelMapper.map(textileEmployee, TextileEmployeeResponse.class);
    }

    public String setResumeUrl(String resumeUrl , Integer id) {
        Optional<TextileEmployee> cseEmployee = textileEmployeeRepository.findById(id);
        TextileEmployee theEmployee;

        if (cseEmployee.isPresent()){
            theEmployee=cseEmployee.get();
        }else {
            throw new UsernameNotFoundException("User not found "+id);
        }
        theEmployee.setResumeURL(resumeUrl);

        textileEmployeeRepository.save(theEmployee);
        return MassageConstant.SUCCESS_TO_UPLOAD.toString();

    }
}
