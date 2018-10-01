package com.ww.SynchronizedDemo;

import java.util.ArrayList;
import java.util.List;

public class HappyCinema2 {
    public static void main(String[] args) {
        List<Integer> avaliable=new ArrayList<>();
        avaliable.add(1);
        avaliable.add(2);
        avaliable.add(3);
        avaliable.add(6);
        avaliable.add(7);
        avaliable.add(8);
        List<Integer> seats1=new ArrayList<>();
        seats1.add(1);
        seats1.add(2);
        //需要的位置
        List<Integer> seats2=new ArrayList<>();
        seats2.add(6);
        seats2.add(7);
        SxtCinema cinema = new SxtCinema(avaliable, "happy cinema");
        new Thread(new HappyCustomer(cinema, seats1), "老高").start();
        new Thread(new HappyCustomer(cinema, seats2), "老王").start();
    }

}

//顾客
class HappyCustomer implements Runnable {

    SxtCinema cinema;
    List<Integer> seats;

    public HappyCustomer(SxtCinema cinema, List<Integer> seats) {
        this.cinema = cinema;
        this.seats = seats;
    }

    @Override
    public void run() {
        synchronized (cinema) {
            boolean flag = cinema.bookTickets(seats);
            if (flag) {
                System.out.println("出票成功" + Thread.currentThread().getName() + "--->位置为" + seats);
            } else {
                System.out.println("出票失败" + Thread.currentThread().getName() + "位置不够");
            }
        }
    }

}

//影院
class SxtCinema {
    List<Integer> avaliable;
    String name;

    public SxtCinema(List<Integer> avaliable, String name) {
        this.avaliable = avaliable;
        this.name = name;
    }

    public boolean bookTickets(List<Integer> seats) {
        System.out.println("欢迎选购"+this.name+"可用位置为：" + avaliable);
        List<Integer> copy=new ArrayList<>();
        copy.addAll(avaliable);
        //相减
        copy.removeAll(seats);
        //判断大小
        if(avaliable.size()-copy.size()!=seats.size()){
            return false;
        }
        //成功
        avaliable=copy;

        return true;
    }
}