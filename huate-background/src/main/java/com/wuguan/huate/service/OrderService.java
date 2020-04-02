/**   
* @Title: OrderService.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:15:18 
* @version V1.0   
*/
package com.wuguan.huate.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wuguan.huate.bean.entity.Order;
import com.wuguan.huate.bean.params.OrderPageParam;
import com.wuguan.huate.bean.params.PayRecordPageParam;
import com.wuguan.huate.bean.vo.OrderRecord;
import com.wuguan.huate.bean.vo.OrderVo;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.comm.PageInfo;

/** 
* @ClassName: OrderService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:15:18 
*  
*/
public interface OrderService {
	
	public static List<String> array = new ArrayList<String>();
	
	void createRandom(Integer bit,Integer num);
	
	Map<String, Object> addOrder(HttpServletRequest request,String data)throws CustomException, UnsupportedEncodingException;

	/**
	* @Title: closeOrder
	* @Description: 关闭订单
	* @param orderNo
	*/
	void closeOrder(String orderNo);

	/**
	* @Title: updateOrder
	* @Description: 修改订单
	* @param orderNo
	* @param payTime
	*/
	void updateOrder(String orderNo, String payTime);

	/**
	* @Title: checkOrder
	* @Description: 检测订单
	* @return
	*/
	List<Order> checkOrder();

	/**
	* @Title: closeOrder
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param orderNo
	* @param sate
	*/
	void closeOrder(String orderNo, Integer sate);

	/**
	* @Title: pageData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param param
	* @return
	*/
	PageInfo<OrderVo> pageData(OrderPageParam param);

	/**
	* @Title: detailData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	Object detailData(Integer id);

	/**
	* @Title: getOrders
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param param
	* @return
	*/
	List<Order> getOrders(OrderPageParam param);

	/**
	* @Title: payRecords
	* @Description: 物业、生活垃圾缴费
	* @param param
	* @return
	*/
	PageInfo<OrderRecord> wyLjPayRecords(PayRecordPageParam param);

	/**
	* @Title: sdPayRecords
	* @Description: 水电缴费记录
	* @param param
	* @return
	*/
	PageInfo<OrderRecord> sdPayRecords(PayRecordPageParam param);

	/**
	* @Title: carPayRecords
	* @Description: 车位管理|车位租赁缴费记录
	* @param param
	* @return
	*/
	PageInfo<OrderRecord> carPayRecords(PayRecordPageParam param);

	/**
	* @Title: detailDataByOrderNo
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param orderNo
	* @return
	*/
	Object detailDataByOrderNo(String orderNo);

	/**
	* @Title: pageDataByUser
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param param
	* @return
	*/
	Object pageDataByUser(OrderPageParam param);

}
