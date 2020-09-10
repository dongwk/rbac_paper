/**
 *
 */
package com.app.web.controller;

import com.app.common.util.JsonUtil;
import com.app.common.util.date.DateUtil;
import com.app.common.web.result.R;
import com.app.core.common.ThrowBiz;
import com.app.model.model.User;
import com.app.service.service.AuthService;
import com.app.service.service.UserService;
import com.app.web.config.annotation.RequiredLogin;
import com.app.web.config.annotation.LoginUser;
import com.app.web.constant.WebConstants;
import com.app.web.controller.manager.TokenManage;
import com.app.web.controller.base.BaseController;
import com.app.web.mo.LoginMo;
import com.app.web.mq.dto.LoginSucDto;
import com.app.web.mq.service.AuthMqService;
import com.app.web.utils.PasswordUtils;
import com.app.web.utils.TokenUtils;
import com.app.web.mo.LoginUserMo;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.Set;

@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthService authService;

	@Autowired
	private TokenManage tokenManage;

	@Autowired
	private AuthMqService authMqService;

	@PostConstruct
	public void init() {
		log.info("初始化测试用户 fd953b76-7e1f-41f7-9f74-8256270e1cd7");
		String token = "fd953b76-7e1f-41f7-9f74-8256270e1cd7";
		User u = authService.getByUsername("zhangsan");
		cacheTokenInfo(token, u);
	}

	@PostMapping
	public R<?> auth(@RequestBody LoginMo loginMo){

		User u = authService.getByUsername(loginMo.getUsername());

		if (u == null) ThrowBiz.throwExce("auth.account.error");
		if (StringUtils.isBlank(u.getPassword())) ThrowBiz.throwExce("auth.password.notset");
		if (!u.getPassword().equals(PasswordUtils.encrypt(loginMo.getPassword(), u.getSalt()))) ThrowBiz.throwExce("auth.account.error");

		// 生成token
		String token = TokenUtils.generateToken();

		// 缓存token和对应的用户
		cacheTokenInfo(token, u);

		// 发送登录成功消息
		authMqService.loginSuccess(new LoginSucDto(u.getId(), DateUtil.date()));

		// 返回
		return R.SUCCESS(ImmutableMap.of(
            "token", token,
            "expire", WebConstants.TOKEN_EXPIRATION,
            "ssl", WebConstants.SSL
		));
	}

	@PostMapping("/logout")
	@RequiredLogin
	public R<?> logout(@LoginUser LoginUserMo loginedUser){
	    tokenManage.del(loginedUser.getToken());
		return R.SUCCESS();
	}

	@PostMapping("/refresh")
	@RequiredLogin
	public R<?> refresh(@LoginUser LoginUserMo loginedUser){
		tokenManage.refresh(loginedUser.getToken());
		return R.SUCCESS();
	}

	/**
	 * 存储登录信息
	 * @param token 登录 token
	 * @param u 登录用户
	 */
	private void cacheTokenInfo(String token, User u){
		// 查询用户权限信息
		Set<String> funs = authService.listByUserId(u.getId());

		// 存储登录信息
		tokenManage.add(token, JsonUtil.toJson(new LoginUserMo(u.getId(), u.getNickname(), u.getUsername(), token, funs)));
	}
}