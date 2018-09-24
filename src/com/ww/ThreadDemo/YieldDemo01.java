package com.ww.ThreadDemo;

/**
 * yield礼让线程，暂停线程，直接进入就绪状态不是阻塞状态
 */
public class YieldDemo01 {
    public static void main(String[] args) {
        new Thread(new MyYield(),"111").start();
        new Thread(new MyYield(),"222").start();
    }

}
class MyYield implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"-->start");
        Thread.yield();//礼让
        System.out.println(Thread.currentThread().getName()+"-->end");
    }
}
