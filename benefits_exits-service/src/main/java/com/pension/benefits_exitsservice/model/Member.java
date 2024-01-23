package com.pension.benefits_exitsservice.model;

import lombok.Data;

@Data
public class Member {
    private Long memberId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private MemberShipStatus memberShipStatus;
}
