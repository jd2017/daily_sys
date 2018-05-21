package com.yun.daily.secure.domain;

import lombok.Data;

@Data
public class RoleModel {
    private String roleId;
    private String roleName;
    private String systemType;
    private String delFlag;
}