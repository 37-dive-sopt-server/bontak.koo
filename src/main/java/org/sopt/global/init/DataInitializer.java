package org.sopt.global.init;

import org.sopt.domain.member.repository.FileMemberRepositoryImpl;
import org.sopt.global.util.SequenceGenerator;

public class DataInitializer {

    private final FileMemberRepositoryImpl memberRepository;

    public DataInitializer(FileMemberRepositoryImpl memberRepository) {
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
