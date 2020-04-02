/**   
* @Title: ResidentServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:23:42 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuguan.huate.bean.entity.Resident;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.db.ResidentMapper;
import com.wuguan.huate.service.ResidentService;
import com.wuguan.huate.web.result.ResultEnums;

/** 
* @ClassName: ResidentServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:23:42 
*  
*/
@Service
@Transactional
public class ResidentServiceImpl implements ResidentService {
	@Autowired
	ResidentMapper residentMapper;

	@Override
	public void addData(Resident resident) throws CustomException {
		Boolean exist = isExist(resident.getIdcard(), null);
		if (exist) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(),"此住户信息已登记");
		}
		residentMapper.addData(resident);
	}

	@Override
	public void updateData(Resident resident) throws CustomException {
		Boolean exist = isExist(resident.getIdcard(), resident.getId());
		if (exist) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(),"此身份证号码已存在");
		}
		residentMapper.updateData(resident);
	}

	@Override
	public void delData(Integer id) throws CustomException {
		residentMapper.delData(id);	
	}

	@Override
	public void addBatch(List<Resident> residents) throws CustomException {
		residentMapper.addBatch(residents);
	}

	@Override
	public Boolean isExist(String idcard, Integer id) throws CustomException {
		Integer exist = residentMapper.isExist(idcard, id);
		if (exist!=null) {
			return true;
		}
		return false;
	}

	@Override
	public List<Resident> getResidents(Integer startRow, Integer pageSize) throws CustomException {
		return residentMapper.getResidents(startRow, pageSize);
	}

	@Override
	public List<Resident> getResidentsByHouseNo(String houseNo) throws CustomException {
		return residentMapper.getResidentsByHouseNo(houseNo);
	}

	@Override
	public void delByHouseNo(String houseNo) {
		residentMapper.delByHouseNo(houseNo);
	}

}
