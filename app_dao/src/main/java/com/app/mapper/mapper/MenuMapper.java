package com.app.mapper.mapper;

import com.app.mapper.base.BaseMapper;
import com.app.model.model.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> listByUserId(Integer userId);

    List<Menu> listByRoleIds(List<Integer> roleIds);
}