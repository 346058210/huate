/**   
* @Title: ComplainServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月10日 上午10:52:00 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.entity.Audit;
import com.wuguan.huate.bean.entity.Complain;
import com.wuguan.huate.bean.enums.AuditEnums;
import com.wuguan.huate.bean.params.ComplainPageParam;
import com.wuguan.huate.bean.vo.ComplainVo;
import com.wuguan.huate.bean.vo.UserVo;
import com.wuguan.huate.bean.vo.WorkerVo;
import com.wuguan.huate.comm.BaseService;
import com.wuguan.huate.comm.Constant;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.comm.SpringUtil;
import com.wuguan.huate.comm.ThreadLocalManager;
import com.wuguan.huate.db.ComplainMapper;
import com.wuguan.huate.service.AuditService;
import com.wuguan.huate.service.ComplainService;
import com.wuguan.huate.service.WorkerService;
import com.wuguan.huate.utils.RedisHelper;

/**
 * @ClassName: ComplainServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月10日 上午10:52:00
 * 
 */
@Service
@Transactional
public class ComplainServiceImpl implements ComplainService {
	@Autowired
	ComplainMapper complainMapper;
	@Autowired
	AuditService auditService;
	@Autowired
	RedisHelper redisHelper;
	@Autowired
	BaseService baseService;
	@Autowired
	WorkerService workerService;
	static final Logger log = LoggerFactory.getLogger("errorLogger");

	@Override
	public void updateData(Complain complain) {
		complainMapper.updateData(complain);
		Map<String, Object> param = null;
		try {
			ComplainVo data = complainMapper.detailData(complain.getId());
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
				param = new HashMap<String, Object>();
				Map<String, Object> thing2 = new HashMap<String, Object>();
				thing2.put("value", "您有新的投诉建议需要处理");
				Map<String, Object> time3 = new HashMap<String, Object>();
				time3.put("value", complain.getCreateTime());
				param.put("thing2", thing2);
				param.put("time3", time3);
				baseService.subscribeMessageSend(jy_accessToken, worker.getOpenid(),
						"pages/complaintlist/complaintdetails/complaintdetails?id=" + complain.getId(),
						Constant.COMPAIN, data, Constant.JY_APPID, Constant.JY_SECRET);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("---------------推送消息失败----------------");
			log.error("推送数据：" + param.toString());
		}
	}

	@Override
	public ComplainVo detailData(Integer id) {
		return complainMapper.detailData(id);
	}

	@Override
	public PageInfo<ComplainVo> pageData(ComplainPageParam param) {
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		UserVo vo = JSONObject.parseObject(bean.getUser().toString(), UserVo.class);
		PageHelper.startPage(param.getPage(), param.getRows());
		Page<ComplainVo> page = complainMapper.pageData(param, vo.getId());
		return new PageInfo<ComplainVo>(page.getTotal(), page);
	}

	@Override
	public void addData(Complain complain) {
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		UserVo vo = JSONObject.parseObject(bean.getUser().toString(), UserVo.class);
		complain.setComplainId(vo.getId());
		complain.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		Audit audit = auditService.getAudit(AuditEnums.DirectType.COMPLAIN.getValue());
		if (audit != null) {
			complain.setAudit(audit.getName());
			complain.setAuditPhone(audit.getPhone());
			complain.setAuditId(audit.getWorkerId());
		}
		complainMapper.addData(complain);
		Map<String, Object> data = null;
		// 发通知
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
					Map<String, Object> thing2 = new HashMap<String, Object>();
					thing2.put("value", "您有新的投诉建议需要处理");
					Map<String, Object> time3 = new HashMap<String, Object>();
					time3.put("value", complain.getCreateTime());
					data.put("thing2", thing2);
					data.put("time3", time3);
					baseService.subscribeMessageSend(jy_accessToken, worker.getOpenid(),
							"pages/complaintlist/complaintdetails/complaintdetails?id=" + complain.getId(),
							Constant.COMPAIN, data, Constant.JY_APPID, Constant.JY_SECRET);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("---------------推送消息失败----------------");
			log.error("推送数据：" + data.toString());
		}
	}

}
