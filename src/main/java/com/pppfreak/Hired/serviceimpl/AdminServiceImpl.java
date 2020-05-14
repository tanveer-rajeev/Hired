package com.pppfreak.Hired.serviceimpl;

import com.pppfreak.Hired.Entity.Admin;
import com.pppfreak.Hired.Entity.EmployeeType;
import com.pppfreak.Hired.form.request.AdminRequestForm;
import com.pppfreak.Hired.repository.AdminRepository;
import com.pppfreak.Hired.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;
    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository , BCryptPasswordEncoder bCryptPasswordEncoder ,
                            ModelMapper modelMapper) {
        this.adminRepository = adminRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public Admin addAdmin(AdminRequestForm requestForm) {

        Admin admin = modelMapper.map(requestForm,Admin.class);
        admin.setEncryptedPassword(bCryptPasswordEncoder.encode(requestForm.getPassword()));
        adminRepository.save(admin);
        return admin;
    }

    @Override
    public Admin getAdmin(Integer id) {
        Optional<Admin> admin = adminRepository.findById(id);

        return admin.get();
    }

    @Override
    public ResponseEntity<Admin> updateAdmin(AdminRequestForm requestForm) {
        Admin admin = adminRepository.findByEmail(requestForm.getEmail());
        admin.setEncryptedPassword(bCryptPasswordEncoder.encode(requestForm.getPassword()));
        admin.setEmployeeType(EmployeeType.ADMIN);
        adminRepository.save(admin);
        return ResponseEntity.ok().body(admin);
    }


}
