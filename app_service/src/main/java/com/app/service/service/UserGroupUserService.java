/**
 * 
 */
package com.app.service.service;

import com.app.mapper.mapper.UserGroupRoleMapper;
import com.app.mapper.mapper.UserGroupUserMapper;
import com.app.model.model.UserGroupRole;
import com.app.model.model.UserGroupUser;
import com.app.service.base.BaseSimpleService;
import org.springframework.stereotype.Service;

@Service
public class UserGroupUserService extends BaseSimpleService<UserGroupUserMapper, UserGroupUser> {

}