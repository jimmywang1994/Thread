package com.ww.SynchronizedDemo;

import java.util.List;

public class Happy12306 {
    public static void main(String[] args) {
        Web12306 web12306 = new Web12306(4, "happy cinema");
        new HappyPassenger(web12306, "老高", 2).start();
        new HappyPassenger(web12306, "老王", 1).start();
    }
}

//顾客
class HappyPassenger extends Thread {

    int seats;

    public HappyPassenger(Runnable target, String name, int seats) {
        super(target, name);
        this.seats = seats;
    }
}

//影院
class Web12306 implements Runnable {
    int avaliable;
    String name;

    public Web12306(int avaliable, String name) {
        this.avaliable = avaliable;
        this.name = name;
    }

    @Override
    public void run() {
        HappyPassenger p = (HappyPassenger) Thread.currentThread();
        boolean flag = this.bookTickets(p.seats);
        if (flag) {
            System.out.println("出票成功" + Thread.currentThread().getName() + "--->位置为" + p.seats);
        } else {
            System.out.println("出票失败" + Thread.currentThread().getName() + "位置不够");
        }
    }

    public synchronized boolean bookTickets(int seats) {
        System.out.println("可用位置为：" + avaliable);
        if (seats > avaliable) {
            return false;
        }
        avaliable -= seats;
        return true;
    }
}
