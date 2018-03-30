/**
 * 
 */
package com.app.service.service;

import com.app.mapper.mapper.UserMapper;
import com.app.model.model.User;
import com.app.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseServiceImpl<UserMapper, User> {

    @Autowired
    private UserMapper userMapper;

    public User get(long id){
        return userMapper.selectById(id);
    }
}