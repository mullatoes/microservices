package com.pension.contributionsservice.model;

import lombok.Data;

@Data
public class Member {
    private Long memberId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
}
