package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class ThreadOfPlatform implements ConcurrencyExample {
    @Override
    public void execute(int lowerBound, int upperBound, int sleep) {
        long start = System.nanoTime();
        LongAdder adder = new LongAdder();
        Task task = new Task(adder, sleep);
        int[] numbers = IntStream.range(lowerBound, upperBound).toArray();

        List<Thread> threads = Arrays.stream(numbers).mapToObj(num ->
                Thread.ofPlatform()
                        .start(task.executeAndGet())
        ).toList();

        threads.parallelStream().forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        long end = System.nanoTime();
        System.out.println("ThreadOfPlatform " + adder.intValue() + " tasks in " + (end - start)/1000000 + "ms");
    }
}
