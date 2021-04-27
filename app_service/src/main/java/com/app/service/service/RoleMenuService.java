package com.app.service.service;

import com.app.mapper.mapper.RoleMenuMapper;
import com.app.model.model.RoleMenu;
import com.app.service.base.BaseMapperService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author dongwk
 * @date 2021-03-17
 * @version 1.0
 */
@Service
public class RoleMenuService extends BaseMapperService<RoleMenuMapper, RoleMenu> {
    public List<Integer> listMenuIdByRoleIds(List<Integer> roleIds) {
        return baseMapper.listMenuIdByRoleIds(roleIds);
    }

}