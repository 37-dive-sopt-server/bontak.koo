package org.sopt.global.init;

import org.sopt.domain.member.repository.MemberRepository;
import org.sopt.global.util.SequenceGenerator;

public class DataInitializer {

    private final MemberRepository memberRepository;

    public DataInitializer(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void initializeSequence() {
        long maxId = memberRepository.findAll().stream()
                .mapToLong(member -> member.getId())
                .max()
                .orElse(0L);

        SequenceGenerator.setInitialValue(maxId);
    }
}
