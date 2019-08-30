package com.app.mapper.mapper;

import com.app.mapper.base.BaseMapper;
import com.app.model.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> selectAll();
}