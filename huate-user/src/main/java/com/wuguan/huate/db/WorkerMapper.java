/**   
* @Title: WorkerMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月1日 下午2:51:29 
* @version V1.0   
*/
package com.wuguan.huate.db;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.wuguan.huate.bean.entity.Worker;
import com.wuguan.huate.bean.params.WorkerParam;

/** 
* @ClassName: WorkerMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月1日 下午2:51:29 
*  
*/
public interface WorkerMapper {

	/**
	* @Title: isExist
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param phone
	* @param id
	* @return
	*/
	Integer isExist(@Param("phone")String phone, @Param("id")Integer id);

	/**
	* @Title: addData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param worker
	*/
	void addData(Worker worker);
	
	void updateData(Worker worker);

	/**
	* @Title: detailData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	Worker detailData(@Param("id")Integer id);

	/**
	* @Title: pageData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param param
	* @return
	*/
	Page<Worker> pageData(WorkerParam param);

	/**
	* @Title: getWorkerByPhone
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param phone
	* @return
	*/
	Worker getWorkerByPhone(@Param("phone")String phone);

}
