package org.sopt.domain.member.dto.res;

import org.sopt.domain.member.entity.Gender;
import org.sopt.domain.member.entity.Member;

import java.time.LocalDate;

public record MemberRes(
        Long userId,
        String name,
        LocalDate birthday,
        String email,
        Gender gender
) {
    public static MemberRes from(Member member) {
        return new MemberRes(
                member.getId(),
                member.getName(),
                member.getBirthday(),
                member.getEmail(),
                member.getGender()
        );
    }
}
