package com.ww.ThreadDemo;

/**
 * 插队线程
 */
public class BlockedJoin01 {
    public static void main(String[] args) {
        Thread t=new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("lambda..." + i);
            }
        });
        t.start();

        for (int i = 0; i < 100; i++) {
            if (i == 20) {
                try {
                    t.join();//插队 main线程被阻塞了
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("main..." + i);
        }
    }
}
