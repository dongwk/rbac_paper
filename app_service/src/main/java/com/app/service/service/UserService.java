/**
 * 
 */
package com.app.service.service;

import com.app.mapper.mapper.UserMapper;
import com.app.model.model.User;
import com.app.service.base.MapperSupportService;
import org.springframework.stereotype.Service;

@Service
public class UserService extends MapperSupportService<UserMapper, User> {

    public User login(String username, String password) {
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        return get(u);
    }


}