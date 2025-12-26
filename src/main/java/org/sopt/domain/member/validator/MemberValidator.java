package org.sopt.domain.member.validator;

import org.sopt.domain.member.dto.req.CreateMemberReq;
import org.sopt.domain.member.entity.Member;
import org.sopt.global.api.ErrorCode;
import org.sopt.global.api.handler.MemberException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class MemberValidator {

    public Member createValidatedMember(CreateMemberReq req) {
        validateAge(req.birthday());

        return Member.of(
                req.name(),
                req.birthday(),
                req.email(),
                req.gender(),
                req.password()
        );
    }

    private void validateAge(LocalDate birthday) {
        int age = Period.between(birthday, LocalDate.now()).getYears();
        if (age < 20) {
            throw new MemberException(ErrorCode.MEMBER_UNDERAGE);
        }
    }
}
