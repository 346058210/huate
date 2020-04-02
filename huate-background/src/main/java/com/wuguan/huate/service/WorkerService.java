/**   
* @Title: WorkerService.java 
* @Package com.wuguan.huate.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月1日 下午2:50:02 
* @version V1.0   
*/
package com.wuguan.huate.service;

import com.wuguan.huate.bean.entity.Worker;
import com.wuguan.huate.bean.params.WorkerParam;

/** 
* @ClassName: WorkerService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月1日 下午2:50:02 
*  
*/
public interface WorkerService {

	/**
	* @Title: addData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param worker
	*/
	void addData(Worker worker);
	void updateData(Worker worker);
	
	Boolean isExist(String phone,Integer id);
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
	Object detailData(Integer id);
	/**
	* @Title: useData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	*/
	void useData(Integer id,Integer isUse);
	/**
	* @Title: pageData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param param
	* @return
	*/
	Object pageData(WorkerParam param);
	/**
	* @Title: getWorkerByPhone
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param phone
	* @return
	*/
	Worker getWorkerByPhone(String phone);

}
