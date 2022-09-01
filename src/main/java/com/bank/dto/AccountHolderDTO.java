package com.bank.dto;

import com.bank.models.utils.Address;
import lombok.*;

import javax.persistence.Embedded;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AccountHolderDTO {
    private String name;
    @Embedded
    private Address address;
    private String password;
    private LocalDate birthday;

}
