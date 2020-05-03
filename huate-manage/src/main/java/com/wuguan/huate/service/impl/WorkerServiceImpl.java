/**   
* @Title: WorkerServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月15日 下午6:49:40 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wuguan.huate.bean.entity.Worker;
import com.wuguan.huate.bean.params.WorkerParam;
import com.wuguan.huate.comm.BaseService;
import com.wuguan.huate.comm.Constant;
import com.wuguan.huate.db.WorkerMapper;
import com.wuguan.huate.service.WorkerService;

/** 
* @ClassName: WorkerServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月15日 下午6:49:40 
*  
*/
@Service
public class WorkerServiceImpl implements WorkerService {
	
	@Autowired
	BaseService baseService;
	@Autowired
	WorkerMapper workerMapper;

	@Override
	public String getOpendId(WorkerParam worker) {
		String result = baseService.getOpenid(worker.getCode(),Constant.JY_APPID,Constant.JY_SECRET);
		JSONObject object = JSONObject.parseObject(result);
		String openid = object.get("openid").toString();
		return openid;
	}

	@Override
	public Boolean isExist(String openId) {
		Integer exist=workerMapper.isExist(openId);
		if (exist!=0) {
			return true;
		}
		return false;
	}

	@Override
	public Worker getByOpenid(String openId) {
		return workerMapper.getByOpenid(openId);
	}

	@Override
	public Worker getByPhone(String phone) {
		return workerMapper.getByPhone(phone);
	}

	@Override
	public void updateData(Worker worker) {
		workerMapper.updateData(worker);
	}

	@Override
	public Worker detailData(Integer id) {
		return workerMapper.detailData(id);
	}

}
