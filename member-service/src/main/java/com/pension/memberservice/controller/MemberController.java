package com.pension.memberservice.controller;

import com.pension.memberservice.dto.MemberDTO;
import com.pension.memberservice.model.Member;
import com.pension.memberservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    Environment environment;

    @GetMapping("/all")
    public ResponseEntity<List<Member>> getAllMembers() {

        List<Member> members = memberService.getAllMembers();

        System.out.println(environment.getProperty("local.server.port"));

        return ResponseEntity.ok(members);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long memberId) {
        System.out.println(environment.getProperty("local.server.port"));
        Member member = memberService.getMemberById(memberId);
        return ResponseEntity.ok(member);
    }

    @PostMapping("/create")
    public ResponseEntity<Member> createMember(@RequestBody MemberDTO memberDTO) {
        Member createdMember = memberService.createMember(memberDTO);
        if (createdMember != null) {
            return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<Member> updateMember(@PathVariable Long memberId, @RequestBody MemberDTO memberDTO) {
        Member updateMember = memberService.updateMember(memberId, memberDTO);

        if (updateMember != null) {
            return ResponseEntity.ok(updateMember);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{memberId}")
    public void deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
    }

    @GetMapping("/status/update/{memberId}")
    public ResponseEntity<String> updateMemberStatus(
            @PathVariable Long memberId
    ) {
        String status = memberService.updateMemberStatus(memberId);
        return ResponseEntity.ok(status);
    }

}
