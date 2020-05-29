package com.zhang.javase.learn.demo.design.factory.function;

import com.zhang.javase.learn.demo.design.factory.Fruit;

/**
 * @author clark
 * @Description:
 * @date 2020/5/29 15:39
 */
public interface Factory {
    Fruit getFruit(String name);
}
