package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.LongAdder;

public class Sequential implements ConcurrencyExample {
    @Override
    public void execute(int lowerBound, int upperBound, int sleep) {
        long start = System.nanoTime();
        LongAdder adder = new LongAdder();

        for (int i = lowerBound; i < upperBound; i++) {
            try {
                Thread.sleep(sleep);
                adder.increment();
            } catch (InterruptedException e) {
                Thread.interrupted();
            }
        }
        long end = System.nanoTime();

        System.out.println("Sequential -> Completed " + adder.intValue() + " tasks in " + (end - start)/1000000 + "ms");
    }
}
