/**   
* @Title: SendMessageServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月29日 下午11:44:48 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuguan.huate.bean.entity.House;
import com.wuguan.huate.bean.entity.Park;
import com.wuguan.huate.bean.vo.HouseM;
import com.wuguan.huate.bean.vo.ParkM;
import com.wuguan.huate.bean.vo.UserVo;
import com.wuguan.huate.comm.BaseService;
import com.wuguan.huate.comm.Constant;
import com.wuguan.huate.service.HouseService;
import com.wuguan.huate.service.ParkService;
import com.wuguan.huate.service.SendMessageService;
import com.wuguan.huate.utils.RedisHelper;

/**
 * @ClassName: SendMessageServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年3月29日 下午11:44:48
 * 
 */
@Service
@Transactional
public class SendMessageServiceImpl implements SendMessageService {
	@Autowired
	RedisHelper redisHelper;
	@Autowired
	HouseService houseService;
	@Autowired
	ParkService parkService;
	@Autowired
	BaseService baseService;
	final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);
	String accessToken = "";

	/**
	 * 物业缴费通知
	 */
	@Override
	public void propertyPayFeeMessageSend() {
		List<HouseM> houses = houseService.queryExpireHouse();
		if (houses.size() != 0) {
			for (HouseM houseM : houses) {
				Boolean bool = redisHelper.existsKey("accessToken");
				if (!bool) {
					accessToken = baseService.authGetAccessToken(Constant.XJ_APPID, Constant.XJ_SECRET);
					redisHelper.set("accessToken", accessToken, 60 * 60 * 2);
				}
				accessToken = redisHelper.get("accessToken");
				//
				// Map<String,Object> thing2 = new HashMap<String, Object>();//单元号房号
				// thing2.put("value",
				// houseM.getBuilding()+"栋"+houseM.getUnit()+"单元"+houseM.getRoom());
				Map<String, Object> thing3 = new HashMap<String, Object>();// 缴费类型 物业费
				thing3.put("value",
						houseM.getBuilding() + "栋" + houseM.getUnit() + "单元" + houseM.getRoom() + "物业管理服务费");
				Map<String, Object> thing7 = new HashMap<String, Object>();// 公司名称
				thing7.put("value", "四川华特物业服务有限公司");
				Map<String, Object> thing5 = new HashMap<String, Object>();// 温馨提示
				thing5.put("value", "您好，您的物业费即将到期，请及时缴纳费用");
				Map<String, Object> params = new HashMap<String, Object>();
				// params.put("thing2", thing2);
				params.put("thing3", thing3);
				params.put("thing7", thing7);
				params.put("thing5", thing5);
				scheduledThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						baseService.subscribeMessageSend(accessToken, houseM.getOpenid(),
								"pages/order/order/order?houseNo=" + houseM.getHouseNo() + "&feeType=1",
								Constant.PROPERTY_PAY_TEMPLATEID, params, Constant.XJ_APPID, Constant.XJ_SECRET);
						House house = new House();
						house.setId(houseM.getId());
						house.setNoticeTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						houseService.updateNoticeTime(house);
					}
				});

			}
		}
	}

	/**
	 * 车位缴费通知
	 */
	@Override
	public void ParkPayFeeMessageSend() {
		List<ParkM> parks = parkService.queryExpirePark();
		if (parks.size() != 0) {
			for (ParkM parkM : parks) {
				Boolean bool = redisHelper.existsKey("accessToken");
				if (!bool) {
					accessToken = baseService.authGetAccessToken(Constant.XJ_APPID, Constant.XJ_SECRET);
					redisHelper.set("accessToken", accessToken, 60 * 60 * 2);
				}
				accessToken = redisHelper.get("accessToken");
				// Map<String,Object> thing2 = new HashMap<String, Object>();//车牌车位
				// thing2.put("value",
				// parkM.getCarNo()!=null?parkM.getCarNo():parkM.getParkNo());
				Map<String, Object> thing3 = new HashMap<String, Object>();// 缴费类型 物业费
				thing3.put("value", parkM.getCarNo() != null ? parkM.getCarNo() : parkM.getParkNo() + "停车费");
				Map<String, Object> thing7 = new HashMap<String, Object>();// 公司名称
				thing7.put("value", "四川华特物业服务有限公司");
				Map<String, Object> thing5 = new HashMap<String, Object>();// 温馨提示
				thing5.put("value", "您好，您的物业费即将到期，请及时缴纳费用");
				Map<String, Object> params = new HashMap<String, Object>();
				// params.put("thing2", thing2);
				params.put("thing3", thing3);
				params.put("thing7", thing7);
				params.put("thing5", thing5);
				scheduledThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						baseService.subscribeMessageSend(accessToken, parkM.getOpenid(),
								"pages/park/order/order?parkNo=" + parkM.getParkNo(), Constant.PROPERTY_PAY_TEMPLATEID,
								params, Constant.XJ_APPID, Constant.XJ_SECRET);
						Park park = new Park();
						park.setId(parkM.getId());
						park.setNoticeTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						parkService.updateNoticeTime(park);
					}
				});
			}
		}

	}

	/**
	 * 通知公告
	 */
	@Override
	public void notificationMessageSend(List<UserVo> user, String publisher, String content, String publishTime) {
		if (user.size() != 0) {
			for (UserVo userVo : user) {
				Boolean bool = redisHelper.existsKey("accessToken");
				if (!bool) {
					accessToken = baseService.authGetAccessToken(Constant.XJ_APPID, Constant.XJ_SECRET);
					redisHelper.set("accessToken", accessToken, 60 * 60 * 2);
				}
				accessToken = redisHelper.get("accessToken");
				Map<String, Object> name1 = new HashMap<String, Object>();// 发布人
				name1.put("value", publisher);
				Map<String, Object> date2 = new HashMap<String, Object>();// 发布时间
				date2.put("value", publishTime);
				Map<String, Object> thing3 = new HashMap<String, Object>();// 内容
				thing3.put("value", content);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("name1", name1);
				params.put("date2", date2);
				params.put("thing3", thing3);
				scheduledThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						baseService.subscribeMessageSend(accessToken, userVo.getOpenid(), "pages/home/home",
								Constant.NOTICE_TEMPLATEID, params, Constant.XJ_APPID, Constant.XJ_SECRET);
					}
				});
			}
		}

	}

}
