/**   
* @Title: AppointRecordServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午10:13:55 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.entity.AppointRecord;
import com.wuguan.huate.bean.entity.Worker;
import com.wuguan.huate.bean.params.AppointRecordPageParam;
import com.wuguan.huate.bean.vo.AppointRecordVo;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.comm.SpringUtil;
import com.wuguan.huate.comm.ThreadLocalManager;
import com.wuguan.huate.db.AppointRecordMapper;
import com.wuguan.huate.service.AppointRecordService;

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

	@Override
	public AppointRecordVo detailData(Integer id) {
		return appointRecordMapper.detailData(id);
	}

	@Override
	public PageInfo<AppointRecordVo> pageData(AppointRecordPageParam param) {
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		Worker vo = JSONObject.parseObject(bean.getUser().toString(), Worker.class);
		PageHelper.startPage(param.getPage(), param.getRows());
		Page<AppointRecordVo> page = appointRecordMapper.pageData(param,vo.getId());
		return new PageInfo<AppointRecordVo>(page.getTotal(), page);
	}

	@Override
	public void updateData(AppointRecord appointRecord) {
		appointRecordMapper.updateData(appointRecord);
	}

	@Override
	public List<AppointRecordVo> queryDelayData() {
		return appointRecordMapper.queryDelayData();
	}

}
