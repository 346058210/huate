/**   
* @Title: SendMessageService.java 
* @Package com.wuguan.huate.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月29日 下午11:44:09 
* @version V1.0   
*/
package com.wuguan.huate.service;

import java.util.List;

import com.wuguan.huate.bean.vo.UserVo;

/** 
* @ClassName: SendMessageService 
* @Description: 推送消息服务
* @author LiuYanHong
* @date 2020年3月29日 下午11:44:09 
*  
*/
public interface SendMessageService {
	
	void propertyPayFeeMessageSend();
	
	void ParkPayFeeMessageSend();
	
	void notificationMessageSend(List<UserVo> user,String publisher,String content,String publishTime);

}
