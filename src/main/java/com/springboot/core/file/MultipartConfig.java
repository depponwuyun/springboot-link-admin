package com.springboot.core.file;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultipartConfig {

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// 单个文件最大
		factory.setMaxFileSize("204800KB"); // 200MB
		// / 设置总上传数据总大小
		factory.setMaxRequestSize("204800KB");// 200MB
		return factory.createMultipartConfig();
	}
}