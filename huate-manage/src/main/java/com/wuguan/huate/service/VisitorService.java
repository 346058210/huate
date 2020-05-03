/**   
* @Title: VisitorService.java 
* @Package com.wuguan.huate.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月15日 下午7:37:02 
* @version V1.0   
*/
package com.wuguan.huate.service;

import com.wuguan.huate.bean.entity.Visitor;
import com.wuguan.huate.bean.params.PageParams;
import com.wuguan.huate.comm.PageInfo;

/** 
* @ClassName: VisitorService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月15日 下午7:37:02 
*  
*/
public interface VisitorService {

	/**
	* @Title: affirmVisitor
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param code
	*/
	void affirmVisitor(String code);

	/**
	* @Title: addData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param visitor
	*/
	void addData(Visitor visitor);

	/**
	* @Title: updateData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param visitor
	*/
	void updateData(Visitor visitor);

	/**
	* @Title: detailData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	Visitor detailData(Integer id);

	/**
	* @Title: pageData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param params
	* @return
	*/
	PageInfo<Visitor> pageData(PageParams params);

	/**
	* @Title: queryByCode
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param code
	* @return
	*/
	Visitor queryByCode(String code);

}
