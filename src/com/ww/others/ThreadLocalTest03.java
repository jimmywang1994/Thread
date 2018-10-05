package com.ww.others;

/**
 * ThreadLocal：分析上下文环境
 * 1.构造器 哪里调用就属于哪里 找线程体
 * 2.run方法 线程自身的
 * get/set/initValue
 */
public class ThreadLocalTest03 {
    //private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    //更改初始值
    /*private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>() {
        protected Integer initialValue() {
            return 200;
        }
    };*/
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

    public static void main(String[] args) {
        new Thread(new MyRun()).start();
    }

    public static class MyRun implements Runnable {
        public MyRun() {
            threadLocal.set(-100);
            System.out.println(Thread.currentThread().getName() + "还剩-->" + threadLocal.get());
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "还剩-->" + threadLocal.get());
        }
    }
}
