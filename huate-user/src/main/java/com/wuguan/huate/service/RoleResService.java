/**   
* @Title: RoleResService.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:16:52 
* @version V1.0   
*/
package com.wuguan.huate.service;

import com.wuguan.huate.comm.CustomException;

/** 
* @ClassName: RoleResService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:16:52 
*  
*/
public interface RoleResService {
	
	void addBatch(Integer roleId,String resIds) throws CustomException;
	void delBatch(Integer roleId)throws CustomException;

}
