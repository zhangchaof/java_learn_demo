package com.zhang.com.zhang.test;

import org.junit.jupiter.api.Test;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Test
 * @Description: TODO
 * @Author clark
 * @Date 2021/1/11 16:15
 **/
public class TaskDecoratorTest {

    @Test
    public void testThreadLocal() {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        ExecutorService mainThreadPool = Executors.newFixedThreadPool(16);

        ThreadPoolTaskExecutor childThreadPool = new ThreadPoolTaskExecutor();
        childThreadPool.setCorePoolSize(2);
        childThreadPool.setMaxPoolSize(2);

        childThreadPool.setTaskDecorator(runnable -> {
            int v = threadLocal.get();
            System.out.println("装饰器中获取到主线程=" + Thread.currentThread().getName() + " 获取上下文=" + v);
            return () -> {
                try {
                    //重新copy传递给子线程
                    threadLocal.set(v);
                    runnable.run();
                } finally {
                    threadLocal.remove();
                }
            };
        });
        childThreadPool.initialize();

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            mainThreadPool.execute(() -> {
                //模拟在主线程设置上下文变量
                threadLocal.set(finalI);
                childThreadPool.execute(() -> System.out.println("子线程" + Thread.currentThread().getName() + " 获取上下文变量=" + threadLocal.get()));
                threadLocal.remove();
            });
        }
        try {
            childThreadPool.getThreadPoolExecutor().awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMDCThreadLocal() {
        ExecutorService mainThreadPool = Executors.newFixedThreadPool(16);

        ThreadPoolTaskExecutor childThreadPool = new ThreadPoolTaskExecutor();
        childThreadPool.setCorePoolSize(2);
        childThreadPool.setMaxPoolSize(2);

        childThreadPool.setTaskDecorator(new MdcTaskDecorator());
        childThreadPool.initialize();

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            mainThreadPool.execute(() -> {
                //模拟在主线程设置上下文变量

                Map<String, String> map = new HashMap<>();
                map.put("test", "test" + finalI);
                map.put("name", Thread.currentThread().getName());
                MDC.setContextMap(map);
                childThreadPool.execute(() -> System.out.println("子线程" + Thread.currentThread().getName() + " 获取上下文变量=" + MDC.getCopyOfContextMap()));
                MDC.clear();
            });
        }
        try {
            childThreadPool.getThreadPoolExecutor().awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class MdcTaskDecorator implements TaskDecorator {

        @Override
        public Runnable decorate(Runnable runnable) {
            RequestAttributes context = RequestContextHolder.currentRequestAttributes();  //1
            System.out.println("装饰器中获取到主线程=" + Thread.currentThread().getName() + " 获取上下文 context=" + context);
            Map<String, String> previous = MDC.getCopyOfContextMap();                      //2
            System.out.println("装饰器中获取到主线程=" + Thread.currentThread().getName() + " 获取上下文 previous=" + previous);
            SecurityContext securityContext = SecurityContextHolder.getContext();          //3
            System.out.println("装饰器中获取到主线程=" + Thread.currentThread().getName() + " 获取上下文 securityContext=" + securityContext);

            return () -> {
                try {
                    RequestContextHolder.setRequestAttributes(context);     //1
                    MDC.setContextMap(previous);                         //2
                    SecurityContextHolder.setContext(securityContext);   //3
                    runnable.run();
                } finally {
                    RequestContextHolder.resetRequestAttributes();        // 1
                    MDC.clear();                                        // 2
                    SecurityContextHolder.clearContext();                // 3
                }
            };
        }
    }
}
