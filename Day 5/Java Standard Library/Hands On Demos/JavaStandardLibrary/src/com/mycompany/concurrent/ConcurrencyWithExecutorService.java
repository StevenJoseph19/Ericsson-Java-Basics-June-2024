package com.mycompany.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrencyWithExecutorService {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 5; i++) {
            Runnable task = () -> {
                System.out.println("Thread executing task.");
            };
            executor.submit(task);
        }

        executor.shutdown();
    }
}
