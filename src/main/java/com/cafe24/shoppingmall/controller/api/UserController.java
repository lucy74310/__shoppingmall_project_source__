package com.cafe24.shoppingmall.controller.api;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.UserService;
import com.cafe24.shoppingmall.vo.UserVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController("userAPIController")
@RequestMapping("/api/user")
public class UserController {
	
	
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value="이메일 존재 여부")
    @ApiImplicitParam(name="email", value="이메일주소", required = true, dataType="string")
	@RequestMapping(value="/checkemail", method=RequestMethod.GET)
	public JSONResult emailCheck(
			@RequestParam(value="email", required=true, defaultValue="") String email
	) {
		
		if("lucy74310@gmail.com".equals(email)) {
			// 이메일 존재 
			return JSONResult.success(true); 
		} else {
			// DB에 존재하는 이메일과 다름
			return JSONResult.success(false);
		}
		
	}
	
	
	@ApiImplicitParam(name="agree", value="동의여부", required = true, dataType="Boolean")
	@RequestMapping(value="/agreecheck", method=RequestMethod.GET)
	public void joinForm(@RequestParam(value="agree", required=true) Boolean agree,
			HttpServletResponse response) throws IOException {
		
		if(false == agree) {
			// 약관에 동의하지 않았으므로 다시 약관페이지 
			response.sendRedirect("/api/user/join/agree");
		} else {
			// 약관동의완료, 회원가입페이지로 리다이렉트;
			response.sendRedirect("/api/user/joinform");
		}
	}
	
	
	@ApiModelProperty(required = true, value = "id")
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public JSONResult join(
		@ModelAttribute @Valid UserVo userVo,
		BindingResult result,
		HttpServletResponse response
	) throws IOException {
		System.out.println(userVo.toString());
		/**
		 * 200 ok
		 * {
		 * 		reault : "fail",
		 * 		message: "이메일 형식이 맞지 않습니다",
		 * 		data: null
		 * }
		 * 
		 * --이게 restful 한 응답임 
		 * 400 bad request
		 * {
		 * 		reault : "fail",
		 * 		message: "이메일 형식이 맞지 않습니다",
		 * 		data: null
		 * }
		 * */
	
		if(result.hasErrors()) {
			response.setStatus(400);
			System.out.println(result.getAllErrors().toString());
			// 
			return JSONResult.fail("필수 요구사항이 만족되지 않았습니다.",result.getAllErrors());
		}
		UserVo insertVo = userService.join(userVo);
		if(null != userVo.getEmail()) {
			//no 넣어서 배송지 저장 
		} 
		
		response.sendRedirect("/api/user/join/success");
		return JSONResult.success(insertVo);
		
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public JSONResult login() {
		return JSONResult.success("login");
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public JSONResult userInfoUpdate() {
		return JSONResult.success("userInfoUpdate");
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public JSONResult deleteUser() {
		return JSONResult.success("deleteUser");
	}
	
	
	@RequestMapping(value="/address/add", method=RequestMethod.POST)
	public JSONResult addressAdd() {
		return JSONResult.success("addressAdd");
	}
	
	@RequestMapping(value="/address/update", method=RequestMethod.POST)
	public JSONResult addressUpdate() {
		return JSONResult.success("addressUpdate");
	}
	
	@RequestMapping(value="/address/delete", method=RequestMethod.POST)
	public JSONResult addressDelete() {
		return JSONResult.success("addressDelete");
	}
	
	
	
	
	
	
	
	
	
	
	@ApiOperation(value="약관동의페이지 요청")
	@RequestMapping(value="/join/agree", method=RequestMethod.GET)
	public String joinAgreementForm() {
		
		String view ="user/agreement";
		return view;
	}
	
	@RequestMapping(value="/joinform", method=RequestMethod.GET)
	public String joinForm() {
		return "회원가입 페이지";
	}
	
	@RequestMapping(value="/join/success", method=RequestMethod.GET)
	public String joinSuccess() {
		
		String view = "user/joinsuccess";
		return view;
	}
	
	
	@RequestMapping(value="/loginform", method=RequestMethod.GET)
	public String loginForm() {
		return "로그인 페이지";
	}
	
	@RequestMapping(value="/updateform", method=RequestMethod.GET)
	public String userInfoUpdateForm() {
		return "유저 정보 업데이트 페이지";
	}

	
	@RequestMapping(value="/deleteform", method=RequestMethod.GET)
	public String deleteUserForm() {
		return "회원 탈퇴 페이지";
	}
	
	
	@RequestMapping(value="/address/list", method=RequestMethod.GET)
	public String addressListPage() {
		return "배송지 목록 페이지";
	}
	
	@RequestMapping(value="/address/addform", method=RequestMethod.GET)
	public String addressAddPage() {
		return "배송지 추가 페이지";
	}
	
	
	@RequestMapping(value="/address/updateform", method=RequestMethod.GET)
	public String addressUpdatePage() {
		return "배송지 수정 페이지";
	}
	
	

	
	
}
