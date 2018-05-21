package com.yun.daily.secure.factory;

import com.yun.daily.personUser.domain.PersonUser;
import com.yun.daily.secure.domain.RoleModel;
import com.yun.daily.secure.domain.SecureUser;
import com.yun.daily.secure.domain.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class SecureUserFactory {
    private SecureUserFactory(){

    };

    public static SecureUser create(UserModel userModel){
        return new SecureUser(userModel.getUserId(),
                              userModel.getAccount(),
                              userModel.getPassword(),
                              mapToGrantedAuthorities(userModel.getRoles()));
    }
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<RoleModel> authorities){
        List<GrantedAuthority> list = new ArrayList<>();
        if(authorities!=null&& !authorities.isEmpty()){
            for (RoleModel role : authorities){
                if(role!=null){
                    String roleName = role.getRoleName(),
                            roleId=role.getRoleId();
                    if(roleName!=null){
                        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
                        list.add(simpleGrantedAuthority);
                    }else if(roleName!=null&&roleId!=null){
                        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleId);
                        list.add(simpleGrantedAuthority);
                    }
                }
            }
        }
        return list;
    }
}
