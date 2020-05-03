/**   
* @Title: LiveServeService.java 
* @Package com.wuguan.huate.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月11日 下午2:45:20 
* @version V1.0   
*/
package com.wuguan.huate.service;

import com.wuguan.huate.bean.entity.LiveServe;
import com.wuguan.huate.bean.params.LiveServeParam;
import com.wuguan.huate.comm.PageInfo;

/** 
* @ClassName: LiveServeService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月11日 下午2:45:20 
*  
*/
public interface LiveServeService {

	/**
	* @Title: addData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param liveServe
	*/
	void addData(LiveServe liveServe);

	/**
	* @Title: updateData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param liveServe
	* @return
	*/
	void updateData(LiveServe liveServe);

	/**
	* @Title: delData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	*/
	void delData(Integer id);

	/**
	* @Title: detailData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	LiveServe detailData(Integer id);

	/**
	* @Title: pageData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param params
	* @return
	*/
	PageInfo<LiveServe> pageData(LiveServeParam params);
	
	Boolean isExist(String name,Integer id);
	
	Boolean isUse(Integer id);

}
