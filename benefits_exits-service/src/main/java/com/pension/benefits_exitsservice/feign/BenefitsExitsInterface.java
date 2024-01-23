package com.pension.benefits_exitsservice.feign;


import com.pension.benefits_exitsservice.model.Contribution;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("CONTRIBUTION-SERVICE")
public interface BenefitsExitsInterface {

    @GetMapping("api/contributions/all/{memberId}")
    ResponseEntity<List<Contribution>> getContributionsByMember(@PathVariable Long memberId);
}
