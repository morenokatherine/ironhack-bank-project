package com.bank.controllers.user;

import com.bank.dto.user.CreateAdminDTO;
import com.bank.models.user.Admin;
import com.bank.services.user.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/users/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin createAdmin(@RequestBody CreateAdminDTO createAdminDTO){
        return adminService.createAdmin(createAdminDTO);
    }
}
