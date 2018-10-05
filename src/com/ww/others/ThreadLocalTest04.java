package com.ww.others;

/**
 * InheritableThreadLocal：继承上下文环境的数据 拷贝一份给子线程
 * 1.构造器 哪里调用就属于哪里 找线程体
 * 2.run方法 线程自身的
 * get/set/initValue
 */
public class ThreadLocalTest04 {
    private static ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set(2);
        System.out.println(Thread.currentThread().getName() + "还剩-->" + threadLocal.get());
        //线程由Main线程开辟
        new Thread(() -> {
            threadLocal.set(200);
            System.out.println(Thread.currentThread().getName() + "还剩-->" + threadLocal.get());
        }).start();
    }


}
