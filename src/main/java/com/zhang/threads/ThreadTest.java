package com.zhang.threads;


import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ThreadTest
 * @Description: TODO
 * @Author clark
 * @Date 2021/2/1 10:43
 **/
public class ThreadTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime.minusMinutes(24*60*14));
        System.out.println("task = " + task);
    }
}

/**
 * 1.使用Callable+Future获取执行结果
 */
class Task implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }
}
