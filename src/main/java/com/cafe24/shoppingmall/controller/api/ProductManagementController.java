package com.cafe24.shoppingmall.controller.api;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.vo.ProductVo;

@RestController("productManagementAPIController")
@RequestMapping("/api/manage/product")
public class ProductManagementController {
	
	@RequestMapping(value="/add", method=RequestMethod.PUT)
	public JSONResult productAdd(
			@RequestBody @Valid ProductVo productVo,
			BindingResult result,
			HttpServletResponse response) throws IOException {
		
		if(result.hasErrors()) {
			//에러! 
			response.setStatus(400);
			return JSONResult.fail("필수 입력 항목을 입력해 주세요.", result.getAllErrors());
		} else {
			//에러없음~
			// insert 하고 no 값을 받아온다 ~
			
			//insert 성공시 
			productVo.setNo(1L);
			response.sendRedirect("/api/manage/product/list");
			return JSONResult.success(productVo);
		}
	}
	
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String productAddForm() {
		
		String view = "productManage/addForm";
		
		return view;
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String productList() {
		
		String view = "productManage/list";
		
		return view;
	}
	
	
	
	
}
