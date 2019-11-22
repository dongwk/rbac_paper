package com.app.mapper.mapper;

import com.app.mapper.base.BaseMapper;
import com.app.model.model.ElementFunction;
import com.app.model.model.Function;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface ElementFunctionMapper extends BaseMapper<ElementFunction> {

    List<Function> listFuncByRoleIds(@Param("roleIds") List<Integer> roleIds);

    Set<String> listFuncUrlByRoleIds(@Param("roleIds") List<Integer> roleIds);

}