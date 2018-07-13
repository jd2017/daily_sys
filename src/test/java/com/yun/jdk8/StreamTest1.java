package com.yun.jdk8;

import lombok.Data;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest1 {
    public static void main(String[] args) {
        //建立对象
        Trader xiaoming=new Trader("小明","广州");
        Trader xiaohong=new Trader("小红","广州");
        Trader xiaohei=new Trader("小黑","广州");
        Trader xiaobai=new Trader("小白","肇庆");

        //新建一个交易的集合
        List<Transaction> transactions= Arrays.asList(
                new Transaction(xiaoming,2017,300),
                new Transaction(xiaohong,2016,1000),
                new Transaction(xiaohong,2017,400),
                new Transaction(xiaohei,2016,710),
                new Transaction(xiaohei,2016,700),
                new Transaction(xiaobai,2016,950)
        );

        List<Transaction> list=transactions.stream()
                .filter(s->s.getYear()==2016)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println("list:"+list);

        String collect = transactions.stream()
                .map(t->t.getTrader().getName())
                .distinct()
                .sorted(Comparator.comparing(String::toString))
                .reduce("",(n1,n2)->n1+n2);
        System.out.println(collect);
        System.out.println();
    }
}

@Data
class Trader {
    private String name;
    private String city;
    public Trader(String name,String city){
       this.name=name;
       this.city=city;
    }
}

@Data
class Transaction {
    private Trader trader;
    private int year;
    private int value;
    public Transaction(Trader trader,int year,int value){
        this.trader=trader;
        this.year=year;
        this.value=value;
    }
}