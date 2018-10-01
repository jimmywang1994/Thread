package com.ww.SynchronizedDemo;

public class HappyCinema {
    public static void main(String[] args) {
        Cinema cinema = new Cinema(2, "happy cinema");
        new Thread(new Customer(cinema, 2), "老高").start();
        new Thread(new Customer(cinema, 1), "老王").start();
    }

}

//顾客
class Customer implements Runnable {

    Cinema cinema;
    int seats;

    public Customer(Cinema cinema, int seats) {
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
class Cinema {
    int avaliable;
    String name;

    public Cinema(int avaliable, String name) {
        this.avaliable = avaliable;
        this.name = name;
    }

    public boolean bookTickets(int seats) {
        System.out.println("可用位置为：" + avaliable);
        if (seats > avaliable) {
            return false;
        }
        avaliable -= seats;
        return true;
    }
}