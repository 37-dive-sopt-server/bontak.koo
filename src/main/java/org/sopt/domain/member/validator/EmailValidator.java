package org.sopt.domain.member.validator;

import org.sopt.domain.member.repository.FileMemberRepositoryImpl;
import org.sopt.global.api.ErrorCode;
import org.sopt.global.api.handler.MemberException;

public class EmailValidator {
    private final FileMemberRepositoryImpl memberRepository;

    // serviceImpl의 저장소를 주입받아 사용
    public EmailValidator(FileMemberRepositoryImpl memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void validateDuplicateEmail(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            // 중복이면 터짐
            throw new MemberException(ErrorCode.DUPLICATE_EMAIL);
        }
    }
}
