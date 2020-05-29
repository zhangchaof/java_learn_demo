package com.zhang.javase.learn.demo.design.factory.function;

import com.zhang.javase.learn.demo.design.factory.Fruit;

/**
 * @author clark
 * @Description:
 * @date 2020/5/29 15:43
 */
public class FactoryTest {
    public static void main(String[] args) {
        //不同工厂，要不同部件要指定特定工厂
        Factory factory = new BananaFactory();
        Fruit apple = factory.getFruit("appple");
        System.out.println("apple = " + apple);
    }
}
