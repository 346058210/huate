/**   
* @Title: PermissionsValidateInterceptor.java 
* @Package com.kedalo.base_service.web.interceptor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2019年10月23日 下午6:42:43 
* @version V1.0   
*/
package com.wuguan.huate.web.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSONObject;
import com.wuguan.huate.annotation.Function;
import com.wuguan.huate.bean.entity.Resources;
import com.wuguan.huate.bean.vo.LoginUser;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.comm.SpringUtil;
import com.wuguan.huate.comm.ThreadLocalManager;
import com.wuguan.huate.web.result.ResultEnums;

/**
 * @ClassName: PermissionsValidateInterceptor
 * @Description: 权限验证
 * @author LiuYanHong
 * @date 2019年10月23日 下午6:42:43
 * 
 */
public class PermissionsValidateInterceptor implements HandlerInterceptor {

	Environment environment;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		environment = SpringUtil.getBean(Environment.class);
		if (environment.getProperty("dev_model").equals("dev")) {
			return true;
		}
		//if (request.getHeader("source").equals("mobile"))
		//	return true;
		// 开发模式放行
		if (environment.getProperty("dev_model").equals("dev"))
			return true;
		// 根据请求路径，查找该路径是否配置权限位
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		if (!method.isAnnotationPresent(Function.class))
			return true;
		// 获取该路径的key
		Function function = method.getAnnotation(Function.class);
		String validateKey = function.key();
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		LoginUser user = JSONObject.parseObject(bean.getUser().toString(), LoginUser.class);
		boolean exists = false;
		for (Resources res : user.getResources()) {
			String resKey = res.getResKey();
			if (validateKey.equals(resKey)) {
				exists = true;
				break;
			}
		}
		if (!exists) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "当前用户目前尚未开通过该权限");
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
