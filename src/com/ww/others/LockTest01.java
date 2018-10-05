package com.ww.others;

/**
 * 不可重入锁 锁不可以连续使用
 */
public class LockTest01 {
    Lock lock=new Lock();
    public void test(){

    }
    public void a() throws InterruptedException {
        lock.lock();
        b();
        lock.unlock();
    }
    //不可重入
    public void b() throws InterruptedException {
        lock.lock();
        lock.unlock();
    }
    public static void main(String[] args) throws InterruptedException {
        LockTest01 lockTest=new LockTest01();
        lockTest.a();
        lockTest.b();
    }
}
//不可重入锁
class Lock{
    //是否持有
    private boolean isLocked=false;
    //使用锁
    public synchronized void lock() throws InterruptedException {
        while (isLocked){
            wait();
        }
        isLocked=true;
    }
    //释放锁
    public synchronized void unlock(){
        isLocked=false;
        notify();
    }
}