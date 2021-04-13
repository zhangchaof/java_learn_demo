package com.zhang.init;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @ClassName PostcontructTest
 * @Description: TODO
 * @Author clark
 * @Date 2021/2/2 14:17
 **/
@Component
public class PostcontructTest {
    //静态代码块会在依赖注入后自动执行,并优先执行
    static{
        System.out.println("---static--");
    }
    /**
     *  @Postcontruct’在依赖注入完成后自动调用
     */
    @PostConstruct
    public static void test(){
        System.out.println("@Postcontruct’在依赖注入完成后自动调用");
    }
}
