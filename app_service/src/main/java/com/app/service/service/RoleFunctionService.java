/**
 * 
 */
package com.app.service.service;

import com.app.mapper.mapper.RoleFunctionMapper;
import com.app.mapper.mapper.RoleMenuMapper;
import com.app.model.model.Function;
import com.app.model.model.RoleFunction;
import com.app.model.model.RoleMenu;
import com.app.service.base.BaseSimpleService;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleFunctionService extends BaseSimpleService<RoleFunctionMapper, RoleFunction> {

    public List<Function> listFuncByRoleIds(List<Integer> roleIds) {
        return baseMapper.listFuncByRoleIds(roleIds);
    }

    public Set<String> listFuncUrlByRoleIds(List<Integer> roleIds) {
        if (roleIds != null && roleIds.size() > 0) return baseMapper.listFuncUrlByRoleIds(roleIds);
        return Sets.newHashSet();
    }

}