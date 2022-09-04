package com.bank.controllers.account;

import com.bank.dto.user.CreateAccountHolderDTO;
import com.bank.dto.account.CreateSavingDTO;
import com.bank.dto.user.CreateAdminDTO;
import com.bank.enums.Status;
import com.bank.models.user.AccountHolder;
import com.bank.models.user.Admin;
import com.bank.services.account.AccountHolderService;
import com.bank.services.account.SavingService;
import com.bank.services.user.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountHolderService accountHolderService;

    @Autowired
    AdminService adminService;

    CreateAccountHolderDTO createAccountHolderDTO;
    CreateAdminDTO createAdminDTO;
    AccountHolder accountHolder;
    Admin admin;

    @BeforeEach
    public void setUp() {
        createAccountHolderDTO = new CreateAccountHolderDTO("katherine - account holder", null, "1234", LocalDate.now());
        createAdminDTO = new CreateAdminDTO("katherine - admin", "1234");
        accountHolder = accountHolderService.createAccountHolder(createAccountHolderDTO);
        admin = adminService.createAdmin(createAdminDTO);
    }

    @Test
    @DisplayName("Test that the get /accounts/ is authorized")
    public void getAccount_Test_Authorized() throws Exception {
        mockMvc.perform(get("/accounts").with(httpBasic(createAccountHolderDTO.getName(),createAccountHolderDTO.getPassword()))).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test it works if an unauthorized account tries to access get /accounts/")
    public void getAccount_Test_Unauthorized() throws Exception {
        mockMvc.perform(get("/accounts")).andExpect(status().isUnauthorized());
        mockMvc.perform(get("/accounts").with(httpBasic(createAccountHolderDTO.getName(),"0000"))).andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Test it works if an unauthorized account tries to access get /accounts/{id}")
    public void getAccountById_Test_Unauthorized() throws Exception {
        mockMvc.perform(get("/accounts/1")).andExpect(status().isUnauthorized());
        mockMvc.perform(get("/accounts/1").with(httpBasic(createAccountHolderDTO.getName(),"0000"))).andExpect(status().isUnauthorized());
    }

}
