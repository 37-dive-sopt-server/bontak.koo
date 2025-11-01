package org.sopt.domain.member.service;

import org.sopt.domain.member.dto.res.MemberIdRes;
import org.sopt.domain.member.dto.res.MemberRes;
import org.sopt.domain.member.dto.res.AllMembersRes;
import org.sopt.domain.member.dto.req.CreateMemberReq;

public interface MemberService {

    MemberIdRes join(CreateMemberReq createMemberReq);

    MemberRes findOne(Long memberId);

    AllMembersRes findAllMembers();

    MemberIdRes deleteMember(Long id);
}
