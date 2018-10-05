package com.ww.others;

/**
 * 用于保证数据的同步，也就是可见性 不保证原子性
 */
public class VolatileTest {
    private volatile static int num = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            while (num == 0) {//此处不要写代码

            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num = 1;
    }
}
