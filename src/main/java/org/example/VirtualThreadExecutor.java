package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class VirtualThreadExecutor implements ConcurrencyExample {
    @Override
    public void execute(int lowerBound, int upperBound, int sleep) {
        long start = System.nanoTime();
        LongAdder adder = new LongAdder();
        Task task = new Task(adder, sleep);

        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(lowerBound, upperBound)
                    .forEach(number -> executorService.submit(task.executeAndGet()));
        }
        long end = System.nanoTime();

        System.out.println("VirtualThreadPerTaskExecutor -> Completed " + adder.intValue() + " tasks in " + (end - start)/1000000 + "ms");
    }
}
