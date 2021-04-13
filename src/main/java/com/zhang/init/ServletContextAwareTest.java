package com.zhang.init;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * @ClassName TestStarted
 * @Description: TestStarted
 * @Author clark
 * @Date 2021/2/2 14:13
 **/
@Component
public class ServletContextAwareTest implements ServletContextAware {
    /**
     * 在填充普通bean属性之后但在初始化之前调用
     * 类似于initializingbean的afterpropertiesset或自定义init方法的回调
     *
     */
    @Override
    public void setServletContext(ServletContext servletContext) {
        System.out.println("setServletContext方法");
    }
}
