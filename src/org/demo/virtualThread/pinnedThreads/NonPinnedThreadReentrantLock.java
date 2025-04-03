package org.demo.virtualThread.pinnedThreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class NonPinnedThreadReentrantLock {
    public static void main(String[] args) {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            int taskToExecute =  10;
            ReentrantLock lock = new ReentrantLock();
            do {
                executor.submit(() -> {
                    try {
                        lock.lock();
                        Thread.sleep(10000);
                        System.out.println("Time consuming task executed");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                    
                });
            } while ((taskToExecute -= 1) > 0);
        }
    }
}
