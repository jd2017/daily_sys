package com.yun.daily.system.dao;

import com.yun.daily.system.domain.TsSysUser;
import com.yun.daily.system.domain.TsSysUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TsSysUserMapper {
    int countByExample(TsSysUserExample example);

    int deleteByExample(TsSysUserExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(TsSysUser record);

    int insertSelective(TsSysUser record);

    List<TsSysUser> selectByExample(TsSysUserExample example);

    TsSysUser selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") TsSysUser record, @Param("example") TsSysUserExample example);

    int updateByExample(@Param("record") TsSysUser record, @Param("example") TsSysUserExample example);

    int updateByPrimaryKeySelective(TsSysUser record);

    int updateByPrimaryKey(TsSysUser record);
}