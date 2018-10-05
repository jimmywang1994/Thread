package com.ww.others;

/**
 * 可重入锁 锁可以连续使用
 */
public class LockTest {
    public void test(){
        synchronized (this){
            while (true){
                //第二次获得相同的锁
                synchronized (this){
                    System.out.println("ReentranLock!");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        new LockTest().test();
    }
}
