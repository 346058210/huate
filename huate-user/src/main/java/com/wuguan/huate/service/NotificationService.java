/**   
* @Title: NotificationService.java 
* @Package com.wuguan.huate.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月30日 上午1:47:12 
* @version V1.0   
*/
package com.wuguan.huate.service;

import com.wuguan.huate.bean.entity.Notification;
import com.wuguan.huate.bean.params.PageParams;
import com.wuguan.huate.comm.PageInfo;

/** 
* @ClassName: NotificationService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月30日 上午1:47:12 
*  
*/
public interface NotificationService {
	
	void publishNotification(Notification notification);

	/**
	* @Title: queryNotifications
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
	PageInfo<Notification> queryNotifications(PageParams params);

	/**
	* @Title: detailData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	Notification detailData(Integer id);

}
