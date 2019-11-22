/**
 * 
 */
package com.app.service.service;

import com.app.mapper.mapper.UserRoleMapper;
import com.app.model.model.UserRole;
import com.app.service.base.BaseSimpleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService extends BaseSimpleService<UserRoleMapper, UserRole> {

    public List<Integer> listRoleIdByUserId(int userId) {
        return baseMapper.listRoleIdByUserId(userId);
    }
}