package com.app.mapper.mapper;

import com.app.mapper.base.BaseMapper;
import com.app.model.model.Function;
import com.app.model.model.RoleFunction;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 *
 * @author dongwk
 * @date 2021-03-17
 * @version 1.0
 */
public interface RoleFunctionMapper extends BaseMapper<RoleFunction> {

    List<Function> listFuncByRoleIds(@Param("roleIds") List<Integer> roleIds);

    Set<String> listFuncUrlByRoleIds(@Param("roleIds") List<Integer> roleIds);
}