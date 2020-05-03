/**   
* @Title: UserHouseRoleService.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:17:10 
* @version V1.0   
*/
package com.wuguan.huate.service;

import com.wuguan.huate.comm.CustomException;

/** 
* @ClassName: UserHouseRoleService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:17:10 
*  
*/
public interface WorkerRoleService {
	
	void addBatch(Integer workerId,String roleIds)throws CustomException;
	void del(Integer workerId)throws CustomException;
	void addData(Integer workerId,Integer roleId)throws  CustomException;

}
