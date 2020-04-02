/**   
* @Title: WorkerServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月1日 下午2:50:31 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.entity.Worker;
import com.wuguan.huate.bean.params.WorkerParam;
import com.wuguan.huate.bean.vo.WorkerVo;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.db.WorkerMapper;
import com.wuguan.huate.service.WorkerService;
import com.wuguan.huate.utils.MD5Utils;
import com.wuguan.huate.web.result.ResultEnums;

/** 
* @ClassName: WorkerServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月1日 下午2:50:31 
*  
*/
@Service
@Transactional
public class WorkerServiceImpl implements WorkerService {
	@Autowired
	WorkerMapper workerMapper;

	@Override
	public void addData(Worker worker) {
		Boolean exist = isExist(worker.getPhone(), null);
		if (exist) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此手机号已使用，请更换后，再添加");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		worker.setCreateTime(sdf.format(new Date()));
		worker.setPassword(MD5Utils.encodePassword(worker.getPassword(), MD5Utils.SALT));
		workerMapper.addData(worker);
	}

	@Override
	public Boolean isExist(String phone, Integer id) {
		Integer exist=workerMapper.isExist(phone,id);
		if (exist!=null) {
			return true;
		}
		return false;
	}

	@Override
	public void updateData(Worker worker) {
		Boolean exist = isExist(worker.getPhone(), worker.getId());
		if (exist) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此手机号已使用，请更换后，再修改");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		worker.setUpdateTime(sdf.format(new Date()));
		if (!worker.getPassword().equals("")) {
			worker.setPassword(MD5Utils.encodePassword(worker.getPassword(), MD5Utils.SALT));	
		}
		workerMapper.updateData(worker);
	}

	@Override
	public void delData(Integer id) {
		Worker worker = new Worker();
		worker.setId(id);
		worker.setIsDel(1);
		workerMapper.updateData(worker);	
	}

	@Override
	public Object detailData(Integer id) {
		Worker worker=workerMapper.detailData(id);
		WorkerVo vo = new WorkerVo();
		BeanUtils.copyProperties(worker, vo);
		return vo;
	}

	@Override
	public void useData(Integer id,Integer isUse) {
		Worker worker = new Worker();
		worker.setId(id);
		worker.setIsUse(isUse);
		workerMapper.updateData(worker);
	}

	@Override
	public Object pageData(WorkerParam param) {
		PageHelper.startPage(param.getPage(),param.getRows());
		Page<Worker> page=workerMapper.pageData(param);
		List<WorkerVo> list = new ArrayList<WorkerVo>();
		for (Worker worker : page) {
			WorkerVo vo = new WorkerVo();
			BeanUtils.copyProperties(worker, vo);
		}
		return new PageInfo<WorkerVo>(page.getTotal(), list);
	}

	@Override
	public Worker getWorkerByPhone(String phone) {
		return workerMapper.getWorkerByPhone(phone);
	}

}
