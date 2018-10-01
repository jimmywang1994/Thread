package com.ww.ThreadDemo;

import static java.lang.Thread.activeCount;

/**
 * 观察线程状态
 */
public class AllState {
    public static void main(String[] args) {
        Thread t=new Thread(()->{
            for(int i=0;i<5;i++){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("......");
        });
        Thread.State state=t.getState();
        System.out.println(state);
        t.start();
        System.out.println(t.getState());
        /*while (state!=Thread.State.TERMINATED){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            state=t.getState();//TIMED_WAITING
            System.out.println(state);
        }*/
        while (state!=Thread.State.TERMINATED){
            int num=Thread.activeCount();
            System.out.println(num);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            state=t.getState();//TIMED_WAITING
            System.out.println(state);
        }
        System.out.println(state);
    }
}
