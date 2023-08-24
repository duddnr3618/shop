package com.mysite.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 파일 저장 path 설정
public class WebMvcConfig implements WebMvcConfigurer {

	@Value(value="${uploadPath}")
	private String uploadPath ;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/image/**")
			.addResourceLocations(uploadPath);
	}
	
	

}
