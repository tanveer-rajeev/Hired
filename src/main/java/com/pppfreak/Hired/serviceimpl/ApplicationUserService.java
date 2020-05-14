package com.pppfreak.Hired.serviceimpl;

import com.pppfreak.Hired.Entity.Admin;
import com.pppfreak.Hired.Entity.CompanyProfile;
import com.pppfreak.Hired.Entity.Employee;
import com.pppfreak.Hired.repository.AdminRepository;
import com.pppfreak.Hired.repository.CompanyProfileRepository;
import com.pppfreak.Hired.repository.EmployeeRepository;
import com.pppfreak.Hired.security.ApplicationUserRole;
import com.pppfreak.Hired.security.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;
    private final AdminRepository adminRepository;
    private final CompanyProfileRepository companyProfileRepository;

    @Autowired
    public ApplicationUserService(EmployeeRepository employeeRepository , AdminRepository adminRepository ,
                                  CompanyProfileRepository companyProfileRepository) {
        this.employeeRepository       = employeeRepository;
        this.adminRepository          = adminRepository;
        this.companyProfileRepository = companyProfileRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(username);
        Employee employee = employeeRepository.findByEmail(username);
        CompanyProfile companyProfile = companyProfileRepository.findByEmail(username);

        if (admin != null) {
            return new UserEntity(admin.getEmail() , admin.getEncryptedPassword() ,
                    ApplicationUserRole.ADMIN.getGrantedAuthorities() , true , true , true , true);
        } else if (companyProfile != null) {
            return new UserEntity(companyProfile.getEmail() , companyProfile.getEncryptedPassword() ,
                    ApplicationUserRole.COMPANY.getGrantedAuthorities() , true , true , true , true);
        }
        return new UserEntity(employee.getEmail() , employee.getEncryptedPassword() ,
                ApplicationUserRole.EMPLOYEE.getGrantedAuthorities() , true , true , true , true);


    }
}
