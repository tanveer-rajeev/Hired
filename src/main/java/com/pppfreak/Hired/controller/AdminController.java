package com.pppfreak.Hired.controller;

import com.pppfreak.Hired.Entity.Admin;
import com.pppfreak.Hired.form.request.AdminRequestForm;
import com.pppfreak.Hired.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public Admin addAdmin(@RequestBody AdminRequestForm admin) {
        return adminService.addAdmin(admin);
    }

    @GetMapping("/{id}")
    public Admin getAdmin(@PathVariable Integer id) {
        return adminService.getAdmin(id);
    }

}
