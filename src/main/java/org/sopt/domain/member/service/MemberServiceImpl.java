package org.sopt.domain.member.service;

import org.sopt.domain.member.dto.req.CreateMemberReq;
import org.sopt.domain.member.dto.res.MemberIdRes;
import org.sopt.domain.member.dto.res.MemberRes;
import org.sopt.domain.member.dto.res.AllMembersRes;
import org.sopt.domain.member.entity.Member;
import org.sopt.domain.member.repository.MemberRepository;
import org.sopt.domain.member.validator.UserValidator;
import org.sopt.global.api.ErrorCode;
import org.sopt.global.api.handler.MemberException;
import org.sopt.global.util.SequenceGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final UserValidator userValidator;

    public MemberServiceImpl(MemberRepository memberRepository, UserValidator userValidator) {
        this.memberRepository = memberRepository;
        this.userValidator = userValidator;
    }

    @Override
    public MemberIdRes join(CreateMemberReq createMemberReq) {

        userValidator.validateAge(createMemberReq.birthday());
        userValidator.validateDuplicateEmail(createMemberReq.email());

        Long id = SequenceGenerator.nextId();
        Member member = Member.of(
                id,
                createMemberReq.name(),
                createMemberReq.birthday(),
                createMemberReq.email(),
                createMemberReq.gender()
        );

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
    public MemberIdRes deleteMember(Long id) {
        boolean deleted = memberRepository.deleteById(id);
        if (!deleted) {
            throw new MemberException(ErrorCode.MEMBER_NOT_FOUND);
        }
        return MemberIdRes.from(id);
    }
}
