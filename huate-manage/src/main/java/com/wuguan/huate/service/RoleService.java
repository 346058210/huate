/**   
* @Title: RoleService.java 
* @Package com.wuguan.huate.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月17日 上午12:29:39 
* @version V1.0   
*/
package com.wuguan.huate.service;

import java.util.List;

import com.wuguan.huate.bean.entity.Role;
import com.wuguan.huate.bean.entity.Worker;

/** 
* @ClassName: RoleService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月17日 上午12:29:39 
*  
*/
public interface RoleService {

	/**
	* @Title: queryHandler
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param roleId
	* @return
	*/
	List<Worker> queryHandler(Integer roleId);

	/**
	* @Title: queryRoles
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
	List<Role> queryRoles();

	/**
	* @Title: getRoleById
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	Role getRoleByworkerId(Integer workerId);

}
