package com.pppfreak.Hired.service;

import com.pppfreak.Hired.Entity.Admin;
import com.pppfreak.Hired.form.request.AdminRequestForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

public interface AdminService {

    Admin addAdmin(AdminRequestForm admin);

    Admin getAdmin(Integer id);

    ResponseEntity<Admin> updateAdmin(AdminRequestForm admin);
}
