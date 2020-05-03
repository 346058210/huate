/**   
* @Title: ComplainServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月10日 上午10:52:00 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.entity.Complain;
import com.wuguan.huate.bean.params.ComplainPageParam;
import com.wuguan.huate.bean.vo.ComplainVo;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.db.ComplainMapper;
import com.wuguan.huate.service.ComplainService;

/** 
* @ClassName: ComplainServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月10日 上午10:52:00 
*  
*/
@Service
public class ComplainServiceImpl implements ComplainService {
	@Autowired
	ComplainMapper complainMapper;

	@Override
	public void updateData(Complain complain) {
		complainMapper.updateData(complain);
	}

	@Override
	public ComplainVo detailData(Integer id) {
		return complainMapper.detailData(id);
	}

	@Override
	public PageInfo<ComplainVo> pageData(ComplainPageParam param) {
		PageHelper.startPage(param.getPage(), param.getRows());
		Page<ComplainVo> page=complainMapper.pageData(param);
		return new PageInfo<ComplainVo>(page.getTotal(), page);
	}

}
