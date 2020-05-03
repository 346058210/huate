/**   
* @Title: AdvertisingServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午5:59:55 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.entity.Advertising;
import com.wuguan.huate.bean.params.AdvertisingPageParam;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.db.AdvertisingMapper;
import com.wuguan.huate.service.AdvertisingService;

/** 
* @ClassName: AdvertisingServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月9日 下午5:59:55 
*  
*/
@Service
@Transactional
public class AdvertisingServiceImpl implements AdvertisingService {
	@Autowired
	AdvertisingMapper advertisingMapper;

	@Override
	public void addData(Advertising advertising) {
		advertising.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())); 
		advertisingMapper.addData(advertising);
	}

	@Override
	public void updateData(Advertising advertising) {
		advertisingMapper.updateData(advertising);
	}

	@Override
	public void delData(Integer id) {
		Advertising advertising = new Advertising();
		advertising.setId(id);
		advertising.setIsDel(1);
		advertisingMapper.updateData(advertising);
	}

	@Override
	public Advertising detailData(Integer id) {
		return advertisingMapper.detailData(id);
	}

	@Override
	public PageInfo<Advertising> pageData(AdvertisingPageParam param) {
		PageHelper.startPage(param.getPage(), param.getRows());
		Page<Advertising> page=advertisingMapper.pageData(param);
		return new PageInfo<Advertising>(page.getTotal(), page);
	}

}
