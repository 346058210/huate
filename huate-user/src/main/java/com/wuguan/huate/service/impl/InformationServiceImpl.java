/**   
* @Title: InformationServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月10日 下午3:11:30 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.entity.Information;
import com.wuguan.huate.bean.params.PageParams;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.db.InformationMapper;
import com.wuguan.huate.service.InformationService;

/**
 * @ClassName: InformationServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月10日 下午3:11:30
 * 
 */
@Service
public class InformationServiceImpl implements InformationService {
	@Autowired
	InformationMapper informationMapper;

	@Override
	public PageInfo<Information> queryInformations(PageParams params) {
		PageHelper.startPage(params.getPage(), params.getRows());
		Page<Information> page = informationMapper.queryInformations();
		return new PageInfo<Information>(page.getTotal(), page);
	}

	@Override
	public Information detailData(Integer id) {
		return informationMapper.detailData(id);
	}

}
