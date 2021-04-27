package com.app.service.service;

import com.app.mapper.mapper.ElementFunctionMapper;
import com.app.model.model.ElementFunction;
import com.app.model.model.Function;
import com.app.service.base.BaseMapperService;
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
public class ElementFunctionService extends BaseMapperService<ElementFunctionMapper, ElementFunction> {

    public List<Function> listFuncByRoleIds(List<Integer> roleIds) {
        return baseMapper.listFuncByRoleIds(roleIds);
    }

    public Set<String> listFuncUrlByRoleIds(List<Integer> roleIds) {
        return baseMapper.listFuncUrlByRoleIds(roleIds);
    }
}