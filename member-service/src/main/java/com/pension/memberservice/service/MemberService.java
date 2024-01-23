package com.pension.memberservice.service;

import com.pension.memberservice.dto.MemberDTO;
import com.pension.memberservice.model.Member;

import java.util.List;

public interface MemberService {
    List<Member> getAllMembers();
    Member getMemberById(Long memberId);
    Member createMember(MemberDTO memberDTO);
    Member updateMember(Long memberId, MemberDTO memberDTO);
    void deleteMember(Long memberId);
    String updateMemberStatus(Long memberId);
}
