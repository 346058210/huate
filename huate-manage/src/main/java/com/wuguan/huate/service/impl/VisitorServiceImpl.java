/**   
* @Title: VisitorServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月15日 下午7:37:26 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.entity.Visitor;
import com.wuguan.huate.bean.entity.Worker;
import com.wuguan.huate.bean.params.PageParams;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.comm.SpringUtil;
import com.wuguan.huate.comm.ThreadLocalManager;
import com.wuguan.huate.db.VisitorMapper;
import com.wuguan.huate.service.VisitorService;
import com.wuguan.huate.service.WorkerService;
import com.wuguan.huate.utils.DateUtils;
import com.wuguan.huate.web.result.ResultEnums;

/** 
* @ClassName: VisitorServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月15日 下午7:37:26 
*  
*/
@Service
public class VisitorServiceImpl implements VisitorService {
	@Autowired
	VisitorMapper visitorMapper;
	@Autowired
	WorkerService workerService;

	@Override
	public void affirmVisitor(String code) {
		Visitor visitor=visitorMapper.queryByCode(code);
		if (visitor==null) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此验证码无效");
		}
		String[] split = visitor.getAccessTime().split(" ");
		String[] time = split[1].split("-");
		if (!DateUtils.isTheSameDay(split[0], new Date())||!DateUtils.inMiddle(time[0], time[1], new Date())) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此验证码失效，请重新申请");
		}
		String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		visitorMapper.affirmVisitor(format,visitor.getId());
	}

	@Override
	public void addData(Visitor visitor) {
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		Worker worker = JSON.parseObject(bean.getUser().toString(), Worker.class);
		String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		visitor.setApplyUserId(worker.getId());
		visitor.setCreateTime(createTime);
		visitor.setAffirmTime(createTime);
		visitor.setApplyUserName(worker.getName());
		visitor.setApplyUserId(worker.getId());
		visitor.setApplyUserPhone(worker.getPhone());
		visitorMapper.addData(visitor);
	}

	@Override
	public void updateData(Visitor visitor) {
		String[] split = visitor.getAccessTime().split(" ");
		String[] time = split[1].split("-");
		if (!DateUtils.isTheSameDay(split[0], new Date())||!DateUtils.inMiddle(time[0], time[1], new Date())) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此验证码失效，请重新申请");
		}
		visitor.setAffirmTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		visitorMapper.updateData(visitor);
	}

	@Override
	public Visitor detailData(Integer id) {
		return visitorMapper.detailData(id);
	}

	@Override
	public PageInfo<Visitor> pageData(PageParams params) {
		PageHelper.startPage(params.getPage(), params.getRows());
		Page<Visitor> page=visitorMapper.pageData(params);
		return new PageInfo<Visitor>(page.getTotal(), page);
	}

	@Override
	public Visitor queryByCode(String code) {
		return visitorMapper.queryByCode(code);
	}
}
