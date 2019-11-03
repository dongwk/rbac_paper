package com.app.service.service;

import com.app.mapper.mapper.UserMapper;
import com.app.model.model.User;
import com.app.service.ao.GenerateTokenAo;
import com.app.service.base.BaseSimpleService;
import org.springframework.stereotype.Service;

@Service
public class AuthService extends BaseSimpleService<UserMapper, User> {

    public User login(GenerateTokenAo ctao) {
        User u = new User();
        u.setUsername(ctao.getUsername());
        u.setPassword(ctao.getPassword());

        return get(u);
    }

    public User getByUsername(String username) {
        User u = new User();
        u.setUsername(username);

        return get(u);
    }
}
