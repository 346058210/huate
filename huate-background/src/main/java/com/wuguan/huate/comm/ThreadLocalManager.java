/**   
* @Title: ThreadLocalManager.java 
* @Package com.kedale.data.comm 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2019年9月24日 下午12:36:54 
* @version V1.0   
*/
package com.wuguan.huate.comm;

import java.util.Map;

import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ThreadLocalManager
 * @Description: 请求参数-全局变量
 * @author LiuYanHong
 * @date 2019年9月24日 下午12:36:54
 * 
 */
@Component
public class ThreadLocalManager {

	private NamedThreadLocal<Object> UserThreadLocal = new NamedThreadLocal<>("CurrenUser");

	private NamedThreadLocal<Map<String, Object>> paramsThreadLocal = new NamedThreadLocal<>("CurrenParams");

	public void setRequestParams(Map<String, Object> params) {
		this.paramsThreadLocal.set(params);
	}

	public Map<String, Object> getRequestParams() {
		return this.paramsThreadLocal.get();
	}

	public void setUser(Object user) {
		this.UserThreadLocal.set(user);
	}

	public Object getUser() {
		return this.UserThreadLocal.get();
	}
}
