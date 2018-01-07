/**
 * 
 */
package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.mapper.UserMapper;
import com.app.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public User get(long uid){
		Page<User> page = PageHelper.offsetPage(0, 10, true);
		System.out.println(page.getTotal());
		return userMapper.selectByPrimaryKey(uid);
	}
}