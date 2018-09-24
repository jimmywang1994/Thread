package com.ww.ThreadDemo;

import com.sun.org.apache.bcel.internal.generic.ILOAD;

/**
 * lanbda+a参数
 */
public class LambdaTest02 {
    static class Ilike2 implements Ilike {

        @Override
        public void lambda() {
            System.out.println("i like lambda2");
        }
    }

    public static void main(String[] args) {
        ILove love = (int a) -> {
            System.out.println("i like lambda-->" + a);
        };
        love.lambda(3);
        //简化
        love = a -> {
            System.out.println("i love lambda-->" + a);
        };
        //再简化
        love = a -> System.out.println("i love lambda-->" + a);
        love.lambda(6);
    }
}

interface ILove {
    void lambda(int a);
}

class Love implements ILove {

    @Override
    public void lambda(int a) {
        System.out.println("I Love " + a);
    }
}


