package com.yun;

/**
 * 内部类测试
 */
public class InnerClass {
    String a = "a";
    static String b = "b";
    final String c = "c";
    final static String d = "d";
    /**
     *1、成员内部类可以用权限修饰符public，protected，默认，private
     */
    public class InnerClass1{
       String a1=a;//对
       String b1=b;//对
       String c1=c;//对
       String d1=d;//对
    }
    /**
     *2、静态内部类可以用权限修饰符public，protected，默认，private
     */
    public static class StaticInnerClass1{
        //String a1=a;//错，非静态不能被静态引用
        String b1=b;//对
        //String c1=c;//对//错，非静态不能被静态引用
        String d1=d;//对
    }

    /**
     * 3、局部内部类测试，局部内部类同局部变量一样，不可以用权限修饰符（public，protected，private）
     * ,方法内的局部内部类不能用static修饰
     */
    public void fangfa1(){
        String a2="你好";
        //String b2="你好";
        class StaticInnerClass2{
            String a3=a2;//对
            public void ff(){
                //Java8中，局部内部类访问的局部变量不必用final修饰，这一点和Java7是不一样的。
                String a4=a2+"1";//对

                //错，Java8中,局部内部类仅仅访问局部变量，局部变量不需要加final,如果要修改局部变量就必须加final了,其实就是不允许修改
                //Variable 'b2' is accessed from within inner class, needs to be final or effectively final
               // b2="hekllo";
            }
        }
    }

    /**
     * 4、匿名内部类
     */
}
