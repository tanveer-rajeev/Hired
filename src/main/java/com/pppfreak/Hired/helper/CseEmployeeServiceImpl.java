package com.pppfreak.Hired.helper;

import com.pppfreak.Hired.Entity.CseEmployee;
import com.pppfreak.Hired.customise.MassageConstant;
import com.pppfreak.Hired.form.request.CseEmployeeRequestForm;

import com.pppfreak.Hired.repository.CseEmployeeRepository;
import com.pppfreak.Hired.response.CseEmployeeResponse;
import com.pppfreak.Hired.response.TextileEmployeeResponse;
import com.pppfreak.Hired.service.CseEmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CseEmployeeServiceImpl implements CseEmployeeService {

    private final ModelMapper modelMapper;
    private final CseEmployeeRepository cseEmployeeRepository;

    @Autowired
    public CseEmployeeServiceImpl(ModelMapper modelMapper , CseEmployeeRepository cseEmployeeRepository ) {
        this.modelMapper            = modelMapper;
        this.cseEmployeeRepository  = cseEmployeeRepository;
    }

    public TextileEmployeeResponse assignCseEmployee(CseEmployeeRequestForm cseEmployeeRequestForm){
        CseEmployee cseEmployee = modelMapper.map(cseEmployeeRequestForm,CseEmployee.class);
        cseEmployeeRepository.save(cseEmployee);
        return modelMapper.map(cseEmployee, TextileEmployeeResponse.class);
    }

    public String setResumeUrl(String viewResumeURI , Integer id) {
        Optional<CseEmployee> cseEmployee = cseEmployeeRepository.findById(id);
        CseEmployee theEmployee;
        if (cseEmployee.isPresent()){
            theEmployee=cseEmployee.get();
        }else {
            throw new UsernameNotFoundException("User not found "+id);
        }
        theEmployee.setResumeURL(viewResumeURI);

        cseEmployeeRepository.save(theEmployee);
        return MassageConstant.SUCCESS_TO_UPLOAD.toString();
    }

    public List<CseEmployee> getAllCseEmployee(){
        List<CseEmployee> list = new ArrayList<>();
         cseEmployeeRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public CseEmployee getEmployeeByUserId(String userId) {
        return null;
    }

    @Override
    public List<CseEmployee> getAllEmployee() {
        return null;
    }

    @Override
    public CseEmployeeResponse addCseEmployee(CseEmployeeRequestForm employee) {
        return null;
    }



}
