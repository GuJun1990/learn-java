package com.baishuiyijiu.threadpool;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class DemoThreadPool {

    private static void shutdownExecutorService() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final AtomicInteger count = new AtomicInteger(0);
        for (int i = 0; i < 60; ++i) {
            executorService.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("hi~\t" + count.incrementAndGet());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        System.out.println("done~"); // 主线程退出了，但是线程池必须等到所有任务执行完毕之后才会关闭
    }

    private static void shutdownNowExecutorService() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final AtomicInteger count = new AtomicInteger(0);
        for (int i = 0; i < 60; ++i) {
            executorService.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("hi~\t" + count.incrementAndGet());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdownNow();
        System.out.println("done~"); // 不等线程池执行完任务退出，执行的任务抛InterruptedException。
    }

    private static void awaitTerminationExecutorService() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final AtomicInteger count = new AtomicInteger(0);
        for (int i = 0; i < 60; ++i) {
            executorService.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("hi~\t" + count.incrementAndGet());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown(); // 发送关闭线程池信号。
        try {executorService.awaitTermination(3600, TimeUnit.SECONDS);} catch (InterruptedException e) {e.printStackTrace();} // 等待线程池关闭。
        System.out.println("done~");
    }

    private static void countDownLatchExecutorService() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final int MAX_TASK_NUM = 60;
        final CountDownLatch latch = new CountDownLatch(MAX_TASK_NUM);
        final AtomicInteger count = new AtomicInteger(0);
        for (int i = 0; i < MAX_TASK_NUM; ++i) {
            executorService.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("hi~\t" + count.incrementAndGet());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }
        try {latch.await();} catch (InterruptedException e) {e.printStackTrace();}
        executorService.shutdown();
        System.out.println("done~");
    }


    public static void main(String[] args) {
//        shutdownExecutorService();
//        shutdownNowExecutorService();
//        awaitTerminationExecutorService();
        countDownLatchExecutorService();
    }

}
