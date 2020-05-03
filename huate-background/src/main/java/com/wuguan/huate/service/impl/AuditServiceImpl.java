/**   
* @Title: AuditServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午11:10:24 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wuguan.huate.bean.entity.Audit;
import com.wuguan.huate.bean.params.AuditPageParam;
import com.wuguan.huate.db.AuditMapper;
import com.wuguan.huate.service.AuditService;

/**
 * @ClassName: AuditServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月9日 下午11:10:24
 * 
 */
@Service
@Transactional
public class AuditServiceImpl implements AuditService {
	@Autowired
	AuditMapper auditMapper;

	@Override
	public void addData(Audit audit) {
		//Boolean exist = isExist(audit.getWorkerId(), null);
		//if (exist)
		//	throw new CustomException(ResultEnums.BUSINESS.getCode(), "此用户已存在");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		audit.setCreateTime(sdf.format(new Date()));
		auditMapper.addData(audit);
	}

	@Override
	public void updateData(Audit audit) {
		//Boolean exist = isExist(audit.getWorkerId(), null);
		//if (exist)
		//	throw new CustomException(ResultEnums.BUSINESS.getCode(), "此用户已存在");
		auditMapper.updateData(audit);
	}

	@Override
	public void delData(Integer id) {
		auditMapper.delData(id);

	}

	@Override
	public Audit detailData(Integer id) {
		return auditMapper.detailData(id);
	}

	@Override
	public List<Audit> queryListData(AuditPageParam params) {
		return auditMapper.queryListData(params);
	}

	@Override
	public Boolean isExist(Integer workerId, Integer id) {
		Integer exist = auditMapper.isExist(workerId, id);
		if (exist != 0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean isUse(Integer id) {
		Integer use=auditMapper.isUse(id);
		if (use!=0) {
			return true;
		}
		return false;
	}

	@Override
	public Audit getAudit(Integer directType) {
		return auditMapper.getAudit(directType);
	}

}
