package com.cafe24.shoppingmall.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;

@RestController("adminAPIContorller")
@RequestMapping("/api/admin")
public class AdminController {
	
	@RequestMapping(value="/loginform", method=RequestMethod.GET)
	public String loginForm() {
		return "관리자 로그인 페이지";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public JSONResult login() {
		return JSONResult.success("Admin Login");
	}
	
	
}
