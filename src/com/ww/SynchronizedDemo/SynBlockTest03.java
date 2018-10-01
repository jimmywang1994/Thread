package com.ww.SynchronizedDemo;

public class SynBlockTest03 {
    public static void main(String[] args) {
        SynWeb12306 web = new SynWeb12306();
        //多个代理
        new Thread(web, "m1").start();
        new Thread(web, "m2").start();
        new Thread(web, "m3").start();
    }
}

class SynWeb12306 implements Runnable {
    private int ticketNums = 10;
    private boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            test5();
        }
    }

    //锁的是资源
    public synchronized void test1() {
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

    //锁的是this
    public void test2() {
        synchronized (this) {
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

    //线程不安全 ticketNums对象在变
    public void test3() {
        synchronized ((Integer) ticketNums) {
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

    //线程不安全 范围太小，锁不住
    public void test4() {
        synchronized (this) {
            if (ticketNums <= 0) {//当只剩一张票的时候，这一块锁不住数据，因为之后会有等待，数据还没写回去，下一个线程就来了，看到的还是一张票
                flag = false;
                return;
            }
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " " + ticketNums--);
    }

    //线程安全：尽可能锁定合理的范围（不是指代码 指数据的完整性）
    //double checking
    public void test5() {
        if (ticketNums <= 0) {
            flag = false;
            return;
        }
        synchronized (this) {
            if (ticketNums <= 0) {
                flag = false;
                return;
            }
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " " + ticketNums--);
    }
}

