package com.pension.benefits_exitsservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class Contribution {

    private Long contributionId;

    private Long memberId;

    private Double amount;

    private String contributionDate;
}
