package com.zhang.threads.differentThreadLocalCompare.threadLocal;

/**
 * @ClassName Main
 * @Description: TODO
 * @Author clark
 * @Date 2021/4/29 17:08
 **/
public class Main {
    public static void main(String[] args) {
        BusinessService businessService = new BusinessService();
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        //模拟一个普通的同步web请求
        new Thread(() -> {
            // 模拟用户身份拦截器
            loginInterceptor.userInterceptor();
            System.out.println(Thread.currentThread().getName() + ":" + ThreadLocalHolder.getUser());
            // 拦截器通过后 同步处理业务
            businessService.doBusiness();
            // 拦截器通过后 异步处理业务
            businessService.doBusinessAsync();
        }).start();

    }
}
