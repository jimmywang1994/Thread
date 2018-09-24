package com.ww.ThreadDemo;

/**
 * 接口
 * 1.真实角色
 * 2.代理角色
 */
public class StaticProxy {
    public static void main(String[] args) {
        new WeddingCompany(new You()).happyMarry();
    }
}
interface Marry{
    void happyMarry();
}
class You implements Marry{

    @Override
    public void happyMarry() {
        System.out.println("you and 嫦娥奔月了");
    }
}
class WeddingCompany implements Marry{

    private Marry target;

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void happyMarry() {
        ready();
        this.target.happyMarry();
        end();
    }

    private void end() {
        System.out.println("after");
    }

    private void ready() {
        System.out.println("before");
    }


}
