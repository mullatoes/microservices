package com.pension.benefits_exitsservice.service;

import com.pension.benefits_exitsservice.dto.ExitBenefitDTO;
import com.pension.benefits_exitsservice.exceptions.MemberNotFoundException;
import com.pension.benefits_exitsservice.model.ExitBenefit;

import java.util.List;

public interface ExitBenefitService {
    List<ExitBenefit> getExitBenefitsForMember(Long memberId);
    ExitBenefit createExitBenefit(Long memberId, ExitBenefitDTO exitBenefitDTO) throws MemberNotFoundException;
    void updateExitBenefit(Long memberId, Long exitBenefitId, ExitBenefitDTO exitBenefitDTO);
    void deleteExitBenefit(Long memberId, Long exitBenefitId);
}
