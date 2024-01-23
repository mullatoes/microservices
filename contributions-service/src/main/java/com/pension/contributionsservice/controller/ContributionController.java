package com.pension.contributionsservice.controller;

import com.pension.contributionsservice.dto.ContributionDTO;
import com.pension.contributionsservice.model.Contribution;
import com.pension.contributionsservice.service.ContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contributions")
public class ContributionController {
    @Autowired
    private ContributionService contributionService;

    @Autowired
    Environment environment;

    @GetMapping("/all/{memberId}")
    public ResponseEntity<List<Contribution>> getContributionsByMember(@PathVariable Long memberId) {
        List<Contribution> contributionsByMember = contributionService.getContributionsByMember(memberId);
        System.out.println(environment.getProperty("local.server.port"));
        return ResponseEntity.ok(contributionsByMember);
    }

    @PostMapping("/create/{memberId}")
    public ResponseEntity<Contribution> createContribution(
            @PathVariable Long memberId,
            @RequestBody ContributionDTO contributionDTO) {
        Contribution createdContribution = contributionService.createContribution(memberId, contributionDTO);
        System.out.println(environment.getProperty("local.server.port"));
        return ResponseEntity.ok(createdContribution);
    }

    @DeleteMapping("/{contributionId}")
    public void deleteContribution(@PathVariable Long contributionId) {
        contributionService.deleteContribution(contributionId);
    }

    @DeleteMapping("/{memberId}/contributions/{contributionId}")
    public void deleteContributionForMember(
            @PathVariable Long memberId,
            @PathVariable Long contributionId) {
        contributionService.deleteContributionForMember(memberId,contributionId);
    }

}
