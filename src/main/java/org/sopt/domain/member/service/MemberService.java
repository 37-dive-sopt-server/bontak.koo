package org.sopt.domain.member.service;

import org.sopt.domain.member.entity.Member;
import org.sopt.domain.member.dto.req.CreateMemberReq;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    Long join(CreateMemberReq createMemberReq);

    Optional<Member> findOne(Long memberId);

    List<Member> findAllMembers();

    boolean deleteMember(Long id);
}
