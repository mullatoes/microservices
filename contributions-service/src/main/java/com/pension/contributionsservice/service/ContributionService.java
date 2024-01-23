package com.pension.contributionsservice.service;

import com.pension.contributionsservice.dto.ContributionDTO;
import com.pension.contributionsservice.model.Contribution;

import java.util.List;

public interface ContributionService {
    List<Contribution> getContributionsByMember(Long memberId);
    Contribution createContribution(Long memberId, ContributionDTO contributionDTO);
    void deleteContribution(Long contributionId);

    void deleteContributionForMember(Long memberId, Long contributionId);
}
