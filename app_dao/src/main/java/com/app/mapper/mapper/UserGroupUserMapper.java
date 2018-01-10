package com.app.mapper.mapper;

import com.app.model.model.UserGroupUser;

public interface UserGroupUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserGroupUser record);

    int insertSelective(UserGroupUser record);

    UserGroupUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserGroupUser record);

    int updateByPrimaryKey(UserGroupUser record);
}