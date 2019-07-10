package com.cafe24.config.web;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class MessageConfig {
	
	//
	// 한글패치... 
	//
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("config/web/messages/message_ko");
		messageSource.setDefaultEncoding("UTF-8");
		
		
		return messageSource;
	}
}
