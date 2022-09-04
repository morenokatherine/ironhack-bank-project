package com.bank.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CreateAdminDTO extends CreateUserDTO{
    public CreateAdminDTO(String name, String password) {
        super(name, password);
    }

}
