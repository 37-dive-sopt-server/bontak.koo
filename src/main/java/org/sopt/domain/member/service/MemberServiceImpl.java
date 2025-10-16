package org.sopt.domain.member.service;

import org.sopt.domain.member.dto.req.CreateMemberReq;
import org.sopt.domain.member.entity.Member;
import org.sopt.domain.member.repository.MemberRepository;
import org.sopt.domain.member.validator.AgeValidator;
import org.sopt.domain.member.validator.EmailValidator;
import org.sopt.global.util.SequenceGenerator;

import java.util.List;
import java.util.Optional;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final EmailValidator emailValidator;
    private final AgeValidator ageValidator;


    public MemberServiceImpl(MemberRepository memberRepository, EmailValidator emailValidator, AgeValidator ageValidator) {
        this.memberRepository = memberRepository;
        this.emailValidator = emailValidator;
        this.ageValidator = ageValidator;
    }


    @Override
    public Long join(CreateMemberReq createMemberReq) {

        // 나이 검증
        AgeValidator.validateAge(createMemberReq.birthday());

        // 이메일 중복 검증
        emailValidator.validateDuplicateEmail(createMemberReq.email());

        Long id = SequenceGenerator.nextId();
        Member member = Member.of(
                id,
                createMemberReq.name(),
                createMemberReq.birthday(),
                createMemberReq.email(),
                createMemberReq.gender()
        );

        memberRepository.save(member);

        return member.getId();
    }

    @Override
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public boolean deleteMember(Long id) {
        return memberRepository.deleteById(id);
    }
}
