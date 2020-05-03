/**   
* @Title: AppointRecordServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午10:13:55 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.entity.AppointRecord;
import com.wuguan.huate.bean.entity.LiveServe;
import com.wuguan.huate.bean.enums.AuditEnums;
import com.wuguan.huate.bean.params.AppointRecordPageParam;
import com.wuguan.huate.bean.vo.AppointRecordVo;
import com.wuguan.huate.bean.vo.UserVo;
import com.wuguan.huate.bean.vo.WorkerVo;
import com.wuguan.huate.comm.BaseService;
import com.wuguan.huate.comm.Constant;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.comm.SpringUtil;
import com.wuguan.huate.comm.ThreadLocalManager;
import com.wuguan.huate.db.AppointRecordMapper;
import com.wuguan.huate.quartz.DelayQueueManager;
import com.wuguan.huate.quartz.DelayTask;
import com.wuguan.huate.quartz.TaskBase;
import com.wuguan.huate.service.AppointRecordService;
import com.wuguan.huate.service.LiveServeService;
import com.wuguan.huate.service.WorkerService;
import com.wuguan.huate.utils.DateUtils;
import com.wuguan.huate.utils.RedisHelper;

/**
 * @ClassName: AppointRecordServiceImpl
 * @Description: 生活记录
 * @author LiuYanHong
 * @date 2020年4月9日 下午10:13:55
 * 
 */
@Service
@Transactional
public class AppointRecordServiceImpl implements AppointRecordService {
	@Autowired
	AppointRecordMapper appointRecordMapper;
	@Autowired
	BaseService baseService;
	@Autowired
	RedisHelper redisHelper;
	@Autowired
	LiveServeService liveServeService;
	@Autowired
	WorkerService workerService;
	@Autowired
	DelayQueueManager delayQueueManager;
	static final Logger log = LoggerFactory.getLogger("errorLogger");

	@Override
	public void addData(AppointRecord record) {
		record.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		UserVo vo = JSONObject.parseObject(bean.getUser().toString(), UserVo.class);
		record.setAppointId(vo.getId());
		appointRecordMapper.addData(record);
		// 添加到延时队列
		JSONArray array = JSONObject.parseArray(record.getMenu());
		String time = array.getJSONObject(1).getString("value");
		String[] ymd = time.split(" ");
		String[] hm = time.split(" ")[1].split("-");
		boolean boo = (DateUtils.getTimeMillis(ymd[0] + hm[0]) - new Date().getTime()) >= 1800000;
		if (boo) {
			long expire = DateUtils.getTimeMillis(ymd[0] + hm[0]) - 1800000;
			delayQueueManager.put(
					new DelayTask(new TaskBase(record.getId(), AuditEnums.DirectType.LIVESERVE.getValue()), expire));
		}
		Map<String, Object> params = null;
		try {
			LiveServe serve = liveServeService.detailLiveServe(record.getLiveId());
			if (serve.getAuditId() != null && !"".equals(serve.getAuditId())) {
				WorkerVo worker = (WorkerVo) workerService.detailData(serve.getAuditId());
				if (worker != null && worker.getOpenid() != null && !"".equals(worker.getOpenid())) {
					Boolean bool = redisHelper.existsKey("jy_accessToken");
					String accessToken;
					if (!bool) {
						accessToken = baseService.authGetAccessToken(Constant.XJ_APPID, Constant.XJ_SECRET);
						redisHelper.set("jy_accessToken", accessToken, 60 * 60 * 2);
					}
					accessToken = redisHelper.get("jy_accessToken");
					Map<String, Object> phrase1 = new HashMap<String, Object>();// 预约结果
					phrase1.put("value", "您有新的服务需要处理");
					Map<String, Object> date3 = new HashMap<String, Object>();// 时间
					date3.put("value", record.getCreateTime());
					params = new HashMap<String, Object>();
					params.put("date3", date3);
					params.put("phrase1", phrase1);
					baseService.subscribeMessageSend(accessToken, worker.getOpenid(),
							"pages/appointment/appdetails/appdetails?id=" + record.getId(), Constant.APPOINT_RESULT,
							params, Constant.XJ_APPID, Constant.XJ_SECRET);
				}
			}
		} catch (Exception e) {
			log.error("---------------推送消息失败----------------");
			log.error("推送数据：" + params.toString());
		}
	}

	@Override
	public AppointRecordVo detailData(Integer id) {
		return appointRecordMapper.detailData(id);
	}

	@Override
	public PageInfo<AppointRecordVo> pageData(AppointRecordPageParam param) {
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		UserVo vo = JSONObject.parseObject(bean.getUser().toString(), UserVo.class);
		param.setAppointId(vo.getId());
		PageHelper.startPage(param.getPage(), param.getRows());
		Page<AppointRecordVo> page = appointRecordMapper.pageData(param);
		return new PageInfo<AppointRecordVo>(page.getTotal(), page);
	}

	@Override
	public void updateData(AppointRecord record) {
		appointRecordMapper.updateData(record);
		Map<String, Object> params = null;
		try {
			AppointRecordVo vo = appointRecordMapper.detailData(record.getId());
			LiveServe serve = liveServeService.detailLiveServe(vo.getLiveId());
			if (serve.getAuditId() != null) {
				WorkerVo worker = (WorkerVo) workerService.detailData(serve.getAuditId());
				if (worker != null && worker.getOpenid() != null && !"".equals(worker.getOpenid())) {
					Boolean bool = redisHelper.existsKey("jy_accessToken");
					String accessToken;
					if (!bool) {
						accessToken = baseService.authGetAccessToken(Constant.XJ_APPID, Constant.XJ_SECRET);
						redisHelper.set("jy_accessToken", accessToken, 60 * 60 * 2);
					}
					accessToken = redisHelper.get("jy_accessToken");
					Map<String, Object> phrase1 = new HashMap<String, Object>();// 预约结果
					phrase1.put("value", "您有新的服务需要处理");
					Map<String, Object> date3 = new HashMap<String, Object>();// 时间
					date3.put("value", record.getCreateTime());
					params = new HashMap<String, Object>();
					params.put("date3", date3);
					params.put("phrase1", phrase1);
					baseService.subscribeMessageSend(accessToken, worker.getOpenid(),
							"pages/appointment/appdetails/appdetails?id=" + record.getId(), Constant.APPOINT_RESULT,
							params, Constant.XJ_APPID, Constant.XJ_SECRET);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("---------------推送消息失败----------------");
			log.error("推送数据：" + params.toString());
		}
	}

	@Override
	public List<AppointRecordVo> queryDelayData() {
		return appointRecordMapper.queryDelayData();
	}

}
