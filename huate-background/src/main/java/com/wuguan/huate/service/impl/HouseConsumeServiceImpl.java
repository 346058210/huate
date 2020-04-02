/**   
* @Title: HouseConsumeServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:20:54 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuguan.huate.bean.entity.HouseConsume;
import com.wuguan.huate.bean.params.HouseConsumePageParam;
import com.wuguan.huate.db.HouseConsumeMapper;
import com.wuguan.huate.service.HouseConsumeService;

/** 
* @ClassName: HouseConsumeServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:20:54 
*  
*/
@Service
@Transactional
public class HouseConsumeServiceImpl implements HouseConsumeService {
	@Autowired
	HouseConsumeMapper houseConsumeMapper;

	/**
	 * 導入Excel
	 */
	@Override
	public void addBatch(List<HouseConsume> consumes) {
		houseConsumeMapper.addBatch(consumes);
	}
	
	/**
	 * 導出Excel
	 */
	@Override
	public List<HouseConsume> exportExcel(HouseConsumePageParam param) {
		return houseConsumeMapper.exportExcel(param);
	}

	/**
	 * 查询水|电数据
	 * @param houseNo
	 * @param type 3水4 电
	 */
	@Override
	public List<HouseConsume> getHouseConsumeByHouseNo(String houseNo, Integer type) {
		return houseConsumeMapper.getHouseConsumeByHouseNo(houseNo, type);
	}

	@Override
	public void updatePayTime(String payTime, Integer relationId) {
		houseConsumeMapper.updatePayTime(payTime,relationId);
	}

	@Override
	public Object detailData(Integer id) {
		return houseConsumeMapper.detailData(id);
	}

	@Override
	public void updateData(HouseConsume consume) {
		houseConsumeMapper.updateData(consume);
	}

	@Override
	public List<HouseConsume> getListDataUnPay(String houseNo) {
		return houseConsumeMapper.getListDataUnPay(houseNo);
	}

}
