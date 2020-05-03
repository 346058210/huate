/**   
* @Title: RoleController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月17日 上午12:22:02 
* @version V1.0   
*/
package com.wuguan.huate.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wuguan.huate.service.RoleService;
import com.wuguan.huate.web.result.ApiResult;

/** 
* @ClassName: RoleController 
* @Description: 查询角色
* @author LiuYanHong
* @date 2020年4月17日 上午12:22:02 
*  
*/
@RestController
public class RoleController {
	@Autowired
	RoleService roleService;
	
	/**
	 * 
	* @Title: queryRoles
	* @Description: 获取角色
	* @return
	 */
	@RequestMapping(value = "/role/queryRoles",method = RequestMethod.GET)
	public ApiResult queryRoles() {
		return ApiResult.success(roleService.queryRoles());	
	}
	
	/**
	 * 
	* @Title: queryHandler
	* @Description: 获取指定角色的工作人员
	* @param roleId
	* @return
	 */
	@RequestMapping(value = "/role/queryHandler",method = RequestMethod.GET)
	public ApiResult queryHandler(Integer roleId) {
		return ApiResult.success(roleService.queryHandler(roleId));
		
	}

}
