/**   

* @Title: ResourcesMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月16日 下午1:20:55 
* @version V1.0   
*/
package com.wuguan.huate.db;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuguan.huate.bean.entity.Resources;

/** 
* @ClassName: ResourcesMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月16日 下午1:20:55 
*  
*/
public interface ResourcesMapper {

	/**
	* @Title: isExist
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param resName
	* @param id
	*/
	Integer isExist(@Param("resName")String resName, @Param("id")Integer id);

	/**
	* @Title: addData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param res
	*/
	void addData(Resources res);

	/**
	* @Title: updateData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param res
	*/
	void updateData(Resources res);

	/**
	* @Title: isUse
	* @Description: 启用|禁用
	* @param res
	*/
	void isUseBatch(@Param("res")List<Resources> res);

	/**
	* @Title: getResourcesByFatherId
	* @Description: 父查子
	* @param pid
	* @return
	*/
	List<Resources> getResourcesByFatherId(@Param("pid")Integer pid);

	/**
	* @Title: recursionByPid
	* @Description: 向下遞歸
	* @param id
	* @return
	*/
	List<Resources> recursionByPid(@Param("pid")Integer pid);

	/**
	* @Title: getResourcesByWorkerId
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	*/
	List<Resources> getResourcesByWorkerId(@Param("workerId")Integer workerId);

	/**
	* @Title: resources
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
	List<Resources> resources();

	/**
	* @Title: resourcesByRoleId
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param roleId
	* @return
	*/
	List<Resources> resourcesByRoleId(@Param("roleId")Integer roleId);

}
