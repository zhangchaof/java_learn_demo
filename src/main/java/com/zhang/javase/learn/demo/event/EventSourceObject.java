package com.zhang.javase.learn.demo.event;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName EventSourceObject
 * @Description: TODO
 * @Author clark
 * @Date 2020/12/31 16:56
 **/
public class EventSourceObject {
    private String name;
    /**
     * 监听器容器
     */
    private Set<CusEventListener> listener;

    public EventSourceObject() {
        this.listener = new HashSet<>();
        this.name = "default name";
    }

    /**
     * 当事件发生时,通知注册在该事件源上的所有监听器做出相应的反应（调用回调方法）
     */
    protected void notifies() {
        CusEventListener cel;
        Iterator<CusEventListener> iterator = this.listener.iterator();
        while (iterator.hasNext()) {
            cel = iterator.next();
            cel.fireCusEvent(new CusEvent(this));
        }
    }

    /**
     * 给事件源注册监听器
     *
     * @param cel
     */
    public void addCusListener(CusEventListener cel) {
        this.listener.add(cel);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!this.name.equals(name)) {
            this.name = name;
            notifies();
        }
    }
}
