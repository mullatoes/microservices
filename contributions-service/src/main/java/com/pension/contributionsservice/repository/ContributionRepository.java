package com.pension.contributionsservice.repository;

import com.pension.contributionsservice.model.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContributionRepository extends JpaRepository<Contribution, Long> {
    List<Contribution> findByMemberId(Long memberId);
}
