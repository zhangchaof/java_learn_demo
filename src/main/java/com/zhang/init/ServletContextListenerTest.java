package com.zhang.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @ClassName ServletContextListenerTest
 * @Description: TODO
 * @Author clark
 * @Date 2021/2/2 14:15
 **/
public class ServletContextListenerTest implements ServletContextListener {
    /**
     * 在初始化Web应用程序中的任何过滤器或servlet之前，将通知所有servletContextListener上下文初始化。
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //ServletContext servletContext = sce.getServletContext();
        System.out.println("执行contextInitialized方法");
    }
}
