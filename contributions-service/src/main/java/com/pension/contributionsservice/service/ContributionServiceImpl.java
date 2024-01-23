package com.pension.contributionsservice.service;

import com.pension.contributionsservice.dto.ContributionDTO;
import com.pension.contributionsservice.feign.ContributionFeignInterface;
import com.pension.contributionsservice.model.Contribution;
import com.pension.contributionsservice.model.Member;
import com.pension.contributionsservice.repository.ContributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContributionServiceImpl implements ContributionService {

    @Autowired
    private ContributionRepository contributionRepository;

    @Autowired
    private ContributionFeignInterface feignInterface;

    @Override
    public List<Contribution> getContributionsByMember(Long memberId) {
        //CALL MEMBER-SERVICE FOR CONFIRMATION
        Member memberExists = feignInterface.getMemberById(memberId).getBody();

        if (memberExists != null)
            return contributionRepository.findByMemberId(memberExists.getMemberId());

        return null;
    }

    @Override
    public Contribution createContribution(Long memberId, ContributionDTO contributionDTO) {
        //CALL MEMBER-SERVICE FOR CONFIRMATION
        Member memberExists = feignInterface.getMemberById(memberId).getBody();

        if (memberExists != null) {
            Contribution contribution = new Contribution();
            contribution.setMemberId(memberId);
            contribution.setAmount(contributionDTO.getAmount());
            contribution.setContributionDate(LocalDate.now());
            return contributionRepository.save(contribution);
        }

        return null;
    }

    @Override
    public void deleteContribution(Long contributionId) {
        contributionRepository.deleteById(contributionId);
    }

    @Override
    public void deleteContributionForMember(Long memberId, Long contributionId) {

        List<Contribution> contributionsByMemberId = contributionRepository.findByMemberId(memberId);
        for (Contribution contribution : contributionsByMemberId) {
            if (contribution.getContributionId().equals(contributionId)) {
                contributionRepository.deleteById(contributionId);
                break;
            }
        }
    }

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        System.out.println(now);
    }
}
