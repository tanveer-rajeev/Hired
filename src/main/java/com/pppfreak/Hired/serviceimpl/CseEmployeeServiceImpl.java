package com.pppfreak.Hired.serviceimpl;

import com.pppfreak.Hired.Entity.CseEmployee;
import com.pppfreak.Hired.Entity.Employee;
import com.pppfreak.Hired.customise.MassageConstant;
import com.pppfreak.Hired.form.request.CseEmployeeRequestForm;

import com.pppfreak.Hired.repository.CseEmployeeRepository;
import com.pppfreak.Hired.response.CseEmployeeResponse;
import com.pppfreak.Hired.response.TextileEmployeeResponse;
import com.pppfreak.Hired.security.LoggedInUserDetails;
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

    public Employee loggedInEmployee(){
        return LoggedInUserDetails.getUserEntity();
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

        return (List<CseEmployee>) cseEmployeeRepository.findAll();
    }

    @Override
    public CseEmployeeResponse addCseEmployee(CseEmployeeRequestForm tempEmployee,Integer employeeId) {

        CseEmployee cseEmployee = modelMapper.map(tempEmployee,CseEmployee.class);
        Employee employee = loggedInEmployee();
        if(employee.getId().equals(employeeId)){
            cseEmployee.setEmployee(new Employee(employeeId,"","",""));
        }else{
            throw new RuntimeException("Id not found "+employeeId);
        }

        cseEmployeeRepository.save(cseEmployee);
        return modelMapper.map(cseEmployee,CseEmployeeResponse.class);
    }


    public CseEmployee getCseEmployeeById(Integer id){
        Optional<CseEmployee> employeeRepositoryById = cseEmployeeRepository.findById(id);
        return employeeRepositoryById.get();
    }

}
