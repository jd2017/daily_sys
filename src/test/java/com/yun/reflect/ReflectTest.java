package com.yun.reflect;

import java.lang.reflect.*;
import java.util.Date;
import java.util.Scanner;

public class ReflectTest {
    public static void main(String[] args) {
        Employee e=new Employee("jack",1000,new Date());
        System.out.println(e.getClass().getName()+" "+e.getName());//com.yun.test.反射$Employee jack
        Employee m=new Manager("jingli",1002,new Date());
        System.out.println(m.getClass().getName()+" "+m.getName());//com.yun.test.反射$Manager jingli
        System.out.println(e.getClass().getSuperclass().getName());//java.lang.Object
        System.out.println(m.getClass().getSuperclass().getName());//com.yun.test.反射$Employee

        try {
            //调用静态方法Class.forName获得类名对应的class对象；
            Class c = Class.forName("com.yun.test.Employee");
            System.out.println("调用静态方法Class.forName获得类名对应的class对象:"+c.getName());
            Employee employee = (Employee) c.newInstance();//得有默认构造方法
            System.out.println(employee.getName());
            Employee employee1 = (Employee) c.getConstructor(String.class,double.class,Date.class).newInstance(new Object[]{"普通雇员1",200,new Date()});
            System.out.println(employee1.getName());

            //输入类名将打印该类的全部信息
            System.out.println("请输入类名");
            Scanner sc = new Scanner(System.in);
            String name = sc.next();
            Class cl = Class.forName(name);
            String modifiers = Modifier.toString(cl.getModifiers());//形式符的字符串表示
            if(modifiers.length()>0) System.out.print(modifiers +" ");
            System.out.println("class "+name);
            Class supercl = cl.getSuperclass();
            if(supercl!=null&&supercl!=Object.class) System.out.print(" extends "+supercl.getName());
            System.out.print("{\n");
            printConstructors(cl);
            System.out.println();
            printMethods(cl);
            System.out.println();
            printFields(cl);
            System.out.print("\n}\n");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }
    }

    static void printConstructors(Class cl){
        Constructor[] constructors = cl.getDeclaredConstructors();
        for (Constructor c : constructors){
            System.out.print("  ");
            String modifiers = Modifier.toString(c.getModifiers());
            if(modifiers.length()>0) System.out.print(modifiers +" ");
            System.out.print(c.getName()+"(");
            Class[] paramTypes = c.getParameterTypes();
            for (int j=0;j<paramTypes.length;j++){
                if(j>0) System.out.print(",");
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(");");
        }
    }
    static void printMethods(Class cl){
        Method[] methods = cl.getDeclaredMethods();
        for (Method m : methods){
            System.out.print("  ");
            String modifiers = Modifier.toString(m.getModifiers());
            if(modifiers.length()>0) System.out.print(modifiers +" ");
            Class returnType = m.getReturnType();
            System.out.print(returnType.getName()+" ");
            System.out.print(m.getName()+"(");
            Class[] paramTypes = m.getParameterTypes();
            for (int j=0;j<paramTypes.length;j++){
                if(j>0) System.out.print(",");
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(");");
        }
    }
    static void printFields(Class cl){
        Field[] fields = cl.getDeclaredFields();
        for (Field f : fields){
            System.out.print("  ");
            String modifiers = Modifier.toString(f.getModifiers());
            if(modifiers.length()>0) System.out.print(modifiers +" ");
            System.out.print(f.getName()+";");
            System.out.println();
        }
    }
}
