/**
 *
 */
package com.app.web.controller;

import com.app.common.web.result.R;
import com.app.model.model.User;
import com.app.service.ao.GenerateTokenAo;
import com.app.service.service.AuthService;
import com.app.service.service.UserService;
import com.app.web.authorization.manager.TokenGenerate;
import com.app.web.authorization.manager.TokenManage;
import com.app.web.constant.CommonConstant;
import com.app.web.controller.base.BaseController;
import com.app.web.controller.common.MessageSourceHandler;
import com.app.web.controller.exce.BizException;
import com.app.web.mo.LoginMo;
import com.app.web.utils.PasswordUtils;
import com.app.web.utils.TokenUtils;
import com.app.web.vo.LoginVo;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthService authService;

	@Autowired
	@Qualifier("memTokenManage")
	private TokenManage tokenManage;

	@Autowired
	private MessageSourceHandler messageSourceHandler;

	@PostMapping
	public R<?> auth(@Valid @RequestBody LoginMo loginMo){

		User u = authService.getByUsername(loginMo.getUsername());

		if (u == null) throw new BizException("auth.username.empty");
		if (!u.getPassword().equals(PasswordUtils.encrypt(loginMo.getPassword(), u.getSalt()))) throw new BizException("auth.account.error");

		// 生成 token
		String token = TokenUtils.generateToken();

		// 存储 token
		tokenManage.storeToken(token, CommonConstant.TOKEN_EXPIRATION);

		// 返回
		return R.SUCCESS(ImmutableMap.of(
				"token", token,
				"id", CommonConstant.TOKEN_EXPIRATION,
				"ssl", CommonConstant.SSL
		));

	}


}