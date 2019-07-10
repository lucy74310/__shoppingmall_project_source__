package com.cafe24.shoppingmall.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insertMember(UserVo userVo) {
		Boolean result = 1 == sqlSession.insert("user.insert", userVo);
		System.out.println(userVo);
		return result;
	}
	
}
