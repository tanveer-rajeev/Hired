package com.pppfreak.Hired.serviceimpl;

import com.pppfreak.Hired.Entity.UserEmployee;
import com.pppfreak.Hired.customise.Custom;
import com.pppfreak.Hired.form.request.UserEmployeeRequestForm;
import com.pppfreak.Hired.repository.UserEmployeeRepository;
import com.pppfreak.Hired.response.EmployeeRegistrationResponse;
import com.pppfreak.Hired.service.UserEmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.pppfreak.Hired.security.ApplicationUserRole.EMPLOYEE;

@Service
public class UserEmployeeServiceImpl implements UserEmployeeService {

    private UserEmployeeRepository userEmployeeRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Custom custom;
    @Autowired
    public UserEmployeeServiceImpl(UserEmployeeRepository userEmployeeRepository ,
                                   BCryptPasswordEncoder bCryptPasswordEncoder , Custom custom) {
        this.userEmployeeRepository = userEmployeeRepository;
        this.bCryptPasswordEncoder  = bCryptPasswordEncoder;
        this.custom                 = custom;
    }

    @Override
    public UserEmployee getUserByEmail(String email) {
        UserEmployee employee= userEmployeeRepository.findByEmail(email);
        if(employee==null){
            throw new UsernameNotFoundException("User not found  "+email);
        }
        return employee;
    }

    @Override
    public UserEmployee getUserByUserId(String userId) {
        UserEmployee employee= userEmployeeRepository.findByUserId(userId);
        if(employee==null){
            throw new UsernameNotFoundException("User not found  "+userId);
        }
        return employee;
    }

    @Override
    public EmployeeRegistrationResponse register(UserEmployeeRequestForm employeeRequestForm) {
        ModelMapper mapper = new ModelMapper();
        UserEmployee employee = mapper.map(employeeRequestForm,UserEmployee.class);
        employee.setEncryptedPassword(bCryptPasswordEncoder.encode(employeeRequestForm.getPassword()));
        employee.setUserId(custom.generatedCustomUserId());
        userEmployeeRepository.save(employee);
        return  mapper.map(employee, EmployeeRegistrationResponse.class);

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEmployee userEmployee = userEmployeeRepository.findByEmail(email);
        if(userEmployee ==null){
            throw new UsernameNotFoundException("User not available  "+email);
        }
        return new User(userEmployee.getEmail(), userEmployee.getEncryptedPassword(), EMPLOYEE.getGrantedAuthorities());
    }

}
