/**   
* @Title: RepairsServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月11日 下午5:47:35 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.entity.Repairs;
import com.wuguan.huate.bean.params.RepairsPageParam;
import com.wuguan.huate.bean.vo.RepairsVo;
import com.wuguan.huate.bean.vo.UserVo;
import com.wuguan.huate.bean.vo.WorkerVo;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.db.RepairsMapper;
import com.wuguan.huate.service.RepairsService;
import com.wuguan.huate.service.UserService;
import com.wuguan.huate.service.WorkerService;

/** 
* @ClassName: RepairsServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月11日 下午5:47:35 
*  
*/
@Service
public class RepairsServiceImpl implements RepairsService {
	@Autowired
	RepairsMapper repairsMapper;
	@Autowired
	WorkerService workerService;
	@Autowired
	UserService userService;
	
	@Override
	public void updateData(Repairs repairs) {
		repairsMapper.updateData(repairs);
	}

	@Override
	public RepairsVo detailData(Integer id) {
		Repairs repairs=repairsMapper.detailData(id);
		RepairsVo repairsVo = new RepairsVo();
		BeanUtils.copyProperties(repairs, repairsVo);
		if (repairs.getReportType()==1) {
			UserVo vo = userService.detail(repairs.getReportId());
			repairsVo.setReportor(vo.getName());
			repairsVo.setReportPhone(vo.getPhone());
		}else {
			WorkerVo workerVo = (WorkerVo) workerService.detailData(repairs.getReportId());
			repairsVo.setReportor(workerVo.getName());
			repairsVo.setReportPhone(workerVo.getPhone());
		}
		return repairsVo;
	}

	@Override
	public PageInfo<RepairsVo> pageData(RepairsPageParam params) {
		PageHelper.startPage(params.getPage(), params.getRows());
		List<RepairsVo> list = new ArrayList<RepairsVo>();
		Page<Repairs> page=repairsMapper.pageData(params);
		if (page.size()!=0) {
			for (Repairs repairs : page) {
				RepairsVo repairsVo = new RepairsVo();
				BeanUtils.copyProperties(repairs, repairsVo);
				if (repairs.getReportType()==1) {
					UserVo vo = userService.detail(repairs.getReportId());
					repairsVo.setReportor(vo.getName());
					repairsVo.setReportPhone(vo.getPhone());
				}else {
					WorkerVo workerVo = (WorkerVo) workerService.detailData(repairs.getReportId());
					repairsVo.setReportor(workerVo.getName());
					repairsVo.setReportPhone(workerVo.getPhone());
				}
				list.add(repairsVo);
			}
		}
		return new PageInfo<RepairsVo>(page.getTotal(), list);
	}

}
