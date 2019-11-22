/**
 * 
 */
package com.app.service.service;

import com.app.mapper.mapper.ElementFunctionMapper;
import com.app.mapper.mapper.RoleElementMapper;
import com.app.model.model.ElementFunction;
import com.app.model.model.Function;
import com.app.model.model.RoleElement;
import com.app.service.base.BaseSimpleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ElementFunctionService extends BaseSimpleService<ElementFunctionMapper, ElementFunction> {

    public List<Function> listFuncByRoleIds(List<Integer> roleIds) {
        return baseMapper.listFuncByRoleIds(roleIds);
    }

    public Set<String> listFuncUrlByRoleIds(List<Integer> roleIds) {
        return baseMapper.listFuncUrlByRoleIds(roleIds);
    }
}