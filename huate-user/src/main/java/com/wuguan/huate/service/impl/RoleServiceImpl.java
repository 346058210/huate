/**   
* @Title: RoleServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:25:12 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuguan.huate.bean.entity.Role;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.db.RoleMapper;
import com.wuguan.huate.service.RoleService;
import com.wuguan.huate.web.result.ResultEnums;

/** 
* @ClassName: RoleServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:25:12 
*  
*/
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleMapper roleMapper;
	
	/**
	 * 新增
	 */
	@Override
	public void add(Role role) {
		Boolean exist = isExist(role.getRoleName(), null);
		if (exist) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此角色名稱已存在");
		}
		try {
			roleMapper.addData(role);
		} catch (Exception e) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "新增失敗");
		}
	}
	
	/**
	 * 修改
	 */
	@Override
	public void update(Role role) {
		Boolean exist = isExist(role.getRoleName(), null);
		if (exist) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此角色名稱已存在");
		}
		try {
			roleMapper.updateData(role);
		} catch (Exception e) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "修改失敗");
		}
		
	}
	
	/**
	 * 是否刪除
	 */
	@Override
	public void del(Integer id) {
		Boolean use = isUse(id);
		if (use) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此角色被绑定，无法删除，请解除绑定后再操作");
		}
		Role role = new Role();
		role.setId(id);
		role.setIsDel(1);
		roleMapper.updateData(role);
	}
	
	/**
	 * 是否存在
	 */
	@Override
	public Boolean isExist(String roleName,Integer id) {
		Integer exist = roleMapper.isExist(roleName, id);
		if (exist!=null) {
			return true;
		}
		return false;
	}

	@Override
	public List<Role> getRolesByWorkerId(Integer workerId) {
		return roleMapper.getRolesByWorkerId(workerId);
	}

	@Override
	public Boolean isUse(Integer id) {
		Integer use=roleMapper.isUse(id);
		if (use!=null) {
			return true;
		}
		return false;
	}

	@Override
	public Role detailData(Integer id) {
		return roleMapper.detailData(id);
	}

	@Override
	public List<Role> listRoles() {
		return roleMapper.listRoles();
	}

}
