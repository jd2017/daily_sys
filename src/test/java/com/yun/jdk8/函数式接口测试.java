package com.yun.jdk8;

public class 函数式接口测试 {
    public void consumeFoo(Foo foo) {
        System.out.println(foo.doSomething());
    }
    public void consumeBar(Bar bar) {
        System.out.println(bar.doSomething());
    }

    public static void main(String[] args) {
        函数式接口测试 consumer = new 函数式接口测试();
        consumer.consumeFoo(() -> 10);    // 10
        consumer.consumeBar(() -> 20);//20

    }

    public int m(){
        try{
            return 1;
        }finally {
           // return 2;
        }
    }

    float f1(){
        byte i=1;
        int ii=012;
        long ll =012;
        float f = -412;
        float f1 = 1.5f;
        return i;
    }
}
