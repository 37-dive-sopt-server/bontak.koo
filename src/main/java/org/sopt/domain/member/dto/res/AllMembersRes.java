package org.sopt.domain.member.dto.res;

import org.sopt.domain.member.entity.Member;
import java.util.List;
import java.util.stream.Collectors;

public record AllMembersRes(List<MemberRes> members) {

    public static AllMembersRes from(List<Member> memberList) {
        List<MemberRes> memberDtos = memberList.stream()
                .map(MemberRes::from)
                .toList();

        return new AllMembersRes(memberDtos);
    }
}
