package com.hcs.config;

import com.hcs.web.login.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new LoginCheckInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns(
						List.of("/", "/home","/login","/logout","/promotions/**","/coupons",
								"/css","/*.ico","**/img/**","/error","/actuator/**")
				);
	}
}
