package org.sopt.domain.member.dto.res;

import org.sopt.domain.member.entity.Gender;
import org.sopt.domain.member.entity.Member;

import java.time.LocalDate;

public record MemberResponse(
        Long id,
        String name,
        Gender gender,
        LocalDate birthDate,
        String email
) {
    public static MemberResponse from(Member m) {
        return new MemberResponse(
                m.getId(),
                m.getName(),
                m.getGender(),
                m.getBirthday(),
                m.getEmail()
        );
    }

    public static MemberResponse of(Long id, String name, Gender gender, LocalDate birthDate, String email) {
        return new MemberResponse(id, name, gender, birthDate, email);
    }
}
