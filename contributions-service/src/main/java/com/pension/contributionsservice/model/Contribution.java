package com.pension.contributionsservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "contributions")
public class Contribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contributionId;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDate contributionDate;
}
