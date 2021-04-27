package com.app.service.service;

import com.app.mapper.mapper.RoleFunctionMapper;
import com.app.model.model.Function;
import com.app.model.model.RoleFunction;
import com.app.service.base.BaseMapperService;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 *
 * @author dongwk
 * @date 2021-03-17
 * @version 1.0
 */
@Service
public class RoleFunctionService extends BaseMapperService<RoleFunctionMapper, RoleFunction> {

    public List<Function> listFuncByRoleIds(List<Integer> roleIds) {
        return baseMapper.listFuncByRoleIds(roleIds);
    }

    public Set<String> listFuncUrlByRoleIds(List<Integer> roleIds) {
        if (roleIds != null && roleIds.size() > 0) return baseMapper.listFuncUrlByRoleIds(roleIds);
        return Sets.newHashSet();
    }
}