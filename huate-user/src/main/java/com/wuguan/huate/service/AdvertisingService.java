/**   
* @Title: AdvertisingService.java 
* @Package com.wuguan.huate.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午5:59:28 
* @version V1.0   
*/
package com.wuguan.huate.service;

import java.util.List;

import com.wuguan.huate.bean.entity.Advertising;

/** 
* @ClassName: AdvertisingService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月9日 下午5:59:28 
*  
*/
public interface AdvertisingService {

	/**
	* @Title: queryAdvertisings
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
	List<Advertising> queryAdvertisings();


}
