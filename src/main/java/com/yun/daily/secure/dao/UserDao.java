package com.yun.daily.secure.dao;

import com.yun.daily.personUser.domain.PersonUser;
import com.yun.daily.secure.domain.RoleModel;
import com.yun.daily.secure.domain.UserModel;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface UserDao {
    @Select("SELECT person_id,account,password FROM td_person_user WHERE account=#{account}")
    @Results({@Result(id=true,property = "userId",column = "person_id"),
            @Result(property = "account",column = "account"),
            @Result(property = "password",column = "password"),
            @Result(property = "roles",javaType = List.class,column = "person_id",
                    many = @Many(select = "com.yun.daily.secure.dao.UserDao.getUserRoles"))
    })
    UserModel selectPersonUserByAccount(String account);

    @Select("SELECT a.role_id,b.role_name FROM tre_user_role a LEFT JOIN ts_sys_role b ON a.role_id=b.role_id WHERE a.user_id=#{person_id}")
    @Results({@Result(id=true,property = "roleId",column = "role_id"),
            @Result(property = "roleName",column = "role_name")})
    List<RoleModel> getUserRoles(String person_id);
}
