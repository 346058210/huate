/**   
* @Title: UserHouseRoleServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:25:41 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.db.WorkerRoleMapper;
import com.wuguan.huate.service.WorkerRoleService;
import com.wuguan.huate.web.result.ResultEnums;

/** 
* @ClassName: UserHouseRoleServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:25:41 
*  
*/
@Service
@Transactional
public class WorkerRoleServiceImpl implements WorkerRoleService {
	@Autowired
	WorkerRoleMapper workerRoleMapper;

	@Override
	public void addBatch(Integer workerId, String roleIds) throws CustomException{
		del(workerId);
		if (roleIds.trim()==null||"".equals(roleIds.trim())) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(),"roleIds字段不能为null或者空字符");
		}
		String[] roles = null;
		if (roleIds.contains(",")) {
			roles = roleIds.split(",");
		}else {
			roles= new String[]{roleIds};
		}
		workerRoleMapper.addBatch(workerId,roles);
	}

	@Override
	public void del(Integer workerId)throws CustomException {
		workerRoleMapper.del(workerId);
	}

	@Override
	public void addData(Integer workerId, Integer roleId) throws CustomException {
		workerRoleMapper.del(workerId);
		workerRoleMapper.addData(workerId,roleId);
	}

}
