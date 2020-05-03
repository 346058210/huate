/**   
* @Title: RoleServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月17日 上午12:30:05 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuguan.huate.bean.entity.Role;
import com.wuguan.huate.bean.entity.Worker;
import com.wuguan.huate.db.RoleMapper;
import com.wuguan.huate.service.RoleService;

/** 
* @ClassName: RoleServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月17日 上午12:30:05 
*  
*/
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleMapper roleMapper;
	@Override
	public List<Worker> queryHandler(Integer roleId) {
		return roleMapper.queryByRoleId(roleId);
	}

	@Override
	public List<Role> queryRoles() {
		return roleMapper.queryRoles();
	}

	@Override
	public Role getRoleByworkerId(Integer workerId) {
		return roleMapper.getRoleByworkerId(workerId);
	}

}
