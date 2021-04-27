package com.app.service.service;

import com.app.mapper.mapper.UserMapper;
import com.app.model.model.User;
import com.app.service.base.BaseMapperService;
import org.springframework.stereotype.Service;

/**
 *
 * @author dongwk
 * @date 2021-03-17
 * @version 1.0
 */
@Service
public class UserService extends BaseMapperService<UserMapper, User> {

    public User login(String username, String password) {
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        return get(u);
    }
}