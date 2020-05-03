/**   
* @Title: HandleService.java 
* @Package com.wuguan.huate.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月10日 下午2:52:21 
* @version V1.0   
*/
package com.wuguan.huate.service;

import java.util.List;

import com.wuguan.huate.bean.entity.Handle;

/** 
* @ClassName: HandleService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月10日 下午2:52:21 
*  
*/
public interface HandleService {

	/**
	* @Title: queryByRepairsId
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param repairsId
	* @return
	*/
	List<Handle> queryByRepairsId(Integer repairsId);

}
