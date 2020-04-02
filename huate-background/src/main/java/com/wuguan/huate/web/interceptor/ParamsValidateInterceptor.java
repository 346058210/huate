/**   
* @Title: ParamsValidateInterceptor.java 
* @Package com.kedale.yaan.web.interceptor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2019年8月20日 下午4:15:17 
* @version V1.0   
*/
package com.wuguan.huate.web.interceptor;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wuguan.huate.annotation.Param;
import com.wuguan.huate.annotation.ParamType;
import com.wuguan.huate.annotation.ParamsValidate;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.utils.RegexUtils;
import com.wuguan.huate.web.result.ResultEnums;


/** 
* @ClassName: ParamsValidateInterceptor 
* @Description: 参数校验
* @author LiuYanHong
* @date 2019年8月20日 下午4:15:17 
*  
*/
public class ParamsValidateInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (!(handler instanceof HandlerMethod))
			return true;
		//获取方法注解
		final HandlerMethod handlerMethod=(HandlerMethod)handler;
		final Method method = handlerMethod.getMethod();
		boolean exists = method.isAnnotationPresent(ParamsValidate.class);
		if (!exists)
			return true;
		ParamsValidate paramsValidate = method.getAnnotation(ParamsValidate.class);
		Param[] params = paramsValidate.validateParams();
		if (params == null || params.length == 0)
			return true;
		for (Param param : params) {
			if (param == null)
				continue;
			String key = param.key();
			String existsKey = request.getParameter(key);
			//判断为空或者空字符
			if (existsKey == null || existsKey.trim().length() == 0) {
				throw new CustomException(ResultEnums.BUSINESS.getCode(), String.format("%s不能为null或空字符！！", key));
			}
			//判断长度
			String limit = param.limit();
			if (limit.length()!=0) 
				if (!existsKey.matches("^.{" + limit + "}")) {
					throw new CustomException(ResultEnums.BUSINESS.getCode(), "请确认该输入符合长度限制,正确长度限制为[" + limit + "]");
				}
			//判断参数类型
			ParamType type = param.type();
			switch (type) {
			case NUMBER:
				if (!RegexUtils.isNumber(existsKey)) {
					throw new CustomException(ResultEnums.BUSINESS.getCode(), "请确认该输入为纯数字");
				}
				break;
			case ENGLISH_NUMBER:
				if (!RegexUtils.isEnglishNumber(existsKey)) {
					throw new CustomException(ResultEnums.BUSINESS.getCode(), "请确认该输入为纯英文、纯数字或英文数字混合");
				}
				break;
			case STRING:
				if (RegexUtils.isContainSpecial(existsKey)) {
					throw new CustomException(ResultEnums.BUSINESS.getCode(), "该输入含有特殊字符");
				}
				break;
			case NAME:
				if (!RegexUtils.isName(existsKey)) {
					throw new CustomException(ResultEnums.BUSINESS.getCode(), "请确认该输入为中文姓名或英文姓名");
				}
				break;
			case EMAIL:
				if (!RegexUtils.isEmail(existsKey)) {
					throw new CustomException(ResultEnums.BUSINESS.getCode(), "请确认该输入为正确的邮箱地址格式");
				}
				break;
			default:
				break;
			}
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
