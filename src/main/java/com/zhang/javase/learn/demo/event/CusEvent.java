package com.zhang.javase.learn.demo.event;

import java.util.EventObject;

/**
 * @ClassName CusEvent
 * @Description: 事件类, 用于封装事件源及一些与事件相关的参数.
 * @Author clark
 * @Date 2020/12/31 16:50
 **/
public class CusEvent extends EventObject {

    private Object source;

    public CusEvent(Object source) {
        super(source);
        this.source = source;
    }

    @Override
    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}
