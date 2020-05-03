/**   
* @Title: HandleService.java 
* @Package com.wuguan.huate.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月17日 下午3:07:03 
* @version V1.0   
*/
package com.wuguan.huate.service;

import java.util.List;

import com.wuguan.huate.bean.entity.Handle;

/** 
* @ClassName: HandleService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月17日 下午3:07:03 
*  
*/
public interface HandleService {
	
	List<Handle> queryByRepairsId(Integer repairsId);

	/**
	* @Title: addData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param handle
	*/
	void addData(Handle handle);

}
