package com.app.mapper.mapper;

import com.app.mapper.base.BaseMapper;
import com.app.model.model.Function;
import com.app.model.model.RoleFunction;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface RoleFunctionMapper extends BaseMapper<RoleFunction> {

    List<Function> listFuncByRoleIds(@Param("roleIds") List<Integer> roleIds);

    Set<String> listFuncUrlByRoleIds(@Param("roleIds") List<Integer> roleIds);

}