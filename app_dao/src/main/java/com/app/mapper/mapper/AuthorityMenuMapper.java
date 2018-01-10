package com.app.mapper.mapper;

import com.app.model.model.AuthorityMenu;

public interface AuthorityMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthorityMenu record);

    int insertSelective(AuthorityMenu record);

    AuthorityMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthorityMenu record);

    int updateByPrimaryKey(AuthorityMenu record);
}