package com.pension.memberservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class MemberDTO {
    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String dateOfBirth;
}
