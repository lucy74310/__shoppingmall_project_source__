package com.cafe24.shoppingmall.controller.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.UserService;
import com.cafe24.shoppingmall.vo.UserVo;

@RestController("userAPIController")
@RequestMapping("/api/user")
public class UserController {
	
	
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/checkemail", method=RequestMethod.GET)
	public JSONResult emailCheck() {
		return JSONResult.success("emailCheck");
	}
	
	@RequestMapping(value="/join/agree", method=RequestMethod.GET)
	public String joinAgreementForm() {
		return "약관동의 페이지";
	}
	
	@RequestMapping(value="/joinform", method=RequestMethod.GET)
	public String joinForm() {
		return "회원가입 페이지";
	}
	
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public JSONResult join(
		@ModelAttribute @Valid UserVo userVo,
		BindingResult result
	) {
		
		if(result.hasErrors()) {
			return JSONResult.fail("요구사항이 모두 만족되지 않았습니다");
		} 
		
		Boolean insert = userService.join(userVo);
		
		return JSONResult.success(insert);
	}
	
	@RequestMapping(value="/loginform", method=RequestMethod.GET)
	public String loginForm() {
		return "로그인 페이지";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public JSONResult login() {
		return JSONResult.success("login");
	}
	
	@RequestMapping(value="/updateform", method=RequestMethod.GET)
	public String userInfoUpdateForm() {
		return "유저 정보 업데이트 페이지";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public JSONResult userInfoUpdate() {
		return JSONResult.success("userInfoUpdate");
	}
	
	@RequestMapping(value="/deleteform", method=RequestMethod.GET)
	public String deleteUserForm() {
		return "회원 탈퇴 페이지";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public JSONResult deleteUser() {
		return JSONResult.success("deleteUser");
	}
	
	@RequestMapping(value="/address/list", method=RequestMethod.GET)
	public String addressListPage() {
		return "배송지 목록 페이지";
	}
	
	@RequestMapping(value="/adderss/addform", method=RequestMethod.GET)
	public String addressAddPage() {
		return "배송지 추가 페이지";
	}
	
	@RequestMapping(value="/address/add", method=RequestMethod.POST)
	public JSONResult addressAdd() {
		return JSONResult.success("addressAdd");
	}
	
	@RequestMapping(value="/adderss/updateform", method=RequestMethod.GET)
	public String addressUpdatePage() {
		return "배송지 수정 페이지";
	}
	
	
	@RequestMapping(value="/address/update", method=RequestMethod.POST)
	public JSONResult addressUpdate() {
		return JSONResult.success("addressUpdate");
	}
	
	@RequestMapping(value="/address/delete", method=RequestMethod.POST)
	public JSONResult addressDelete() {
		return JSONResult.success("addressDelete");
	}
	
	
}
