package com.wuguan.huate.comm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.wuguan.huate.utils.RedisHelper;
import com.wuguan.huate.web.interceptor.CorsInterceptor;
import com.wuguan.huate.web.interceptor.ParamsValidateInterceptor;
import com.wuguan.huate.web.interceptor.TokenInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

	@Value("${file.upload.path}")
	private String path;

	@Bean
	public RedisHelper initRedisBean(Environment environment) {
		return new RedisHelper(environment.getProperty("spring.redis.database", Integer.class));
	}

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		// 跨域拦截处理
		registry.addInterceptor(new CorsInterceptor()).addPathPatterns("/**");
		// Token校验
		registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**")
			.excludePathPatterns("/worker/registerLogin","/book/pageData","/role/queryRoles","/role/queryHandler","/image/**","/queryWxTml");
		// 参数校验
		registry.addInterceptor(new ParamsValidateInterceptor()).addPathPatterns("/**");
	}

	// 静态资源路径从定向
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/image/**").addResourceLocations("file:" + path);
	}
}
