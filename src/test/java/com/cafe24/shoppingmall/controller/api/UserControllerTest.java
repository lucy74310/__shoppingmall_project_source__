package com.cafe24.shoppingmall.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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
public class UserControllerTest {
	
	
	private MockMvc mockMvc;
	
	
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	/*
	 * 페이지 반환 테스트 : joinAgreeForm, deleteForm, updateForm, 
	 * loginForm, joinFormm, addressAddForm, addressUpdateForm
	 * 
	 * */
	
	 
	@Test
	public void testJoinAgreeForm() throws Exception {
		ResultActions resultActions = 
				mockMvc.perform(get("/api/user/join/agree"));
		
		resultActions
			.andExpect(status().isOk())
			.andDo(print());
	}
	
	@Test
	public void testJoinForm() throws Exception {
		ResultActions resultActions = 
				mockMvc.perform(get("/api/user/joinform"));
		
		resultActions
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void testLoginForm() throws Exception {
		ResultActions resultActions = 
				mockMvc.perform(get("/api/user/loginform"));
		
		resultActions
			.andExpect(status().isOk())
			.andDo(print());
	}
	
	
	
	@Test
	public void testUpdateForm() throws Exception {
		ResultActions resultActions = 
				mockMvc.perform(get("/api/user/updateform"));
		
		resultActions
			.andExpect(status().isOk())
			.andDo(print());
	}
	
	
	@Test
	public void testDeleteForm() throws Exception {
		ResultActions resultActions = 
				mockMvc.perform(get("/api/user/deleteform"));
		
		resultActions
			.andExpect(status().isOk())
			.andDo(print());
	}
	@Test
	public void testAddressList() throws Exception {
		ResultActions resultActions = 
				mockMvc.perform(get("/api/user/address/list"));
		
		resultActions
			.andExpect(status().isOk())
			.andDo(print());
	}
	
	@Test
	public void testAddressAddForm() throws Exception {
		ResultActions resultActions = 
				mockMvc.perform(get("/api/user/address/addform"));
		resultActions
			.andExpect(status().isOk())
			.andDo(print());
	}
	
	
	@Test
	public void testAddressUpdateForm() throws Exception {
		ResultActions resultActions = 
				mockMvc.perform(get("/api/user/address/updateform"));
		
		resultActions
			.andExpect(status().isOk())
			.andDo(print());
	}
	
	
	/*
	 * 페이지 반환 테스트 끝 
	 */
	
	
	
	
	
}
