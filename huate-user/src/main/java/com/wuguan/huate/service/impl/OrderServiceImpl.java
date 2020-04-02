/**   
* @Title: OrderServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:22:42 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.entity.Order;
import com.wuguan.huate.bean.entity.OrderItem;
import com.wuguan.huate.bean.enums.FeeNormEnums;
import com.wuguan.huate.bean.enums.HouseEnums;
import com.wuguan.huate.bean.params.ConsumeOrderDetail;
import com.wuguan.huate.bean.params.OrderPageParam;
import com.wuguan.huate.bean.params.ParkOrderDetail;
import com.wuguan.huate.bean.params.PayRecordPageParam;
import com.wuguan.huate.bean.params.PropertyOrderDetail;
import com.wuguan.huate.bean.vo.OrderRecord;
import com.wuguan.huate.bean.vo.OrderVo;
import com.wuguan.huate.bean.vo.UserVo;
import com.wuguan.huate.comm.BaseService;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.comm.SpringUtil;
import com.wuguan.huate.comm.ThreadLocalManager;
import com.wuguan.huate.db.OrderMapper;
import com.wuguan.huate.service.FeeNormService;
import com.wuguan.huate.service.HouseConsumeService;
import com.wuguan.huate.service.HouseService;
import com.wuguan.huate.service.OrderItemService;
import com.wuguan.huate.service.OrderService;
import com.wuguan.huate.service.ParkService;
import com.wuguan.huate.service.UserService;
import com.wuguan.huate.utils.DateUtils;
import com.wuguan.huate.web.result.ResultEnums;

/** 
* @ClassName: OrderServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
 * @param <E>
* @date 2020年3月15日 下午10:22:42 
*  
*/
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	FeeNormService feeNormService;
	@Autowired
	OrderItemService orderItemService;
	@Autowired
	UserService userService;
	@Autowired
	HouseService houseService;
	@Autowired
	ParkService parkService;
	@Autowired
	BaseService baseService;
	@Autowired
	HouseConsumeService houseConsumeService;
	@Autowired
	OrderMapper orderMapper;

	@Override
	public Map<String, Object> addOrder(HttpServletRequest request,String data) throws CustomException, UnsupportedEncodingException {
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		JSONObject object = JSONObject.parseObject(data);
		UserVo user = JSONObject.parseObject(bean.getUser().toString(), UserVo.class);
		String openid = user.getOpenid();
		int amount = object.getIntValue("amount");
		String shopName=object.containsKey("shopName")?object.getString("shopName"):"";
		String houseNo=object.containsKey("houseNo")?object.getString("houseNo"):"";
		String carNo=object.containsKey("carNo")?object.getString("carNo"):"";
		String parkNo=object.containsKey("parkNo")?object.getString("parkNo"):"";
		JSONArray list = object.getJSONArray("data");
		//生成订单号
		String orderNo = DateUtils.getNo(new Date())+array.get(0);
		//删除用过的序列
		array.remove(0);
		//订单描述
		String desc="";
		List<OrderItem> items = new ArrayList<OrderItem>();
		int money=0;
		for (Object obj : list) {
			JSONObject info = JSONObject.parseObject(obj.toString());
			Object any = info.get("detail");
			if (info.getInteger("feeType")==FeeNormEnums.FeeTypeEnum.PROPERTY.getValue()||info.getInteger("feeType")==FeeNormEnums.FeeTypeEnum.RUBBISH.getValue()) {
				desc+="物业管理费";
				List<PropertyOrderDetail> details = JSON.parseArray(any.toString(), PropertyOrderDetail.class);
				for (PropertyOrderDetail detail : details) {
					OrderItem item = new OrderItem();
					String alias=(detail.getType()==HouseEnums.TypeEnum.BUSINESS.getValue())?shopName+"|":(detail.getHouseNo()!=null?detail.getHouseNo()+"|":"");
					item.setDesc(alias+detail.getName()+"|"+detail.getTimeFrame()+"|"+detail.getMoney()/100.0);
					item.setDueTime(detail.getDaoqi_time());
					item.setFeeTypeId(info.getInteger("feeType"));
					item.setMoney(detail.getMoney()/100.0);
					item.setNum(detail.getMonth());
					
					item.setOrderNo(orderNo);
					item.setRelationId(detail.getHouseId());
					item.setUserId(user.getId());
					item.setName(detail.getHouseNo()+detail.getName());
					items.add(item);
					money+=detail.getMoney();
				}
			}else if (info.getInteger("feeType")==FeeNormEnums.FeeTypeEnum.PARKRENT.getValue()) {
				desc+="|车位租赁费";
				List<ParkOrderDetail> details = JSON.parseArray(any.toString(), ParkOrderDetail.class);
				for (ParkOrderDetail detail : details) {
					OrderItem item = new OrderItem();
					item.setDesc((detail.getParkNo()!=null?detail.getParkNo()+"|":"")+detail.getName()+"|"+detail.getTimeFrame()+"|"+detail.getMoney()/100.0);
					item.setDueTime(detail.getDaoqi_time());
					item.setFeeTypeId(info.getInteger("feeType"));
					item.setMoney(detail.getMoney()/100.0);
					item.setNum(detail.getMonth());
					item.setOrderNo(orderNo);
					item.setRelationId(detail.getParkId());
					item.setUserId(user.getId());
					item.setName(detail.getParkNo()+detail.getName());
					items.add(item);
					money+=detail.getMoney();
				}	
			}else if (info.getInteger("feeType")==FeeNormEnums.FeeTypeEnum.PARKMANAGE.getValue()) {
				desc+="|车位管理费";
				List<ParkOrderDetail> details = JSON.parseArray(any.toString(), ParkOrderDetail.class);
				for (ParkOrderDetail detail : details) {
					OrderItem item = new OrderItem();
					item.setDesc((detail.getParkNo()!=null?detail.getParkNo()+"|":"")+detail.getName()+"|"+detail.getTimeFrame()+"|"+detail.getMoney()/100.0);
					item.setDueTime(detail.getDaoqi_time());
					item.setFeeTypeId(info.getInteger("feeType"));
					item.setMoney(detail.getMoney()/100.0);
					item.setNum(detail.getMonth());
					item.setOrderNo(orderNo);
					item.setRelationId(detail.getParkId());
					item.setUserId(user.getId());
					item.setName(detail.getParkNo()+detail.getName());
					items.add(item);
					money+=detail.getMoney();
				}
			}else if (info.getInteger("feeType")==FeeNormEnums.FeeTypeEnum.WATER.getValue()) {
				desc+="|水费";
				List<ConsumeOrderDetail> details = JSON.parseArray(any.toString(), ConsumeOrderDetail.class);
				for (ConsumeOrderDetail detail : details) {
					OrderItem item = new OrderItem();
					String alias=(detail.getType()==HouseEnums.TypeEnum.BUSINESS.getValue())?shopName+"|":(detail.getHouseNo()!=null?detail.getHouseNo()+"|":"");
					item.setDesc(alias+detail.getMonth()+FeeNormEnums.FeeTypeEnum.getByValue(info.getInteger("feeType")).getName()+"|"+detail.getMoney()/100.0);
					//item.setDueTime(detail.getMonth());
					item.setFeeTypeId(info.getInteger("feeType"));
					item.setMoney(detail.getMoney()/100.0);
					item.setOrderNo(orderNo);
					item.setRelationId(detail.getConsumeId());
					item.setUserId(user.getId());
					item.setName(detail.getHouseNo()+FeeNormEnums.FeeTypeEnum.getByValue(info.getInteger("feeType")).getName());
					items.add(item);
					money+=detail.getMoney();
				}
			}else if (info.getInteger("feeType")==FeeNormEnums.FeeTypeEnum.ELECTRIC.getValue()) {
				desc+="|电费";
				List<ConsumeOrderDetail> details = JSON.parseArray(any.toString(), ConsumeOrderDetail.class);
				for (ConsumeOrderDetail detail : details) {
					OrderItem item = new OrderItem();
					String alias=(detail.getType()==HouseEnums.TypeEnum.BUSINESS.getValue())?shopName+"|":(detail.getHouseNo()!=null?detail.getHouseNo()+"|":"");
					item.setDesc(alias+detail.getMonth()+FeeNormEnums.FeeTypeEnum.getByValue(info.getInteger("feeType")).getName()+"|"+detail.getMoney()/100.0);
					//item.setDueTime(detail.getMonth());
					item.setFeeTypeId(info.getInteger("feeType"));
					item.setMoney(detail.getMoney()/100.0);
					item.setOrderNo(orderNo);
					item.setRelationId(detail.getConsumeId());
					item.setUserId(user.getId());
					item.setName(detail.getHouseNo()+FeeNormEnums.FeeTypeEnum.getByValue(info.getInteger("feeType")).getName());
					items.add(item);
					money+=detail.getMoney();
				}
			}
		}
		if (amount!=money) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(),"共计金额异常");
		}
		//添加订单明细
		orderItemService.addBatch(items);
		//生成订单
		Order order = new Order();
		String content=!shopName.equals("")?(shopName+"|"+(desc.startsWith("|")?desc.substring(1):desc)):(desc.startsWith("|")?desc.substring(1):desc);
		order.setDesc(content);
		order.setMoney(money/100.0);
		order.setOrderNo(orderNo);
		order.setUserId(user.getId());
		order.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		order.setHouseNo(houseNo);
		order.setCarNo(carNo);
		order.setParkNo(parkNo);
		orderMapper.addOrder(order);
		//微信后台下单	
		Map<String, Object> map = baseService.createOrder(request, desc, orderNo, money, openid);
		if (map==null) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "微信后台统一下单失败");
		}
		map.put("orderNo", orderNo);
		map.put("orderId", order.getId());
		//修改订单加上支付ID
		String payId = map.get("package").toString();
		String substring = payId.substring(payId.indexOf("=")+1, payId.length());
		orderMapper.updateOrderPayId(order.getId(), substring);
		return map;
	}
	
	/**
	 * @param bit 多少位
	 * @param num 多少个
	 * bit 要大于 num 的长度
	 */
	@Override
	public void createRandom(Integer bit,Integer num) {
		for (int i = 0; i < num; i++) {
			String s = Integer.toString(i);
			String b="";
			for (int j = 0; j < bit-s.length(); j++) {
				b+="0";
			}
			s=b+s;
			array.add(s);
		}
	}

	/**
	 * 关闭订单
	 */
	@Override
	public void closeOrder(String orderNo,Integer sate) {
		baseService.closeOrders(orderNo);
		orderMapper.closeOrder(orderNo,sate);
	}

	/**
	 * 修改订单状态
	 */
	@Override
	public void updateOrder(String orderNo, String payTime) {
		//修改訂單狀態
		orderMapper.updateOrder(orderNo,payTime);
		//更新房屋表|車位表|水電消耗表
		List<OrderItem> items = orderItemService.getOrderItemByOrderNo(orderNo);
		for (OrderItem item : items) {
			if (item.getFeeTypeId()==FeeNormEnums.FeeTypeEnum.PROPERTY.getValue()||item.getFeeTypeId()==FeeNormEnums.FeeTypeEnum.RUBBISH.getValue()) {
				//房屋表
				houseService.updateDueTime(item.getDueTime(),item.getRelationId());
			}else if (item.getFeeTypeId()==FeeNormEnums.FeeTypeEnum.PARKRENT.getValue()) {
				//車位表
				parkService.updateDueTime(item.getDueTime(),item.getRelationId());
			}else if (item.getFeeTypeId()==FeeNormEnums.FeeTypeEnum.WATER.getValue()||item.getFeeTypeId()==FeeNormEnums.FeeTypeEnum.ELECTRIC.getValue()) {
				//水電消耗表
				houseConsumeService.updatePayTime(payTime,item.getRelationId());
			}
		}
	}

	/**
	 * 查询所有未关闭，支付状态不明确的订单
	 */
	@Override
	public List<Order> checkOrder() {
		return orderMapper.checkOrder();
	}

	/**
	 * 分页查询
	 */
	@Override
	public PageInfo<OrderVo> pageData(OrderPageParam param) {
		PageHelper.startPage(param.getPage(), param.getRows());
		Page<Order> page=orderMapper.pageData(param);
		List<OrderVo> list = new ArrayList<OrderVo>();
		for (Order order : page) {
			OrderVo vo = new OrderVo();
			BeanUtils.copyProperties(order, vo);
			vo.setPhone(userService.detail(order.getUserId()).getPhone());
			list.add(vo);
		}
		return new PageInfo<OrderVo>(page.getTotal(), list);
	}

	/**
	 * 订单详情
	 */
	@Override
	public Object detailData(Integer id) {
		OrderVo vo = new OrderVo();
		Order order=orderMapper.detail(id);
		BeanUtils.copyProperties(order, vo);
		List<OrderItem> list = orderItemService.getOrderItemByOrderNo(order.getOrderNo());
		vo.setItems(list);
		vo.setPhone(userService.detail(order.getUserId()).getPhone());
		return vo;
	}
	
	/**
	 * 订单详情
	 */
	@Override
	public Object detailDataByOrderNo(String orderNo) {
		OrderVo vo = new OrderVo();
		Order order=orderMapper.detailDataByOrderNo(orderNo);
		BeanUtils.copyProperties(order, vo);
		List<OrderItem> list = orderItemService.getOrderItemByOrderNo(order.getOrderNo());
		vo.setItems(list);
		vo.setPhone(userService.detail(order.getUserId()).getPhone());
		return vo;
	}

	@Override
	public List<Order> getOrders(OrderPageParam param) {
		return orderMapper.getOrders(param);
	}

	/**
	 * 物业|生活垃圾缴费记录
	 */
	@Override
	public PageInfo<OrderRecord> wyLjPayRecords(PayRecordPageParam param) {
		PageHelper.startPage(param.getPage(), param.getRows());
		Page<OrderRecord> page=orderMapper.wyLjPayRecords(param);
		return new PageInfo<OrderRecord>(page.getTotal(), page);
	}

	/**
	 * 水电缴费记录
	 */
	@Override
	public PageInfo<OrderRecord> sdPayRecords(PayRecordPageParam param) {
		PageHelper.startPage(param.getPage(), param.getRows());
		Page<OrderRecord> page=orderMapper.sdPayRecords(param);
		return new PageInfo<OrderRecord>(page.getTotal(), page);
	}

	/**
	 * 车位管理|车位租赁缴费记录
	 */
	@Override
	public PageInfo<OrderRecord> carPayRecords(PayRecordPageParam param) {
		PageHelper.startPage(param.getPage(), param.getRows());
		Page<OrderRecord> page=orderMapper.carPayRecords(param);
		return new PageInfo<OrderRecord>(page.getTotal(), page);
	}

	@Override
	public Object pageDataByUser(OrderPageParam param) {
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		UserVo user = JSONObject.parseObject(bean.getUser().toString(), UserVo.class);
		PageHelper.startPage(param.getPage(), param.getRows());
		Page<Order> page=orderMapper.pageDataByUser(param,user.getId());
		List<OrderVo> list = new ArrayList<OrderVo>();
		for (Order order : page) {
			OrderVo vo = new OrderVo();
			BeanUtils.copyProperties(order, vo);
			vo.setPhone(userService.detail(order.getUserId()).getPhone());
			list.add(vo);
		}
		return new PageInfo<OrderVo>(page.getTotal(), list);
	}

	@Override
	public void closeOrder(String orderNo) {
		// TODO Auto-generated method stub
		
	}

}
