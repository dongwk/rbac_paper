package com.app.mapper.mapper;

import com.app.mapper.base.BaseMapper;
import com.app.model.model.UserRole;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<Integer> listRoleIdByUserId(int userId);

}