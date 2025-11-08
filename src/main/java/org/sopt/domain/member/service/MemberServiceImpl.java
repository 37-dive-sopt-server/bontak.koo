package org.sopt.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.domain.member.dto.req.CreateMemberReq;
import org.sopt.domain.member.dto.res.AllMembersRes;
import org.sopt.domain.member.dto.res.MemberIdRes;
import org.sopt.domain.member.dto.res.MemberRes;
import org.sopt.domain.member.entity.Member;
import org.sopt.domain.member.repository.MemberRepository;
import org.sopt.domain.member.validator.MemberValidator;
import org.sopt.global.api.ErrorCode;
import org.sopt.global.api.handler.MemberException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;

    @Override
    @Transactional
    public MemberIdRes join(CreateMemberReq createMemberReq) {

        if (memberRepository.existsByEmailAndIsDeletedFalse(createMemberReq.email())) {
            throw new MemberException(ErrorCode.DUPLICATE_EMAIL);
        }

        Member member = memberValidator.createValidatedMember(createMemberReq);

        memberRepository.save(member);

        return MemberIdRes.from(member.getId());
    }

    @Override
    public MemberRes findOne(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));
        return MemberRes.from(member);
    }


    @Override
    public AllMembersRes findAllMembers() {
        List<Member> members = memberRepository.findAll();
        return AllMembersRes.from(members);
    }

    @Override
    @Transactional
    public MemberIdRes deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));

        memberRepository.delete(member);

        return MemberIdRes.from(id);
    }
}
