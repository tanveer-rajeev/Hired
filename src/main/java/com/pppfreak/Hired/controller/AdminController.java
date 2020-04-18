package com.pppfreak.Hired.controller;

import com.pppfreak.Hired.Entity.Admin;
import com.pppfreak.Hired.response.AdminResponse;
import com.pppfreak.Hired.form.request.AdminRequestForm;
import com.pppfreak.Hired.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
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
    public AdminResponse addAdmin(@RequestBody AdminRequestForm admin_response){
        return adminService.addAdmin(admin_response);
    }


}
