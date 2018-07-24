package com.yun.jdk8;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class tt {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("\\@(\\S+)");
        Matcher m = p.matcher("dsfds@ZYJyjdsfdsf@qq");
        if(m.find()){
            System.out.println(m.group(1));
        }
        System.out.println(m.group(1));
    }
}
