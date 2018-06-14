package com.yun.test;

import com.yun.daily.personUser.domain.PersonUser;

import java.util.ArrayList;
import java.util.List;

public class tt {
    public static void main(String[] args){
        List<PersonUser> list = new ArrayList();
        PersonUser p = new PersonUser();
        p.setPersonId("");
        p.setName("用户");
        PersonUser p1 = new PersonUser();
        p1.setPersonId("1");
        p1.setName("用户1");
        PersonUser p2 = new PersonUser();
        p2.setPersonId("2");
        p2.setName("用户2");
        list.add(p);
        list.add(p1);
        list.add(p2);
        for (PersonUser pp:list) {
            pp.setName("兔子");
        }

        for (PersonUser ppp:list) {
            System.out.println(ppp.getName());
        }
    }
}
