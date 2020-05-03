package com.wuguan.huate.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.wuguan.huate.bean.vo.UserVo;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.comm.SpringUtil;
import com.wuguan.huate.comm.ThreadLocalManager;
import com.wuguan.huate.utils.RedisHelper;
import com.wuguan.huate.web.result.ResultEnums;

public class TokenInterceptor implements HandlerInterceptor {

	Environment environment;
	RedisHelper redisHelper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		System.err.println(requestURI);
		environment = SpringUtil.getBean(Environment.class);
		redisHelper = SpringUtil.getBean(RedisHelper.class);
		ThreadLocalManager manager = SpringUtil.getBean(ThreadLocalManager.class);
		if (environment.getProperty("dev_model").equals("dev")) {
			manager.setUser(JSONObject.toJSON(new UserVo()));
			return true;
		}
		// 验证token
		String token = request.getHeader("Authorization");
		if (token == null)
			throw new CustomException(ResultEnums.UNAUTHORIZED.getCode(), "token令牌缺失");
		Boolean bool = redisHelper.existsKey(token);
		if (!bool)
			throw new CustomException(ResultEnums.UNAUTHORIZED.getCode(), "token令牌已失效");
		String str = redisHelper.get(token);
		if (str==null)
			throw new CustomException(ResultEnums.UNAUTHORIZED.getCode(), "该令牌不合法");
		manager.setUser(str);
		redisHelper.set(token, redisHelper.get(token), 60*60*24);
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
