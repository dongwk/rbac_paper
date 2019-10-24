/**
 *
 */
package com.app.web.controller;

import com.app.common.web.result.R;
import com.app.model.model.User;
import com.app.service.ao.GenerateTokenAo;
import com.app.service.dto.GenerateTokenDto;
import com.app.service.service.AuthService;
import com.app.service.service.UserService;
import com.app.web.constant.CommonConstant;
import com.app.web.controller.base.BaseController;
import com.app.web.controller.common.TokenHandler;
import com.app.web.mo.LoginMo;
import com.app.web.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {

	@Autowired
	private TokenHandler tokenHandler;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthService authService;



	@PostMapping
	public R<?> auth(@RequestBody LoginMo loginMo){
		User u = authService.login(
				GenerateTokenAo.builder()
				.username(loginMo.getUsername())
				.password(loginMo.getPassword())
				.build()
		);

		if (u != null) return R.SUCCESS(
				LoginVo.builder()
				.token(UUID.randomUUID().toString())
				.expires(CommonConstant.TOKEN_EXPIRATION)
				.ssl(CommonConstant.SSL)
				.build());


		return null;
	}


}