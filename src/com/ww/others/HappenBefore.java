package com.ww.others;

/**
 * 多线程中的指令重排：代码执行顺序与预期不一致
 */
public class HappenBefore {
    private static int a = 0;
    private static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        //线程1 读取数据
        //线程2 修改数据
        for (int i = 0; i < 10; i++) {
            a = 0;
            flag = false;
            Thread t1 = new Thread(() -> {
                a = 1;
                flag = true;
            });
            Thread t2 = new Thread(() -> {
                if (flag) {
                    a *= 1;
                }
                //指令重排
                if (a == 0) {
                    System.out.println("happen before a->" + a);
                }
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        }
    }
}
