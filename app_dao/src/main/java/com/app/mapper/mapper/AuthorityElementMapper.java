package com.app.mapper.mapper;

import com.app.model.model.AuthorityElement;

public interface AuthorityElementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthorityElement record);

    int insertSelective(AuthorityElement record);

    AuthorityElement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthorityElement record);

    int updateByPrimaryKey(AuthorityElement record);
}