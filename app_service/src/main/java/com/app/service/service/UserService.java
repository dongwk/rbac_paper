/**
 * 
 */
package com.app.service.service;

import com.app.mapper.mapper.UserMapper;
import com.app.model.model.User;
import com.app.service.base.BaseSimpleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseSimpleService<UserMapper, User> {

    public User login(String username, String password) {
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        return get(u);
    }

    public List<User> selectAll(){
        return baseMapper.selectAll();
    }
}