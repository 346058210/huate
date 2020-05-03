/**   
* @Title: RepairsServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月11日 下午5:47:35 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.entity.Audit;
import com.wuguan.huate.bean.entity.Handle;
import com.wuguan.huate.bean.entity.Repairs;
import com.wuguan.huate.bean.enums.AuditEnums;
import com.wuguan.huate.bean.params.RepairsPageParam;
import com.wuguan.huate.bean.vo.RepairsVo;
import com.wuguan.huate.bean.vo.UserVo;
import com.wuguan.huate.bean.vo.WorkerVo;
import com.wuguan.huate.comm.BaseService;
import com.wuguan.huate.comm.Constant;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.comm.SpringUtil;
import com.wuguan.huate.comm.ThreadLocalManager;
import com.wuguan.huate.db.RepairsMapper;
import com.wuguan.huate.quartz.DelayQueueManager;
import com.wuguan.huate.quartz.DelayTask;
import com.wuguan.huate.quartz.TaskBase;
import com.wuguan.huate.service.AuditService;
import com.wuguan.huate.service.HandleService;
import com.wuguan.huate.service.RepairsService;
import com.wuguan.huate.service.UserService;
import com.wuguan.huate.service.WorkerService;
import com.wuguan.huate.utils.DateUtils;
import com.wuguan.huate.utils.RedisHelper;

/**
 * @ClassName: RepairsServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月11日 下午5:47:35
 * 
 */
@Service
@Transactional
public class RepairsServiceImpl implements RepairsService {
	@Autowired
	RepairsMapper repairsMapper;
	@Autowired
	UserService userService;
	@Autowired
	HandleService handleService;
	@Autowired
	AuditService auditService;
	@Autowired
	BaseService baseService;
	@Autowired
	RedisHelper redisHelper;
	@Autowired
	WorkerService workerService;
	@Autowired
	DelayQueueManager delayQueueManager;
	static final Logger log = LoggerFactory.getLogger("errorLogger");

	@Override
	public void updateData(Repairs repairs) {
		// 新增处理流程
		Handle handle = new Handle();
		handle.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		handle.setHandleResult("您的报修已撤销，如有疑问请联系客服");
		handle.setRepairsId(repairs.getId());
		handleService.addData(handle);
		repairsMapper.updateData(repairs);
		Map<String, Object> params = null;
		try {
			Repairs data = repairsMapper.detailData(repairs.getId());
			WorkerVo worker = null;
			if (data.getHandlerId() != null) {
				worker = (WorkerVo) workerService.detailData(data.getHandlerId());
			} else if (data.getAuditId() != null) {
				worker = (WorkerVo) workerService.detailData(data.getAuditId());
			}
			if (worker != null && worker.getOpenid() != null && !"".equals(worker.getOpenid())) {
				Boolean bool = redisHelper.existsKey("jy_accessToken");
				String jy_accessToken = "";
				if (!bool) {
					jy_accessToken = baseService.authGetAccessToken(Constant.JY_APPID, Constant.JY_SECRET);
					redisHelper.set("jy_accessToken", jy_accessToken, 60 * 60 * 2);
				}
				jy_accessToken = redisHelper.get("jy_accessToken");
				params = new HashMap<String, Object>();
				Map<String, Object> thing1 = new HashMap<String, Object>();
				thing1.put("value", repairs.getServeAddr());
				Map<String, Object> date3 = new HashMap<String, Object>();
				date3.put("value", repairs.getCreateTime());
				Map<String, Object> thing4 = new HashMap<String, Object>();
				thing4.put("value", "有新的报修，请尽快处理");
				params.put("thing1", thing1);
				params.put("date3", date3);
				params.put("thing4", thing4);
				baseService.subscribeMessageSend(jy_accessToken, worker.getOpenid(),
						"pages/repairsdetails/redetalils/redetalils?id=" + repairs.getId(), Constant.REPAIRDS, data,
						Constant.JY_APPID, Constant.JY_SECRET);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("---------------推送消息失败----------------");
			log.error("推送数据：" + params.toString());
		}
	}

	@Override
	public RepairsVo detailData(Integer id) {
		Repairs repairs = repairsMapper.detailData(id);
		RepairsVo repairsVo = new RepairsVo();
		BeanUtils.copyProperties(repairs, repairsVo);
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		UserVo vo = JSONObject.parseObject(bean.getUser().toString(), UserVo.class);
		repairsVo.setReportor(vo.getName());
		repairsVo.setReportPhone(vo.getPhone());
		repairsVo.setHandles(handleService.queryByRepairsId(id));
		return repairsVo;
	}

	@Override
	public PageInfo<RepairsVo> pageData(RepairsPageParam params) {
		PageHelper.startPage(params.getPage(), params.getRows());
		List<RepairsVo> list = new ArrayList<RepairsVo>();
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		UserVo vo = JSONObject.parseObject(bean.getUser().toString(), UserVo.class);
		Page<Repairs> page = repairsMapper.pageData(params, vo.getId());
		if (page.size() != 0) {
			for (Repairs repairs : page) {
				RepairsVo repairsVo = new RepairsVo();
				BeanUtils.copyProperties(repairs, repairsVo);
				repairsVo.setReportor(vo.getName());
				repairsVo.setReportPhone(vo.getPhone());
				repairsVo.setHandles(handleService.queryByRepairsId(repairs.getId()));
				list.add(repairsVo);
			}
		}
		return new PageInfo<RepairsVo>(page.getTotal(), list);
	}

	@Override
	public void addData(Repairs repairs) {
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		UserVo vo = JSONObject.parseObject(bean.getUser().toString(), UserVo.class);
		repairs.setReportId(vo.getId());
		repairs.setReportType(1);
		repairs.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		Audit audit = auditService.getAudit(AuditEnums.DirectType.REPAIRS.getValue());
		if (audit != null) {
			repairs.setAudit(audit.getName());
			repairs.setAuditPhone(audit.getPhone());
			repairs.setAuditId(audit.getWorkerId());
		}
		repairsMapper.addData(repairs);
		// 新增处理流程
		Handle handle = new Handle();
		handle.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		handle.setHandleResult("正在为您分配维修人员");
		handle.setRepairsId(repairs.getId());
		handleService.addData(handle);
		// 添加到延时队列
		String appointmentTime = repairs.getAppointmentTime();
		String[] split = appointmentTime.split(" ");
		String[] time = split[1].split("-");
		boolean boo = (DateUtils.getTimeMillis(split[0] + time[0]) - new Date().getTime()) >= 1800000;
		if (boo) {
			long expire = DateUtils.getTimeMillis(split[0] + time[0]) - 1800000;
			delayQueueManager.put(
					new DelayTask(new TaskBase(repairs.getId(), AuditEnums.DirectType.REPAIRS.getValue()), expire));
		}
		// 发通知
		Map<String, Object> data = null;
		try {
			if (audit != null) {
				WorkerVo worker = (WorkerVo) workerService.detailData(audit.getWorkerId());
				if (worker != null && worker.getOpenid() != null && !"".equals(worker.getOpenid())) {
					Boolean bool = redisHelper.existsKey("jy_accessToken");
					String jy_accessToken = "";
					if (!bool) {
						jy_accessToken = baseService.authGetAccessToken(Constant.JY_APPID, Constant.JY_SECRET);
						redisHelper.set("jy_accessToken", jy_accessToken, 60 * 60 * 2);
					}
					jy_accessToken = redisHelper.get("jy_accessToken");
					data = new HashMap<String, Object>();
					Map<String, Object> thing1 = new HashMap<String, Object>();
					thing1.put("value", repairs.getServeAddr());
					Map<String, Object> date3 = new HashMap<String, Object>();
					date3.put("value", repairs.getCreateTime());
					Map<String, Object> thing4 = new HashMap<String, Object>();
					thing4.put("value", "有新的报修，请尽快处理");
					data.put("thing1", thing1);
					data.put("date3", date3);
					data.put("thing4", thing4);
					baseService.subscribeMessageSend(jy_accessToken, worker.getOpenid(),
							"pages/repairsdetails/redetalils/redetalils?id=" + repairs.getId(), Constant.REPAIRDS, data,
							Constant.JY_APPID, Constant.JY_SECRET);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("---------------推送消息失败----------------");
			log.error("推送数据：" + data.toString());
		}

	}

	@Override
	public List<Repairs> queryDelayData() {
		return repairsMapper.queryDelayData();
	}

}
