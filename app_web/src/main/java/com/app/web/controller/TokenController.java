package com.app.web.controller;

import com.app.common.web.result.R;
import com.app.model.model.User;
import com.app.service.service.UserService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.manager.TokenManage;
import com.app.web.mo.LoginUserMo;
import com.app.web.vo.LoginVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * 获取和删除token的请求地址，在Restful设计中其实就对应着登录和退出登录的资源映射
 * @author ScienJus
 * @date 2015/7/30.
 */
@RestController
@RequestMapping("/token")
public class TokenController {

    private TokenManage tokenManage;

    @Autowired
    private UserService userService;

    @PostMapping
    @ApiOperation(value = "登录")
    public R<LoginVo> login(@RequestParam String username, @RequestParam String password) {
        Assert.notNull(username, "username can not be empty");
        User user = userService.login(username, password);
        if (user == null) {  //密码错误
            return R.MODEL(HttpStatus.UNAUTHORIZED);
        }

        //生成一个token，保存用户登录状态
//        TokenModel model = tokenManager.createToken(user.getId());
//        TokenModel model = new TokenModel(1,"lisi");

        return null;
    }

    @DeleteMapping
    @Authorization
    @ApiOperation(value = "退出登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header"),
    })
    public R logout(@LoginUser LoginUserMo userVo) {
//        tokenManager.deleteToken();
        System.out.println(userVo);
        return R.SUCCESS();
    }

}