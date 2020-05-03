/**   
* @Title: InformationService.java 
* @Package com.wuguan.huate.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月10日 下午3:11:12 
* @version V1.0   
*/
package com.wuguan.huate.service;

import com.wuguan.huate.bean.entity.Information;
import com.wuguan.huate.bean.params.InformationPageParam;
import com.wuguan.huate.comm.PageInfo;

/** 
* @ClassName: InformationService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月10日 下午3:11:12 
*  
*/
public interface InformationService {

	/**
	* @Title: addData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param information
	*/
	void addData(Information information);

	/**
	* @Title: updateData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param information
	*/
	void updateData(Information information);

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
	Information detailData(Integer id);

	/**
	* @Title: pageData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param params
	* @return
	*/
	PageInfo<Information> pageData(InformationPageParam params);
	
	Boolean isExist(String title,Integer id);

}
