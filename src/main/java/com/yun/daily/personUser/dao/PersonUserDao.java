package com.yun.daily.personUser.dao;

import com.yun.daily.personUser.domain.PersonUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PersonUserDao {
    PersonUser selectByPersonId(String personId);
    int deleteByPersonId(String personId);
    int insert(PersonUser personUser);
    int insertSelective(PersonUser personUser);
    int updateByPersonId(PersonUser personUser);
}
