/**   
* @Title: DelayQueueManager.java 
* @Package com.wuguan.huate.task 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月28日 上午1:13:08 
* @version V1.0   
*/
package com.wuguan.huate.quartz;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wuguan.huate.bean.entity.Repairs;
import com.wuguan.huate.bean.enums.AuditEnums;
import com.wuguan.huate.bean.vo.AppointRecordVo;
import com.wuguan.huate.bean.vo.RepairsVo;
import com.wuguan.huate.bean.vo.UserVo;
import com.wuguan.huate.bean.vo.WorkerVo;
import com.wuguan.huate.comm.BaseService;
import com.wuguan.huate.comm.Constant;
import com.wuguan.huate.service.AppointRecordService;
import com.wuguan.huate.service.RepairsService;
import com.wuguan.huate.service.UserService;
import com.wuguan.huate.service.WorkerService;
import com.wuguan.huate.utils.DateUtils;
import com.wuguan.huate.utils.RedisHelper;
import com.wuguan.huate.quartz.DelayTask;

/**
 * @ClassName: DelayQueueManager
 * @Description: 启动完成之后调用run方法
 * @author LiuYanHong
 * @date 2020年4月28日 上午1:13:08
 * 
 */
@Component
public class DelayQueueManager implements CommandLineRunner {

	private DelayQueue<DelayTask> delayQueue = new DelayQueue<>();
	@Autowired
	AppointRecordService appointRecordService;
	@Autowired
	RepairsService repairsService;
	@Autowired
	WorkerService workerService;
	@Autowired
	UserService userService;
	@Autowired
	RedisHelper redisHelper;
	@Autowired
	BaseService baseService;

	/**
	 * 加入到延时队列中
	 * 
	 * @param task
	 */
	public void put(DelayTask task) {
		delayQueue.put(task);
	}

	@Override
	public void run(String... args) throws Exception {
		List<AppointRecordVo> list = appointRecordService.queryDelayData();
		if (list.size() != 0) {
			for (AppointRecordVo appointRecordVo : list) {
				JSONArray array = JSONObject.parseArray(appointRecordVo.getMenu());
				String time = array.getJSONObject(1).getString("value");
				String[] ymd = time.split(" ");
				String[] hm = time.split(" ")[1].split("-");
				boolean bool = (DateUtils.getTimeMillis(ymd[0] + hm[0]) - new Date().getTime()) >= 1800000;
				if (bool)//
					put(new DelayTask(new TaskBase(appointRecordVo.getId(), AuditEnums.DirectType.LIVESERVE.getValue()),
							(DateUtils.getTimeMillis(ymd[0] + hm[0])) - 1800000));
				boolean boo = DateUtils.inMiddle2(ymd[0] + hm[1], new Date());// true 未过期 false 过期
				if (!bool && boo)
					put(new DelayTask(new TaskBase(appointRecordVo.getId(), AuditEnums.DirectType.LIVESERVE.getValue()),
							System.currentTimeMillis()));// 当前时间大于第一预约时间小于第二预约时间
			}
		}
		List<Repairs> data = repairsService.queryDelayData();
		if (data.size() != 0) {
			for (Repairs repairs : data) {
				String appointmentTime = repairs.getAppointmentTime();
				String[] split = appointmentTime.split(" ");
				String[] time = split[1].split("-");
				boolean bool = (DateUtils.getTimeMillis(split[0] + time[0]) - new Date().getTime()) >= 1800000;
				if (bool) {
					put(new DelayTask(new TaskBase(repairs.getId(), AuditEnums.DirectType.REPAIRS.getValue()),
							(DateUtils.getTimeMillis(split[0] + time[0])) - 1800000));// 当前时间小于第一预约时间30分钟及以上
				} else {
					put(new DelayTask(new TaskBase(repairs.getId(), AuditEnums.DirectType.REPAIRS.getValue()),
							System.currentTimeMillis()));// 当前时间大于第一预约时间小于第二预约时间
				}

			}
		}
		Executors.newSingleThreadExecutor().execute(new Thread(this::excuteThread));
	}

	/**
	 * 延时任务执行线程
	 */
	private void excuteThread() {
		while (true) {
			try {
				DelayTask task = delayQueue.take();
				processTask(task);
			} catch (InterruptedException e) {
				continue;
			}
		}
	}

	/**
	 * @Title: processTask
	 * @Description: 执行延时任务（具体业务逻辑）
	 * @param task
	 */
	private void processTask(DelayTask task) {
		TaskBase base = task.getBase();
		if (base.getType() == AuditEnums.DirectType.LIVESERVE.getValue()) {// 生活服务
			// 工作人员提醒
			AppointRecordVo detailData = appointRecordService.detailData(base.getId());
			try {
				if (detailData.getAuditId() != null && !"".equals(detailData.getAuditId())) {
					WorkerVo worker = (WorkerVo) workerService.detailData(detailData.getAuditId());
					if (worker != null && worker.getOpenid() != null && !"".equals(worker.getOpenid())) {
						Boolean bool = redisHelper.existsKey("jy_accessToken");
						String accessToken;
						if (!bool) {
							accessToken = baseService.authGetAccessToken(Constant.JY_APPID, Constant.JY_SECRET);
							redisHelper.set("jy_accessToken", accessToken, 60 * 60 * 2);
						}
						accessToken = redisHelper.get("jy_accessToken");
						Map<String, Object> phrase1 = new HashMap<String, Object>();// 预约结果
						phrase1.put("value", "您有新的服务需要处理");
						Map<String, Object> date3 = new HashMap<String, Object>();// 时间
						date3.put("value", detailData.getCreateTime());
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("date3", date3);
						params.put("phrase1", phrase1);
						baseService.subscribeMessageSend(accessToken, worker.getOpenid(),
								"pages/appointment/appdetails/appdetails?id=" + detailData.getId(),
								Constant.APPOINT_RESULT, params, Constant.JY_APPID, Constant.JY_SECRET);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 客户提醒
			try {
				UserVo detail = userService.detail(detailData.getAppointId());
				if (detail != null && detail.getOpenid() != null && !"".equals(detail.getOpenid())) {
					Boolean bool = redisHelper.existsKey("accessToken");
					String accessToken;
					if (!bool) {
						accessToken = baseService.authGetAccessToken(Constant.XJ_APPID, Constant.XJ_SECRET);
						redisHelper.set("accessToken", accessToken, 60 * 60 * 2);
					}
					accessToken = redisHelper.get("accessToken");
					JSONArray array = JSONObject.parseArray(detailData.getMenu());
					String time = array.getJSONObject(1).getString("value");
					Map<String, Object> thing3 = new HashMap<String, Object>();// 预约时间
					thing3.put("value", time);
					Map<String, Object> thing2 = new HashMap<String, Object>();// 时间
					thing2.put("value", "您的生活预约服务预约时间将到");
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("thing3", thing3);
					params.put("thing2", thing2);
					baseService.subscribeMessageSend(accessToken, detail.getOpenid(),
							"pages/appointment/appdetails/appdetails?id=" + detailData.getId(), Constant.RESERVE_TX,
							params, Constant.XJ_APPID, Constant.XJ_SECRET);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (base.getType() == AuditEnums.DirectType.REPAIRS.getValue()) {// 报损报修
			RepairsVo detailData = repairsService.detailData(base.getId());
			try {// 工作人员提醒
				WorkerVo worker;
				if (detailData.getHandlerId() != null) {
					worker = (WorkerVo) workerService.detailData(detailData.getHandlerId());
				} else {
					worker = (WorkerVo) workerService.detailData(detailData.getAuditId());
				}
				if (worker != null && worker.getOpenid() != null && !"".equals(worker.getOpenid())) {
					Boolean bool = redisHelper.existsKey("jy_accessToken");
					String accessToken;
					if (!bool) {
						accessToken = baseService.authGetAccessToken(Constant.JY_APPID, Constant.JY_SECRET);
						redisHelper.set("jy_accessToken", accessToken, 60 * 60 * 2);
					}
					accessToken = redisHelper.get("jy_accessToken");
					Map<String, Object> thing1 = new HashMap<String, Object>();// 报修位置
					thing1.put("value", detailData.getServeAddr());
					Map<String, Object> date3 = new HashMap<String, Object>();// 时间
					date3.put("value", detailData.getCreateTime());
					Map<String, Object> thing4 = new HashMap<String, Object>();// 注意事项
					thing4.put("value", "您有新的报修，请及时处理");
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("thing1", thing1);
					params.put("date3", date3);
					params.put("thing4", thing4);
					baseService.subscribeMessageSend(accessToken, worker.getOpenid(),
							"pages/repairsdetails/redetalils/redetalils?id=" + detailData.getId(), Constant.REPAIRDS,
							params, Constant.JY_APPID, Constant.JY_SECRET);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 客户提醒
			try {
				if (detailData.getReportType() == 1) {
					UserVo detail = userService.detail(detailData.getReportId());
					if (detail != null && detail.getOpenid() != null && !"".equals(detail.getOpenid())) {
						Boolean bool = redisHelper.existsKey("accessToken");
						String accessToken;
						if (!bool) {
							accessToken = baseService.authGetAccessToken(Constant.XJ_APPID, Constant.XJ_SECRET);
							redisHelper.set("accessToken", accessToken, 60 * 60 * 2);
						}
						accessToken = redisHelper.get("accessToken");
						Map<String, Object> thing3 = new HashMap<String, Object>();// 预约时间
						thing3.put("value", detailData.getAppointmentTime());
						Map<String, Object> thing2 = new HashMap<String, Object>();// 时间
						thing2.put("value", "您的报修服务预约时间将到");
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("thing3", thing3);
						params.put("thing2", thing2);
						baseService.subscribeMessageSend(accessToken, detail.getOpenid(),
								"pages/appointment/appdetails/appdetails?id=" + detailData.getId(), Constant.RESERVE_TX,
								params, Constant.XJ_APPID, Constant.XJ_SECRET);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
