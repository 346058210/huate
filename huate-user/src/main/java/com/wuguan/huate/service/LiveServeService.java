/**   
* @Title: LiveServeService.java 
* @Package com.wuguan.huate.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月11日 下午2:45:20 
* @version V1.0   
*/
package com.wuguan.huate.service;

import java.util.List;

import com.wuguan.huate.bean.entity.LiveServe;

/** 
* @ClassName: LiveServeService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月11日 下午2:45:20 
*  
*/
public interface LiveServeService {

	/**
	* @Title: queryLiveServes
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
	List<LiveServe> queryLiveServes();

	/**
	* @Title: detailLiveServe
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	LiveServe detailLiveServe(Integer id);


}
