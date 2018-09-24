package com.ww.ThreadDemo;

/**
 * 共享资源
 */
public class Web12306 implements Runnable{
    private int ticketNums=100;
    @Override
    public void run() {
        while (true){
            if(ticketNums<0){
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" "+ticketNums--);
        }
    }

    public static void main(String[] args) {
        Web12306 web=new Web12306();
        //多个代理
        new Thread(web,"m1").start();
        new Thread(web,"m2").start();
        new Thread(web,"m3").start();
    }
}
