/**   
* @Title: RoleMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:30:56 
* @version V1.0   
*/
package com.wuguan.huate.db;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuguan.huate.bean.entity.Role;

/** 
* @ClassName: RoleMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:30:56 
*  
*/
public interface RoleMapper {
	/**
	 * 
	* @Title: isExist
	* @Description: 是否存在
	* @param roleName
	* @return
	 */
	Integer isExist(@Param("roleName")String roleName,@Param("id")Integer id);

	/**
	* @Title: addData
	* @Description: 新增角色
	* @param role
	*/
	void addData(Role role);

	/**
	* @Title: updateData
	* @Description: 修改
	* @param role
	*/
	void updateData(Role role);

	/**
	* @Title: getRolesByWorkerId
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param workerId
	* @return
	*/
	List<Role> getRolesByWorkerId(@Param("workerId")Integer workerId);

	/**
	* @Title: isUse
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	Integer isUse(@Param("roleId")Integer roleId);

	/**
	* @Title: detailData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	Role detailData(@Param("id")Integer id);

	/**
	* @Title: listRoles
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
	List<Role> listRoles();

}
