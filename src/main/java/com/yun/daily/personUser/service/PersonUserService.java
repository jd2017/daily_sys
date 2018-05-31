package com.yun.daily.personUser.service;

import com.yun.daily.personUser.dao.PersonUserDao;
import com.yun.daily.personUser.domain.PersonUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PersonUserService {
    @Resource
    private PersonUserDao personUserDao;

    public PersonUser queryUserById(String personId){
        return personUserDao.selectByPersonId(personId);
    };
    public int deleteByPersonId(String personId){
        return personUserDao.deleteByPersonId(personId);
    };
    public int insert(PersonUser personUser){
        return personUserDao.insert(personUser);
    };
    public int insertSelective(PersonUser personUser){
        return personUserDao.insertSelective(personUser);
    };
    public int updateByPersonId(PersonUser personUser){
        return personUserDao.updateByPersonId(personUser);
    };
    public PersonUser queryByAccount(String account){
        return personUserDao.queryByAccount(account);
    };

}
