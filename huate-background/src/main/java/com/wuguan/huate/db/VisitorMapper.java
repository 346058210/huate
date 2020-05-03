/**   
* @Title: VisitorMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月11日 下午6:58:55 
* @version V1.0   
*/
package com.wuguan.huate.db;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.wuguan.huate.bean.params.VisitorPageParam;
import com.wuguan.huate.bean.vo.VisitorVo;

/** 
* @ClassName: VisitorMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月11日 下午6:58:55 
*  
*/
public interface VisitorMapper {

	/**
	* @Title: detailData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	VisitorVo detailData(@Param("id")Integer id);

	/**
	* @Title: pageData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param params
	* @return
	*/
	Page<VisitorVo> pageData(VisitorPageParam params);

}
