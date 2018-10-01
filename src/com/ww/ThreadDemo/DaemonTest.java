package com.ww.ThreadDemo;

/**
 * 守护线程：是为用户线程服务的；jvm停止不用等待守护线程执行完毕
 */
public class DaemonTest {
    public static void main(String[] args) {
        God god=new God();
        Me me=new Me();
        new Thread(god).start();
        new Thread(me).start();
    }

}

class Me extends Thread{
    @Override
    public void run() {
       for(int i=0;i<=365*100;i++){
           System.out.println("happy life....");
       }
        System.out.println("oooo....");
    }
}

class God extends Thread{
    @Override
    public void run() {
        while (true){
            System.out.println("bless you....");
        }
    }
}
