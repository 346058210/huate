/**   
* @Title: LiveServeServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月11日 下午2:45:58 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuguan.huate.bean.entity.LiveServe;
import com.wuguan.huate.db.LiveServeMapper;
import com.wuguan.huate.service.LiveServeService;

/** 
* @ClassName: LiveServeServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月11日 下午2:45:58 
*  
*/
@Service
public class LiveServeServiceImpl implements LiveServeService {
	@Autowired
	LiveServeMapper liveServeMapper;

	@Override
	public List<LiveServe> queryLiveServes() {
		return liveServeMapper.queryLiveServes();
	}

	@Override
	public LiveServe detailLiveServe(Integer id) {
		return liveServeMapper.detailLiveServe(id);
	}

}
