package com.zhang.init;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * @ClassName ApplicationRunnerTest
 * @Description: TODO
 * @Author clark
 * @Date 2021/2/2 14:18
 **/
public class ApplicationRunnerTest implements ApplicationRunner {
    /**
     * 用于指示bean包含在SpringApplication中时应运行的接口。可以定义多个applicationrunner bean
     * 在同一应用程序上下文中，可以使用有序接口或@order注释对其进行排序。
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunner的run方法");
    }
}
