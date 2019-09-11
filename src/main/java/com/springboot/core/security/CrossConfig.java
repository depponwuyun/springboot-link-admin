package com.springboot.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
/**
 * 跨域配置
* @ClassName: CrossConfig 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author link
* @date 2019年9月7日 上午9:32:35 
*
 */
@Configuration
public class CrossConfig {

  @Bean
  public CorsConfiguration corsConfiguration() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    //实际请求中允许携带的首部字段
    corsConfiguration.addAllowedHeader("*");
    //允许那些域跨域访问
    corsConfiguration.addAllowedOrigin("*");
    //允许那些请求方法
    corsConfiguration.addAllowedMethod("*");
    return corsConfiguration;
  }

  @Bean
  public CorsFilter corsFilter(CorsConfiguration corsConfiguration) {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfiguration); // 4
    return new CorsFilter(source);
  }

}
