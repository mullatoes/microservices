package com.pension.benefits_exitsservice.feign;

import com.pension.benefits_exitsservice.model.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("MEMBER-SERVICE")
public interface MemberServiceInterfaceFeignClient {

    @GetMapping("api/members/status/update/{memberId}")
    ResponseEntity<String> updateMemberStatus(
            @PathVariable Long memberId
    );

    @GetMapping("api/members/{memberId}")
    ResponseEntity<Member> getMemberById(@PathVariable Long memberId);
}
