package com.zhang.javase.learn.demo.event;

/**
 * @ClassName MainTest
 * @Description: TODO
 * @Author clark
 * @Date 2020/12/31 17:04
 **/
public class MainTest {
    public static void main(String[] args) {
        EventSourceObject object = new EventSourceObject();
        //注册监听器
        object.addCusListener(new CusEventListener() {
            @Override
            public void fireCusEvent(CusEvent e) {
                super.fireCusEvent(e);
            }
        });
        //触发事件
        object.setName("测试");
    }
}
