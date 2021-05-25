package com.zhang.javase;

/**
 * @ClassName FalseSharingTest
 * @Description: https://blog.csdn.net/qq_28119741/article/details/102815659
 * 伪共享：当多线程修改互相独立的变量时，如果这些变量共享同一个缓存行，就会无意中影响彼此的性能，这就是伪共享。
 * <p>
 * 默认使用这个注解是无效的，需要在JVM启动参数加上-XX:-RestrictContended才会生效,再次运行程序发现时间是718ms。注意，以上三种方式中的前两种是通过加字段的形式实现的（上面go代码里的实现也是这样的），加的字段又没有地方使用，可能会被jvm优化掉，所以建议使用第三种方式。
 * @Author clark
 * @Date 2021/4/29 19:27
 *
 *  总结：
 * （1）CPU具有多级缓存，越接近CPU的缓存越小也越快
 *
 * （2）CPU缓存中的数据是以缓存行为单位处理的；
 *
 * （3）CPU缓存行能带来免费加载数据的好处，所以处理数据性能非常高
 *
 * （4）CPU缓存行也带来了弊端，多线程处理不相干的变量时会相互影响，也就是伪共享
 *
 * （5）避免伪共享的主要思路就是让不相干的变量不要出现在同一个缓存行中；
 *
 * 1是每两个变量之间加上7个long类型；2是创建自己的long类型，而不是用原生的；3是使用java8的注解
 *
 **/
public class FalseSharingTest {

    public static void main(String[] args) throws InterruptedException {
        // testPointer(new Pointer());
        // testPointer1(new Pointer1());
        // testPointer2(new Pointer2());
        testPointer3(new Pointer3());
    }

    private static void testPointer(Pointer pointer) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                pointer.x++;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                pointer.y++;
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(System.currentTimeMillis() - start);
        System.out.println(pointer);
    }

    private static void testPointer1(Pointer1 pointer) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                pointer.x++;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                pointer.y++;
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(System.currentTimeMillis() - start);
        System.out.println(pointer);
    }

    private static void testPointer2(Pointer2 pointer) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                pointer.x.value++;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                pointer.y.value++;
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(System.currentTimeMillis() - start);
        System.out.println(pointer);
    }

    private static void testPointer3(Pointer3 pointer) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                pointer.x.value++;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                pointer.y.value++;
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(System.currentTimeMillis() - start);
        System.out.println(pointer);
    }

}

class Pointer {
    volatile long x;
    volatile long y;
}

/**
 * 伪共享的原理我们知道了，一个缓存行是64字节，一个long类型是8个字节，所以避免伪共享也很简单，大概有以下三种方式：
 */

/**
 * （1）在两个long类型的变量之间再加7个long类型
 */
class Pointer1 {
    volatile long x;
    long p1, p2, p3, p4, p5, p6, p7;
    volatile long y;
}


/**
 * （2）使用@sun.misc.Contended注解（java8)
 */
class Pointer2 {
    MyLong x = new MyLong();
    MyLong y = new MyLong();
}

class MyLong {
    volatile long value;
    long p1, p2, p3, p4, p5, p6, p7;
}

/**
 * (3）使用@sun.misc.Contended注解（java8）
 */
class Pointer3 {
    MyLong2 x = new MyLong2();
    MyLong2 y = new MyLong2();
}

@sun.misc.Contended
class MyLong2 {
    volatile long value;
}

