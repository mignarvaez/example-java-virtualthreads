package org.example;

import java.util.concurrent.atomic.LongAdder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {

        Sequential sequential = new Sequential();
        sequential.execute(1, 1000, 10);

        CompletableFutureExample completableFutureExample = new CompletableFutureExample();
        completableFutureExample.execute(1, 1000, 10);

        ThreadOfPlatform threadOfPlatform = new ThreadOfPlatform();
        threadOfPlatform.execute(1, 1000, 10);

        CachedThread cachedThread = new CachedThread();
        cachedThread.execute(1,1000, 10);

        ThreadOfVirtual threadOfVirtual = new ThreadOfVirtual();
        threadOfVirtual.execute(1, 1000, 10);

        VirtualThreadExecutor virtualThreadExecutor = new VirtualThreadExecutor();
        virtualThreadExecutor.execute(1, 1000, 10);

    }
}
