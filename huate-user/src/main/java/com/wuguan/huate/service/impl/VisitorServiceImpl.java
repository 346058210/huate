/**   
* @Title: VisitorServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月11日 下午6:58:09 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.entity.Visitor;
import com.wuguan.huate.bean.params.PageParams;
import com.wuguan.huate.bean.vo.UserVo;
import com.wuguan.huate.bean.vo.VisitorVo;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.comm.SpringUtil;
import com.wuguan.huate.comm.ThreadLocalManager;
import com.wuguan.huate.db.VisitorMapper;
import com.wuguan.huate.service.VisitorService;
import com.wuguan.huate.utils.RandomCode;

/** 
* @ClassName: VisitorServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月11日 下午6:58:09 
*  
*/
@Service
@Transactional
public class VisitorServiceImpl implements VisitorService {
	@Autowired
	VisitorMapper visitorMapper;
	@Override
	public VisitorVo detailData(Integer id) {
		return visitorMapper.detailData(id);
	}

	@Override
	public PageInfo<VisitorVo> pageData(PageParams params) {
		PageHelper.startPage(params.getPage(), params.getRows());
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		UserVo vo = JSONObject.parseObject(bean.getUser().toString(), UserVo.class);
		Page<VisitorVo> page=visitorMapper.pageData(params,vo.getId());
		return new PageInfo<VisitorVo>(page.getTotal(), page);
	}

	@Override
	public void addData(Visitor visitor) {
		String code = RandomCode.randomString();
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		UserVo vo = JSONObject.parseObject(bean.getUser().toString(), UserVo.class);
		visitor.setApplyUserId(vo.getId());
		visitor.setApplyUserName(vo.getName());
		visitor.setApplyUserPhone(vo.getPhone());
		visitor.setCode(code);
		visitor.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		visitorMapper.addData(visitor);
	}

}
