package com.pension.memberservice.service;

import com.pension.memberservice.dto.MemberDTO;
import com.pension.memberservice.model.Member;
import com.pension.memberservice.model.MemberShipStatus;
import com.pension.memberservice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    @Override
    public Member createMember(MemberDTO memberDTO) {
        Member member = new Member();
        mapDtoToEntity(memberDTO, member);
        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(Long memberId, MemberDTO memberDTO) {
        Member existingMember = memberRepository.findById(memberId).orElse(null);
        if (existingMember != null) {
            mapDtoToEntity(memberDTO, existingMember);
            return memberRepository.save(existingMember);
        }
        return null;
    }

    @Override
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    @Override
    public String updateMemberStatus(Long memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        assert member != null;
        member.setMemberShipStatus(MemberShipStatus.WITHDRAWN);
        memberRepository.save(member);
        return "Status updated";
    }

    private void mapDtoToEntity(MemberDTO memberDTO, Member member) {
        member.setFirstName(memberDTO.getFirstName());
        member.setLastName(memberDTO.getLastName());
        member.setEmail(memberDTO.getEmail());
        member.setPhoneNumber(memberDTO.getPhoneNumber());
        member.setDateOfBirth(memberDTO.getDateOfBirth());
        member.setMemberShipStatus(MemberShipStatus.ACTIVE);
    }
}
