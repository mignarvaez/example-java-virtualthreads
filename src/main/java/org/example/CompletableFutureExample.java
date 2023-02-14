package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.LongAdder;

public class CompletableFutureExample implements ConcurrencyExample {
    @Override
    public void execute(int lowerBound, int upperBound, int sleep) {
        long start = System.nanoTime();
        LongAdder adder = new LongAdder();
        Task task = new Task(adder, sleep);

        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (int i = lowerBound; i < upperBound; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(task.executeAndGet());
            futures.add(future);
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();

        long end = System.nanoTime();

        System.out.println("CompletableFuture -> Completed " + adder.intValue() + " tasks in " + (end - start)/1000000 + "ms");
    }
}
