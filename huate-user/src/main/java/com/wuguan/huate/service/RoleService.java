/**   
* @Title: RoleService.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:08:45 
* @version V1.0   
*/
package com.wuguan.huate.service;

import java.util.List;

import com.wuguan.huate.bean.entity.Role;

/** 
* @ClassName: RoleService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:08:45 
*  
*/
public interface RoleService {
	
	void add(Role role);
	void update(Role role);
	void del(Integer id);
	Boolean isExist(String roleName,Integer id);
	Boolean isUse(Integer id);
	List<Role> getRolesByWorkerId(Integer workerId);
	/**
	* @Title: detailData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	Role detailData(Integer id);
	/**
	* @Title: listRoles
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
	List<Role> listRoles();

}
