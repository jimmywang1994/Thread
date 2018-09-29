package com.ww.SynchronizedDemo;

import com.ww.ThreadDemo.Web12306;

public class UnSafeTest01 implements Runnable {
    private int ticketNums = 100;
    private boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            test(); 
        }
    }

    public void test() {
        if (ticketNums < 0) {
            flag = false;
            return;
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " " + ticketNums--);
    }

    public static void main(String[] args) {
        Web12306 web = new Web12306();
        //多个代理
        new Thread(web, "m1").start();
        new Thread(web, "m2").start();
        new Thread(web, "m3").start();
    }
}
