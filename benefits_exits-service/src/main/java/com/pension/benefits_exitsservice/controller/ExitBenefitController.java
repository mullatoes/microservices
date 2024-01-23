package com.pension.benefits_exitsservice.controller;

import com.pension.benefits_exitsservice.dto.ExitBenefitDTO;
import com.pension.benefits_exitsservice.exceptions.ExitBenefitNotPossibleException;
import com.pension.benefits_exitsservice.exceptions.MemberNotFoundException;
import com.pension.benefits_exitsservice.model.ExitBenefit;
import com.pension.benefits_exitsservice.service.ExitBenefitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exits")
public class ExitBenefitController {

    @Autowired
    private ExitBenefitService exitBenefitService;

    @GetMapping("/{memberId}")
    public ResponseEntity<List<ExitBenefit>> getExitBenefitsForMember(@PathVariable Long memberId) {
        List<ExitBenefit> exitBenefits = exitBenefitService.getExitBenefitsForMember(memberId);
        return ResponseEntity.ok(exitBenefits);
    }

    @PostMapping("/{memberId}")
    public ResponseEntity<ExitBenefit> createExitBenefit(
            @PathVariable Long memberId,
            @RequestBody ExitBenefitDTO exitBenefitDTO) {
        try {
            ExitBenefit createdExitBenefit = exitBenefitService.createExitBenefit(memberId, exitBenefitDTO);
            return ResponseEntity.ok(createdExitBenefit);
        } catch (ExitBenefitNotPossibleException | MemberNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
