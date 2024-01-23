package com.pension.contributionsservice.feign;

import com.pension.contributionsservice.model.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("MEMBER-SERVICE")
public interface ContributionFeignInterface {

    @GetMapping("api/members/{memberId}")
    ResponseEntity<Member> getMemberById(@PathVariable Long memberId);
}
