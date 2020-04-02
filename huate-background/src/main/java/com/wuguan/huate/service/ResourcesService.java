/**   
* @Title: ResourcesService.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:10:48 
* @version V1.0   
*/
package com.wuguan.huate.service;

import java.util.List;

import com.wuguan.huate.bean.entity.Resources;
import com.wuguan.huate.bean.vo.ResourcesTree;

/** 
* @ClassName: ResourcesService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:10:48 
*  
*/
public interface ResourcesService {
	
	void add(Resources res);
	void update(Resources res);
	void isUse(Resources res);
	Boolean isExist(String resName,Integer id);
	List<Resources> getResourcesByFatherId(Integer pid);
	List<ResourcesTree> getResourcesTree();
	/**
	* @Title: getResourcesByWorkerId
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	List<Resources> getResourcesByWorkerId(Integer id);
	/**
	* @Title: listResources
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
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
	List<Resources> resourcesByRoleId(Integer roleId);
	
}
