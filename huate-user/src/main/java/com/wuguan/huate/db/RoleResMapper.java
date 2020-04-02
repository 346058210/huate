/**   
* @Title: RoleResMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月16日 下午5:48:50 
* @version V1.0   
*/
package com.wuguan.huate.db;

import org.apache.ibatis.annotations.Param;

/** 
* @ClassName: RoleResMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月16日 下午5:48:50 
*  
*/
public interface RoleResMapper {

	/**
	* @Title: del
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param roleId
	*/
	void del(@Param("roleId")Integer roleId);

	/**
	* @Title: addBatch
	* @Description: 角色、资源绑定
	* @param roleId
	* @param reses
	*/
	void addBatch(@Param("roleId")Integer roleId, @Param("reses")String[] reses);

}
