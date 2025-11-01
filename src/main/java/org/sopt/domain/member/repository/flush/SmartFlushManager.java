package org.sopt.domain.member.repository.flush;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class SmartFlushManager {

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private Runnable flushTask;

    private static final long INTERVAL_SECONDS = 10;
    private volatile boolean isDirty = false;

    public void setFlushTask(Runnable flushTask) {
        this.flushTask = flushTask;
        startScheduler();
    }

    @PostConstruct
    private void startScheduler() {
        scheduler.scheduleAtFixedRate(() -> {
            if (isDirty && flushTask != null) {
                flushTask.run();
                isDirty = false;
            }
        }, INTERVAL_SECONDS, INTERVAL_SECONDS, TimeUnit.SECONDS);

        Runtime.getRuntime().addShutdownHook(new Thread(this::flushOnExit));
    }

    public void markDirty() {
        isDirty = true;
    }

    @PreDestroy
    private void flushOnExit() {
        if (isDirty && flushTask != null) {
            flushTask.run();
        }
        scheduler.shutdown();
    }
}
