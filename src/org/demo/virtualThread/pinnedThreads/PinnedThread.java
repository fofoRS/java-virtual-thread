package org.demo.virtualThread.pinnedThreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PinnedThread {
    public static void main(String[] args) {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            int taskToExecute =  10;
            do {
                executor.submit(() -> {
                    synchronized (PinnedThread.class) {
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Time consuming task executed");
                    }
                });
            } while ((taskToExecute -= 1) > 0);
        }
    }
}
