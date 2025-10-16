package org.sopt.domain.member.repository;

import org.sopt.domain.member.entity.Member;
import org.sopt.domain.member.repository.flush.SmartFlushManager;
import org.sopt.domain.member.repository.storage.FileStorage;

import java.util.*;

public class FileMemberRepositoryImpl implements MemberRepository {

    private final Map<Long, Member> store = new HashMap<>();
    private final FileStorage fileStorage;
    private final SmartFlushManager flushManager;

    // 기존 데이터 로드
    public FileMemberRepositoryImpl(FileStorage fileStorage, SmartFlushManager flushManager) {
        this.fileStorage = fileStorage;
        this.flushManager = flushManager;
        this.flushManager.setFlushTask(this::saveToFile);
        loadFromFile();
    }


    @Override
    public Member save(Member member) {
        store.put(member.getId(), member);
        flushManager.markDirty(); // dirty로 표시
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

    // 파일로 데이터 저장
    // dirty일때만 파일에 저장
    // 하나의 스레드만 플러시할 수 있습니다
    private synchronized void saveToFile() {
        fileStorage.write(store);
    }


    // 파일에서 데이터 로드
    // 안전하지 않은 형변환 컴파일러 경고를 무시합니다
    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        Object obj = fileStorage.read();
        if (obj instanceof Map<?, ?> map) {
            store.clear();
            store.putAll((Map<Long, Member>) map);
        }
    }
}
