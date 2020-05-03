/**   
* @Title: VisitorServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月11日 下午6:58:09 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.params.VisitorPageParam;
import com.wuguan.huate.bean.vo.VisitorVo;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.db.VisitorMapper;
import com.wuguan.huate.service.VisitorService;

/** 
* @ClassName: VisitorServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月11日 下午6:58:09 
*  
*/
@Service
public class VisitorServiceImpl implements VisitorService {
	@Autowired
	VisitorMapper visitorMapper;
	@Override
	public VisitorVo detailData(Integer id) {
		return visitorMapper.detailData(id);
	}

	@Override
	public PageInfo<VisitorVo> pageData(VisitorPageParam params) {
		PageHelper.startPage(params.getPage(), params.getRows());
		Page<VisitorVo> page=visitorMapper.pageData(params);
		return new PageInfo<VisitorVo>(page.getTotal(), page);
	}

}
