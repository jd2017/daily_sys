package com.yun;

public class 多态 {
    public static void main(String[] args) {
        A b = new B();
        System.out.println(b.name);//父类name
        System.out.println(b.desc);//父类desc
        System.out.println(new B().name);//子类name
        System.out.println(new B().desc);//子类desc
        b.staticFF();//父类静态方法
    }
    static class A{
        protected int value=1;
        protected static int num=1;
        protected String name="父类name";
        protected static String desc="父类desc";
        public A(int v) {

        }
        public void setValue(int value){
            this.value = value;
        }
        public int getValue(){
            return value;
        }

        public static void staticFF(){
            System.out.println("父类静态方法");
        }
    }
    static class B extends A{
        protected int value=1;
        protected static int num=1;
        protected String name="子类name";
        protected static String desc="子类desc";
        public B() {
            super(5);
        }
        public void setValue(int value){
            super.setValue(2*value);
        }
        public static void staticFF(){
            System.out.println("子类静态方法");
        }
    }
}
