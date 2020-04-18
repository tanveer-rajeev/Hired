package com.pppfreak.Hired.service;

import com.pppfreak.Hired.Entity.Admin;
import com.pppfreak.Hired.response.AdminResponse;
import com.pppfreak.Hired.form.request.AdminRequestForm;

import java.util.List;

public interface AdminService {

    List<Admin> getAllAdmin();
    Admin getAdminByUserId(String userId);
    AdminResponse addAdmin(AdminRequestForm admin);
    AdminResponse update_Admin(Admin admin);

    void delete_Admin(Integer id);
}
