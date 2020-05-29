package com.zhang.javase.learn.demo.design.factory.simple;

import com.zhang.javase.learn.demo.design.factory.Apple;
import com.zhang.javase.learn.demo.design.factory.Banana;
import com.zhang.javase.learn.demo.design.factory.Fruit;

/**
 * @author clark
 * @Description:
 * @date 2020/5/29 15:19
 */
public class SimpleFactory {

    public Fruit getFruit(String name) {
        if ("apple".equals(name)) {
            return new Apple();
        } else if ("banana".equals(name)) {
            return new Banana();
        } else {
            System.out.println("无法生产");
            return null;
        }
    }

}
