/**   
* @Title: AuditServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月16日 下午10:20:30 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuguan.huate.bean.entity.Audit;
import com.wuguan.huate.db.AuditMapper;
import com.wuguan.huate.service.AuditService;

/** 
* @ClassName: AuditServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月16日 下午10:20:30 
*  
*/
@Service
public class AuditServiceImpl implements AuditService {
	@Autowired
	AuditMapper auditMapper;

	@Override
	public Audit getAudit(Integer directType) {
		return auditMapper.getAudit(directType);
	}
	
}
