package com.ww.cooperationDemo;

/**
 * 线程协作模型：生产者消费者实现方式二 信号灯法
 * 借助标志位
 */
public class CoTest02 {
    public static void main(String[] args) {
        TV tv=new TV();
        new Actor(tv).start();
        new Audience(tv).start();
    }
}

//生产者 演员
class Actor extends Thread {
    TV tv;

    public Actor(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                this.tv.play("奇葩说");
            } else {
                this.tv.play("太污了");
            }
        }
    }
}

//消费者 观众
class Audience extends Thread {
    TV tv;

    public Audience(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                this.tv.watch();
            } else {
                this.tv.watch();
            }
        }
    }
}

//同一个资源 电视
class TV {
    String voice;
    //信号灯
    //T 表示演员表演 观众等待
    //F 表示观众观看 演员等待
    boolean flag = true;

    //表演
    public synchronized void play(String voice) {
        //演员等待
        if(!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("表演了" + voice);
        this.voice = voice;
        //唤醒
        this.notifyAll();
        //切换标志
        this.flag=!this.flag;
    }

    //观看
    public synchronized void watch() {
        //观众等待
        if(flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //观看
        System.out.println("听到了" + voice);
        this.notifyAll();
        //切换标志
        this.flag=!this.flag;
    }
}