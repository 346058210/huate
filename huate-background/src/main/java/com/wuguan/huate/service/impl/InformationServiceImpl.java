/**   
* @Title: InformationServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月10日 下午3:11:30 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.entity.Information;
import com.wuguan.huate.bean.params.InformationPageParam;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.db.InformationMapper;
import com.wuguan.huate.service.InformationService;
import com.wuguan.huate.web.result.ResultEnums;

/** 
* @ClassName: InformationServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月10日 下午3:11:30 
*  
*/
@Service
@Transactional
public class InformationServiceImpl implements InformationService {
	@Autowired
	InformationMapper informationMapper;

	@Override
	public void addData(Information information) {
		Boolean exist = isExist(information.getTitle(), null);
		if (exist) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此标题已存在");
		}
		information.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		informationMapper.addData(information);
	}

	@Override
	public void updateData(Information information) {
		Boolean exist = isExist(information.getTitle(), information.getId());
		if (exist) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此标题已存在");
		}
		informationMapper.updateData(information);
	}

	@Override
	public void delData(Integer id) {
		Information information = new Information();
		information.setId(id);
		information.setIsDel(1);
		informationMapper.updateData(information);
	}

	@Override
	public Information detailData(Integer id) {
		return informationMapper.detailData(id);
	}

	@Override
	public PageInfo<Information> pageData(InformationPageParam params) {
		PageHelper.startPage(params.getPage(), params.getRows());
		Page<Information> page=informationMapper.pageData(params);
		return new PageInfo<Information>(page.getTotal(), page);
	}

	@Override
	public Boolean isExist(String title, Integer id) {
		Integer exist=informationMapper.isExist(title,id);
		if (exist!=0) {
			return true;
		}
		return false;
	}

}
