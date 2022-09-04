package com.bank.controllers.user;

import com.bank.dto.user.CreateThirdPartyDTO;
import com.bank.models.user.ThirdParty;
import com.bank.services.user.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThirdPartyController {
    @Autowired
    ThirdPartyService thirdPartyService;

    @PostMapping("/users/third-party")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty createThirdParty(@RequestBody CreateThirdPartyDTO createThirdPartyDTO){
        return thirdPartyService.createThirdParty(createThirdPartyDTO);
    }
}
