package com.zhang.javase.learn.demo.design.factory.abstracts;

import com.zhang.javase.learn.demo.design.factory.Fruit;

/**
 * @author clark
 * @Description:
 * @date 2020/5/29 18:56
 */
public class FruitFactoryTest {
    public static void main(String[] args) {
        //工厂内有不同部件，要啥部件直接要
        FruitFactory fruitFactory = new FruitFactory();
        Fruit apple = fruitFactory.getApple();
        System.out.println("apple = " + apple);
    }
}
