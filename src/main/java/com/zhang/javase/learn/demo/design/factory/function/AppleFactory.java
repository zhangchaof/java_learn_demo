package com.zhang.javase.learn.demo.design.factory.function;

import com.zhang.javase.learn.demo.design.factory.Apple;
import com.zhang.javase.learn.demo.design.factory.Fruit;

/**
 * @author clark
 * @Description:
 * @date 2020/5/29 15:40
 */
public class AppleFactory implements Factory{

    @Override
    public Fruit getFruit(String name) {
        return new Apple();
    }
}
