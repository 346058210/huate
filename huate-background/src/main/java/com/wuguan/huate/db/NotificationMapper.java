/**   
* @Title: NotificationMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月30日 上午1:49:04 
* @version V1.0   
*/
package com.wuguan.huate.db;

import com.wuguan.huate.bean.entity.Notification;

/** 
* @ClassName: NotificationMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月30日 上午1:49:04 
*  
*/
public interface NotificationMapper {

	void publishNotification(Notification notification);
}
