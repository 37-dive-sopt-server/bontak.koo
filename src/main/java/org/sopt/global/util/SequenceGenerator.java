package org.sopt.global.util;

import java.util.concurrent.atomic.AtomicLong;

public class SequenceGenerator {

    private static final AtomicLong sequence = new AtomicLong(1L);

    private SequenceGenerator() {
    }

    public static Long nextId() {
        return sequence.getAndIncrement();
    }
}
