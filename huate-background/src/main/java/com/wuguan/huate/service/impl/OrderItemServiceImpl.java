/**   
* @Title: OrderItemServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:21:53 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuguan.huate.bean.entity.OrderItem;
import com.wuguan.huate.bean.params.ParkCountParam;
import com.wuguan.huate.bean.params.PropertyParam;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.db.OrderItemMapper;
import com.wuguan.huate.service.OrderItemService;

/** 
* @ClassName: OrderItemServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:21:53 
*  
*/
@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {
	@Autowired
	OrderItemMapper orderItemMapper;
	
	@Override
	public void addBatch(List<OrderItem> detail) throws CustomException {
		orderItemMapper.addBatch(detail);
	}

	@Override
	public List<OrderItem> getOrderItemByOrderNo(String orderNo) {
		return orderItemMapper.getOrderItemByOrderNo(orderNo);
	}

	@Override
	public Double todayPropertyPayFee() {
		return orderItemMapper.todayPropertyPayFee();
	}

	@Override
	public Double todayTotalPayFee() {
		return orderItemMapper.todayTotalPayFee();
	}

	@Override
	public Object totadyPayFee() {
		return orderItemMapper.totadyPayFee();
	}

	@Override
	public List<Map<String, Object>> parkPayFee() {
		return orderItemMapper.parkPayFee();
	}

	@Override
	public Object feeCountByType(String startTime, String endTime) {
		return orderItemMapper.feeCountByType(startTime,endTime);
	}

	@Override
	public Object propertyFeeCount(PropertyParam param) {
		return orderItemMapper.propertyFeeCount(param);
	}

	@Override
	public Object parkFeeCount(ParkCountParam param) {
		return orderItemMapper.parkFeeCount(param);
	}

}
