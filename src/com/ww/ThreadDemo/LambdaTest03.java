package com.ww.ThreadDemo;

public class LambdaTest03 {
    public static void main(String[] args) {
        IInterest interest = (int a1, int a2) -> {
            System.out.println("i interested in lanbda");
            return a1 + a2;
        };
        System.out.println(interest.lambda(200, 300));

        interest = (a1, a2) -> {
            return a1 + a2;
        };
        System.out.println(interest.lambda(100, 300));
        interest = (a1, a2) -> a1 + a2;
        System.out.println(interest.lambda(1000, 2000));
    }
}

interface IInterest {
    int lambda(int a1, int a2);
}

class Interest implements IInterest {

    @Override
    public int lambda(int a1, int a2) {
        System.out.println("i interested in lambda" + (a1 + a2));
        return a1 + a2;
    }
}
