package com.pppfreak.Hired.service;

import com.pppfreak.Hired.Entity.Admin;
import com.pppfreak.Hired.request_response_Model.Admin_Response;
import com.pppfreak.Hired.request_response_Model.Admin_RequestModel;

import java.util.List;

public interface AdminService {

    List<Admin> getAllAdmin();
    Admin getAdminByUserId(String userId);
    Admin_Response addAdmin(Admin_RequestModel admin);
    Admin_Response update_Admin(Admin admin);

    void delete_Admin(Integer id);
}
