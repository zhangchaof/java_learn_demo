package com.zhang.javase.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author clark
 * @Description:
 * @date 2020/5/27 17:59
 */
public class ListTest {
    public static void main(String[] args) {
        toArrayTest();
    }

    /**
     * 测试ArrayList中
     * Object[] toArray()
     * 与<T> T[] toArray(T[] a)
     *
     * 使用 toArray 带参方法，数组空间大小的 length，
     * 1） 等于 0，动态创建与 size 相同的数组，性能最好。
     * 2） 大于 0 但小于 size，重新创建大小等于 size 的数组，增加 GC 负担。
     * 3） 等于 size，在高并发情况下，数组创建完成之后，size 正在变大的情况下，负面影响与 2 相同。
     * 4） 大于 size，空间浪费，且在 size 处插入 null 值，存在 NPE 隐患。
     */
    public static void toArrayTest() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        Integer[] integers = list.toArray(new Integer[0]);
        list.add(3);
        System.out.println("integers = " + integers.length);

    }
}
