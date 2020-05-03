/**   
* @Title: NotificationServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月30日 上午1:48:26 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.entity.Notification;
import com.wuguan.huate.bean.params.PageParams;
import com.wuguan.huate.bean.vo.UserVo;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.db.NotificationMapper;
import com.wuguan.huate.service.NotificationService;
import com.wuguan.huate.service.SendMessageService;
import com.wuguan.huate.service.UserService;

/**
 * @ClassName: NotificationServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年3月30日 上午1:48:26
 * 
 */
@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {
	@Autowired
	NotificationMapper notificationMapper;
	@Autowired
	UserService userService;
	@Autowired
	SendMessageService sendMessageService;

	@Override
	public void publishNotification(Notification notification) {
		String publishTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		notification.setPublishTime(publishTime);
		notificationMapper.publishNotification(notification);
		List<UserVo> user = userService.getListUser();
		sendMessageService.notificationMessageSend(user, notification.getPublisher(), notification.getContent(),
				publishTime);
	}

	@Override
	public PageInfo<Notification> queryNotifications(PageParams params) {
		PageHelper.startPage(params.getPage(), params.getRows());
		Page<Notification> page = notificationMapper.queryNotifications();
		return new PageInfo<Notification>(page.getTotal(), page);
	}

	@Override
	public Notification detailData(Integer id) {
		return notificationMapper.detailData(id);
	}

}
