/**   
* @Title: BasicServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月8日 下午7:42:16 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuguan.huate.bean.entity.Basic;
import com.wuguan.huate.bean.vo.BasicVo;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.db.BasicMapper;
import com.wuguan.huate.service.BasicService;
import com.wuguan.huate.web.result.ResultEnums;

/** 
* @ClassName: BasicServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月8日 下午7:42:16 
*  
*/
@Service
@Transactional
public class BasicServiceImpl implements BasicService {
	@Autowired
	BasicMapper basicMapper;

	@Override
	public void addData(Basic basic) {
		Boolean exist = isExist(basic.getCompany(), null);
		if (exist) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此公司名称的基础信息已存在");
		}
		basicMapper.addData(basic);
	}

	@Override
	public void updateData(Basic basic) {
		Boolean exist = isExist(basic.getCompany(), basic.getId());
		if (exist) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此公司名称的基础信息已存在");
		}
		basicMapper.updateData(basic);
		
	}

	@Override
	public void delData(Integer id) {
		basicMapper.delData(id);
		
	}

	@Override
	public BasicVo detailData(Integer id) {
		return basicMapper.detailData(id);
	}

	@Override
	public List<BasicVo> queryData(String company) {
		return basicMapper.queryData(company);
	}

	@Override
	public Boolean isExist(String company, Integer id) {
		Integer exist=basicMapper.isExist(company,id);
		if (exist!=0) {
			return true;
		}
		return false;
	}

}
