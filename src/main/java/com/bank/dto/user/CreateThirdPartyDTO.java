package com.bank.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CreateThirdPartyDTO {
    private String name;
    private String hashKey;

}
