package com.pension.benefits_exitsservice.repository;

import com.pension.benefits_exitsservice.model.ExitBenefit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExitBenefitRepository extends JpaRepository<ExitBenefit, Long> {
    List<ExitBenefit> findByMemberId(Long memberId);
    Optional<ExitBenefit> findByIdAndMemberId(Long exitId, Long memberId);

}
