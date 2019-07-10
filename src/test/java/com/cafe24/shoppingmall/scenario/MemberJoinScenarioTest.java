package com.cafe24.shoppingmall.scenario;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shoppingmall.config.AppConfig;
import com.cafe24.shoppingmall.config.TestWebConfig;
import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.vo.UserVo;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class MemberJoinScenarioTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before 
	public void setUp() {
		mockMvc = MockMvcBuilders.
				webAppContextSetup(webApplicationContext).
				build();
	}
	
	/*
	 *  1. 회원 가입 요청
	 *  2. 회원 약관 페이지 
	 *  3. 회원 약관 동의 
	 *  4. 회원정보 입력 페이지 
	 *  5. 아이디 중복 체크 요청 
	 *  6. 아이디 중복 조회
	 *  7. 아이디 중복 여부 결과
	 *  8. 아이디 중복 시 재시도 
	 *  9. 회원 등록 요청 
	 *  10. 입력 정보 유효성 검사 
	 *  11. 회원 등록 요청
	 *  12. 회원 정보 저장 
	 *  13. 회원 가입 완료 페이지 반환
	 */
	
	@Test
	public void memberJoinScenarioTest() throws Exception{
		
		
		// 1. 회원 가입 요청  
		mockMvc.perform(get("/api/user/join/agree"))
			.andExpect(status().isOk())
			.andDo(print())
			// 2. 회원 약관 페이지
			.andExpect(content().string("\"약관동의 페이지\""))
		    .andReturn();
			//assertThat(result.getResponse().getContentAsString(), is("\"약관동의 페이지\""));
		
		// 3. 회원 약관 동의
		// 약관에 동의하지 않으면 약관동의 페이지 반환
		mockMvc.perform(get("/api/user/joinform").param("agree", "false"))
						.andExpect(status().isOk())
						.andDo(print())
						.andExpect(content().string("\"약관동의 페이지\""))
						.andReturn();
				
		
		// 약관에 동의하면 회원가입 페이지 반환 & 4. 회원정보 입력 페이지 
		mockMvc.perform(get("/api/user/joinform").param("agree", "true"))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(content().string("\"회원가입 페이지\""))
				.andReturn();
		

		// 5. 아이디 중복 체크 요청 & 6. 아이디 중복 조회 & 7. 아이디 중복 여부 결과 8. 아이디 중복 시 재시도
		// 중복되는 ID가 없을 때까지 반복 
		// ID 중복될 때 
		mockMvc.perform(get("/api/user/checkemail").param("email", "lucy74310@gmail.com"))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(true)));
		
		// ID 중복되지 않을 때 
		mockMvc.perform(get("/api/user/checkemail").param("email", "test@gmail.com"))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(false)));
		 

		
		// 9. 회원 등록 요청
		HashMap<String, String> user = new HashMap<String, String>();
		user.put("id", "test");
		user.put("name", "user1");
		user.put("password", "1234");
		user.put("telephone", "010-1111-2222");
		user.put("email", "test@gmail.com");
		user.put("gender", "female");
		user.put("birthday", "1999-07-10");
		ResultActions resultAction = 
				mockMvc.perform(post("/api/user/join").param("id", user.get("id"))
						.param("name", user.get("name"))
						.param("password", user.get("password"))
						.param("telephone", user.get("telephone"))
						.param("email", user.get("email"))
						.param("gender", user.get("gender"))
						.param("birthday", user.get("birthday"))
						);
		
		
		// 회원이 주소를 입력했을 경우 - 주소 삽입 (기본배송지로 설정)
		resultAction
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data.id", is(user.get("id"))))
			.andExpect(jsonPath("$.data.name", is(user.get("name"))))
			.andExpect(jsonPath("$.data.password", is(user.get("password"))))
			.andExpect(jsonPath("$.data.telephone", is(user.get("telephone"))))
			.andExpect(jsonPath("$.data.email", is(user.get("email"))))
			.andExpect(jsonPath("$.data.gender", is(user.get("gender"))))
			.andExpect(jsonPath("$.data.birthday", is(user.get("birthday"))))
			;
		
		MvcResult result = resultAction.andReturn();
		String contentAsString = result.getResponse().getContentAsString();
		System.out.println(contentAsString);
		JSONResult jsonResult = new ObjectMapper().readValue(contentAsString, JSONResult.class);
		System.out.println(jsonResult.toString());
		
		
		
		
		
			
		
	}
}
