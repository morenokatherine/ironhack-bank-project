package com.bank.services.user;

import com.bank.dto.user.CreateThirdPartyDTO;
import com.bank.models.user.ThirdParty;
import com.bank.repositories.user.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThirdPartyService {
    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    public ThirdParty getThirdPartyByHashKey(String hashKey) {
        Optional<ThirdParty> thirdParty = thirdPartyRepository.findByHashKey(hashKey);
        if (!thirdParty.isPresent()) {
            throw new IllegalArgumentException("Hash key not found");
        }
        return thirdParty.get();
    }

    public ThirdParty createThirdParty(CreateThirdPartyDTO createThirdPartyDTO) {
        ThirdParty thirdParty = new ThirdParty(createThirdPartyDTO.getName(), createThirdPartyDTO.getHashKey());
        return thirdPartyRepository.save(thirdParty);
    }
}
