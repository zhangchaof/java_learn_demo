package com.zhang.init;

import org.springframework.boot.CommandLineRunner;

/**
 * @ClassName CommandLineRunnerTest
 * @Description: TODO
 * @Author clark
 * @Date 2021/2/2 14:18
 **/
public class CommandLineRunnerTest implements CommandLineRunner {
    /**
     * 用于指示bean包含在SpringApplication中时应运行的接口。可以在同一应用程序上下文中定义多个commandlinerunner bean，并且可以使用有序接口或@order注释对其进行排序。
     * 如果需要访问applicationArguments而不是原始字符串数组，请考虑使用applicationrunner。
     *
     */
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("CommandLineRunner的run方法");
    }
}
