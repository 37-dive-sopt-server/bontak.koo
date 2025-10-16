package org.sopt.domain.member.repository.flush;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SmartFlushManager {

    // 별도의 백그라운드 스레드 생성
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final Runnable flushTask;
    private final long intervalSeconds;

    // 데이터 변경 여부 추적
    // volatile: 항상 메인 메모리에 반영하여, 가시성을 해결합니다
    // true: 해시맵과 파일의 데이터가 같음
    // false: 해시맵과 파일의 데이터가 다름
    private volatile boolean isDirty = false;

    public SmartFlushManager(Runnable flushTask, long intervalSeconds) {
        this.flushTask = flushTask;
        this.intervalSeconds = intervalSeconds;
        startScheduler();
    }

    private void startScheduler() {
        scheduler.scheduleAtFixedRate(() -> {
            if (isDirty) {
                flushTask.run();
                isDirty = false;
            }
        }, intervalSeconds, intervalSeconds, TimeUnit.SECONDS);

        Runtime.getRuntime().addShutdownHook(new Thread(this::flushOnExit));
    }

    public void markDirty() {
        isDirty = true;
    }

    // 프로그램 종료 시점 강제 Flush
    private void flushOnExit() {
        if (isDirty) {
            flushTask.run();
        }
        scheduler.shutdown();
    }
}
