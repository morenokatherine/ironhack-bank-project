package com.bank.repositories.user;

import com.bank.models.user.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThirdPartyRepository extends JpaRepository<ThirdParty,Integer> {
    Optional<ThirdParty> findByHashKey(String hashKey);
}
