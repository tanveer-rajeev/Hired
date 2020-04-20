package com.pppfreak.Hired.serviceimpl;

import com.pppfreak.Hired.Entity.UserEmployee;
import com.pppfreak.Hired.Entity.TextileEmployee;
import com.pppfreak.Hired.form.request.MasterForm;
import com.pppfreak.Hired.helper.CseEmployeeServiceHelper;
import com.pppfreak.Hired.helper.EmployeeType;
import com.pppfreak.Hired.helper.TextileEmployeeServiceHelper;
import com.pppfreak.Hired.response.EmployeeResponse;
import com.pppfreak.Hired.security.LoggedInUserDetails;
import com.pppfreak.Hired.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeServiceImpl implements EmployeeService {


    private final CseEmployeeServiceHelper cseEmployeeServiceHelper;

    private final TextileEmployeeServiceHelper textileEmployeeServiceHelper;

    @Autowired
    public EmployeeServiceImpl(CseEmployeeServiceHelper cseEmployeeServiceHelper ,
                               TextileEmployeeServiceHelper textileEmployeeServiceHelper) {

        this.cseEmployeeServiceHelper = cseEmployeeServiceHelper;
        this.textileEmployeeServiceHelper = textileEmployeeServiceHelper;
    }

    @Override
    public EmployeeResponse addEmployee(MasterForm masterForm)  {

        UserEmployee employee = LoggedInUserDetails.getUserEntity();
        String department = employee.getDepartment();
        System.out.println(department);
        if(EmployeeType.CSE.getDepartment().equals(department)){
            return cseEmployeeServiceHelper.assignCseEmployee(masterForm.getCseEmployeeRequestForm());

        }else if(EmployeeType.TEXTILE.getDepartment().equals(department)){

            return textileEmployeeServiceHelper.assignTextileEmployee(masterForm.getTextileEmployeeRequestForm());
        }

        return new EmployeeResponse();

    }

    @Override
    public TextileEmployee getEmployeeByUserId(String userId) {
//        TextileEmployee textileEmployee = employeeRepository.findByUserId(userId);
//
//        if(textileEmployee ==null){
//            throw new RuntimeException("Emplopee Not Found "+userId);
//        }
//        return textileEmployee;
        return null;
    }

    @Override
    public List<TextileEmployee> getAllEmployee() {
//        List<TextileEmployee> textileEmployee = new ArrayList<>();
//                employeeRepository
//                        .findAll().forEach(textileEmployee::add);
//                return textileEmployee;
        return null;
    }




    @Override
    public void updateEmployee(TextileEmployee textileEmployee) {
        //employeeRepository.save(textileEmployee);
    }

    @Override
    public void deleteEmployee(Integer id) {
//        Optional<TextileEmployee> employee = employeeRepository.findById(id);
//        if(!employee.isPresent()){
//            throw  new RuntimeException("Employee not found "+id);
//        }
//        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteEmployeeExperience(Integer employeeId,Integer experienceId) {

//        Optional<TextileEmployee> employee = employeeRepository.findById(employeeId);
//
//        Experience experience = experienceService.getExperience(experienceId);
//
//        if(!employee.isPresent()){
//            throw  new RuntimeException("Employee not found "+employeeId);
//        }
//        if(!experience
//                .getId()
//                .equals(experienceId)){
//            throw  new RuntimeException("Experience not present in employee profile "+experienceId);
//        }
//
//        experienceService.deleteExperience(experienceId);
    }

    @Override
    public TextileEmployee getEmployee(String email) {
//        return  employeeRepository.findByEmail(email);
        return null;
    }



}
