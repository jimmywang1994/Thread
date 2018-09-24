package com.ww.ThreadDemo;

import java.util.List;

public class LambdaTest01 {
    static class Ilike2 implements Ilike{

        @Override
        public void lambda() {
            System.out.println("i like lambda2");
        }
    }
    public static void main(String[] args) {
        Ilike ilike=new Like();
        ilike.lambda();
        ilike=new Ilike2();
        ilike.lambda();
        ilike=new Ilike() {
            @Override
            public void lambda() {
                System.out.println("i like lambda3");
            }
        };
        ilike.lambda();
        //lambda
        ilike=()->{
            System.out.println("i like lambda4");
        };
        ilike.lambda();
    }
}

interface Ilike{
    void lambda();
}
class Like implements Ilike{

    @Override
    public void lambda() {
        System.out.println("i like lambda");
    }
}
