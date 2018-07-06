package com.yun.reflect;

import java.util.Date;

class Manager extends Employee{
    public Manager(String name,double salary,Date hireDay){
        super(name,salary,hireDay);
        System.out.println("Manager");
    }
}
