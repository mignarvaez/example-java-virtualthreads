package org.example;

import java.util.concurrent.atomic.LongAdder;

public class Task {

    public final LongAdder adder;

    public int sleep;

    public Task(LongAdder adder, int sleep) {
        this.adder = adder;
        this.sleep = sleep;
    }

    public LongAdder getAdder() {
        return adder;
    }

    public int getSleep() {
        return sleep;
    }

    public Runnable executeAndGet() {
        return () -> {
            try {
                Thread.sleep(sleep);
                adder.increment();
            } catch (InterruptedException e) {
                Thread.interrupted();
            }
        };
    }
}
