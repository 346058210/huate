/**   
* @Title: QuartzTask.java 
* @Package com.wuguan.huate.quartz 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月24日 上午1:22:07 
* @version V1.0   
*/
package com.wuguan.huate.quartz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.wuguan.huate.bean.entity.Order;
import com.wuguan.huate.bean.entity.User;
import com.wuguan.huate.comm.BaseService;
import com.wuguan.huate.comm.Constant;
import com.wuguan.huate.comm.SpringUtil;
import com.wuguan.huate.service.OrderService;
import com.wuguan.huate.service.SendMessageService;
import com.wuguan.huate.service.UserService;
import com.wuguan.huate.utils.DateUtils;
import com.wuguan.huate.utils.RedisHelper;

/** 
* @ClassName: QuartzTask 
* @Description: 定时任务
* @author LiuYanHong
* @date 2020年3月24日 上午1:22:07 
*  
*/
@Component
@EnableScheduling
public class QuartzTask extends BaseService{
	@Autowired
	OrderService orderService;
	@Autowired
	UserService userService;
	@Autowired
	SendMessageService sendMessageService;
	
	/**
	 * 
	* @Title: createRandom
	* @Description: 生产数字序列
	 */
	@Scheduled(fixedRate = 1000*30)
	public void createRandom() {
		OrderService.array.clear();
		orderService.createRandom(6, 10000);
	}
	
	/**
	 * 
	* @Title: createRandom
	* @Description: 检查到期物业，通知缴费
	 */
	//@Scheduled(cron ="0 0 18 * * ?")
	//public void checkExpireHouse() {
	//	sendMessageService.propertyPayFeeMessageSend();
	//}
	
	/**
	 * 
	* @Title: createRandom
	* @Description: 检查到期车位，通知缴费
	 */
	//@Scheduled(cron ="0 0 18 * * ?")
	//public void checkExpirePark() {
	//	sendMessageService.ParkPayFeeMessageSend();
	//}
	
	/**
	 * 
	* @throws ParseException 
	 * @Title: checkOrder
	* @Description: 檢測訂單，支付失敗|超時的關閉
	 */
	@Scheduled(fixedRate = 1000*60*5)
	public void checkOrder() throws ParseException {
		List<Order> orders=orderService.checkOrder();
		for (Order order : orders) {
			Map<String, Object> map = super.queryOrder(order.getOrderNo());
			String return_code=(String)map.get("return_code");
			if ("SUCCESS".equals(return_code)) {
				String resultCode=(String)map.get("result_code");
				if ("SUCCESS".equals(resultCode)) {
					String tradeState=(String)map.get("trade_state");
					String orderNo = (String) map.get("out_trade_no");
					if ("SUCCESS".equals(tradeState)) {
						String payTime = DateUtils.convertTime((String) map.get("time_end"));
						// 支付成功-修改订单状态
						// 修改相关表的到期时间（物业|停车|水电消耗）
						orderService.updateOrder(orderNo, payTime);
						//推送通知
						RedisHelper bean = SpringUtil.getBean(RedisHelper.class);
						Boolean bool = bean.existsKey("accessToken");
						String accessToken="";
						if (!bool) {
							accessToken = authGetAccessToken(Constant.XJ_APPID, Constant.XJ_SECRET);
							bean.set("accessToken", accessToken, 60*60*2);
						}
						accessToken=bean.get("accessToken");
						//
						User user=userService.getUserByOrderNo(orderNo);
						//OrderVo order = (OrderVo) orderService.detailDataByOrderNo(orderNo);
						Map<String,Object> thing = new HashMap<String, Object>();
						thing.put("value", order.getDesc());
						Map<String,Object> amount = new HashMap<String, Object>();
						amount.put("value", order.getMoney()+"元");
						Map<String,Object> phrase = new HashMap<String, Object>();
						phrase.put("value", "缴费成功");
						Map<String,Object> date = new HashMap<String, Object>();
						date.put("value", payTime);
						Map<String,Object> params = new HashMap<String, Object>();
						params.put("thing1", thing);
						params.put("amount2", amount);
						params.put("phrase4", phrase);
						params.put("date3", date);
						String data = JSONObject.toJSON(params).toString();
						super.subscribeMessageSend(accessToken, user.getOpenid(),"pages/order/details/details?id="+orderNo,Constant.PAY_TEMPLATEID, data);
					}else if ("PAYERROR".equals(tradeState)) {
						orderService.closeOrder(orderNo, 0);
					}else if ("NOTPAY".equals(tradeState)) {
						Boolean bool= (System.currentTimeMillis()/1000-new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(order.getCreateTime()).getTime()/1000)>=300?true:false;
						if (bool) {//超时
							orderService.closeOrder(orderNo,null);
						}
					}
				}
			}
		}
	}

}
