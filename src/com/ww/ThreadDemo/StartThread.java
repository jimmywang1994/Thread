package com.ww.ThreadDemo;

public class StartThread extends Thread{

    @Override
    public void run() {
        for (int i=0;i<20;i++){
            System.out.println("一边听歌");
        }
    }

    public static void main(String[] args) {
        //启动线程
        StartThread startThread=new StartThread();
        //startThread.start();
        for (int i=0;i<20;i++){
            System.out.println("一边coding");
        }
        startThread.start();//不保证立即执行 cpu调用
    }
}
