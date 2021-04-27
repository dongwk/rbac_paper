package com.app.service.service;

import com.app.mapper.mapper.MenuMapper;
import com.app.model.model.Menu;
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
public class MenuService extends BaseMapperService<MenuMapper, Menu> {

    public List<Menu> listByUserId(Integer userId) {
        return baseMapper.listByUserId(userId);
    }

    public List<Menu> listMenuByUserId(Integer userId) {
        return baseMapper.listMenuByUserId(userId);
    }

    public List<Menu> listMenuByMenuIds(List<Integer> roleIds) {
        return baseMapper.listByRoleIds(roleIds);
    }

}