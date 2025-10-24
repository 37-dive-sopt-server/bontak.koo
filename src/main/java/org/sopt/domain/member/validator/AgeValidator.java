package org.sopt.domain.member.validator;

import org.sopt.global.api.ErrorCode;
import org.sopt.global.api.handler.MemberException;

import java.time.LocalDate;
import java.time.Period;

public class AgeValidator {
    private static final int MINIMUM_AGE = 20;

    public static void validateAge(LocalDate birthday) {
        int age = Period.between(birthday, LocalDate.now()).getYears();
        if (age < MINIMUM_AGE) {
            throw new MemberException(ErrorCode.MEMBER_UNDERAGE);
        }
    }
}
