/**   
* @Title: ParkService.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:15:52 
* @version V1.0   
*/
package com.wuguan.huate.service;

import com.wuguan.huate.bean.entity.Park;

/** 
* @ClassName: ParkService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:15:52 
*  
*/
public interface ParkService {

	Park getParksByParkNo(String parkNo);
	
	/**
	* @Title: queryParkByCarNo
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param carNo
	* @return
	*/
	Object queryParkByCarNo(String carNo);

	/**
	* @Title: detailData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	Object detailData(Integer id);

}
