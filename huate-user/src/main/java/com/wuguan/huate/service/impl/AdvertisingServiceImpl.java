/**   
* @Title: AdvertisingServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午5:59:55 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuguan.huate.bean.entity.Advertising;
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
	public List<Advertising> queryAdvertisings() {
		return advertisingMapper.queryAdvertisings();
	}

}
