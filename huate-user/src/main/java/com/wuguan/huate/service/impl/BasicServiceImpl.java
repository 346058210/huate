/**   
* @Title: BasicServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月13日 下午5:15:07 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wuguan.huate.bean.vo.BasicVo;
import com.wuguan.huate.db.BasicMapper;
import com.wuguan.huate.service.BasicService;

/** 
* @ClassName: BasicServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月13日 下午5:15:07 
*  
*/
@Service
public class BasicServiceImpl implements BasicService {
	@Autowired
	BasicMapper basicMapper;
	@Override
	public BasicVo queryBasic() {
		return basicMapper.queryBasic();
	}

}
