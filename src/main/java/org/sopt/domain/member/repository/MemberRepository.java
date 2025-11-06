package org.sopt.domain.member.repository;

import org.sopt.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmailAndIsDeletedFalse(String email);
}
