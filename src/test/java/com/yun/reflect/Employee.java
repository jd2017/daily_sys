package com.yun.reflect;

import lombok.Data;

import java.util.Date;

@Data
class Employee{
    private String name="没名字";
    private double salary;
    private Date hireDay;
    public Employee(String name,double salary,Date hireDay){
        this.name=name;
        this.salary=salary;
        this.hireDay=hireDay;
        System.out.println("Employee");
    }
    public Employee(){
        this.name=name;
        this.salary=salary;
        this.hireDay=hireDay;
        System.out.println("Employee");
    }
}
