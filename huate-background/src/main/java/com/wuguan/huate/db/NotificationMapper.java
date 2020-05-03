/**   
* @Title: NotificationMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月30日 上午1:49:04 
* @version V1.0   
*/
package com.wuguan.huate.db;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.wuguan.huate.bean.entity.Notification;
import com.wuguan.huate.bean.params.NotificationPageParam;

/**
 * @ClassName: NotificationMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年3月30日 上午1:49:04
 * 
 */
public interface NotificationMapper {

	void publishNotification(Notification notification);

	/**
	 * @Title: updateData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param notification
	 */
	void updateData(Notification notification);

	/**
	 * @Title: delData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 */
	void delData(@Param("id") Integer id);

	/**
	 * @Title: detailData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 */
	Notification detailData(@Param("id") Integer id);

	/**
	 * @Title: pageData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param param
	 * @return
	 */
	Page<Notification> pageData(NotificationPageParam param);
}
