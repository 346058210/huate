/**   
* @Title: LiveServeServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月11日 下午2:45:58 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.entity.Audit;
import com.wuguan.huate.bean.entity.LiveServe;
import com.wuguan.huate.bean.enums.AuditEnums;
import com.wuguan.huate.bean.params.LiveServeParam;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.db.LiveServeMapper;
import com.wuguan.huate.service.AuditService;
import com.wuguan.huate.service.LiveServeService;
import com.wuguan.huate.web.result.ResultEnums;

/** 
* @ClassName: LiveServeServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月11日 下午2:45:58 
*  
*/
@Service
public class LiveServeServiceImpl implements LiveServeService {
	@Autowired
	LiveServeMapper liveServeMapper;
	@Autowired
	AuditService auditService;

	@Override
	public void addData(LiveServe liveServe) {
		Boolean exist = isExist(liveServe.getName(), null);
		if (exist) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此类型名称已存在");
		}
		if (liveServe.getAuditId()==null) {
			Audit audit = auditService.getAudit(AuditEnums.DirectType.LIVESERVE.getValue());
			if (audit!=null) {
				liveServe.setAudit(audit.getName());
				liveServe.setAuditId(audit.getWorkerId());
				liveServe.setAuditPhone(audit.getPhone());
			}
		}
		liveServeMapper.addData(liveServe);
	}

	@Override
	public void updateData(LiveServe liveServe) {
		Boolean exist = isExist(liveServe.getName(), liveServe.getId());
		if (exist) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此类型名称已存在");
		}
		liveServeMapper.updateData(liveServe);
	}

	@Override
	public void delData(Integer id) {
		Boolean use = isUse(id);
		if (use) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此类型名称已存在");
		}
		LiveServe serve = new LiveServe();
		serve.setId(id);
		serve.setIsDel(1);
		liveServeMapper.updateData(serve);
	}

	@Override
	public LiveServe detailData(Integer id) {
		return liveServeMapper.detailData(id);
	}

	@Override
	public PageInfo<LiveServe> pageData(LiveServeParam params) {
		PageHelper.startPage(params.getPage(), params.getRows());
		Page<LiveServe> page=liveServeMapper.pageData(params);
		return new PageInfo<LiveServe>(page.getTotal(),page);
	}

	@Override
	public Boolean isExist(String name, Integer id) {
		Integer exist=liveServeMapper.isExist(name,id);
		if (exist!=0) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean isUse(Integer id) {
		Integer use=liveServeMapper.isUse(id);
		if (use!=0) {
			return true;
		}
		return false;
	}

}
