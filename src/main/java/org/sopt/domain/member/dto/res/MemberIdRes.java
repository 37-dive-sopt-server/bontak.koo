package org.sopt.domain.member.dto.res;


public record MemberIdRes(Long userId) {
    public static MemberIdRes from(Long userId) {
        return new MemberIdRes(userId);
    }
}
