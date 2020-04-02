/**   
* @Title: RoleResServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:24:43 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.db.RoleResMapper;
import com.wuguan.huate.service.RoleResService;
import com.wuguan.huate.web.result.ResultEnums;
/** 
* @ClassName: RoleResServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:24:43 
*  
*/
@Service
@Transactional
public class RoleResServiceImpl implements RoleResService {
	@Autowired
	RoleResMapper roleResMapper;
	
	/**
	 * 角色资源绑定
	 */
	@Override
	public void addBatch(Integer roleId, String resIds)throws CustomException {
		delBatch(roleId);
		if (resIds.trim()==null||"".equals(resIds.trim())) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(),"resIds字段不能为null或者空字符");
		}
		String[] reses = resIds.split(",");
		roleResMapper.addBatch(roleId,reses);
	}

	@Override
	public void delBatch(Integer roleId)throws CustomException {
		roleResMapper.del(roleId);
	}

}
