/**
 * 
 */
package com.app.service.service;

import com.app.mapper.mapper.MenuMapper;
import com.app.model.model.Menu;
import com.app.service.base.BaseSimpleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dongwk
 */
@Service
public class MenuService extends BaseSimpleService<MenuMapper, Menu> {

    public List<Menu> listByUserId(Integer userId) {
       return baseMapper.listByUserId(userId);
    }
}