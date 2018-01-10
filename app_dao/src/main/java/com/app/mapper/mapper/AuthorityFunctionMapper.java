package com.app.mapper.mapper;

import com.app.model.model.AuthorityFunction;

public interface AuthorityFunctionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthorityFunction record);

    int insertSelective(AuthorityFunction record);

    AuthorityFunction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthorityFunction record);

    int updateByPrimaryKey(AuthorityFunction record);
}