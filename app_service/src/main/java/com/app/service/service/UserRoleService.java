package com.app.service.service;

import com.app.mapper.mapper.UserRoleMapper;
import com.app.model.model.UserRole;
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
public class UserRoleService extends BaseMapperService<UserRoleMapper, UserRole> {
    public List<Integer> listRoleIdByUserId(int userId) {
        return baseMapper.listRoleIdByUserId(userId);
    }

}