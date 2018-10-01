package com.ww.ThreadDemo;


public class BlockedJoin02 {
    public static void main(String[] args) {
        System.out.println("爸爸和儿子买烟的故事");
        new Thread(new Father()).start();
    }
}

class Father extends Thread {
    @Override
    public void run() {
        System.out.println("想抽烟，发现没了");
        System.out.println("让儿子去买烟");
        Thread t = new Thread();
        t.start();
        new Thread(new Son()).start();
        try {
            t.join();
        } catch (InterruptedException e) {

        }
    }
}

class Son extends Thread {
    @Override
    public void run() {
        System.out.println("接过钱出去了");
        System.out.println("路过一个游戏厅，玩了10秒");
        for (int i = 0; i < 10; i++) {
            System.out.println((i+1) + "秒过去了");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("买完了");
    }
}
