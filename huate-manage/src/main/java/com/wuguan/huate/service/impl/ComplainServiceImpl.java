/**   
* @Title: ComplainServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月16日 下午10:13:34 
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

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.entity.Complain;
import com.wuguan.huate.bean.entity.User;
import com.wuguan.huate.bean.entity.Worker;
import com.wuguan.huate.bean.params.ComplainPageParam;
import com.wuguan.huate.bean.vo.ComplainVo;
import com.wuguan.huate.comm.BaseService;
import com.wuguan.huate.comm.Constant;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.comm.SpringUtil;
import com.wuguan.huate.comm.ThreadLocalManager;
import com.wuguan.huate.db.ComplainMapper;
import com.wuguan.huate.service.ComplainService;
import com.wuguan.huate.service.UserService;
import com.wuguan.huate.service.WorkerService;
import com.wuguan.huate.utils.RedisHelper;

/**
 * @ClassName: ComplainServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月16日 下午10:13:34
 * 
 */
@Service
@Transactional
public class ComplainServiceImpl implements ComplainService {
	@Autowired
	ComplainMapper complainMapper;
	@Autowired
	BaseService baseService;
	@Autowired
	RedisHelper redisHelper;
	@Autowired
	WorkerService workerService;
	@Autowired
	UserService userService;
	static final Logger log = LoggerFactory.getLogger("errorLogger");

	@Override
	public PageInfo<ComplainVo> pageData(ComplainPageParam param) {
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		Worker worker = JSON.parseObject(bean.getUser().toString(), Worker.class);
		PageHelper.startPage(param.getPage(), param.getRows());
		Page<ComplainVo> page = complainMapper.pageData(param, worker.getId());
		return new PageInfo<ComplainVo>(page.getTotal(), page);
	}

	@Override
	public ComplainVo detailData(Integer id) {
		return complainMapper.detailData(id);
	}

	/**
	 * 指派
	 */
	@Override
	public void allotHandle(Complain complain) {
		complainMapper.allotHandle(complain);
		Map<String, Object> params = null;
		try {
			if (complain.getHandlerId() != null && !"".equals(complain.getHandlerId())) {
				Worker worker = workerService.detailData(complain.getHandlerId());
				if (worker != null && worker.getOpenid() != null && !"".equals(worker.getOpenid())) {
					Boolean bool = redisHelper.existsKey("jy_accessToken");
					String accessToken;
					if (!bool) {
						accessToken = baseService.authGetAccessToken(Constant.JY_APPID, Constant.JY_SECRET);
						redisHelper.set("jy_accessToken", accessToken, 60 * 60 * 2);
					}
					accessToken = redisHelper.get("jy_accessToken");
					Map<String, Object> thing2 = new HashMap<String, Object>();// 投诉建议
					thing2.put("value", "您有新的投诉建议，请及时处理");
					Map<String, Object> time3 = new HashMap<String, Object>();// 时间
					time3.put("value", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					params = new HashMap<String, Object>();
					params.put("thing2", thing2);
					params.put("time3", time3);
					baseService.subscribeMessageSend(accessToken, worker.getOpenid(),
							"pages/complaintlist/complaintdetails/complaintdetails?id=" + complain.getId(),
							Constant.COMPAIN, params, Constant.JY_APPID, Constant.JY_SECRET);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("-----------消息推送失败------------");
			log.error("推送消息：" + params.toString());
		}
	}

	@Override
	public void handle(Complain complain) {
		complain.setHandleTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		complainMapper.allotHandle(complain);
	}

	/**
	 * 
	 */
	@Override
	public void revocation(Complain complain) {
		complainMapper.allotHandle(complain);
		Map<String, Object> params = null;
		try {
			ComplainVo vo = complainMapper.detailData(complain.getId());
			Worker worker = null;
			if (vo.getHandlerId() != null) {
				worker = workerService.detailData(vo.getHandlerId());
			} else if (vo.getAuditId() != null) {
				worker = workerService.detailData(vo.getAuditId());
			}
			if (worker != null && worker.getOpenid() != null && !"".equals(worker.getOpenid())) {
				Boolean bool = redisHelper.existsKey("jy_accessToken");
				String accessToken;
				if (!bool) {
					accessToken = baseService.authGetAccessToken(Constant.JY_APPID, Constant.JY_SECRET);
					redisHelper.set("jy_accessToken", accessToken, 60 * 60 * 2);
				}
				accessToken = redisHelper.get("jy_accessToken");
				Map<String, Object> thing2 = new HashMap<String, Object>();// 投诉建议
				thing2.put("value", "您有新的投诉建议，请及时处理");
				Map<String, Object> time3 = new HashMap<String, Object>();// 时间
				time3.put("value", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				params = new HashMap<String, Object>();
				params.put("thing2", thing2);
				params.put("time3", time3);
				baseService.subscribeMessageSend(accessToken, worker.getOpenid(),
						"pages/complaintlist/complaintdetails/complaintdetails?id=" + complain.getId(),
						Constant.COMPAIN, params, Constant.JY_APPID, Constant.JY_SECRET);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("-----------消息推送失败------------");
			log.error("推送消息：" + params.toString());
		}

	}

	/**
	 * 处理完
	 */
	@Override
	public void handleOver(Complain complain) {
		complainMapper.allotHandle(complain);
		Map<String, Object> params = null;
		try {
			ComplainVo vo = complainMapper.detailData(complain.getId());
			User detail = userService.detail(vo.getComplainId());
			if (detail != null && detail.getOpenid() != null && !"".equals(detail.getOpenid())) {
				Boolean bool = redisHelper.existsKey("xj_accessToken");
				String accessToken;
				if (!bool) {
					accessToken = baseService.authGetAccessToken(Constant.XJ_APPID, Constant.XJ_SECRET);
					redisHelper.set("xj_accessToken", accessToken, 60 * 60 * 2);
				}
				accessToken = redisHelper.get("xj_accessToken");
				Map<String, Object> thing4 = new HashMap<String, Object>();// 状态
				thing4.put("value", "已处理");
				Map<String, Object> thing5 = new HashMap<String, Object>();// 反馈
				thing5.put("value", "您提交的投诉建议已处理");
				Map<String, Object> time3 = new HashMap<String, Object>();// 时间
				time3.put("value", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				params = new HashMap<String, Object>();
				params.put("thing4", thing4);
				params.put("thing5", thing5);
				params.put("time3", time3);
				baseService.subscribeMessageSend(accessToken, detail.getOpenid(),
						"pages/complaintlist/complaintdetails/complaintdetails?id=" + complain.getId(),
						Constant.COMPAIN_RESULT, params,Constant.XJ_APPID, Constant.XJ_SECRET);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("-----------消息推送失败------------");
			log.error("推送消息：" + params.toString());
		}
	}

}
