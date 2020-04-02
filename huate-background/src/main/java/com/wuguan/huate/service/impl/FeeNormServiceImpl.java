/**   
* @Title: FeeNormServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:19:50 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.entity.FeeNorm;
import com.wuguan.huate.bean.params.FeeNormPageParam;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.db.FeeNormMapper;
import com.wuguan.huate.service.FeeNormService;
import com.wuguan.huate.web.result.ResultEnums;

/** 
* @ClassName: FeeNormServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:19:50 
*  
*/
@Service
@Transactional
public class FeeNormServiceImpl implements FeeNormService {
	@Autowired
	FeeNormMapper feeNormMapper;

	@Override
	public void addData(FeeNorm norm) throws CustomException{
		Boolean exist = isExist(norm.getTypeName(), null);
		if (exist) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(),"此收费标准已存在");
		}
		try {
			feeNormMapper.addData(norm);
		} catch (Exception e) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(),"新增失败");
		}
	}

	@Override
	public void updateData(FeeNorm norm)throws CustomException{
		Boolean exist = isExist(norm.getTypeName(), norm.getId());
		if (exist) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(),"此收费标准已存在");
		}
		try {
			feeNormMapper.updateData(norm);
		} catch (Exception e) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(),"修改失败");
		}
	}

	@Override
	public void del(Integer id)throws CustomException {
		Boolean use = isUse(id);
		if (use) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(),"当前标准已绑定使用，请解绑后删除");
		}
		
	}

	@Override
	public List<FeeNorm> getFeeNorms() throws CustomException{
		return feeNormMapper.getFeeNorms();
	}

	@Override
	public Boolean isExist(String typeName, Integer id) throws CustomException {
		Integer exist = feeNormMapper.isExist(typeName, id);
		if (exist!=null) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean isUse(Integer id) throws CustomException {
		Integer use = feeNormMapper.isUse(id);
		if (use!=null) {
			return true;
		}
		return false;
	}

	@Override
	public FeeNorm detailData(Integer id) {
		return feeNormMapper.detailData(id);
	}

	@Override
	public PageInfo<FeeNorm> pageData(FeeNormPageParam param) {
		PageHelper.startPage(param.getPage(), param.getRows());
		Page<FeeNorm> page=feeNormMapper.pageData(param);
		return new PageInfo<FeeNorm>(page.getTotal(), page);
	}

}
