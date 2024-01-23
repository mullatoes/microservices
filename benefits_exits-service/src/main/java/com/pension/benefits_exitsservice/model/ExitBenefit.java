package com.pension.benefits_exitsservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "exit_benefits")
public class ExitBenefit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Double amount;
}
