package com.bank;

import com.bank.dto.user.CreateAdminDTO;
import com.bank.models.user.Admin;
import com.bank.services.user.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	AdminService adminService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CreateAdminDTO createAdminDTO = new CreateAdminDTO("admin", "admin");
		adminService.createAdmin(createAdminDTO);
	}

}
