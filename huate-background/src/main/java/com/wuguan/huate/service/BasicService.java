/**   
* @Title: BasicService.java 
* @Package com.wuguan.huate.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月8日 下午7:41:56 
* @version V1.0   
*/
package com.wuguan.huate.service;

import java.util.List;

import com.wuguan.huate.bean.entity.Basic;
import com.wuguan.huate.bean.vo.BasicVo;

/** 
* @ClassName: BasicService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月8日 下午7:41:56 
*  
*/
public interface BasicService {

	/**
	* @Title: addData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param basic
	*/
	void addData(Basic basic);

	/**
	* @Title: updateData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param basic
	*/
	void updateData(Basic basic);

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
	BasicVo detailData(Integer id);

	/**
	* @Title: queryData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param company
	* @return
	*/
	List<BasicVo> queryData(String company);
	
	Boolean isExist(String company,Integer id);

}
