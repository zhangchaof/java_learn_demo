package com.zhang.javase.learn.demo.event;

import java.util.EventListener;

/**
 * @ClassName CusEventListener
 * @Description: 事件监听器，实现 java.util.EventListener 接口。定义回调方法，将你想要做的事放到这个方法下,因为事件源发生相应的事件时会调用这个方法。
 * @Author clark
 * @Date 2020/12/31 16:53
 **/
public class CusEventListener implements EventListener {
    /**
     * @param event 事件发生后的回调方法
     */
    public void fireCusEvent(CusEvent event) {
        EventSourceObject eObject = (EventSourceObject) event.getSource();
        System.out.println("My name has been changed!");
        System.out.println("I got a new name,named" + eObject.getName());
    }
}
