package com.app.service.service;

import com.app.mapper.mapper.UserMapper;
import com.app.model.model.Function;
import com.app.model.model.User;
import com.app.service.ao.GenerateTokenAo;
import com.app.service.base.BaseMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthService extends BaseMapperService<UserMapper, User> {

    @Autowired
    private FunctionService functionService;
    @Autowired
    private MenuFunctionService menuFunctionService;
    @Autowired
    private ElementFunctionService elementFunctionService;
    @Autowired
    private RoleFunctionService roleFunctionService;
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 登录
     * @param ctao
     * @return
     */
    public User login(GenerateTokenAo ctao) {
        User u = new User();
        u.setUsername(ctao.getUsername());
        u.setPassword(ctao.getPassword());

        return get(u);
    }

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    public User getByUsername(String username) {
        User u = new User();
        u.setUsername(username);

        return get(u);
    }

    /**
     * 根据用户ID查询 URL 功能表
     * @param userId
     * @return
     */
    public Set<String> listByUserId(int userId) {

//        private MenuFunctionService menuFunctionService;
//        private ElementFunctionService elementFunctionService;
//        private RoleFunctionService roleFunctionService;

        List<Integer> roleIds = userRoleService.listRoleIdByUserId(userId);

        Set<String> set1 = roleFunctionService.listFuncUrlByRoleIds(roleIds);
        Set<String> set2 = elementFunctionService.listFuncUrlByRoleIds(roleIds);

        List<Function> list1 = roleFunctionService.listFuncByRoleIds(roleIds);
        List<Function> list2 = elementFunctionService.listFuncByRoleIds(roleIds);

        set1.addAll(set2);

        return set1;
    }
}
