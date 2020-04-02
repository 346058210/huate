/**   
* @Title: UserRoleMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月16日 下午6:28:26 
* @version V1.0   
*/
package com.wuguan.huate.db;

import org.apache.ibatis.annotations.Param;

/** 
* @ClassName: UserRoleMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月16日 下午6:28:26 
*  
*/
public interface WorkerRoleMapper {

	/**
	* @Title: del
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param userId
	*/
	void del(@Param("workerId")Integer workerId);

	/**
	* @Title: addBatch
	* @Description: 用户角色绑定
	* @param userId
	* @param roles
	*/
	void addBatch(@Param("workerId")Integer workerId, @Param("roles")String[] roles);

}
