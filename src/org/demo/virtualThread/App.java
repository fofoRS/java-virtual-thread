package org.demo.virtualThread;

public class App {
    public static void main(String[] args) throws Exception {
        Runnable task = () -> {
            System.out.println("Hello from a virtual thread:" + Thread.currentThread().getName());
        };

        Thread.Builder virtualThreadBuilder = Thread.ofVirtual().name("greeting-thread-", 0);

        Thread thread1 = virtualThreadBuilder.start(task);
        Thread thread2 = virtualThreadBuilder.start(task);
        Thread thread3 = virtualThreadBuilder.start(task);

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Thread " + thread1.getName() + " has finished");
        System.out.println("Thread " + thread2.getName() + " has finished");
        System.out.println("Thread " + thread3.getName() + " has finished");
    }
}
