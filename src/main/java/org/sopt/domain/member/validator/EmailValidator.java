package org.sopt.domain.member.validator;

import org.sopt.domain.member.repository.MemoryMemberRepository;
import org.sopt.global.api.ErrorCode;
import org.sopt.global.api.handler.MemberException;

public class EmailValidator {
    private final MemoryMemberRepository memberRepository;

    // serviceImpl의 저장소를 주입받아 사용
    public EmailValidator(MemoryMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void validateDuplicateEmail(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            // 중복이면 터짐
            throw new MemberException(ErrorCode.DUPLICATE_EMAIL);
        }
    }
}
