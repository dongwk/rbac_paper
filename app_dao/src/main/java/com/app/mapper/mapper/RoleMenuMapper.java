package com.app.mapper.mapper;

import com.app.mapper.base.BaseMapper;
import com.app.model.model.RoleMenu;

import java.util.List;

public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    List<Integer> listMenuIdByRoleIds(List<Integer> roleIds);
}