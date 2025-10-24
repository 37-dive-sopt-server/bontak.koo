package org.sopt.domain.member.dto.req;

import org.sopt.domain.member.entity.Gender;

import java.time.LocalDate;

public record CreateMemberReq(
        String name,
        LocalDate birthday,
        String email,
        Gender gender
) {
    public static CreateMemberReq of(String name, String birthday, String email, Gender gender) {
        return new CreateMemberReq(name, LocalDate.parse(birthday), email, gender);
    }
}
