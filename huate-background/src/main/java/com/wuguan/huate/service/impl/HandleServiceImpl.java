/**   
* @Title: HandleServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月10日 下午2:52:38 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuguan.huate.bean.entity.Handle;
import com.wuguan.huate.db.HandleMapper;
import com.wuguan.huate.service.HandleService;

/** 
* @ClassName: HandleServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月10日 下午2:52:38 
*  
*/
@Service
public class HandleServiceImpl implements HandleService {
	@Autowired
	HandleMapper handleMapper;

	@Override
	public List<Handle> queryByRepairsId(Integer repairsId) {
		return handleMapper.queryByRepairsId(repairsId);
	}

}
