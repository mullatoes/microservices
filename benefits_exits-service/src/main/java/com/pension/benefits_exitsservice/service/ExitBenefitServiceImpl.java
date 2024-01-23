package com.pension.benefits_exitsservice.service;

import com.pension.benefits_exitsservice.dto.ExitBenefitDTO;
import com.pension.benefits_exitsservice.exceptions.ExitBenefitNotFoundException;
import com.pension.benefits_exitsservice.exceptions.ExitBenefitNotPossibleException;
import com.pension.benefits_exitsservice.exceptions.MemberNotFoundException;
import com.pension.benefits_exitsservice.feign.BenefitsExitsInterface;
import com.pension.benefits_exitsservice.feign.MemberServiceInterfaceFeignClient;
import com.pension.benefits_exitsservice.model.Contribution;
import com.pension.benefits_exitsservice.model.ExitBenefit;
import com.pension.benefits_exitsservice.model.Member;
import com.pension.benefits_exitsservice.model.MemberShipStatus;
import com.pension.benefits_exitsservice.repository.ExitBenefitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExitBenefitServiceImpl implements ExitBenefitService {

    @Autowired
    private ExitBenefitRepository exitBenefitRepository;

    @Autowired
    private BenefitsExitsInterface benefitsExitsInterface;

    @Autowired
    private MemberServiceInterfaceFeignClient memberServiceInterface;

    @Override
    public List<ExitBenefit> getExitBenefitsForMember(Long memberId) {
        return exitBenefitRepository.findByMemberId(memberId);
    }

    @Override
    public ExitBenefit createExitBenefit(Long memberId, ExitBenefitDTO exitBenefitDTO) throws MemberNotFoundException {

        //call member-service and check the member status
        Member member = memberServiceInterface.getMemberById(memberId).getBody();
        if (member == null) {
            throw new MemberNotFoundException("Member not found with ID: " + memberId);
        }

        if (member.getMemberShipStatus() != MemberShipStatus.ACTIVE) {
            throw new ExitBenefitNotPossibleException("This member is already exited or withdrawn");
        }


        //call contribution-service and get all contributions for member
        List<Contribution> memberContributions = benefitsExitsInterface.getContributionsByMember(memberId).getBody();

        double totalBenefitsPayableWithoutInterest = calculateTotalBenefitsPayable(memberContributions);

        double declaredInterest = 1.12;

        double total = calculateTotalWithInterest(totalBenefitsPayableWithoutInterest, declaredInterest);

        ExitBenefit exitBenefit = buildExitBenefit(memberId, exitBenefitDTO, total);
        //call member service and update member status

        String response = memberServiceInterface.updateMemberStatus(memberId).getBody();
        System.out.println("Response: " + response);
        return exitBenefitRepository.save(exitBenefit);
    }

    private ExitBenefit buildExitBenefit(Long memberId, ExitBenefitDTO exitBenefitDTO, double total) {
        ExitBenefit exitBenefit = new ExitBenefit();
        exitBenefit.setMemberId(memberId);
        exitBenefit.setType(exitBenefitDTO.getType());
        exitBenefit.setAmount(total);
        return exitBenefit;
    }

    private double calculateTotalWithInterest(double totalBenefitsPayableWithoutInterest, double declaredInterest) {
        return totalBenefitsPayableWithoutInterest * declaredInterest;
    }

    private double calculateTotalBenefitsPayable(List<Contribution> contributions) {
        return contributions.stream()
                .mapToDouble(Contribution::getAmount)
                .sum();
    }

    @Override
    public void updateExitBenefit(Long memberId, Long exitBenefitId, ExitBenefitDTO exitBenefitDTO) {
        Optional<ExitBenefit> optionalExitBenefit = exitBenefitRepository.findByIdAndMemberId(exitBenefitId, memberId);
        if (optionalExitBenefit.isPresent()) {
            ExitBenefit exitBenefit = optionalExitBenefit.get();
            exitBenefit.setType(exitBenefitDTO.getType());
            exitBenefit.setAmount(10.0);
            exitBenefitRepository.save(exitBenefit);
        } else {
            throw new ExitBenefitNotFoundException("Exit/Benefit not found for member with ID: " + memberId +
                    " and exit/benefit ID: " + exitBenefitId);
        }
    }

    @Override
    public void deleteExitBenefit(Long memberId, Long exitBenefitId) {
        Optional<ExitBenefit> optionalExitBenefit = exitBenefitRepository.findByIdAndMemberId(exitBenefitId, memberId);
        if (optionalExitBenefit.isPresent()) {
            exitBenefitRepository.deleteById(exitBenefitId);
        } else {
            throw new ExitBenefitNotFoundException("Exit/Benefit not found for member with ID: " + memberId +
                    " and exit/benefit ID: " + exitBenefitId);
        }
    }
}
