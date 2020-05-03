/**   
* @Title: HomeServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月2日 下午9:13:11 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuguan.huate.service.FeeCountService;
import com.wuguan.huate.service.HomeService;
import com.wuguan.huate.service.OrderItemService;
import com.wuguan.huate.service.UserService;

/** 
* @ClassName: HomeServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月2日 下午9:13:11 
*  
*/
@Service
public class HomeServiceImpl implements HomeService {
	@Autowired
	UserService userService;
	@Autowired
	FeeCountService feeCountService;
	@Autowired
	OrderItemService orderItemService;
	/**
	 * 首页数量统计
	 */
	@Override
	public Object numberCount() {
		//今日注册用户
		Integer num=userService.todayRegisterNum(); 
		//物业欠费
		Double arrearage=feeCountService.todayArrearage();
		//今日物业缴费
		Double payFee=orderItemService.todayPropertyPayFee();
		//今日总缴费
		Double totalPayFee=orderItemService.todayTotalPayFee();
		Map<String,Object> map = new HashedMap<String, Object>();
		map.put("name", "今日注册用户");
		map.put("value", num==null?0:num);
		Map<String,Object> qf = new HashedMap<String, Object>();
		qf.put("name", "物业欠费");
		qf.put("value", arrearage==null?0:arrearage);
		Map<String,Object> jf = new HashedMap<String, Object>();
		jf.put("name", "今日物业缴费");
		jf.put("value", payFee==null?0:payFee);
		Map<String,Object> total = new HashedMap<String, Object>();
		total.put("name", "今日总缴费");
		total.put("value", totalPayFee==null?0:totalPayFee);
		List<Object> list = new ArrayList<Object>();
		list.add(map);
		list.add(qf);
		list.add(jf);
		list.add(total);
		return list;
	}

}
