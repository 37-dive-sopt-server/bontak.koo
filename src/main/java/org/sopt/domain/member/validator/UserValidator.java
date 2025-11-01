package org.sopt.domain.member.validator;

import org.sopt.domain.member.repository.MemberRepository;
import org.sopt.global.api.ErrorCode;
import org.sopt.global.api.handler.MemberException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component // ✅ 스프링 빈 등록 (Service 단에서 주입 가능)
public class UserValidator {

    private static final int MINIMUM_AGE = 20;
    private final MemberRepository memberRepository;

    // ✅ 생성자 주입 (스프링 자동 DI)
    public UserValidator(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 나이 검증 - 생일로 계산하여 20세 미만이면 예외 발생
     */
    public void validateAge(LocalDate birthday) {
        int age = Period.between(birthday, LocalDate.now()).getYears();
        if (age < MINIMUM_AGE) {
            throw new MemberException(ErrorCode.MEMBER_UNDERAGE);
        }
    }

    /**
     * 이메일 중복 검증 - 이미 존재하는 이메일이면 예외 발생
     */
    public void validateDuplicateEmail(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new MemberException(ErrorCode.DUPLICATE_EMAIL);
        }
    }
}
