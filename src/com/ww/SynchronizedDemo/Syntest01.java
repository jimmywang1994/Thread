package com.ww.SynchronizedDemo;

import com.ww.ThreadDemo.Web12306;

public class Syntest01{
    public static void main(String[] args) {
        SafeWeb12306 web = new SafeWeb12306();
        //多个代理
        new Thread(web, "m1").start();
        new Thread(web, "m2").start();
        new Thread(web, "m3").start();
    }
}
class SafeWeb12306 implements Runnable{
    private int ticketNums = 1000;
    private boolean flag = true;

    @Override
    public void run() {
        while (flag) {

            test();
        }
    }

    public synchronized void test() {
        if (ticketNums <= 0) {
            flag = false;
            return;
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " " + ticketNums--);
    }
}
