package org.sopt.domain.member.repository;

import org.sopt.domain.member.entity.Member;
import org.sopt.domain.member.repository.flush.SmartFlushManager;
import org.sopt.domain.member.repository.storage.FileStorage;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FileMemberRepositoryImpl implements MemberRepository {

    private final Map<Long, Member> store = new HashMap<>();
    private final FileStorage fileStorage;
    private final SmartFlushManager flushManager;

    public FileMemberRepositoryImpl(FileStorage fileStorage, SmartFlushManager flushManager) {
        this.fileStorage = fileStorage;
        this.flushManager = flushManager;

        // SmartFlushManager가 flush 시 실행할 작업 지정
        this.flushManager.setFlushTask(this::saveToFile);

        // 애플리케이션 시작 시 파일 로드
        loadFromFile();
    }

    @Override
    public Member save(Member member) {
        store.put(member.getId(), member);
        flushManager.markDirty(); // dirty 플래그 설정 (비동기 flush 대상)
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return store.values().stream()
                .filter(member -> member.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public boolean deleteById(Long id) {
        if (store.containsKey(id)) {
            store.remove(id);
            flushManager.markDirty();  // dirty 표시
            return true;
        }
        return false;
    }

    private synchronized void saveToFile() {
        fileStorage.write(store);
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        Object obj = fileStorage.read();
        if (obj instanceof Map<?, ?> map) {
            store.clear();
            store.putAll((Map<Long, Member>) map);
        }
    }
}
