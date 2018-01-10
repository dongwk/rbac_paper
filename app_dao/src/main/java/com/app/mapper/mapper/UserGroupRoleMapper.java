package com.app.mapper.mapper;

import com.app.model.model.UserGroupRole;

public interface UserGroupRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserGroupRole record);

    int insertSelective(UserGroupRole record);

    UserGroupRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserGroupRole record);

    int updateByPrimaryKey(UserGroupRole record);
}