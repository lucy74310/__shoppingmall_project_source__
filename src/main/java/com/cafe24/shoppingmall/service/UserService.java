package com.cafe24.shoppingmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.repository.UserDao;
import com.cafe24.shoppingmall.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	public UserDao userDao;
	
	public UserVo join(UserVo userVo) {
		return userDao.insertMember(userVo);
	}
	
}
