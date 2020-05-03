/**   
* @Title: AdvertisingService.java 
* @Package com.wuguan.huate.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午5:59:28 
* @version V1.0   
*/
package com.wuguan.huate.service;

import com.wuguan.huate.bean.entity.Advertising;
import com.wuguan.huate.bean.params.AdvertisingPageParam;
import com.wuguan.huate.comm.PageInfo;

/** 
* @ClassName: AdvertisingService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月9日 下午5:59:28 
*  
*/
public interface AdvertisingService {

	/**
	* @Title: addData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param advertising
	*/
	void addData(Advertising advertising);

	/**
	* @Title: updateData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param advertising
	*/
	void updateData(Advertising advertising);

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
	Advertising detailData(Integer id);

	/**
	* @Title: pageData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param param
	* @return
	*/
	PageInfo<Advertising> pageData(AdvertisingPageParam param);

}
