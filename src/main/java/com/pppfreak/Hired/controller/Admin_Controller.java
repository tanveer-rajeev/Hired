package com.pppfreak.Hired.controller;

import com.pppfreak.Hired.Entity.Admin;
import com.pppfreak.Hired.request_response_Model.Admin_Response;
import com.pppfreak.Hired.request_response_Model.Admin_RequestModel;
import com.pppfreak.Hired.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class Admin_Controller {

    private AdminService adminService;

    @Autowired
    public Admin_Controller(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<Admin> getAllAdmin(){
        return adminService.getAllAdmin();
    }

    @GetMapping("{userId}")
    public Admin getAdminByUserId(@PathVariable  String userId){
        return adminService.getAdminByUserId(userId);
    }

    @PostMapping
    public Admin_Response addAdmin(@RequestBody Admin_RequestModel admin_response){
        return adminService.addAdmin(admin_response);
    }


}
