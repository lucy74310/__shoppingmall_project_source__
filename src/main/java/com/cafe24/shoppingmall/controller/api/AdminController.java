package com.cafe24.shoppingmall.controller.api;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;

@RestController("adminAPIContorller")
@RequestMapping("/api/admin")
public class AdminController {
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginForm() {
		
		String view = "admin/login";
		
		return view;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public JSONResult login(
			@RequestParam("id") String id,
			@RequestParam("password") String password,
			HttpServletResponse response
	) throws IOException {
		
		if("admin".equals(id)) {
			if("1234".equals(password)) {
				response.sendRedirect("/api/admin/home");
				return JSONResult.success(true);
			} else {
				return JSONResult.fail("비밀번호가 틀렸습니다.", false);
			}
			
		} else {
			return JSONResult.fail("아이디가 틀렸습니다.", false);
		}
		
	}
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String adminHome() {
		
		String view = "admin/index";
		return view;
	}
	
}
