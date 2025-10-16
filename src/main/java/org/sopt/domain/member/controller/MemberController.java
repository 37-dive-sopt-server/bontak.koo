package org.sopt.domain.member.controller;

import org.sopt.domain.member.entity.Member;
import org.sopt.domain.member.dto.req.CreateMemberReq;
import org.sopt.domain.member.service.MemberService;
import org.sopt.domain.member.service.MemberServiceImpl;

import java.util.List;
import java.util.Optional;

public class MemberController {

    private final MemberService memberService = new MemberServiceImpl();

    public Long createMember(CreateMemberReq createMemberReq) {

        return memberService.join(createMemberReq);
    }

    public Optional<Member> findMemberById(Long id) {
        return memberService.findOne(id);
    }

    public List<Member> getAllMembers() {
        return memberService.findAllMembers();
    }

    public boolean deleteMemberById(Long id) {
        return memberService.deleteMember(id);
    }}
