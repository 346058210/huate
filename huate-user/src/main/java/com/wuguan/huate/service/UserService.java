/**   
* @Title: UserService.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:06:11 
* @version V1.0   
*/
package com.wuguan.huate.service;

import java.util.List;
import com.wuguan.huate.bean.entity.User;
import com.wuguan.huate.bean.params.UserPagePram;
import com.wuguan.huate.bean.params.UserParam;
import com.wuguan.huate.bean.vo.UserVo;
import com.wuguan.huate.comm.CustomException;

/** 
* @ClassName: UserService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:06:11 
*  
*/
public interface UserService {
	
	void add(User user)throws CustomException;
	void update(User user)throws CustomException;
	void del(Integer id)throws CustomException;
	Boolean isExist(String openid)throws CustomException;
	/**
	* @Title: checkUser
	* @Description: 註冊、登錄校驗
	* @param param
	* @return
	*/
	String getOpenId(UserParam param);
	
	User getByOpenid(String openid);
	/**
	* @Title: detail
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	UserVo detail(Integer id);
	/**
	* @Title: pageData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param param
	* @return
	*/
	Object pageData(UserPagePram param);
	/**
	* @Title: getListUser
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
	List<UserVo> getListUser();
	/**
	* @Title: operationData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param param
	 * @param exist 
	* @return
	*/
	UserVo operationData(UserParam param, Boolean exist);
	/**
	* @Title: getUserByOrderNo
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param orderNo
	* @return
	*/
	User getUserByOrderNo(String orderNo);


}
