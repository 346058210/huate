/**   
* @Title: AuditService.java 
* @Package com.wuguan.huate.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午11:10:05 
* @version V1.0   
*/
package com.wuguan.huate.service;

import java.util.List;

import com.wuguan.huate.bean.entity.Audit;
import com.wuguan.huate.bean.params.AuditPageParam;
/** 
* @ClassName: AuditService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月9日 下午11:10:05 
*  
*/
public interface AuditService {

	/**
	* @Title: addData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param audit
	*/
	void addData(Audit audit);

	/**
	* @Title: updateData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param audit
	*/
	void updateData(Audit audit);

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
	Audit detailData(Integer id);

	/**
	* @Title: pageData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param params
	* @return
	*/
	List<Audit> queryListData(AuditPageParam params);
	
	Boolean isExist(Integer workerId,Integer id);
	
	Boolean isUse(Integer id);
	
	Audit getAudit(Integer directType);

}
