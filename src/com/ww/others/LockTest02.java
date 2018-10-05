package com.ww.others;

/**
 * 可重入锁 锁可以连续使用
 */
public class LockTest02 {
    ReLock lock = new ReLock();
    public void a() throws InterruptedException {
        lock.lock();
        System.out.println(lock.getHoldCount());
        b();
        lock.unlock();
        System.out.println(lock.getHoldCount());
    }

    //可重入
    public void b() throws InterruptedException {
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
    }

    public static void main(String[] args) throws InterruptedException {
        LockTest02 lockTest = new LockTest02();
        lockTest.a();
        lockTest.b();
        Thread.sleep(1000);
        System.out.println(lockTest.lock.getHoldCount());
    }
}

class ReLock {
    //是否持有
    private boolean isLocked = false;
    private Thread lockedBy = null;//存储线程
    private int holdCount = 0;

    //使用锁
    public synchronized void lock() throws InterruptedException {
        Thread t = Thread.currentThread();
        while (isLocked && lockedBy != t) {
            wait();
        }
        isLocked = true;
        lockedBy = t;
        holdCount++;
    }

    //释放锁
    public synchronized void unlock() {
        if (Thread.currentThread() == lockedBy) {
            holdCount--;
            if (holdCount == 0) {
                isLocked = false;
                notify();
                lockedBy = null;
            }
        }
    }

    public int getHoldCount() {
        return holdCount;
    }
}