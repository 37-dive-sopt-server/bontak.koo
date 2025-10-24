package org.sopt.global.util;

import java.util.concurrent.atomic.AtomicLong;

public class SequenceGenerator {

    private static final AtomicLong sequence = new AtomicLong(1L);

    private SequenceGenerator() {
    }

    public static Long nextId() {
        return sequence.getAndIncrement();
    }

    // 파일 로드 시, 마지막 ID 값으로 초기화
    public static void setInitialValue(long lastId) {
        sequence.set(lastId + 1); // 다음 ID부터 시작하도록 +1
    }
}
