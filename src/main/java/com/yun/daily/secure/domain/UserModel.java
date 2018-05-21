package com.yun.daily.secure.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by itw_zhuyj on 2018/4/2.
 */
@Data
public class UserModel {
    private String userId;

    private String account;

    private String password;

    private String name;

    private List<RoleModel> roles;
}
