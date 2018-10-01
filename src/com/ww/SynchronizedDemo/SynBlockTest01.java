package com.ww.SynchronizedDemo;

public class SynBlockTest01 {
    public static void main(String[] args) {
        Account account = new Account(1000, "礼金");
        BlockDrawing you = new BlockDrawing(account, 80, "你");
        BlockDrawing she = new BlockDrawing(account, 90, "她");
        you.start();
        she.start();
    }
}

//模拟取款  线程安全
class BlockDrawing extends Thread {
    Account account;//取款账户
    int drawingMoney;//取的钱数
    int packactTotal;//取的总数

    public BlockDrawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        test();
    }

    //目标不对，锁定失败，不应该锁this，应该锁account
    public  void test() {
        synchronized (account){
            if(account.money<=0){
                return;
            }
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
}