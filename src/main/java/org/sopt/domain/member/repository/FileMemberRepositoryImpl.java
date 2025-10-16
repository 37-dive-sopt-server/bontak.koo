package org.sopt.domain.member.repository;

import org.sopt.domain.member.entity.Member;
import org.sopt.domain.member.repository.storage.FileStorage;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FileMemberRepositoryImpl implements MemberRepository {

    private final Map<Long, Member> store = new HashMap<>();
    private final FileStorage fileStorage;

    // 별도의 백그라운드 스레드 생성
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    // 데이터 변경 여부 추적
    // volatile: 항상 메인 메모리에 반영하여, 가시성을 해결합니다
    // true: 해시맵과 파일의 데이터가 같음
    // false: 해시맵과 파일의 데이터가 다름
    private volatile boolean isDirty = false;


    // 기존 데이터 로드
    public FileMemberRepositoryImpl() {
        this.fileStorage = new FileStorage("./members.txt");
        loadFromFile();
        startSmartFlush();
    }


    private void startSmartFlush() {
        scheduler.scheduleAtFixedRate(() -> {
            if (isDirty) { // 변경되었다면
                saveToFile();
                isDirty = false; // 다시 clean
            }
        }, 10, 10, TimeUnit.SECONDS);

        // 종료될때 flush
        Runtime.getRuntime().addShutdownHook(new Thread(this::flushOnExit));
    }


    @Override
    public Member save(Member member) {
        store.put(member.getId(), member);
        isDirty = true;  // dirty 표시
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
            isDirty = true;  // dirty 표시
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

    // 프로그램 종료 시점 강제 Flush
    private void flushOnExit() {
        if (isDirty) {
            saveToFile();
        }
        scheduler.shutdown();
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
        isDirty = false; // 초기 상태 clean
    }
}
