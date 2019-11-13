/**
 *
 */
package com.app.web.controller;

import com.app.common.util.JsonUtil;
import com.app.common.web.result.R;
import com.app.model.model.User;
import com.app.service.service.AuthService;
import com.app.service.service.UserService;
import com.app.web.authorization.annotation.Authorization;
import com.app.web.authorization.annotation.LoginedUser;
import com.app.web.authorization.manager.TokenManage;
import com.app.web.constant.CommonConstant;
import com.app.web.controller.base.BaseController;
import com.app.web.controller.common.MessageSourceHandler;
import com.app.web.controller.exce.BizException;
import com.app.web.mo.LoginMo;
import com.app.web.utils.PasswordUtils;
import com.app.web.utils.TokenUtils;
import com.app.web.mo.LoginedUserMo;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthService authService;

	@Autowired
	private TokenManage tokenManage;

	@Autowired
	private MessageSourceHandler messageSourceHandler;

	@PostMapping
	public R<?> auth(@RequestBody LoginMo loginMo){

		User u = authService.getByUsername(loginMo.getUsername());

		if (u == null) throw new BizException("auth.account.error");
		if (StringUtils.isBlank(u.getPassword())) throw new BizException("auth.password.notset");
		if (!u.getPassword().equals(PasswordUtils.encrypt(loginMo.getPassword(), u.getSalt()))) throw new BizException("auth.account.error");

		// 生成 token
		String token = TokenUtils.generateToken();

		// 存储 token
		tokenManage.add(token, JsonUtil.toJson(new LoginedUserMo(u.getId(), u.getNickname(), u.getUsername(), token)));

		// 返回
		return R.SUCCESS(ImmutableMap.of(
            "token", token,
            "expire", CommonConstant.TOKEN_EXPIRATION,
            "ssl", CommonConstant.SSL
		));
	}

	@PostMapping("/logout")
    @Authorization
	public R<?> logout(@LoginedUser LoginedUserMo loginedUser){

	    tokenManage.del(loginedUser.getToken());

		// 返回
		return R.SUCCESS();
	}

}