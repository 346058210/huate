/**   
* @Title: RepairsServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月17日 上午12:57:11 
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
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.entity.Audit;
import com.wuguan.huate.bean.entity.Handle;
import com.wuguan.huate.bean.entity.Repairs;
import com.wuguan.huate.bean.entity.User;
import com.wuguan.huate.bean.entity.Worker;
import com.wuguan.huate.bean.enums.AuditEnums;
import com.wuguan.huate.bean.enums.RepairsEnums;
import com.wuguan.huate.bean.params.RepairsPageParam;
import com.wuguan.huate.bean.vo.RepairsVo;
import com.wuguan.huate.comm.BaseService;
import com.wuguan.huate.comm.Constant;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.comm.SpringUtil;
import com.wuguan.huate.comm.ThreadLocalManager;
import com.wuguan.huate.db.RepairsMapper;
import com.wuguan.huate.service.AuditService;
import com.wuguan.huate.service.HandleService;
import com.wuguan.huate.service.RepairsService;
import com.wuguan.huate.service.UserService;
import com.wuguan.huate.service.WorkerService;
import com.wuguan.huate.utils.RedisHelper;
import com.wuguan.huate.web.result.ResultEnums;

/**
 * @ClassName: RepairsServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月17日 上午12:57:11
 * 
 */
@Service
@Transactional
public class RepairsServiceImpl implements RepairsService {
	@Autowired
	RepairsMapper repairsMapper;
	@Autowired
	AuditService auditService;
	@Autowired
	WorkerService workerService;
	@Autowired
	UserService userService;
	@Autowired
	HandleService handleService;
	@Autowired
	BaseService baseService;
	@Autowired
	RedisHelper redisHelper;
	static final Logger log = LoggerFactory.getLogger("errorLogger");

	@Override
	public void addData(Repairs repairs) {
		repairs.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		Worker worker = JSON.parseObject(bean.getUser().toString(), Worker.class);
		repairs.setReportId(worker.getId());
		repairs.setReportType(2);
		Audit audit = auditService.getAudit(AuditEnums.DirectType.REPAIRS.getValue());
		if (audit != null) {
			repairs.setAudit(audit.getName());
			repairs.setAuditPhone(audit.getPhone());
			repairs.setAuditId(audit.getWorkerId());
		}
		repairsMapper.addData(repairs);
		Handle handle = new Handle();
		handle.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		handle.setRepairsId(repairs.getId());
		handle.setHandleResult("正在为您分配维修人员");
		handleService.addData(handle);
		Map<String, Object> params = null;
		try {
			if (repairs.getAuditId() != null) {
				Worker work = workerService.detailData(repairs.getAuditId());
				if (work != null && work.getOpenid() != null && !"".equals(work.getOpenid())) {
					Boolean bool = redisHelper.existsKey("jy_accessToken");
					String accessToken;
					if (!bool) {
						accessToken = baseService.authGetAccessToken(Constant.JY_APPID, Constant.JY_SECRET);
						redisHelper.set("jy_accessToken", accessToken, 60 * 60 * 2);
					}
					accessToken = redisHelper.get("jy_accessToken");
					Map<String, Object> thing1 = new HashMap<String, Object>();// 报修位置
					thing1.put("value", repairs.getServeAddr());
					Map<String, Object> date3 = new HashMap<String, Object>();// 时间
					date3.put("value", repairs.getCreateTime());
					Map<String, Object> thing4 = new HashMap<String, Object>();// 注意事项
					thing4.put("value", "您有新的报修，请及时处理");
					params = new HashMap<String, Object>();
					params.put("thing1", thing1);
					params.put("date3", date3);
					params.put("thing4", thing4);
					baseService.subscribeMessageSend(accessToken, work.getOpenid(),
							"pages/repairsdetails/redetalils/redetalils?id=" + repairs.getId(), Constant.REPAIRDS,
							params, Constant.JY_APPID, Constant.JY_SECRET);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("-----------消息推送失败------------");
			log.error("推送消息：" + params.toString());
		}
	}

	@Override
	public void allotHandle(Repairs repairs) {
		repairsMapper.allotHandle(repairs);
		Handle handle = new Handle();
		handle.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		handle.setRepairsId(repairs.getId());
		handle.setHandleResult("待联系|上门  维修人员" + " " + repairs.getHandler() + " " + repairs.getHandlePhone());
		handleService.addData(handle);
		Map<String, Object> params = null;
		try {
			if (repairs.getHandlerId() != null) {
				Worker work = workerService.detailData(repairs.getHandlerId());
				if (work != null && work.getOpenid() != null && !"".equals(work.getOpenid())) {
					Boolean bool = redisHelper.existsKey("jy_accessToken");
					String accessToken;
					if (!bool) {
						accessToken = baseService.authGetAccessToken(Constant.JY_APPID, Constant.JY_SECRET);
						redisHelper.set("jy_accessToken", accessToken, 60 * 60 * 2);
					}
					accessToken = redisHelper.get("jy_accessToken");
					Map<String, Object> thing1 = new HashMap<String, Object>();// 报修位置
					thing1.put("value", repairs.getServeAddr());
					Map<String, Object> date3 = new HashMap<String, Object>();// 时间
					date3.put("value", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					Map<String, Object> thing4 = new HashMap<String, Object>();// 注意事项
					thing4.put("value", "您有新的报修，请及时处理");
					params = new HashMap<String, Object>();
					params.put("thing1", thing1);
					params.put("date3", date3);
					params.put("thing4", thing4);
					baseService.subscribeMessageSend(accessToken, work.getOpenid(),
							"pages/repairsdetails/redetalils/redetalils?id=" + repairs.getId(), Constant.REPAIRDS,
							params, Constant.JY_APPID, Constant.JY_SECRET);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("-----------消息推送失败------------");
			log.error("推送消息：" + params.toString());
		}
	}

	@Override
	public RepairsVo detailData(Integer id) {
		Repairs repairs = repairsMapper.detailData(id);
		if (repairs == null) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "ID为" + id + "的数据不存在");
		}
		RepairsVo repairsVo = new RepairsVo();
		BeanUtils.copyProperties(repairs, repairsVo);
		if (repairs.getReportType() == RepairsEnums.RepaortTypeEnum.USER.getValue()) {
			User user = userService.detail(repairs.getReportId());
			repairsVo.setReportor(user.getName());
			repairsVo.setReportPhone(user.getPhone());
		} else if (repairs.getReportType() == RepairsEnums.RepaortTypeEnum.WORKER.getValue()) {
			Worker worker = workerService.detailData(repairs.getReportId());
			repairsVo.setReportor(worker.getName());
			repairsVo.setReportPhone(worker.getPhone());
		}
		List<Handle> list = handleService.queryByRepairsId(id);
		repairsVo.setHandles(list);
		return repairsVo;
	}

	@Override
	public PageInfo<RepairsVo> pageData(RepairsPageParam param) {
		PageHelper.startPage(param.getPage(), param.getRows());
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		Worker worker = JSON.parseObject(bean.getUser().toString(), Worker.class);
		List<RepairsVo> list = new ArrayList<RepairsVo>();
		Page<Repairs> page = repairsMapper.pageData(param, worker.getId());
		if (page.size() != 0) {
			for (Repairs repairs : page) {
				RepairsVo vo = new RepairsVo();
				BeanUtils.copyProperties(repairs, vo);
				if (repairs.getReportType() == RepairsEnums.RepaortTypeEnum.USER.getValue()) {
					User user = userService.detail(repairs.getReportId());
					vo.setReportor(user.getName());
					vo.setReportPhone(user.getPhone());
				} else if (repairs.getReportType() == RepairsEnums.RepaortTypeEnum.WORKER.getValue()) {
					Worker work = workerService.detailData(repairs.getReportId());
					vo.setReportor(work.getName());
					vo.setReportPhone(work.getPhone());
				}
				list.add(vo);
				List<Handle> Handles = handleService.queryByRepairsId(repairs.getId());
				vo.setHandles(Handles);
			}
		}
		return new PageInfo<RepairsVo>(page.getTotal(), list);
	}

	@Override
	public PageInfo<RepairsVo> pageHandleData(RepairsPageParam param) {
		PageHelper.startPage(param.getPage(), param.getRows());
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		Worker worker = JSON.parseObject(bean.getUser().toString(), Worker.class);
		List<RepairsVo> list = new ArrayList<RepairsVo>();
		Page<Repairs> page = repairsMapper.pageHandleData(param, worker.getId());
		if (page.size() != 0) {
			for (Repairs repairs : page) {
				RepairsVo vo = new RepairsVo();
				BeanUtils.copyProperties(repairs, vo);
				if (repairs.getReportType() == RepairsEnums.RepaortTypeEnum.USER.getValue()) {
					User user = userService.detail(repairs.getReportId());
					vo.setReportor(user.getName());
					vo.setReportPhone(user.getPhone());
				} else if (repairs.getReportType() == RepairsEnums.RepaortTypeEnum.WORKER.getValue()) {
					Worker work = workerService.detailData(repairs.getReportId());
					vo.setReportor(work.getName());
					vo.setReportPhone(work.getPhone());
				}
				List<Handle> Handles = handleService.queryByRepairsId(repairs.getId());
				vo.setHandles(Handles);
			}
		}
		return new PageInfo<RepairsVo>(page.getTotal(), list);
	}

	@Override
	public void handle(Repairs repairs) {
		repairsMapper.allotHandle(repairs);
	}

	@Override
	public void handleOver(Repairs repairs) {
		repairsMapper.allotHandle(repairs);
		// 新增处理流程
		Handle handle = new Handle();
		handle.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		handle.setHandleResult("您的报修已完成，如有疑问请联系客服");
		handle.setRepairsId(repairs.getId());
		handleService.addData(handle);
		Map<String, Object> params = null;
		try {
			Repairs data = repairsMapper.detailData(repairs.getId());
			if (data.getReportType() == RepairsEnums.RepaortTypeEnum.USER.getValue()) {
				User user = userService.detail(repairs.getReportId());
				if (user != null && user.getOpenid() != null && !"".equals(user.getOpenid())) {
					Boolean bool = redisHelper.existsKey("xj_accessToken");
					String accessToken;
					if (!bool) {
						accessToken = baseService.authGetAccessToken(Constant.XJ_APPID, Constant.XJ_SECRET);
						redisHelper.set("xj_accessToken", accessToken, 60 * 60 * 2);
					}
					accessToken = redisHelper.get("xj_accessToken");
					Map<String, Object> phrase1 = new HashMap<String, Object>();// 报修状态
					phrase1.put("value", "已处理");
					Map<String, Object> time2 = new HashMap<String, Object>();// 时间
					time2.put("value", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					Map<String, Object> thing3 = new HashMap<String, Object>();// 报修反馈
					thing3.put("value", "您的报修已处理完成");
					params = new HashMap<String, Object>();
					params.put("phrase1", phrase1);
					params.put("time2", time2);
					params.put("thing3", thing3);
					baseService.subscribeMessageSend(accessToken, user.getOpenid(),
							"pages/repairsdetails/redetalils/redetalils?id=" + repairs.getId(),
							Constant.XJ_REPAIRDS_RESULT, params, Constant.XJ_APPID, Constant.XJ_SECRET);
				}
			} else if (data.getReportType() == RepairsEnums.RepaortTypeEnum.WORKER.getValue()) {
				Worker work = workerService.detailData(repairs.getReportId());
				if (work != null && work.getOpenid() != null && !"".equals(work.getOpenid())) {
					Boolean bool = redisHelper.existsKey("jy_accessToken");
					String accessToken;
					if (!bool) {
						accessToken = baseService.authGetAccessToken(Constant.JY_APPID, Constant.JY_SECRET);
						redisHelper.set("jy_accessToken", accessToken, 60 * 60 * 2);
					}
					accessToken = redisHelper.get("jy_accessToken");
					Map<String, Object> phrase1 = new HashMap<String, Object>();// 报修状态
					phrase1.put("value", "已处理");
					Map<String, Object> time2 = new HashMap<String, Object>();// 时间
					time2.put("value", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					Map<String, Object> thing3 = new HashMap<String, Object>();// 报修反馈
					thing3.put("value", "您的报修已处理完成");
					params = new HashMap<String, Object>();
					params.put("phrase1", phrase1);
					params.put("time2", time2);
					params.put("thing3", thing3);
					baseService.subscribeMessageSend(accessToken, work.getOpenid(),
							"pages/repairsdetails/redetalils/redetalils?id=" + repairs.getId(),
							Constant.REPAIRDS_RESULT, params, Constant.JY_APPID, Constant.JY_SECRET);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("-----------消息推送失败------------");
			log.error("推送消息：" + params.toString());
		}
	}

	@Override
	public void revocation(Repairs repairs) {
		repairsMapper.allotHandle(repairs);
		// 新增处理流程
		Handle handle = new Handle();
		handle.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		handle.setHandleResult("您的报修已撤销，如有疑问请联系客服");
		handle.setRepairsId(repairs.getId());
		handleService.addData(handle);
		Repairs data = repairsMapper.detailData(repairs.getId());
		Map<String, Object> params = null;
		try {
			Worker work = null;
			if (data.getHandlerId() != null) {
				work = workerService.detailData(data.getHandlerId());
			} else if (data.getAuditId() != null) {
				work = workerService.detailData(data.getAuditId());
			}
			if (work != null && work.getOpenid() != null && !"".equals(work.getOpenid())) {
				Boolean bool = redisHelper.existsKey("jy_accessToken");
				String accessToken;
				if (!bool) {
					accessToken = baseService.authGetAccessToken(Constant.JY_APPID, Constant.JY_SECRET);
					redisHelper.set("jy_accessToken", accessToken, 60 * 60 * 2);
				}
				accessToken = redisHelper.get("jy_accessToken");
				Map<String, Object> thing1 = new HashMap<String, Object>();// 报修位置
				thing1.put("value", repairs.getServeAddr());
				Map<String, Object> date3 = new HashMap<String, Object>();// 时间
				date3.put("value", repairs.getCreateTime());
				Map<String, Object> thing4 = new HashMap<String, Object>();// 注意事项
				thing4.put("value", "您有新的报修，请及时处理");
				params = new HashMap<String, Object>();
				params.put("thing1", thing1);
				params.put("date3", date3);
				params.put("thing4", thing4);
				baseService.subscribeMessageSend(accessToken, work.getOpenid(),
						"pages/repairsdetails/redetalils/redetalils?id=" + repairs.getId(), Constant.REPAIRDS, params,Constant.JY_APPID, Constant.JY_SECRET);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("-----------消息推送失败------------");
			log.error("推送消息：" + params.toString());
		}

	}

	@Override
	public List<Repairs> queryDelayData() {
		return repairsMapper.queryDelayData();
	}

}
