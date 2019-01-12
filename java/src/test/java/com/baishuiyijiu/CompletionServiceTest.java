package com.baishuiyijiu;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author gujun@qiyi.com
 * @since 2019-01-09 15:29
 */
public class CompletionServiceTest {
    @Test
    public void test() {
        ExecutorService es = Executors.newFixedThreadPool(10);
        CompletionService<Integer> cs = new ExecutorCompletionService<>(es);

        // 模拟瞬间产生10个任务，且每个任务执行时间不一样
        for (int i = 0; i < 10; ++i) {
            cs.submit(() -> {
                System.out.println(Thread.currentThread().getName() + "开始执行！");
                Thread.sleep(Math.abs(new Random().nextInt(1000*10)));
                System.out.println(Thread.currentThread().getName() + "执行完毕！");
                return new Random().nextInt(1000);
            });
        }

        // 获取执行结果
        for (int i = 0; i < 10; ++i) {
            try {
                long start = System.currentTimeMillis();
                Future<Integer> result = cs.take(); // 返回完成的任务，如果没有完成的任务则阻塞
                long end = System.currentTimeMillis();
                System.out.println("Wait until task complete, time: " + (end-start));
                System.out.println(result.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 模拟瞬间产生10个任务，且每个任务执行时间不一样
        for (int i = 0; i < 10; ++i) {
            cs.submit(() -> {
                System.out.println(Thread.currentThread().getName() + "->开始执行！");
                Thread.sleep(Math.abs(new Random().nextInt(1000*10)));
                System.out.println(Thread.currentThread().getName() + "->执行完毕！");
                return new Random().nextInt(1000);
            });
        }

        // 获取执行结果
        for (int i = 0; i < 10; ++i) {
            try {
                long start = System.currentTimeMillis();
                Future<Integer> result = cs.take(); // 返回完成的任务，如果没有完成的任务则阻塞
                long end = System.currentTimeMillis();
                System.out.println("->Wait until task complete, time: " + (end-start));
                System.out.println(result.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        es.shutdown();
    }
}
