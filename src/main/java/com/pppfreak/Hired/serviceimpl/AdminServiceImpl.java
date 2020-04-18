package com.pppfreak.Hired.serviceimpl;

import com.pppfreak.Hired.customise.Custom;
import com.pppfreak.Hired.repository.AdminRepository;
import com.pppfreak.Hired.Entity.Admin;
import com.pppfreak.Hired.response.AdminResponse;
import com.pppfreak.Hired.form.request.AdminRequestForm;
import com.pppfreak.Hired.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;

    private ModelMapper modelMapper;

    private Custom custom;

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository , ModelMapper modelMapper , Custom custom , BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
        this.custom = custom;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public List<Admin> getAllAdmin() {
        List<Admin> admins = new ArrayList<>();
        adminRepository
                .findAll()
                .forEach(admins::add);
        return admins;
    }

    @Override
    public Admin getAdminByUserId(String userId) {
        Admin byUserId = adminRepository.findByUserId(userId);
        if (byUserId == null) {
            throw new RuntimeException("Admin not found  " + userId);
        }
        return byUserId;
    }

    @Override
    public AdminResponse addAdmin(AdminRequestForm admin) {

        if(adminRepository.findByEmail(admin.getEmail())!=null){
            throw new RuntimeException("Record already exist "+admin.getEmail());
        }

        Admin theAdmin = modelMapper.map(admin , Admin.class);

        theAdmin.setUserId(custom.generatedCustomUserId());
        theAdmin.setEncryptedPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        adminRepository.save(theAdmin);

        AdminResponse admin_response = modelMapper.map(theAdmin , AdminResponse.class);
        return admin_response;
    }

    @Override
    public AdminResponse update_Admin(Admin admin) {
        Admin admin1 = adminRepository.save(admin);
        AdminResponse admin_response = modelMapper.map(admin1, AdminResponse.class);
        return admin_response;
    }

    @Override
    public void delete_Admin(Integer id) {

        adminRepository.findById(id);
    }
}
