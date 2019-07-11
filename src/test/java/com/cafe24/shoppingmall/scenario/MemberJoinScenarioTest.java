package com.cafe24.shoppingmall.scenario;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shoppingmall.config.AppConfig;
import com.cafe24.shoppingmall.config.TestWebConfig;

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
			.andExpect(content().string("\"user/agreement\""));
		    
			//assertThat(result.getResponse().getContentAsString(), is("\"약관동의 페이지\""));
		
		// 3. 회원 약관 동의
		// 약관에 동의하지 않으면 약관동의 페이지 반환
		mockMvc.perform(get("/api/user/agreecheck").param("agree", "false"))
						.andExpect(status().is3xxRedirection())
						.andDo(print())
						.andExpect(redirectedUrl("/api/user/join/agree"));
						
		
		// 약관에 동의하면 회원가입 페이지 반환
		// 4. 회원정보 입력 페이지 
		mockMvc.perform(get("/api/user/agreecheck").param("agree", "true"))
				.andExpect(status().is3xxRedirection())
				.andDo(print())
				.andExpect(redirectedUrl("/api/user/joinform"));
		

		// 5. 아이디 중복 체크 요청 & 6. 아이디 중복 조회 & 7. 아이디 중복 여부 결과 8. 아이디 중복 시 재시도
		
		// 중복되는 ID가 없을 때까지 반복 
		// ID 중복될 때 
		mockMvc.perform(get("/api/user/checkemail").param("email", "lucy74310@gmail.com"))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.result", is("success")))
				.andExpect(jsonPath("$.data", is(true)));
		// ID 중복 없을 때까지 반복 요청 
		
		// ID 중복되지 않을 때 
		mockMvc.perform(get("/api/user/checkemail").param("email", "test@gmail.com"))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data", is(false)));
		 

		
		// 9. 회원 등록 요청 &  10. 입력 정보 유효성 검사
		
		// 필수 데이터를 다 넣지 않았을 때 (validation 통과 실패 시나리오 )
		Map<String, String> userNotValid = new HashMap<String, String>();
		userNotValid.put("id", "test");
		userNotValid.put("name", "user1");
		userNotValid.put("password", "1234");
		userNotValid.put("telephone", "010-1111-2222");
		userNotValid.put("gender", "female");
		userNotValid.put("birthday", "1999-07-10");
		
		ResultActions resultAction = 
				mockMvc.perform(post("/api/user/join").param("id", userNotValid.get("id"))
						.param("name", userNotValid.get("name"))
						.param("password", userNotValid.get("password"))
						.param("telephone", userNotValid.get("telephone"))
						.param("gender", userNotValid.get("gender"))
						.param("birthday", userNotValid.get("birthday"))
						);
		
		resultAction
		.andExpect(status().isBadRequest())
		.andDo(print())
		.andExpect(jsonPath("$.result", is("fail")))
		.andExpect(jsonPath("$.data[0].defaultMessage", is("may not be empty")));
		// 다시 회원가임폼 리턴 (생략)
		
		
		// 필수 데이터를 다 넣었을 때 (validation 통과 성공 시나리오 )
		Map<String, String> userValid = new HashMap<String, String>();
		userValid.put("id", "test");
		userValid.put("name", "user1");
		userValid.put("password", "1234");
		userValid.put("telephone", "010-1111-2222");
		userValid.put("email", "test@gmail.com");
		userValid.put("gender", "female");
		userValid.put("birthday", "1999-07-10");
		
		// 필수 데이터 다 넣었으므로  11. 회원 등록 요청 & 12. 회원정보 저장 
		resultAction = mockMvc.perform(post("/api/user/join").param("id", userValid.get("id"))
						.param("name", userValid.get("name"))
						.param("password", userValid.get("password"))
						.param("telephone", userValid.get("telephone"))
						.param("email", userValid.get("email"))
						.param("gender", userValid.get("gender"))
						.param("birthday", userValid.get("birthday"))
						);
		// 13. 회원 가입 성공시 회원 가입 완료 페이지 반환
		resultAction
			.andExpect(status().is3xxRedirection())
			.andDo(print())
			.andExpect(redirectedUrl("/api/user/join/success"))
			.andExpect(jsonPath("$.result", is("success")))
			.andExpect(jsonPath("$.data.no", is(notNullValue())));
		
		
		

		// json to object 할때 참고 
		// MvcResult result = resultAction.andReturn();
		// String contentAsString = result.getResponse().getContentAsString();
		// JSONResult jsonResult = new ObjectMapper().readValue(contentAsString, JSONResult.class);
		
	}
}
