package com.yun.枚举;

import java.util.Scanner;

public class EnumClassTest {
    public static void main(String[] args) {
//        EnumClass.MAX.getCname() ;
        EnumClass small = EnumClass.SMALL;
        EnumClass[] values = EnumClass.values();
        EnumClass.valueOf("SMALL");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：(SMALL,MEDIUM,LARGE,MAX)");
        String input = scanner.next().toUpperCase();
        EnumClass enumClass = Enum.valueOf(EnumClass.class,input);
        System.out.println("enumClass:"+enumClass);
        System.out.println("cname="+enumClass.getCname());
        System.out.println("EnumClass.SMALL="+ EnumClass.SMALL);

        //返回枚举常量在在enum中声明的位置，位置从0开始
        System.out.println("enumClass.ordinal()="+enumClass.ordinal());
        System.out.println("enumClass.name()="+enumClass.name());
        System.out.println("enumClass.toString()="+enumClass.toString());
        System.out.println("EnumClass.SMALL==enumClass"+(EnumClass.SMALL==enumClass));//true
    }
}
