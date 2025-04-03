# Virtual Thread

In genral virtual thread is aims to improve the throughput allowing the application to execute more concurrent work or accept more request under a thread-per-request approach. Virtual threads still uses platform OS threads as carriers. Once the virtual thread gets blocked, platform thread will become free to accept a new vritual threads. We have to keep in mind that with virtual threads we no longer have a 1:1 relationship between virtual and platform threads.

## Considerations

- Virtual Threads are not to perform tasks faster, it is all about more throughput.
- Don't chare havy and resource consuming objects via LocalThread and they will result
- Avoid having long lived blocking virtual threads with synchronization using ``synchronozed`` this will make the underlined platform OS thread get block and will degrate the main benifict of virtual threads.
- Don't  pool virtual threads, as they are cheap to create and execute unlike plataform thread (traditional threads)

## Suggestions

- If you need to share an object, use ScopedValues which doesn't present the issue of ThreadLocal variable in the Virtual Threads context
- You can still create virtual threads by using the Executors class, call newVirtualThreadPerTaskExecutor().
- For synchronization use other locking method such as the usage of the ReentrantLock which doesn't block the underlined platform thread allowing other virtual thread make use of the platform thread when current virtual thread blocks.
- To limit the concurrent work please don't pool virtual threads, instead use other mechanisms such as the Semaphore. Semantically it's te same as pooling since it queue the virtual threads until the Semaphore is released.

### How to debug and find Pinned Virtual Thread?

There are multiple ways to identify pinned thread the most common options are via JFR and jmd. For simplicity I'll leave a simple JDK option that trace pinned threads easily via command line.

`java -Djdk.tracePinnedThreads=full <java_program>`

you can use **short** if you don't want to print the whole stack trace.
