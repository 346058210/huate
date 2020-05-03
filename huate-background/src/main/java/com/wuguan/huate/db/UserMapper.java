/**   
* @Title: UserMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月16日 下午6:45:25 
* @version V1.0   
*/
package com.wuguan.huate.db;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.wuguan.huate.bean.entity.User;

/** 
* @ClassName: UserMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月16日 下午6:45:25 
*  
*/
public interface UserMapper {

	/**
	* @Title: isExist
	* @Description: 微信识别码是否存在
	* @param openId
	* @return
	*/
	Integer isExist(@Param("openid")String openid);

	/**
	* @Title: addData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param user
	*/
	void addData(User user);

	/**
	* @Title: updateData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param user
	*/
	void updateData(User user);

	/**
	* @Title: getByOpenid
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param openid
	* @return
	*/
	User getByOpenid(@Param("openid")String openid);

	/**
	* @Title: detail
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	User detail(@Param("id")Integer id);

	/**
	* @Title: getUsersByContent
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param content
	*/
	Page<User> getUsersByContent(@Param("content")String content);

	/**
	* @Title: getListUser
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
	List<User> getListUser();

	/**
	* @Title: getUserByOrderNo
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param orderNo
	* @return
	*/
	User getUserByOrderNo(@Param("orderNo")String orderNo);

	/**
	* @Title: todayRegisterNum
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
	Integer todayRegisterNum();


}
