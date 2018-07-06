package com.yun;

import java.util.Scanner;

/**
 * 斐波那契数列指的是这样一个数列 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233，377，610，987，1597，2584，4181，6765，10946，17711，28657，46368........
 自然中的斐波那契数列
 自然中的斐波那契数列
 这个数列从第3项开始，每一项都等于前两项之和。
 */
public class 斐波那契数列 {
    public static void main(String[] args) {
          while (true){
              System.out.print("请输入：");
              Scanner sc = new Scanner(System.in);
              int n = sc.nextInt();
              int prev=1;
              int next=1;
              int val=1;
              for (int i=3;i<=n;i++){
                  int temp = prev+next;
                  if(i!=n){
                      prev = next;
                      next = temp;
                  }else{
                      val = temp;
                  }
              }
              System.out.println("第"+n+"项为："+val);
          }
    }
}
