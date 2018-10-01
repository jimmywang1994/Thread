package com.ww.SynchronizedDemo;

public class SynTest02 {
    public static void main(String[] args) {
        Account account = new Account(100, "礼金");
        SafeDrawing you = new SafeDrawing(account, 80, "你");
        SafeDrawing she = new SafeDrawing(account, 90, "她");
        you.start();
        she.start();
    }
}

//模拟取款
class SafeDrawing extends Thread {
    Account account;//取款账户
    int drawingMoney;//取的钱数
    int packactTotal;//取的总数

    public SafeDrawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        test();
    }

    //目标不对，锁定失败，不应该锁this，应该锁account
    public synchronized void test() {
        if (account.money - drawingMoney < 0) {
            return;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        account.money -= drawingMoney;
        packactTotal += drawingMoney;
        System.out.println(this.getName() + "--->账户余额为" + account.money);
        System.out.println(this.getName() + "--->口袋的钱为" + packactTotal);
    }
}