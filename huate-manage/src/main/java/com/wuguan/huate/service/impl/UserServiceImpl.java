/**   
* @Title: UserServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:26:32 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wuguan.huate.bean.entity.User;
import com.wuguan.huate.db.UserMapper;
import com.wuguan.huate.service.UserService;

/** 
* @ClassName: UserServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:26:32 
*  
*/
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;

	@Override
	public User detail(Integer id) {
		User user = userMapper.detail(id);
		return user;
	}

}
