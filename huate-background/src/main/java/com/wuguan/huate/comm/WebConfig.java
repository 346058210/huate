package com.wuguan.huate.comm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.wuguan.huate.utils.RedisHelper;
import com.wuguan.huate.web.interceptor.CorsInterceptor;
import com.wuguan.huate.web.interceptor.ParamsValidateInterceptor;
import com.wuguan.huate.web.interceptor.PermissionsValidateInterceptor;
import com.wuguan.huate.web.interceptor.TokenInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
	
	@Bean
	public RedisHelper initRedisBean(Environment environment) {
		return new RedisHelper(environment.getProperty("spring.redis.database", Integer.class));
	}
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		// 跨域拦截处理
		registry.addInterceptor(new CorsInterceptor()).addPathPatterns("/**");
		//Token校验
		registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/checkUser","/order/notify","/worker/login");
		//参数校验
		registry.addInterceptor(new ParamsValidateInterceptor()).addPathPatterns("/**");
		//权限校验
		//registry.addInterceptor(new PermissionsValidateInterceptor()).addPathPatterns("/**");
	}
}
