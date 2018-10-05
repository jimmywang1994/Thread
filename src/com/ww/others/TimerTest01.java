package com.ww.others;

import java.util.*;

/**
 * 任务调度：借助Timer和TimerTest类
 */
public class TimerTest01 {
    public static void main(String[] args) {
        Timer timer=new Timer();
        //执行安排
        Calendar calendar=new GregorianCalendar(2018,10,5,15,31,0);
       //timer.schedule(new MyTask(),1000,200);//执行多次
        //timer.schedule(new MyTask(),new Date(5000L),200);//5秒后执行
        timer.schedule(new MyTask(),calendar.getTime(),200);//指定时间执行
    }
}
class MyTask extends TimerTask{

    @Override
    public void run() {
        for (int i=0;i<10;i++){
            System.out.println("放空大脑");
        }
        System.out.println("end");
    }
}